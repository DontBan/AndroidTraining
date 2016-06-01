package com.gmail.noraware29.myfragmentviewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    ViewPager mViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* タブナビゲーションにする*/
        ActionBar ab= getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Page0").setTabListener(this));
        ab.addTab(ab.newTab().setText("Page1").setTabListener(this));
        ab.addTab(ab.newTab().setText("Page2").setTabListener(this));


        /* ViewPagerをつくる */
        mViewPager = (ViewPager) findViewById(R.id.Pager);
//        ViewPager pager = (ViewPager) findViewById(R.id.Pager);

        FragmentManager fm = getSupportFragmentManager();
        SampleFragmentPagerAdapter sampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(fm);
        mViewPager.setAdapter(sampleFragmentPagerAdapter);
//        pager.setAdapter(sampleFragmentPagerAdapter);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        /* 選択されたタブに対応するページに移動する */
        /* 選択されたタブは何番か？ */
        int tabPosition = tab.getPosition();
        if (tabPosition == tab.INVALID_POSITION) {
            tabPosition = 0;
        }
        /* ページを移動する */
        if (mViewPager != null) {
            mViewPager.setCurrentItem(tabPosition);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
