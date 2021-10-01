package com.zisad.hellomedico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class secondactivity extends AppCompatActivity {
    private Button btnvd;
    private EditText editText;
    DatabaseHelper mDatabaseHelper;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_secondactivity);
        super.setTitle("Admin Panel");
        this.btnvd = (Button) findViewById(R.id.vd);
        this.btnvd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, ListdataActivity.class));
            }
        });
        ((Button) findViewById(R.id.mp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, NearbyPharmacyActivity.class));
            }
        });
        ((Button) findViewById(R.id.ai)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, add.class));
            }
        });
        ((Button) findViewById(R.id.vf)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, FoodHabitActivity.class));
            }
        });
        ((Button) findViewById(R.id.af)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, AddFoodDetails.class));
            }
        });
        ((Button) findViewById(R.id.am)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, AddMedicineActivity.class));
            }
        });
        ((Button) findViewById(R.id.vm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, MedicineListActivity.class));
            }
        });
        ((Button) findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, HomeActivity.class));
                secondactivity.this.finish();
            }
        });
    }
}
