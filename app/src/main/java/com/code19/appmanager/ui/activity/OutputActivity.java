package com.code19.appmanager.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;
import com.code19.appmanager.R;
import com.code19.appmanager.model.AppModel;
import com.code19.appmanager.utils.FileUtils2;
import com.code19.appmanager.utils.GetAppAsyncTask;
import com.code19.library.DateUtils;
import com.code19.library.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OutputActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 10086;
    private TextView tvResult;
    private List<AppModel> mListData;
    private boolean hasSystem = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        mListData = new ArrayList<>();
        Button button = (Button) findViewById(R.id.btn_output_all);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        CheckBox cbSystem = (CheckBox) findViewById(R.id.cb_system);
        button.setOnClickListener(this);
        hasSystem = cbSystem.isChecked();
        cbSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hasSystem = isChecked;
            }
        });
        GetAppAsyncTask getAppAsyncTask = new GetAppAsyncTask(this, new GetAppAsyncTask.CallBack() {
            @Override
            public void send(List<AppModel> result) {
                mListData.clear();
                mListData.addAll(result);
            }
        });
        getAppAsyncTask.execute();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && requestCode == REQUEST_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(OutputActivity.this, "Thanks for you granted permissions!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(OutputActivity.this, "The application may crash without permissions!", Toast.LENGTH_LONG).show();
        }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                            FileUtils2.copy(appModel.getAppApk(), FileUtils2.getApkFilePath(OutputActivity.this) + appModel.getAppPack() + ".apk", false);
                            updateUI(appModel.getAppName() + ":" + appModel.getAppPack() + "--success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            updateUI(appModel.getAppName() + "--exception");
                        }
                    } else {
                        if (!appModel.isSystem()) {
                            try {
                                FileUtils2.copy(appModel.getAppApk(), FileUtils2.getApkFilePath(OutputActivity.this) + appModel.getAppPack() + ".apk", false);
                                updateUI(appModel.getAppName() + ":" + appModel.getAppPack() + "--success");
                            } catch (Exception e) {
                                e.printStackTrace();
                                updateUI(appModel.getAppName() + "--exception");
                            }
                        }
                    }
                    if (i == (mListData.size() - 1)) {
                        updateUI("-------------导出完毕-------------");
                        writeLog();
                    }
                }
            }
        }).start();
    }

    private void updateUI(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvResult != null) {
                    tvResult.append(string + "\n");
                    int offset = tvResult.getLineCount() * tvResult.getLineHeight() - tvResult.getHeight();
                    tvResult.scrollTo(0, offset > 0 ? offset : 0);
                }
            }
        });
    }

    private void writeLog() {
        FileUtils.writeFile("/sdcard/" + DateUtils.getDateTime() + "_outapk.xml", tvResult.getText().toString(), false);
    }
}
