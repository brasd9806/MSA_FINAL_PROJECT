package com.sparta.msa.payment.dto.response;

import com.sparta.msa.payment.entity.PaymentLog;
import com.sparta.msa.payment.enums.PaymentAction;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentLogResponse {

    Long id;
    Long paymentId;
    PaymentAction action;
    Long amount;
    String description;
    LocalDateTime createdAt;

    public static PaymentLogResponse from(PaymentLog log) {
        return PaymentLogResponse.builder()
                .id(log.getId())
                .paymentId(log.getPayment().getId())
                .action(log.getAction())
                .amount(log.getAmount())
                .description(log.getDescription())
                .createdAt(log.getCreatedAt())
                .build();
    }
}
