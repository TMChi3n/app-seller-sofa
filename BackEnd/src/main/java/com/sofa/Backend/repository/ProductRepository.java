package com.sofa.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofa.Backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getById(int id);

}
