package com.sparta.msa.user.dto.response;

import com.sparta.msa.user.entity.User;
import com.sparta.msa.user.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;
    String userId;
    String userNm;
    String phoneNumber;
    UserRole role;
    LocalDateTime createdAt;

    private UserResponse(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.userNm = user.getUserNm();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    public static UserResponse from(User user) {
        return new UserResponse(user);
    }
}
