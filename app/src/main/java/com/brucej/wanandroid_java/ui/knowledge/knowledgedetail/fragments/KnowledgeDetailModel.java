package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;

import com.brucej.wanandroid_java.api.RestApi;
import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeListBean;
import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;

import io.reactivex.Observable;

public class KnowledgeDetailModel extends BaseModel {

    public void getKnowledgeListDetail(int page, int cid, KnowledgeListDetailCallback callback) {
        Observable<BaseBean<KnowledgeListBean>> observable =
                RetrofitHelper.getInstance().getApi(RestApi.HOST, RestApi.class)
                        .getKnowledgeListData(page, cid);
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeListDetailCallback
            extends BaseDataCallback<KnowledgeListBean> {
    }
}
