package com.example.automech;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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

public class UbahServis extends AppCompatActivity {
    EditText id,plat,keluhan,catatan,tanggal;
    ImageButton batal,simpan,hapus;
    RadioGroup r1;
    RadioButton rb1,rb2,rb3,rb4,masalahx;
    DBManager dbManager;
    String _ID;
    Calendar calendar;

    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubahservis);

        id = findViewById(R.id.nota_no);
        plat = findViewById(R.id.plat_no_set);
        keluhan = findViewById(R.id.keluhan);
        catatan = findViewById(R.id.catatanservis);
        r1 = findViewById(R.id.masalah);
        rb1 = findViewById(R.id.elektrik);
        rb2 = findViewById(R.id.mesin);
        rb3 = findViewById(R.id.transmisi);
        rb4 = findViewById(R.id.lainnya);

        dbManager = new DBManager(this);
        dbManager.open();

        final Intent intent = getIntent();
        String ids = intent.getStringExtra("id");
        String id2s = intent.getStringExtra("id2");
        String mons = intent.getStringExtra("montir");
        String id3s = intent.getStringExtra("id3");
        String masalahs = intent.getStringExtra("masalah");
        String keluhans = intent.getStringExtra("keluhan");
        String catatans = intent.getStringExtra("catatan");

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

        switch (mons){
            case "M001 - Andi":spinner.setSelection(0);
                break;
            case "M002 - Budi":spinner.setSelection(1);
                break;
            case "M003 - Dika":spinner.setSelection(2);
                break;
            case "M004 - Eko":spinner.setSelection(3);
                break;
            case "M005 - Farhan":spinner.setSelection(4);
                break;
            case "M006 - Hasna":spinner.setSelection(5);
                break;
        }
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

        _ID = ids;
        id.setText(ids);
        plat.setText(id2s);
        switch(masalahs){
            case "Elektrik": rb1.setChecked(true);
                break;
            case "Mesin": rb2.setChecked(true);
                break;
            case "Transmisi": rb3.setChecked(true);
                break;
            case "Lainnya": rb4.setChecked(true);
                break;
        }
        keluhan.setText(keluhans);
        catatan.setText(catatans);
        tanggal.setText(id3s);

        simpan = findViewById(R.id.simpanservis);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String idx = id.getText().toString();
                    String tipex = plat.getText().toString();
                    String tanggalx = tanggal.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    masalahx = findViewById(tipeid);
                    String masalahxx = masalahx.getText().toString();
                    String keluhanx = keluhan.getText().toString();
                    String catatanx = catatan.getText().toString();
                    if(catatanx.isEmpty()){
                        catatanx = "-";
                    }
                    if(!idx.isEmpty() && !tipex.isEmpty() && !masalahxx.isEmpty() && !keluhanx.isEmpty()) {
                        dbManager.updateServis(idx, tipex, montirx[0], tanggalx, masalahxx, keluhanx, catatanx);
                        Intent intent1 = new Intent(UbahServis.this, LihatServis.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Nota servis " + idx +
                                        " atas motor " + tipex + " berhasil diubah",
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

        hapus = findViewById(R.id.hapusservis);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteServis(_ID);
                Intent intent1 = new Intent(UbahServis.this, LihatServis.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Nota servis " + _ID +
                        " berhasil dihapus", Toast.LENGTH_LONG).show();
            }
        });

        batal = findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UbahServis.this, LihatServis.class);
                startActivity(intent1);
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
                    myDateListener, year, month, day);
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

}
