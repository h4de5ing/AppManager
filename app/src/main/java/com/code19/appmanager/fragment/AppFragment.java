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
import com.code19.appmanager.utils.AppUtil2;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        mListData = new ArrayList<>();
        List<AppModel> appModels = AppUtil2.getInstallApp(getActivity());
        for (AppModel appModel : appModels) {
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
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userapp, null);
        final RecyclerView recylist = (RecyclerView) view.findViewById(R.id.recy_list);
        recylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AppRecyAdapter(getActivity(), mListData);
        mAdapter.setOnClickListener(new AppRecyAdapter.OnClickListener() {
            @Override
            public void onItemClick(final int position, View view) {
                ViewUtils.appInfoDialog(getActivity(), mListData.get(position));
            }
        });
        recylist.setAdapter(mAdapter);
        return view;
    }
}
