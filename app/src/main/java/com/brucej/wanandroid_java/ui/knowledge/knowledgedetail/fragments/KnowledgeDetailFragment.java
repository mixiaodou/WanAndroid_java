package com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brucej.wanandroid_java.R;
import com.brucej.wanandroid_java.base.BaseFragment;
import com.brucej.wanandroid_java.core.beans.KnowledgeBean;
import com.brucej.wanandroid_java.core.beans.KnowledgeListBean;
import com.brucej.wanandroid_java.ui.articledetail.ArticleDetailActivity;
import com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.fragments.adapters.KnowledgeListDetailAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeDetailFragment extends
        BaseFragment<KnowledgeDetailPresenter, KnowledgeDetailView, KnowledgeDetailModel>
        implements KnowledgeDetailView {
    private String TAG = "KnowledgeDetailFragment--";
    private static final String CHILD_RENBEAN = "childrenBean";
    @BindView(R.id.fragment_knowledge_detail_recyc)
    public RecyclerView recyc;
    @BindView(R.id.fragment_knowledge_detail_srl)
    public SmartRefreshLayout srl;


    public static KnowledgeDetailFragment getInstance(KnowledgeBean.ChildrenBean childrenBean) {
        KnowledgeDetailFragment fragment = new KnowledgeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CHILD_RENBEAN, childrenBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_detail;
    }

    @Override
    protected KnowledgeDetailPresenter createPresenter() {
        return new KnowledgeDetailPresenter();
    }

    private KnowledgeBean.ChildrenBean childrenBean;
    private ArrayList<KnowledgeListBean.DatasBean> dataList;
    private KnowledgeListDetailAdapter listDetailAdapter;
    private int pageNum;

    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        mPresenter.attachView(this);
        mPresenter.setModel(new KnowledgeDetailModel());
        //
        initBundle();
        initAdapter();
        initSRL();
        srl.autoRefresh();
    }

    private void initSRL() {
        srl.setEnableRefresh(true);
        srl.setEnableLoadMore(true);
        srl.setEnableAutoLoadMore(false);
        srl.setOnRefreshListener(refreshLayout -> {
            mPresenter.getKnowledgeListDetail(0, childrenBean.getId());
        });
        srl.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.getKnowledgeListDetail(pageNum + 1, childrenBean.getId());
        });
    }

    private void initAdapter() {
        dataList = new ArrayList<>();
        listDetailAdapter = new KnowledgeListDetailAdapter(R.layout.item_knowledge_list_detail,
                dataList);
        recyc.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyc.setAdapter(listDetailAdapter);
        listDetailAdapter.setOnItemClickListener((adapter, view, position) ->
                skipDetail(dataList.get(position).getLink(), dataList.get(position).getTitle())
        );
    }

    private void skipDetail(String url, String tittle) {
        ArticleDetailActivity.skip(getContext(), url, tittle);
    }

    private void initBundle() {
        Bundle bundle = getArguments();
        childrenBean = bundle.getParcelable(CHILD_RENBEAN);
        Log.i(TAG, "" + childrenBean.toString());
    }

    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showSnackBar(String msg) {

    }

    public void showKnowledgeDetail(KnowledgeListBean data) {
        int curPage = data.getCurPage();
        pageNum = curPage - 1;
        if (pageNum == 0) {
            listDetailAdapter.replaceData(data.getDatas());
            srl.finishRefresh();
        } else {
            if (data.getDatas() == null || data.getDatas().size() == 0) {
                srl.finishLoadMoreWithNoMoreData();
                return;
            }
            listDetailAdapter.addData(data.getDatas());
            srl.finishLoadMore();
        }
    }
}
