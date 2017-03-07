package com.teamnexters.zipsa.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnexters.zipsa.R;
import com.teamnexters.zipsa.fragment.NaverMapFragment;
import com.teamnexters.zipsa.util.ConstantsCommon;
import com.teamnexters.zipsa.util.HttpCommuicator;
import com.teamnexters.zipsa.util.OnDataSentListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.teamnexters.zipsa.util.CommonUtilForActivity.loadBackButton;
import static com.teamnexters.zipsa.util.CommonUtilForActivity.loadImage;

public class CurrentMapActivity extends AppCompatActivity implements OnDataSentListener {

    // Naver 지도 프래그먼트 관련 변수
    private NaverMapFragment naverMapFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private String currentPointAddress;

    private OnDataSentListener onDataSentListener;

    @BindView(R.id.current_map_search_edit_text) EditText searchWithAddressEditText;
    @BindView(R.id.top_bar_left_image) ImageView backButton;
    @BindView(R.id.top_bar_text_title) TextView title;
    @BindView(R.id.current_map_activity_address_text_view) TextView addressTextView;
    @BindView(R.id.current_map_activity_search_button) ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_map);
        viewSetting();

        //map setting
        naverMapFragment = new NaverMapFragment();
        naverMapFragment.setArguments(new Bundle());

        // 프래그먼트에 주소 검색 후 주소의 좌표를 보내는 객체
        onDataSentListener = naverMapFragment;

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.naver_map_fragment_here, naverMapFragment);
        fragmentTransaction.commit();
    }

    public void setAddressData(String address) {
        this.currentPointAddress = address;
    }

    void viewSetting() {
        ButterKnife.bind(this);
        loadBackButton(this, backButton);
        loadImage(this, R.drawable.search, searchButton);

        backButton.setVisibility(View.VISIBLE);
        title.setText("내 위치 확인");
        title.setVisibility(View.VISIBLE);
        searchWithAddressEditText.setHint("주소로 검색");
    }

    @OnClick(R.id.top_bar_left_image)
    void backButtonClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.current_map_activity_search_button)
    void searchButtonClicked()  {
        String searchAddress = searchWithAddressEditText.getText().toString();
        String encodedAddress = null;
        try {
            encodedAddress = URLEncoder.encode(searchAddress, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(ConstantsCommon.TAG, "UnsupportedEncodingException in searchButtonClicked");
        }

        HttpCommuicator httpCommuicator = new HttpCommuicator();
        httpCommuicator.sendRequestTo(ConstantsCommon.NAVER_API_ADDRESS + encodedAddress);
//        onDataSentListener.onDataSent(searchAddress);
    }

    @Override
    public void onDataSent(Object object) {
        currentPointAddress = (String) object;
        addressTextView.setText(currentPointAddress);
    }
}
