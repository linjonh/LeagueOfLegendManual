package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */

public class ViewPagerLoopAdapter extends PagerAdapter {
    private List<String> mDataList = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }
}
