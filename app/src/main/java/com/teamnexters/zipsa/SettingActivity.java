package com.teamnexters.zipsa;

import android.graphics.Typeface;
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

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.top_bar_left_image) ImageView backButton;
    @BindView(R.id.top_bar_text_title) TextView title;
    @BindView(R.id.top_bar_right_text) TextView storeSettingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        viewSetting();
    }

    void viewSetting() {
        ButterKnife.bind(this);

        loadBackButton(this, backButton);

        storeSettingButton.setText("저장");
        title.setText("설정");

        backButton.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        storeSettingButton.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.top_bar_left_image)
    void backButtonClicked() {
        super.onBackPressed();
    }

    @OnClick(R.id.top_bar_right_text)
    void storeButtonClicked() {
        /* */
    }
}