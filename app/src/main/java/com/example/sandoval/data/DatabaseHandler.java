package com.example.sandoval.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.sandoval.R;
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

        //Retrieve

        //Update

        //Delete
}
