package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adminapp.Model.Cart;
import com.example.adminapp.Model.Orders;
import com.example.adminapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
    private TextView order_id, Date,total_amount,Additional_text,item_count,orderState;
    private DatabaseReference cartListRef,orderDetailsRef,allProductsRef;
    private RecyclerView ordersList;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth mAuth;
//    private String ID;
//    private String date;
//    private int amount;
//    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        //mAuth = FirebaseAuth.getInstance();
        //   FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference orderRef= FirebaseDatabase.getInstance().getReference().child("AdminOrders");
        String orderId = getIntent().getStringExtra("orderID");

        Toolbar toolbar = (Toolbar) findViewById(R.id.topAppBar);
        toolbar.setTitle("Orders");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        order_id =  findViewById(R.id.Order_id);
        Date = findViewById(R.id.Date);
        total_amount = findViewById(R.id.amount);
        Additional_text = findViewById(R.id.note_text);
        item_count= findViewById(R.id.number_of_items);
        orderState = findViewById(R.id.status);

        ordersList = findViewById(R.id.products_lists);
        ordersList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ordersList.setLayoutManager(layoutManager);

        viewOrderdetails(orderId);
        viewCartNumber(orderId);

        orderDetailsRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders");

        cartListRef  = FirebaseDatabase.getInstance().getReference().child("AdminOrders")
                .child("products");

//        allProductsRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders").child("products").child(orderId);

        fetchDetails(orderId);

    }

    private void viewOrderdetails(String orderId) {

      //  FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference orderDetailsRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders");

        orderDetailsRef.child(orderId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Orders orders = dataSnapshot.getValue(Orders.class);
                    // getOrderInfo();
//                        order_id.setText(ID);
//                        Date.setText("Placed on : "+date);
//                        total_amount.setText(("UGX " + (new DecimalFormat("#,###")).format(Integer.valueOf(amount))));
//                        Additional_text.setText("Special Text : "+text);

                    HashMap<String, Object> products = new HashMap<>();
                    products.putAll((HashMap) dataSnapshot.getValue());
                    int cartCount = products.size();

                    order_id.setText("Order ID:#"+orders.getOrderid());
                    Date.setText("Placed on : "+orders.getDate());
                    total_amount.setText(("UGX " + (new DecimalFormat("#,###")).format(Integer.valueOf(orders.getTotalAmount()))));
                    Additional_text.setText(orders.getSpecialText());
                    item_count.setText(String.valueOf(cartCount));
                    orderState.setText(orders.getState());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    //
    private void viewCartNumber(String orderId) {

        // FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference orderDetailsRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders");

        orderDetailsRef.child(orderId).child("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //     Orders orders = dataSnapshot.getValue(Orders.class);
                    // getOrderInfo();
//                        order_id.setText(ID);
//                        Date.setText("Placed on : "+date);
//                        total_amount.setText(("UGX " + (new DecimalFormat("#,###")).format(Integer.valueOf(amount))));
//                        Additional_text.setText("Special Text : "+text);

                    HashMap<String, Object> products = new HashMap<>();
                    products.putAll((HashMap) dataSnapshot.getValue());
                    int cartCount = products.size();

//                order_id.setText("Order ID:#"+orders.getOrderid());
//                Date.setText("Placed on : "+orders.getDate());
//                total_amount.setText(("UGX " + (new DecimalFormat("#,###")).format(Integer.valueOf(orders.getTotalAmount()))));
//                Additional_text.setText("Special Text : "+orders.getSpecialText());
                    item_count.setText("Items : " +(String.valueOf(cartCount)));

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

 public void  fetchDetails(String orderId){

     allProductsRef = FirebaseDatabase.getInstance().getReference().child("AdminOrders")
             .child(orderId).child("products");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(allProductsRef, Cart.class)
                        .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int i, @NonNull @NotNull Cart model) {

                holder.txtProductQuantity.setText("Qty : " + model.getQuantity());
                holder.txtProductPrice.setText("Price: UGX " + (new DecimalFormat("#,###")).format(Integer.valueOf(model.getPrice())));
                holder.txtProductName.setText(model.getPname());

                Picasso.get().load(model.getImage()).into(holder.imageView);

            }

            @NonNull
            @NotNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_layout3,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        ordersList.setAdapter(adapter);
        adapter.startListening();
    }


}
