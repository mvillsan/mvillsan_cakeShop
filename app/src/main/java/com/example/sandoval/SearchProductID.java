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

        //Method to get the id fields used in xml layout files
        refs();

        //Going back to the home page
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        cmdSearchByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProdByID();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn4);
        cmdSearchByID = findViewById(R.id.searchProdBtnSS);
        prodID = findViewById(R.id.prodIDEditTxtSS);
        prodName = findViewById(R.id.prodNameTxtSS);
        prodPrice = findViewById(R.id.prodPriceTxtSS);
        prodQuantity = findViewById(R.id.prodQuantTxtSS);
    }

    public void home(){
        Intent intent = new Intent(this, HomeProducts.class);
        startActivity(intent);
    }

    public void searchProdByID(){
        //Converting String prodID to Integer type
        String productID = prodID.getText().toString();

        if(productID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Input PRODUCT ID!", Toast.LENGTH_SHORT).show();
            prodName.setText("");
            prodPrice.setText("");
            prodQuantity.setText("");
        }else{
            //Getting product items from the database
            DatabaseHandler db = new DatabaseHandler(SearchProductID.this);

            db.getAllProducts();

            List<Product> productList =  db.getAllProducts();

            for(Product product: productList) {
                Log.d("SearchProductID", "On Create: " + product.getName() + ", " + product.getId());
            }

            //Product product = db.getProduct(Integer.parseInt(productID));
            Product product;
            product = db.getProduct(Integer.parseInt(productID));
            //Check whether the Product ID exists
            if(product != null) {
                Toast.makeText(getApplicationContext(),"Product ID EXISTS.", Toast.LENGTH_SHORT).show();
                double productPrice = product.getPrice();
                int productQuantity = product.getQuantity();
                prodName.setText(product.getName());
                prodPrice.setText(" " +productPrice);
                prodQuantity.setText(" " + productQuantity);
            } else {
                Toast.makeText(getApplicationContext(),"Product ID DOES NOT EXISTS.", Toast.LENGTH_SHORT).show();
                prodID.setText("");
                prodName.setText("");
                prodPrice.setText("");
                prodQuantity.setText("");
            }
        }
    }
}