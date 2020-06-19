package com.example.automech;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String tbl_pemilik = "tbl_pemilik";
    public static final String id_pemilik = "_id";
    public static final String tipe_id = "tipe_id";
    public static final String nama = "nama";
    public static final String alamat = "alamat";
    public static final String no_telepon = "no_telepon";

    public static final String tbl_motor = "tbl_motor";
    public static final String plat_no = "_id";
    public static final String id_pemilik_set = "id_pemilik_set";
    public static final String jenis_motor = "jenis_motor";
    public static final String merek = "merek";
    public static final String model = "model";
    public static final String km_tempuh = "km_tempuh";

    public static final String tbl_servis = "tbl_servis";
    public static final String nota_no = "_id";
    public static final String plat_no_set = "plat_no_set";
    public static final String montir = "montir";
    public static final String tanggal = "tanggal";
    public static final String masalah = "masalah";
    public static final String keluhan = "keluhan";
    public static final String catatan_servis = "catatan_servis";

    static final String database_name = "AUTOMECH.DB";

    static final int database_version = 1;

    private static final String create_tbl_pemilik = "CREATE TABLE " + tbl_pemilik + "("
            + id_pemilik + " TEXT PRIMARY KEY NOT NULL, "
            + tipe_id + " TEXT NOT NULL, "
            + nama + " TEXT NOT NULL, "
            + alamat + " TEXT NOT NULL, "
            + no_telepon + " TEXT NOT NULL"
            + ");";

    private static final String create_tbl_motor = "CREATE TABLE " + tbl_motor + "("
            + plat_no  + " TEXT PRIMARY KEY NOT NULL, "
            + id_pemilik_set + " TEXT NOT NULL, "
            + jenis_motor + " TEXT NOT NULL, "
            + merek + " TEXT NOT NULL, "
            + model + " TEXT NOT NULL, "
            + km_tempuh + " TEXT NOT NULL, "
            + " FOREIGN KEY ( " +  id_pemilik_set + " ) REFERENCES " + tbl_pemilik + "(" + id_pemilik + ")"
            + ");";

    private static final String create_tbl_servis = "CREATE TABLE " + tbl_servis + "("
            + nota_no + " TEXT PRIMARY KEY NOT NULL, "
            + plat_no_set + " TEXT NOT NULL, "
            + montir + " TEXT NOT NULL,"
            + tanggal + " TEXT NOT NULL, "
            + masalah + " TEXT NOT NULL, "
            + keluhan + " TEXT NOT NULL, "
            + catatan_servis + " TEXT, "
            + " FOREIGN KEY ( " + plat_no_set + " ) REFERENCES " + tbl_motor + "(" + plat_no + " ) "
            + ");";

    public DatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_tbl_pemilik);
        db.execSQL(create_tbl_motor);
        db.execSQL(create_tbl_servis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_pemilik);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_motor);
        db.execSQL("DROP TABLE IF EXISTS " + tbl_servis);
        onCreate(db);
    }
}
