package com.zisad.hellomedico;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class ListdataActivity extends AppCompatActivity {
    private static final String TAG = "ListdataActivity";
    DiseasesAdapter adapter;
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_list_data_activity);
        super.setTitle("Dieases List");
        this.mListView = (ListView) findViewById(R.id.it1);
        this.mDatabaseHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView:Displaying data in the ListView.");
        Cursor data = this.mDatabaseHelper.getData();
        ArrayList<String> name = new ArrayList<>();
        final ArrayList<Integer> ID = new ArrayList<>();
        while (data.moveToNext()) {
            name.add(data.getString(1));
            ID.add(Integer.valueOf(data.getInt(0)));
        }
        this.adapter = new DiseasesAdapter(this, name, ID);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataContainer.f46ID = ((Integer) ID.get(i)).intValue();
                ListdataActivity.this.startActivity(new Intent(ListdataActivity.this, DiseasesInfo.class));
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DataContainer.admin) {
                    DataContainer.f46ID = ((Integer) ID.get(i)).intValue();
                    ListdataActivity.this.showOptions();
                    return true;
                }
                Toast.makeText(ListdataActivity.this, "Only admin can perform this operation", 0).show();
                return true;
            }
        });
        this.mListView.setAdapter(this.adapter);
    }

    /* access modifiers changed from: private */
    public void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_options, (ViewGroup) null, false);
        ((LinearLayout) view.findViewById(R.id.layout_editButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListdataActivity.this.startActivity(new Intent(ListdataActivity.this, editDiseases.class));
                ListdataActivity.this.finish();
            }
        });
        ((LinearLayout) view.findViewById(R.id.layout_deleteButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ListdataActivity.this.mDatabaseHelper.deleteItem(DataContainer.f46ID)) {
                    Toast.makeText(ListdataActivity.this, "Successfully Deleted", 0).show();
                    ListdataActivity.this.startActivity(new Intent(ListdataActivity.this, ListdataActivity.class));
                    ListdataActivity.this.finish();
                    return;
                }
                Toast.makeText(ListdataActivity.this, "Sorry, try again", 0).show();
            }
        });
        builder.setView(view);
        builder.create();
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        ((SearchView) menu.findItem(R.id.search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String s) {
                ListdataActivity.this.adapter.getFilter().filter(s);
                return true;
            }

            public boolean onQueryTextChange(String s) {
                ListdataActivity.this.adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }
}
