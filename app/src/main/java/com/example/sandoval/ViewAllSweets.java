package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageButton;

import com.example.sandoval.data.DatabaseHandler;
import com.example.sandoval.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ViewAllSweets extends AppCompatActivity {

    ImageButton homeBtn;
    ListView viewAllProducts;

    private ArrayList<String> productArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_sweets);

        refs();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        productArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(ViewAllSweets.this);
        db.getAllProducts();
        List<Product> productList =  db.getAllProducts();

        for(Product product: productList) {
            Log.d("ViewAllActivity", "On Create: " + product.getName() + ", " + product.getId());
            productArrayList.add(product.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                productArrayList
        );

        //add to our listview
        viewAllProducts.setAdapter(arrayAdapter);

        //Attach eventlistener to listview

        viewAllProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Price: " + productList.get(position).getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refs(){
        homeBtn = findViewById(R.id.homeBtn5);
        viewAllProducts = findViewById(R.id.listViewProds);
    }
    public void home(){
        Intent intent = new Intent(getApplicationContext(), HomeCakeShop.class);
        startActivity(intent);
    }
}