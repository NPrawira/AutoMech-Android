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

public class BuatServisBaru extends AppCompatActivity {
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
        setContentView(R.layout.lihatmotorbaru);
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
                String idx = idTxt.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), BuatServis.class);
                modify_intent.putExtra("id",idx);
                startActivity(modify_intent);
            }
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BuatServisBaru.this, MainMenu.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
