package com.example.automech;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuatMotor extends AppCompatActivity {
    EditText id,id2,merek,model,tempuh;
    RadioGroup r1;
    RadioButton tipe;
    ImageButton simpan;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buatmotor);

        AutoCompleteTextView autocmplt;
        String[] arr = {"Aprilia","Bajaj","BMW Motorrad","Ducati","Gilera","Harley-Davidson","Honda",
                "Husqvarna","Kaisar","Kanzen","Kawasaki","Kymco","KTM","Lambretta","Minerva",
                "MV Agusta","Piaggio","Suzuki","SYM","Triumph","TVS", "Vespa","VIAR","Yamaha"};

        autocmplt = findViewById(R.id.merek);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, arr);
        autocmplt.setThreshold(1);
        autocmplt.setAdapter(adapter);

        dbManager = new DBManager(this);
        dbManager.open();
        Intent intent = getIntent();
        String ids = intent.getStringExtra("id");

        id = findViewById(R.id.plat_no);
        id2 = findViewById(R.id.no_id_set);
        id2.setText(ids);
        merek = findViewById(R.id.merek);
        model = findViewById(R.id.model);
        tempuh = findViewById(R.id.km_tempuh);
        r1 = findViewById(R.id.jenis_motor);

        simpan = findViewById(R.id.simpanmotor);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ids = id.getText().toString();
                    int tipeid = r1.getCheckedRadioButtonId();
                    tipe = findViewById(tipeid);
                    String tipes = tipe.getText().toString();
                    String id2s = id2.getText().toString();
                    String mereks = merek.getText().toString();
                    String models = model.getText().toString();
                    String tempuhs = tempuh.getText().toString();
                    if (!ids.isEmpty() && !tipes.isEmpty() && !id2s.isEmpty() && !mereks.isEmpty()
                            && !models.isEmpty() && !tempuhs.isEmpty()) {
                        dbManager.createMotor(ids, id2s, tipes, mereks, models, tempuhs);
                        Intent intent = new Intent(BuatMotor.this, BuatServis.class);
                        intent.putExtra("id", ids);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Data motor " + ids +
                                " atas ID Pemilik " + id2s + " berhasil dibuat",
                                Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
