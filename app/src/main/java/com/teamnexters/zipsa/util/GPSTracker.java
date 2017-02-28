package com.teamnexters.zipsa.util;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


/**
 * Created by Hwasoo.Sung on 2017-02-24.
 */

public class GPSTracker extends Service implements LocationListener {

    private Context context;

    private LocationManager locationManager;

    private boolean isNetworkEnabled;
    private boolean isGPSEnabled;
    private String providerInfo;
    private boolean isLocationTrackingEnabled;

    private boolean isLocationChanged; //이건 다음에 이용해야겠군
    private Location currentLocation;

    public  GPSTracker() {

    }

    public GPSTracker(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setCurrentLocation();
    }

    private void setCurrentLocation() {

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled) {
            providerInfo = LocationManager.GPS_PROVIDER;
            isLocationTrackingEnabled = true;
        } else if (isNetworkEnabled) {
            providerInfo = LocationManager.NETWORK_PROVIDER;
            isLocationTrackingEnabled = true;
        } else {
            isLocationTrackingEnabled = false;
        }

        if (isLocationTrackingEnabled) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE}, ConstantsCommon.PERMISSION_REQUEST_FOR_LOCATION);
            }
        }

        if (!providerInfo.isEmpty()) {
            locationManager.requestLocationUpdates(providerInfo, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, this);
            if (locationManager != null) {
                currentLocation = locationManager.getLastKnownLocation(providerInfo);
//                Log.d(ConstantsCommon.TAG, "Check " + "currentLocation is " + currentLocation.toString());
            }
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean isLocationChanged() {
        return isLocationChanged;
    }

    public void setLocationChanged(boolean locationChanged) {
        isLocationChanged = locationChanged;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public void removeGPSTracker() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        isLocationChanged = true;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
