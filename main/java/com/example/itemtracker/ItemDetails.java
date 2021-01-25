package com.example.itemtracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails extends AppCompatActivity {
    TextView tv_details_name, tv_details_id, tv_details;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        tv_details_name = findViewById(R.id.tv_details_name);
        tv_details_id = findViewById(R.id.tv_details_id);
        tv_details = findViewById(R.id.tv_details);
        databaseHelper = new DatabaseHelper(ItemDetails.this);
    }

}
