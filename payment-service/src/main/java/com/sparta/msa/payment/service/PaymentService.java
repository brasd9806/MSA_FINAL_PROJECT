package com.sparta.msa.payment.service;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.payment.client.OrderClient;
import com.sparta.msa.payment.client.dto.OrderResponse;
import com.sparta.msa.payment.dto.request.CancelRequest;
import com.sparta.msa.payment.dto.response.PaymentLogResponse;
import com.sparta.msa.payment.dto.response.PaymentResponse;
import com.sparta.msa.payment.entity.Payment;
import com.sparta.msa.payment.entity.PaymentLog;
import com.sparta.msa.payment.enums.PaymentAction;
import com.sparta.msa.payment.enums.PaymentStatus;
import com.sparta.msa.payment.exception.PaymentErrorCode;
import com.sparta.msa.payment.exception.PaymentException;
import com.sparta.msa.payment.repository.PaymentLogRepository;
import com.sparta.msa.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final OrderClient orderClient;

    // 결제
    @Transactional
    public PaymentResponse pay(Long orderId) {
        ApiResponse<OrderResponse> response = orderClient.getOrder(orderId);
        OrderResponse order = response.getData();

        if (!"PENDING".equals(order.getStatus())) {
            throw new PaymentException(PaymentErrorCode.ORDER_NOT_PAYABLE);
        }

        Payment payment = paymentRepository.save(Payment.builder()
                .orderId(orderId)
                .totalAmount(order.getTotalPrice())
                .build());

        paymentLogRepository.save(PaymentLog.builder()
                .payment(payment)
                .action(PaymentAction.APPROVE)
                .amount(order.getTotalPrice())
                .description("결제 승인")
                .build());

        return PaymentResponse.from(payment);
    }

    // 결제 조회 (주문 기준)
    public List<PaymentResponse> list(Long orderId) {
        return paymentRepository.findAllByOrderId(orderId).stream()
                .map(PaymentResponse::from)
                .toList();
    }

    // 결제 로그 조회
    public List<PaymentLogResponse> logs(Long paymentId) {
        return paymentLogRepository.findAllByPaymentId(paymentId).stream()
                .map(PaymentLogResponse::from)
                .toList();
    }

    // 취소 (전액/부분 취소)
    @Transactional
    public PaymentResponse cancel(Long paymentId, CancelRequest request) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.PAYMENT_NOT_FOUND));

        if (payment.currentStatus() == PaymentStatus.CANCELLED) {
            throw new PaymentException(PaymentErrorCode.ALREADY_FULLY_CANCELLED);
        }

        if (request.getAmount() > payment.remainingAmount()) {
            throw new PaymentException(PaymentErrorCode.CANCEL_AMOUNT_EXCEEDS);
        }

        payment.addCancelledAmount(request.getAmount());

        boolean isFullCancel = payment.currentStatus() == PaymentStatus.CANCELLED;
        PaymentAction action = isFullCancel ? PaymentAction.CANCEL : PaymentAction.PARTIAL_CANCEL;
        String description = request.getDescription() != null ? request.getDescription()
                : (isFullCancel ? "전액 취소" : "부분 취소");

        paymentLogRepository.save(PaymentLog.builder()
                .payment(payment)
                .action(action)
                .amount(request.getAmount())
                .description(description)
                .build());

        if (isFullCancel) {
            orderClient.cancelOrder(payment.getOrderId());
        }

        return PaymentResponse.from(payment);
    }
}
