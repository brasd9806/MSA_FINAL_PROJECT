package com.sparta.msa.product.category.exception;

import com.sparta.msa.common.BusinessException;

public class CategoryException extends BusinessException {

    public CategoryException(CategoryErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
