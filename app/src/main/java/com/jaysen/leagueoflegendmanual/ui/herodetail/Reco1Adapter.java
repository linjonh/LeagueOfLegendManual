package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.EquipmentRecommend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 */

public class Reco1Adapter extends RecyclerView.Adapter<Reco1ViewHolder> {


    public void setRecommends(List<EquipmentRecommend> recommends) {
        this.recommends = recommends;
        notifyDataSetChanged();
    }

    private List<EquipmentRecommend> recommends = new ArrayList<>();

    @Override
    public Reco1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend1_layout, parent, false);
        return new Reco1ViewHolder(view);
    }

    public EquipmentRecommend getItemData(int pos) {
        if (recommends.isEmpty() || pos >= recommends.size()) {
            return null;
        }
        return recommends.get(pos);
    }

    @Override
    public void onBindViewHolder(Reco1ViewHolder holder, int position) {
        String[] ids = getItemData(position).getEquipmentIds().split(",");
        for (String id : ids) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(holder.itemView.getContext());
            simpleDraweeView.setImageURI(URLAddress.EQUIPMENT_ImageDl_URL + id + ".png");
            simpleDraweeView.setAspectRatio(1f);
            holder.equipmentIconLayout.addView(simpleDraweeView, new LinearLayoutCompat.LayoutParams(-2, -1));
        }
        holder.equipmentTitle.setText(getItemData(position).type);
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }
}
