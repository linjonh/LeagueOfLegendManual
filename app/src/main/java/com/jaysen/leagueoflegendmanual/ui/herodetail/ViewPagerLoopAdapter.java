package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */

public class ViewPagerLoopAdapter extends PagerAdapter implements View.OnClickListener {
    public void setmDataList(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    private List<String> mDataList = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_skin_layout, container, false);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }

    public String getItemData(int pos) {
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
