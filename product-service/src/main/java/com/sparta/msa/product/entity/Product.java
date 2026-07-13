package com.sparta.msa.product.entity;

import com.sparta.msa.product.category.entity.Category;
import com.sparta.msa.product.enums.ProductStatus;
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

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Long price;

    @Column(nullable = false)
    Integer stock;

    @Column
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProductStatus status;

    @Column
    Integer discountRate;

    @Column
    Long discountPrice;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Product(String name, Long price, Integer stock, String description, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.status = ProductStatus.ON_SALE;
    }

    public void update(String name, Long price, String description, Category category) {
        this.name = name;
        this.price = price;
        if (StringUtils.hasText(description)) this.description = description;
        this.category = category;
    }

    public void decreaseStock(int quantity) {
        this.stock -= quantity;
        if (this.stock == 0) this.status = ProductStatus.SOLD_OUT;
    }

    public void applyDiscount(int discountRate) {
        this.discountRate = discountRate;
        this.discountPrice = this.price * (100 - discountRate) / 100;
        this.status = ProductStatus.ON_DISCOUNT;
    }

    public void removeDiscount() {
        this.discountRate = null;
        this.discountPrice = null;
        this.status = ProductStatus.ON_SALE;
    }
}
