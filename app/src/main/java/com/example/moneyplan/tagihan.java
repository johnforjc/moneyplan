package com.example.moneyplan;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class tagihan extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button submit, pilihwaktu, pilihtanggal;
    Date d;
    String formated_simple_d;
    TextView waktu, mydate;
    DatabaseHelper dbHelper;
    EditText namaTagihan;
    Calendar c;
    Cursor cursor;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan);

        id = 1000;

        dbHelper = new DatabaseHelper(this);

        c = Calendar.getInstance();

        namaTagihan = findViewById(R.id.editText);

        waktu = findViewById(R.id.textView23);
        mydate = findViewById(R.id.textView22);

        submit = findViewById(R.id.button8);
        pilihwaktu = findViewById(R.id.button7);
        pilihtanggal = findViewById(R.id.button6);

        pilihtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment myDatePicker = new DatePickerFragment();
                myDatePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        pilihwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "timepicker");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaTagihan.getText().length()>0)
                {
                    String stagihan = namaTagihan.getText().toString();

                    d = c.getTime();
                    SimpleDateFormat simple_d = new SimpleDateFormat("yyyy-MM-dd");
                    formated_simple_d = simple_d.format(d);

                    String mySqlQuery = "insert into tagihan(nama_tagihan, tanggal) VALUES('" + stagihan + "', '" + formated_simple_d + "');";

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    try
                    {
                        db.execSQL(mySqlQuery);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();

//                    db = dbHelper.getReadableDatabase();
//                    cursor = db.rawQuery("SELECT id_tagihan FROM tagihan WHERE nama_tanggihan = '" + stagihan + "' AND tanggal = '" + formated_simple_d + "'", null);

                    id += 1;

                    startAlarm(id);

                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        d = c.getTime();
        SimpleDateFormat simple_d = new SimpleDateFormat("yyyy-MM-dd");
        formated_simple_d = simple_d.format(d);

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        mydate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);


        updateAlarmSettingText(c);
//        startAlarm(c);
    }

    private void startAlarm(int reqCode) {
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, alertTagihan.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, reqCode, intent, 0);

        if(c.before(Calendar.getInstance()))
        {
            c.add(Calendar.DATE, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarm.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void updateAlarmSettingText(Calendar c) {
        String alarmSetting = "Alarm set for: ";
        alarmSetting += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        waktu.setText(alarmSetting);
    }

}
