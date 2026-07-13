package com.sparta.msa.user.address.controller;

import com.sparta.msa.common.ApiResponse;
import com.sparta.msa.user.address.dto.request.AddressRequest;
import com.sparta.msa.user.address.dto.response.AddressResponse;
import com.sparta.msa.user.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponse>> add(@PathVariable Long userId, @Valid @RequestBody AddressRequest request) {
        return ApiResponse.created(addressService.add(userId, request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressResponse>>> getAll(@PathVariable Long userId) {
        return ApiResponse.success(addressService.getAll(userId));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long userId, @PathVariable Long addressId) {
        addressService.delete(userId, addressId);
        return ApiResponse.success();
    }
}
