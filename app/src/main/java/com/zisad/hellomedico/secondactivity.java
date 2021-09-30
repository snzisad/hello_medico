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
        setContentView((int) C0512R.layout.activity_secondactivity);
        super.setTitle("Admin Panel");
        this.btnvd = (Button) findViewById(C0512R.C0514id.f62vd);
        this.btnvd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, ListdataActivity.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f60mp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, NearbyPharmacyActivity.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f55ai)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, add.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f63vf)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, FoodHabitActivity.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f54af)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, AddFoodDetails.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f56am)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, AddMedicineActivity.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.f64vm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, MedicineListActivity.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.logout)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                secondactivity.this.startActivity(new Intent(secondactivity.this, HomeActivity.class));
                secondactivity.this.finish();
            }
        });
    }
}
