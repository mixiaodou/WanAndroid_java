package com.example.module_knowledge.knowledgedetail.fragments;

import com.example.lib_comon.base.BasePresenter;
import com.example.module_knowledge.beans.KnowledgeListBean;

public class KnowledgeDetailPresenter extends
        BasePresenter<KnowledgeDetailView, KnowledgeDetailModel> {
    private static final String TAG = "KnowledgeDetailPresenter--";

    public void getKnowledgeListDetail(int page, int cid) {
        m.getKnowledgeListDetail(page, cid,
                new KnowledgeDetailModel.KnowledgeListDetailCallback() {
                    @Override
                    public void onScussess(KnowledgeListBean baseData) {
                        v.showKnowledgeDetail(baseData);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        v.showToast(errorMsg);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
