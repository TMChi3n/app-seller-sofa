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
    private ImageView cartView;
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
        cartView = findViewById(R.id.cart);

        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        // setOnEditorActionListener use action from to console
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (event == null || !event.isShiftPressed()) {
                        // Trigger search when Enter is pressed without Shift key
                        performSearch(v.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });

        fetchProducts();
    }

    private void fetchProducts() {
        Call<List<ProductResponse>> call = apiInterface.getAllProducts();
        // enqueue use send the request and notify callback of its response
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
                if (response.isSuccessful()) {  // if success, get data product from to update and response RecyclerView equal Adapter
                    List<ProductResponse> products = response.body();
                    if (products != null && !products.isEmpty()) {
                        productList.clear();
                        productList.addAll(products);
                        adapter.notifyDataSetChanged(); // Inform the adapter of the updated data

                        if (products.size() == 1) {
                            // Only one product found, navigate to its detail activity
                            displayProductDetail(products.get(0));
                        }
                    } else {
                        // Handle case when no products are found
                        Toast.makeText(MainActivity.this, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle an unsuccessful response
                    Toast.makeText(MainActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                // Handle a network or other API call failure
                Log.e("API Call", "Error during search", t);
                Toast.makeText(MainActivity.this, "Search error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void displayProductDetail(ProductResponse product) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}
