package com.sparta.msa.cart.exception;

import com.sparta.msa.common.BusinessException;

public class CartException extends BusinessException {

    public CartException(CartErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
