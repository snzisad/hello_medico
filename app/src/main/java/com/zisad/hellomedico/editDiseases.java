package com.zisad.hellomedico;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editDiseases extends AppCompatActivity {
    private Button btnUpdate;
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
        setContentView((int) R.layout.activity_edit_diseases);
        super.setTitle("Update Diseases");
        this.editText1 = (EditText) findViewById(R.id.dn);
        this.editText2 = (EditText) findViewById(R.id.ds);
        this.editText3 = (EditText) findViewById(R.id.dm);
        this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
        this.mDatabaseHelper = new DatabaseHelper(this);
        getData();
        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = editDiseases.this.editText1.getText().toString();
                String symp = editDiseases.this.editText2.getText().toString();
                String medicine = editDiseases.this.editText3.getText().toString();
                if (editDiseases.this.checkBlankEditText(name, symp, medicine)) {
                    editDiseases.this.UpdateData(name, symp, medicine);
                    editDiseases.this.finish();
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

    private void getData() {
        Cursor cursor = this.mDatabaseHelper.getDiseasesDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.editText1.setText(cursor.getString(1));
            this.editText2.setText(cursor.getString(2));
            this.editText3.setText(cursor.getString(3));
        }
    }

    public void UpdateData(String name, String symp, String medicine) {
        if (this.mDatabaseHelper.updateData(Integer.toString(DataContainer.f46ID), name, symp, medicine)) {
            toastMessage("Successfully Updated");
            startActivity(new Intent(this, ListdataActivity.class));
            finish();
            return;
        }
        toastMessage(" Something Went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
