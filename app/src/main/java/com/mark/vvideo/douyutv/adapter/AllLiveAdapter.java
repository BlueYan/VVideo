package com.mark.vvideo.douyutv.adapter;

import android.content.Context;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.view.AllLiveFragment;
import com.mark.vvideo.util.Utils;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLiveAdapter extends CommonAdapter<AllLive.Data> {

    private static final String TAG = AllLiveAdapter.class.getSimpleName();

    public AllLiveAdapter(Context context, List<AllLive.Data> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_douyu_alllive;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, AllLive.Data item) {
        mHolder.setImageUrlToSimpleDraweeView(R.id.id_room_img, item.getRoom_src())
                .setText(R.id.id_tv_room_name, item.getRoom_name())
                .setText(R.id.id_tv_nickname, item.getNickname())
                .setText(R.id.id_tv_category, item.getGame_name())
                .setText(R.id.id_tv_online_num, Utils.parseNumber(item.getOnline()) + "人在观看");
    }

}
