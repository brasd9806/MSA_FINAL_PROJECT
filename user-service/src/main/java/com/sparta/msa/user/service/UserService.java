package com.sparta.msa.user.service;

import com.sparta.msa.user.dto.request.UserCreateRequest;
import com.sparta.msa.user.dto.request.UserPwdUpdateRequest;
import com.sparta.msa.user.dto.request.UserUpdateRequest;
import com.sparta.msa.user.dto.response.UserResponse;
import com.sparta.msa.user.entity.User;
import com.sparta.msa.user.exception.UserErrorCode;
import com.sparta.msa.user.exception.UserException;
import com.sparta.msa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new UserException(UserErrorCode.DUPLICATE_USER_ID);
        }

        User user = userRepository.save(User.builder()
                .userId(request.getUserId())
                .userNm(request.getUserNm())
                .phoneNumber(request.getPhoneNumber())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build());
        return UserResponse.from(user);
    }

    public UserResponse update(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (StringUtils.hasText(request.getUserNm())) {
            user.setUserNm(request.getUserNm());
        }
        if (StringUtils.hasText(request.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        userRepository.save(user);
        return UserResponse.from(user);
    }

    public void pwdUpdate(Long id, UserPwdUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        user.delete();
        userRepository.save(user);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }
}
