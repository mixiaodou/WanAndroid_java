package com.example.lib_comon.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地配置信息 帮助类
 * 对SharedPreferences封装
 */
public class ConfigureHelper {
    private static ConfigureHelper mConfigureHelper;


    private ConfigureHelper() {
    }

    private static SharedPreferences mSP;

    public static void init(Context context) {
        if (context != null && mSP == null) {
            mSP = context.getSharedPreferences("ConfigureHelper", Context.MODE_PRIVATE);
        }
    }

    public static ConfigureHelper getInstance() {
        if (mConfigureHelper == null) {
            synchronized (ConfigureHelper.class) {
                if (mConfigureHelper == null) {
                    mConfigureHelper = new ConfigureHelper();
                }
            }
        }
        return mConfigureHelper;
    }

    public void putString(String k, String s) {
        SharedPreferences.Editor edit = mSP.edit();
        edit.putString(k, s);
        //edit.commit();//commit 会阻塞当前线程
        edit.apply();//开启异步线程 保存到本地
    }

    public String getString(String k) {
        String v = mSP.getString(k, "");
        return v;
    }
}
