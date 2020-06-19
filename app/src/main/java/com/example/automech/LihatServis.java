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

public class LihatServis extends AppCompatActivity {
    CursorAdapter adapter;
    DBManager dbManager;
    ListView listView;
    ImageButton kembali;
    String[] from = new String[] {  DatabaseHelper.nota_no, DatabaseHelper.plat_no_set,
            DatabaseHelper.montir, DatabaseHelper.tanggal, DatabaseHelper.masalah,
            DatabaseHelper.keluhan, DatabaseHelper.catatan_servis};
    int[] to = new int[] {R.id.nota_no, R.id.plat_no_set, R.id.montir, R.id.tanggal, R.id.masalah,
            R.id.keluhan, R.id.catatanservis};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatservis);
        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor =dbManager.readServis();

        listView = findViewById(R.id.lihatservis);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.servis_fragment, cursor, from, to,
                0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTxt = view.findViewById(R.id.nota_no);
                TextView id2Txt = view.findViewById(R.id.plat_no_set);
                TextView monTxt = view.findViewById(R.id.montir);
                TextView id3Txt = view.findViewById(R.id.tanggal);
                TextView masalahTxt = view.findViewById(R.id.masalah);
                TextView keluhanTxt = view.findViewById(R.id.keluhan);
                TextView catatanTxt = view.findViewById(R.id.catatanservis);

                String idx = idTxt.getText().toString();
                String id2x = id2Txt.getText().toString();
                String monx = monTxt.getText().toString();
                String id3sx = id3Txt.getText().toString();
                String masalahx = masalahTxt.getText().toString();
                String keluhanx = keluhanTxt.getText().toString();
                String catatanx = catatanTxt.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UbahServis.class);
                modify_intent.putExtra("id",idx);
                modify_intent.putExtra("id2",id2x);
                modify_intent.putExtra("montir",monx);
                modify_intent.putExtra("id3",id3sx);
                modify_intent.putExtra("masalah", masalahx);
                modify_intent.putExtra("keluhan",keluhanx);
                modify_intent.putExtra("catatan",catatanx);
                startActivity(modify_intent);
            }
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatServis.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
