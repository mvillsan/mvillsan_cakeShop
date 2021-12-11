package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeCakeShop extends AppCompatActivity {

    //Declaration of Variables
    ImageButton addProds,searchByIdProds, viewAllProds,updateProds, deleteProds;
    ImageView homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cakeshop);

        //References
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
                addSweets();
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
                viewAllSweets();
            }
        });

        updateProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSweets();
            }
        });

        deleteProds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSweets();
            }
        });
    }

    //References
    public void refs(){
        homeBtn = findViewById(R.id.homeBtn2);
        addProds = findViewById(R.id.addBtn);
        searchByIdProds = findViewById(R.id.searchByIdBtn);
        viewAllProds = findViewById(R.id.viewAllBtn);
        updateProds = findViewById(R.id.updateBtn);
        deleteProds = findViewById(R.id.deleteBtn);
    }

    //Return to Home Screen
    public void home(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //Enter Add Sweets Screen
    public void addSweets(){
        Intent intent = new Intent(getApplicationContext(), AddSweets.class);
        startActivity(intent);
    }

    //Enter Search Sweets Screen
    public void searchID(){
        Intent intent = new Intent(getApplicationContext(), SearchSweetsID.class);
        startActivity(intent);
    }

    //Enter View All Sweets Screen
    public void viewAllSweets(){
        Intent intent = new Intent(getApplicationContext(), ViewAllSweets.class);
        startActivity(intent);
    }

    //Enter Update Sweets Screen
    public void updateSweets(){
        Intent intent = new Intent(getApplicationContext(), UpdateSweets.class);
        startActivity(intent);
    }

    //Enter Delete Sweets Screen
    public void deleteSweets(){
        Intent intent = new Intent(getApplicationContext(), DeleteSweets.class);
        startActivity(intent);
    }
}