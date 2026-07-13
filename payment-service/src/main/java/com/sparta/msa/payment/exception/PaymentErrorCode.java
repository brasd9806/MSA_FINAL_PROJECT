package com.sparta.msa.payment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode {

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 결제입니다."),
    PAYMENT_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "이미 완료된 결제입니다."),
    ORDER_NOT_PAYABLE(HttpStatus.BAD_REQUEST, "결제할 수 없는 주문 상태입니다.");

    private final HttpStatus status;
    private final String message;
}
