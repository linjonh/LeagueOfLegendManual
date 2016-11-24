package com.jaysen.leagueoflegendmanual.ui.HeroInfos;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/22.
 */

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.HeroViewHolder> implements
        View.OnClickListener {


    public void setmHeroEntities(List<HeroEntity> mHeroEntities) {
        this.mHeroEntities = mHeroEntities;
        notifyDataSetChanged();
    }

    private List<HeroEntity> mHeroEntities = new ArrayList<>();

    @Inject
    HeroListAdapter() {

    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero_layout, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, int position) {
        holder.title.setText(getItem(position).getLegendTitle() + " " + getItem(position).getLegendName());
        holder.tag.setText(getItem(position).getTags());
//        holder.description.setText(getItem(position).getDescription());
        Preconditions.checkNotNull(holder.iconAvatar);
        holder.iconAvatar.setImageURI(getItem(position).getAvatarUrl());
        holder.itemView.setTag(getItem(position));
        holder.itemView.setOnClickListener(this);
    }

    private HeroEntity getItem(int position) {
        return mHeroEntities.get(position);
    }

    @Override
    public int getItemCount() {
        return mHeroEntities.size();
    }

    private long startClickTime = 0;

    @Override
    public void onClick(View view) {
        long tmp = System.currentTimeMillis();
        if (tmp - startClickTime > 500) {//item点击间隔大于500毫秒在响应事件
            startClickTime = tmp;
            if (mListener != null) {
                mListener.onItemClick((HeroEntity) view.getTag());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    private OnItemClickListener mListener;

    interface OnItemClickListener {
        void onItemClick(HeroEntity itemData);
    }

    static class HeroViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.icon_avatar)
        SimpleDraweeView iconAvatar;
        @BindView(R.id.title)
        TextView         title;
        @BindView(R.id.tag)
        TextView         tag;
   /*     @BindView(R.id.description)
        TextView         description;*/

        public HeroViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
