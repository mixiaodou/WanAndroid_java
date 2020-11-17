package com.example.module_knowledge.knowledgedetail.fragments;

import com.example.lib_comon.base.BaseIView;
import com.example.module_knowledge.beans.KnowledgeListBean;

public interface KnowledgeDetailView extends BaseIView {
    void showKnowledgeDetail(KnowledgeListBean data);
}
