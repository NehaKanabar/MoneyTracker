package com.example.splitmoney;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
        // Future navigation options like Split Money, View History, etc.
    }
}
