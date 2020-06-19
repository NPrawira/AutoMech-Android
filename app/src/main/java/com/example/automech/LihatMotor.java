package com.example.automech;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatMotor extends AppCompatActivity {
    CursorAdapter adapter;
    DBManager dbManager;
    ListView listView;
    ImageButton kembali;
    String[] from = new String[] {  DatabaseHelper.plat_no, DatabaseHelper.id_pemilik_set,
            DatabaseHelper.jenis_motor, DatabaseHelper.merek, DatabaseHelper.model,
            DatabaseHelper.km_tempuh};
    int[] to = new int[] {R.id.plat_no, R.id.no_id_set, R.id.jenis_motor, R.id.merek, R.id.model,
            R.id.km_tempuh};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatmotor);
        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor =dbManager.readMotor();

        listView = findViewById(R.id.lihatmotor);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.motor_fragment, cursor,from,to,
                0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTxt = view.findViewById(R.id.plat_no);
                TextView id2Txt = view.findViewById(R.id.no_id_set);
                TextView jenisTxt = view.findViewById(R.id.jenis_motor);
                TextView merekTxt = view.findViewById(R.id.merek);
                TextView modelTxt = view.findViewById(R.id.model);
                TextView tempuhTxt = view.findViewById(R.id.km_tempuh);

                String idx = idTxt.getText().toString();
                String id2x = id2Txt.getText().toString();
                String jenisx = jenisTxt.getText().toString();
                String merekx = merekTxt.getText().toString();
                String modelx = modelTxt.getText().toString();
                String tempuhx = tempuhTxt.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UbahMotor.class);
                modify_intent.putExtra("id",idx);
                modify_intent.putExtra("id2",id2x);
                modify_intent.putExtra("jenis",jenisx);
                modify_intent.putExtra("merek",merekx);
                modify_intent.putExtra("model",modelx);
                modify_intent.putExtra("tempuh",tempuhx);
                startActivity(modify_intent);
            }
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatMotor.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
