package com.mark.vvideo.bilibili.adapter;

import android.content.Context;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.bilibili.model.entry.Comment;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function:
 */

public class CommentAdapter extends CommonAdapter<Comment> {

    public CommentAdapter(Context context, List<Comment> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bili_comment;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, Comment item) {

    }
}
