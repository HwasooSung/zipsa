package com.teamnexters.zipsa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamnexters.zipsa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.teamnexters.zipsa.util.CommonUtilForActivity.loadImage;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.top_bar_left_text) TextView editHomeListButton;
    @BindView(R.id.top_bar_image_title) ImageView titleImage;
    @BindView(R.id.top_bar_right_image) ImageView optionSettingButton;
    @BindView(R.id.add_new_house_button) ImageView addNewHouseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewSetting();
    }

    void viewSetting() {
        ButterKnife.bind(this);

        loadImage(this, R.drawable.zipsa, titleImage);
        loadImage(this, R.drawable.setting, optionSettingButton);
        loadImage(this, R.drawable.add_new_memo, addNewHouseButton);

        editHomeListButton.setText("편집");
        editHomeListButton.setVisibility(View.VISIBLE);
        titleImage.setVisibility(View.VISIBLE);
        optionSettingButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.top_bar_left_text)
    void editHomeListButtonClicked() {

    }
    @OnClick(R.id.top_bar_right_image)
    void optionSettingButtonClicked() {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.add_new_house_button)
    void addNewHouseNuttonClicked() {
        Intent intent = new Intent(MainActivity.this, CurrentMapActivity.class);
        startActivity(intent);
    }

}
