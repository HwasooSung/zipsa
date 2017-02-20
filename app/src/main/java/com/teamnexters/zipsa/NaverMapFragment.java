package com.teamnexters.zipsa;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;


public class NaverMapFragment extends Fragment {

    private NMapContext mMapContext;
    private NMapController nMapController;
    private NMapView mapView;

    private NMapOverlayItem nMapOverlayItem;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location currentPoint;

    //    private OnFragmentInteractionListener mListener;

    public NaverMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mMapContext = new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_naver_map, container, false);
    }

    private void initMapView() {
        // 퍼미션 등록
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE}, ConstantsCommon.TAG_CODE_PERMISSION_LOCATION);
        } else {
        }

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentPoint = location;
                NGeoPoint currentNGeoPoint = new NGeoPoint(currentPoint.getLongitude(), currentPoint.getLatitude());
                nMapController.animateTo(currentNGeoPoint);
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
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, locationListener);

        mapView = (NMapView) getView().findViewById(R.id.map_view);

        Location lastLocation = null;
        Location gpsLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location wifiLastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(gpsLastLocation != null) {
            lastLocation = gpsLastLocation;
        }
        else if(wifiLastLocation != null) {
            lastLocation = wifiLastLocation;
        }

        /* interacting with users */
        mapView.setClientId(ConstantsCommon.NAVER_CLIENT_ID);
        mapView.setClickable(true);
        mapView.setEnabled(true);
        mapView.setFocusable(true);
        mapView.setFocusableInTouchMode(true);
        mapView.requestFocus();

        mMapContext.setupMapView(mapView);
        nMapController = mapView.getMapController();

        mapView.setScalingFactor(2.0f, false);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        initMapView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }

    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }
//    private void initLocation() {
//        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE}, ConstantsCommon.TAG_CODE_PERMISSION_LOCATION);
//
//        } else {
//        }
//
//        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.d("HI", location.getLatitude() + ", " + location.getLongitude());
//                currentPoint = location;
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//            }
//        };
//
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, locationListener);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, locationListener);
//    }

//
//
//
//
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
