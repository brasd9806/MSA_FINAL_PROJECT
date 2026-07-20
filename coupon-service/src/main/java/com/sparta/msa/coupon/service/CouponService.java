package com.sparta.msa.coupon.service;

import com.sparta.msa.coupon.dto.request.CouponCreateRequest;
import com.sparta.msa.coupon.dto.response.CouponResponse;
import com.sparta.msa.coupon.dto.response.UserCouponResponse;
import com.sparta.msa.coupon.entity.Coupon;
import com.sparta.msa.coupon.entity.UserCoupon;
import com.sparta.msa.coupon.exception.CouponErrorCode;
import com.sparta.msa.coupon.exception.CouponException;
import com.sparta.msa.coupon.repository.CouponRepository;
import com.sparta.msa.coupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public CouponResponse create(CouponCreateRequest request) {
        Coupon coupon = Coupon.builder()
                .code(request.getCode())
                .name(request.getName())
                .discountRate(request.getDiscountRate() != null ? request.getDiscountRate() : 0)
                .discountAmount(request.getDiscountAmount() != null ? request.getDiscountAmount() : 0L)
                .minOrderPrice(request.getMinOrderPrice() != null ? request.getMinOrderPrice() : 0L)
                .maxUseCount(request.getMaxUseCount() != null ? request.getMaxUseCount() : 1)
                .expiredAt(request.getExpiredAt())
                .build();

        return CouponResponse.from(couponRepository.save(coupon));
    }

    @Transactional
    public UserCouponResponse issue(Long userId, String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

        if (coupon.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CouponException(CouponErrorCode.COUPON_EXPIRED);
        }

        if (coupon.getCurrentUseCount() >= coupon.getMaxUseCount()) {
            throw new CouponException(CouponErrorCode.COUPON_EXHAUSTED);
        }

        if (userCouponRepository.existsByUserIdAndCouponId(userId, coupon.getId())) {
            throw new CouponException(CouponErrorCode.COUPON_ALREADY_ISSUED);
        }

        UserCoupon userCoupon = UserCoupon.builder()
                .userId(userId)
                .coupon(coupon)
                .build();

        coupon.incrementUseCount();
        return UserCouponResponse.from(userCouponRepository.save(userCoupon));
    }

    public List<UserCouponResponse> listMyCoupons(Long userId) {
        return userCouponRepository.findAllByUserId(userId).stream()
                .map(UserCouponResponse::from)
                .toList();
    }

    @Transactional
    public UserCouponResponse use(Long userId, Long userCouponId) {
        UserCoupon userCoupon = userCouponRepository.findById(userCouponId)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

        if (!userCoupon.getUserId().equals(userId)) {
            throw new CouponException(CouponErrorCode.COUPON_NOT_FOUND);
        }

        if (userCoupon.getUsed()) {
            throw new CouponException(CouponErrorCode.COUPON_ALREADY_USED);
        }

        userCoupon.use();
        return UserCouponResponse.from(userCoupon);
    }
}
