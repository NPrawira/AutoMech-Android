package com.example.automech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UbahMotor extends AppCompatActivity {
    EditText id,id2,merek,model,tempuh;
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
        setContentView(R.layout.ubahmotor);

        id = findViewById(R.id.plat_no);
        id2 = findViewById(R.id.no_id_set);
        merek = findViewById(R.id.merek);
        model = findViewById(R.id.model);
        tempuh = findViewById(R.id.km_tempuh);
        r1 = findViewById(R.id.jenis_motor);
        rb1 = findViewById(R.id.bebek);
        rb2 = findViewById(R.id.skutik);
        rb3 = findViewById(R.id.sport);
        rb4 = findViewById(R.id.lainnya);
        dbManager = new DBManager(this);
        dbManager.open();

        final Intent intent = getIntent();
        String ids = intent.getStringExtra("id");
        String id2s = intent.getStringExtra("id2");
        String jeniss =intent.getStringExtra("jenis");
        String mereks = intent.getStringExtra("merek");
        String models = intent.getStringExtra("model");
        String tempuhs = intent.getStringExtra("tempuh");

        AutoCompleteTextView autocmplt;
        String[] arr = {"Aprilia","Bajaj","BMW Motorrad","Ducati","Gilera","Harley-Davidson","Honda",
                "Husqvarna","Kaisar","Kanzen","Kawasaki","Kymco","KTM","Lambretta","Minerva",
                "MV Agusta","Piaggio","Suzuki","SYM","Triumph","TVS", "Vespa","VIAR","Yamaha"};

        autocmplt = findViewById(R.id.merek);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arr);
        autocmplt.setThreshold(1);
        autocmplt.setAdapter(adapter);

        _ID = ids;
        id.setText(ids);
        switch(jeniss){
            case "Bebek": rb1.setChecked(true);
                break;
            case "Skutik": rb2.setChecked(true);
                break;
            case "Sport": rb3.setChecked(true);
                break;
            case "Lainnya": rb4.setChecked(true);
                break;
        }
        id2.setText(id2s);
        merek.setText(mereks);
        model.setText(models);
        tempuh.setText(tempuhs);

        simpan = findViewById(R.id.simpanmotor);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String idx = id.getText().toString();
                    String id2x = id2.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    tipex = findViewById(tipeid);
                    String jenisx = tipex.getText().toString();
                    String merekx = merek.getText().toString();
                    String modelx = model.getText().toString();
                    String tempuhx = tempuh.getText().toString();
                    if(!idx.isEmpty() && !id2x.isEmpty() && !jenisx.isEmpty() &&
                            !merekx.isEmpty() && !modelx.isEmpty() && !tempuhx.isEmpty()) {
                        dbManager.updateMotor(idx, id2x, jenisx, merekx, modelx, tempuhx);
                        Intent intent1 = new Intent(UbahMotor.this, LihatMotor.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Data motor " + idx +
                                        " atas ID Pemilik " + id2x + " berhasil diubah",
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

        hapus = findViewById(R.id.hapusmotor);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteMotor(_ID);
                Intent intent1 = new Intent(UbahMotor.this, LihatMotor.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Data motor " + _ID +
                        " berhasil dihapus", Toast.LENGTH_LONG).show();
            }
        });

        batal = findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UbahMotor.this, LihatMotor.class);
                startActivity(intent1);
            }
        });

    }
}
