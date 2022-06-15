package com.example.practiceset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "mydata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table User(name TEXT,email TEXT primary key,password Text,contact TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists User");
    }

    public Boolean insertuserdata(String name, String email, String password, String contact) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("contact", contact);
        long result = DB.insert("User", null, values);
        DB.close();
        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from User", null);
        return res;
    }
}