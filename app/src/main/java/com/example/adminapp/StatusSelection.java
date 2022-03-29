package com.example.adminapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adminapp.Orders.ConfirmedOrders;
import com.example.adminapp.Orders.PendingOrders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.paperdb.Paper;

public class StatusSelection extends AppCompatActivity {

    private Button ConfirmedBtn, PendingBtn,ApprovedBtn;
    private ProgressDialog loadingBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_selection);


        ConfirmedBtn = (Button) findViewById(R.id.confirmed_btn);
        //ApprovedBtn = (Button) findViewById(R.id.approved_btn);
        PendingBtn = (Button) findViewById(R.id.pending_btn);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);


        PendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StatusSelection.this, PendingOrders.class);
                startActivity(intent);
            }
        });


        ConfirmedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatusSelection.this, ConfirmedOrders.class);
                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.topAppBar);
        toolbar.setTitle("Selection");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }



}