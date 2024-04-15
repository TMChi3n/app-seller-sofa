package com.example.app_sofa_frontend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.model.ProductResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    // Context is component provide access allow information about status of app.
    // Provide Activities, Fragments, Services, access to source, image, theme, style...
    private Context context;
    private List<ProductResponse> listCart;

    public CartAdapter(Context context, List<ProductResponse> cartItems) {
        context = context;
        listCart = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);    // parent is ViewGroup
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ProductResponse item = listCart.get(position);

        holder.cartItemNameTextView.setText(item.getName_sofa());

        String formattedPrice = String.format("%,.2f", item.getPrice());
        holder.cartItemPriceTextView.setText("Giá tiền: " + formattedPrice + "VND");

        // Load image using Picasso or Glide into cartItemImageView
        Picasso.get().load(item.getImg_url()).into(holder.cartItemImageView);
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private ProductResponse product;
        ImageView cartItemImageView;
        TextView cartItemNameTextView;
        TextView cartItemPriceTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemImageView = itemView.findViewById(R.id.cartItemImageView);
            cartItemNameTextView = itemView.findViewById(R.id.cartItemNameTextView);
            cartItemPriceTextView = itemView.findViewById(R.id.cartItemPriceTextView);
        }
    }
}

