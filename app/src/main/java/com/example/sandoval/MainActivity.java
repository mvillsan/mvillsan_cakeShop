package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button enterBtn;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refs();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterHomeProducts();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn);
        enterBtn = findViewById(R.id.enterBtn);
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void enterHomeProducts(){
        Intent intent = new Intent(getApplicationContext(), HomeProducts.class);
        startActivity(intent);
    }
}