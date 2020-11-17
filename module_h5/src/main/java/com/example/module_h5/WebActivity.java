package com.example.module_h5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_comon.base.BaseActivity;
import com.example.lib_comon.utils.CommonUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

@Route(path = "/h5/WebActivity")
public class WebActivity extends
        BaseActivity<WebPresenter, WebIView, WeblModel> implements WebIView {
    private static final String URL = "url";
    private static final String TITTLE = "tittle";
    private static final String TAG = "WebActivity--";

    @BindView(R2.id.layout_toolbar)
    public Toolbar toolbar;
    @BindView(R2.id.layout_toolbar_tittle)
    public TextView toolbarTittle;
    @BindView(R2.id.web_framelayout)
    public FrameLayout frameLayout;
    //
    private AgentWeb.PreAgentWeb agentWeb;
    @Autowired
    String url;
    @Autowired
    String tittle;

    /*
   public static void skip(Context context, String url, String tittle) {
      Intent intent = new Intent(context, WebActivity.class);
      intent.putExtra(URL, url);
      intent.putExtra(TITTLE, tittle);
      context.startActivity(intent);
   }
  */

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected WebPresenter createPresenter() {
        return new WebPresenter();
    }


    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        ARouter.getInstance().inject(this);
        //
        mPresenter.setModel(new WeblModel());
        mPresenter.attachView(this);
        //
        url = getIntent().getStringExtra(URL);
        tittle = getIntent().getStringExtra(TITTLE);
        Log.i(TAG, "url=" + url);
        //
        initToolbar();
        initWebView();
        //
        agentWeb.ready();
        agentWeb.go(url);
        setTittle(tittle);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        //
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }


    private void initWebView() {
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(frameLayout, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.layout_error, R.id.layout_error_reloadTv)
                .createAgentWeb();
        WebView webView = agentWeb.get().getWebCreator().getWebView();
        WebSettings webSettings = webView.getSettings();
        //todo 解决 不显示图片问题
        // https://blog.csdn.net/u013320868/article/details/52837671
        // android5.1 开始https中默认不允许使用http加载图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webSettings.setBlockNetworkImage(false);
        }
        //支持缓存
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        if (CommonUtil.isNetworkConnected(getApplicationContext())) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        //
        webSettings.setJavaScriptEnabled(true);//支持js
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);//不显示缩放按钮
        webSettings.setUseWideViewPort(true);//图片调整到webview尺寸
        //自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public void setTittle(String tittle) {
        toolbarTittle.setText(tittle);
    }

    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showSnackBar(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (agentWeb != null) {
            agentWeb.get().getWebLifeCycle().onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (agentWeb != null) {
            agentWeb.get().getWebLifeCycle().onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (agentWeb != null) {
            agentWeb.get().getWebLifeCycle().onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (!agentWeb.get().back()) {
            super.onBackPressed();
        }
    }
}
