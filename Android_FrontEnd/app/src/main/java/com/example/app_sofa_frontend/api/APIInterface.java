package com.example.app_sofa_frontend.api;

import com.example.app_sofa_frontend.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("products")
    Call<ProductResponse> getProduct();

}