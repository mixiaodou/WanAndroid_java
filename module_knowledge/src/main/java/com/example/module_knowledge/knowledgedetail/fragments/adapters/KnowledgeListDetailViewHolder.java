package com.example.module_knowledge.knowledgedetail.fragments.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_knowledge.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeListDetailViewHolder extends BaseViewHolder {
    @BindView(R2.id.item_knowledge_list_detail_authorImg)
    public ImageView authorImg;
    @BindView(R2.id.item_knowledge_list_detail_authornameTv)
    public TextView authornameTv;
    @BindView(R2.id.item_knowledge_list_detail_typeTv)
    public TextView typeTv;
    @BindView(R2.id.item_knowledge_list_detail_contentTv)
    public TextView contentTv;
    @BindView(R2.id.item_knowledge_list_detail_timeTv)
    public TextView timeTv;

    public KnowledgeListDetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
