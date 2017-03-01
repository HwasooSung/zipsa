package com.teamnexters.zipsa.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by Hwasoo.Sung on 2017-03-01.
 */

public class AddressGiver {
    private Geocoder geocoder;
    private List<Address> addresses;
    private String address;
    private Context context;

    public AddressGiver(Context context, Location location) {
        this.context = context;
        geocoder = new Geocoder(this.context, Locale.KOREA);
        setAddress(location);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(Location location) {
        try {
            if (geocoder != null) {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses != null && addresses.size() != 0) {
                    address = addresses.get(0).getAddressLine(0).toString().substring(addresses.get(0).getCountryName().toString().length() + 1);
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
