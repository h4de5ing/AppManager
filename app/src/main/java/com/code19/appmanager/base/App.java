package com.code19.appmanager.base;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by gh0st on 2017/2/18.
 */

public class App extends Application {
    private static App sApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public static App getInstance() {
        return sApp;
    }
}
