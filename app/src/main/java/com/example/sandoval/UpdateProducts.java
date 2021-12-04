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

public class UpdateProducts extends AppCompatActivity {

    ImageButton homeBtn;
    Button searchProd, cmdUpdateBtn;
    EditText prodID, prodName, prodPrice, prodQuant;

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

        cmdUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProductByID();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn6);
        searchProd = findViewById(R.id.searchProdBtnUS);
        prodID = findViewById(R.id.prodIDEditTxtUS);
        prodName = findViewById(R.id.prodNameEditTxtUS);
        prodPrice = findViewById(R.id.prodPriceEditTxtUS);
        prodQuant = findViewById(R.id.prodQuantEditTxtUS);
        cmdUpdateBtn = findViewById(R.id.updateProdBtnUS);
    }

    public void home(){
        Intent intent = new Intent(getApplicationContext(), HomeProducts.class);
        startActivity(intent);
    }

    public void searchProdByID(){
        String productID = prodID.getText().toString();

        //Check whether the product ID edit text is empty or not
        if(productID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Input PRODUCT ID!", Toast.LENGTH_SHORT).show();
            prodName.setText("");
            prodPrice.setText("");
            prodQuant.setText("");
        }else{
            //Getting product items from the database
            DatabaseHandler db = new DatabaseHandler(UpdateProducts.this);

            db.getAllProducts();

            List<Product> productList =  db.getAllProducts();

            for(Product product: productList) {
                Log.d("UpdateProductID", "On Create: " + product.getName() + ", " + product.getId());
            }

            Product product;
            product = db.getProduct(Integer.parseInt(productID));

            //Check whether the Product ID exists
            if(product != null) {
                Toast.makeText(getApplicationContext(),"Product ID EXISTS.", Toast.LENGTH_SHORT).show();
                double productPrice = product.getPrice();
                int productQuantity = product.getQuantity();
                prodName.setText(product.getName());
                prodPrice.setText(productPrice + "");
                prodQuant.setText(productQuantity + "");
            } else {
                Toast.makeText(getApplicationContext(),"Product ID DOES NOT EXISTS.", Toast.LENGTH_SHORT).show();
                prodID.setText("");
                prodName.setText("");
                prodPrice.setText("");
                prodQuant.setText("");
            }
        }
    }

    public void updateProductByID(){
        String productID = prodID.getText().toString();

        //Check whether the product ID edit text is empty or not
        if(productID.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Input PRODUCT ID!", Toast.LENGTH_SHORT).show();
            prodName.setText("");
            prodPrice.setText("");
            prodQuant.setText("");
        }else {
            //Check whether the product name, price and quantity edit texts are empty or not.
            if (prodName.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Search Product ID First!", Toast.LENGTH_SHORT).show();
            } else if (prodPrice.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Search Product ID First!", Toast.LENGTH_SHORT).show();
            } else if (prodQuant.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Search Product ID First!", Toast.LENGTH_SHORT).show();
            } else {
                //Getting product items from the database
                DatabaseHandler db = new DatabaseHandler(UpdateProducts.this);

                db.getAllProducts();

                List<Product> productList = db.getAllProducts();

                for (Product product : productList) {
                    Log.d("UpdateProductID", "On Create: " + product.getName() + ", " + product.getId());
                }

                Product product;
                product = db.getProduct(Integer.parseInt(productID));
                if (product != null) {
                    product.setName(prodName.getText().toString());
                    product.setPrice(Double.parseDouble(prodPrice.getText().toString()));
                    product.setQuantity(Integer.parseInt(prodQuant.getText().toString()));
                    db.updateProduct(product);
                    Toast.makeText(getApplicationContext(), "Product # " + product.getId() + " Succesfully UPDATED !", Toast.LENGTH_SHORT).show();
                }
                prodID.setText("");
                prodName.setText("");
                prodPrice.setText("");
                prodQuant.setText("");
            }
        }
    }
}