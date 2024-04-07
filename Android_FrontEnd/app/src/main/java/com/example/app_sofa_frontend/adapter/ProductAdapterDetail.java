package com.example.app_sofa_frontend.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.activity.DetailActivity;
import com.example.app_sofa_frontend.model.ProductResponse;

import java.util.List;

public class ProductAdapterDetail extends RecyclerView.Adapter<ProductAdapterDetail.ProductViewHolder> {

    private List<ProductResponse> productList;
    private Context context;

    public ProductAdapterDetail(Context context, List<ProductResponse> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductResponse product = productList.get(position);
        holder.bind(product);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;

        private ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txt_name_product);
            productPrice = itemView.findViewById(R.id.txt_price);
            imgProduct = itemView.findViewById(R.id.img_product);
        }

        public void bind(ProductResponse product) {
            productName.setText(product.getName_sofa());
            String formattedPrice = String.format("%.2f VND", product.getPrice());
            productPrice.setText(formattedPrice);
            Glide.with(itemView).load(product.getImg_url()).into(imgProduct);
        }
    }
}
