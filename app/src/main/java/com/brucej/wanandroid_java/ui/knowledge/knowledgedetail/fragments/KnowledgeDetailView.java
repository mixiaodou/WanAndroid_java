package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;

import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeListBean;
import com.example.lib_comon.base.BaseIView;

public interface KnowledgeDetailView extends BaseIView {
    void showKnowledgeDetail(KnowledgeListBean data);
}
