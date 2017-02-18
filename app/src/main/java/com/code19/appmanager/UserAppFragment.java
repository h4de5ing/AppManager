package com.code19.appmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.code19.library.AppUtils;
import com.code19.library.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh0st on 2017/2/17.
 */

public class UserAppFragment extends Fragment {
    private List<AppModel> mListData;
    private int mPosition = 0;
    private AppRecyAdapter mAdapter;
    private String[] mApp_nav;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        if (mPosition == 2) {
            mApp_nav = getResources().getStringArray(R.array.app2_nav);
        } else {
            mApp_nav = getResources().getStringArray(R.array.app_nav);
        }
        mListData = new ArrayList<>();
        List<AppModel> installApp = AppUtil2.getInstallApp(getActivity());
        for (AppModel appModel : installApp) {
            switch (mPosition) {
                case 0:
                    if (!appModel.isSystem())
                        mListData.add(appModel);
                    break;
                case 1:
                    if (appModel.isSystem())
                        mListData.add(appModel);
                    break;
                case 2:
                    if (appModel.isCollection())
                        mListData.add(appModel);
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userapp, null);
        final RecyclerView recylist = (RecyclerView) view.findViewById(R.id.recy_list);
        recylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AppRecyAdapter(getActivity(), mListData);
        mAdapter.setOnClickListener(new AppRecyAdapter.OnClickListener() {
            @Override
            public void onItemClick(final int position, View view) {
                ViewUtils.listDialog(getActivity(), mApp_nav, new OnDialogItemSelected() {
                    @Override
                    public void onItemSelected(int navposition) {
                        AppModel bean = mListData.get(position);
                        switch (navposition) {
                            case 0:
                                if (bean.isSystem()) {
                                    AppUtil2.openApp(getActivity(), bean.getAppPack());
                                } else {
                                    AppUtils.runApp(getActivity(), bean.getAppPack());
                                }
                                break;
                            case 1:
                                if (mPosition == 2) {
                                    FileUtils.shareFile(getActivity(), "分享APK", bean.getAppApk());
                                } else {
                                    AppUtils.uninstallApk(getActivity(), bean.getAppPack());
                                }
                                break;
                            case 2:
                                if (mPosition == 2) {
                                    FileUtils2.delApkFile(getActivity(), bean.getAppPack());
                                } else {
                                    AppUtil2.collection(getActivity(), bean);
                                    Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                AppUtil2.viewAppInfo(getActivity(), bean.getAppPack());
                                break;
                        }
                    }
                });
            }
        });
        recylist.setAdapter(mAdapter);
        return view;
    }
}
