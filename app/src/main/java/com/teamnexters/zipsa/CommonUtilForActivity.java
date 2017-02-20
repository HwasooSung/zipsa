package com.teamnexters.zipsa;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Hwasoo.Sung on 2017-02-14.
 */

public class CommonUtilForActivity {

    public static void loadImage(Context context, int drawableId, ImageView view) {
        Glide.with(context).load(drawableId).into(view);
    }
    public static void loadBackButton(Context context, ImageView view) {
        Glide.with(context).load(R.drawable.back).into(view);
    }
}
