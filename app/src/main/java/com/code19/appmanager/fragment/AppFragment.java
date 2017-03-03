package com.code19.appmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code19.appmanager.R;
import com.code19.appmanager.adapter.AppRecyAdapter;
import com.code19.appmanager.model.AppModel;
import com.code19.appmanager.utils.GetAppAsyncTask;
import com.code19.appmanager.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh0st on 2017/2/17.
 */

public class AppFragment extends Fragment {
    private List<AppModel> mListData;
    private int mPosition = 0;
    private AppRecyAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userapp, null);
        RecyclerView recylist = (RecyclerView) view.findViewById(R.id.recy_list);
        recylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListData = new ArrayList<>();
        mAdapter = new AppRecyAdapter(getActivity(), mListData);
        recylist.setAdapter(mAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        GetAppAsyncTask getAppAsyncTask = new GetAppAsyncTask(getActivity(), new GetAppAsyncTask.CallBack() {
            @Override
            public void send(List<AppModel> result) {
                for (AppModel appModel : result) {
                    switch (mPosition) {
                        case 0:
                            if (!appModel.isSystem())
                                mListData.add(appModel);
                            break;
                        case 1:
                            if (appModel.isSystem())
                                mListData.add(appModel);
                            break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        getAppAsyncTask.execute();
        mAdapter.setOnClickListener(new AppRecyAdapter.OnClickListener() {
            @Override
            public void onItemClick(final int position, View view) {
                ViewUtils.appInfoDialog(getActivity(), mListData.get(position));
            }
        });
        return view;
    }
}
