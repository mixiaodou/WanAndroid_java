package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class KnowledgeListDetailAdapter extends
        BaseQuickAdapter<KnowledgeListBean.DatasBean, KnowledgeListDetailViewHolder> {
    public KnowledgeListDetailAdapter(int layoutResId, @Nullable List<KnowledgeListBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull KnowledgeListDetailViewHolder helper, KnowledgeListBean.DatasBean item) {
        helper.authornameTv.setText(item.getAuthor());
        helper.typeTv.setText(item.getSuperChapterName()+" "+item.getChapterName());
        helper.contentTv.setText(item.getTitle());
        helper.typeTv.setText(item.getNiceDate());
    }
}
