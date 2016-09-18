package com.mvp.library.base;

/**
 * 创建人：
 * 创建时间： 2016/8/9 10
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public interface IBasePresenter {

    void onCreate();  //初始化数据

    void onDestroy(); //释放资源

}
