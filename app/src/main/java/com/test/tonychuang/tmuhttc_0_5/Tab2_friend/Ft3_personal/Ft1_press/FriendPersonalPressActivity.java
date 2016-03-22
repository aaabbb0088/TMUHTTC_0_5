package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press.VP1_fourdaydata.FriendPersonalPressFourDayFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press.VP2_draw.FriendPersonalPressDrawFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press.VP3_table.FriendPersonalPressTableFragment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class FriendPersonalPressActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private static final int TAB_COUNT = 3;

    private ActionBar actionBar;
    private MyInitReturnBar myInitReturnBar;
    private String[] tabs = {"近期血壓", "血壓趨勢圖", "血壓紀錄"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal_press);

        initViews();
        initBar();
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "會員暱稱", 2);
        actionBar = myInitReturnBar.getActionBar();

        if (actionBar != null) {
            // Create a tab listener that is called when the user changes tabs.
            ActionBar.TabListener tabListener = new ActionBar.TabListener() {
                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                    // show the given tab
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                    // hide the given tab
                }

                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                    // probably ignore this event
                }
            };

            // Add 2 tabs, specifying the tab's text and TabListener
            actionBar.removeAllTabs();
            for (int i = 0; i < TAB_COUNT; i++) {
                actionBar.addTab(actionBar.newTab().setText(tabs[i]).setTabListener(tabListener));
            }
        }
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between pages, select the
                // corresponding tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FriendPersonalPressFourDayFragment();
                case 1:
                    return new FriendPersonalPressDrawFragment();
                case 2:
                    return new FriendPersonalPressTableFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }


    /**
     * v2
     */
    /**
     *
     */



    /**
     * d1
     */
    /**
     *
     */



    /**
     * d2
     */
    /**
     *
     */

}
