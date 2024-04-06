package com.sofa.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sofas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sofa;
    private String name_sofa;
    private double price;
    private String descriptions;
    private String img_url;

    public Product(int id_sofa, String name_sofa, double price) {
        this.id_sofa = id_sofa;
        this.name_sofa = name_sofa;
        this.price = price;
    }

}
