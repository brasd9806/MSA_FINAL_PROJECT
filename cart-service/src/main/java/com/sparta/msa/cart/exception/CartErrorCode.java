package com.sparta.msa.cart.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CartErrorCode {

    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니에 존재하지 않는 상품입니다.");

    private final HttpStatus status;
    private final String message;
}
