package com.example.automech;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    ImageButton buatdata,buatmotor,buatservis, lihatpemilik, lihatmotor, lihatservis;
    boolean backPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        buatdata = findViewById(R.id.buatdata);
        buatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buatdata = new Intent(MainMenu.this, BuatPemilik.class);
                startActivity(buatdata);
                Toast.makeText(getApplicationContext(),
                        "Buat data baru", Toast.LENGTH_SHORT).show();
            }
        });

        buatmotor = findViewById(R.id.buatmotor);
        buatmotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buatdata = new Intent(MainMenu.this, BuatMotorBaru.class);
                startActivity(buatdata);
                Toast.makeText(getApplicationContext(),
                        "Buat motor baru", Toast.LENGTH_SHORT).show();
            }
        });

        buatservis = findViewById(R.id.buatservis);
        buatservis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buatdata = new Intent(MainMenu.this, BuatServisBaru.class);
                startActivity(buatdata);
                Toast.makeText(getApplicationContext(),
                        "Buat servis baru", Toast.LENGTH_SHORT).show();
            }
        });

        lihatpemilik = findViewById(R.id.lihatpemilik);
        lihatpemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihatpemilik = new Intent(MainMenu.this, LihatPemilik.class);
                startActivity(lihatpemilik);
                Toast.makeText(getApplicationContext(),
                        "Lihat data pemilik", Toast.LENGTH_SHORT).show();
            }
        });

        lihatmotor = findViewById(R.id.lihatmotor);
        lihatmotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihatmotor = new Intent(MainMenu.this, LihatMotor.class);
                startActivity(lihatmotor);
                Toast.makeText(getApplicationContext(),
                        "Lihat data motor", Toast.LENGTH_SHORT).show();
            }
        });


        lihatservis = findViewById(R.id.lihatservis);
        lihatservis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihatservis = new Intent(MainMenu.this, LihatServis.class);
                startActivity(lihatservis);
                Toast.makeText(getApplicationContext(),
                        "Lihat data servis", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(backPressed) {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Tekan dua kali untuk keluar", Toast.LENGTH_LONG).show();
            backPressed = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressed = false;
                }
            }, 2000);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
