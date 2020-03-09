package com.example.lib_comon.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter, V extends BaseIView, M extends BaseModel>
        extends Fragment {

    public P mPresenter;
    private Unbinder mBinder;
    public PageStateManager pageState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, v);
        pageState = new PageStateManager();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        initDataAndEvent(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void initDataAndEvent(Bundle instanceState);
}
