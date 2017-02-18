package com.code19.appmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code19.appmanager.AppModel;
import com.code19.appmanager.AppRecyAdapter;
import com.code19.appmanager.AppUtil;
import com.code19.appmanager.R;
import com.code19.appmanager.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gh0st on 2017/2/17.
 */

public class UserAppFragment extends Fragment {
    private List<AppModel> mListData;
    private boolean mIsSystem = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mIsSystem = bundle.getBoolean("isSystem");
        }
        mListData = new ArrayList<>();
        List<AppModel> installApp = AppUtil.getInstallApp(getActivity());
        for (AppModel appModel : installApp) {
            if (mIsSystem) {
                if (appModel.isSystem()) {
                    mListData.add(appModel);
                }
            } else {
                if (!appModel.isSystem()) {
                    mListData.add(appModel);
                }
            }
        }
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
            public void onItemClick(int position, View view) {
                ViewUtils.showAppPopup(getActivity(), mListData.get(position), recylist.getChildAt(position).findViewById(R.id.recy_app_name));
            }
        });
        recylist.setAdapter(adapter);
        return view;
    }
}
