package com.brucej.wanandroid_java.ui.knowledge;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brucej.wanandroid_java.R;
import com.brucej.wanandroid_java.ui.knowledge.beans.KnowledgeBean;
import com.brucej.wanandroid_java.ui.knowledge.adapters.KnowledgeAdapter;
import com.brucej.wanandroid_java.ui.knowledge.knowledgedetail.KnowledgeDetailActivity;
import com.example.lib_comon.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeFrament extends
        BaseFragment<KnowledgePresenter, KnowledgeIView, KnowLedgeModel>
        implements KnowledgeIView {

    @BindView(R.id.fragment_knowledge_srl)
    public SmartRefreshLayout srlView;
    @BindView(R.id.fragment_knowledge_recyc)
    public RecyclerView recycView;
    //
    private ArrayList<KnowledgeBean> knowledgeList;
    private KnowledgeAdapter knowledgeAdapter;

    public static KnowledgeFrament getInstance() {
        KnowledgeFrament frament = new KnowledgeFrament();
        return frament;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        mPresenter.attachView(this);
        mPresenter.setModel(new KnowLedgeModel());
        pageState.init(srlView, () -> {
            mPresenter.getKnowledgeData();
        });
        //initSRL
        initSRL();
        initRecyc();
        srlView.autoRefresh();
    }

    private void initRecyc() {
        knowledgeList = new ArrayList<>();
        knowledgeAdapter = new KnowledgeAdapter(R.layout.item_knowledge, knowledgeList);

        recycView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recycView.setAdapter(knowledgeAdapter);
        knowledgeAdapter.setOnItemClickListener((adapter, view, position) -> {
            KnowledgeBean bean = knowledgeList.get(position);
            KnowledgeDetailActivity.skip(getContext(), bean.getChildren(), bean.getName());
        });
    }

    private void initSRL() {
        srlView.setEnableRefresh(true);
        srlView.setEnableLoadMore(false);
        srlView.setEnableAutoLoadMore(false);
        srlView.setOnRefreshListener(refreshLayout -> {
            mPresenter.getKnowledgeData();
        });
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

    @Override
    public void showKnowledgeView(List<KnowledgeBean> list) {
        pageState.showNormal();
        knowledgeAdapter.addData(list);
        srlView.finishRefresh();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadError() {
        pageState.showError();
    }

}
