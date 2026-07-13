package com.sparta.msa.user.address.repository;

import com.sparta.msa.user.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUserId(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
