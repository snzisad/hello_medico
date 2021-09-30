package com.zisad.hellomedico;

import android.os.AsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object, String, String> {
    GoogleMap gMap;
    String googlePlacesData;
    String url;

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objects) {
        this.gMap = (GoogleMap) objects[0];
        this.url = (String) objects[1];
        try {
            this.googlePlacesData = new DownloadUrl().readUrl(this.url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.googlePlacesData;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String s) {
        showNearbyPlaces(new DataParser().parse(s));
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList) {
        for (int i = 0; i < nearbyPlaceList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);
            LatLng latLng = new LatLng(Double.parseDouble(googlePlace.get("lat")), Double.parseDouble(googlePlace.get("lng")));
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(240.0f));
            markerOptions.title(googlePlace.get("place_name"));
            markerOptions.snippet(googlePlace.get("vicinity"));
            this.gMap.addMarker(markerOptions);
            this.gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            this.gMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        }
    }
}
