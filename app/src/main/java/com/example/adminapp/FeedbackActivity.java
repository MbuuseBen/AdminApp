package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.adminapp.Model.Feedback;
import com.example.adminapp.ViewHolder.FeedbackViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

import io.paperdb.Paper;

public class FeedbackActivity extends AppCompatActivity {
    private RecyclerView feedbackList;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity_layout);

        reference = FirebaseDatabase.getInstance().getReference().child("Feedback");
        feedbackList = findViewById(R.id.feedback_list);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.topAppBar);
        mToolbar.setTitle("Feedback");
        setSupportActionBar(mToolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


//displayFeedback();
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

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



                    Intent home = new Intent(FeedbackActivity.this, MainActivity.class);
                    startActivity(home);



                    break;


                case R.id.seller_logout:


                    Paper.book().destroy();
                    FirebaseAuth.getInstance().signOut();

                    Intent intent = new Intent(FeedbackActivity.this, LoginActivity.class);
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

    @Override
    protected void onStart()
    {
        super.onStart();


       // DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Feedback");
//        FirebaseRecyclerOptions<Feedback> options =
//                new FirebaseRecyclerOptions.Builder<Feedback>()
//                        .setQuery(reference.orderByChild("subject"), Feedback.class)
//                        .build();

        FirebaseRecyclerOptions<Feedback> options = new FirebaseRecyclerOptions.Builder<Feedback>()
                .setQuery(reference,Feedback.class).build();

        FirebaseRecyclerAdapter<Feedback, FeedbackViewHolder> adapter =
                new FirebaseRecyclerAdapter<Feedback, FeedbackViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull FeedbackViewHolder holder, int i, @NonNull @NotNull Feedback model) {


                holder.txtSubject.setText(model.getSubject());
                holder.txtMessage.setText(model.getMessage());

            }

            @NonNull
            @NotNull
            @Override
            public FeedbackViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_layout, parent, false);
                FeedbackViewHolder holder = new FeedbackViewHolder(view);
                return holder;
            }
        };

        feedbackList.setAdapter(adapter);
        adapter.startListening();

    }
}