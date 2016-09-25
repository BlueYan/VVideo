package com.mark.vvideo.bilibili.adapter;

import android.content.Context;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mvp.library.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/25.
 * Function:
 */

public class ReplyCommentAdapter extends CommonAdapter<Comment.ListBean.ReplyBean> {

    public ReplyCommentAdapter(Context context, List<Comment.ListBean.ReplyBean> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bili_reply_comment;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, Comment.ListBean.ReplyBean item) {
            mHolder.setText(R.id.id_tv_name, item.getNick())
                    .setText(R.id.id_tv_msg, item.getMsg())
                    .setText(R.id.id_tv_time, TimeUtils.getDateDiff(
                            item.getCreate_at(), new SimpleDateFormat("yyyy-MM-dd HH:mm")));
    }
}
