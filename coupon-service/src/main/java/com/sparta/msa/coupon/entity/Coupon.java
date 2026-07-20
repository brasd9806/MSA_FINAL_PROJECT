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
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = 50)
    String code;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false)
    @Builder.Default
    Integer discountRate = 0;

    @Column(nullable = false)
    @Builder.Default
    Long discountAmount = 0L;

    @Column(nullable = false)
    @Builder.Default
    Long minOrderPrice = 0L;

    @Column(nullable = false)
    @Builder.Default
    Integer maxUseCount = 1;

    @Column(nullable = false)
    @Builder.Default
    Integer currentUseCount = 0;

    @Column(nullable = false)
    LocalDateTime expiredAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    public void incrementUseCount() {
        this.currentUseCount++;
    }
}
