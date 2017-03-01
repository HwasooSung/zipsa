package com.teamnexters.zipsa.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.teamnexters.zipsa.R;
import com.teamnexters.zipsa.navermap.NMapPOIflagType;
import com.teamnexters.zipsa.navermap.NMapViewerResourceProvider;
import com.teamnexters.zipsa.util.AddressGiver;
import com.teamnexters.zipsa.util.ConstantsCommon;
import com.teamnexters.zipsa.util.GPSTracker;

/*
 * NAVER 지도 API
 * https://developers.naver.com/docs/map/android/
 */

public class NaverMapFragment extends Fragment {

    private GPSTracker gpsTracker;
    private Location currentLocation;

    private NMapContext mMapContext;
    private NMapController nMapController;
    private NMapView mapView;

    private NGeoPoint currentNGeoPoint;
    private NMapViewerResourceProvider nMapViewerResourceProvider;
    private NMapOverlayManager nMapOverlayManager;
    private NMapPOIdata poiData;
    private NMapPOIdataOverlay nMapPOIdataOverlay;
    private int markerId = NMapPOIflagType.PIN;

    private AddressGiver addressGiver;
    private String currentLocationAddress;

    private OnAddressDataLoadedEventListener onAddressDataLoadedEventListener;

    public interface OnAddressDataLoadedEventListener {
        public void onAddressDataLoaded(String address);
    }

    public NaverMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // 얘가 Key임!!!!!!!!!!
            onAddressDataLoadedEventListener = (OnAddressDataLoadedEventListener) context;
        } catch (Exception e){
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mMapContext = new NMapContext(super.getActivity());
        mMapContext.onCreate();
        gpsTracker = new GPSTracker(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_naver_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        initMapView();
    }

    // 현재 위치 찾고 네이버 지도 현재 위치로 포커스
    private void initMapView() {
        // xml view 갖다 붙임
        mapView = (NMapView) getView().findViewById(R.id.map_view);

        // interacting with users
        mapView.setClientId(ConstantsCommon.NAVER_CLIENT_ID);
        mapView.setClickable(true);
        mapView.setEnabled(true);
        mapView.setFocusable(true);
        mapView.setFocusableInTouchMode(true);
        mapView.requestFocus();

        mMapContext.setupMapView(mapView); // 얘가 객체 만들어주나봄.. 얘 없으니 계속 null 익셉션 뜸
        nMapController = mapView.getMapController();

        // 현재 위치 불러오기
        currentLocation = gpsTracker.getCurrentLocation();
        currentNGeoPoint = new NGeoPoint(currentLocation.getLongitude(), currentLocation.getLatitude());

        // 그 위치로 지도 이동
        nMapController.animateTo(currentNGeoPoint);

        // 주소 따오기
        addressGiver = new AddressGiver(getContext(), currentLocation);
        currentLocationAddress = addressGiver.getAddress();

        // activity로 주소 data 보내기
        onAddressDataLoadedEventListener.onAddressDataLoaded(currentLocationAddress);

        // 마커 찍는 부분
        currentNGeoPoint = new NGeoPoint(currentLocation.getLongitude(), currentLocation.getLatitude());
        nMapViewerResourceProvider = new NMapViewerResourceProvider(getContext());
        nMapOverlayManager = new NMapOverlayManager(getContext(), mapView, nMapViewerResourceProvider);

        // set POI(Point of Interest) data
        poiData = new NMapPOIdata(1, nMapViewerResourceProvider);
        poiData.beginPOIdata(1);
        Log.d(ConstantsCommon.TAG, "usage of currentLocationAddress");
        poiData.addPOIitem(currentNGeoPoint.getLongitude(), currentNGeoPoint.getLatitude(), currentLocationAddress, markerId, 0);
        poiData.endPOIdata();

        // create POI data overlay
        nMapPOIdataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData, null);

        mapView.setScalingFactor(2.0f, false);
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
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET);
        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE);

        gpsTracker.removeGPSTracker();
        mMapContext.onDestroy();
        super.onDestroy();
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//

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
