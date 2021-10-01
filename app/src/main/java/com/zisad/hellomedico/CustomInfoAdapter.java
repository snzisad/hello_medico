package com.zisad.hellomedico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private View view;

    public CustomInfoAdapter(Context context2) {
        this.context = context2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_custom_marker_info, (ViewGroup) null);
    }

    public View getInfoWindow(Marker marker) {
        getData(marker);
        return this.view;
    }

    public View getInfoContents(Marker marker) {
        getData(marker);
        return this.view;
    }

    private void getData(Marker marker) {
        TextView textView = (TextView) this.view.findViewById(R.id.tvPharmacyPhone);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tvcall);
        ((TextView) this.view.findViewById(R.id.tvPharmacyName)).setText(marker.getTitle());
        ((TextView) this.view.findViewById(R.id.tvPharmacyLocation)).setText(marker.getSnippet());
    }
}
