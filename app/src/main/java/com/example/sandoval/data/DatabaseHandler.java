package com.example.sandoval.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.sandoval.R;
import com.example.sandoval.model.Product;
import com.example.sandoval.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

        public DatabaseHandler(Context context) {
            super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String CREATE_CAKESHOP_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                    + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                    + Util.KEY_NAME + " TEXT,"
                    + Util.KEY_DESCRIPTION + " TEXT,"
                    + Util.KEY_FLAVOR + " TEXT,"
                    + Util.KEY_THEME + " TEXT,"
                    + Util.KEY_PRICE + " INTEGER,"
                    + Util.KEY_QUANTITY + " INTEGER" + ")";

            db.execSQL(CREATE_CAKESHOP_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String DROP_TABLE = String.valueOf(R.string.drop_table);
            db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});
        }

        //CRUD

        //Create
        public boolean addProduct (Product product) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Util.KEY_NAME,product.getName());
            values.put(Util.KEY_DESCRIPTION,product.getDesc());
            values.put(Util.KEY_FLAVOR,product.getFlavor());
            values.put(Util.KEY_THEME,product.getTheme());
            values.put(Util.KEY_PRICE, product.getPrice());
            values.put(Util.KEY_QUANTITY, product.getQuantity());

            db.insert(Util.TABLE_NAME, null, values);
            Log.d("db","Add Sweets: " + "Successful");

            db.close();
            return true;
        }

        //Retrieve by productID
        public Product getProduct(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(Util.TABLE_NAME,
                    new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_DESCRIPTION, Util.KEY_FLAVOR, Util.KEY_THEME, Util.KEY_PRICE, Util.KEY_QUANTITY},
                    Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                    null, null, null, null);

            if(cursor != null) {
                cursor.moveToFirst();
            }

            if(cursor.getCount() != 0)
            {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDesc(cursor.getString(2));
                product.setFlavor(cursor.getString(3));
                product.setTheme(cursor.getString(4));
                product.setPrice(cursor.getInt(5));
                product.setQuantity(cursor.getInt(6));
                return product;
            } else{
                return null;
            }
        }

        //Retrieve all products
        public List<Product> getAllProducts() {
            List<Product> productList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            //Select All Contacts
            String selectAll = "SELECT * FROM " + Util.TABLE_NAME;


            Cursor cursor = db.rawQuery(selectAll, null);

            if(cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setName(cursor.getString(1));
                    product.setDesc(cursor.getString(2));
                    product.setFlavor(cursor.getString(3));
                    product.setTheme(cursor.getString(4));
                    product.setPrice(cursor.getInt(5));
                    product.setQuantity(cursor.getInt(6));

                    //add product object to list
                    productList.add(product);
                }while(cursor.moveToNext());
            }

            return productList;
        }

        //Update
        public int updateProduct(Product product) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Util.KEY_NAME,product.getName());
            values.put(Util.KEY_DESCRIPTION,product.getDesc());
            values.put(Util.KEY_FLAVOR,product.getFlavor());
            values.put(Util.KEY_THEME,product.getTheme());
            values.put(Util.KEY_PRICE, product.getPrice());
            values.put(Util.KEY_QUANTITY, product.getQuantity());

            //update the row

            Log.d("UpdateActivity","Updated product");
            return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                    new String[]{String.valueOf(product.getId())});
        }

        //Delete
        public void deleteProduct(Product product) {
            SQLiteDatabase db = this.getWritableDatabase();

            db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                    new String[]{String.valueOf(product.getId())});
            db.close();
        }
}
