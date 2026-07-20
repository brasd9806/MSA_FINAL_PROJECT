package com.sparta.msa.coupon.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CouponCreateRequest {

    String code;
    String name;
    Integer discountRate;
    Long discountAmount;
    Long minOrderPrice;
    Integer maxUseCount;
    LocalDateTime expiredAt;
}
