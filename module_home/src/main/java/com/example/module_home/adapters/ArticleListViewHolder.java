package com.example.module_home.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_home.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleListViewHolder extends BaseViewHolder {

    @BindView(R2.id.item_article_list_authorImg)
    public ImageView authorImg;
    @BindView(R2.id.item_article_list_authornameTv)
    public TextView authorTv;
    @BindView(R2.id.item_article_list_typeTv)
    public TextView typeTv;
    @BindView(R2.id.item_article_list_contentTv)
    public TextView contentTv;
    @BindView(R2.id.item_article_list_timeTv)
    public TextView timeTv;
    @BindView(R2.id.item_article_list_newtagTv)
    public TextView newtagTv;
    @BindView(R2.id.item_article_list_projecttagTv)
    public TextView projecttagTv;


    public ArticleListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
