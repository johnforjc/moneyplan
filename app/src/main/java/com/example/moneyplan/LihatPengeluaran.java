package com.example.moneyplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class LihatPengeluaran extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;
    Cursor cursor;
    ListView listView;
    MyObject_ListAdapter listAdapter;
    Spinner mySpinner;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pengeluaran);
        mySpinner = (Spinner) findViewById(R.id.sp_name);
        myButton = (Button) findViewById(R.id.button4);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seleted = mySpinner.getSelectedItemPosition();

                listView = (ListView) findViewById(R.id.pengeluaran_lv);
                dbHelper = new DatabaseHelper(getApplicationContext());
                sqLiteDatabase = dbHelper.getReadableDatabase();

                if(seleted == 0) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-01-01' AND '2020-01-2020'  ", null);
                else if(seleted == 2) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-03-01' AND '2020-03-31'  ", null);
                else if(seleted == 1) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-02-01' AND '2020-02-31'  ", null);
                else if(seleted == 3) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-04-01' AND '2020-04-31'  ", null);
                else if(seleted == 4) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-05-01' AND '2020-05-31'  ", null);
                else if(seleted == 5) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-06-01' AND '2020-06-31'  ", null);
                else if(seleted == 6) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-07-01' AND '2020-07-31'  ", null);
                else if(seleted == 7) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-08-01' AND '2020-08-31'  ", null);
                else if(seleted == 8) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-09-01' AND '2020-09-31'  ", null);
                else if(seleted == 9) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-10-01' AND '2020-10-31'  ", null);
                else if(seleted == 10) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-11-01' AND '2020-11-31'  ", null);
                else if(seleted == 11) cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='false' AND  Tanggal BETWEEN '2020-12-01' AND '2020-12-31'  ", null);

//                Toast.makeText(getApplicationContext(), "urutan "+ urutan, Toast.LENGTH_LONG).show();

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
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(LihatPengeluaran.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

}
