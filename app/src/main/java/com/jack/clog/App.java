package com.jack.clog;

import android.app.Application;

import cn.jack.lib.toast.CToast;

/**
 * Created by manji
 * Date：2018/10/23 上午9:51
 * Desc：
 */
public class App extends Application {

    private static App mInstance;

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CToast.init(this);
    }
}
