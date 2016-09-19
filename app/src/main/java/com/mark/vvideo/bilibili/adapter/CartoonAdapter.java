package com.mark.vvideo.bilibili.adapter;

import android.content.Context;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.bilibili.model.entry.AllRankModel;
import com.mark.vvideo.util.Utils;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/19.
 * Function:
 */
public class CartoonAdapter extends CommonAdapter<AllRankModel.VideosBean> {

    private static final String TAG = CartoonAdapter.class.getSimpleName();

    public CartoonAdapter(Context context, List<AllRankModel.VideosBean> list) {
        super(context, list);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cartoon_rank;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, AllRankModel.VideosBean item) {
        RoundingParams mParams = RoundingParams.fromCornersRadii(5f, 5f, 0f, 0f);
        ((SimpleDraweeView)mHolder.findView(R.id.id_dv)).getHierarchy().setRoundingParams(mParams);
        mHolder.setImageUrlToSimpleDraweeView(R.id.id_dv, item.getPic())
                .setText(R.id.id_tv_title, item.getTitle())
                .setText(R.id.id_tv_play_num, Utils.parseNumber(item.getPlay()))
                .setText(R.id.id_tv_fav_num, Utils.parseNumber(item.getFavorites()));
    }
}
