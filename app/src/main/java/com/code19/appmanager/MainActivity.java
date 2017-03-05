package com.code19.appmanager;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;

import com.code19.appmanager.adapter.TabPagerAdapter;
import com.code19.appmanager.ui.activity.ListApkActivity;
import com.code19.appmanager.utils.ViewUtils;
import com.code19.library.DensityUtil;
import com.code19.library.L;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private long mLastSearchTime;

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
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(DensityUtil.getScreenW(this));
        searchView.setInputType(InputType.TYPE_CLASS_TEXT);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (System.currentTimeMillis() - mLastSearchTime > 00) {
                    L.i("搜索的应用名：" + query);
                    mLastSearchTime = System.currentTimeMillis();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_apk:
                startActivity(new Intent(MainActivity.this, ListApkActivity.class));
                break;
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
