package com.example.module_knowledge;

import com.example.lib_comon.base.BaseModel;
import com.example.lib_comon.bean.BaseBean;
import com.example.lib_comon.core.net.RetrofitHelper;
import com.example.module_knowledge.api.KnowledgeApi;
import com.example.module_knowledge.beans.KnowledgeBean;

import java.util.List;

import io.reactivex.Observable;

public class KnowLedgeModel extends BaseModel {
    public void getKnowledgeData(KnowledgeDataCallback callback) {
        Observable<BaseBean<List<KnowledgeBean>>> observable =
                RetrofitHelper.getInstance().getApi(KnowledgeApi.HOST, KnowledgeApi.class).getKnowledgeList();
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeDataCallback extends BaseDataCallback<List<KnowledgeBean>> {

    }
}
