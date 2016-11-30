package com.jaysen.leagueoflegendmanual.ui.herodetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.dagger.DataModule;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Allytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Enemytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Skins;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseHeroDetail;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;
import com.jaysen.leagueoflegendmanual.ui.APP;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HeroDetailInfoActivity extends AppCompatActivity implements
                                                              ViewPager.OnPageChangeListener,
                                                              View.OnClickListener,
                                                              Presenter.View<HeroDetailInfoEntity> {

    private static final String TAG = HeroDetailInfoActivity.class.getSimpleName();
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
    @BindView(R.id.storyTV)
    TextView             mStoryTv;
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

    @Inject
    HeroDetailPresenter mHeroDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            String title = getIntent().getStringExtra("title");
            getSupportActionBar().setTitle(title);
        }
        APP app = (APP) getApplication();
        app.getApplicationComponent().getDataSourceBuilder().dataModule(new DataModule()).build().inject(this);

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

        //skill intro
        mSkillIntroduceAdapter = new SkillIntroduceAdapter();
        LinearLyoutAtMostMrg layoutManager  = new LinearLyoutAtMostMrg(this, LinearLayoutManager.VERTICAL, false);
        LinearLyoutAtMostMrg layoutManager1 = new LinearLyoutAtMostMrg(this, LinearLayoutManager.VERTICAL, false);
        LinearLyoutAtMostMrg layoutManager2 = new LinearLyoutAtMostMrg(this, LinearLayoutManager.VERTICAL, false);

        skillRCV.setLayoutManager(layoutManager);
        skillRCV.setAdapter(mSkillIntroduceAdapter);
        //recommend 1
        mReco1Adapter = new Reco1Adapter();
        recommendRCV1.setLayoutManager(layoutManager1);
        recommendRCV1.setAdapter(mReco1Adapter);

        //recommend 2
        mReco2Adapter = new Reco1Adapter();
        recommendRCV2.setLayoutManager(layoutManager2);
        recommendRCV2.setAdapter(mReco2Adapter);
        scrollDx = getScrollDx();
        setmViewPageLoop();

        mHeroDetailPresenter.setMvpView(this);
        UseCaseHeroDetail.RequestParam mParam = new UseCaseHeroDetail.RequestParam();
        mParam.heroNameId = getIntent().getStringExtra("heroNameId") + ".json";
        mHeroDetailPresenter.setmParam(mParam);
        mHeroDetailPresenter.loadData();

    }


    private void setSkinViewPagerIndicator(List<Skins> indicators) {
        int w       = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        for (int i = 0; i < indicators.size(); i++) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
            simpleDraweeView.setAspectRatio(1);
            simpleDraweeView.setTag(i);
            simpleDraweeView.setPadding(padding, padding, padding, padding);
            simpleDraweeView.setOnClickListener(this);
            simpleDraweeView.setBackgroundResource(R.drawable.selector_indicator);
            mLoopIndicator.addView(simpleDraweeView, new LinearLayout.LayoutParams(w, w));
            simpleDraweeView.setImageURI(String.format(URLAddress.SKIN_SMALL_ImageDl_URL, indicators.get(i).getSkinId()));
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
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
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
        // 2016/11/28 select small avatar indicator
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

    @Override
    public void onLoadSuccess(HeroDetailInfoEntity data) {
        Log.d(TAG, "onLoadSuccess: ");
        Toast.makeText(this, "onLoadSuccess", Toast.LENGTH_SHORT).show();
        mViewPagerLoopAdapter.setmDataList(data.skinIdNames);
        setSkinViewPagerIndicator(data.getSkinIdNames());
        mStoryTv.setText(Html.fromHtml(data.getDescription()));

        mReco1Adapter.setRecommends(data.getRecommend1());
        mReco2Adapter.setRecommends(data.getRecommend2());
        mSkillIntroduceAdapter.setmSpellsList(data.getSpellsList());

        mAttackProgress.setProgress(data.attributeInfo.attack);
        magicProgress.setProgress(data.attributeInfo.magic);
        mDefenseProgress.setProgress(data.attributeInfo.defense);
        mDifficultyProgress.setProgress(data.attributeInfo.difficulty);

        mAllytipTitleTv.setText(getString(R.string.ally_usage, data.legendName));
        String allyTips = getAppendedString(data.getAllytips());
        mAllytipTv.setText(allyTips);
        mEnemytipTitleTv.setText(getString(R.string.enemy_usage, data.getLegendName()));
        mEnemytipTv.setText(getAppendedString(data.getEnemytips()));
    }

    @NonNull
    private <T> String getAppendedString(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int           size          = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) instanceof Enemytips) {
                String tip = ((Enemytips) list.get(i)).getTip();
                stringBuilder.append(tip);
            } else if (list.get(i) instanceof Allytips) {
                String tip = ((Allytips) list.get(i)).getTip();
                stringBuilder.append(tip);
            }
            if (i < size - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onLoadFailed() {
        Log.d(TAG, "onLoadFailed: ");
        Toast.makeText(this, "onLoadFailed", Toast.LENGTH_SHORT).show();

    }
}
