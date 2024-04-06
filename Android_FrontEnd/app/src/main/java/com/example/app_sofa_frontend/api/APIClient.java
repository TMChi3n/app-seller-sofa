package com.example.app_sofa_frontend.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static APIClient instance;
    private Retrofit retrofit;
    private static final String BaseUrl = "http://localhost:8080";

    private APIClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return  instance;
    }

    public APIInterface getApi() {
        return retrofit.create(APIInterface.class);
    }

}
