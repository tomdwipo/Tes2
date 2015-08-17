package com.androbos.tes.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tommy on 18/08/15.
 */
public class Route {
    @SerializedName("legs")
    private List<Legs> legsList;

    public List<Legs> getLegsList() {
        return legsList;
    }

    public void setLegsList(List<Legs> legsList) {
        this.legsList = legsList;
    }
}