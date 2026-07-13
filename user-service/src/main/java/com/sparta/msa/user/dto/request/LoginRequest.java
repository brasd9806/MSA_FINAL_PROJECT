package com.sparta.msa.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password;
}
