package com.sparta.msa.coupon.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_coupons")
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    Coupon coupon;

    @Column(nullable = false)
    @Builder.Default
    Boolean used = false;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime issuedAt;

    LocalDateTime usedAt;

    public void use() {
        this.used = true;
        this.usedAt = LocalDateTime.now();
    }
}
