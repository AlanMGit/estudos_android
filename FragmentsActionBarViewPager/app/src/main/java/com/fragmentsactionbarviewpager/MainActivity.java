package com.fragmentsactionbarviewpager;

import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));

        //Configura Tabs
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Frag 1").setTabListener(new MyTabListener(mViewPager, 0)));

        actionBar.addTab(actionBar.newTab().setText("Frag 2").setTabListener(new MyTabListener(mViewPager, 1)));

        actionBar.addTab(actionBar.newTab().setText("Frag 3").setTabListener(new MyTabListener(mViewPager, 2)));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
