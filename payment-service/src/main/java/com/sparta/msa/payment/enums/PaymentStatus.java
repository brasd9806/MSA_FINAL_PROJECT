package com.sparta.msa.payment.enums;

public enum PaymentStatus {
    APPROVED,       // 결제 완료
    CANCELLED,      // 전액 취소
    PARTIAL_CANCEL  // 부분 취소
}
