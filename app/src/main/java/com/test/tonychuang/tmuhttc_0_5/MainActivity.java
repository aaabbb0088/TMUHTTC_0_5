package com.test.tonychuang.tmuhttc_0_5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.Tab1_person.PersonFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.FriendFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab3_community.CommunityFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab4_setting.SettingFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private View personLayout; //Person界面佈局
    private View friendLayout;
    private View communityLayout;
    private View settingLayout;

    private ImageView personImage; //在Tab佈局上顯示Person圖標的控件
    private ImageView friendImage;
    private ImageView communityImage;
    private ImageView settingImage;

    private TextView personText; //在Tab佈局上顯示Person標題的控件
    private TextView friendText;
    private TextView communityText;
    private TextView settingText;

    private PersonFragment personFragment; //用於展示Person的Fragment
    private FriendFragment friendFragment;
    private CommunityFragment communityFragment;
    private SettingFragment settingFragment;

    private FragmentManager fragmentManager; //用於對Fragment進行管理

    private static int mainUpdatedFlag = 0;
    public static int medUpdatedFlag = 0;
    public static int rptUpdatedFlag = 0;
    public static int payUpdatedFlag = 0;
    public static int rcdUpdatedFlag = 0;
    public static int friUpdatedFlag = 0;
    public static int friBoardUpdatedFlag = 0;
    public static int comUpdatedFlag = 0;

    private boolean isMemberflag = true;

    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initBar();
        fragmentManager = getSupportFragmentManager();
        memberTabSetting(); //正式時，放在非同步完成資料更新後

        //test code 同步中的表示Dialog
