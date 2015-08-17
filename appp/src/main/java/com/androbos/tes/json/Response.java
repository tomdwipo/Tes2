package com.androbos.tes.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tommy on 18/08/15.
 */
public class Response {
    @SerializedName("routes")
    private List<Route> routeList;

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
