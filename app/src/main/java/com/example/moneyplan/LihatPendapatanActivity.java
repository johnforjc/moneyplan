package com.example.moneyplan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class LihatPendapatanActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;
    Cursor cursor;
    ListView listView;
    MyObject_ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_pendapatan);

        listView = (ListView) findViewById(R.id.pendapatan_lv);
        dbHelper = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='true'", null);
        listAdapter = new MyObject_ListAdapter(getApplicationContext(), R.layout.rowdata);
        listView.setAdapter(listAdapter);

        if(cursor.moveToFirst())
        {
            MyObject awal = new MyObject("Tanggal", "Nama Catatan", "Jumlah");
            listAdapter.add(awal);

            do{
                String tanggal, namaCatatan, jumlah;

                tanggal = cursor.getString(2);
                namaCatatan = cursor.getString(1);
                jumlah = cursor.getString(4);

                MyObject data = new MyObject(tanggal, namaCatatan, jumlah);

                listAdapter.add(data);

            }while(cursor.moveToNext());
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
