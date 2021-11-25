package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandoval.data.DatabaseHandler;
import com.example.sandoval.model.Product;

import java.util.List;

public class SearchProductID extends AppCompatActivity {

    ImageButton homeBtn;
    Button cmdSearchByID;
    EditText prodID;
    TextView prodName,prodPrice, prodQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_id);

        refs();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        cmdSearchByID.setOnClickListener(searchProdByID);
    }
    public void refs(){
        homeBtn = findViewById(R.id.homeBtn4);
        cmdSearchByID = findViewById(R.id.searchProdBtn);
        prodID = findViewById(R.id.prodIDTxt);
        prodName = findViewById(R.id.searchProdNameTxt);
        prodPrice = findViewById(R.id.searchProdPriceTxt);
        prodQuantity = findViewById(R.id.searchProdQuantityTxt);
    }
    public void home(){
        Intent intent = new Intent(this, HomeProducts.class);
        startActivity(intent);
    }

    View.OnClickListener searchProdByID = new View.OnClickListener() {

        String productID = prodID.getText().toString();
        int pID = Integer.parseInt(productID);

        @Override
        public void onClick(View view) {
            if(productID.isEmpty()) {
                Toast.makeText(getApplicationContext(), "PLEASE INPUT A PRODUCT ID!", Toast.LENGTH_SHORT).show();
            }
            /*else{
                DatabaseHandler db = new DatabaseHandler(AddProducts.this);
                if(db.getProduct(pID))
                Product product = new Product();
                product.getName();
                product.getQuantity()
                product.setQuantity(Integer.parseInt(prodQuantity.getText().toString()));


                if(db.addProduct(product)) {
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();

                    List<Product> productList =  db.getAllProducts();

                    for(Product prod: productList) {
                        Log.d("ProductActivity", "On Create: " + prod.getName());
                    }
                }
                prodName.setText("");
                prodPrice.setText("");
                prodQuantity.setText("");
            }*/
        }
    };
}