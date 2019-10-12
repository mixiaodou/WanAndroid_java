package com.brucej.wanandroid_java.ui.knowledge;

import com.brucej.wanandroid_java.base.BaseModel;
import com.brucej.wanandroid_java.core.RetrofitHelper;
import com.brucej.wanandroid_java.core.beans.BaseBean;
import com.brucej.wanandroid_java.core.beans.KnowledgeBean;

import java.util.List;

import io.reactivex.Observable;

public class KnowLedgeModel extends BaseModel {
    public void getKnowledgeData(KnowledgeDataCallback callback) {
        Observable<BaseBean<List<KnowledgeBean>>> observable =
                RetrofitHelper.getInstance()
                        .getRestApi().getKnowledgeList();
        handlerBaseData(observable, callback);
    }

    public interface KnowledgeDataCallback extends BaseDataCallback<List<KnowledgeBean>> {

    }
}
