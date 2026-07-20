package com.sparta.msa.payment.entity;

import com.sparta.msa.payment.enums.PaymentAction;
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
@Table(name = "payment_logs")
public class PaymentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PaymentAction action;

    @Column(nullable = false)
    Long amount;

    @Column
    String description;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Builder
    public PaymentLog(Payment payment, PaymentAction action, Long amount, String description) {
        this.payment = payment;
        this.action = action;
        this.amount = amount;
        this.description = description;
    }
}
