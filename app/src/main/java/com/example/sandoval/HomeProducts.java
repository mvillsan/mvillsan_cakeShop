package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeProducts extends AppCompatActivity {

    ImageButton homeBtn,addProds,searchByIdProds, viewAllProds,updateProds, deleteProds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_products);

        refs();

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

        updateProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProd();
            }
        });

        deleteProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProd();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn2);
        addProds = findViewById(R.id.addBtn);
        searchByIdProds = findViewById(R.id.searchByIdBtn);
        viewAllProds = findViewById(R.id.viewAllBtn);
        updateProds = findViewById(R.id.updateBtn);
        deleteProds = findViewById(R.id.deleteBtn);
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

    public void updateProd(){
        Intent intent = new Intent(getApplicationContext(), UpdateProducts.class);
        startActivity(intent);
    }

    public void deleteProd(){
        Intent intent = new Intent(getApplicationContext(), DeleteProducts.class);
        startActivity(intent);
    }
}