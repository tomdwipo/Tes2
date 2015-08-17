package com.androbos.tes.model;

import io.realm.RealmObject;

/**
 * Created by tommy on 18/08/15.
 */
public class LatLngModel extends RealmObject {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
