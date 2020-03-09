package com.example.lib_comon.base;


import android.util.Log;

import com.example.lib_comon.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseModel {
    private String TAG = "BaseModel--";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        compositeDisposable.clear();
    }

    public <T> void handlerBaseData(Observable<BaseBean<T>> observable,
                                    final BaseDataCallback<T> callback) {
        Log.i(TAG, "handlerBaseData");
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<T>>() {
                    @Override
                    public void accept(BaseBean<T> listBaseBean) throws Exception {
                        Log.i(TAG, "listBaseBean=" + listBaseBean.toString());
                        if (listBaseBean.getErrorCode() == 0) {
                            callback.onScussess(listBaseBean.getData());
                        } else {
                            callback.onError(listBaseBean.getErrorMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "throwable=" + throwable.getMessage());
                        callback.onFail();
                    }
                });
        //
        addDisposable(disposable);
    }

    public interface BaseDataCallback<T> {
        void onScussess(T baseData);

        void onError(String errorMsg);

        void onFail();
    }
}
