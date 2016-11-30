package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Skins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */

public class ViewPagerLoopAdapter extends PagerAdapter implements View.OnClickListener {
    public void setmDataList(List<Skins> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    private List<Skins> mDataList = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View             view             = LayoutInflater.from(container.getContext()).inflate(R.layout.item_skin_layout, container, false);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.skinBigDraweeView);
        simpleDraweeView.setImageURI(String.format(URLAddress.SKIN_BIG_ImageDl_URL, getItemData(position).getSkinId()));
        container.addView(view);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public Skins getItemData(int pos) {
        if (mDataList.isEmpty() || getCount() < pos) {
            return null;
        }
        return mDataList.get(pos);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void onClick(View v) {
        // TODO: 2016/11/28 save skin images
    }
}
