package com.teamnexters.zipsa;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.teamnexters.zipsa.CommonUtilForActivity.loadBackButton;
import static com.teamnexters.zipsa.CommonUtilForActivity.loadImage;

public class CurrentMapActivity extends AppCompatActivity {

    // Naver 지도 프래그먼트 관련 변수
    NaverMapFragment naverMapFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.top_bar_left_image) ImageView backButton;
    @BindView(R.id.top_bar_text_title) TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_map);
        viewSetting();

        //map setting
        naverMapFragment = new NaverMapFragment();
        naverMapFragment.setArguments(new Bundle());

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.naver_map_fragment_here, naverMapFragment);
        fragmentTransaction.commit();
    }

    void viewSetting() {
        ButterKnife.bind(this);
        loadBackButton(this, backButton);
        title.setText("내 위치 확인");

        backButton.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.top_bar_left_image)
    void backButtonClicked() {
        super.onBackPressed();
    }

}
