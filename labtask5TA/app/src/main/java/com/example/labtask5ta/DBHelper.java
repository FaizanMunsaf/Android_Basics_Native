package com.example.labtask5ta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {super(context,"database",null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Userdetails(ID TEXT primary key,name TEXT ,rollno Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String ID,String name,String rollno) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", ID);
        values.put("name", name);
        values.put("rollno", rollno);
        long result = DB.insert("Userdetails", null, values);
        DB.close();
        if (result == 1) {
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from Userdetails",null );
        return res;
    }

}
