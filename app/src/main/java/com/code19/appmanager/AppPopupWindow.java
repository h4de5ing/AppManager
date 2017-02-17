package com.code19.appmanager;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gh0st on 2017/2/17.
 */

public class AppPopupWindow extends Dialog implements View.OnClickListener {
    private AppModel mAppModel;

    public AppPopupWindow(Context context, AppModel appModel) {
        super(context);
        View view = View.inflate(context, R.layout.popwin_app_layout, null);
        TextView tvOpen = (TextView) view.findViewById(R.id.pop_tv_open);
        TextView tvUninstall = (TextView) view.findViewById(R.id.pop_tv_uninstall);
        TextView tvCollection = (TextView) view.findViewById(R.id.pop_tv_collection);
        TextView tvInfo = (TextView) view.findViewById(R.id.pop_tv_info);
        tvOpen.setOnClickListener(this);
        tvUninstall.setOnClickListener(this);
        tvCollection.setOnClickListener(this);
        tvInfo.setOnClickListener(this);
        this.mAppModel = appModel;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pop_tv_open:
                mAppModel.getAppName();
                break;
            case R.id.pop_tv_uninstall:
                break;
            case R.id.pop_tv_collection:
                break;
            case R.id.pop_tv_info:
                break;

        }
    }
}
