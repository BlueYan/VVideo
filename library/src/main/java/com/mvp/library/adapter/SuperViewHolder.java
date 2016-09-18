package com.mvp.library.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 创建人：
 * 创建时间： 2016/8/22 11
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public class SuperViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    private SparseArray<View> mViews;

    public SuperViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        mViews = new SparseArray<View>();
    }

    /**
     * 通过id获取view
     * @param id
     * @param <T>
     * @return
     */
    private  <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if ( view == null ) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T)view;
    }

    /**
     * 查找view
     * @param id
     * @return
     */
    public View findView(int id) {
        return getView(id);
    }

    /**
     * 设置TextView文本
     * @param viewId
     * @param text
     * @return
     */
    public SuperViewHolder setText(int viewId, String text) {
        TextView mTV = getView(viewId);
        mTV.setText(text);
        return this;
    }

    /**
     * 设置复选框文本
     * @param viewId
     * @param text
     * @return
     */
    public SuperViewHolder setCheckBoxText(int viewId, String text)
    {
        CheckBox cb = getView(viewId);
        cb.setText(text);
        return this;
    }

    /**
     * 设置资源图片
     * @param viewId
     * @param resId
     * @return
     */
    public SuperViewHolder setImageResource(int viewId, int resId) {
        ImageView mIV = getView(viewId);
        mIV.setImageResource(resId);
        return this;
    }

    /**
     * 设置Bitmap图片
     * @param viewId
     * @param bitmap
     * @return
     */
    public SuperViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView mIV = getView(viewId);
        mIV.setImageBitmap(bitmap);
        if ( bitmap != null && !bitmap.isRecycled() ) {
            bitmap.recycle();
        }
        return this;
    }

    /**
     * 为view设置事件监听器
     * @param viewId
     * @param listener 防高频点击事件
     * @return
     */
    public SuperViewHolder setViewClickListener(int viewId,
                                                View.OnClickListener listener) {
        View mView = getView(viewId);
        mView.setOnClickListener(listener);
        return this;
    }
}
