package com.jaysen.leagueoflegendmanual.ui.summoner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 2016/12/2.
 */

public class SummonerAdapter extends AbsRCVAdapter<SummonerAdapter.VH, SummonerSkillEntity> {

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summoner_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH                  viewHolder = getCastViewHolder(holder);
        SummonerSkillEntity itemData   = getItemData(position);
        viewHolder.summonerIconIv.setImageURI(
                String.format(URLAddress.SUMMONER_ID_ImageDl_URL,
                        itemData.getImage()));
        viewHolder.summonerTitleTv.setText(itemData.getName());
        viewHolder.itemView.setOnClickListener(this);
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.summonerIconIv)
        SimpleDraweeView summonerIconIv;
        @BindView(R.id.summonerTitleTv)
        TextView         summonerTitleTv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
