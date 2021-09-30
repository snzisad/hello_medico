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

public class EditMedicineActivity extends AppCompatActivity {
    private Button btnUpdate;
    /* access modifiers changed from: private */
    public EditText editText1;
    /* access modifiers changed from: private */
    public EditText editText2;
    MedicineDB medicineDB;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_edit_medicine);
        super.setTitle("Update Medicine");
        this.editText1 = (EditText) findViewById(C0512R.C0514id.f58dn);
        this.editText2 = (EditText) findViewById(C0512R.C0514id.f59ds);
        this.btnUpdate = (Button) findViewById(C0512R.C0514id.btnUpdate);
        this.medicineDB = new MedicineDB(this);
        getData();
        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = EditMedicineActivity.this.editText1.getText().toString();
                String symp = EditMedicineActivity.this.editText2.getText().toString();
                if (EditMedicineActivity.this.checkBlankEditText(name, symp)) {
                    EditMedicineActivity.this.UpdateData(name, symp);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean checkBlankEditText(String name, String symp) {
        boolean result = true;
        if (TextUtils.isEmpty(name)) {
            result = false;
            this.editText1.setError("Enter Name");
        }
        if (!TextUtils.isEmpty(symp)) {
            return result;
        }
        this.editText2.setError("Enter Diseases");
        return false;
    }

    private void getData() {
        Cursor cursor = this.medicineDB.getFoodDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.editText1.setText(cursor.getString(1));
            this.editText2.setText(cursor.getString(2));
        }
    }

    public void UpdateData(String name, String symp) {
        if (this.medicineDB.updateData(Integer.toString(DataContainer.f46ID), name, symp)) {
            toastMessage("Successfully Updated");
            startActivity(new Intent(this, MedicineListActivity.class));
            finish();
            return;
        }
        toastMessage(" Something Went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
