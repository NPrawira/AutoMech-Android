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

public class LihatPemilik extends AppCompatActivity {
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
        setContentView(R.layout.lihatpemilik);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor =dbManager.readPemilik();

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
                TextView tipeTxt = view.findViewById(R.id.tipe_id);
                TextView namaTxt = view.findViewById(R.id.nama);
                TextView alamatTxt = view.findViewById(R.id.alamat);
                TextView notelTxt = view.findViewById(R.id.no_telepon);

                String idx = idTxt.getText().toString();
                String tipex = tipeTxt.getText().toString();
                String namax = namaTxt.getText().toString();
                String alamatx = alamatTxt.getText().toString();
                String notelx = notelTxt.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UbahPemilik.class);
                modify_intent.putExtra("id",idx);
                modify_intent.putExtra("tipe",tipex);
                modify_intent.putExtra("nama",namax);
                modify_intent.putExtra("alamat",alamatx);
                modify_intent.putExtra("notelp",notelx);
                startActivity(modify_intent);
            }
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatPemilik.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
