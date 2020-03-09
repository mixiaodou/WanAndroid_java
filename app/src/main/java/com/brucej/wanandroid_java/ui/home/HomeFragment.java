package com.brucej.wanandroid_java.ui.home;


import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.brucej.wanandroid_java.R;
import com.brucej.wanandroid_java.ui.home.beans.ArticleBean;
import com.brucej.wanandroid_java.ui.home.beans.BannerBean;
import com.brucej.wanandroid_java.ui.BannerGlideImageLoader;
import com.brucej.wanandroid_java.ui.articledetail.ArticleDetailActivity;
import com.brucej.wanandroid_java.ui.main.adapters.ArticleListAdapter;
import com.example.lib_comon.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter, HomeIView, HomeModel>
        implements HomeIView {
    private String TAG = "HomeFragment--";
    @BindView(R.id.fragment_home_recyc)
    public RecyclerView recycView;
    @BindView(R.id.fragment_home_srl)
    public SmartRefreshLayout srlView;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    private List<ArticleBean.DatasBean> articleDataList = new ArrayList<>();
    private ArticleListAdapter articleListAdapter;

    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        mPresenter.attachView(this);
        mPresenter.setModel(new HomeModel());
        pageState.init(srlView, () -> {
            mPresenter.getBanner();
            mPresenter.getArticleList(0);
        });
        //
        initArticleListAdapter();
        //
        initBanner();
        //
        initSrlView();
        //
        srlView.autoRefresh();
    }

    private void initArticleListAdapter() {
        articleListAdapter = new ArticleListAdapter(R.layout.item_article_list, articleDataList);
        recycView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recycView.setAdapter(articleListAdapter);
        //点击监听
        articleListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ArticleBean.DatasBean bean = articleDataList.get(position);
            ArticleDetailActivity.skip(getContext(), bean.getLink(), bean.getTitle());
        });
    }

    private Banner banner;

    private void initBanner() {
        View bannerParent = View.inflate(getContext(),
                R.layout.layout_home_banner, null);
        banner = bannerParent.findViewById(R.id.banner);
        articleListAdapter.addHeaderView(bannerParent);
        banner.setImageLoader(new BannerGlideImageLoader());
        //指示器样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //指示器方向
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //切换动画
        banner.setBannerAnimation(AccordionTransformer.class);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setOnBannerListener(position -> {
            BannerBean bean = bannerBeanList.get(position);
            ArticleDetailActivity.skip(getContext(), bean.getUrl(), bean.getTitle());
        });
        //banner.start();
    }

    private int pageNo = 0;

    private void initSrlView() {
        srlView.setEnableRefresh(true);
        srlView.setEnableLoadMore(true);
        srlView.setEnableAutoLoadMore(false);
        srlView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.i(TAG, "setOnRefreshListener onRefresh");
                mPresenter.getBanner();
                mPresenter.getArticleList(0);
            }
        });
        srlView.setOnLoadMoreListener(refreshlayout -> {
            Log.i(TAG, "setOnLoadmoreListener");
            pageNo++;
            mPresenter.getArticleList(pageNo);
        });
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

    private List<BannerBean> bannerBeanList;

    @Override
    public void showBanner(List<BannerBean> bannerBeanList) {
        pageState.showNormal();
        this.bannerBeanList = bannerBeanList;
        ArrayList<String> imgUrls = new ArrayList<>();
        ArrayList<String> indictorTittles = new ArrayList<>();
        for (BannerBean bean : bannerBeanList) {
            imgUrls.add(bean.getImagePath());
            indictorTittles.add(bean.getTitle());
        }
        banner.setBannerTitles(indictorTittles);
        banner.setImages(imgUrls);
        banner.start();
    }

    @Override
    public void showArticleList(ArticleBean articleBean) {
        pageState.showNormal();
        pageNo = articleBean.getCurPage() - 1;
        if (pageNo == 0) {
            articleListAdapter.replaceData(articleBean.getDatas());
            srlView.finishRefresh();
            srlView.finishLoadMore();
            Log.i(TAG, "showArticleList finishRefresh");
        } else {
            if (articleBean.getDatas() == null || articleBean.getDatas().size() == 0) {
                srlView.finishLoadMoreWithNoMoreData();
                return;
            }
            srlView.finishLoadMore();
            Log.i(TAG, "showArticleList finishLoadMore");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadError() {
        pageState.showError();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (banner != null) {
            banner.releaseBanner();
        }
    }
}
