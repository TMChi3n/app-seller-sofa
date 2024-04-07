package com.example.app_sofa_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sofa_frontend.activity.CartActivity;
import com.example.app_sofa_frontend.activity.DetailActivity;
import com.example.app_sofa_frontend.activity.LoginActivity;
import com.example.app_sofa_frontend.adapter.ProductAdapter;
import com.example.app_sofa_frontend.model.ProductResponse;
import com.example.app_sofa_frontend.api.APIClient;
import com.example.app_sofa_frontend.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<ProductResponse> productList;
    private APIInterface apiInterface;
    private ImageView imgUser;
    private ImageView imgViewCart;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUser = findViewById(R.id.user);
        imgViewCart = findViewById(R.id.cart);
        recyclerView = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        fetchProducts();
    }

    private void fetchProducts() {
        Call<List<ProductResponse>> call = apiInterface.getAllProducts();
        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProductResponse> products = response.body();
                    productList.clear();
                    productList.addAll(products);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("API Call", "Failed", t);
                Toast.makeText(MainActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch(String query) {
        Call<List<ProductResponse>> call = apiInterface.searchProduct(query);
        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProductResponse> products = response.body();
                    if (products != null && !products.isEmpty()) {
                        if (products.size() == 1) {
                            // Only one product found, navigate to its detail activity
                            displayProductDetail(products.get(0));
                        } else {
                            // Show search results in the RecyclerView
                            adapter = new ProductAdapter(MainActivity.this, products);
                            recyclerView.setAdapter(adapter);
                        }
                    } else {
                        // Handle case when no products are found
                        Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(MainActivity.this, "Failed to search products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("API Call", "Failed", t);
                Toast.makeText(MainActivity.this, "Search Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void displayProductDetail(ProductResponse product) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}
