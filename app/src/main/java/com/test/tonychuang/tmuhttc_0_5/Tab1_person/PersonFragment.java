package com.test.tonychuang.tmuhttc_0_5.Tab1_person;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.PersonDataFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.PersonServiceFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP3_board.PersonBoardFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    private ViewPager mViewPager;
    private static final int TAB_COUNT = 3;
    private View view;  //Fragment的佈局

    private ActionBar actionBar;
    private String[] tabs = {"我的健康數據", "我的服務資訊", "健康公布欄"};


    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        initViews();
        initBar();
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        return view;
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initViews() {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    public void initBar() {
        actionBar = MainActivity.actionBar;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // Specify that tabs should be displayed in the action bar.
        //禁用ActionBar標題
        actionBar.setDisplayShowTitleEnabled(false);
        //禁用ActionBar圖標
        actionBar.setDisplayUseLogoEnabled(false);
        //禁用ActionBar返回鍵
        actionBar.setDisplayShowHomeEnabled(false);
        //關閉自定義的ActionBar(setting fragment page)
        actionBar.setDisplayShowCustomEnabled(false);

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
                    return new PersonDataFragment();
                case 1:
                    return new PersonServiceFragment();
                case 2:
                    return new PersonBoardFragment();
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
