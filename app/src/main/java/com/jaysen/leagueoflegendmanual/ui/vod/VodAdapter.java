package com.jaysen.leagueoflegendmanual.ui.vod;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 */

public class VodAdapter extends AbsRCVAdapter<VodAdapter.VH, VodEntity> {
    @Override
    public VodAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vod_layout,
                                                                     parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        getCastViewHolder(holder);
    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }
    }
}
