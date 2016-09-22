package com.mark.vvideo.douyutv.adapter;

import android.content.Context;

import com.mark.vvideo.R;
import com.mark.vvideo.adapter.CommonAdapter;
import com.mark.vvideo.adapter.SuperViewHolder;
import com.mark.vvideo.douyutv.model.entry.AllLive;
import com.mark.vvideo.douyutv.view.AllLiveFragment;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLiveAdapter extends CommonAdapter<AllLive> {

    private static final String TAG = AllLiveAdapter.class.getSimpleName();

    public AllLiveAdapter(Context context, List<AllLive> list) {
        super(context, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_douyu_alllive;
    }

    @Override
    public void onBindData(SuperViewHolder mHolder, int position, AllLive item) {

    }
}
