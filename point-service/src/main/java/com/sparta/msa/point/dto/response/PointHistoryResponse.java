package com.sparta.msa.point.dto.response;

import com.sparta.msa.point.entity.PointHistory;
import com.sparta.msa.point.enums.PointType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointHistoryResponse {

    Long id;
    Long userId;
    Long amount;
    PointType type;
    String description;
    LocalDateTime createdAt;

    public static PointHistoryResponse from(PointHistory pointHistory) {
        return PointHistoryResponse.builder()
                .id(pointHistory.getId())
                .userId(pointHistory.getUserId())
                .amount(pointHistory.getAmount())
                .type(pointHistory.getType())
                .description(pointHistory.getDescription())
                .createdAt(pointHistory.getCreatedAt())
                .build();
    }
}
