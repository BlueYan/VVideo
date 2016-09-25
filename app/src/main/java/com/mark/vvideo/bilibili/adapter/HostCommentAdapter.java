package com.mark.vvideo.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.bilibili.model.entry.Comment;
import com.mark.vvideo.util.DividerItemDecoration;
import com.mvp.library.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/25.
 * Function:
 */

public class HostCommentAdapter extends CommonAdapter<Comment.HotListBean> {

    private static final String TAG = HostCommentAdapter.class.getSimpleName();

    private static int[] lv = new int[] {R.drawable.ic_lv0, R.drawable.ic_lv1, R.drawable.ic_lv2,
            R.drawable.ic_lv3, R.drawable.ic_lv4, R.drawable.ic_lv5, R.drawable.ic_lv6,
            R.drawable.ic_lv7, R.drawable.ic_lv8, R.drawable.ic_lv9};

    public HostCommentAdapter(Context context, List<Comment.HotListBean> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bili_comment;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, Comment.HotListBean item) {
        mHolder.setImageUrlToSimpleDraweeView(R.id.id_simpleDraweeView, item.getFace())
                .setText(R.id.id_tv_name, item.getNick())
                .setText(R.id.id_tv_float,"#" + item.getLv())
                .setText(R.id.id_tv_time,
                        TimeUtils.getDateDiff(
                                item.getCreate_at(), new SimpleDateFormat("yyyy-MM-dd HH:mm")))
                .setText(R.id.id_tv_reply_num, item.getReply_count()+"")
                .setText(R.id.id_tv_support_num, item.getGood()+"")
                .setImageResource(R.id.id_iv_level, lv[item.getLevel_info().getCurrent_level()])
                .setText(R.id.id_tv_msg, item.getMsg());
    }
}
