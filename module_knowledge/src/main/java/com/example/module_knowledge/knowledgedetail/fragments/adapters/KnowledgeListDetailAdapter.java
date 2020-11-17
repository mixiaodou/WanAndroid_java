package com.example.module_knowledge.knowledgedetail.fragments.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_knowledge.beans.KnowledgeListBean;

import java.util.Collection;
import java.util.List;

public class KnowledgeListDetailAdapter extends BaseQuickAdapter<KnowledgeListBean.DatasBean, KnowledgeListDetailViewHolder> {

    private String TAG = "KnowledgeListDetailAdapter--";

    public KnowledgeListDetailAdapter(int layoutResId, @Nullable List<KnowledgeListBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull KnowledgeListDetailViewHolder helper, KnowledgeListBean.DatasBean item) {
        helper.authornameTv.setText(item.getAuthor());
        helper.typeTv.setText(item.getSuperChapterName() + " " + item.getChapterName());
        helper.contentTv.setText(item.getTitle());
        helper.typeTv.setText(item.getNiceDate());
    }

    @Override
    public void addData(@NonNull Collection<? extends KnowledgeListBean.DatasBean> newData) {
        super.addData(newData);
        Log.i(TAG,"--addData");
    }

    @Override
    public void replaceData(@NonNull Collection<? extends KnowledgeListBean.DatasBean> data) {
        super.replaceData(data);
        Log.i(TAG,"--replaceData");
    }
}
