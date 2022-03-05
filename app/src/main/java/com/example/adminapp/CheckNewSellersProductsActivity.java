package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adminapp.Model.Products;
import com.example.adminapp.R;
import com.example.adminapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import io.paperdb.Paper;

public class CheckNewSellersProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_new_sellers_products);


        unverifiedProductsRef = FirebaseDatabase.getInstance().getReference().child("products");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        recyclerView = findViewById(R.id.admin_orders_checklist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.topAppBar);
        mToolbar.setTitle("Approve New Seller Products");
        setSupportActionBar(mToolbar);


        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new  FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(unverifiedProductsRef.orderByChild("productState").equalTo("Not Approved"),Products.class)
                        .build();


        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Products model) {

                        holder.txtProductName.setText(model.getPname());
//                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price : UGX " + model.getPrice() );
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final  String productID = model.getPid();
                                CharSequence options [] = new CharSequence[] {
                                        "Yes",
                                        "No"

                                };

                                AlertDialog.Builder builder = new AlertDialog.Builder(CheckNewSellersProductsActivity.this);
                                builder.setTitle("DO you want to approve this Product. Are You Sure?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {

                                        if (position == 0){

                                            changeProductState(productID);

                                        }if (position == 0 ){

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
                    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.seller_home:



                    Intent home = new Intent(CheckNewSellersProductsActivity.this, MainActivity.class);
                    startActivity(home);



                    break;


                case R.id.seller_logout:


                    Paper.book().destroy();
                    FirebaseAuth.getInstance().signOut();

                    Intent intent = new Intent(CheckNewSellersProductsActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, selectedFragment)
//                    .commit();
            return true;
        }
    };
    private void changeProductState(String productID) {
        unverifiedProductsRef.child(productID)
                .child("productState")
                .setValue("Approved")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        Toast.makeText(CheckNewSellersProductsActivity.this, "Item Has been Approved, Now Available for Sale", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}