package com.mark.vvideo.util;

/**
 * Author: Mark.
 * Date: 2016/9/19.
 * Function: 公用工具类
 */
public class Utils {

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

}
