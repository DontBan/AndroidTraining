package com.gmail.noraware29.myfragmentviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by KOBAYASHI Tomohiro on 16/05/31.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    @SuppressWarnings("unused")
    private static final String TAG = SampleFragmentPagerAdapter.class.getSimpleName();

    private static final int PAGE_COUNT = 3;

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SampleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
