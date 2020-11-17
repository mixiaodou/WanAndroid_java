package com.example.module_home.api;

import com.example.lib_comon.bean.BaseBean;
import com.example.module_home.beans.ArticleBean;
import com.example.module_home.beans.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeApi {
    String HOST = "https://www.wanandroid.com/";

    /**
     * https://www.wanandroid.com/banner/json
     * banner数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseBean<List<BannerBean>>> getBanner();

    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseBean<ArticleBean>> getArticleList(@Path("num") int num);
}
