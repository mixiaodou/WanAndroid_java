package com.brucej.wanandroid_java.ui.knowledge;

import com.brucej.wanandroid_java.api.RestApi;
import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeBean;
import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;

import java.util.List;

import io.reactivex.Observable;

public class KnowLedgeModel extends BaseModel {
    public void getKnowledgeData(KnowledgeDataCallback callback) {
        Observable<BaseBean<List<KnowledgeBean>>> observable =
                RetrofitHelper.getInstance().getApi(RestApi.HOST, RestApi.class).getKnowledgeList();
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeDataCallback extends BaseDataCallback<List<KnowledgeBean>> {

    }
}
