package com.sparta.msa.point.service;

import com.sparta.msa.point.dto.request.PointEarnRequest;
import com.sparta.msa.point.dto.request.PointUseRequest;
import com.sparta.msa.point.dto.response.PointBalanceResponse;
import com.sparta.msa.point.dto.response.PointHistoryResponse;
import com.sparta.msa.point.entity.PointHistory;
import com.sparta.msa.point.enums.PointType;
import com.sparta.msa.point.exception.PointErrorCode;
import com.sparta.msa.point.exception.PointException;
import com.sparta.msa.point.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public PointHistoryResponse earn(Long userId, PointEarnRequest request) {
        PointHistory history = PointHistory.builder()
                .userId(userId)
                .amount(request.getAmount())
                .type(PointType.EARN)
                .description(request.getDescription())
                .build();

        return PointHistoryResponse.from(pointHistoryRepository.save(history));
    }

    @Transactional
    public PointHistoryResponse use(Long userId, PointUseRequest request) {
        Long balance = pointHistoryRepository.findTotalPointsByUserId(userId);

        if (balance < request.getAmount()) {
            throw new PointException(PointErrorCode.INSUFFICIENT_POINT);
        }

        PointHistory history = PointHistory.builder()
                .userId(userId)
                .amount(request.getAmount())
                .type(PointType.USE)
                .description(request.getDescription())
                .build();

        return PointHistoryResponse.from(pointHistoryRepository.save(history));
    }

    public PointBalanceResponse getBalance(Long userId) {
        Long balance = pointHistoryRepository.findTotalPointsByUserId(userId);
        return PointBalanceResponse.builder()
                .userId(userId)
                .balance(balance)
                .build();
    }

    public List<PointHistoryResponse> getHistory(Long userId) {
        return pointHistoryRepository.findAllByUserId(userId).stream()
                .map(PointHistoryResponse::from)
                .toList();
    }
}
