package com.bobo.myyoulu.app;

import android.app.Application;

/**
 * Created by QuBo on 2016/10/25.
 *
 */
public class MyApplication extends Application {
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
    public static MyApplication getApp() {
        return app;
    }
}
