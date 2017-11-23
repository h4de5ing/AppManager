package com.code19.appmanager.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.code19.appmanager.R;
import com.code19.appmanager.model.AppModel;
import com.code19.appmanager.utils.FileUtils2;
import com.code19.appmanager.utils.GetAppAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class OutAppAPKFragment extends Fragment implements View.OnClickListener {
    private TextView tvResult;
    private List<AppModel> mListData;
    private CheckBox cbSystem;
    private boolean hasSystem = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_output, null);
        mListData = new ArrayList<>();
        Button button = (Button) view.findViewById(R.id.btn_output_all);
        tvResult = (TextView) view.findViewById(R.id.tv_result);
        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        cbSystem = (CheckBox) view.findViewById(R.id.cb_system);
        button.setOnClickListener(this);
        hasSystem = cbSystem.isChecked();
        cbSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hasSystem = isChecked;
            }
        });
        GetAppAsyncTask getAppAsyncTask = new GetAppAsyncTask(getActivity(), new GetAppAsyncTask.CallBack() {
            @Override
            public void send(List<AppModel> result) {
                mListData.clear();
                mListData.addAll(result);
            }
        });
        getAppAsyncTask.execute();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_output_all:
                alertDialog();
                break;
        }
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("你确定要全部导出吗？");
        builder.setMessage("系统里面APK众多,你确定需要导出所有APk吗？这个过程花费教长时间,请耐心等待...");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                outputAllApk();
            }
        });
        builder.show();
    }

    private void outputAllApk() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mListData.size(); i++) {
                    AppModel appModel = mListData.get(i);
                    if (hasSystem) {
                        try {
                            FileUtils2.copy(appModel.getAppApk(), FileUtils2.getApkFilePath(getActivity()) + appModel.getAppPack() + ".apk", false);
                            tvResult.append(appModel.getAppName() + ":" + appModel.getAppPack() + ".success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            tvResult.append(appModel.getAppName() + ":" + appModel.getAppPack() + ".exception");
                        }
                    } else {
                        if (!appModel.isSystem()) {
                            try {
                                FileUtils2.copy(appModel.getAppApk(), FileUtils2.getApkFilePath(getActivity()) + appModel.getAppPack() + ".apk", false);
                                tvResult.append(appModel.getAppName() + ":" + appModel.getAppPack() + ".success");
                            } catch (Exception e) {
                                e.printStackTrace();
                                tvResult.append(appModel.getAppName() + ":" + appModel.getAppPack() + ".exception");
                            }
                        }
                    }
                    if (i == mListData.size()) {
                        tvResult.append("导出完毕");
                    }
                }
            }
        }).start();
    }
}
