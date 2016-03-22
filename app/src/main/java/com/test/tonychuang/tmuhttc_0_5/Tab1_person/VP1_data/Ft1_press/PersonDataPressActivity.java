package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press;

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
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP1_fourdaydata.PersonDataPressFourDayFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP2_draw.PersonDataSearchDrawPressFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP3_table.PersonDataSearchTablePressFragment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class PersonDataPressActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private static final int TAB_COUNT = 3;

    private ActionBar actionBar;
    private MyInitReturnBar myInitReturnBar;
    private String[] tabs = {"近期血壓", "血壓趨勢圖", "血壓紀錄"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data_press);

        initViews();
        initBar();
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            // 點擊 ActionBar 返回按鈕時 結束目前的 Activity
            case android.R.id.home:
                finish();
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
        myInitReturnBar = new MyInitReturnBar(this, "我的血壓", 2);
        actionBar = myInitReturnBar.getActionBar();


        //region 已寫成MyInitReturnBar.class
//        actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setElevation(0);
//            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // Specify that tabs should be displayed in the action bar.
//            //禁用ActionBar標題
//            actionBar.setDisplayShowTitleEnabled(false);
//            //禁用ActionBar圖標
//            actionBar.setDisplayUseLogoEnabled(false);
//            //禁用ActionBar返回鍵
//            actionBar.setDisplayShowHomeEnabled(false);
//
//            //自定義 ActionBar
//            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
//                    ActionBar.LayoutParams.MATCH_PARENT,
//                    ActionBar.LayoutParams.MATCH_PARENT,
//                    Gravity.CENTER);
//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_return, null);
//            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
//            ImageButton actionBarLeftBtn = (ImageButton) titleView.findViewById(R.id.actionBarLeftBtn);
//            actionBarLeftBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//            actionBarText.setText("我的血壓");
//            actionBarText.setTextColor(Color.WHITE);
//            actionBar.setCustomView(titleView, lp);
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            actionBar.setDisplayShowCustomEnabled(true);
//
//            /**
//             * 顯示ActionBar & 左上角回上一頁鍵(onOptionsItemSelected()已mark)
//             */
////            actionBar.setDisplayHomeAsUpEnabled(true);
////            actionBar.setHomeButtonEnabled(true);
////            actionBar.setTitle("關心 會員暱稱");
        //endregion


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
                    return new PersonDataPressFourDayFragment();
                case 1:
                    return new PersonDataSearchDrawPressFragment();
                case 2:
                    return new PersonDataSearchTablePressFragment();
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
