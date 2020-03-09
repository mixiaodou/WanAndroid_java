package com.brucej.wanandroid_java.ui.knowledge.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean, KnowledgeViewHolder> {
    private String TAG = "KnowledgeAdapter--";

    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull KnowledgeViewHolder helper, KnowledgeBean item) {
        helper.tittleTv.setText(item.getName());
        StringBuilder str = new StringBuilder();
        for (KnowledgeBean.ChildrenBean bean : item.getChildren()) {
            str.append(bean.getName() + " ");
        }
        Log.i(TAG, "str=" + str.toString());
        helper.contentTv.setText(str.toString());
    }
}
