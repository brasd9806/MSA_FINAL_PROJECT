package com.sparta.msa.coupon.dto.response;

import com.sparta.msa.coupon.entity.Coupon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponResponse {

    Long id;
    String code;
    String name;
    Integer discountRate;
    Long discountAmount;
    Long minOrderPrice;
    Integer maxUseCount;
    Integer currentUseCount;
    LocalDateTime expiredAt;
    LocalDateTime createdAt;

    public static CouponResponse from(Coupon coupon) {
        return CouponResponse.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .name(coupon.getName())
                .discountRate(coupon.getDiscountRate())
                .discountAmount(coupon.getDiscountAmount())
                .minOrderPrice(coupon.getMinOrderPrice())
                .maxUseCount(coupon.getMaxUseCount())
                .currentUseCount(coupon.getCurrentUseCount())
                .expiredAt(coupon.getExpiredAt())
                .createdAt(coupon.getCreatedAt())
                .build();
    }
}
