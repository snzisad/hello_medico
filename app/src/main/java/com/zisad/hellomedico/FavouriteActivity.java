package com.zisad.hellomedico;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_favourite);
        super.setTitle("Favourite");
        this.mListView = (ListView) findViewById(R.id.it1);
        this.mDatabaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Cursor data = this.mDatabaseHelper.favouriteData();
        ArrayList<String> name = new ArrayList<>();
        final ArrayList<Integer> ID = new ArrayList<>();
        while (data.moveToNext()) {
            name.add(data.getString(1));
            ID.add(Integer.valueOf(data.getInt(0)));
        }
        DiseasesAdapter adapter = new DiseasesAdapter(this, name, ID);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataContainer.f46ID = ((Integer) ID.get(i)).intValue();
                FavouriteActivity.this.startActivity(new Intent(FavouriteActivity.this, DiseasesInfo.class));
            }
        });
        this.mListView.setAdapter(adapter);
    }
}
