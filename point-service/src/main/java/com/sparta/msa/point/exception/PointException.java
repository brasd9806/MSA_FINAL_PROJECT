package com.sparta.msa.point.exception;

import com.sparta.msa.common.BusinessException;

public class PointException extends BusinessException {

    public PointException(PointErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
