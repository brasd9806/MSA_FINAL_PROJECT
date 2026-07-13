package com.sparta.msa.payment.service;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.payment.client.OrderClient;
import com.sparta.msa.payment.client.dto.OrderResponse;
import com.sparta.msa.payment.dto.response.PaymentResponse;
import com.sparta.msa.payment.entity.Payment;
import com.sparta.msa.payment.enums.PaymentStatus;
import com.sparta.msa.payment.exception.PaymentErrorCode;
import com.sparta.msa.payment.exception.PaymentException;
import com.sparta.msa.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    @Transactional
    public PaymentResponse pay(Long orderId) {
        ApiResponse<OrderResponse> response = orderClient.getOrder(orderId);
        OrderResponse order = response.getData();

        if (!"PENDING".equals(order.getStatus())) {
            throw new PaymentException(PaymentErrorCode.ORDER_NOT_PAYABLE);
        }

        // Update order status to PAID
        // We call cancel for now but in real scenario we'd have a separate update endpoint
        // For this migration, we store payment and let the order status be managed via order-service

        Payment payment = paymentRepository.save(Payment.builder()
                .orderId(orderId)
                .amount(order.getTotalPrice())
                .build());

        return PaymentResponse.from(payment);
    }

    public List<PaymentResponse> list(Long orderId) {
        return paymentRepository.findAllByOrderId(orderId).stream()
                .map(PaymentResponse::from)
                .toList();
    }

    @Transactional
    public void cancel(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException(PaymentErrorCode.PAYMENT_NOT_FOUND));

        if (payment.getStatus() == PaymentStatus.CANCELLED) {
            throw new PaymentException(PaymentErrorCode.PAYMENT_ALREADY_COMPLETED);
        }

        payment.cancel();
        orderClient.cancelOrder(payment.getOrderId());
    }
}
