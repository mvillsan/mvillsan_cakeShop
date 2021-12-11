package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Declaration of Variables
    Button enterBtn;
    ImageView homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References
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
                enterHomeCakeShop();
            }
        });
    }

    //References
    public void refs(){
        homeBtn = findViewById(R.id.homeBtn);
        enterBtn = findViewById(R.id.enterBtn);
    }

    //Return to Home Screen
    public void home(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //Enter Home CakeShop Menu
    public void enterHomeCakeShop(){
        Intent intent = new Intent(getApplicationContext(), HomeCakeShop.class);
        startActivity(intent);
    }
}