//        final MySyncingDialog mySyncDialog = new MySyncingDialog(false, MainActivity.this, "資料同步中,請稍後");
//        mySyncDialog.show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mySyncDialog.dismiss();
//            }
//        }, 5000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_layout:
                // 當點擊了健康tab時，選中第1個tab
                setTabSelection(0);
                personLayout.setEnabled(false);
                friendLayout.setEnabled(true);
                communityLayout.setEnabled(true);
                settingLayout.setEnabled(true);
                break;
            case R.id.friend_layout:
                // 當點擊了好友tab時，選中第3個tab
                setTabSelection(1);
                personLayout.setEnabled(true);
                friendLayout.setEnabled(false);
                communityLayout.setEnabled(true);
                settingLayout.setEnabled(true);
                break;
            case R.id.community_layout:
                // 當點擊了聯繫tab時，選中第2個tab
                setTabSelection(2);
                personLayout.setEnabled(true);
                friendLayout.setEnabled(true);
                communityLayout.setEnabled(false);
                settingLayout.setEnabled(true);
                break;
            case R.id.setting_layout:
                // 當點擊了設置tab時，選中第4個tab
                setTabSelection(3);
                personLayout.setEnabled(true);
                friendLayout.setEnabled(true);
                communityLayout.setEnabled(true);
                settingLayout.setEnabled(false);
                break;
            default:
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */
    //region 初始化View、Bar
    private void initViews() {
        personLayout = findViewById(R.id.person_layout);
        personLayout.setOnClickListener(this);
        friendLayout = findViewById(R.id.friend_layout);
        friendLayout.setOnClickListener(this);
        communityLayout = findViewById(R.id.community_layout);
        communityLayout.setOnClickListener(this);
        settingLayout = findViewById(R.id.setting_layout);
        settingLayout.setOnClickListener(this);
        personImage = (ImageView) findViewById(R.id.person_image);
        friendImage = (ImageView) findViewById(R.id.friend_image);
        communityImage = (ImageView) findViewById(R.id.community_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);
        personText = (TextView) findViewById(R.id.person_text);
        friendText = (TextView) findViewById(R.id.friend_text);
        communityText = (TextView) findViewById(R.id.community_text);
        settingText = (TextView) findViewById(R.id.setting_text);
    }

    private void initBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }
    //endregion

    private void memberTabSetting() {
        if (isMemberflag) {   //如果是遠距會員->if,如果是非遠距會員->else
            setTabSelection(0);// 第一次啟動時選中第0個tab
        } else {
            setTabSelection(1);
            personLayout.setVisibility(View.GONE);
        }
    }

    //region 處理TAB切換分頁
    /**
     * 根據傳入的index參數來設置選中的tab頁。
     */
    /**
     * @param index
     */
    private void setTabSelection(int index) {
        // 每次選中之前先清楚掉上次的選中狀態
        clearSelection();
        // 開啟一個Fragment事務
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隱藏掉所有的Fragment，以防止有多個Fragment顯示在界面上的情況
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 當點擊了健康tab時，改變控件的圖片和文字顏色
                personImage.setImageResource(R.mipmap.ic_accessibility_gray_24dp);
                personText.setTextColor(this.getResources().getColor(R.color.colorTabClicked));
                personLayout.setBackgroundColor(Color.WHITE);
                if (personFragment == null) {
                    // 如果MessageFragment為空，則創建一個並添加到界面上
                    personFragment = new PersonFragment();
                    transaction.add(R.id.content, personFragment);
                } else {
                    // 如果MessageFragment不為空，則直接將它顯示出來
                    personFragment.initBar(); //更新ActionBar
                    transaction.show(personFragment);
                }
                break;
            case 1:
                // 當點擊了好友tab時，改變控件的圖片和文字顏色
                friendImage.setImageResource(R.mipmap.ic_people_gary_24dp);
                friendText.setTextColor(this.getResources().getColor(R.color.colorTabClicked));
                friendLayout.setBackgroundColor(Color.WHITE);
                if (friendFragment == null) {
                    // 如果ContactsFragment為空，則創建一個並添加到界面上
                    friendFragment = new FriendFragment();
                    transaction.add(R.id.content, friendFragment);
                } else {
                    // 如果ContactsFragment不為空，則直接將它顯示出來
                    friendFragment.initBar(); //更新ActionBar
                    transaction.show(friendFragment);
                }
                break;
            case 2:
                // 當點擊了聯繫tab時，改變控件的圖片和文字顏色
                communityImage.setImageResource(R.mipmap.ic_person_pin_gary_24dp);
                communityText.setTextColor(this.getResources().getColor(R.color.colorTabClicked));
                communityLayout.setBackgroundColor(Color.WHITE);
                if (communityFragment == null) {
                    // 如果NewsFragment為空，則創建一個並添加到界面上
                    communityFragment = new CommunityFragment();
                    transaction.add(R.id.content, communityFragment);
                } else {
                    // 如果NewsFragment不為空，則直接將它顯示出來
                    communityFragment.initBar(); //更新ActionBar
                    transaction.show(communityFragment);
                }
                break;
            case 3:
            default:
                // 當點擊了設置tab時，改變控件的圖片和文字顏色
                settingImage.setImageResource(R.mipmap.ic_settings_gary_24dp);
                settingText.setTextColor(this.getResources().getColor(R.color.colorTabClicked));
                settingLayout.setBackgroundColor(Color.WHITE);
                if (settingFragment == null) {
                    // 如果SettingFragment為空，則創建一個並添加到界面上
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    // 如果SettingFragment不為空，則直接將它顯示出來
                    settingFragment.initBar(); //更新ActionBar
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的選中狀態。
     */
    /**
     *
     */
    private void clearSelection() {
        personImage.setImageResource(R.mipmap.ic_accessibility_white_24dp);
        personText.setTextColor(Color.parseColor("#FFFFFF"));
        personLayout.setBackgroundColor(Color.TRANSPARENT); //背景透明
        friendImage.setImageResource(R.mipmap.ic_people_white_24dp);
        friendText.setTextColor(Color.parseColor("#FFFFFF"));
        friendLayout.setBackgroundColor(Color.TRANSPARENT);
        communityImage.setImageResource(R.mipmap.ic_person_pin_white_24dp);
        communityText.setTextColor(Color.parseColor("#FFFFFF"));
        communityLayout.setBackgroundColor(Color.TRANSPARENT);
        settingImage.setImageResource(R.mipmap.ic_settings_white_24dp);
        settingText.setTextColor(Color.parseColor("#FFFFFF"));
        settingLayout.setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * 將所有的Fragment都置為隱藏狀態。
     */
    /**
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (personFragment != null) {
            transaction.hide(personFragment);
        }
        if (friendFragment != null) {
            transaction.hide(friendFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    //endregion
    //region 按下返回鍵出現確認離開視窗 onKeyDown, dialog
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (settingFragment != null && SettingFragment.getDialog() != null) {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getRepeatCount() == 0                  //確定按下退出鍵and防止重複按下退出鍵
                    && !SettingFragment.getDialog().isShowing()) {  //GPS設定選單出現時，不會跳出離開Dialog
                excDialog();
            }
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getRepeatCount() == 0) {  //確定按下退出鍵and防止重複按下退出鍵
                excDialog();
            }
        }
        return false;
    }

    private void excDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exc_body, null);
        TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();
        alertDialog.show();

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                MainActivity.this.finish();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    //endregion


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
     * 同步所有個人資料
     *
     * 一、判斷是不是遠距會員
     *   是-> isMemberflag = true
     *        更新遠距會員個人資料
     *   否-> isMemberflag = false
     *        更新APP會員個人資料
     *
     * 二、更新資料
     * mainUpdatedFlag = 0;//更新資料
     * mainUpdatedFlag = 1;//已更新資料
     *
     * APP會員
     * 1.個人基本設定PersonalDataSetting
     * 2.個人設定PersonalSetting
     *
     *
     * 遠距會員
     * 1.個人基本設定PersonalDataSetting
     * 2.個人設定PersonalSetting
     *
     * 1.個人警戒值上下限設定檔WarningLevelSetting (僅登入者資料)
     * 2.個人血壓流水資料PressDataTable (僅登入者資料)
     * 3.個人血壓留言PressMsgTable (僅登入者資料)
     * 4.個人每日平均血壓PressAvgTable (僅登入者資料)
     * 5.個人血糖流水資料GlycemiaDataTable (僅登入者資料)
     * 6.個人血糖留言GlycemiaMsgTable (僅登入者資料)
     * 7.個人每日平均血壓GlycemiaAvgTable (僅登入者資料)
     * 8.APP使用者個人訊息表PersonalNoticeTable
     * 9.APP使用者中心訊息表CenterNoticeTable
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
