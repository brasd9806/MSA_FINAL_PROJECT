package com.sparta.msa.order.client;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.order.client.dto.CartItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cart-service", url = "${cart.service.url}")
public interface CartClient {

    @GetMapping("/api/carts/internal")
    ApiResponse<List<CartItemResponse>> getCartItems(@RequestParam("ids") List<Long> ids);

    @DeleteMapping("/api/carts/internal")
    ApiResponse<Void> deleteCartItems(@RequestParam("ids") List<Long> ids);
}
