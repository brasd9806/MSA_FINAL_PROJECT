package com.sparta.msa.user.wishlist.service;

import com.sparta.msa.user.wishlist.dto.response.WishlistResponse;
import com.sparta.msa.user.wishlist.entity.Wishlist;
import com.sparta.msa.user.wishlist.exception.WishlistErrorCode;
import com.sparta.msa.user.wishlist.exception.WishlistException;
import com.sparta.msa.user.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<WishlistResponse> list(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream()
                .map(WishlistResponse::from)
                .toList();
    }

    @Transactional
    public void add(Long userId, Long productId) {
        if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new WishlistException(WishlistErrorCode.ALREADY_IN_WISHLIST);
        }
        wishlistRepository.save(Wishlist.builder()
                .userId(userId)
                .productId(productId)
                .build());
    }

    @Transactional
    public void remove(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new WishlistException(WishlistErrorCode.WISHLIST_NOT_FOUND));
        wishlistRepository.delete(wishlist);
    }
}
