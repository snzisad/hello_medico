package com.zisad.hellomedico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MedicineDB extends SQLiteOpenHelper {
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "DISEASES";
    public static final String DATABASE = "MEDICINE.DB";
    public static final String TABLE_NAME = "MEDICINE_TABLE";

    public MedicineDB(Context context) {
        super(context, DATABASE, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEDICINE_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DISEASES TEXT ) ");
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE IF EXISTS MEDICINE_TABLE");
        onCreate(db);
    }

    public boolean addData(String name, String diseases) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put(COL3, diseases);
        if (db.insert(TABLE_NAME, (String) null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public boolean updateData(String id, String name, String diseases) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put(COL3, diseases);
        try {
            if (((long) db.update(TABLE_NAME, contentValues, "ID = " + id, (String[]) null)) == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Error", e.toString());
            return false;
        }
    }

    public Cursor getData() {
        return getWritableDatabase().rawQuery(" SELECT * FROM MEDICINE_TABLE", (String[]) null);
    }

    public Cursor getFoodDetails(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(" SELECT * FROM MEDICINE_TABLE WHERE ID = " + id, (String[]) null);
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("ID=");
        sb.append(id);
        return db.delete(TABLE_NAME, sb.toString(), (String[]) null) > 0;
    }
}
