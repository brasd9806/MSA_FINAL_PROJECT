package com.sparta.msa.user.address.service;

import com.sparta.msa.user.address.dto.request.AddressRequest;
import com.sparta.msa.user.address.dto.response.AddressResponse;
import com.sparta.msa.user.address.entity.Address;
import com.sparta.msa.user.address.exception.AddressErrorCode;
import com.sparta.msa.user.address.exception.AddressException;
import com.sparta.msa.user.address.repository.AddressRepository;
import com.sparta.msa.user.entity.User;
import com.sparta.msa.user.exception.UserErrorCode;
import com.sparta.msa.user.exception.UserException;
import com.sparta.msa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressResponse add(Long userId, AddressRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Address address = addressRepository.save(Address.builder()
                .user(user)
                .address(request.getAddress())
                .isDefault(request.isDefault())
                .build());
        return AddressResponse.from(address);
    }

    public List<AddressResponse> getAll(Long userId) {
        return addressRepository.findAllByUserId(userId).stream()
                .map(AddressResponse::from)
                .toList();
    }

    public void delete(Long userId, Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new AddressException(AddressErrorCode.ADDRESS_NOT_FOUND);
        }
        addressRepository.deleteByIdAndUserId(addressId, userId);
    }
}
