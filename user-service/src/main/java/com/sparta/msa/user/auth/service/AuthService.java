package com.sparta.msa.user.auth.service;

import com.sparta.msa.common.JwtUtil;
import com.sparta.msa.user.auth.dto.response.LoginResponse;
import com.sparta.msa.user.dto.request.LoginRequest;
import com.sparta.msa.user.entity.User;
import com.sparta.msa.user.exception.UserErrorCode;
import com.sparta.msa.user.exception.UserException;
import com.sparta.msa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(user.getId(), user.getRole().name());
        return LoginResponse.of(token);
    }
}
