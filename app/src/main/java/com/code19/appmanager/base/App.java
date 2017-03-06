package com.code19.appmanager.base;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by gh0st on 2017/2/18.
 */

public class App extends Application {
    private static App sApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        CrashReport.initCrashReport(getApplicationContext(), "7c33bdd2-8fa5-4f18-b974-b6bba0d6f42e", false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static App getInstance() {
        return sApp;
    }
}
