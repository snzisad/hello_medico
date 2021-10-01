package com.zisad.hellomedico;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class medi extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    /* renamed from: tv */
    TextView f73tv;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_medi);
        this.f73tv = (TextView) findViewById(R.id.textView2);
        this.databaseHelper = new DatabaseHelper(this);
        Cursor cursor = this.databaseHelper.getDiseasesDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.f73tv.setText(cursor.getString(3));
        }
    }
}
