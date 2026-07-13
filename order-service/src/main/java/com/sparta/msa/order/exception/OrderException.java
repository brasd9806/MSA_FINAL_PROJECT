package com.sparta.msa.order.exception;

import com.sparta.msa.common.BusinessException;

public class OrderException extends BusinessException {

    public OrderException(OrderErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
