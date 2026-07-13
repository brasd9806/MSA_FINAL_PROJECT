package com.sparta.msa.user.address.exception;

import com.sparta.msa.common.BusinessException;

public class AddressException extends BusinessException {

    public AddressException(AddressErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
