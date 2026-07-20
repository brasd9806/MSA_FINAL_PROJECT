package com.sparta.msa.payment.dto.response;

import com.sparta.msa.payment.entity.Payment;
import com.sparta.msa.payment.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {

    Long id;
    Long orderId;
    Long totalAmount;
    Long cancelledAmount;
    Long remainingAmount;
    PaymentStatus status;
    LocalDateTime createdAt;

    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .totalAmount(payment.getTotalAmount())
                .cancelledAmount(payment.getCancelledAmount())
                .remainingAmount(payment.remainingAmount())
                .status(payment.currentStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
