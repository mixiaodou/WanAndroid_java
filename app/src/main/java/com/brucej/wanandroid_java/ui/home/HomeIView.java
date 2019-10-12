package com.brucej.wanandroid_java.ui.home;

import com.brucej.wanandroid_java.base.BaseIView;
import com.brucej.wanandroid_java.core.beans.ArticleBean;
import com.brucej.wanandroid_java.core.beans.BannerBean;

import java.util.List;

public interface HomeIView extends BaseIView {

    void showBanner(List<BannerBean> beanList);

    void showArticleList(ArticleBean articleBean);

    void showLoading();

    void showLoadError();
}
