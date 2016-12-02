package com.jaysen.leagueoflegendmanual.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.ui.HeroInfos.HeroListFragment;
import com.jaysen.leagueoflegendmanual.ui.equipment.EquipmentFragment;
import com.jaysen.leagueoflegendmanual.ui.vod.VodFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements HeroListFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.mainViewPager)
    ViewPager  mainViewPager;
    @BindView(R.id.navBottomRG)
    RadioGroup navBottomRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getActionBar() != null) {
            getActionBar().setTitle("英雄联盟手册");
        }
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("英雄联盟手册");
        }
        MainViewPagerAdapter mAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mAdapter);
        mainViewPager.addOnPageChangeListener(this);
        mainViewPager.setOffscreenPageLimit(4);
        navBottomRG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //stub
    }

    @Override
    public void onPageSelected(int position) {
        navBottomRG.getChildAt(position).performClick();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //stub
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.id_equipment:
                mainViewPager.setCurrentItem(0);
                break;
            case R.id.id_hero:
                mainViewPager.setCurrentItem(1);

                break;
            case R.id.id_summoner_skill:
                mainViewPager.setCurrentItem(2);

                break;
            case R.id.id_vod:
                mainViewPager.setCurrentItem(3);

                break;
        }
    }

    /**
     * Created by jaysen.lin@foxmail.com on 2016/12/2.
     */

    public static class MainViewPagerAdapter extends FragmentPagerAdapter {

        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return EquipmentFragment.newInstance("", "");
                case 1:
                    return HeroListFragment.newInstance("", "");
                case 2:
                    return EquipmentFragment.newInstance("", "");
                case 3:
                    return new VodFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
