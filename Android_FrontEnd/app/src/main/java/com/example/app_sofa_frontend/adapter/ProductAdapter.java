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
import com.example.app_sofa_frontend.MainActivity;
import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.activity.DetailActivity;
import com.example.app_sofa_frontend.model.ProductResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductResponse> productList;
    private Context context;

    public ProductAdapter(Context context, List<ProductResponse> productList) {
        this.context = context;
        this.productList = productList;
    }

    // Display an item in the list
    // Create ViewHolder new equal inflate an layout XML file and return ViewHolder this
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductResponse product = productList.get(position);
        holder.bind(product);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).displayProductDetail(product);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder represent for RecyclerView
    // RecyclerView is ListView and GridView
    class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;

        private ImageView imgProduct;

        // init constructor
        // represent ViewHolder and init listener event when user click
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txtProductName);
            productPrice = itemView.findViewById(R.id.txtPriceProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);

            imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ProductResponse product = productList.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("product", product);
                        context.startActivity(intent);
                    }
                }
            });
        }

        // assign data of products to element in presentation used in ViewHolder
        public void bind(ProductResponse product) {
            productName.setText(product.getName_sofa());
            String formattedPrice = String.format("%.2f VND", product.getPrice());
            productPrice.setText(formattedPrice);
            Picasso.get().load(product.getImg_url()).into(imgProduct);
        }
    }

}
