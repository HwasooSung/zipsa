package com.teamnexters.zipsa.util;

/**
 * Created by Hwasoo.Sung on 2017-02-15.
 */

public class ConstantsCommon {
    public static String NAVER_CLIENT_ID = "3iUfYSGYlzT9nevsm6cV";
    public static String NAVER_CLIENT_SECRET = "USfKjEgCaj";

    public static int PERMISSION_REQUEST_FOR_LOCATION = 1;

    // millisecond
    public static long MIN_TIME_INTERVAL_FOR_UPDATE = 1000*60*1;
    // meter
    public static float MIN_DISTANCE_CHANGE_FOR_UPDATE = 10;
    // TAG of log for debugging
    public static String TAG = "ZIPSA_TAG";

    public static String NAVER_API_ADDRESS = "https://openapi.naver.com/v1/map/reversegeocode?encoding=utf-8&coordType=latlng&query=";
}
