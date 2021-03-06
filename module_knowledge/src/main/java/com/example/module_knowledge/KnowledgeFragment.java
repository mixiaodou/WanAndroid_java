package com.example.module_knowledge;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lib_comon.base.BaseFragment;
import com.example.module_knowledge.adapters.KnowledgeAdapter;
import com.example.module_knowledge.beans.KnowledgeBean;
import com.example.module_knowledge.knowledgedetail.KnowledgeDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
@Route(path = "/knowledge/KnowledgeFragment")
public class KnowledgeFragment extends
        BaseFragment<KnowledgePresenter, KnowledgeIView, KnowLedgeModel>
        implements KnowledgeIView {

    @BindView(R2.id.fragment_knowledge_srl)
    public SmartRefreshLayout srlView;
    @BindView(R2.id.fragment_knowledge_recyc)
    public RecyclerView recycView;
    //
    private ArrayList<KnowledgeBean> knowledgeList;
    private KnowledgeAdapter knowledgeAdapter;

    public static KnowledgeFragment getInstance() {
        KnowledgeFragment frament = new KnowledgeFragment();
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
