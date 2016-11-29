package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaysen.leagueoflegendmanual.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 */
public class Reco1ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.equipmentTitle)
    TextView     equipmentTitle;
    @BindView(R.id.equipmentIconLayout)
    LinearLayout equipmentIconLayout;

    public Reco1ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
