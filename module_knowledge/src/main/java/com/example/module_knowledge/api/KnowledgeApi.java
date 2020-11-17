package com.example.module_knowledge.api;

import com.example.lib_comon.bean.BaseBean;
import com.example.module_knowledge.beans.KnowledgeBean;
import com.example.module_knowledge.beans.KnowledgeListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KnowledgeApi {
    String HOST = "https://www.wanandroid.com/";

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
