package com.example.automech;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuatPemilik extends AppCompatActivity {
    EditText id,nama,alamat,notelp;
    RadioGroup r1;
    RadioButton tipe;
    ImageButton batal,simpan;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buatpemilik);

        id = findViewById(R.id.id_pemilik);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        notelp = findViewById(R.id.no_telepon);
        r1 = findViewById(R.id.tipe_id);

        dbManager = new DBManager(this);
        dbManager.open();

        simpan = findViewById(R.id.simpanpemilik);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ids = id.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    tipe = findViewById(tipeid);
                    String tipes = tipe.getText().toString();
                    String namas = nama.getText().toString();
                    String alamats = alamat.getText().toString();
                    String notelps = notelp.getText().toString();
                    if (!ids.isEmpty() && !tipes.isEmpty() && !namas.isEmpty() && !alamats.isEmpty()
                            && !notelps.isEmpty()) {
                        dbManager.createPemilik(ids, tipes, namas, alamats, notelps);
                        Intent intent = new Intent(BuatPemilik.this, BuatMotor.class);
                        intent.putExtra("id", ids);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Data pemilik dengan ID Pemilik "
                                + ids + " berhasil dibuat", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lengkapi data sebelum lanjut!",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        batal = findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BuatPemilik.this, MainMenu.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
