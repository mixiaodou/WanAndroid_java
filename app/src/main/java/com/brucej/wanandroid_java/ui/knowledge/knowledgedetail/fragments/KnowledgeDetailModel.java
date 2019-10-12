package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;

import com.brucej.wanandroid_java.base.BaseModel;
import com.brucej.wanandroid_java.core.RetrofitHelper;
import com.brucej.wanandroid_java.core.beans.BaseBean;
import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;

import io.reactivex.Observable;

public class KnowledgeDetailModel extends BaseModel {

    public void getKnowledgeListDetail(int page, int cid, KnowledgeListDetailCallback callback) {
        Observable<BaseBean<KnowledgeListBean>> observable =
                RetrofitHelper.getInstance().getRestApi()
                        .getKnowledgeListData(page, cid);
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeListDetailCallback
            extends BaseDataCallback<KnowledgeListBean> {
    }
}
