package com.sofa.Backend.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofa.Backend.dto.ProductDto;
import com.sofa.Backend.model.Product;
import com.sofa.Backend.repository.ProductRepository;
import com.sofa.Backend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) {
        Optional<Product> productOption = productRepository.findById(id);
        return productOption.map(this::convertToDto).orElse(null);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        Product saveProduct = productRepository.save(product);
        return convertToDto(saveProduct);
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto productDto) {
        Product existingProduct = productRepository.getById(id);
        existingProduct.setName_sofa(productDto.getName_sofa());
        existingProduct.setDescriptions(productDto.getDescriptions());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setImg_url(productDto.getImg_url());

        return convertToDto(existingProduct);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByQuery(String query) {
        return productRepository.findByQuery(query);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId_sofa(product.getId_sofa());
        productDto.setName_sofa(product.getName_sofa());
        productDto.setDescriptions(product.getDescriptions());
        productDto.setPrice(product.getPrice());
        productDto.setImg_url(product.getImg_url());

        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName_sofa(productDto.getName_sofa());
        product.setPrice(productDto.getPrice());
        product.setDescriptions(productDto.getDescriptions());
        product.setImg_url(productDto.getImg_url());

        return product;
    }

}
