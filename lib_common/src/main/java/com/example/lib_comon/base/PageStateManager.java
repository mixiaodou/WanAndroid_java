package com.example.lib_comon.base;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.example.lib_comon.R;


public class PageStateManager {
    private static final String TAG = "--";
    private static final byte STATE_NORMAL = 0;
    private static final byte STATE_LOADING = 1;
    private static final byte STATE_ERROR = 2;

    public interface ErrorRetryCallback {
        void onErrorRetry();
    }

    private FrameLayout parentLayout;
    private byte currentState = STATE_NORMAL;

    public PageStateManager() {
    }

    private ErrorRetryCallback errorRetryCallback;
    private ViewGroup contentViewParent;
    private View contentView;

    public void init(View contentView, ErrorRetryCallback errorRetryCallback) {
        ViewParent parent = contentView.getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new IllegalArgumentException
                    ("包裹contentView的父布局只支持 ViewGroup");
        }
        this.errorRetryCallback = errorRetryCallback;
        this.contentViewParent = (ViewGroup) parent;
        this.contentView = contentView;
        inflateErrorView();
    }

    private View errorView;
    private long errorViewClickFlag;

    private void inflateErrorView() {
        if (errorView == null) {
            errorView = View.inflate(contentView.getContext(), R.layout.layout_error, null);
            errorView.setVisibility(View.GONE);
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (System.currentTimeMillis() - errorViewClickFlag > 1200) {
                        if (errorRetryCallback != null) {
                            errorRetryCallback.onErrorRetry();
                        }
                        errorViewClickFlag = System.currentTimeMillis();
                    }
                }
            });
            contentViewParent.addView(errorView);
        }
    }

    public void showNormal() {
        if (currentState != STATE_NORMAL) {
            changeContentState(STATE_NORMAL);
        }
    }

    public void showError() {
        if (currentState != STATE_ERROR) {
            changeContentState(STATE_ERROR);
        }
    }

    private void changeContentState(byte state) {
        switch (state) {
            case STATE_NORMAL:
                Log.i(TAG, "STATE_normal");
                errorView.setVisibility(View.GONE);
                contentView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                Log.i(TAG, "STATE_error");
                errorView.setVisibility(View.VISIBLE);
                contentView.setVisibility(View.GONE);
                break;
        }
        currentState = state;
    }

}
