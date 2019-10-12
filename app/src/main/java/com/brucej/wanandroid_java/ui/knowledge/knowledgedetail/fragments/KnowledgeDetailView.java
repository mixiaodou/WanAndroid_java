package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;

import com.brucej.wanandroid_java.base.BaseIView;
import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;

import java.util.List;

public interface KnowledgeDetailView extends BaseIView {
    void showKnowledgeDetail(KnowledgeListBean data);
}
