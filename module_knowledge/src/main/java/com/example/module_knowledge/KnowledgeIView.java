package com.example.module_knowledge;

import com.example.lib_comon.base.BaseIView;
import com.example.module_knowledge.beans.KnowledgeBean;

import java.util.List;

public interface KnowledgeIView extends BaseIView {

    void showKnowledgeView(List<KnowledgeBean> list);

    void showLoading();

    void showLoadError();

}
