package com.fragmentsactionbar;


import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

/**
 * Created by Alan on 04/07/2016.
 */
public class MyTabListener implements ActionBar.TabListener {
    private Context context;
    private Fragment frag;

    public MyTabListener(Context context, Fragment frag) {
        this.context = context;
        this.frag = frag;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        ft.replace(R.id.layout_frag, frag, null);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}
