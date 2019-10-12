package com.brucej.wanandroid_java.ui.knowledge;

import com.brucej.wanandroid_java.base.BaseIView;
import com.brucej.wanandroid_java.core.beans.KnowledgeBean;

import java.util.List;

public interface KnowledgeIView extends BaseIView {

    void showKnowledgeView(List<KnowledgeBean> list);

    void showLoading();

    void showLoadError();

}
