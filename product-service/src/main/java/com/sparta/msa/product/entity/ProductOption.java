package com.sparta.msa.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_options")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Setter
    @Column(nullable = false, length = 100)
    String name;

    @Setter
    @Column(nullable = false)
    BigDecimal additionalPrice;

    @Setter
    @Column(nullable = false)
    Integer stock;

    @Builder
    public ProductOption(Product product, String name, BigDecimal additionalPrice, Integer stock) {
        this.product = product;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.stock = stock;
    }
}
