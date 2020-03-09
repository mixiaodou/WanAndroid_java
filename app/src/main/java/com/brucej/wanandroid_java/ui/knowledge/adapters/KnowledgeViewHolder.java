package com.brucej.wanandroid_java.ui.knowledge.adapters;

import android.view.View;
import android.widget.TextView;

import com.brucej.wanandroid_java.R;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeViewHolder extends BaseViewHolder {
    @BindView(R.id.item_knowledge_tittleTv)
    public TextView tittleTv;
    @BindView(R.id.item_knowledge_contentTv)
    public TextView contentTv;

    public KnowledgeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
