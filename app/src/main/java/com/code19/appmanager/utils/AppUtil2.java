package com.code19.appmanager.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.code19.appmanager.model.AppModel;
import com.code19.library.AppUtils;
import com.code19.library.DateUtils;
import com.code19.library.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh0st on 2017/2/18.
 */

public class AppUtil2 {
    public static List<AppModel> getInstallApp(Context context) {
        List<AppModel> appDatas = new ArrayList<AppModel>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infoList = pm.getInstalledPackages(0);
        for (PackageInfo info : infoList) {
            AppModel appModel = new AppModel();
            String appName = AppUtils.getAppName(context, info.packageName);
            Drawable appIcon = AppUtils.getAppIcon(context, info.packageName);
            String appDate = DateUtils.formatDate(AppUtils.getAppDate(context, info.packageName));
            String appSize = FileUtils.formatFileSize(context, AppUtils.getAppSize(context, info.packageName));
            appModel.setAppName(appName);
            appModel.setAppIcon(appIcon);
            appModel.setAppDate(appDate);
            appModel.setAppSize(appSize);
            appModel.setAppPack(info.packageName);
            appModel.setAppApk(getAppApk(context, info.packageName));
            int flags = info.applicationInfo.flags;
            if ((flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                appModel.setSystem(true);
            } else {
                appModel.setSystem(false);
            }
            if (!TextUtils.isEmpty(appName) && !TextUtils.isEmpty(appDate) && !TextUtils.isEmpty(appSize)) {
                appDatas.add(appModel);
            }
            File file = new File(FileUtils2.getApkFilePath(context) + info.packageName + ".apk");
            appModel.setCollection(file.exists());
        }
        return appDatas;
    }

    private static String getAppApk(Context context, String packageName) {
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }

    //启动应用
    public static void openApp(Context context, String packname) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packname);
        if (intent == null) {
            Toast.makeText(context, "无法启动应用", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent);
    }


    //查看应用信息
    public static void viewAppInfo(Context context, String packname) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:" + packname));
        context.startActivity(intent);
    }
}
