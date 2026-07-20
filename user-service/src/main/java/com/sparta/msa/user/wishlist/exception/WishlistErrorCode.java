package com.sparta.msa.user.wishlist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WishlistErrorCode {
    WISHLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "위시리스트 항목을 찾을 수 없습니다."),
    ALREADY_IN_WISHLIST(HttpStatus.CONFLICT, "이미 위시리스트에 추가된 상품입니다.");

    private final HttpStatus status;
    private final String message;
}
