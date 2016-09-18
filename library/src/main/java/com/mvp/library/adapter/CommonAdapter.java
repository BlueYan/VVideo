package com.mvp.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建人：
 * 创建时间： 2016/8/22 11
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> {

    private Context mContext;

    private List<T> mData;

    private LayoutInflater mInflater;

    protected OnItemClickListener mListener; //点击事件

    protected OnItemLongClickListener mLongListener; //长按点击事件

    public CommonAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mData = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final SuperViewHolder mHolder = new
                SuperViewHolder(mContext,
                mInflater.inflate(getLayoutId(), parent, false));
        //点击方法的回调接口
        if (mListener != null) {
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickListener(mHolder.itemView,
                            mHolder.getLayoutPosition());
                }
            });
        }
        //长按方法的回调接口
        if (mLongListener != null) {
            mHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongListener.onLongClickListener(mHolder.itemView,
                            mHolder.getLayoutPosition());
                    return true;
                }
            });
        }
        return mHolder;
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        onBindData(holder, position, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 增加某一个item
     * @param position
     * @param item
     */
    public void addItem(int position, T item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 更新某一个
     * @param postion
     */
    public void updateItem(int postion) {
        notifyItemChanged(postion);
    }

    /**
     * 删除某一个item
     * @param position
     */
    public void deleteItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteItem(T item) {
        mData.remove(item);
        notifyDataSetChanged();
    }


    protected abstract int getLayoutId();

    //重写绑定数据的方法
    public abstract void onBindData(SuperViewHolder mHolder, int
            position, T item);

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener
                                                   listener) {
        this.mLongListener = listener;
    }

    public interface OnItemClickListener {
        public void onClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        public void onLongClickListener(View view, int position);
    }

}
