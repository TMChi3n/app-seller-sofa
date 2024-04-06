package com.example.app_sofa_frontend.model;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("name_sofa")
    private String name_sofa;
    @SerializedName("price")
    private double price;
    @SerializedName("descriptions")
    private String descriptions;
    @SerializedName("img_url")
    private String img_url;

    public String getName_sofa() {
        return name_sofa;
    }

    public void setName_sofa(String name_sofa) {
        this.name_sofa = name_sofa;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
