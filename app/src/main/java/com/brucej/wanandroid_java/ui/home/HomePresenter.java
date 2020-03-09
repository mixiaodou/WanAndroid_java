package com.brucej.wanandroid_java.ui.home;

import com.brucej.wanandroid_java.ui.home.beans.ArticleBean;
import com.brucej.wanandroid_java.ui.home.beans.BannerBean;
import com.example.lib_comon.base.BasePresenter;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeIView, HomeModel> {


    public void getBanner() {
        m.getBanerData(new HomeModel.BannerDataCallback() {
            @Override
            public void onScussess(List<BannerBean> baseData) {
                v.showBanner(baseData);
            }

            @Override
            public void onError(String errorMsg) {
                v.showToast(errorMsg);
            }

            @Override
            public void onFail() {
                v.showLoadError();
            }
        });
    }

    public void getArticleList(int num) {
        m.getArticleListData(num, new HomeModel.ArticleListDataCallback() {
            @Override
            public void onScussess(ArticleBean baseData) {
                v.showArticleList(baseData);
            }

            @Override
            public void onError(String errorMsg) {
                v.showToast(errorMsg);
            }

            @Override
            public void onFail() {

            }
        });
    }

}
