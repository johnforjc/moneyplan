package com.example.moneyplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class SettingAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView alarmText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alarm);

        alarmText = findViewById(R.id.AlarmSetting);

        Button button = (Button) findViewById(R.id.settingAlarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "timepicker");
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SettingAlarm.this, Dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateAlarmSettingText(c);
        startAlarm(c);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

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

        alarmText.setText(alarmSetting);
    }


}
