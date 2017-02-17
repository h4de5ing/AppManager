package com.code19.appmanager.fragment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.code19.appmanager.AppModel;
import com.code19.appmanager.AppRecyAdapter;
import com.code19.appmanager.R;
import com.code19.appmanager.ViewUtils;
import com.code19.library.AppUtils;
import com.code19.library.DateUtils;
import com.code19.library.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh0st on 2017/2/17.
 */

public class UserAppFragment extends Fragment {
    private List<AppModel> mListData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListData = getAppData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userapp, null);
        final RecyclerView recylist = (RecyclerView) view.findViewById(R.id.recy_list);
        recylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        AppRecyAdapter adapter = new AppRecyAdapter(getActivity(), mListData);
        adapter.setOnClickListener(new AppRecyAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), mListData.get(position).getAppName(), Toast.LENGTH_SHORT).show();
                ViewUtils.showAppPopup(getActivity(), mListData.get(position));
            }
        });
        recylist.setAdapter(adapter);
        return view;
    }

    private List<AppModel> getAppData() {
        List<AppModel> appDatas = new ArrayList<AppModel>();
        PackageManager pm = getActivity().getPackageManager();
        List<PackageInfo> infoList = pm.getInstalledPackages(0);
        for (PackageInfo info : infoList) {
            AppModel appModel = new AppModel();
            String appName = AppUtils.getAppName(getActivity(), info.packageName);
            Drawable appIcon = AppUtils.getAppIcon(getActivity(), info.packageName);
            String appDate = DateUtils.formatDate(AppUtils.getAppDate(getActivity(), info.packageName));
            String appSize = FileUtils.formatFileSize(getActivity(), AppUtils.getAppSize(getActivity(), info.packageName));
            appModel.setAppName(appName);
            appModel.setAppIcon(appIcon);
            appModel.setAppDate(appDate);
            appModel.setAppSize(appSize);
            appModel.setAppPack(info.packageName);
            appModel.setAppApk(getAppApk(getActivity(), info.packageName));
            if (!TextUtils.isEmpty(appName) && !TextUtils.isEmpty(appDate) && !TextUtils.isEmpty(appSize)) {
                appDatas.add(appModel);
            }
        }
        return appDatas;
    }

    public static String getAppApk(Context context, String packageName) {
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }
}
