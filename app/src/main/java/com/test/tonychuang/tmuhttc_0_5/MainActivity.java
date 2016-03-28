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
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
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
    public static Boolean medUpdatedFlag = true;
    public static Boolean rptUpdatedFlag = true;
    public static Boolean payUpdatedFlag = true;
    public static Boolean rcdUpdatedFlag = true;
    public static Boolean friBoardUpdatedFlag = true;

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
                , false, false, false, false, false, false, false, false}; //MainActivity重新啟動時初始化更新旗標
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
                    PsnDataSettingShrPref psnDataSettingShrPref = new PsnDataSettingShrPref(MainActivity.this, signInShrPref.getAID());
                    if (psnDataSettingShrPref.getPID().equals("error")) {
                        //空資料，要整筆下載
                        psnDataSettingShrPref.setAllData(psnDataSettingRows.get(0));

//                        //test
//                        String str = psnDataSettingShrPref.getPID() + "\n"
//                                + psnDataSettingShrPref.getPWD() + "\n"
//                                + psnDataSettingShrPref.getAID() + "\n"
//                                + psnDataSettingShrPref.getSID() + "\n"
//                                + psnDataSettingShrPref.getNICKNAME() + "11111";
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                        //test

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
                        PsnSettingShrPref psnSettingShrPref =
                                new PsnSettingShrPref(MainActivity.this, signInShrPref.getAID(),
                                        psnSettingRows.get(0));

//                        //test
//                        String str = psnSettingShrPref.getCENTER_MSG_FLAG() + "\n"
//                                + psnSettingShrPref.getCENTER_NOT_FLAG() + "\n"
//                                + psnSettingShrPref.getDATA_NOT_FLAG() + "\n"
//                                + psnSettingShrPref.getLOCATION_FLAG() + "\n"
//                                + psnSettingShrPref.getMEDICINE_NOT_FLAG();
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                        //test

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
    //遠距會員 更新 11個Table 刪除舊資料
    private void UpdateMemberData() {
        if (signInShrPref.getMemberFlag()) { //遠距會員
            mainDB = LiteOrm.newSingleInstance(MainActivity.this, signInShrPref.getAID());
            final Calendar calendar = Calendar.getInstance(Locale.TAIWAN);
            calendar.add(Calendar.MONTH, -1);
            final Calendar clr = Calendar.getInstance(Locale.TAIWAN);
            clr.add(Calendar.DAY_OF_MONTH, -1);
            String lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
            Date lastTime;

            //更新動作
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
                        WLevelShrPref wLevelShrPref = new WLevelShrPref(MainActivity.this,
                                signInShrPref.getAID(), wLevelRows.get(0));

//                        //test
//                        Toast.makeText(MainActivity.this,
//                                String.valueOf(wLevelShrPref.getBG_AM_Max()),
//                                Toast.LENGTH_SHORT).show();
//                        //test

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
                    .limit(1, 1));
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
                        for (int i = 0; i < preDataRows.size(); i++) {
                            mainDB.save(preDataRows.get(i));
                        }

//                        //test
//                        long count = mainDB.queryCount(PreDataRow.class);
//                        ArrayList<PreDataRow> list1 = mainDB.query(PreDataRow.class);
//                        String str = String.valueOf(count) + "\n" + list1.get(0).getPData_datetime();
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                        //test

                        mainDB.delete(new WhereBuilder(PreDataRow.class)
                                .lessThan(PreDataRow.PDATA_DATETIME,
                                        new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime())));
                        LiteOrm.releaseMemory();
                    } else {
                        Toast.makeText(MainActivity.this, "個人血壓量測資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
                    updateRnEndflagSetting(3);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);

            //3.個人血壓按讚PressThumbTable (僅登入者資料) end[4] //只更新一天資料
            ArrayList<PreThumbRow> preThumbRowArrayList = mainDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
                    .whereEquals(PreThumbRow.PDATA_THUMB_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreThumbRow.ID)
                    .limit(1, 1));
            if (preThumbRowArrayList.size() != 0) {
                try {
                    lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(preThumbRowArrayList.get(0).getPData_thumb_datetime());
                    if (lastTime.after(clr.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime());
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
                        for (int i = 0; i < preThumbRows.size(); i++) {
                            mainDB.save(preThumbRows.get(i));
                        }
                        mainDB.delete(new WhereBuilder(PreThumbRow.class)
                                .lessThan(PreThumbRow.PDATA_THUMB_DATETIME,
                                        new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime())));
                        LiteOrm.releaseMemory();
                    } else {
                        Toast.makeText(MainActivity.this, "個人按讚資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
                    updateRnEndflagSetting(4);

//                    //test
//                    long count = mainDB.queryCount(PreThumbRow.class);
//                    ArrayList<PreThumbRow> list1 = mainDB.query(PreThumbRow.class);
//                    String str = String.valueOf(count) + "\n" + list1.get(0).getPData_thumb_datetime();
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                    //test

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
                    if (lastTime.after(clr.getTime())) {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                    } else {
                        lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime());
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
                        for (int i = 0; i < preMsgRows.size(); i++) {
                            mainDB.save(preMsgRows.get(i));
                        }

//                        //test
//                        long count = mainDB.queryCount(PreMsgRow.class);
//                        ArrayList<PreMsgRow> list1 = mainDB.query(PreMsgRow.class);
//                        String str = String.valueOf(count) + "\n" + list1.get(0).getPMsg_datetime();
//                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
//                        //test

                        mainDB.delete(new WhereBuilder(PreMsgRow.class)
                                .lessThan(PreMsgRow.PMSG_DATETIME,
                                        new MyDateSFormat().getFrmt_yMdHm().format(clr.getTime())));
                        LiteOrm.releaseMemory();
                    }
                    updateRnEndflagSetting(5);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //5.個人每日平均血壓PressAvgTable (僅登入者資料) end[6]
            ArrayList<PreAvgRow> preAvgRowArrayList = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                    .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreAvgRow.ID)
                    .limit(1, 1));
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
                        for (int i = 0; i < preAvgRows.size(); i++) {
                            mainDB.save(preAvgRows.get(i));
                        }

                        //test
                        long count = mainDB.queryCount(PreAvgRow.class);
                        ArrayList<PreAvgRow> list1 = mainDB.query(PreAvgRow.class);
                        String str = String.valueOf(count) + "\n" + list1.get(0).getPAvg_datetime();
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                        //test

                        mainDB.delete(new WhereBuilder(PreAvgRow.class)
                                .lessThan(PreAvgRow.PAVG_DATETIME,
                                        new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                        LiteOrm.releaseMemory();
                    } else {
                        Toast.makeText(MainActivity.this, "個人按讚資料更新失敗", Toast.LENGTH_SHORT).show();
                    }
                    updateRnEndflagSetting(6);
                }
            }.execute(signInShrPref.getSID(), lastDataTime);


            //6.個人血糖流水資料GlycemiaDataTable (僅登入者資料) end[7]


            //7.個人血糖按讚GlycemiaThumbTable (僅登入者資料) end[8]


            //8.個人血糖留言GlycemiaMsgTable (僅登入者資料) end[9]


            //9.個人每日平均血壓GlycemiaAvgTable (僅登入者資料) end[10]


            //10.APP使用者個人訊息表PersonalNoticeTable end[11]


            //11.APP使用者中心訊息表CenterNoticeTable end[12]


            //更新結束，若成功執行
            /**
             * 1.創或開Table
             * 2.搜尋最後一筆時間(沒資料->自定義抓取時間、有資料->使用資料時間抓取資料)
             * 3.將資料寫入SQLite
             */
            //更新結束，不論結果都要執行
            updateRnEndflagSetting(7);
            updateRnEndflagSetting(8);
            updateRnEndflagSetting(9);
            updateRnEndflagSetting(10);
            updateRnEndflagSetting(11);
            updateRnEndflagSetting(12);
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
        }
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
    //更新好友關係表、好友群組表
    private void UpdateFriendData() {
        if (signInShrPref.getSameSignInMachine()) { //不同裝置

            //更新動作


            //更新結束，若成功執行
            /**
             * 1.創或開Table
             * 2.搜尋最後一筆時間(沒資料->自定義抓取時間、有資料->使用資料時間抓取資料)
             * 3.將資料寫入SQLite
             */

            //更新結束，不論結果都要執行
            updateRnEndflagSetting(updateEndflag.length - 3);
            updateRnEndflagSetting(updateEndflag.length - 2);
        } else { //同裝置
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
    //更新 APP使用者中心留言板表CenterMessageTable
    private void UpdateCommunityData() {

        //更新動作


        //更新結束，若成功執行
        /**
         * 1.創或開Table
         * 2.搜尋最後一筆時間(沒資料->自定義抓取時間、有資料->使用資料時間抓取資料)
         * 3.將資料寫入SQLite
         */

        //更新結束，不論結果都要執行
        updateRnEndflagSetting(updateEndflag.length - 1);
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
