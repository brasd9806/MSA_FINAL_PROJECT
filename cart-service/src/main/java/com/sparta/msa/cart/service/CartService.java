package com.sparta.msa.cart.service;

import com.sparta.msa.cart.client.ProductClient;
import com.sparta.msa.cart.client.dto.ProductResponse;
import com.sparta.msa.cart.dto.request.CartItemRequest;
import com.sparta.msa.cart.dto.response.CartItemResponse;
import com.sparta.msa.cart.entity.CartItem;
import com.sparta.msa.cart.exception.CartErrorCode;
import com.sparta.msa.cart.exception.CartException;
import com.sparta.msa.cart.repository.CartItemRepository;
import com.sparta.msa.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductClient productClient;

    public List<CartItemResponse> list(Long userId) {
        return cartItemRepository.findAllByUserId(userId).stream()
                .map(CartItemResponse::from)
                .toList();
    }

    @Transactional
    public void addItem(Long userId, CartItemRequest request) {
        ApiResponse<ProductResponse> response = productClient.getProduct(request.getProductId());
        ProductResponse product = response.getData();

        Optional<CartItem> existing = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());

        if (existing.isPresent()) {
            existing.get().updateQuantity(existing.get().getQuantity() + request.getQuantity());
        } else {
            cartItemRepository.save(CartItem.builder()
                    .userId(userId)
                    .productId(request.getProductId())
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .quantity(request.getQuantity())
                    .build());
        }
    }

    @Transactional
    public void updateItem(Long userId, Long cartItemId, CartItemRequest request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartException(CartErrorCode.CART_ITEM_NOT_FOUND));
        cartItem.updateQuantity(request.getQuantity());
    }

    @Transactional
    public void deleteItem(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartException(CartErrorCode.CART_ITEM_NOT_FOUND));
        cartItemRepository.delete(cartItem);
    }

    public List<CartItemResponse> getByIds(List<Long> cartItemIds) {
        return cartItemRepository.findAllById(cartItemIds).stream()
                .map(CartItemResponse::from)
                .toList();
    }

    @Transactional
    public void deleteByIds(List<Long> cartItemIds) {
        cartItemRepository.deleteAllById(cartItemIds);
    }
}
