package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        String[] ids = getItemData(position).getEquipmentIds().split("\\$");
        int      w   = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, holder.itemView.getContext().getResources().getDisplayMetrics());
        int      mL  = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, holder.itemView.getContext().getResources().getDisplayMetrics());

        for (String id : ids) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(holder.itemView.getContext());
            String           imageURI         = String.format(URLAddress.EQUIPMENT_ImageDl_URL, id);
            simpleDraweeView.setImageURI(imageURI);
            simpleDraweeView.setAspectRatio(1f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, w);
            params.setMargins(mL, 0, 0, 0);
            holder.equipmentIconLayout.addView(simpleDraweeView, params);
        }
        holder.equipmentTitle.setText(getItemData(position).type);
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }
}
