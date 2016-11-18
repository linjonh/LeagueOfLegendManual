package com.jaysen.leagueoflegendmanual;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * basic abstract domain layer useCase class.
 */

public abstract class UseCase<Q extends UseCase.RequestParam, P extends UseCase.Response> {


    private Q                  mRequestParam;
    private UseCaseCallBack<P> mUseCaseCallBack;

    public UseCaseCallBack<P> getmUseCaseCallBack() {
        return mUseCaseCallBack;
    }

    public void setmUseCaseCallBack(UseCaseCallBack<P> mUseCaseCallBack) {
        this.mUseCaseCallBack = mUseCaseCallBack;
    }


    public void setmRequestParam(Q mRequestParam) {
        this.mRequestParam = mRequestParam;
    }

    public Q getmRequestParam() {
        return mRequestParam;
    }

    void run() {
        executeUseCase(mRequestParam);
    }

    /**
     * subclass must implement this method to use mUseCaseCallBack to return response values
     *
     * @param requestParam parameters for query
     */
    public abstract void executeUseCase(Q requestParam);

    public interface RequestParam {
    }

    public interface Response {
    }

    public interface UseCaseCallBack<R> {
        void onSuccess(R response);

        void onError();
    }
}
