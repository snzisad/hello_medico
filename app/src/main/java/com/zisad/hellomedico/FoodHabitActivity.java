package com.zisad.hellomedico;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodHabitActivity extends AppCompatActivity {
    FoodDatabase foodDatabase;
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0512R.layout.activity_food_habit);
        super.setTitle("Food Habit");
        this.mListView = (ListView) findViewById(C0512R.C0514id.it1);
        this.mDatabaseHelper = new DatabaseHelper(this);
        this.foodDatabase = new FoodDatabase(this);
        populateListView();
    }

    private void populateListView() {
        Cursor data = this.foodDatabase.getData();
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
                FoodHabitActivity.this.startActivity(new Intent(FoodHabitActivity.this, ViewFoodActivity.class));
            }
        });
        this.mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (DataContainer.admin) {
                    DataContainer.f46ID = ((Integer) ID.get(i)).intValue();
                    FoodHabitActivity.this.showOptions();
                    return true;
                }
                Toast.makeText(FoodHabitActivity.this, "Only admin can perform this operation", 0).show();
                return true;
            }
        });
        this.mListView.setAdapter(adapter);
    }

    /* access modifiers changed from: private */
    public void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(C0512R.layout.layout_options, (ViewGroup) null, false);
        ((LinearLayout) view.findViewById(C0512R.C0514id.layout_editButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FoodHabitActivity.this.startActivity(new Intent(FoodHabitActivity.this, EditFoodActivity.class));
                FoodHabitActivity.this.finish();
            }
        });
        ((LinearLayout) view.findViewById(C0512R.C0514id.layout_deleteButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FoodHabitActivity.this.foodDatabase.deleteItem(DataContainer.f46ID)) {
                    Toast.makeText(FoodHabitActivity.this, "Successfully Deleted", 0).show();
                    FoodHabitActivity.this.startActivity(new Intent(FoodHabitActivity.this, FoodHabitActivity.class));
                    FoodHabitActivity.this.finish();
                    return;
                }
                Toast.makeText(FoodHabitActivity.this, "Sorry, try again", 0).show();
            }
        });
        builder.setView(view);
        builder.create();
        builder.show();
    }
}
