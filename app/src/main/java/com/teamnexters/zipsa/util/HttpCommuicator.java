package com.teamnexters.zipsa.util;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONObject;

/**
 * Created by Hwasoo.Sung on 2017-03-07.
 */

public class HttpCommuicator {

    public HttpCommuicator() {}

    public void sendRequestTo(String url) {
        final String naverUrl = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = HttpRequest.get(naverUrl);
                httpRequest.header(ConstantsCommon.NAVER_API_REQUEST_PROPERTY_ID, ConstantsCommon.NAVER_CLIENT_ID);
                httpRequest.header(ConstantsCommon.NAVER_API_REQUEST_PROPERTY_SECRET, ConstantsCommon.NAVER_CLIENT_SECRET);
                boolean isSuccess = httpRequest.ok();
                int statusCode = httpRequest.code();

//                Log.d(ConstantsCommon.TAG, "isSuccess: " + isSuccess + "\nstatusCode is " + statusCode);
                String response = httpRequest.body().toString();
//                Log.d(ConstantsCommon.TAG, "response is " + response);
            }
        }).start();
    }

}
