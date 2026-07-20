package com.sparta.msa.product.repository;

import com.sparta.msa.product.category.entity.Category;
import com.sparta.msa.product.dto.request.ProductSearchRequest;
import com.sparta.msa.product.entity.Product;
import com.sparta.msa.product.enums.ProductStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> from(ProductSearchRequest req) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(req.getKeyword())) {
                String pattern = "%" + req.getKeyword().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("name")), pattern),
                        cb.like(cb.lower(root.get("description")), pattern)
                ));
            }

            if (req.getCategoryId() != null) {
                Join<Product, Category> category = root.join("category");
                predicates.add(cb.equal(category.get("id"), req.getCategoryId()));
            }

            if (req.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), req.getMinPrice()));
            }

            if (req.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), req.getMaxPrice()));
            }

            if (req.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), req.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
