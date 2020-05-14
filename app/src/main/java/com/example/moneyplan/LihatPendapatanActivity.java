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

public class LihatPendapatanActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;
    Cursor cursor;
    ListView listView;
    MyObject_ListAdapter listAdapter;
    Button myButton;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_pendapatan);
        mySpinner = (Spinner) findViewById(R.id.sp_pendapatan);
        myButton = (Button) findViewById(R.id.button3);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seleted = mySpinner.getSelectedItemPosition();
                String urutan = "";

                if(seleted == 0) urutan="01";
                else if(seleted == 1) urutan="02";
                else if(seleted == 2) urutan="03";
                else if(seleted == 3) urutan="04";
                else if(seleted == 4) urutan="05";
                else if(seleted == 5) urutan="06";
                else if(seleted == 6) urutan="07";
                else if(seleted == 7) urutan="08";
                else if(seleted == 8) urutan="09";
                else if(seleted == 9) urutan="10";
                else if(seleted == 10) urutan="11";
                else if(seleted == 11) urutan="12";

                listView = (ListView) findViewById(R.id.pendapatan_lv);
                dbHelper = new DatabaseHelper(getApplicationContext());
                sqLiteDatabase = dbHelper.getReadableDatabase();
                cursor =sqLiteDatabase.rawQuery("SELECT * FROM money_note WHERE income='true' AND  Tanggal BETWEEN '2020-"+ urutan +"-01' AND '2020-"+ urutan +"-31'  ", null);
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
        Intent intent = new Intent(LihatPendapatanActivity.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
