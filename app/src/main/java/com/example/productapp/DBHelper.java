package com.example.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static String DBname="company.db";
    static String tablename="product";
    static String col1="pid";
    static String col2="pcode";
    static String col3="pname";
    static String col4="price";

    public DBHelper(Context context)
    {
        super(context,  DBname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+tablename+"("+col1+" integer primary key autoincrement,"+
        col2+" text,"+
        col3+" text,"+
        col4+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertdata(String pcode,String pname,String price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,pcode);
        cv.put(col3,pname);
        cv.put(col4,price);
        long status=db.insert(tablename,null,cv);
        if (status==-1)
            return false;
        else
            return true;
    }
    public Cursor searchdata(String pcode)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+tablename+" where "+col2+"='"+pcode+"'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }
}
