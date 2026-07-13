package com.sparta.msa.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank(message = "아이디는 필수입니다.")
    @Email(message = "아이디는 이메일 형식이어야 합니다.")
    String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
        message = "비밀번호는 8자 이상이며 숫자와 특수문자를 포함해야 합니다."
    )
    String password;

    @NotBlank(message = "이름은 필수입니다.")
    String userNm;

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$", message = "유효한 휴대폰 번호 형식이 아닙니다.")
    String phoneNumber;
}
