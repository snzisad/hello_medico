package com.zisad.hellomedico;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DiseasesInfo extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    String favourite = "0";
    Menu menu;
    TextView tvDiseasesName;
    TextView tvMedicine;
    TextView tvSymptom;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_diseases_info);
        this.tvDiseasesName = (TextView) findViewById(R.id.tvDiseasesName);
        this.tvSymptom = (TextView) findViewById(R.id.tvSymptom);
        this.tvMedicine = (TextView) findViewById(R.id.tvMedicine);
        this.databaseHelper = new DatabaseHelper(this);
        Cursor cursor = this.databaseHelper.getDiseasesDetails(DataContainer.f46ID);
        if (cursor.moveToFirst()) {
            super.setTitle(cursor.getString(1));
            this.tvDiseasesName.setText(cursor.getString(1));
            this.tvSymptom.setText(cursor.getString(2));
            this.tvMedicine.setText(cursor.getString(3));
            this.favourite = cursor.getString(4);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.diseases_menu, menu2);
        this.menu = menu2;
        changeFavouriteIcon();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.favourite) {
            return super.onOptionsItemSelected(item);
        }
        changeFavouriteStatus();
        return true;
    }

    public void changeFavouriteStatus() {
        if (this.favourite.equals("0")) {
            if (this.databaseHelper.makeFavourite(Integer.toString(DataContainer.f46ID))) {
                this.favourite = "1";
                changeFavouriteIcon();
                Toast.makeText(this, "Added to favourite", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
        } else if (this.databaseHelper.removeFavourite(Integer.toString(DataContainer.f46ID))) {
            this.favourite = "0";
            changeFavouriteIcon();
            Toast.makeText(this, "Removed from favourite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeFavouriteIcon() {
        if (this.favourite.equals("0")) {
            this.menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_nonfavorite));
        } else {
            this.menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite));
        }
    }
}
