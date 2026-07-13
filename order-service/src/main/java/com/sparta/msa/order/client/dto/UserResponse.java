package com.sparta.msa.order.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    Long id;
    String userId;
    String userNm;
    String phoneNumber;
}
