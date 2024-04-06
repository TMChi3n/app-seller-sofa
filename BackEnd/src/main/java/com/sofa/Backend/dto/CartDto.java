package com.sofa.Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private int quantity;
    private String product_name;
    private double amount;
}
