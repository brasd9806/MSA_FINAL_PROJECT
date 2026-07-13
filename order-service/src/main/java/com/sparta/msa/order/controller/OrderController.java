package com.sparta.msa.order.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.order.dto.request.OrderCreateRequest;
import com.sparta.msa.order.dto.response.OrderItemResponse;
import com.sparta.msa.order.dto.response.OrderResponse;
import com.sparta.msa.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> list(@AuthenticationPrincipal Long userId) {
        return ApiResponse.success(orderService.list(userId));
    }

    @GetMapping("/{orderId}/detail")
    public ResponseEntity<ApiResponse<OrderResponse>> findById(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.findById(orderId));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<ApiResponse<List<OrderItemResponse>>> findOrderItems(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.findOrderItems(orderId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(@AuthenticationPrincipal Long userId, @Valid @RequestBody OrderCreateRequest request) {
        return ApiResponse.success(orderService.create(userId, request));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);
        return ApiResponse.success();
    }

    // Internal endpoint for payment-service
    @GetMapping("/internal/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderInternal(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.findById(orderId));
    }
}
