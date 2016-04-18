package com.test.tonychuang.tmuhttc_0_5.Tab3_community;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.tonychuang.chat.ChatActivity;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;

import me.grantland.widget.AutofitTextView;

/**
 * 留言板，參考EmojiChat-master、Android-Chat-Widget-Example、TMUHTTC_0_4
 *
 * 聯繫-附近醫院
 *  地圖->調用google map
 *  1.捉到使用者位置
 *  2.搜索附近醫院(googleMap"醫院"搜尋)
 *  3.細項:縮放、路徑、3D、顯示位置點資訊
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment implements View.OnClickListener {

    private final static String CALL = "android.intent.action.CALL";
    private LinearLayout chatLayout;
    private LinearLayout callCenterLayout;
    private LinearLayout nearlyHospitalLayout;
    private LinearLayout call119Layout;

    private View view;
    private ActionBar actionBar;


    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);
        initBar();
        initView();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chatLayout:
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "Chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.callCenterLayout:
                toast("正在為您撥號，請稍後");
                Intent callCenter = new Intent(CALL, Uri.parse("tel:" + "0221765226"));
                startActivity(callCenter);
                break;
            case R.id.nearlyHospitalLayout:
                break;
            case R.id.call119Layout:
                toast("正在為您撥號，請稍後");
                Intent call119 = new Intent(CALL, Uri.parse("tel:" + "119"));
                startActivity(call119);
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        chatLayout = (LinearLayout) view.findViewById(R.id.chatLayout);
        chatLayout.setOnClickListener(this);
        callCenterLayout = (LinearLayout) view.findViewById(R.id.callCenterLayout);
        callCenterLayout.setOnClickListener(this);
        nearlyHospitalLayout = (LinearLayout) view.findViewById(R.id.nearlyHospitalLayout);
        nearlyHospitalLayout.setOnClickListener(this);
        call119Layout = (LinearLayout) view.findViewById(R.id.call119Layout);
        call119Layout.setOnClickListener(this);
    }
    public void initBar() {
        actionBar = MainActivity.actionBar;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //禁用系統ActionBar標題
        actionBar.setDisplayShowTitleEnabled(false);
        //禁用系統ActionBar圖標
        actionBar.setDisplayUseLogoEnabled(false);
        //禁用系統ActionBar返回鍵
        actionBar.setDisplayShowHomeEnabled(false);
        //清除所有已加入的Tab
        actionBar.removeAllTabs();

        //自定義 ActionBar
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title, null);
        AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
        actionBarText.setText("聯繫醫療單位");
        actionBarText.setTextColor(Color.WHITE);
        actionBar.setCustomView(titleView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void toast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }



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
