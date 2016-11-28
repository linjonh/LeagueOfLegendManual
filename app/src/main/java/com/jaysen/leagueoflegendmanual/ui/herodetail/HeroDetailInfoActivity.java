package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.jaysen.leagueoflegendmanual.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HeroDetailInfoActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPageLoop)
    ViewPager            mLoopViewPager;
    @BindView(R.id.loopIndicator)
    LinearLayout         mLoopIndicator;
    @BindView(R.id.attackTv)
    TextView             mAttackTv;
    @BindView(R.id.attackProgress)
    ProgressBar          mAttackProgress;
    @BindView(R.id.defenseTv)
    TextView             mDefenseTv;
    @BindView(R.id.defenseProgress)
    ProgressBar          mDefenseProgress;
    @BindView(R.id.magicTv)
    TextView             magicTv;
    @BindView(R.id.magicProgress)
    ProgressBar          magicProgress;
    @BindView(R.id.difficultyTv)
    TextView             mDifficultyTv;
    @BindView(R.id.difficultyProgress)
    ProgressBar          mDifficultyProgress;
    @BindView(R.id.allytipTitleTv)
    TextView             mAllytipTitleTv;
    @BindView(R.id.allytipTv)
    TextView             mAllytipTv;
    @BindView(R.id.enemytipTitleTv)
    TextView             mEnemytipTitleTv;
    @BindView(R.id.enemytipTv)
    TextView             mEnemytipTv;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.skillRCV)
    RecyclerView         skillRCV;
    @BindView(R.id.recommendRCV1)
    RecyclerView         recommendRCV1;
    @BindView(R.id.recommendRCV2)
    RecyclerView         recommendRCV2;
    @BindView(R.id.loadingViewSwitcher)
    ViewSwitcher         loadingViewSwitcher;
    private ViewPagerLoopAdapter mViewPagerLoopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
        mViewPagerLoopAdapter = new ViewPagerLoopAdapter();
        mLoopViewPager.setAdapter(mViewPagerLoopAdapter);
        mLoopViewPager.addOnPageChangeListener(this);
        setmViewPageLoop();
    }


    private void setmViewPageLoop() {
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mLoopViewPager.setCurrentItem((int) (aLong % mViewPagerLoopAdapter.getCount()), true);
                    }
                });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //stub
    }

    @Override
    public void onPageSelected(int position) {
        // TODO: 2016/11/28 select small avatar indicator
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //stub
    }
}
