package com.brucej.wanandroid_java;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_comon.app.ConfigureHelper;

public class WanAndroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConfigureHelper.init(this);

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
