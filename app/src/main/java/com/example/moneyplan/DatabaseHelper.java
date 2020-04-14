package com.example.moneyplan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "moneyplan.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context)  // Contructor
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) // Pembuatan database
    {
        String sql = "create table money_note(" +
                "id_catatan integer primary key autoincrement, " +
                "Nama_catatan text null, " +
                "Tanggal date null," +
                "income boolean null," +
                "jumlah int null)";
        Log.d("Data", "onCreate"+ sql);
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
