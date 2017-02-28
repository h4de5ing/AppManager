package com.code19.appmanager.base;

import android.app.Application;


/**
 * Created by gh0st on 2017/2/18.
 */

public class App extends Application {
    private static App sApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static App getInstance() {
        return sApp;
    }
}
