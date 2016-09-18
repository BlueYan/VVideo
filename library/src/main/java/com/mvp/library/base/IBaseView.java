package com.mvp.library.base;

/**
 * 创建人：
 * 创建时间： 2016/8/9 10
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public interface IBaseView<T extends IBasePresenter> {

    /**
     * 设置P层
     * @param presenter
     */
    void setPresenter(T presenter);

}
