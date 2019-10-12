package com.brucej.wanandroid_java.ui.home;

import com.brucej.wanandroid_java.base.BaseModel;
import com.brucej.wanandroid_java.core.RetrofitHelper;
import com.brucej.wanandroid_java.core.beans.ArticleBean;
import com.brucej.wanandroid_java.core.beans.BannerBean;
import com.brucej.wanandroid_java.core.beans.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel {

    public void getBanerData(BannerDataCallback callback) {
        Observable<BaseBean<List<BannerBean>>> observable = RetrofitHelper.getInstance()
                .getRestApi().getBanner();
        handlerBaseData(observable, callback);
    }

    public interface BannerDataCallback extends BaseDataCallback<List<BannerBean>> {

    }


    public void getArticleListData(int num, ArticleListDataCallback callback) {
        Observable<BaseBean<ArticleBean>> observable = RetrofitHelper.getInstance()
                .getRestApi().getArticleList(num);
        handlerBaseData(observable, callback);

    }
    public interface ArticleListDataCallback extends BaseDataCallback<ArticleBean> {

    }

}
