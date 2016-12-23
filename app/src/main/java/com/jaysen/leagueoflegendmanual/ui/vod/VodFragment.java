package com.jaysen.leagueoflegendmanual.ui.vod;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseVod;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;
import com.jaysen.leagueoflegendmanual.ui.MyApplicationLike;
import com.jaysen.leagueoflegendmanual.ui.player.PlayerActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VodFragment extends Fragment implements Presenter.View<List<VodEntity>>,
        SwipeRefreshLayout.OnRefreshListener, AbsRCVAdapter.OnItemClickListener<Object> {


    @BindView(R.id.vodRCV)
    RecyclerView       vodRCV;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private VodAdapter vodAdapter;
    @Inject
    VodPresenter mVodPresenter;

    public VodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vod, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyApplicationLike.getDataSourceComponent().inject(this);
        //set up refresh indicator
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(this);
        //set up list adapter
        vodAdapter = new VodAdapter();
        vodRCV.setAdapter(vodAdapter);
        vodRCV.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        vodAdapter.setOnItemClickListener(this);
        //load data
        mVodPresenter.setmParam(new UseCaseVod.RequestParam());
        mVodPresenter.setMvpView(this);
        mVodPresenter.loadData();
    }

    @Override
    public void onLoadSuccess(List<VodEntity> vodEntityList) {
        swipeRefreshLayout.setRefreshing(false);
        vodAdapter.setmDataSets(vodEntityList);
    }

    @Override
    public void onLoadFailed() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mVodPresenter.loadData();
    }

    @Override
    public void onItemClick(Object itemData) {
        VodEntity vodEntity = (VodEntity) itemData;
        Uri       uri       =Uri.parse(vodEntity.getVodlink());
        Intent    intent    = new Intent(this.getContext(), PlayerActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }
}
