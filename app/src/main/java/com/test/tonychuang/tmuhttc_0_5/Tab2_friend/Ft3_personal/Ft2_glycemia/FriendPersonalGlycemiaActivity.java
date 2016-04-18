package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia;

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
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP1_fourdaydata.FriendPersonalGlycemiaFourDayFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP2_draw.FriendPersonalGlycemiaDrawFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP3_table.FriendPersonalGlycemiaTableFragment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class FriendPersonalGlycemiaActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private static final int TAB_COUNT = 3;

    private ActionBar actionBar;
    private MyInitReturnBar myInitReturnBar;
    private String[] tabs = {"近期血糖", "血糖趨勢圖", "血糖紀錄"};

    public static String friAid;
    public static String friSid;
    private String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal_glycemia);

        friAid = this.getIntent().getExtras().getString("friAid");
        friSid = this.getIntent().getExtras().getString("friSid");
        nickName = this.getIntent().getExtras().getString("nickName");
        initViews();
        initBar(nickName);

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

    private void initBar(String nickName) {
        myInitReturnBar = new MyInitReturnBar(this, "關心 " + nickName + " 的血糖", 2);
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
                    return new FriendPersonalGlycemiaFourDayFragment();
                case 1:
                    return new FriendPersonalGlycemiaDrawFragment();
                case 2:
                    return new FriendPersonalGlycemiaTableFragment();
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
