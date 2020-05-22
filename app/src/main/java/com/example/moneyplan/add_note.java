package com.example.moneyplan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class add_note extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button Submit, Cancel, myDatePickerButton;
    DatabaseHelper dbHelper;
    EditText jumlah_field, nama_catatan;
    RadioGroup option;
    RadioButton option_selected_id;
    Date d;
    String formated_simple_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        dbHelper = new DatabaseHelper(this);

        Submit = findViewById(R.id.button);
        Cancel = findViewById(R.id.button2);
        myDatePickerButton = findViewById(R.id.button5);

        option = findViewById(R.id.radioGroup);
        jumlah_field = findViewById(R.id.editText2);
        nama_catatan = findViewById(R.id.editText3);

        myDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment myDatePicker = new DatePickerFragment();
                myDatePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                boolean tipe_catatan;
                String note="";
                int jumlah_value=0;
                int selectedId = option.getCheckedRadioButtonId();

                if(selectedId != -1)
                {
                    option_selected_id = (RadioButton)findViewById(selectedId);

                    String option_selected = (String) option_selected_id.getText();

                    if(option_selected.equals("Pendapatan"))
                    {
                        tipe_catatan = true;
                    }
                    else
                    {
                        tipe_catatan = false;
                    }

                    if(nama_catatan.getText().length()>0){
                        note = (String) nama_catatan.getText().toString();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Masukkan nama catatan", Toast.LENGTH_LONG).show();
                        return ;
                    }

                    if(jumlah_field.getText().length()>0)
                    {
                        jumlah_value = Integer.parseInt(jumlah_field.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Masukkan jumlah catatan", Toast.LENGTH_LONG).show();
                        return ;
                    }

                    String mySqlQuery = "insert into money_note(Nama_catatan, Tanggal, income, jumlah) VALUES('" + note + "', '" + formated_simple_d + "', '" + tipe_catatan +  "', '" + jumlah_value + "');";

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    try
                    {
                        db.execSQL(mySqlQuery);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Pilih jenis catatan anda", Toast.LENGTH_LONG).show();
                }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(add_note.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        d = c.getTime();
        SimpleDateFormat simple_d = new SimpleDateFormat("yyyy-MM-dd");
        formated_simple_d = simple_d.format(d);

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView selectedDate = findViewById(R.id.textView18);
        selectedDate.setText(currentDateString);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(add_note.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
