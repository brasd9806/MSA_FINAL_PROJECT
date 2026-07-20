package com.sparta.msa.coupon.exception;

import com.sparta.msa.common.BusinessException;

public class CouponException extends BusinessException {

    public CouponException(CouponErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
