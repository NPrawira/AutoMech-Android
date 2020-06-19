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

public class BuatMotorBaru extends AppCompatActivity {
    CursorAdapter adapter;
    DBManager dbManager;
    ListView listView;
    ImageButton kembali;
    String[] from = new String[] {  DatabaseHelper.id_pemilik, DatabaseHelper.tipe_id,
            DatabaseHelper.nama, DatabaseHelper.alamat, DatabaseHelper.no_telepon};
    int[] to = new int[] {R.id.id_pemilik, R.id.tipe_id, R.id.nama, R.id.alamat, R.id.no_telepon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatpemilikbaru);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.readPemilik();

        listView = findViewById(R.id.lihatpemilik);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.pemilik_fragment, cursor,from,to,
                0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTxt = view.findViewById(R.id.id_pemilik);
                String idx = idTxt.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), BuatMotor.class);
                modify_intent.putExtra("id",idx);
                startActivity(modify_intent);
            }
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuatMotorBaru.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
