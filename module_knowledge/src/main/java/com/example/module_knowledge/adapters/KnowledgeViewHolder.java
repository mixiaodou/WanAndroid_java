package com.example.module_knowledge.adapters;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_knowledge.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeViewHolder extends BaseViewHolder {
    @BindView(R2.id.item_knowledge_tittleTv)
    public TextView tittleTv;
    @BindView(R2.id.item_knowledge_contentTv)
    public TextView contentTv;

    public KnowledgeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
