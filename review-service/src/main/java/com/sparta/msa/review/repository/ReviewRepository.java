package com.sparta.msa.review.repository;

import com.sparta.msa.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProductId(Long productId);

    List<Review> findAllByUserId(Long userId);

    boolean existsByUserIdAndOrderIdAndProductId(Long userId, Long orderId, Long productId);
}
