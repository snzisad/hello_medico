package com.zisad.hellomedico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class diseases extends AppCompatActivity {
    private static final String TAG = "diseases";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_diseases);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button6)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button7)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button10)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button11)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
        ((Button) findViewById(R.id.button12)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diseases.this.startActivity(new Intent(diseases.this, Symp.class));
            }
        });
    }
}
