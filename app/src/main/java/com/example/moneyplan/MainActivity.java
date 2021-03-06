package com.example.moneyplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.splash);
        cl.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        dbcenter = new DatabaseHelper(this);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}

