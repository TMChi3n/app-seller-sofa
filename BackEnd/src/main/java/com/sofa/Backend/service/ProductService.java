package com.sofa.Backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sofa.Backend.dto.ProductDto;
import com.sofa.Backend.model.Product;

@Service
public interface ProductService {

    List<ProductDto> getAllProduct();

    ProductDto getProductById(int id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(int id, ProductDto productDto);

    void deleteProductById(int id);

    List<Product> findByQuery(String query);

}
