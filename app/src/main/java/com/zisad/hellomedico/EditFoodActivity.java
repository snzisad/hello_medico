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

public class EditFoodActivity extends AppCompatActivity {
    private Button btnUpdate;
    /* access modifiers changed from: private */
    public EditText editText1;
    /* access modifiers changed from: private */
    public EditText editText2;
    FoodDatabase foodDatabase;
    DatabaseHelper mDatabaseHelper;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_edit_food);
        super.setTitle("Update Food");
        this.editText1 = (EditText) findViewById(R.id.dn);
        this.editText2 = (EditText) findViewById(R.id.ds);
        this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
        this.mDatabaseHelper = new DatabaseHelper(this);
        this.foodDatabase = new FoodDatabase(this);
        getData();
        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = EditFoodActivity.this.editText1.getText().toString();
                String symp = EditFoodActivity.this.editText2.getText().toString();
                if (EditFoodActivity.this.checkBlankEditText(name, symp)) {
                    EditFoodActivity.this.UpdateData(name, symp);
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
        this.editText2.setError("Enter Food");
        return false;
    }

    private void getData() {
        Cursor cursor = this.foodDatabase.getFoodDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.editText1.setText(cursor.getString(1));
            this.editText2.setText(cursor.getString(2));
        }
    }

    public void UpdateData(String name, String symp) {
        if (this.foodDatabase.updateData(Integer.toString(DataContainer.f46ID), name, symp)) {
            toastMessage("Successfully Updated");
            startActivity(new Intent(this, FoodHabitActivity.class));
            finish();
            return;
        }
        toastMessage(" Something Went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
