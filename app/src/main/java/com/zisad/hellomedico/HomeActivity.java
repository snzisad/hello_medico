package com.zisad.hellomedico;


import android.content.Intent;
import android.os.Bundle;
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
        setContentView((int) C0512R.layout.activity_home);
        super.setTitle("Hello Medico");
        Toolbar toolbar = (Toolbar) findViewById(C0512R.C0514id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(C0512R.C0514id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, C0512R.string.navigation_drawer_open, C0512R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(C0512R.C0514id.nav_view)).setNavigationItemSelectedListener(this);
        init();
        ButtonClickListener();
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(C0512R.C0514id.drawer_layout);
        if (drawer.isDrawerOpen(8388611)) {
            drawer.closeDrawer(8388611);
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        this.layoutBtnSearchDiseases = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnSearchDiseases);
        this.layoutBtnSearchMedicine = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnSearchMedicine);
        this.layoutBtnMiniPrescription = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnMiniPrescription);
        this.layoutBtnNearbyPharmacy = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnNearbyPharmacy);
        this.layoutBtnHome = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnHome);
        this.layoutBtnFavourite = (LinearLayout) findViewById(C0512R.C0514id.layoutBtnFavourite);
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
        getMenuInflater().inflate(C0512R.C0515menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == C0512R.C0514id.admin) {
            startActivity(new Intent(this, Login.class));
            return true;
        } else if (id == C0512R.C0514id.about) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id != C0512R.C0514id.feedback && id == C0512R.C0514id.rate) {
        }
        ((DrawerLayout) findViewById(C0512R.C0514id.drawer_layout)).closeDrawer(8388611);
        return true;
    }
}
