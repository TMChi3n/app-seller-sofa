package com.sofa.Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int id_sofa;
    private String name_sofa;
    private double price;
    private String descriptions;
    private String img_url;
}
