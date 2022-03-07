package com.example.adminapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.Interface.ItemClickListner;
import com.example.adminapp.R;

public class FeedbackViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public TextView txtSubject, txtMessage;
    private ItemClickListner itemClickListner;


    public FeedbackViewHolder(View itemView) {
        super(itemView);

        txtSubject = itemView.findViewById(R.id.subject);
        txtMessage = itemView.findViewById(R.id.message);

    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner){
        this.itemClickListner = itemClickListner;
    }

}
