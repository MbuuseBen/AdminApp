package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.adminapp.Orders.ConfirmedOrders;
import com.example.adminapp.Orders.PendingOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn, approveSellerProductsBtn;

    private LinearLayout checkNewOrders,editProducts,addACategory,showStatistics,Approveproducts,feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);


      //  LogoutBtn = (Button)findViewById(R.id.admin_logout_btn);
//        CheckOrdersBtn = (Button) findViewById(R.id.admin_check_orders_btn);
//        maintainProductsBtn = (Button) findViewById(R.id.admin_maintain_btn);
//        approveSellerProductsBtn = (Button) findViewById(R.id.admin_approve_products_btn);

        checkNewOrders = findViewById(R.id.newOrdersPic);
        editProducts = findViewById(R.id.editProductPic);
        addACategory = findViewById(R.id.addCategoryPic);
        Approveproducts = findViewById(R.id.addASupplierPic);
        showStatistics = findViewById(R.id.showStatisticsPic);
        feedback = findViewById(R.id.FeedBackPic);

        editProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        LogoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Paper.book().destroy();
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            }
//        });

        checkNewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StatusSelection.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        Approveproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckNewSellersProductsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });

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

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                    break;


                case R.id.seller_logout:


                    Paper.book().destroy();
                    FirebaseAuth.getInstance().signOut();

                    Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                    logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(logout);
                    finish();
                    break;
            }

            return true;
        }
    };

}