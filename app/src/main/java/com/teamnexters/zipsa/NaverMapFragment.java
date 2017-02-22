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
import android.widget.Toast;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

/*
 * NAVER 지도 API
 * https://developers.naver.com/docs/map/android/
 */

public class NaverMapFragment extends Fragment {

    private boolean isNetworkEnabled;
    private boolean isGPSEnabled;
    private String providerInfo;
    private boolean isLocationTrackingEnabled;

    private NMapContext mMapContext;
    private NMapController nMapController;
    private NMapView mapView;

    private NGeoPoint currentNGeoPoint;
    private NMapOverlayItem nMapOverlayItem;
    private NMapViewerResourceProvider nMapViewerResourceProvider;
    private NMapOverlayManager nMapOverlayManager;
    private NMapPOIdata poiData;
    private NMapPOIdataOverlay nMapPOIdataOverlay;
    int markerId = NMapPOIflagType.PIN;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location currentLocation;

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

    // 현재 위치 찾고 네이버 지도 현재 위치로 포커스
    private void initMapView() {
        // 퍼미션 등록
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE}, ConstantsCommon.TAG_CODE_PERMISSION_LOCATION);
        } else {}

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLocation = location;
                currentNGeoPoint = new NGeoPoint(currentLocation.getLongitude(), currentLocation.getLatitude());
                nMapController.animateTo(currentNGeoPoint);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onProviderDisabled(String provider) {}
        };

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSEnabled) {
            isLocationTrackingEnabled = true;
            providerInfo = LocationManager.GPS_PROVIDER;
        }
        else if(isNetworkEnabled) {
            isLocationTrackingEnabled = true;
            providerInfo = LocationManager.NETWORK_PROVIDER;
        }
        else {
            isLocationTrackingEnabled = false;
        }

        if(!providerInfo.isEmpty()) {
            locationManager.requestLocationUpdates(providerInfo, ConstantsCommon.MIN_TIME_INTERVAL_FOR_UPDATE, ConstantsCommon.MIN_DISTANCE_CHANGE_FOR_UPDATE, locationListener);
            if(locationManager != null) {
                currentLocation = locationManager.getLastKnownLocation(providerInfo);
            }
        }

        //
        mapView = (NMapView) getView().findViewById(R.id.map_view);

        /* interacting with users */
        mapView.setClientId(ConstantsCommon.NAVER_CLIENT_ID);
        mapView.setClickable(true);
        mapView.setEnabled(true);
        mapView.setFocusable(true);
        mapView.setFocusableInTouchMode(true);
        mapView.requestFocus();

        mMapContext.setupMapView(mapView);

        // 마커 찍는 부분
        currentNGeoPoint = new NGeoPoint(currentLocation.getLongitude(), currentLocation.getLatitude());
        nMapViewerResourceProvider = new NMapViewerResourceProvider(getContext());
        nMapOverlayManager = new NMapOverlayManager(getContext(), mapView, nMapViewerResourceProvider);

        // set POI(Point of Interest) data
        poiData = new NMapPOIdata(1, nMapViewerResourceProvider);
        poiData.beginPOIdata(1);
        poiData.addPOIitem(currentNGeoPoint.getLongitude(), currentNGeoPoint.getLatitude(), "is this title?", markerId, 0);
        poiData.endPOIdata();

        // create POI data overlay
        nMapPOIdataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData, null);

        // set event listener to the overlay
//        NMapPOIdataOverlay.OnStateChangeListener onPOIDataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
//
//            @Override
//            public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem item) {
//                if(item != null) {
//                    Log.i("NMap Listener TAG", "onFocusChanged: " + item.toString());
//                } else {
//                    Log.i("Nmap Listener TAG", "onFocusChanged: ");
//                }
//            }
//            @Override
//            public  void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
//            }
//        };
//        nMapPOIdataOverlay.setOnStateChangeListener(onPOIDataStateChangeListener);

        // register callout overlay listener to customize it.
//        nMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {
//            @Override
////          public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
////              return null;
////          }
//        };
//        nMapOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);

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
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ;
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET);
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE);

        locationManager.removeUpdates(locationListener);
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
