package com.novo.personalproject.service;

import com.novo.personalproject.dao.ProductRepository;
import com.novo.personalproject.dto.ProductCreateDto;
import com.novo.personalproject.dto.ProductReadDto;
import com.novo.personalproject.mapper.ProductCreateMapper;
import com.novo.personalproject.mapper.ProductReadMapper;
import com.novo.personalproject.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductReadMapper productReadMapper;
    @Autowired
    private final ProductCreateMapper productCreateMapper;

    public Page<ProductReadDto> findAllProducts(Pageable pageable) {
        Page<Product> allProducts = productRepository.findAll(pageable);
        return allProducts.map(productReadMapper::map);
    }

    public Optional <ProductReadDto> findProductById(Integer id) {
        return productRepository.findById(id)
                .map(productReadMapper::map);
    }

    public Optional<ProductReadDto> createProduct(ProductCreateDto dto) {

        return Optional.of(productRepository.save(productCreateMapper.map(dto)))
                .map(productReadMapper::map);

    }
}
