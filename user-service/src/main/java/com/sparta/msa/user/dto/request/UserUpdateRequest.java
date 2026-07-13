package com.sparta.msa.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String userNm;

    @Pattern(regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$", message = "유효한 휴대폰 번호 형식이 아닙니다.")
    String phoneNumber;
}
