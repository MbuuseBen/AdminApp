package com.example.adminapp.ViewHolder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adminapp.Interface.ItemClickListner;
import com.example.adminapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice,txtProductQuantity, txtTotal,txtSubTotal;
    private ItemClickListner itemClickListner;
    public ImageView imageView;

    public CartViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.cart_product_image);
        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
//        txtTotal=itemView.findViewById(R.id.total);
//        txtSubTotal=itemView.findViewById(R.id.subtotal);

    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner){
        this.itemClickListner = itemClickListner;
    }

}
