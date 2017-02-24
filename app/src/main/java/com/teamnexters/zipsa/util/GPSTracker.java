package com.teamnexters.zipsa.util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Hwasoo.Sung on 2017-02-24.
 */

public class GPSTracker implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
