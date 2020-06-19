package com.example.automech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UbahPemilik extends AppCompatActivity {
    EditText id,nama,alamat,notelp;
    RadioGroup r1;
    RadioButton rb1,rb2,rb3,rb4,tipex;
    ImageButton batal,simpan,hapus;
    DBManager dbManager;
    String _ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubahpemilik);

        id = findViewById(R.id.id_pemilik);
        nama = findViewById(R.id.spesialisasi);
        alamat = findViewById(R.id.alamat);
        notelp = findViewById(R.id.no_telepon);
        r1 = findViewById(R.id.tipe_id);
        rb1 = findViewById(R.id.ktp);
        rb2 = findViewById(R.id.sim);
        rb3 = findViewById(R.id.stnk);
        rb4 = findViewById(R.id.lainnya);
        dbManager = new DBManager(this);
        dbManager.open();

        final Intent intent = getIntent();
        String ids = intent.getStringExtra("id");
        String tipes = intent.getStringExtra("tipe");
        String namas = intent.getStringExtra("nama");
        String alamats = intent.getStringExtra("alamat");
        String notelps = intent.getStringExtra("notelp");

        _ID = ids;
        id.setText(ids);
        switch(tipes){
            case "KTP": rb1.setChecked(true);
                        break;
            case "SIM": rb2.setChecked(true);
                        break;
            case "STNK": rb3.setChecked(true);
                        break;
            case "Lainnya": rb4.setChecked(true);
                            break;
        }
        nama.setText(namas);
        alamat.setText(alamats);
        notelp.setText(notelps);

        simpan = findViewById(R.id.simpanpemilik);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String idx = id.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    tipex = findViewById(tipeid);
                    String tipexx = tipex.getText().toString();
                    String namax = nama.getText().toString();
                    String alamatx = alamat.getText().toString();
                    String notelpx = notelp.getText().toString();
                    if(!idx.isEmpty() && !tipexx.isEmpty() && !namax.isEmpty() && !alamatx.isEmpty()
                    && !notelpx.isEmpty()) {
                        dbManager.updatePemilik(idx, tipexx, namax, alamatx, notelpx);
                        Intent intent1 = new Intent(UbahPemilik.this, LihatPemilik.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Data pemilik dengan ID Pemilik "
                                + idx + " berhasil diubah", Toast.LENGTH_LONG).show();
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

        hapus = findViewById(R.id.hapuspemilik);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dbManager.deletePemilik(_ID);
                Intent intent1 = new Intent(UbahPemilik.this, LihatPemilik.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Data pemilik dengan ID Pemilik " + _ID +
                        " berhasil dihapus", Toast.LENGTH_LONG).show();
            }
        });

        batal = findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UbahPemilik.this, LihatPemilik.class);
                startActivity(intent1);
            }
        });

    }
}
