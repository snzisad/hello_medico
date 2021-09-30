package com.zisad.hellomedico;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add extends AppCompatActivity {
    private static final String TAG = "add";
    private Button btnadd;
    /* access modifiers changed from: private */
    public EditText editText1;
    /* access modifiers changed from: private */
    public EditText editText2;
    /* access modifiers changed from: private */
    public EditText editText3;
    DatabaseHelper mDatabaseHelper;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_add);
        super.setTitle("Add Medicine");
        this.editText1 = (EditText) findViewById(C0512R.C0514id.f58dn);
        this.editText2 = (EditText) findViewById(C0512R.C0514id.f59ds);
        this.editText3 = (EditText) findViewById(C0512R.C0514id.f57dm);
        this.btnadd = (Button) findViewById(C0512R.C0514id.btnadd);
        this.mDatabaseHelper = new DatabaseHelper(this);
        this.btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = add.this.editText1.getText().toString();
                String symp = add.this.editText2.getText().toString();
                String medicine = add.this.editText3.getText().toString();
                if (add.this.checkBlankEditText(name, symp, medicine)) {
                    add.this.AddData(name, symp, medicine);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean checkBlankEditText(String name, String symp, String medicine) {
        boolean result = true;
        if (TextUtils.isEmpty(name)) {
            result = false;
            this.editText1.setError("Enter Name");
        }
        if (TextUtils.isEmpty(symp)) {
            result = false;
            this.editText2.setError("Enter Symptomp");
        }
        if (!TextUtils.isEmpty(medicine)) {
            return result;
        }
        this.editText3.setError("Enter Medicine");
        return false;
    }

    public void AddData(String name, String symp, String medicine) {
        if (this.mDatabaseHelper.addData(name, symp, medicine)) {
            toastMessage("Successfully Added");
            this.editText1.setText((CharSequence) null);
            this.editText2.setText((CharSequence) null);
            this.editText3.setText((CharSequence) null);
            return;
        }
        toastMessage(" Something Went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
