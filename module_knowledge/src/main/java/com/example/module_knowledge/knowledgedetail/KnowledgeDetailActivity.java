package com.example.module_knowledge.knowledgedetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lib_comon.base.BaseActivity;
import com.example.module_knowledge.R;
import com.example.module_knowledge.R2;
import com.example.module_knowledge.beans.KnowledgeBean;
import com.example.module_knowledge.knowledgedetail.fragments.KnowledgeDetailFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailPresenter, KnowledgeDetailIView, KnowledgeDetailModel>
        implements KnowledgeDetailIView {
    private String TAG = "KnowledgeDetailActivity--";
    @BindView(R2.id.activity_knowledge_detail_tab)
    public TabLayout tabLayout;
    @BindView(R2.id.activity_knowledge_detail_vp)
    public ViewPager vp;
    @BindView(R2.id.layout_toolbar)
    public Toolbar toolbar;
    @BindView(R2.id.layout_toolbar_tittle)
    public TextView toolbarTv;
    //
    private ArrayList<KnowledgeBean.ChildrenBean> childrenBeanList;
    private String tittle;
    private static final String TITTLE = "tittle";
    private static final String LIST = "list";

    public static void skip(Context context, ArrayList<KnowledgeBean.ChildrenBean> childrenBeanList, String tittle) {
        Intent intent = new Intent(context, KnowledgeDetailActivity.class);
        intent.putParcelableArrayListExtra(LIST, childrenBeanList);
        intent.putExtra(TITTLE, tittle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_detail;
    }

    @Override
    protected KnowledgeDetailPresenter createPresenter() {
        return new KnowledgeDetailPresenter();
    }

    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        mPresenter.attachView(this);
        mPresenter.setModel(new KnowledgeDetailModel());
        //
        initIntent();
        initToolbar();
        setTittle(tittle);
        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //标签选中指示器 位置和样式
        tabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        //标签字体 两种颜色
        tabLayout.setTabTextColors(R.color.gray, R.color.white);
        tabLayout.setUnboundedRipple(true);
        //添加Tab，TabLayout+ViewPager Tab自动创建
//        for (int i = 0; i < childrenBeanList.size(); i++) {
//            KnowledgeBean.ChildrenBean bean = childrenBeanList.get(i);
//            TabLayout.Tab tab = tabLayout.newTab();
//            tab.setText(bean.getName());
//            tabLayout.addTab(tab, i);
//
//        }
        //-----------
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), childrenBeanList);
        vp.setAdapter(vpAdapter);
        //设置tabLayout支持ViewPager
        tabLayout.setupWithViewPager(vp, true);
        //改变Tab形式的样式
        tabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int tabCount = tabLayout.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    invokeTabTextView(tabLayout, i);
                }
                if (tabCount <= 3) {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                } else {
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
            }
        }, 300);
    }

    private void initIntent() {
        childrenBeanList = getIntent().getParcelableArrayListExtra("list");
        tittle = getIntent().getStringExtra(TITTLE);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setTittle(String tittle) {
        toolbarTv.setText(tittle);
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

    //TabLayout.Tab 中的textView 设置所有字母大写，通过反射设置为允许小写;
    private void invokeTabTextView(TabLayout tabLayout, int index) {
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        try {
            //Class<? extends TabLayout.Tab> tabClass = tab.getClass();
            //Class<?> tabViewClass = tabClass.getDeclaredField("view").getType();
            //内部类 需要加$
            Class<?> tabViewClass =
                    Class.forName("android.support.design.widget.TabLayout$TabView");
            Log.i(TAG, "tabViewClass=" + tabViewClass);
            Field textViewField = tabViewClass.getDeclaredField("textView");
            //
            Object tabViewObj = tab.view;
            textViewField.setAccessible(true);
            TextView tv = (TextView) textViewField.get(tabViewObj);
            tv.setAllCaps(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private class VPAdapter extends FragmentPagerAdapter {
        private List<KnowledgeBean.ChildrenBean> childrenBeanList;
        private SparseArray<KnowledgeDetailFragment> fragmentList;

        public VPAdapter(FragmentManager fm, List<KnowledgeBean.ChildrenBean> childrenBeanList) {
            super(fm);
            this.childrenBeanList = childrenBeanList;
            this.fragmentList = new SparseArray<>();
        }

        /*public VPAdapter(FragmentManager fm) {
            super(fm);
        }*/

        @Override
        public Fragment getItem(int i) {
            KnowledgeBean.ChildrenBean bean = childrenBeanList.get(i);
            KnowledgeDetailFragment fragment = fragmentList.get(i);
            if (fragment == null) {
                fragment = KnowledgeDetailFragment.getInstance(bean);
                fragmentList.put(i, fragment);

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return childrenBeanList.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            KnowledgeBean.ChildrenBean bean = childrenBeanList.get(position);
            return bean.getName();
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }
    }
}
