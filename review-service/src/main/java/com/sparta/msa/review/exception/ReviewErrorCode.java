package com.sparta.msa.review.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 리뷰를 작성하셨습니다."),
    REVIEW_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "본인의 리뷰만 수정/삭제할 수 있습니다.");

    private final HttpStatus status;
    private final String message;
}
