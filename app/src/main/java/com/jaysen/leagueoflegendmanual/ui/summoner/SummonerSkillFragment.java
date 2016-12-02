package com.jaysen.leagueoflegendmanual.ui.summoner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseSummonerSkill;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;
import com.jaysen.leagueoflegendmanual.ui.AbsRCVAdapter;
import com.jaysen.leagueoflegendmanual.ui.MyApplicationLike;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummonerSkillFragment extends Fragment implements AbsRCVAdapter.OnItemClickListener<Object>,
                                                               Presenter.View<List<SummonerSkillEntity>> {


    @BindView(R.id.summonerSkillRCV)
    RecyclerView summonerSkillRCV;
    private SummonerAdapter mAdapter;
    @Inject
    SummonerPresenter mSummonerPresenter;

    public SummonerSkillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summoner_skill, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyApplicationLike.getDataSourceComponent().inject(this);
        summonerSkillRCV.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter = new SummonerAdapter();
        summonerSkillRCV.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mSummonerPresenter.setmParam(new UseCaseSummonerSkill.RequestParam());
        mSummonerPresenter.setMvpView(this);
        mSummonerPresenter.loadData();
    }

    @Override
    public void onItemClick(Object itemData) {
        // TODO: 2016/12/3 handle item click
        Toast.makeText(getContext(), "onItemClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadSuccess(List<SummonerSkillEntity> data) {
        Toast.makeText(getContext(), "onLoadSuccess", Toast.LENGTH_SHORT).show();

        mAdapter.setmDataSets(data);

    }

    @Override
    public void onLoadFailed() {
        Toast.makeText(getContext(), "onLoadFailed", Toast.LENGTH_SHORT).show();
    }
}
