package com.code19.appmanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.code19.appmanager.adapter.TabPagerAdapter;
import com.code19.appmanager.utils.ViewUtils;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tl_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                ViewUtils.openDialog(this);
                break;
            case R.id.action_about:
                ViewUtils.aboutDialog(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
