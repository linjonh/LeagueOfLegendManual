package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/30.
 */

public class LinearLyoutAtMostMrg extends LinearLayoutManager {
    private static final String TAG = LinearLyoutAtMostMrg.class.getSimpleName();

    public LinearLyoutAtMostMrg(Context context) {
        super(context);
    }

    public LinearLyoutAtMostMrg(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLyoutAtMostMrg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//        int h = View.MeasureSpec.getSize(heightSpec);
//        Log.d(TAG, "onMeasure: h=" + h);
//        Log.d(TAG, "getItemCount: " + getItemCount());
//        Log.d(TAG, "getChildCount: " + getChildCount());
//        heightSpec = View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.AT_MOST);
//        int hzise = View.MeasureSpec.getSize(heightSpec);
//        int wzise = View.MeasureSpec.getSize(widthSpec);
//        setMeasuredDimension(wzise, hzise * getItemCount());
//        Log.d(TAG, "setMeasuredDimension: wzise=" + wzise + "hzise * getItemCount() =" + hzise * getItemCount());
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
