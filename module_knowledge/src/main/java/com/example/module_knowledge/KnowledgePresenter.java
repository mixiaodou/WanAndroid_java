package com.example.module_knowledge;

import com.example.lib_comon.base.BasePresenter;
import com.example.module_knowledge.beans.KnowledgeBean;

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
