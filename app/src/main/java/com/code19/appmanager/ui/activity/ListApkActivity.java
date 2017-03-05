package com.code19.appmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.code19.appmanager.R;

public class ListApkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_app);
        RecyclerView recylist = (RecyclerView) findViewById(R.id.recy_list);
    }
}
