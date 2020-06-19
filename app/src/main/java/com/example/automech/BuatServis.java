package com.example.automech;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BuatServis extends AppCompatActivity {
    EditText id,id2,keluhan,catatan,tanggal;
    RadioGroup r1;
    RadioButton masalah;
    ImageButton simpan;
    DBManager dbManager;

    private Calendar calendar;
    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buatservis);

        dbManager = new DBManager(this);
        dbManager.open();
        final Intent intent= getIntent();
        String ids = intent.getStringExtra("id");

        tanggal = findViewById(R.id.tanggal);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        showDate(year, month+1, day);

        Spinner spinner = findViewById(R.id.montir);

        List<String> categories = new ArrayList<>();
        categories.add("Andi");
        categories.add("Budi");
        categories.add("Dika");
        categories.add("Eko");
        categories.add("Farhan");
        categories.add("Hasna");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        final String[] montirx = new String[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String montir = parent.getItemAtPosition(position).toString();
                montirx[0] = montir;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        id = findViewById(R.id.nota_no);
        id.setText("N");
        id2 = findViewById(R.id.plat_no_set);
        id2.setText(ids);
        keluhan = findViewById(R.id.keluhan);
        catatan = findViewById(R.id.catatanservis);
        r1 = findViewById(R.id.masalah);

        simpan = findViewById(R.id.simpanservis);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ids = id.getText().toString();
                    String id2s = id2.getText().toString();
                    String tanggals = tanggal.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    masalah = findViewById(tipeid);
                    String masalahs = masalah.getText().toString();
                    String keluhans = keluhan.getText().toString();
                    String catatans = catatan.getText().toString();
                    if(catatans.isEmpty()){
                        catatans = "-";
                    }
                    if (!ids.isEmpty()  && !id2s.isEmpty() && !tanggals.isEmpty() &&
                    !masalahs.isEmpty() && !keluhans.isEmpty()) {
                        dbManager.createServis(ids, id2s, montirx[0], tanggals, masalahs, keluhans, catatans);
                        Intent intent1 = new Intent(BuatServis.this, MainMenu.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Nota servis " + ids +
                                " untuk motor " + id2s + " berhasil dibuat",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lengkapi data sebelum lanjut!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, day, month, year);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        tanggal.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
