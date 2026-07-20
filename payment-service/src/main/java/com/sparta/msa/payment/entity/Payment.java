package com.sparta.msa.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long orderId;

    @Column(nullable = false)
    Long totalAmount;

    // 누적 취소 금액 (부분취소 추적용)
    @Column(nullable = false)
    Long cancelledAmount;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Builder
    public Payment(Long orderId, Long totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.cancelledAmount = 0L;
    }

    public void addCancelledAmount(Long amount) {
        this.cancelledAmount += amount;
    }

    public Long remainingAmount() {
        return this.totalAmount - this.cancelledAmount;
    }

    public PaymentStatus currentStatus() {
        if (cancelledAmount == 0) return com.sparta.msa.payment.enums.PaymentStatus.APPROVED;
        if (cancelledAmount.equals(totalAmount)) return com.sparta.msa.payment.enums.PaymentStatus.CANCELLED;
        return com.sparta.msa.payment.enums.PaymentStatus.PARTIAL_CANCEL;
    }
}
