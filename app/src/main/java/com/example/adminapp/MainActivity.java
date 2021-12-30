package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn, approveSellerProductsBtn;

    private LinearLayout checkNewOrders,editProducts,addACategory,showStatistics,Approveproducts,imageAds;
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
        imageAds = findViewById(R.id.putAnAdPic);

        editProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(MainActivity.this, NewOrdersActivity.class);
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
}