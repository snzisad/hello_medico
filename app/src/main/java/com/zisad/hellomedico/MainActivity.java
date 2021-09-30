package com.zisad.hellomedico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_main);
        ((Button) findViewById(C0512R.C0514id.badmin)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        ((Button) findViewById(C0512R.C0514id.buser)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, secondactivity.class));
            }
        });
    }
}
