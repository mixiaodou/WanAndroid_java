package com.brucej.wanandroid_java.ui.knowledge;

import com.brucej.wanandroid_java.base.BasePresenter;
import com.brucej.wanandroid_java.core.beans.KnowledgeBean;

import java.util.List;

public class KnowledgePresenter extends BasePresenter<KnowledgeIView, KnowLedgeModel> {

    public void getKnowledgeData() {
        m.getKnowledgeData(new KnowLedgeModel.KnowledgeDataCallback() {
            @Override
            public void onScussess(List<KnowledgeBean> baseData) {
                v.showKnowledgeView(baseData);
            }

            @Override
            public void onError(String errorMsg) {
                v.showToast(errorMsg);
            }

            @Override
            public void onFail() {
                v.showLoadError();
            }
        });
    }
}
