package com.zisad.hellomedico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SYMP";
    public static final String COL4 = "MEDICINE";
    public static final String COL5 = "FAVOURITE";
    public static final String DATABASE = "DISEASES.DB";
    public static final String TABLE_NAME = "DISEASES_TABLE";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DISEASES_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SYMP TEXT, MEDICINE TEXT, FAVOURITE TEXT ) ");
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE IF EXISTS DISEASES_TABLE");
        onCreate(db);
    }

    public boolean addData(String name, String symp, String medicine) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put(COL3, symp);
        contentValues.put(COL4, medicine);
        contentValues.put(COL5, "0");
        if (db.insert(TABLE_NAME, (String) null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public boolean updateData(String id, String name, String symp, String medicine) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put(COL3, symp);
        contentValues.put(COL4, medicine);
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

    public boolean makeFavourite(String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, "1");
        try {
            if (((long) db.update(TABLE_NAME, contentValues, "ID = " + id, (String[]) null)) == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Zisad", e.toString());
            return false;
        }
    }

    public boolean removeFavourite(String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, "0");
        try {
            if (((long) db.update(TABLE_NAME, contentValues, "ID = " + id, (String[]) null)) == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Zisad", e.toString());
            return false;
        }
    }

    public Cursor getData() {
        return getWritableDatabase().rawQuery(" SELECT * FROM DISEASES_TABLE", (String[]) null);
    }

    public Cursor favouriteData() {
        return getWritableDatabase().rawQuery(" SELECT * FROM DISEASES_TABLE WHERE FAVOURITE = 1 ", (String[]) null);
    }

    public Cursor getDiseasesDetails(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(" SELECT * FROM DISEASES_TABLE WHERE ID = " + id, (String[]) null);
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("ID=");
        sb.append(id);
        return db.delete(TABLE_NAME, sb.toString(), (String[]) null) > 0;
    }
}
