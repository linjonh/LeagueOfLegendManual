package com.jaysen.leagueoflegendmanual.ui.vod;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 */

public class VodAdapter extends AbsRCVAdapter<VodAdapter.VodHolder, VodEntity> {


    @Override
    public VodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vod_layout, parent, false);
        return new VodHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VodHolder vodHolder = getCastViewHolder(holder);
        VodEntity itemData  = getItemData(position);
        vodHolder.heroItemLinkTv.setText(itemData.getHeroName() + " " + itemData.getHeroTitle());
        vodHolder.itemView.setOnClickListener(this);
        vodHolder.itemView.setTag(itemData);
    }

    public class VodHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hero_item_link_tv)
        TextView heroItemLinkTv;

        public VodHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
