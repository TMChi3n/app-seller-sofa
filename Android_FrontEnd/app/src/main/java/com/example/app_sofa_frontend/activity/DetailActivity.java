package com.example.app_sofa_frontend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_sofa_frontend.MainActivity;
import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.model.ProductResponse;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtProductDescription;

    private ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_sofa);

        backHome = findViewById(R.id.back);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imgProduct = findViewById(R.id.imgProductDetail);
        txtProductName = findViewById(R.id.txtProductNameDetail);
        txtProductPrice = findViewById(R.id.txtPriceProductDetail);
        txtProductDescription = findViewById(R.id.txtDescriptionProductDetail);

        // Nhận dữ liệu từ Intent
        ProductResponse product = (ProductResponse) getIntent().getSerializableExtra("product");

        if (product != null) {
            // Hiển thị thông tin sản phẩm
            txtProductName.setText(product.getName_sofa());
            String formattedPrice = String.format("%.2f", product.getPrice());
            txtProductPrice.setText(formattedPrice);
            txtProductDescription.setText(product.getDescriptions());
            Picasso.get().load(product.getImg_url()).into(imgProduct);
        } else {
            Toast.makeText(this, "Product details not available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
