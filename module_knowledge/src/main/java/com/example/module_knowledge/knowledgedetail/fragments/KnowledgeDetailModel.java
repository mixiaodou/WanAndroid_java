package com.example.module_knowledge.knowledgedetail.fragments;

import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;
import com.example.module_knowledge.api.KnowledgeApi;
import com.example.module_knowledge.beans.KnowledgeListBean;

import io.reactivex.Observable;

public class KnowledgeDetailModel extends BaseModel {

    public void getKnowledgeListDetail(int page, int cid, KnowledgeListDetailCallback callback) {
        Observable<BaseBean<KnowledgeListBean>> observable =
                RetrofitHelper.getInstance().getApi(KnowledgeApi.HOST, KnowledgeApi.class)
                        .getKnowledgeListData(page, cid);
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeListDetailCallback
            extends BaseDataCallback<KnowledgeListBean> {
    }
}
