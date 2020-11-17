package com.example.module_home;

import com.example.lib_comon.base.BaseIView;
import com.example.module_home.beans.ArticleBean;
import com.example.module_home.beans.BannerBean;

import java.util.List;

public interface HomeIView extends BaseIView {

    void showBanner(List<BannerBean> beanList);

    void showArticleList(ArticleBean articleBean);

    void showLoading();

    void showLoadError();
}
