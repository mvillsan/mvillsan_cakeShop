package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UpdateProducts extends AppCompatActivity {

    ImageButton homeBtn;

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
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn6);
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(), HomeProducts.class);
        startActivity(intent);
    }
}