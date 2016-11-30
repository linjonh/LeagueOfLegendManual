package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 */
public class SkillViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.skill_item_img)
    SimpleDraweeView skillItemImg;
    @BindView(R.id.skillIntroTv)
    TextView         skillIntroTv;
    @BindView(R.id.skillIntroTitleTv)
    TextView         skillIntroTitleTv;

    public SkillViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
