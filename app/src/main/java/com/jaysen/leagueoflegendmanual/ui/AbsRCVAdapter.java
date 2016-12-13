package com.jaysen.leagueoflegendmanual.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 * abstract RecycleAdapter:1. abstract data set, 2.and click event 3.viewHolder
 */

public abstract class AbsRCVAdapter<VH extends RecyclerView.ViewHolder, DATA> extends
        RecyclerView.Adapter implements View.OnClickListener {

    private long       startClickTime = 0;
    private List<DATA> mDataSets      = new ArrayList<>();

    public void setmDataSets(List<DATA> mDataSets) {
        this.mDataSets = mDataSets;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSets.size();
    }

    /**
     *
     * @param pos position
     * @return
     */
    public DATA getItemData(int pos) {
        if (mDataSets.isEmpty() || pos >= mDataSets.size()) {
            return null;
        }
        return mDataSets.get(pos);
    }

    @Override
    public void onClick(View v) {
        long tmp = System.currentTimeMillis();
        if (tmp - startClickTime > 500) {//item点击间隔大于500毫秒在响应事件
            startClickTime = tmp;
            if (mListener != null) {
                mListener.onItemClick(v.getTag());
            }
        }
    }

    public VH getCastViewHolder(RecyclerView.ViewHolder holder) {
        return (VH) holder;
    }

    public void setOnItemClickListener(OnItemClickListener<Object> mListener) {
        this.mListener = mListener;
    }

    private OnItemClickListener<Object> mListener;

    public interface OnItemClickListener<DATA> {
        void onItemClick(DATA itemData);
    }

}
