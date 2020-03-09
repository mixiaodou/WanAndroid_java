package com.brucej.wanandroid_java.ui.main.activitys;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_comon.base.BaseActivity;
import com.example.lib_comon.utils.StatusBarUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.permisson.PermissonUtil;
import com.brucej.wanandroid_java.R;
import com.brucej.wanandroid_java.ui.home.HomeFragment;
import com.brucej.wanandroid_java.ui.knowledge.KnowledgeFrament;

import butterknife.BindView;

@Route(path = "app/MainActivity")
public class MainActivity extends BaseActivity<MainPresenter, MainIView, MainModel> implements MainIView {

    private final String TAG = "MainActivity--";
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.bottomNav_main)
    BottomNavigationView bottomNav;
    @BindView(R.id.leftNav_main)
    NavigationView leftNav;
    @BindView(R.id.layout_toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_toolbar_tittle)
    TextView tittleTv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initDataAndEvent(Bundle instanceState) {
        mPresenter.attachView(this);
        mPresenter.setModel(new MainModel());
        //toolbar
        initToolbar();
        //drawerLayout
        initDrawerLayout();
        //leftNav
        initLeftNav();
        //bottomNav
        initBottomNav();
        //
        bottomNav.setSelectedItemId(R.id.nav_main_home);
        //
        StatusBarUtil.setColor(this, Color.parseColor("#00abff"));

    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        /*todo 代码设置 scrollFlags
        app:layout_scrollFlags="scroll|enterAlways"
        * */
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams)
                toolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setNavigationIcon(R.drawable.ic_menu_white);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawerLayout.addDrawerListener(toggle);
    }

    private void initLeftNav() {
        leftNav.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.left_nav_wan_android:
                    break;
                case R.id.left_nav_collect:
                    break;
                case R.id.left_nav_setting:
                    break;
                case R.id.left_nav_about_us:
                    break;
                case R.id.left_nav_login_out:
                    break;
            }
            closeDrawer();
            return true;
        });
    }

    private void initBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            /*switch (menuItem.getItemId()) {
                case R.id.nav_main_home:
                    break;
                case R.id.nav_main_knowledge:
                    break;
                case R.id.nav_main_me:
                    break;
                case R.id.nav_main_navigation:
                    break;
                case R.id.nav_main_project:
                    break;
            }*/
            switchFragment(menuItem.getItemId());
            return true;
        });
    }

    private SparseArray<Fragment> fragments = new SparseArray<>();
    private int lastFragmentId = -1;

    private void switchFragment(int id) {
        if (lastFragmentId == id) {
            return;
        }
        Fragment fragment = fragments.get(id);
        switch (id) {
            case R.id.nav_main_home:
                if (fragment == null) {
                    fragment = HomeFragment.getInstance();
                    fragments.put(id, fragment);
                }
                switchTittle("首页");
                break;
            case R.id.nav_main_knowledge:
                if (fragment == null) {
                    fragment = KnowledgeFrament.getInstance();
                    fragments.put(id, fragment);
                }
                switchTittle("知识");
                break;
            case R.id.nav_main_me:
                switchTittle("公众号");
                break;
            case R.id.nav_main_navigation:
                switchTittle("导航");
                break;
            case R.id.nav_main_project:
                switchTittle("项目");
                break;
        }
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (!fragment.isAdded()) {
                transaction.add(R.id.frame_main, fragment);
            }
            if (lastFragmentId != -1) {
                Fragment lastFragment = fragments.get(lastFragmentId);
                transaction.hide(lastFragment);
            }
            lastFragmentId = id;
            transaction.show(fragment);
            transaction.commit();
            Log.i(TAG, "transaction.commit()");
        }
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
    protected void onDestroy() {
        if (fragments != null) {
            fragments.clear();
        }
        super.onDestroy();
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.LEFT, true);
    }

    @Override
    public void switchTittle(String tittle) {
        if (!TextUtils.isEmpty(tittle)) {
            tittleTv.setText(tittle);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        PermissonUtil.getInstance().requestPermissons(this,
                (b, strings) -> {
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissonUtil.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载 选项菜单
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    //选项菜单 监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}
