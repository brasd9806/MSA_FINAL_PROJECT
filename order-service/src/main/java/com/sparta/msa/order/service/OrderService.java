package com.sparta.msa.order.service;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.order.client.CartClient;
import com.sparta.msa.order.client.UserClient;
import com.sparta.msa.order.client.dto.CartItemResponse;
import com.sparta.msa.order.dto.request.OrderCreateRequest;
import com.sparta.msa.order.dto.response.OrderItemResponse;
import com.sparta.msa.order.dto.response.OrderResponse;
import com.sparta.msa.order.entity.Order;
import com.sparta.msa.order.entity.OrderItem;
import com.sparta.msa.order.enums.OrderStatus;
import com.sparta.msa.order.exception.OrderErrorCode;
import com.sparta.msa.order.exception.OrderException;
import com.sparta.msa.order.repository.OrderItemRepository;
import com.sparta.msa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserClient userClient;
    private final CartClient cartClient;

    public List<OrderResponse> list(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(OrderResponse::from)
                .toList();
    }

    public OrderResponse findById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderErrorCode.ORDER_NOT_FOUND));
        return OrderResponse.from(order);
    }

    public List<OrderItemResponse> findOrderItems(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId).stream()
                .map(OrderItemResponse::from)
                .toList();
    }

    @Transactional
    public OrderResponse create(Long userId, OrderCreateRequest request) {
        // Validate user exists
        userClient.getUser(userId);

        // Get cart items via Feign
        ApiResponse<List<CartItemResponse>> cartResponse = cartClient.getCartItems(request.getCartItemIds());
        List<CartItemResponse> cartItems = cartResponse.getData();

        Long totalPrice = cartItems.stream()
                .mapToLong(CartItemResponse::getTotalPrice)
                .sum();

        Order order = Order.builder()
                .userId(userId)
                .addressId(request.getAddressId())
                .totalPrice(totalPrice)
                .build();
        orderRepository.save(order);

        cartItems.forEach(cartItem -> {
            orderItemRepository.save(OrderItem.builder()
                    .order(order)
                    .productId(cartItem.getProductId())
                    .productName(cartItem.getProductName())
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .build());
        });

        // Delete cart items after order creation
        cartClient.deleteCartItems(request.getCartItemIds());

        return OrderResponse.from(order);
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderErrorCode.ORDER_NOT_FOUND));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderException(OrderErrorCode.ORDER_CANNOT_CANCEL);
        }

        order.updateStatus(OrderStatus.CANCELLED);
    }
}
