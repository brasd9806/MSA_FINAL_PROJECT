package com.sparta.msa.user.wishlist.exception;

import com.sparta.msa.common.BusinessException;

public class WishlistException extends BusinessException {
    public WishlistException(WishlistErrorCode errorCode) {
        super(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
    }
}
