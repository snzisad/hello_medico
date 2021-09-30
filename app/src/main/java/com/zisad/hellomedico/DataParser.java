package com.zisad.hellomedico;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser {
    private HashMap<String, String> getPlace(JSONObject googlePlaceJSON) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        try {
            if (!googlePlaceJSON.isNull("name")) {
                placeName = googlePlaceJSON.getString("name");
            }
            if (!googlePlaceJSON.isNull("vicinity")) {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
            String latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject(FirebaseAnalytics.Param.LOCATION).getString("lat");
            String longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject(FirebaseAnalytics.Param.LOCATION).getString("lng");
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", googlePlaceJSON.getString("reference"));
        } catch (Exception e) {
        }
        return googlePlaceMap;
    }

    private HashMap<String, String> getDistanceDuration(JSONArray googleDirectionJSON) {
        HashMap<String, String> googleDirectionMap = new HashMap<>();
        try {
            googleDirectionMap.put("duration", googleDirectionJSON.getJSONObject(0).getJSONObject("duration").getString("text"));
            googleDirectionMap.put("distance", googleDirectionJSON.getJSONObject(0).getJSONObject("distance").getString("text"));
        } catch (Exception e) {
        }
        return googleDirectionMap;
    }

    private List<HashMap<String, String>> getAllPlaces(JSONArray jsonArray) {
        int count = jsonArray.length();
        List<HashMap<String, String>> placeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try {
                placeList.add(getPlace((JSONObject) jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placeList;
    }

    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONObject(jsonData).getJSONArray("results");
        } catch (Exception e) {
        }
        return getAllPlaces(jsonArray);
    }

    public HashMap<String, String> parseDirection(String jsonData) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONObject(jsonData).getJSONArray("routes").getJSONObject(0).getJSONArray("legs");
        } catch (Exception e) {
        }
        return getDistanceDuration(jsonArray);
    }

    public String[] parseRoute(String jsonData) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONObject(jsonData).getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        } catch (Exception e) {
        }
        return getPaths(jsonArray);
    }

    public String[] getPaths(JSONArray googleStepsJSON) {
        int count = googleStepsJSON.length();
        String[] polylines = new String[count];
        for (int i = 0; i < count; i++) {
            try {
                polylines[i] = getPath(googleStepsJSON.getJSONObject(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return polylines;
    }

    public String getPath(JSONObject googlePathJSON) {
        try {
            return googlePathJSON.getJSONObject("polyline").getString("points");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
