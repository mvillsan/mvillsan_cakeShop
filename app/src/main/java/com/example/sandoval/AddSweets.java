package com.example.sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sandoval.data.DatabaseHandler;
import com.example.sandoval.model.Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddSweets extends AppCompatActivity {

    //Declaration of Variables
    ImageView homeBtn;
    Button cmdAddSweet;
    TextInputEditText sweetsName, sweetsDesc, sweetsFlavor, sweetsTheme, sweetsPrice, sweetsQuantity;
    TextInputLayout nameLayout,descLayout,flavorLayout, themeLayout, priceLayout, quantityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sweets);

        //References
        refs();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });

        //Adding a Sweet Goody Validation
        cmdAddSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSweets();
            }
        });

        //Validations while edittext is being changed.
        sweetsName.addTextChangedListener(new ValidationTextWatcher(sweetsName));
        sweetsDesc.addTextChangedListener(new ValidationTextWatcher(sweetsDesc));
        sweetsFlavor.addTextChangedListener(new ValidationTextWatcher(sweetsFlavor));
        sweetsTheme.addTextChangedListener(new ValidationTextWatcher(sweetsTheme));
        sweetsPrice.addTextChangedListener(new ValidationTextWatcher(sweetsPrice));
        sweetsQuantity.addTextChangedListener(new ValidationTextWatcher(sweetsQuantity));
    }

    //References
    public void refs(){
        homeBtn = findViewById(R.id.homeBtn3);
        cmdAddSweet = findViewById(R.id.addSWBtn);
        sweetsName = findViewById(R.id.sweetsNameEditTxtAS);
        sweetsDesc = findViewById(R.id.descEditTxtAS);
        sweetsFlavor = findViewById(R.id.flavorEditTxtAS);
        sweetsTheme = findViewById(R.id.themeEditTxtAS);
        sweetsPrice = findViewById(R.id.priceEditTxtAS);
        sweetsQuantity = findViewById(R.id.quantityEditTxtAS);
        nameLayout = findViewById(R.id.nameASGTextInputLayout);
        descLayout = findViewById(R.id.descASGTextInputLayout);
        flavorLayout = findViewById(R.id.flavorASGTextInputLayout);
        themeLayout = findViewById(R.id.themeASGTextInputLayout);
        priceLayout = findViewById(R.id.priceASGTextInputLayout);
        quantityLayout = findViewById(R.id.quantityASGTextInputLayout);
    }

    //Return to Home Screen
    public void home(){
        Intent intent = new Intent(getApplicationContext(), HomeCakeShop.class);
        startActivity(intent);
    }

    //Adding a Sweet Goody in the database
    public boolean addSweets() {
        boolean isValid = true;
        String name = sweetsName.getText().toString();
        String descrip = sweetsDesc.getText().toString();
        String flavor = sweetsFlavor.getText().toString();
        String theme = sweetsTheme.getText().toString();
        String sPrice = sweetsPrice.getText().toString();
        String sQuantity = sweetsQuantity.getText().toString();

        //Sweets Name Validation
        if (name.isEmpty()) {
            nameLayout.setError(getString(R.string.req_nameSweet));
            isValid = false;
        } else {
            Boolean isValidName = name.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValidName) {
                nameLayout.setError("Invalid Sweet's Name, ex: Choco Cake");
                requestFocus(sweetsName);
                return false;
            } else {
                nameLayout.setErrorEnabled(false);
                nameLayout.setError("");
            }
        }

        //Sweets Description Validations
        if (descrip.isEmpty()) {
            descLayout.setError(getString(R.string.req_descSweet));
            isValid = false;
        } else {
            descLayout.setErrorEnabled(false);
            descLayout.setError("");
        }

        //Sweets Flavor Validations
        if (flavor.isEmpty()) {
            flavorLayout.setError(getString(R.string.req_flavorSweet));
            isValid = false;
        } else {
            Boolean isValidFlavor = flavor.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValidFlavor) {
                flavorLayout.setError("Invalid Sweet's Flavor, ex: Chocolate");
                requestFocus(sweetsFlavor);
                return false;
            } else {
                flavorLayout.setErrorEnabled(false);
                flavorLayout.setError("");
            }
        }

        //Sweets Theme Validations
        if (theme.isEmpty()) {
            sweetsTheme.setText(getString(R.string.defaultTheme));
            isValid = false;
        } else {
            Boolean isValidTheme = theme.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValidTheme) {
                themeLayout.setError("Invalid Sweet's Theme, ex: Birthday Theme");
                requestFocus(sweetsTheme);
                return false;
            } else {
                themeLayout.setErrorEnabled(false);
                themeLayout.setError("");
            }
        }

        //Sweets Price Validations
        if (sPrice.isEmpty()) {
            priceLayout.setError(getString(R.string.req_priceSweet));
            isValid = false;
        } else {
            priceLayout.setErrorEnabled(false);
            priceLayout.setError("");
        }

        //Sweets Quantity Validations
        if (sQuantity.isEmpty()) {
            sweetsQuantity.setText(getString(R.string.defaultQuan));
            isValid = false;
        } else {
            quantityLayout.setErrorEnabled(false);
            quantityLayout.setError("");
        }
        //If all validations are passed, add sweets to the database
        if (isValid) {
            boolean validNameDB = true;

            //Parse String to Integer type
            int price = Integer.parseInt(sPrice);
            int quantity = Integer.parseInt(sQuantity);

            Product product = new Product();
            product.setName(name);
            product.setDesc(descrip);
            product.setFlavor(flavor);
            product.setTheme(theme);
            product.setPrice(price);
            product.setQuantity(quantity);

            //Connect to database
            DatabaseHandler db = new DatabaseHandler(AddSweets.this);

            //Check if sweets have the same name in those stored in DB
            List<Product> sweetCheck = db.getAllProducts();
            for (Product sweet : sweetCheck){
                if(product.getName().equalsIgnoreCase(sweet.getName())){
                    Toast.makeText(getApplicationContext(), "Name Already Existed! Input Another Sweet's Name", Toast.LENGTH_SHORT).show();
                    validNameDB = false;
                }
            }

            //Sweets Name does not exist in DB
            if(validNameDB){
                //Add sweets to the database
                if (db.addProduct(product)) {
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();

                    List<Product> productList = db.getAllProducts();

                    for (Product prod : productList) {
                        Log.d("CakeShopActivity", "On Create: " + prod.getName());
                    }
                }
                //Clear fields
                sweetsName.setText("");
                sweetsDesc.setText("");
                sweetsFlavor.setText("");
                sweetsTheme.setText("");
                sweetsPrice.setText("");
                sweetsQuantity.setText("");
            }else{
                sweetsName.setText("");
            }
        }
        return true;
    }

    //Setting FOCUS
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    //Sweets Name Validations
    private boolean validateSweetsName() {
        if (sweetsName.getText().toString().trim().isEmpty()) {
            nameLayout.setError(getString(R.string.req_nameSweet));
        } else {
            String name = sweetsName.getText().toString();
            Boolean  isValid = name.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValid) {
                nameLayout.setError("Invalid Sweet's Name, ex: Choco Cake");
                requestFocus(sweetsName);
                return false;
            } else {
                nameLayout.setErrorEnabled(false);
                nameLayout.setError("");
            }
        }
        return true;
    }

    //Sweets Description Validations
    private boolean validateSweetsDesc() {
        if (sweetsDesc.getText().toString().trim().isEmpty()) {
            descLayout.setError(getString(R.string.req_descSweet));
        } else {
            descLayout.setErrorEnabled(false);
            descLayout.setError("");
        }
        return true;
    }

    //Sweets Flavor Validations
    private boolean validateSweetsFlavor() {
        if (sweetsFlavor.getText().toString().trim().isEmpty()) {
            flavorLayout.setError(getString(R.string.req_flavorSweet));
        } else {
            String flavor = sweetsFlavor.getText().toString();
            Boolean  isValid = flavor.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValid) {
                flavorLayout.setError("Invalid Sweet's Flavor, ex: Chocolate");
                requestFocus(sweetsFlavor);
                return false;
            } else {
                flavorLayout.setErrorEnabled(false);
                flavorLayout.setError("");
            }
        }
        return true;
    }

    //Sweets Theme Validations
    private boolean validateSweetsTheme() {
        if (sweetsTheme.getText().toString().trim().isEmpty()) {
            sweetsTheme.setHint(getString(R.string.req_defaultTheme));
        } else {
            String theme = sweetsTheme.getText().toString();
            Boolean  isValid = theme.matches("[A-Za-z][A-Za-z ]*+");
            if (!isValid) {
                themeLayout.setError("Invalid Sweet's Theme, ex: Birthday Theme");
                requestFocus(sweetsTheme);
                return false;
            } else {
                themeLayout.setErrorEnabled(false);
                themeLayout.setError("");
            }
        }
        return true;
    }

    //Sweets Price Validations
    private boolean validateSweetsPrice() {
        if (sweetsPrice.getText().toString().trim().isEmpty()) {
            priceLayout.setError(getString(R.string.req_priceSweet));
        } else {
            priceLayout.setErrorEnabled(false);
            priceLayout.setError("");
        }
        return true;
    }

    //Sweets Quantity Validations
    private boolean validateSweetsQuan() {
        if (sweetsQuantity.getText().toString().trim().isEmpty()) {
            sweetsQuantity.setHint(getString(R.string.req_defaultQuan));
        } else {
            quantityLayout.setErrorEnabled(false);
            quantityLayout.setError("");
        }
        return true;
    }

    //ValidationTextWatcher
    private class ValidationTextWatcher implements TextWatcher {
        private View view;
        private ValidationTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.sweetsNameEditTxtAS:
                    validateSweetsName();
                    break;
                case R.id.descEditTxtAS:
                    validateSweetsDesc();
                    break;
                case R.id.flavorEditTxtAS:
                    validateSweetsFlavor();
                    break;
                case R.id.themeEditTxtAS:
                    validateSweetsTheme();
                    break;
                case R.id.priceEditTxtAS:
                    validateSweetsPrice();
                    break;
                case R.id.quantityEditTxtAS:
                    validateSweetsQuan();
                    break;
            }
        }
    }
}