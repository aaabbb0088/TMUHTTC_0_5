package com.test.tonychuang.tmuhttc_0_5;

import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.PersonFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.FriendFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab3_community.CommunityFragment;
import com.test.tonychuang.tmuhttc_0_5.Tab4_setting.SettingFragment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.CtrMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.CtrNotRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FGRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRecvNotSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrNotSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PsnNotRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SMedRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SPayRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRcrdRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRprtRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnDataSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnDataSettingRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnSettingRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.WLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    private SignInShrPref signInShrPref;
    private MySyncingDialog mySyncingDialog;

    private Boolean[] updateEndflag;

    private DataBase mainDB;

    public static ActionBar actionBar;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        initBar();
        initViews();
        initFragmentManager();
        initTabSetting();
        initData();

        updateData();

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
    private void initBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }

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

    private void initFragmentManager() {
        fragmentManager = getSupportFragmentManager();
    }

    //判斷是否是遠距會員，決定Tab顯示
    private void initTabSetting() {
//        //test code
//        SignInShrPref signInShrPref = new SignInShrPref(this);
//        signInShrPref.setMemberFlag(true);
//        //test code

        if (new SignInShrPref(this).getMemberFlag()) {   //如果是遠距會員->if,如果是非遠距會員->else
            setTabSelection(0);// 第一次啟動時選中第0個tab
        } else {
            setTabSelection(1);
            personLayout.setVisibility(View.GONE);
        }
    }

    //根據傳入的index參數來設置選中的tab頁。
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
    //清除掉所有的選中狀態。
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

    //將所有的Fragment都置為隱藏狀態。
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


    /**
     * d1
     */
    /**
     * 新增非同步更新資料的 endFlag 在 initData()
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(this);
        updateEndflag = new Boolean[]{false, false, false, false, false, false, false, false
                , false, false, false, false, false, false, false, false, false, false, false
                , false, false, false, false}; //MainActivity重新啟動時初始化更新旗標
    }

    private void updateData() {
        int allUpdateAsycFlag = 0;
        for (int i = 0; i < updateEndflag.length; i++) {
            if (updateEndflag[i]) {
                allUpdateAsycFlag = 1;
                break;
            }
        }
        if (allUpdateAsycFlag == 0) {
            mySyncingDialog = new MySyncingDialog(false, this, "資料同步中，請稍後");
            mySyncingDialog.show();
            mainDB = LiteOrm.newSingleInstance(MainActivity.this, signInShrPref.getAID());
            UpdatePsnDataSetting();
            UpdatePsnSetting();
            UpdateMemberData();
            UpdateFriendData();
            UpdateCommunityData();
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    //更新 .個人基本資料PersonalDataSetting (修改 遠距會員身分flag,修改 密碼、會員頭像、暱稱、性別、生日) end[0]
    private void UpdatePsnDataSetting() {
        new AsyncTask<String, Void, ArrayList<PsnDataSettingRow>>() {
            @Override
            protected ArrayList<PsnDataSettingRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<PsnDataSettingRow> psnDataSettingRows = null;

                try {
                    JSONObject jsonObject = httcjsonapi.UpdatePsnDataSetting(params[0]);
                    psnDataSettingRows = jsonParser.parsePsnDataSettingRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return psnDataSettingRows;
            }

            @Override
            protected void onPostExecute(ArrayList<PsnDataSettingRow> psnDataSettingRows) {
                super.onPostExecute(psnDataSettingRows);
                if (psnDataSettingRows != null) {
                    if (psnDataSettingRows.size() != 0) {
                        PsnDataSettingShrPref psnDataSettingShrPref =
                                new PsnDataSettingShrPref(MainActivity.this, signInShrPref.getAID());
                        if (psnDataSettingShrPref.getPID().equals("error")) {
                            //空資料，要整筆下載
                            psnDataSettingShrPref.setAllData(psnDataSettingRows.get(0));

//                            //test
//                            String str = psnDataSettingShrPref.getPID() + "\n"
//                                    + psnDataSettingShrPref.getPWD() + "\n"
//                                    + psnDataSettingShrPref.getAID() + "\n"
//                                    + psnDataSettingShrPref.getSID() + "\n"
//                                    + psnDataSettingShrPref.getNICKNAME() + "11111";
//                            Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                            //test

                        } else {
                            //已有資料，只需更新 遠距會員身分flag
                            psnDataSettingShrPref.setMEMBERFLAG(psnDataSettingRows.get(0).getMemberflag());
                            //不同裝置，更新 密碼、會員頭像、暱稱、性別、生日
                            if (!signInShrPref.getSameSignInMachine()) {
                                psnDataSettingShrPref.setPWD(psnDataSettingRows.get(0).getPwd());
                                psnDataSettingShrPref.setAVATAR(psnDataSettingRows.get(0).getAvatar());
                                psnDataSettingShrPref.setNICKNAME(psnDataSettingRows.get(0).getNickname());
                                psnDataSettingShrPref.setSEX(psnDataSettingRows.get(0).getSex());
                                psnDataSettingShrPref.setBIRTHDAY(psnDataSettingRows.get(0).getBirthday());
                            }

//                        //test
//                        String str = psnDataSettingShrPref.getPID() + "\n"
//                                + psnDataSettingShrPref.getPWD() + "\n"
//                                + psnDataSettingShrPref.getAID() + "\n"
//                                + psnDataSettingShrPref.getSID() + "\n"
//                                + psnDataSettingShrPref.getNICKNAME() + "22222";
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                        //test

                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "個人資料更新失敗", Toast.LENGTH_SHORT).show();
                }
                //更新結束，不論結果都要執行
                updateRnEndflagSetting(0);
            }
        }.execute(signInShrPref.getAID());
    }

    //不同裝置登入 個人設定 全部更新 end[1]
    private void UpdatePsnSetting() {
        if (!signInShrPref.getSameSignInMachine()) { //不同裝置
            //更新動作
            new AsyncTask<String, Void, ArrayList<PsnSettingRow>>() {
                @Override
                protected ArrayList<PsnSettingRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PsnSettingRow> psnSettingRows = null;

                    try {
                        JSONObject jsonObject = httcjsonapi.UpdatePsnSetting(params[0]);
                        psnSettingRows = jsonParser.parsePsnSettingRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return psnSettingRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PsnSettingRow> psnSettingRows) {
                    super.onPostExecute(psnSettingRows);
                    if (psnSettingRows != null) { //撈取資料成功
                        if (psnSettingRows.size() != 0) {
                            PsnSettingShrPref psnSettingShrPref =
                                    new PsnSettingShrPref(MainActivity.this, signInShrPref.getAID(),
                                            psnSettingRows.get(0));

//                            //test
//                            String str = psnSettingShrPref.getCENTER_MSG_FLAG() + "\n"
//                                    + psnSettingShrPref.getCENTER_NOT_FLAG() + "\n"
//                                    + psnSettingShrPref.getDATA_NOT_FLAG() + "\n"
//                                    + psnSettingShrPref.getLOCATION_FLAG() + "\n"
//                                    + psnSettingShrPref.getMEDICINE_NOT_FLAG();
//                            Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                            //test

                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人設定更新失敗", Toast.LENGTH_SHORT).show();
                    }
                    //更新結束
                    updateRnEndflagSetting(1);
                }
            }.execute(signInShrPref.getAID());
        } else { //同裝置
            updateRnEndflagSetting(1);
        }
    }

    /**
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
    //遠距會員 更新 11+4個Table 刪除舊資料 end[2]~end[15] + end[16]
    private void UpdateMemberData() {
        final Calendar calendar = Calendar.getInstance(Locale.TAIWAN);
        calendar.add(Calendar.MONTH, -1);
        final Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.DAY_OF_MONTH, -1);
        Date todayDate = clr.getTime();
        final String todayStr = new MyDateSFormat().getFrmt_yMd().format(new Date());
        try {
            todayDate = new MyDateSFormat().getFrmt_yMd().parse(todayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
        Date lastTime;

        //遠距會員
        if (signInShrPref.getMemberFlag()) {
            //1.個人警戒值上下限設定檔WarningLevelSetting (僅登入者資料) end[2]
            new AsyncTask<String, Void, ArrayList<WLevelRow>>() {
                @Override
                protected ArrayList<WLevelRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<WLevelRow> WLevelRows = null;

                    try {
                        JSONObject jsonObject = httcjsonapi.UpdateWLevelShrPref(params[0]);
                        WLevelRows = jsonParser.parseWLevelRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return WLevelRows;
                }

                @Override
                protected void onPostExecute(ArrayList<WLevelRow> wLevelRows) {
                    super.onPostExecute(wLevelRows);
                    if (wLevelRows != null) {
                        if (wLevelRows.size() != 0) {
                            WLevelShrPref wLevelShrPref = new WLevelShrPref(MainActivity.this,
                                    signInShrPref.getAID(), wLevelRows.get(0));

//                            //test
//                            Toast.makeText(MainActivity.this,
//                                    String.valueOf(wLevelShrPref.getBG_AM_Max()),
//                                    Toast.LENGTH_SHORT).show();
//                            //test

                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人警戒值資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
                    updateRnEndflagSetting(2);
                }
            }.execute(signInShrPref.getSID());


            //2.個人血壓流水資料PressDataTable (僅登入者資料) end[3]
            ArrayList<PreDataRow> preDataRowArrayList = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                    .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreDataRow.ID)
                    .limit(0, 1));
            if (preDataRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(preDataRowArrayList.get(0).getPData_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<PreDataRow>>() {
                @Override
                protected ArrayList<PreDataRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PreDataRow> preDataRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdatePressDataTable(params[0], params[1]);
                        preDataRows = jsonParser.parsePreDataRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return preDataRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PreDataRow> preDataRows) {
                    super.onPostExecute(preDataRows);
                    if (preDataRows != null) {
                        if (preDataRows.size() != 0) {
                            for (int i = 0; i < preDataRows.size(); i++) {
                                mainDB.save(preDataRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(PreDataRow.class)
                                    .lessThan(PreDataRow.PDATA_DATETIME,
                                            new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血壓量測資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(PreDataRow.class);
//                    ArrayList<PreDataRow> list1 = mainDB.query(PreDataRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPData_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(3);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);

            //3.個人血壓按讚PressThumbTable (僅登入者資料) end[4] //只更新一天資料
            ArrayList<PreThumbRow> preThumbRowArrayList = mainDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
                    .whereEquals(PreThumbRow.PDATA_THUMB_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreThumbRow.ID)
                    .limit(0, 1));
            if (preThumbRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(preThumbRowArrayList.get(0).getPData_thumb_datetime());
                    if (lastTime.after(todayDate)) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
            }
            new AsyncTask<String, Void, ArrayList<PreThumbRow>>() {
                @Override
                protected ArrayList<PreThumbRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PreThumbRow> preThumbRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdatePressThumbTable(params[0], params[1]);
                        preThumbRows = jsonParser.parsePreThumbRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return preThumbRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PreThumbRow> preThumbRows) {
                    super.onPostExecute(preThumbRows);
                    if (preThumbRows != null) {
                        if (preThumbRows.size() != 0) {
                            for (int i = 0; i < preThumbRows.size(); i++) {
                                mainDB.save(preThumbRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(PreThumbRow.class)
                                    .lessThan(PreThumbRow.PDATA_THUMB_DATETIME, todayStr));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血壓按讚資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(PreThumbRow.class);
//                    ArrayList<PreThumbRow> list1 = mainDB.query(PreThumbRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPData_thumb_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(4);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //4.個人血壓留言PressMsgTable (僅登入者資料) end[5]
            /**
             * 創或開Table 使用LiteOrm
             *
             * 參數:sid，更新到最後一筆的資料時間
             * 前動作:如果APP端沒有資料，從30天前更新起，如果有資料，從最後一筆開始更新
             * 回傳:DataTable
             * 後動作:資料為空，不動作;資料非空，全部寫入;刪除舊的資料
             */
            ArrayList<PreMsgRow> preMsgRowArrayList = mainDB.query(new QueryBuilder<PreMsgRow>(PreMsgRow.class)
                    .whereEquals(PreMsgRow.PMSG_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreMsgRow.ID)
                    .limit(0, 1));
            if (preMsgRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(preMsgRowArrayList.get(0).getPMsg_datetime());
                    if (lastTime.after(todayDate)) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
            }
            new AsyncTask<String, Void, ArrayList<PreMsgRow>>() {
                @Override
                protected ArrayList<PreMsgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PreMsgRow> preMsgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdatePressMsgTable(params[0], params[1]);
                        preMsgRows = jsonParser.parsePreMsgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return preMsgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PreMsgRow> preMsgRows) {
                    super.onPostExecute(preMsgRows);
                    if (preMsgRows != null) {
                        if (preMsgRows.size() != 0) {
                            for (int i = 0; i < preMsgRows.size(); i++) {
                                mainDB.save(preMsgRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(PreMsgRow.class)
                                    .lessThan(PreMsgRow.PMSG_DATETIME, todayStr));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血壓留言資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(PreMsgRow.class);
//                    ArrayList<PreMsgRow> list1 = mainDB.query(PreMsgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPMsg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(5);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //5.個人每日平均血壓PressAvgTable (僅登入者資料) end[6]
            ArrayList<PreAvgRow> preAvgRowArrayList = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                    .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreAvgRow.ID)
                    .limit(0, 1));
            if (preAvgRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMd().parse(preAvgRowArrayList.get(0).getPAvg_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMd().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMd().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMd().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<PreAvgRow>>() {
                @Override
                protected ArrayList<PreAvgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PreAvgRow> preAvgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdatePressAvgTable(params[0], params[1]);
                        preAvgRows = jsonParser.parsePreAvgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return preAvgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PreAvgRow> preAvgRows) {
                    super.onPostExecute(preAvgRows);
                    if (preAvgRows != null) {
                        if (preAvgRows.size() != 0) {
                            for (int i = 0; i < preAvgRows.size(); i++) {
                                mainDB.save(preAvgRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(PreAvgRow.class)
                                    .lessThan(PreAvgRow.PAVG_DATETIME,
                                            new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人平均血壓資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(PreAvgRow.class);
//                    ArrayList<PreAvgRow> list1 = mainDB.query(PreAvgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPAvg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(6);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //6.個人血糖流水資料GlycemiaDataTable (僅登入者資料) end[7]
            ArrayList<GlyDataRow> glyDataRowArrayList = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                    .whereEquals(GlyDataRow.GDATA_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyDataRow.ID)
                    .limit(0, 1));
            if (glyDataRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(glyDataRowArrayList.get(0).getGData_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<GlyDataRow>>() {
                @Override
                protected ArrayList<GlyDataRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<GlyDataRow> glyDataRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateGlycemiaDataTable(params[0], params[1]);
                        glyDataRows = jsonParser.parseGlyDataRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return glyDataRows;
                }

                @Override
                protected void onPostExecute(ArrayList<GlyDataRow> glyDataRows) {
                    super.onPostExecute(glyDataRows);
                    if (glyDataRows != null) {
                        if (glyDataRows.size() != 0) {
                            for (int i = 0; i < glyDataRows.size(); i++) {
                                mainDB.save(glyDataRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(GlyDataRow.class)
                                    .lessThan(GlyDataRow.GDATA_DATETIME,
                                            new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血糖量測資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(GlyDataRow.class);
//                    ArrayList<GlyDataRow> list1 = mainDB.query(GlyDataRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getGData_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(7);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //7.個人血糖按讚GlycemiaThumbTable (僅登入者資料) end[8]
            ArrayList<GlyThumbRow> glyThumbRowArrayList = mainDB.query(new QueryBuilder<GlyThumbRow>(GlyThumbRow.class)
                    .whereEquals(GlyThumbRow.GDATA_THUMB_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyThumbRow.ID)
                    .limit(0, 1));
            if (glyThumbRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(glyThumbRowArrayList.get(0).getGData_thumb_datetime());
                    if (lastTime.after(todayDate)) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
            }
            new AsyncTask<String, Void, ArrayList<GlyThumbRow>>() {
                @Override
                protected ArrayList<GlyThumbRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<GlyThumbRow> glyThumbRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateGlycemiaThumbTable(params[0], params[1]);
                        glyThumbRows = jsonParser.parseGlyThumbRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return glyThumbRows;
                }

                @Override
                protected void onPostExecute(ArrayList<GlyThumbRow> glyThumbRows) {
                    super.onPostExecute(glyThumbRows);
                    if (glyThumbRows != null) {
                        if (glyThumbRows.size() != 0) {
                            for (int i = 0; i < glyThumbRows.size(); i++) {
                                mainDB.save(glyThumbRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(GlyThumbRow.class)
                                    .lessThan(GlyThumbRow.GDATA_THUMB_DATETIME, todayStr));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血糖按讚資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(GlyThumbRow.class);
//                    ArrayList<GlyThumbRow> list1 = mainDB.query(GlyThumbRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getGData_thumb_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(8);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //8.個人血糖留言GlycemiaMsgTable (僅登入者資料) end[9]
            ArrayList<GlyMsgRow> glyMsgRowArrayList = mainDB.query(new QueryBuilder<GlyMsgRow>(GlyMsgRow.class)
                    .whereEquals(GlyMsgRow.GMSG_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyMsgRow.ID)
                    .limit(0, 1));
            if (glyMsgRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(glyMsgRowArrayList.get(0).getGMsg_datetime());
                    if (lastTime.after(todayDate)) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(todayDate);
            }
            new AsyncTask<String, Void, ArrayList<GlyMsgRow>>() {
                @Override
                protected ArrayList<GlyMsgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<GlyMsgRow> glyMsgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateGlycemiaMsgTable(params[0], params[1]);
                        glyMsgRows = jsonParser.parseGlyMsgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return glyMsgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<GlyMsgRow> glyMsgRows) {
                    super.onPostExecute(glyMsgRows);
                    if (glyMsgRows != null) {
                        if (glyMsgRows.size() != 0) {
                            for (int i = 0; i < glyMsgRows.size(); i++) {
                                mainDB.save(glyMsgRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(GlyMsgRow.class)
                                    .lessThan(GlyMsgRow.GMSG_DATETIME, todayStr));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人血糖留言資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(GlyMsgRow.class);
//                    ArrayList<GlyMsgRow> list1 = mainDB.query(GlyMsgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getGMsg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(9);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //9.個人每日平均血糖GlycemiaAvgTable (僅登入者資料) end[10]
            ArrayList<GlyAvgRow> glyAvgRowArrayList = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                    .whereEquals(GlyAvgRow.GAVG_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyAvgRow.ID)
                    .limit(0, 1));
            if (glyAvgRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMd().parse(glyAvgRowArrayList.get(0).getGAvg_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMd().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMd().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMd().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<GlyAvgRow>>() {
                @Override
                protected ArrayList<GlyAvgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<GlyAvgRow> glyAvgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateGlycemiaAvgTable(params[0], params[1]);
                        glyAvgRows = jsonParser.parseGlyAvgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return glyAvgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<GlyAvgRow> glyAvgRows) {
                    super.onPostExecute(glyAvgRows);
                    if (glyAvgRows != null) {
                        if (glyAvgRows.size() != 0) {
                            for (int i = 0; i < glyAvgRows.size(); i++) {
                                mainDB.save(glyAvgRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(GlyAvgRow.class)
                                    .lessThan(GlyAvgRow.GAVG_DATETIME,
                                            new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人平均血糖資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(GlyAvgRow.class);
//                    ArrayList<GlyAvgRow> list1 = mainDB.query(GlyAvgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getGAvg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(10);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //10.APP使用者個人訊息表PersonalNoticeTable APP不主動刪除資料 end[11]
            ArrayList<PsnNotRow> psnNotRowArrayList = mainDB.query(new QueryBuilder<PsnNotRow>(PsnNotRow.class)
                    .appendOrderDescBy(PsnNotRow.ID)
                    .limit(0, 1));
            if (psnNotRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(psnNotRowArrayList.get(0).getPsnNot_datetime());
                    lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<PsnNotRow>>() {
                @Override
                protected ArrayList<PsnNotRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PsnNotRow> psnNotRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdatePersonalNoticeTable(params[0], params[1]);
                        psnNotRows = jsonParser.parsePsnNotRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return psnNotRows;
                }

                @Override
                protected void onPostExecute(ArrayList<PsnNotRow> psnNotRows) {
                    super.onPostExecute(psnNotRows);
                    if (psnNotRows != null) {
                        if (psnNotRows.size() != 0) {
                            for (int i = 0; i < psnNotRows.size(); i++) {
                                mainDB.save(psnNotRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人推撥訊息更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(PsnNotRow.class);
//                    ArrayList<PsnNotRow> list1 = mainDB.query(PsnNotRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPsnNot_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(11);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);

            //11.用藥紀錄 一個月資料 end[12]
            ArrayList<SMedRow> sMedRowArrayList = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
                    .whereEquals(SMedRow.SMED_SID, signInShrPref.getSID())
                    .appendOrderDescBy(SMedRow.ID)
                    .limit(0, 1));
            if (sMedRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(sMedRowArrayList.get(0).getSMed_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<SMedRow>>() {
                @Override
                protected ArrayList<SMedRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<SMedRow> sMedRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateServiceMedicineTable(params[0], params[1]);
                        sMedRows = jsonParser.parseSMedRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return sMedRows;
                }

                @Override
                protected void onPostExecute(ArrayList<SMedRow> sMedRows) {
                    super.onPostExecute(sMedRows);
                    if (sMedRows != null) {
                        if (sMedRows.size() != 0) {
                            for (int i = 0; i < sMedRows.size(); i++) {
                                mainDB.save(sMedRows.get(i));
                            }
                            mainDB.delete(new WhereBuilder(SMedRow.class)
                                    .lessThan(SMedRow.SMED_DATETIME,
                                            new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人用藥資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(SMedRow.class);
//                    ArrayList<SMedRow> list1 = mainDB.query(SMedRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getSMed_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(12);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //12.健康報告書 不刪除 end[13] -> 1.確定下載資料方法、下載資料路徑設定(設成 Download/'sid'+'_yyyy-MM')->SRprt_path 2.完成健康報告書資料同步
            /**
             *
             */
            ArrayList<SRprtRow> sRprtRowArrayList = mainDB.query(new QueryBuilder<SRprtRow>(SRprtRow.class)
                    .whereEquals(SRprtRow.SRPRT_SID, signInShrPref.getSID())
                    .appendOrderDescBy(SRprtRow.ID)
                    .limit(0, 1));
            if (sRprtRowArrayList.size() != 0) { //已有資料，下載新資料
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(sRprtRowArrayList.get(0).getSRprt_datetime());
                    lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else { //原本沒資料，全部下載
                lastDataTime = "1900-01-01";
            }
            new AsyncTask<String, Void, ArrayList<SRprtRow>>() {
                @Override
                protected ArrayList<SRprtRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<SRprtRow> sRprtRows = null;

                    JSONObject jsonObject;
                    try {
//                        jsonObject = httcjsonapi.UpdateServiceMedicineTable(params[0], params[1]);
//                        sRprtRows = jsonParser.parseSMedRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return sRprtRows;
                }

                @Override
                protected void onPostExecute(ArrayList<SRprtRow> sRprtRows) {
                    super.onPostExecute(sRprtRows);
                    if (sRprtRows != null) {
                        if (sRprtRows.size() != 0) {
                            for (int i = 0; i < sRprtRows.size(); i++) {
                                mainDB.save(sRprtRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
//                        Toast.makeText(MainActivity.this, "個人健康報告書資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(SRprtRow.class);
//                    ArrayList<SRprtRow> list1 = mainDB.query(SRprtRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getSRprt_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(13);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            /**
             *
             */
            //13.繳費紀錄 不刪除 end[14]
            ArrayList<SPayRow> sPayRowArrayList = mainDB.query(new QueryBuilder<SPayRow>(SPayRow.class)
                    .whereEquals(SPayRow.SPAY_SID, signInShrPref.getSID())
                    .appendOrderDescBy(SPayRow.ID)
                    .limit(0, 1));
            if (sPayRowArrayList.size() != 0) { //已有資料，下載新資料
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(sPayRowArrayList.get(0).getSPay_datetime());
                    lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else { //原本沒資料，全部下載
                lastDataTime = "1900-01-01";
            }
            new AsyncTask<String, Void, ArrayList<SPayRow>>() {
                @Override
                protected ArrayList<SPayRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<SPayRow> sPayRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateServicePayTable(params[0], params[1]);
                        sPayRows = jsonParser.parseSPayRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return sPayRows;
                }

                @Override
                protected void onPostExecute(ArrayList<SPayRow> sPayRows) {
                    super.onPostExecute(sPayRows);
                    if (sPayRows != null) {
                        if (sPayRows.size() != 0) {
                            for (int i = 0; i < sPayRows.size(); i++) {
                                mainDB.save(sPayRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人繳費資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(SPayRow.class);
//                    ArrayList<SPayRow> list1 = mainDB.query(SPayRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getSPay_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(14);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //服務紀錄 一個月資料 end[15]
            ArrayList<SRcrdRow> sRcrdRowArrayList = mainDB.query(new QueryBuilder<SRcrdRow>(SRcrdRow.class)
                    .whereEquals(SRcrdRow.SRCRD_SID, signInShrPref.getSID())
                    .appendOrderDescBy(SRcrdRow.ID)
                    .limit(0, 1));
            if (sRcrdRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(sRcrdRowArrayList.get(0).getSRcrd_datetime());
                    if (lastTime.after(calendar.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            }
            new AsyncTask<String, Void, ArrayList<SRcrdRow>>() {
                @Override
                protected ArrayList<SRcrdRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<SRcrdRow> sRcrdRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateServiceRecordTable(params[0], params[1]);
                        sRcrdRows = jsonParser.parseSRcrdRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return sRcrdRows;
                }

                @Override
                protected void onPostExecute(ArrayList<SRcrdRow> sRcrdRows) {
                    super.onPostExecute(sRcrdRows);
                    if (sRcrdRows != null) {
                        if (sRcrdRows.size() != 0) {
                            for (int i = 0; i < sRcrdRows.size(); i++) {
                                mainDB.save(sRcrdRows.get(i));
                            }

                            mainDB.delete(new WhereBuilder(SRcrdRow.class)
                                    .lessThan(SMedRow.SMED_DATETIME,
                                            new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人服務歷程資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(SRcrdRow.class);
//                    ArrayList<SRcrdRow> list1 = mainDB.query(SRcrdRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getSRcrd_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(15);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


        } else { //非遠距會員
            updateRnEndflagSetting(2);
            updateRnEndflagSetting(3);
            updateRnEndflagSetting(4);
            updateRnEndflagSetting(5);
            updateRnEndflagSetting(6);
            updateRnEndflagSetting(7);
            updateRnEndflagSetting(8);
            updateRnEndflagSetting(9);
            updateRnEndflagSetting(10);
            updateRnEndflagSetting(11);
            updateRnEndflagSetting(12);
            updateRnEndflagSetting(13);
            updateRnEndflagSetting(14);
            updateRnEndflagSetting(15);
        }

        //11.APP使用者中心訊息表CenterNoticeTable APP不主動刪除資料 end[16]
        ArrayList<CtrNotRow> ctrNotRowArrayList = mainDB.query(new QueryBuilder<CtrNotRow>(CtrNotRow.class)
                .appendOrderDescBy(CtrNotRow.ID)
                .limit(0, 1));
        if (ctrNotRowArrayList.size() != 0) {
            try {
                lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(ctrNotRowArrayList.get(0).getCtrNot_datetime());
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
        }
        new AsyncTask<String, Void, ArrayList<CtrNotRow>>() {
            @Override
            protected ArrayList<CtrNotRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<CtrNotRow> ctrNotRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateCenterNoticeTable(params[0], params[1]);
                    ctrNotRows = jsonParser.parseCtrNotRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ctrNotRows;
            }

            @Override
            protected void onPostExecute(ArrayList<CtrNotRow> ctrNotRows) {
                super.onPostExecute(ctrNotRows);
                if (ctrNotRows != null) {
                    if (ctrNotRows.size() != 0) {
                        for (int i = 0; i < ctrNotRows.size(); i++) {
                            mainDB.save(ctrNotRows.get(i));
                        }
                        LiteOrm.releaseMemory();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "中心推撥訊息更新失敗", Toast.LENGTH_SHORT).show();
                }
//                //test
//                long count = mainDB.queryCount(CtrNotRow.class);
//                ArrayList<CtrNotRow> list1 = mainDB.query(CtrNotRow.class);
//                String str = String.valueOf(count) + "\n" + list1.get(0).getCtrNot_datetime();
//                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                //test
                updateRnEndflagSetting(16);
            }
        }.execute(signInShrPref.getAID(), lastDataTime);
    }

    /**
     * 判斷與上次使用的裝飾是否相同，相同不用更新以下兩項，不同需要更新以下兩項
     * APP使用者好友關係表FriendTable -> 好友非遠距會員(中間不能點擊)、好友是遠距會員(中間可以點擊)
     *                                 如果自己與好友都不是遠距會員(好友設定不能點擊)
     *                                 ----------------------以上兩項判斷在Adapter中執行------------
     * APP使用者好友群組表FriendGroupTable
     */
    /**
     *
     */
    //更新好友關係表、好友群組表、好友設定三表 end[17]~end[21]
    private void UpdateFriendData() {
        //不同裝置
        if (!signInShrPref.getSameSignInMachine()) {
            //更新好友關係表
            new AsyncTask<String, Void, ArrayList<FRow>>() {
                @Override
                protected ArrayList<FRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<FRow> fRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateFriendTable(params[0]);
                        fRows = jsonParser.parseFRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return fRows;
                }

                @Override
                protected void onPostExecute(ArrayList<FRow> fRows) {
                    super.onPostExecute(fRows);
                    if (fRows != null) {
                        if (fRows.size() != 0) {
                            mainDB.deleteAll(FRow.class);
                            for (int i = 0; i < fRows.size(); i++) {
                                mainDB.save(fRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "好友關係表更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(FRow.class);
//                    ArrayList<FRow> list1 = mainDB.query(FRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getF_active_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 3);
                }
            }.execute(signInShrPref.getAID());

            //更新好友群組表
            new AsyncTask<String, Void, ArrayList<FGRow>>() {
                @Override
                protected ArrayList<FGRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<FGRow> fgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateFriendGroupTable(params[0]);
                        fgRows = jsonParser.parseFGRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return fgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<FGRow> fgRows) {
                    super.onPostExecute(fgRows);
                    if (fgRows != null) {
                        if (fgRows.size() != 0) {
                            mainDB.deleteAll(FGRow.class);
                            for (int i = 0; i < fgRows.size(); i++) {
                                mainDB.save(fgRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "群組表更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(FGRow.class);
//                    ArrayList<FGRow> list1 = mainDB.query(FGRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getFG_fri_aid();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 2);
                }
            }.execute(signInShrPref.getAID());

            //APP使用者個人資料分享好友設定表
            new AsyncTask<String, Void, ArrayList<FShrSetRow>>() {
                @Override
                protected ArrayList<FShrSetRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<FShrSetRow> fShrSetRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateFriendShareSettingTable(params[0]);
                        fShrSetRows = jsonParser.parseFShrSetRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return fShrSetRows;
                }

                @Override
                protected void onPostExecute(ArrayList<FShrSetRow> fShrSetRows) {
                    super.onPostExecute(fShrSetRows);
                    if (fShrSetRows != null) {
                        if (fShrSetRows.size() != 0) {
                            mainDB.deleteAll(FShrSetRow.class);
                            for (int i = 0; i < fShrSetRows.size(); i++) {
                                mainDB.save(fShrSetRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人資料分享設定表更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(FShrSetRow.class);
//                    ArrayList<FShrSetRow> list1 = mainDB.query(FShrSetRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getFShrSet_fri_aid();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 6);
                }
            }.execute(signInShrPref.getAID());

            //APP使用者個人訊息分享好友設定表 ---改成---> 好友資料分享設定表
            new AsyncTask<String, Void, ArrayList<FShrNotSetRow>>() {
                @Override
                protected ArrayList<FShrNotSetRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<FShrNotSetRow> fShrNotSetRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateFriendShareNoticeSettingTable(params[0]);
                        fShrNotSetRows = jsonParser.parseFShrNotSetRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return fShrNotSetRows;
                }

                @Override
                protected void onPostExecute(ArrayList<FShrNotSetRow> fShrNotSetRows) {
                    super.onPostExecute(fShrNotSetRows);
                    if (fShrNotSetRows != null) {
                        if (fShrNotSetRows.size() != 0) {
                            mainDB.deleteAll(FShrNotSetRow.class);
                            for (int i = 0; i < fShrNotSetRows.size(); i++) {
                                mainDB.save(fShrNotSetRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人訊息分享設定表更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(FShrNotSetRow.class);
//                    ArrayList<FShrNotSetRow> list1 = mainDB.query(FShrNotSetRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getFShrNotSet_fri_aid();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 5);
                }
            }.execute(signInShrPref.getAID());

            //APP使用者好友訊息接受設定表
            new AsyncTask<String, Void, ArrayList<FRecvNotSetRow>>() {
                @Override
                protected ArrayList<FRecvNotSetRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<FRecvNotSetRow> fRecvNotSetRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateFriendReceiveNoticeSettingTable(params[0]);
                        fRecvNotSetRows = jsonParser.parseFRecvNotSetRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return fRecvNotSetRows;
                }

                @Override
                protected void onPostExecute(ArrayList<FRecvNotSetRow> fRecvNotSetRows) {
                    super.onPostExecute(fRecvNotSetRows);
                    if (fRecvNotSetRows != null) {
                        if (fRecvNotSetRows.size() != 0) {
                            mainDB.deleteAll(FRecvNotSetRow.class);
                            for (int i = 0; i < fRecvNotSetRows.size(); i++) {
                                mainDB.save(fRecvNotSetRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "個人訊息分享設定表更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(FRecvNotSetRow.class);
//                    ArrayList<FRecvNotSetRow> list1 = mainDB.query(FRecvNotSetRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getFRecvNotSet_fri_aid();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 4);
                }
            }.execute(signInShrPref.getAID());


            //撈取資料結束，若成功執行
            /**
             * 1.創或開Table
             * 2.取全部資料 : 有資料，全刪除後寫入 ; 無資料，不動作
             * 3.將資料寫入SQLite
             */
        } else { //同裝置 不用更新
            updateRnEndflagSetting(updateEndflag.length - 6);
            updateRnEndflagSetting(updateEndflag.length - 5);
            updateRnEndflagSetting(updateEndflag.length - 4);
            updateRnEndflagSetting(updateEndflag.length - 3);
            updateRnEndflagSetting(updateEndflag.length - 2);
        }
    }

    /**
     * 更新資料
     * APP使用者中心留言板表CenterMessageTable
     */
    /**
     *
     */
    //更新 APP使用者中心留言板表CenterMessageTable end[22]
    private void UpdateCommunityData() {
        ArrayList<CtrMsgRow> ctrMsgRowArrayList = mainDB.query(new QueryBuilder<CtrMsgRow>(CtrMsgRow.class)
                .appendOrderDescBy(CtrMsgRow.ID)
                .limit(0, 1));
        if (ctrMsgRowArrayList.size() != 0) { //有資料，更新
            String lastDataTime = ctrMsgRowArrayList.get(0).getCtrMsg_datetime();
            new AsyncTask<String, Void, ArrayList<CtrMsgRow>>() {
                @Override
                protected ArrayList<CtrMsgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<CtrMsgRow> ctrMsgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateCenterMessageTable(params[0], params[1]);
                        ctrMsgRows = jsonParser.parseCtrMsgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ctrMsgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<CtrMsgRow> ctrMsgRows) {
                    super.onPostExecute(ctrMsgRows);
                    if (ctrMsgRows != null) {
                        if (ctrMsgRows.size() != 0) {
                            for (int i = 0; i < ctrMsgRows.size(); i++) {
                                mainDB.save(ctrMsgRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "留言板更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(CtrMsgRow.class);
//                    ArrayList<CtrMsgRow> list1 = mainDB.query(CtrMsgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getCtrMsg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 1);
                }
            }.execute(signInShrPref.getAID(), lastDataTime);
        } else { //無資料，從最後一筆未讀資料開始更新
            new AsyncTask<String, Void, ArrayList<CtrMsgRow>>() {
                @Override
                protected ArrayList<CtrMsgRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<CtrMsgRow> ctrMsgRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.UpdateCenterMessageTable(params[0], params[1]);
                        ctrMsgRows = jsonParser.parseCtrMsgRow(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ctrMsgRows;
                }

                @Override
                protected void onPostExecute(ArrayList<CtrMsgRow> ctrMsgRows) {
                    super.onPostExecute(ctrMsgRows);
                    if (ctrMsgRows != null) {
                        if (ctrMsgRows.size() != 0) {
                            for (int i = 0; i < ctrMsgRows.size(); i++) {
                                mainDB.save(ctrMsgRows.get(i));
                            }
                            LiteOrm.releaseMemory();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "留言板更新失敗", Toast.LENGTH_SHORT).show();
                    }
//                    //test
//                    long count = mainDB.queryCount(CtrMsgRow.class);
//                    ArrayList<CtrMsgRow> list1 = mainDB.query(CtrMsgRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getCtrMsg_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test
                    updateRnEndflagSetting(updateEndflag.length - 1);
                }
            }.execute(signInShrPref.getAID(), "");
        }
    }


    private void updateRnEndflagSetting(int Num) {
        updateEndflag[Num] = true;
        int endFlagCount = 0;

        for (int i = 0; i < updateEndflag.length; i++) {
            if (updateEndflag[i]) {
                endFlagCount = endFlagCount + 1;
            }
        }

        if (endFlagCount >= updateEndflag.length) {
            mySyncingDialog.dismiss();
            if (mainDB != null) {
                mainDB.close();
            }
            if (signInShrPref.getMemberFlag()) {
                //更新Tab0頁面資料
            } else {
                //更新Tab1頁面資料
            }
        }
    }
}
