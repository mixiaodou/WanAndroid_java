package com.brucej.wanandroid_java.ui.home;

import com.brucej.wanandroid_java.api.RestApi;
import com.brucej.wanandroid_java.ui.home.beans.ArticleBean;
import com.brucej.wanandroid_java.ui.home.beans.BannerBean;
import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;

import java.util.List;

import io.reactivex.Observable;

public class HomeModel extends BaseModel {

    public void getBanerData(BannerDataCallback callback) {
        Observable<BaseBean<List<BannerBean>>> observable = RetrofitHelper.getInstance()
                .getApi(RestApi.HOST, RestApi.class).getBanner();
        handlerBaseData(observable, callback);
    }

    public interface BannerDataCallback extends BaseDataCallback<List<BannerBean>> {

    }


    public void getArticleListData(int num, ArticleListDataCallback callback) {
        Observable<BaseBean<ArticleBean>> observable = RetrofitHelper.getInstance()
                .getApi(RestApi.HOST, RestApi.class).getArticleList(num);
        handlerBaseData(observable, callback);

    }

    public interface ArticleListDataCallback extends BaseDataCallback<ArticleBean> {

    }

}
