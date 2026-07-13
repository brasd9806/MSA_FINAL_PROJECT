package com.sparta.msa.cart.client;

import com.sparta.msa.cart.client.dto.ProductResponse;
import com.sparta.msa.common.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {

    @GetMapping("/api/products/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable Long productId);
}
