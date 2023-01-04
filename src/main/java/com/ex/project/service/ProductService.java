package com.ex.project.service;

import com.ex.project.dto.ProductDTO;
import com.ex.project.entity.ProductEntity;
import com.ex.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void productSave(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.toChangeEntity(productDTO);
        productRepository.save(productEntity);
    }
}
