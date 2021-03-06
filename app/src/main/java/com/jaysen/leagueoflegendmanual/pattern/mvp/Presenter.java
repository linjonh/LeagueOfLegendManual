package com.jaysen.leagueoflegendmanual.pattern.mvp;

import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */

public abstract class Presenter<MvpView extends Presenter.View, ReqParam extends UseCase.RequestParam> {
    private MvpView mView;

    public ReqParam getmParam() {
        return mParam;
    }

    public void setmParam(ReqParam mParam) {
        this.mParam = mParam;
    }

    private ReqParam mParam;

    public interface View<T> {
        void onLoadSuccess(T data);

        void onLoadFailed();
    }

    public abstract void loadData();

    public MvpView getMvpView() {
        return mView;
    }

    public void setMvpView(MvpView mvpView) {
        mView = mvpView;
    }
}
