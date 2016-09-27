package com.mark.vvideo.bilibili.adapter;

import android.content.Context;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.bilibili.model.entry.Tag;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/27.
 * Function:
 */

public class TagAdapter extends CommonAdapter<Tag> {

    public TagAdapter(Context context, List<Tag> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_tag;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, Tag item) {
        mHolder.setText(R.id.id_tv_tag, item.getTag());
    }
}
