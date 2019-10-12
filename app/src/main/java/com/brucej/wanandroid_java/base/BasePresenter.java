package com.brucej.wanandroid_java.base;

public class BasePresenter<V extends BaseIView, M extends BaseModel> {

    public V v;
    public M m;

    public BasePresenter() {
    }

    public void setModel(M m) {
        this.m = m;
    }

    public void attachView(V v) {
        this.v = v;
    }

    public void detachView() {
        this.v = null;
        m.clearDisposable();
    }
}
