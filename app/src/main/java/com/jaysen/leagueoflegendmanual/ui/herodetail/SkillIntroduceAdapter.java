package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
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
        String tooltip = getItemData(position).getTooltip();
        String quick   = UnicodeTransform.unicode2String(getItemData(position).getName()) + getQuickShotcut(position);
        if (tooltip == null) {
            //passive
            holder.skillIntroTv.setText(UnicodeTransform.unicode2String(getItemData(position).getDescription()));
        } else {
            //Q W E R skill
            holder.skillIntroTv.setText(Html.fromHtml(UnicodeTransform.unicode2String(tooltip)));
        }
        holder.skillIntroTitleTv.setText(quick);
        holder.skillItemImg.setImageURI(String.format(URLAddress.HERO_SKILL_ImageDl_URL, getItemData(position).getImageName()));
    }

    String getQuickShotcut(int position) {
        switch (position) {
            case 0:
                return "  被动技能";
            case 1:
                return "  快捷键：Q";
            case 2:
                return "  快捷键：W";
            case 3:
                return "  快捷键：E";
            case 4:
                return "  快捷键：R";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mSpellsList.size();
    }
}
