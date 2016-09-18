package com.mvp.library.utils;

import com.orhanobut.logger.Logger;

/**
 * 创建人：
 * 创建时间： 2016/8/9 16
 * 功能概述: 配置Log，只有在标志位是true的时候才可以输出。
 * 修改人：
 * 修改时间：
 */
public class LogUtils {

    private static boolean flag = false;

    private static final String TAG = LogUtils.class.getSimpleName();

    public static void init(boolean log) {
        flag = log;
        Logger.init(TAG).hideThreadInfo();
    }

    /**
     * 打印信息
     * @param str
     */
    public static void d(String str) {
        if ( flag ) {
            Logger.d(str);
        }
    }

    /**
     * 打印json格式
     * @param json
     */
    public static void json(String json) {
        if ( flag ) {
            Logger.json(json);
        }
    }

    /**
     * 打印信息
     * @param e
     */
    public static void e(String e) {
        if ( flag ) {
            Logger.e(e);
        }
    }

}
