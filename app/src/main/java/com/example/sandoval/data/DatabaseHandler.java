package com.example.sandoval.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.sandoval.R;
import com.example.sandoval.model.Product;
import com.example.sandoval.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

        public DatabaseHandler(Context context) {
            super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String CREATE_PRODUCT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                    + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                    + Util.KEY_NAME + " TEXT,"
                    + Util.KEY_PRICE + " DOUBLE,"
                    + Util.KEY_QUANTITY + " INTEGER" + ")";

            db.execSQL(CREATE_PRODUCT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String DROP_TABLE = String.valueOf(R.string.drop_table);
            db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});
        }

        //CRUD

        //Create
        public  void addProduct (Product product) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Util.KEY_NAME,product.getName());
            values.put(Util.KEY_PRICE, product.getPrice());
            values.put(Util.KEY_QUANTITY, product.getQuantity());

            db.insert(Util.TABLE_NAME, null, values);
            db.close();
        }

        //Retrieve
        public Product getProduct(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(Util.TABLE_NAME,
                    new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE, Util.KEY_QUANTITY},
                    Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                    null, null, null, null);

            if(cursor != null) {
                cursor.moveToFirst();
            }

            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setPrice(cursor.getLong(2));
            product.setQuantity(cursor.getInt(3));

            return product;
        }

        //Update

        //Delete
}
