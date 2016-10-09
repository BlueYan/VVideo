package com.mark.vvideo.util;

import com.mvp.library.utils.EncryptUtils;
import com.mvp.library.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Author: Mark.
 * Date: 2016/9/19.
 * Function: 公用工具类
 */
public class Utils {

    private static final String URL_PARAM = "?aid=android&client_sys=android&ne=1&support_pwd=1&time=";

    private static final String URL_PARAM1 = "?aid=android&client_sys=android&time=";

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

    public static byte[] decompressXML(byte[] data) throws DataFormatException
    {

        byte[] dest = new byte[data.length + 2];
        System.arraycopy(data, 0, dest, 2, data.length);
        dest[0] = 0x78;
        dest[1] = 0x01;
        data = dest;
        Inflater decompresser = new Inflater();
        decompresser.setInput(data);

        byte[] bufferArray = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        try
        {
            int i = 1;
            while (i != 0)
            {
                i = decompresser.inflate(bufferArray);
                baos.write(bufferArray, 0, i);
            }
            data = baos.toByteArray();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                baos.flush();
                baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        decompresser.end();
        return data;
    }

}
