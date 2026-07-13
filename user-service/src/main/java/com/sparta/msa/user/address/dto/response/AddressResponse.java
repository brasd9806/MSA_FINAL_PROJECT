package com.sparta.msa.user.address.dto.response;

import com.sparta.msa.user.address.entity.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {

    Long id;
    String address;
    boolean isDefault;
    LocalDateTime createdAt;

    private AddressResponse(Address address) {
        this.id = address.getId();
        this.address = address.getAddress();
        this.isDefault = address.isDefault();
        this.createdAt = address.getCreatedAt();
    }

    public static AddressResponse from(Address address) {
        return new AddressResponse(address);
    }
}
