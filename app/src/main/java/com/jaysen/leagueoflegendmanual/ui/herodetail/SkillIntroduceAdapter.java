package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Spells;
import com.jaysen.leagueoflegendmanual.util.UnicodeTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 */

public class SkillIntroduceAdapter extends RecyclerView.Adapter<SkillViewHolder> {
    public void setmSpellsList(List<Spells> mSpellsList) {
        this.mSpellsList = mSpellsList;
        notifyDataSetChanged();
    }

    private List<Spells> mSpellsList = new ArrayList<>();

    @Override
    public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill_intro_layout, parent, false);
        return new SkillViewHolder(view);
    }

    public Spells getItemData(int pos) {
        if (mSpellsList.isEmpty() || pos >= mSpellsList.size()) {
            return null;
        }
        return mSpellsList.get(pos);
    }

    @Override
    public void onBindViewHolder(SkillViewHolder holder, int position) {
        holder.skillIntroTv.setText(UnicodeTransform.unicode2String(getItemData(position).getDescription()));
        holder.skillItemImg.setImageURI(getItemData(position).getImageName());
    }

    @Override
    public int getItemCount() {
        return mSpellsList.size();
    }
}
