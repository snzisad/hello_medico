package com.zisad.hellomedico;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MedicineListActivity extends AppCompatActivity {
    DiseasesAdapter adapter;
    private ListView mListView;
    MedicineDB medicineDB;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_medicine_list);
        super.setTitle("Medicine List");
        this.mListView = (ListView) findViewById(C0512R.C0514id.it1);
        this.medicineDB = new MedicineDB(this);
        populateListView();
    }

    private void populateListView() {
        Cursor data = this.medicineDB.getData();
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
                MedicineListActivity.this.startActivity(new Intent(MedicineListActivity.this, ViewMedicineActivity.class));
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DataContainer.admin) {
                    DataContainer.f46ID = ((Integer) ID.get(i)).intValue();
                    MedicineListActivity.this.showOptions();
                    return true;
                }
                Toast.makeText(MedicineListActivity.this, "Only admin can perform this operation", 0).show();
                return true;
            }
        });
        this.mListView.setAdapter(this.adapter);
    }

    /* access modifiers changed from: private */
    public void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(C0512R.layout.layout_options, (ViewGroup) null, false);
        ((LinearLayout) view.findViewById(C0512R.C0514id.layout_editButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MedicineListActivity.this.startActivity(new Intent(MedicineListActivity.this, EditMedicineActivity.class));
                MedicineListActivity.this.finish();
            }
        });
        ((LinearLayout) view.findViewById(C0512R.C0514id.layout_deleteButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MedicineListActivity.this.medicineDB.deleteItem(DataContainer.f46ID)) {
                    Toast.makeText(MedicineListActivity.this, "Successfully Deleted", 0).show();
                    MedicineListActivity.this.startActivity(new Intent(MedicineListActivity.this, MedicineListActivity.class));
                    MedicineListActivity.this.finish();
                    return;
                }
                Toast.makeText(MedicineListActivity.this, "Sorry, try again", 0).show();
            }
        });
        builder.setView(view);
        builder.create();
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0512R.C0515menu.menu_search, menu);
        ((SearchView) menu.findItem(C0512R.C0514id.search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String s) {
                MedicineListActivity.this.adapter.getFilter().filter(s);
                return true;
            }

            public boolean onQueryTextChange(String s) {
                MedicineListActivity.this.adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }
}
