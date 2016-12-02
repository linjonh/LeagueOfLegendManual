package com.jaysen.leagueoflegendmanual.ui.equipment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.EquipmentEntity;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 */

public class EquipmentAdapter extends
                              AbsRCVAdapter<EquipmentAdapter.EquipmentViewHolder, EquipmentEntity> {

    @Override
    public EquipmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipment_layout, parent, false);
        return new EquipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EquipmentEntity itemData = getItemData(position);
        Preconditions.checkNotNull(itemData);
        EquipmentViewHolder viewHolder = getCastViewHolder(holder);
        viewHolder.equipmentIconIv.setImageURI(
                String.format(URLAddress.EQUIPMENT_ImageDl_NO_EXTENTION_URL, itemData.getImage()));

        viewHolder.itemView.setOnClickListener(this);
        viewHolder.equipmentTitleTv.setText(itemData.getName());
    }


    public static class EquipmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipmentIconIv)
        SimpleDraweeView equipmentIconIv;
        @BindView(R.id.equipmentTitleTv)
        TextView         equipmentTitleTv;

        public EquipmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
