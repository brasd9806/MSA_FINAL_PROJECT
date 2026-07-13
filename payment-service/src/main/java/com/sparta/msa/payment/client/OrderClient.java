package com.sparta.msa.payment.client;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.payment.client.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {

    @GetMapping("/api/orders/internal/{orderId}")
    ApiResponse<OrderResponse> getOrder(@PathVariable Long orderId);

    @PatchMapping("/api/orders/{orderId}/cancel")
    ApiResponse<Void> cancelOrder(@PathVariable Long orderId);
}
