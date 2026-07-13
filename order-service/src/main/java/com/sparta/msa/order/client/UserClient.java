package com.sparta.msa.order.client;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.order.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/api/users/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable Long userId);
}
