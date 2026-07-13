package com.sparta.msa.user.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.user.dto.request.UserCreateRequest;
import com.sparta.msa.user.dto.request.UserPwdUpdateRequest;
import com.sparta.msa.user.dto.request.UserUpdateRequest;
import com.sparta.msa.user.dto.response.UserResponse;
import com.sparta.msa.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.created(userService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> findById(@PathVariable Long id) {
        return ApiResponse.success(userService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return ApiResponse.success(userService.update(id, request));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponse<Void>> pwdUpdate(@PathVariable Long id, @Valid @RequestBody UserPwdUpdateRequest request) {
        userService.pwdUpdate(id, request);
        return ApiResponse.success();
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success();
    }
}
