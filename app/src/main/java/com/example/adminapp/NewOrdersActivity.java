package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adminapp.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class NewOrdersActivity extends AppCompatActivity {


    private RecyclerView ordersList;
    private DatabaseReference orderRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

        orderRef= FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList = findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options=
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                        .setQuery(orderRef, AdminOrders.class)
                        .build();
        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull AdminOrdersViewHolder holder, int position, @NonNull @NotNull AdminOrders model) {


                        holder.userFirstName.setText("Name : "+model.getFirstname()+ " "+ model.getLastname());
                        holder.userPhoneNumber.setText("Phone : "+model.getPhone());
                        holder.userTotalPrice.setText("Total Amount : "+ (new DecimalFormat("#,###")).format(Integer.valueOf(model.getTotalAmount())));
                        holder.userDateTime.setText("Orders at : "+model.getDate() + ""+ model.getTime());
                        holder.userShippingAddress.setText("Address : "+model.getAddress());


                        holder.ShowOrdersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String uID = getRef(position).getKey();

                                Intent intent = new Intent(NewOrdersActivity.this, UserProductsActivity.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence options[] = new CharSequence[]{
                                        "Yes",
                                        "No"
                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(NewOrdersActivity.this);
                                builder.setTitle("Have You Shipped these ordered products?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(i==0){

                                            String uID = getRef(position).getKey();
                                            RemoveOrder(uID);
                                        }else {
                                            finish();
                                        }
                                    }
                                });

                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @NotNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout,parent,false);
                        return  new  AdminOrdersViewHolder(view);
                    }
                };
        ordersList.setAdapter(adapter);
        adapter.startListening();
    }



    public  static  class AdminOrdersViewHolder extends  RecyclerView.ViewHolder{

        public TextView userFirstName, userPhoneNumber, userTotalPrice, userDateTime,userShippingAddress;
        public Button ShowOrdersBtn;



        public  AdminOrdersViewHolder(View itemView){
            super((itemView));

            userFirstName = itemView.findViewById(R.id.order_user_name);
            userPhoneNumber = itemView.findViewById(R.id.order_phone_number);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);
            userDateTime = itemView.findViewById(R.id.order_date_time);
            userShippingAddress= itemView.findViewById(R.id.order_address_city);
            ShowOrdersBtn = itemView.findViewById(R.id.show_all_products_btn);


        }

    }

    private void RemoveOrder(String uID) {

        orderRef.child(uID).removeValue();
    }

}