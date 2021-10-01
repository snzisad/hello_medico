package com.zisad.hellomedico;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewMedicineActivity extends AppCompatActivity {
    Cursor cursor;
    MedicineDB medicineDB;
    TextView tvDiseasesName;
    TextView tvFood;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_view_medicine);
        super.setTitle("Medicibe Details");
        this.tvDiseasesName = (TextView) findViewById(R.id.tvDiseasesName);
        this.tvFood = (TextView) findViewById(R.id.tvFood);
        this.medicineDB = new MedicineDB(this);
        this.cursor = this.medicineDB.getFoodDetails(DataContainer.f46ID);
        if (this.cursor.moveToFirst()) {
            super.setTitle(this.cursor.getString(1));
            this.tvDiseasesName.setText(this.cursor.getString(1));
            this.tvFood.setText(this.cursor.getString(2));
        }
    }
}
