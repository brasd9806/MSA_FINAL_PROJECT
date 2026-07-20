package com.sparta.msa.coupon.dto.response;

import com.sparta.msa.coupon.entity.UserCoupon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCouponResponse {

    Long id;
    Long userId;
    CouponResponse coupon;
    Boolean used;
    LocalDateTime issuedAt;
    LocalDateTime usedAt;

    public static UserCouponResponse from(UserCoupon userCoupon) {
        return UserCouponResponse.builder()
                .id(userCoupon.getId())
                .userId(userCoupon.getUserId())
                .coupon(CouponResponse.from(userCoupon.getCoupon()))
                .used(userCoupon.getUsed())
                .issuedAt(userCoupon.getIssuedAt())
                .usedAt(userCoupon.getUsedAt())
                .build();
    }
}
