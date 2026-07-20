package com.sparta.msa.payment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode {

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 결제입니다."),
    ORDER_NOT_PAYABLE(HttpStatus.BAD_REQUEST, "결제할 수 없는 주문 상태입니다."),
    CANCEL_AMOUNT_EXCEEDS(HttpStatus.BAD_REQUEST, "취소 금액이 잔여 결제금액을 초과합니다."),
    ALREADY_FULLY_CANCELLED(HttpStatus.BAD_REQUEST, "이미 전액 취소된 결제입니다.");

    private final HttpStatus status;
    private final String message;
}
