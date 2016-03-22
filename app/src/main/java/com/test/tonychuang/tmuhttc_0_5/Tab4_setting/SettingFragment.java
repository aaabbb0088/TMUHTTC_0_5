package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;


/**
 * 個人設定
 * 1.個人資訊 : 大頭照、暱稱、性別、出生年月日、修改密碼(輸入舊密碼、輸入新密碼、確認新密碼)、登出鍵 -> 登入頁面
 * 2.數據異常推撥flag
 * 藥物提醒推撥flag
 * 繳費提醒推撥flag
 * 健康報告書推撥flag
 * 服務歷程推撥flag
 * 健康公佈欄推撥flag
 * 遠距中心留言板回覆推撥flag
 * <p>
 * 開啟自身定位紀錄flag
 * <p>
 * 3.問題回報鍵 -> 問題回報頁面
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    private LinearLayout personalDataSettingLayout;
    private LinearLayout noticeLayout;
    private LinearLayout gpsLayout;
    private LinearLayout feedbackLayout;

    private View view;
    private ActionBar actionBar;
    private TextView gpsSettingTv;
    private ImageView gpsSettingImv;

    private static DialogPlus dialog;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initBar();
        initView();
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.personalDataSettingLayout:
                intent = new Intent().setClass(getActivity(), SettingPersonalDataActivity.class);
                startActivity(intent);
                break;
            case R.id.noticeLayout:
                intent = new Intent().setClass(getActivity(), SettingNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.gpsLayout:
//                settingGPSBottomSheet();
                settingGPSBottomDialog();
                break;
            case R.id.feedbackLayout:
                intent = new Intent().setClass(getActivity(), SettingFeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */
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
        TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
        actionBarText.setText("個人設定");
        actionBarText.setTextColor(Color.WHITE);
        actionBar.setCustomView(titleView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }
    private void initView() {
        personalDataSettingLayout = (LinearLayout) view.findViewById(R.id.personalDataSettingLayout);
        personalDataSettingLayout.setOnClickListener(SettingFragment.this);
        noticeLayout = (LinearLayout) view.findViewById(R.id.noticeLayout);
        noticeLayout.setOnClickListener(SettingFragment.this);
        gpsLayout = (LinearLayout) view.findViewById(R.id.gpsLayout);
        gpsLayout.setOnClickListener(SettingFragment.this);
        feedbackLayout = (LinearLayout) view.findViewById(R.id.feedbackLayout);
        feedbackLayout.setOnClickListener(SettingFragment.this);
        gpsSettingTv = (TextView) view.findViewById(R.id.gpsSettingTv);
        gpsSettingImv = (ImageView) view.findViewById(R.id.gpsSettingImv);
    }
    private void settingGPSBottomDialog() {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_gps_setting, null);
        dialog = DialogPlus
                .newDialog(getActivity())
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getOnClickListener())   //確認鍵、取消鍵
                .setOnBackPressListener(getOnBackPressListener())  //按返回鍵會縮下去
                .create();
        dialog.show();
    }



    /**
     * v2
     */
    /**
     *
     */
    private OnClickListener getOnClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.gpsOnText:
                        gpsSettingTv.setText("開啟");
                        gpsSettingImv.setImageResource(R.mipmap.setting_gps_on);
                        Toast.makeText(getActivity(), "GPS ON", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                    case R.id.gpsOffText:
                        gpsSettingTv.setText("關閉");
                        gpsSettingImv.setImageResource(R.mipmap.setting_gps_off);
                        Toast.makeText(getActivity(), "GPS OFF", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                }
            }
        };
    }
    private OnBackPressListener getOnBackPressListener() {
        return new OnBackPressListener() {
            @Override
            public void onBackPressed(DialogPlus dialogPlus) {
                dialogPlus.dismiss();
            }
        };
    }
    public static DialogPlus getDialog() {
        return dialog;
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
