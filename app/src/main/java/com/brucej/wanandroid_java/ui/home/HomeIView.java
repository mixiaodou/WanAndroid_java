package com.brucej.wanandroid_java.ui.home;

import com.brucej.wanandroid_java.ui.home.beans.ArticleBean;
import com.brucej.wanandroid_java.ui.home.beans.BannerBean;
import com.example.lib_comon.base.BaseIView;

import java.util.List;

public interface HomeIView extends BaseIView {

    void showBanner(List<BannerBean> beanList);

    void showArticleList(ArticleBean articleBean);

    void showLoading();

    void showLoadError();
}
