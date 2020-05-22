package com.example.moneyplan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView addNote = findViewById(R.id.imageView);
        addNote.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(Dashboard.this, add_note.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView lihatPendapatan = findViewById(R.id.imageView3);
        lihatPendapatan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(Dashboard.this, LihatPendapatanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView lihatPengeluaran = findViewById(R.id.imageView4);
        lihatPengeluaran.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(Dashboard.this, LihatPengeluaran.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView SettingAlarm = findViewById(R.id.imageView5);
        SettingAlarm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(Dashboard.this, SettingAlarm.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView pengingatTagihan = findViewById(R.id.imageView10);
        pengingatTagihan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(Dashboard.this, tagihan.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
