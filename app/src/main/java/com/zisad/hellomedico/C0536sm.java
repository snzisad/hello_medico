package com.zisad.hellomedico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class C0536sm extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_sm);
        ((Button) findViewById(C0512R.C0514id.button8)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent int1 = new Intent(C0536sm.this, Symp.class);
                int1.putExtra("ID", 5);
                C0536sm.this.startActivity(int1);
            }
        });
        ((Button) findViewById(C0512R.C0514id.button9)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent int1 = new Intent(C0536sm.this, medi.class);
                int1.putExtra("ID", 5);
                C0536sm.this.startActivity(int1);
            }
        });
    }
}
