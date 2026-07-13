package com.sparta.msa.user.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {

    String token;

    public static LoginResponse of(String token) {
        return LoginResponse.builder().token(token).build();
    }
}
