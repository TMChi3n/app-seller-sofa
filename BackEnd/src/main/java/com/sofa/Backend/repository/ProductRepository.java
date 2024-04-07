package com.sofa.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sofa.Backend.model.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getById(int id);

    @Transactional
    @Modifying
    @Query("SELECT s FROM Product s WHERE s.name_sofa LIKE CONCAT('%', :query, '%')")
    List<Product> findByQuery(@Param("query") String query);

}
