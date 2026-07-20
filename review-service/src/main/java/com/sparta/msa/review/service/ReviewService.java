package com.sparta.msa.review.service;

import com.sparta.msa.review.dto.request.ReviewRequest;
import com.sparta.msa.review.dto.response.ReviewResponse;
import com.sparta.msa.review.entity.Review;
import com.sparta.msa.review.exception.ReviewErrorCode;
import com.sparta.msa.review.exception.ReviewException;
import com.sparta.msa.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse create(Long userId, ReviewRequest request) {
        // Note: orderId and productId need to come from request - but per spec, ReviewRequest only has rating/content.
        // We'll need a separate create request or infer from path. Per the spec, ReviewRequest has rating+content.
        // The controller will need to accept orderId and productId as request params or path variables.
        // For now this will be handled by the controller passing those separately.
        throw new UnsupportedOperationException("Use create(userId, productId, orderId, request) instead");
    }

    @Transactional
    public ReviewResponse create(Long userId, Long productId, Long orderId, ReviewRequest request) {
        if (reviewRepository.existsByUserIdAndOrderIdAndProductId(userId, orderId, productId)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = Review.builder()
                .userId(userId)
                .productId(productId)
                .orderId(orderId)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        return ReviewResponse.from(reviewRepository.save(review));
    }

    public List<ReviewResponse> listByProduct(Long productId) {
        return reviewRepository.findAllByProductId(productId).stream()
                .map(ReviewResponse::from)
                .toList();
    }

    public List<ReviewResponse> listByUser(Long userId) {
        return reviewRepository.findAllByUserId(userId).stream()
                .map(ReviewResponse::from)
                .toList();
    }

    @Transactional
    public ReviewResponse update(Long userId, Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!review.getUserId().equals(userId)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_AUTHORIZED);
        }

        review.update(request.getRating(), request.getContent());
        return ReviewResponse.from(review);
    }

    @Transactional
    public void delete(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!review.getUserId().equals(userId)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_AUTHORIZED);
        }

        reviewRepository.delete(review);
    }
}
