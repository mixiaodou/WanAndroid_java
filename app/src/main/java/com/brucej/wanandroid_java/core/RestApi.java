package com.brucej.wanandroid_java.core;

import com.brucej.wanandroid_java.core.beans.ArticleBean;
import com.brucej.wanandroid_java.core.beans.BannerBean;
import com.brucej.wanandroid_java.core.beans.BaseBean;
import com.brucej.wanandroid_java.core.beans.KnowledgeBean;
import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
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

    /**
     * 知识体系列表
     * https://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    Observable<BaseBean<List<KnowledgeBean>>> getKnowledgeList();

    /**
     * 知识体系列表数据
     * https://www.wanandroid.com/article/list/0/json?cid=60
     */
    @GET("article/list/{num}/json")
    Observable<BaseBean<KnowledgeListBean>> getKnowledgeListData(@Path("num") int page, @Query("cid") int cid);

}
