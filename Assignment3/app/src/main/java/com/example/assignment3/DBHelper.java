package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {super(context,"mydb",null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Userdetails(name TEXT primary key,Description TEXT ,date_ TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name,String description,String date_) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("Description", description);
        values.put("date_", date_);
        long result = DB.insert("Userdetails", null, values);
        DB.close();
        if (result == 1) {
            return true;
        }
        else{
            return false;
        }
    }


    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from Userdetails",null );
        return res;
    }

    public Integer deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        int i = DB.delete("Userdetails","name=?",new String[]{name});
        return i;
    }

    public boolean updateDAta(String name,String description,String date_){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("date", date_);
        Cursor cursor = DB.rawQuery("select * from Userdetails where name=?",new String[]{name});
        if(cursor.getCount() > 0){
            long result = DB.update("Userdetails",values,"name=?",new String[]{name});
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
