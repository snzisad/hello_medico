package com.zisad.hellomedico;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMedicineActivity extends AppCompatActivity {
    private Button btnadd;
    /* access modifiers changed from: private */
    public EditText editText1;
    /* access modifiers changed from: private */
    public EditText editText2;
    MedicineDB medicineDB;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_add_medicine);
        super.setTitle("Add Medicine");
        this.editText1 = (EditText) findViewById(C0512R.C0514id.f58dn);
        this.editText2 = (EditText) findViewById(C0512R.C0514id.f59ds);
        this.btnadd = (Button) findViewById(C0512R.C0514id.btnadd);
        this.medicineDB = new MedicineDB(this);
        this.btnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = AddMedicineActivity.this.editText1.getText().toString();
                String symp = AddMedicineActivity.this.editText2.getText().toString();
                if (AddMedicineActivity.this.checkBlankEditText(name, symp)) {
                    AddMedicineActivity.this.AddData(name, symp);
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

    public void AddData(String name, String symp) {
        if (this.medicineDB.addData(name, symp)) {
            toastMessage("Successfully Added");
            this.editText1.setText((CharSequence) null);
            this.editText2.setText((CharSequence) null);
            return;
        }
        toastMessage(" Something Went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
