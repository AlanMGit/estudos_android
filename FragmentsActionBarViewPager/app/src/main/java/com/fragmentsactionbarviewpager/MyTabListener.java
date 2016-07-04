package com.fragmentsactionbarviewpager;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

/**
 * Created by Alan on 04/07/2016.
 */
public class MyTabListener implements ActionBar.TabListener {

    private ViewPager mViewPager;
    private int idx;

    public MyTabListener(ViewPager mViewPager, int idx) {
        this.mViewPager = mViewPager;
        this.idx = idx;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Navega para pagina desejada do viewpager
        mViewPager.setCurrentItem(idx);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
