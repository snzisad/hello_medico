package com.zisad.hellomedico;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Symp extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    /* renamed from: tv */
    TextView f72tv;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_symp);
        this.f72tv = (TextView) findViewById(C0512R.C0514id.textView3);
        this.databaseHelper = new DatabaseHelper(this);
        Cursor cursor = this.databaseHelper.getDiseasesDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.f72tv.setText(cursor.getString(2));
        }
    }
}
