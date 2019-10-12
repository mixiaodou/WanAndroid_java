package com.brucej.wanandroid_java.ui.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brucej.wanandroid_java.R;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleListViewHolder extends BaseViewHolder {

    @BindView(R.id.item_article_list_authorImg)
    ImageView authorImg;
    @BindView(R.id.item_article_list_authornameTv)
    TextView authorTv;
    @BindView(R.id.item_article_list_typeTv)
    TextView typeTv;
    @BindView(R.id.item_article_list_contentTv)
    TextView contentTv;
    @BindView(R.id.item_article_list_timeTv)
    TextView timeTv;
    @BindView(R.id.item_article_list_newtagTv)
    TextView newtagTv;
    @BindView(R.id.item_article_list_projecttagTv)
    TextView projecttagTv;



    public ArticleListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
