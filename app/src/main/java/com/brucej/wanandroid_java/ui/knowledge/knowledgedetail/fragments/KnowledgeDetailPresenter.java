package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;

import com.brucej.wanandroid_java.base.BasePresenter;
import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;

public class KnowledgeDetailPresenter extends
        BasePresenter<KnowledgeDetailView, KnowledgeDetailModel> {
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
