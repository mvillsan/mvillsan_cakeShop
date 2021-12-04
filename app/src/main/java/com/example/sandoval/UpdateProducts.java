package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateProducts extends AppCompatActivity {

    ImageButton homeBtn;
    Button searchProd;
    EditText prodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        refs();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        searchProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProdByID();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn6);
        searchProd = findViewById(R.id.searchProdBtnUS);
        prodID = findViewById(R.id.prodIDEditTxtUS);
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(), HomeProducts.class);
        startActivity(intent);
    }

    public void searchProdByID(){
        //Toast.makeText(getApplicationContext(), "button clicked", Toast.LENGTH_SHORT).show();

        String productID = prodID.getText().toString();

        if(productID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Input PRODUCT ID!", Toast.LENGTH_SHORT).show();
        }
    }
}