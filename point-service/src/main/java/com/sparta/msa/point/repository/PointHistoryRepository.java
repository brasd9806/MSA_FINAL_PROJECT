package com.sparta.msa.point.repository;

import com.sparta.msa.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(CASE WHEN h.type = 'EARN' THEN h.amount ELSE -h.amount END), 0) FROM PointHistory h WHERE h.userId = :userId")
    Long findTotalPointsByUserId(@Param("userId") Long userId);
}
