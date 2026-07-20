package com.sparta.msa.payment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelRequest {

    @NotNull
    @Min(1)
    Long amount; // 취소할 금액 (전액이면 remainingAmount와 동일)

    String description;
}
