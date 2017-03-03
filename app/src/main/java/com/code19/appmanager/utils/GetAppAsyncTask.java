package com.code19.appmanager.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.code19.appmanager.R;
import com.code19.appmanager.model.AppModel;

import java.util.List;

/**
 * Created by gh0st on 2017/2/21.
 */

public class GetAppAsyncTask extends AsyncTask<Void, Integer, List<AppModel>> {
    private Context mContext;
    private CallBack mCallback;
    private ProgressDialog mProgressDialog;

    public GetAppAsyncTask(Context context, CallBack callBack) {
        this.mContext = context;
        this.mCallback = callBack;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage(context.getString(R.string.loading));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressDialog != null)
            mProgressDialog.show();
    }

    @Override
    protected List<AppModel> doInBackground(Void... voids) {
        return AppUtil2.getInstallApp(mContext);
    }

    @Override
    protected void onPostExecute(List<AppModel> appModels) {
        super.onPostExecute(appModels);
        mCallback.send(appModels);
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    public interface CallBack {
        void send(List<AppModel> result);
    }
}
