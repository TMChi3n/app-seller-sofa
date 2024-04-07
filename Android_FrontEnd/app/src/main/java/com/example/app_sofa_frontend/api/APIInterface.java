package com.example.app_sofa_frontend.api;

import com.example.app_sofa_frontend.model.ProductResponse;
import com.example.app_sofa_frontend.model.UserAccount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/product")
    Call<List<ProductResponse>> getAllProducts();

    @GET("/api/product/{id}")
    Call<ProductResponse> getProductById(@Path("id") int id);

    @GET("/api/product/search")
    Call<List<ProductResponse>> searchProduct(@Query("query") String nameProduct);


    @POST("/api/login")
    Call<UserAccount> loginAccount(@Body UserAccount userAccount);

    @POST("/api/register")
    Call<UserAccount> registerAccount(@Body UserAccount userAccount);

}

