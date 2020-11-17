package com.example.module_home;

import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;
import com.example.module_home.api.HomeApi;
import com.example.module_home.beans.ArticleBean;
import com.example.module_home.beans.BannerBean;

import java.util.List;

import io.reactivex.Observable;

public class HomeModel extends BaseModel {

    public void getBanerData(BannerDataCallback callback) {
        Observable<BaseBean<List<BannerBean>>> observable = RetrofitHelper.getInstance()
                .getApi(HomeApi.HOST, HomeApi.class).getBanner();
        handlerBaseData(observable, callback);
    }

    public interface BannerDataCallback extends BaseDataCallback<List<BannerBean>> {

    }


    public void getArticleListData(int num, ArticleListDataCallback callback) {
        Observable<BaseBean<ArticleBean>> observable = RetrofitHelper.getInstance()
                .getApi(HomeApi.HOST, HomeApi.class).getArticleList(num);
        handlerBaseData(observable, callback);

    }

    public interface ArticleListDataCallback extends BaseDataCallback<ArticleBean> {

    }

}
