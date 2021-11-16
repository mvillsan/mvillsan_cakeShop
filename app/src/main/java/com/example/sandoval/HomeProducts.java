package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeProducts extends AppCompatActivity {

    ImageButton homeBtn,addProds,searchByIdProds, viewAllProds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_products);

        homeBtn = findViewById(R.id.homeBtn2);
        addProds = findViewById(R.id.addBtn);
        searchByIdProds = findViewById(R.id.searchByIdBtn);
        viewAllProds = findViewById(R.id.viewAllBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        addProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProd();
            }
        });

        searchByIdProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchID();
            }
        });

        viewAllProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllProd();
            }
        });
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void addProd(){
        Intent intent = new Intent(getApplicationContext(), AddProducts.class);
        startActivity(intent);
    }

    public void searchID(){
        Intent intent = new Intent(getApplicationContext(), SearchProductID.class);
        startActivity(intent);
    }

    public void viewAllProd(){
        Intent intent = new Intent(getApplicationContext(), ViewAllProducts.class);
        startActivity(intent);
    }
}