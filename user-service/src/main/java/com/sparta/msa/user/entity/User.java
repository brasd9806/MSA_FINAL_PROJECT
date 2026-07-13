package com.sparta.msa.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String userId;

    @Column(nullable = false)
    String userNm;

    @Column(nullable = false)
    String phoneNumber;

    @Column(nullable = false)
    String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRole role;

    @Column(nullable = false)
    String useYn;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public User(String userId, String userNm, String phoneNumber, String passwordHash) {
        this.userId = userId;
        this.userNm = userNm;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.role = UserRole.USER;
        this.useYn = "Y";
    }

    public void setUserNm(String userNm) {
        if (StringUtils.hasText(userNm)) this.userNm = userNm;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) this.phoneNumber = phoneNumber;
    }

    public void setPasswordHash(String passwordHash) {
        if (StringUtils.hasText(passwordHash)) this.passwordHash = passwordHash;
    }

    public void delete() {
        this.useYn = "N";
    }
}
