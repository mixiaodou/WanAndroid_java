package com.brucej.wanandroid_java.ui.knowledge;

import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeBean;
import com.example.lib_comon.base.BaseIView;

import java.util.List;

public interface KnowledgeIView extends BaseIView {

    void showKnowledgeView(List<KnowledgeBean> list);

    void showLoading();

    void showLoadError();

}
