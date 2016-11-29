package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote.RemoteHeroDetailDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HeroDetailInfoActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,
        View.OnClickListener {

    @BindView(R.id.viewPageLoop)
    ViewPager            mLoopViewPager;
    @BindView(R.id.loopIndicator)
    LinearLayout         mLoopIndicator;
    @BindView(R.id.horizontalIndicator)
    HorizontalScrollView mHorizontalScrollView;
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
    private ViewPagerLoopAdapter  mViewPagerLoopAdapter;
    private int                   scrollDx;
    private SkillIntroduceAdapter mSkillIntroduceAdapter;
    private Reco1Adapter          mReco1Adapter;
    private Reco1Adapter          mReco2Adapter;

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
        ArrayList<String> mDataList = new ArrayList<>();
        List<String>      strings   = Arrays.asList(getResources().getStringArray(R.array.skin_test_big));
        setSkinViewPagerIndicator();
        mDataList.addAll(strings);
        mViewPagerLoopAdapter.setmDataList(mDataList);

        //skill intro
        mSkillIntroduceAdapter = new SkillIntroduceAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        skillRCV.setLayoutManager(layoutManager);
        skillRCV.setAdapter(mSkillIntroduceAdapter);
        //recommend 1
        mReco1Adapter = new Reco1Adapter();
        recommendRCV1.setLayoutManager(layoutManager);
        recommendRCV1.setAdapter(mReco1Adapter);

        //recommend 2
        mReco2Adapter = new Reco1Adapter();
        recommendRCV2.setLayoutManager(layoutManager);
        recommendRCV2.setAdapter(mReco2Adapter);
        scrollDx = getScrollDx();
        setmViewPageLoop();


    }

    private void loadData() {
    }

    private void setSkinViewPagerIndicator() {
        String[] indicatorStrings = getResources().getStringArray(R.array.skin_test_small);
        int      w                = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int      padding          = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        for (int i = 0; i < indicatorStrings.length; i++) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            simpleDraweeView.setAspectRatio(1);
            simpleDraweeView.setTag(i);
            simpleDraweeView.setPadding(padding, padding, padding, padding);
            simpleDraweeView.setOnClickListener(this);
            simpleDraweeView.setBackgroundResource(R.drawable.selector_indicator);
            mLoopIndicator.addView(simpleDraweeView, new LinearLayout.LayoutParams(w, w));
            simpleDraweeView.setImageURI(indicatorStrings[i]);
        }
    }

    @NonNull
    private int getScrollDx() {
        String[] indicatorStrings = getResources().getStringArray(R.array.skin_test_small);
        int      w                = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int      screenWidth      = getResources().getDisplayMetrics().widthPixels;
        return (w * indicatorStrings.length - screenWidth) / indicatorStrings.length;
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
        mLoopIndicator.getChildAt(position).setSelected(true);
        if (position == 0) {
            mLoopIndicator.getChildAt(mLoopIndicator.getChildCount() - 1).setSelected(false);
        } else {
            mLoopIndicator.getChildAt(position - 1).setSelected(false);

        }
        if (position < mViewPagerLoopAdapter.getCount() && position > 0) {
            mHorizontalScrollView.smoothScrollBy(scrollDx, 0);
        } else if (position == 0) {
            mHorizontalScrollView.smoothScrollTo(0, 0);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //stub
    }

    @Override
    public void onClick(View v) {
        if (v instanceof SimpleDraweeView) {
            int pos = (int) v.getTag();
            mLoopViewPager.setCurrentItem(pos, true);
        }
    }
}
