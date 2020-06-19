package com.example.automech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void createPemilik(String _id, String tipe_id, String nama, String alamat, String no_telepon) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.id_pemilik, _id);
        contentValue.put(DatabaseHelper.tipe_id, tipe_id);
        contentValue.put(DatabaseHelper.nama, nama);
        contentValue.put(DatabaseHelper.alamat, alamat);
        contentValue.put(DatabaseHelper.no_telepon, no_telepon);
        database.insert(DatabaseHelper.tbl_pemilik, null, contentValue);
    }

    public Cursor readPemilik() {
        String[] columns = new String[] { DatabaseHelper.id_pemilik, DatabaseHelper.tipe_id, DatabaseHelper.nama, DatabaseHelper.alamat,DatabaseHelper.no_telepon };
        Cursor cursor = database.query(DatabaseHelper.tbl_pemilik, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updatePemilik(String _id, String tipe_id, String nama, String alamat, String no_telepon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.id_pemilik, _id);
        contentValues.put(DatabaseHelper.tipe_id, tipe_id);
        contentValues.put(DatabaseHelper.nama, nama);
        contentValues.put(DatabaseHelper.alamat, alamat);
        contentValues.put(DatabaseHelper.no_telepon, no_telepon);
        int i = database.update(DatabaseHelper.tbl_pemilik, contentValues, DatabaseHelper.id_pemilik + " = '" + _id + "'", null);
        return i;
    }

    public void deletePemilik(String _id) {
        database.delete(DatabaseHelper.tbl_pemilik, DatabaseHelper.id_pemilik + " = '" + _id + "'", null);
    }

    public void createMotor(String _id, String id_pemilik_set, String jenis_motor, String merek, String model, String km_tempuh) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.plat_no, _id);
        contentValue.put(DatabaseHelper.id_pemilik_set, id_pemilik_set);
        contentValue.put(DatabaseHelper.jenis_motor, jenis_motor);
        contentValue.put(DatabaseHelper.merek, merek);
        contentValue.put(DatabaseHelper.model, model);
        contentValue.put(DatabaseHelper.km_tempuh, km_tempuh);
        database.insert(DatabaseHelper.tbl_motor, null, contentValue);
    }

    public Cursor readMotor() {
        String[] columns = new String[] { DatabaseHelper.plat_no, DatabaseHelper.id_pemilik_set, DatabaseHelper.jenis_motor, DatabaseHelper.merek, DatabaseHelper.model, DatabaseHelper.km_tempuh };
        Cursor cursor = database.query(DatabaseHelper.tbl_motor, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateMotor(String _id, String id_pemilik_set, String jenis_motor, String merek, String model, String km_tempuh) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.plat_no, _id);
        contentValues.put(DatabaseHelper.id_pemilik_set, id_pemilik_set);
        contentValues.put(DatabaseHelper.jenis_motor, jenis_motor);
        contentValues.put(DatabaseHelper.merek, merek);
        contentValues.put(DatabaseHelper.model, model);
        contentValues.put(DatabaseHelper.km_tempuh, km_tempuh);
        int i = database.update(DatabaseHelper.tbl_motor, contentValues, DatabaseHelper.plat_no + " = '" + _id + "'", null);
        return i;
    }

    public void deleteMotor(String _id) {
        database.delete(DatabaseHelper.tbl_motor, DatabaseHelper.plat_no + "='" + _id + "'", null);
    }

    public void createServis(String _id, String plat_no_set, String montir, String tanggal, String masalah, String keluhan, String catatan_servis) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.nota_no, _id);
        contentValue.put(DatabaseHelper.plat_no_set, plat_no_set);
        contentValue.put(DatabaseHelper.montir, montir);
        contentValue.put(DatabaseHelper.tanggal, tanggal);
        contentValue.put(DatabaseHelper.masalah, masalah);
        contentValue.put(DatabaseHelper.keluhan, keluhan);
        contentValue.put(DatabaseHelper.catatan_servis, catatan_servis);
        database.insert(DatabaseHelper.tbl_servis, null, contentValue);
    }

    public Cursor readServis() {
        String[] columns = new String[] { DatabaseHelper.nota_no, DatabaseHelper.plat_no_set, DatabaseHelper.montir, DatabaseHelper.tanggal, DatabaseHelper.masalah, DatabaseHelper.keluhan, DatabaseHelper.catatan_servis};
        Cursor cursor = database.query(DatabaseHelper.tbl_servis, columns, null, null, null, null, DatabaseHelper.nota_no);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateServis(String _id, String plat_no_set, String montir, String tanggal, String masalah, String keluhan, String catatan_servis) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.nota_no, _id);
        contentValues.put(DatabaseHelper.plat_no_set, plat_no_set);
        contentValues.put(DatabaseHelper.montir, montir);
        contentValues.put(DatabaseHelper.tanggal, tanggal);
        contentValues.put(DatabaseHelper.masalah, masalah);
        contentValues.put(DatabaseHelper.keluhan, keluhan);
        contentValues.put(DatabaseHelper.catatan_servis, catatan_servis);
        int i = database.update(DatabaseHelper.tbl_servis, contentValues, DatabaseHelper.nota_no + " = '" + _id + "'", null);
        return i;
    }

    public void deleteServis(String _id) {
        database.delete(DatabaseHelper.tbl_servis, DatabaseHelper.nota_no + "='" + _id + "'", null);
    }

}
