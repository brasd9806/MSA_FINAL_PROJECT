package com.sparta.msa.product.service;

import com.sparta.msa.product.dto.request.ProductOptionRequest;
import com.sparta.msa.product.dto.response.ProductOptionResponse;
import com.sparta.msa.product.entity.Product;
import com.sparta.msa.product.entity.ProductOption;
import com.sparta.msa.product.exception.ProductErrorCode;
import com.sparta.msa.product.exception.ProductException;
import com.sparta.msa.product.repository.ProductOptionRepository;
import com.sparta.msa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;

    public List<ProductOptionResponse> list(Long productId) {
        return productOptionRepository.findAllByProductId(productId).stream()
                .map(ProductOptionResponse::from)
                .toList();
    }

    @Transactional
    public void save(Long productId, ProductOptionRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        productOptionRepository.save(ProductOption.builder()
                .product(product)
                .name(request.getName())
                .additionalPrice(request.getAdditionalPrice())
                .stock(request.getStock())
                .build());
    }

    @Transactional
    public void update(Long optionId, ProductOptionRequest request) {
        ProductOption option = productOptionRepository.findById(optionId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        option.setName(request.getName());
        option.setAdditionalPrice(request.getAdditionalPrice());
        option.setStock(request.getStock());
    }

    @Transactional
    public void delete(Long optionId) {
        ProductOption option = productOptionRepository.findById(optionId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));
        productOptionRepository.delete(option);
    }
}
