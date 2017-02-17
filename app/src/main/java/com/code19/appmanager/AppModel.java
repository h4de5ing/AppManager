package com.code19.appmanager;

import android.graphics.drawable.Drawable;

/**
 * Created by gh0st on 2017/2/17.
 */

public class AppModel {
    private Drawable icon;
    private String appName;
    private String appSize;
    private String appDate;
    private String appApk;

    public String getAppPack() {
        return appPack;
    }

    public void setAppPack(String appPack) {
        this.appPack = appPack;
    }

    private String appPack;

    public Drawable getAppIcon() {
        return icon;
    }

    public void setAppIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppApk() {
        return appApk;
    }

    public void setAppApk(String appApk) {
        this.appApk = appApk;
    }
}
