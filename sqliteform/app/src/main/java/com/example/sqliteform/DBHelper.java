package com.example.sqliteform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {super(context,"mydb",null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Userdetails(name TEXT,email TEXT primary key,password Text,contact TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name,String email,String password,String contact) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("contact", contact);
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

    public Integer deleteData(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        int i = DB.delete("Userdetails","email=?",new String[]{email});
        return i;
    }

    public boolean updateDAta(String name,String email,String password,String contact){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("password",password);
        values.put("contact",contact);
        Cursor cursor = DB.rawQuery("select * from Userdetails where email=?",new String[]{email});
        if(cursor.getCount() > 0){
            long result = DB.update("Userdetails",values,"email=?",new String[]{email});
            if(result == 1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
}
