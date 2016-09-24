package com.mark.vvideo.util;

import com.mvp.library.utils.EncryptUtils;
import com.mvp.library.utils.LogUtils;

import java.net.URL;

/**
 * Author: Mark.
 * Date: 2016/9/19.
 * Function: 公用工具类
 */
public class Utils {

    private static final String URL_PARAM = "?aid=android&client_sys=android&time=";

    private static final String URL_PARAM1 = "?aid=android&clientsys=android&time=";

    public static String parseNumber(int num) {
        if ( num / 1000 >= 1 ) {
            int num1 = num / 10000;
            int num2 = num % 10000 / 1000;
            StringBuilder mBuilder = new StringBuilder();
            mBuilder.append(num1)
                    .append(".")
                    .append(num2)
                    .append("万");
            return mBuilder.toString();
        }
        return String.valueOf(num);
    }

    public static String getMD5String(String time, String str) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("room/")
                .append(str)
                .append(URL_PARAM1)
                .append(time)
                .append("1231");
        LogUtils.d("str = " + mBuilder.toString());
        String md5Result = EncryptUtils.getMD5(mBuilder.toString());
        LogUtils.d("result = " + md5Result);
        return md5Result.toLowerCase();
    }

    public static String getUrl(String str) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String mBaseUrl = "http://capi.douyucdn.cn/api/v1/";
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append(mBaseUrl)
                .append("room/")
                .append(str)
                .append(URL_PARAM)
                .append(time)
                .append("&auth=")
                .append(getMD5String(time, str));
        LogUtils.d("url = " + mBuilder.toString());
        return mBuilder.toString();
    }

}
