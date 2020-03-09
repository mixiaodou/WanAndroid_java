package com.brucej.wanandroid_java.ui.main.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import com.brucej.wanandroid_java.ui.home.beans.ArticleBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, ArticleListViewHolder> {
    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull ArticleListViewHolder helper, ArticleBean.DatasBean item) {
        helper.authorTv.setText(item.getAuthor());
        helper.typeTv.setText(item.getSuperChapterName() + "/" + item.getChapterName());
        helper.contentTv.setText(item.getTitle());
        helper.timeTv.setText(item.getNiceDate());
        if (item.getTags() != null && item.getTags().size() > 0) {
            if (item.getTags().size() == 1) {
                helper.newtagTv.setText(item.getTags().get(0).getName());
                helper.newtagTv.setVisibility(View.VISIBLE);
                helper.projecttagTv.setVisibility(View.GONE);
            } else if (item.getTags().size() == 2) {
                helper.newtagTv.setText(item.getTags().get(0).getName());
                helper.projecttagTv.setText(item.getTags().get(1).getName());
                helper.newtagTv.setVisibility(View.VISIBLE);
                helper.projecttagTv.setVisibility(View.VISIBLE);
            }
        } else {
            helper.newtagTv.setVisibility(View.GONE);
            helper.projecttagTv.setVisibility(View.GONE);
        }

    }
}
