package com.teamnexters.zipsa.util;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.security.Permission;

/**
 * Created by Hwasoo.Sung on 2017-02-24.
 */

public class GPSTracker implements LocationListener {

    final private Context context;

    private LocationManager locationManager;

    private PermissionsChecker permissionsChecker;

    private boolean isNetworkEnabled;
    private boolean isGPSEnabled;
    private String providerInfo;
    private boolean isLocationTrackingEnabled;

    private Location currentLocation;

    GPSTracker(Context context) {
        this.context = context;
        permissionsChecker = new PermissionsChecker(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public Location getCurrentLocation() {


        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSEnabled) {
            providerInfo = LocationManager.GPS_PROVIDER;
            isLocationTrackingEnabled = true;
        }
        else if(isNetworkEnabled) {
            providerInfo = LocationManager.NETWORK_PROVIDER;
            isLocationTrackingEnabled = true;
        }
        else {
            isLocationTrackingEnabled = false;
        }

        if(isLocationTrackingEnabled) {
            ///////////
            if(permissionsChecker.lacksPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})) {
                ////
            }
            locationManager.requestLocationUpdates(providerInfo, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, this);
        }
    }


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
