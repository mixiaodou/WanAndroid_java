package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brucej.wanandroid_java.R;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeListDetailViewHolder extends BaseViewHolder {
    @BindView(R.id.item_knowledge_list_detail_authorImg)
    public ImageView authorImg;
    @BindView(R.id.item_knowledge_list_detail_authornameTv)
    public TextView authornameTv;
    @BindView(R.id.item_knowledge_list_detail_typeTv)
    public TextView typeTv;
    @BindView(R.id.item_knowledge_list_detail_contentTv)
    public TextView contentTv;
    @BindView(R.id.item_knowledge_list_detail_timeTv)
    public TextView timeTv;

    public KnowledgeListDetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
