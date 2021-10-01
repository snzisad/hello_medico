package com.zisad.hellomedico;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout layoutBtnFavourite;
    LinearLayout layoutBtnHome;
    LinearLayout layoutBtnMiniPrescription;
    LinearLayout layoutBtnNearbyPharmacy;
    LinearLayout layoutBtnSearchDiseases;
    LinearLayout layoutBtnSearchMedicine;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home);
        super.setTitle("Hello Medico");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        init();
        ButtonClickListener();
    }

    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        this.layoutBtnSearchDiseases = (LinearLayout) findViewById(R.id.layoutBtnSearchDiseases);
        this.layoutBtnSearchMedicine = (LinearLayout) findViewById(R.id.layoutBtnSearchMedicine);
        this.layoutBtnMiniPrescription = (LinearLayout) findViewById(R.id.layoutBtnMiniPrescription);
        this.layoutBtnNearbyPharmacy = (LinearLayout) findViewById(R.id.layoutBtnNearbyPharmacy);
        this.layoutBtnHome = (LinearLayout) findViewById(R.id.layoutBtnHome);
        this.layoutBtnFavourite = (LinearLayout) findViewById(R.id.layoutBtnFavourite);
    }

    private void ButtonClickListener() {
        this.layoutBtnSearchDiseases.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, ListdataActivity.class));
                DataContainer.admin = false;
            }
        });
        this.layoutBtnSearchMedicine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, MedicineListActivity.class));
                DataContainer.admin = false;
            }
        });
        this.layoutBtnMiniPrescription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, FoodHabitActivity.class));
                DataContainer.admin = false;
            }
        });
        this.layoutBtnNearbyPharmacy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, NearbyPharmacyActivity.class));
            }
        });
        this.layoutBtnFavourite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, FavouriteActivity.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.admin) {
            startActivity(new Intent(this, Login.class));
            return true;
        } else if (id == R.id.about) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id != R.id.feedback && id == R.id.rate) {
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(Gravity.START);
        return true;
    }
}
