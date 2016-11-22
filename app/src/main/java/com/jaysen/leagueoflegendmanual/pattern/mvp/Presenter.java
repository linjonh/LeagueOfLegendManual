package com.jaysen.leagueoflegendmanual.pattern.mvp;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */

public interface Presenter<MvpView extends Presenter.View> {

    interface View<T> {
        void onLoadSuccess(T data);

        void onLoadFailed();
    }

    void loadData();

    View getMvpView();
}
