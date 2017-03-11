package com.teamnexters.zipsa.model;

import android.location.Location;

/**
 * Created by Hwasoo.Sung on 2017-03-12.
 */

public class LocationInfo {
    private String address;
    private Location location;

    public LocationInfo() {

    }

    public LocationInfo(String address, Location location) {
        this.address = address;
        this.location = location;
    }


}
