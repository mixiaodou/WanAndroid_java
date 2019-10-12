package com.brucej.wanandroid_java.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, V extends BaseIView, M extends BaseModel>
        extends AppCompatActivity {
    public P mPresenter;
    private Unbinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        mPresenter = createPresenter();
        initDataAndEvent(savedInstanceState);
    }

    //todo 模板模式：定义一组功能的流程，由实现类来实现功能
    //模板模式
    protected abstract int getLayoutId();

    //模板模式
    protected abstract P createPresenter();

    //模板模式
    protected abstract void initDataAndEvent(Bundle instanceState);

    @Override
    protected void onDestroy() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
