package com.zisad.hellomedico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class NearbyPharmacyActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final int LOCATION_REQUEST = 1000;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 1000;
    private GoogleApiClient client;
    private Marker currentLocaiton;
    Object[] dataTransfer;
    private GoogleMap gMap;
    Location lastLocation;
    double latitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private LocationRequest locationRequest;
    double longitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    GetNearbyPlaces places;
    ProgressDialog progressDialog;
    String type;
    String url;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0512R.layout.activity_nearby_pharmacy);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Searching nearby Pharmacy");
        this.progressDialog.show();
        this.dataTransfer = new Object[2];
        this.places = new GetNearbyPlaces();
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(C0512R.C0514id.map)).getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            buildGoogleApiCliend();
            this.gMap.setMyLocationEnabled(true);
            return;
        }
        checkLocationPermission();
    }

    public void searchPharmacy() {
        if (this.latitude == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || this.longitude == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            Toast.makeText(this, "Device location cannot be granted", Toast.LENGTH_SHORT).show();
        } else {
            this.type = "pharmacy";
            this.url = getUrl(this.type);
            this.dataTransfer[0] = this.gMap;
            this.dataTransfer[1] = this.url;
            new GetNearbyPlaces().execute(this.dataTransfer);
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        }
        this.progressDialog.cancel();
    }

    private String getUrl(String type2) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + this.latitude + "," + this.longitude);
        StringBuilder sb = new StringBuilder();
        sb.append("&radius=");
        sb.append(this.PROXIMITY_RADIUS);
        googlePlaceUrl.append(sb.toString());
        googlePlaceUrl.append("&type=" + type2);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + getResources().getString(C0512R.string.google_maps_key));
        return googlePlaceUrl.toString();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            buildGoogleApiCliend();
            this.gMap.setMyLocationEnabled(true);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
            new AlertDialog.Builder(this).setTitle((CharSequence) "Location Permission Needed").setMessage((CharSequence) "This app needs the Location permission, please accept to use location functionality").setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(NearbyPharmacyActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1000);
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1000);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private synchronized void buildGoogleApiCliend() {
        this.client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        this.client.connect();
    }

    @SuppressLint({"RestrictedApi"})
    public void onConnected(@Nullable Bundle bundle) {
        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(1000);
        this.locationRequest.setFastestInterval(1000);
        this.locationRequest.setPriority(102);
        if (!(ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            checkLocationPermission();
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(this.client, this.locationRequest, (com.google.android.gms.location.LocationListener) this);
    }

    public void onConnectionSuspended(int i) {
    }

    public void onLocationChanged(Location location) {
        this.lastLocation = location;
        if (this.currentLocaiton != null) {
            this.currentLocaiton.remove();
        }
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        LatLng latLng = new LatLng(this.latitude, this.longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Locaion");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(330.0f));
        this.currentLocaiton = this.gMap.addMarker(markerOptions);
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        this.gMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
        if (this.client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.client, (com.google.android.gms.location.LocationListener) this);
        }
        searchPharmacy();
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
