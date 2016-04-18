package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.ThbAidsAdapter;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press.FriendPersonalPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.FriendPersonalGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft3_medicine.FriendPersonalMedicineActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft4_report.FriendPersonalReportActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft5_pay.FriendPersonalPayActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft6_record.FriendPersonalRecordActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft7_msg.FriendMsgGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft7_msg.FriendMsgPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.FriGlycemiaPsnJudgment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.FriPressPsnJudgment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrDataFlagRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SMedRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SPayRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRcrdRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRprtRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

public class FriendPersonalActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private LinearLayout presslayout;
    private LinearLayout glycemialayout;
    private TextView pressValueText;
    private TextView glycemiaValueText;
    private TextView glycemiaMealText;
    private TextView pressDateText;
    private TextView glycemiaDateText;
    private TextView pressUnitText;
    private TextView glycemiaUnitText;
    private AutofitTextView pressThumbTv;
    private AutofitTextView glycemiaThumbTv;
    private FrameLayout pressMsgBtn;
    private FrameLayout glycemiaMsgBtn;
    private LinearLayout pressThbBtn;
    private LinearLayout glycemiaThbBtn;
    private LinearLayout pillLayout;
    private LinearLayout reportLayout;
    private LinearLayout payLayout;
    private LinearLayout recordLayout;
    private View grayV;

    private ActionBar actionBar;
    private MyInitReturnBar myInitReturnBar;

    /*更新資料所需容器pointer*/
    private String friAid;
    private String friSid;
    private String nickName;
    private SignInShrPref signInShrPref;
    private MyDateSFormat myDateSFormat;
    private FWLevelRow fwLevelRow;
    private MySyncingDialog mySyncingDialog;
    private Boolean[] updateEndflag;
    private ArrayList<FRow> fRows;
    private ArrayList<FShrDataFlagRow> fShrDataFlagRows;
    private ArrayList<PreThumbRow> preThumbRows;
    private ArrayList<GlyThumbRow> glyThumbRows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal);

        initView();
        initData();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.presslayout:
                intent.setClass(FriendPersonalActivity.this, FriendPersonalPressActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.glycemialayout:
                intent.setClass(FriendPersonalActivity.this, FriendPersonalGlycemiaActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.pressMsgBtn:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendMsgPressActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                startActivity(intent);
                break;
            case R.id.glycemiaMsgBtn:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendMsgGlycemiaActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                startActivity(intent);
                break;
            case R.id.pressThbBtn:
                pressThbPlus();
                break;
            case R.id.glycemiaThbBtn:
                glycemiaThbPlus();
                break;
            case R.id.pillLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalMedicineActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.reportLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalReportActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.payLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalPayActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.recordLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalRecordActivity.class);
                intent.putExtra("friAid", friAid);
                intent.putExtra("friSid", friSid);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.pressThbBtn:
                if (preThumbRows.get(0).getPData_thumb_count() != 0){
                    thumbAlertDialog("P");
                }
                break;
            case R.id.glycemiaThbBtn:
                if (glyThumbRows.get(0).getGData_thumb_count() != 0){
                    thumbAlertDialog("G");
                }
                break;
        }
        return true;
    }


    /**
     * v1
     */
    /**
     *
     */
    public void initBar(String title) {
        myInitReturnBar = new MyInitReturnBar(this, title, 0);
        actionBar = myInitReturnBar.getActionBar();
    }

    private void initView() {
        presslayout = (LinearLayout) findViewById(R.id.presslayout);
        assert presslayout != null;
        presslayout.setOnClickListener(this);
        glycemialayout = (LinearLayout) findViewById(R.id.glycemialayout);
        assert glycemialayout != null;
        glycemialayout.setOnClickListener(this);
        pressValueText = (TextView) findViewById(R.id.pressValueText);
        glycemiaValueText = (TextView) findViewById(R.id.glycemiaValueText);
        glycemiaMealText = (TextView) findViewById(R.id.glycemiaMealText);
        pressDateText = (TextView) findViewById(R.id.pressDateText);
        glycemiaDateText = (TextView) findViewById(R.id.glycemiaDateText);
        pressUnitText = (TextView) findViewById(R.id.pressUnitText);
        glycemiaUnitText = (TextView) findViewById(R.id.glycemiaUnitText);
        pressThumbTv = (AutofitTextView) findViewById(R.id.pressThumbTv);
        glycemiaThumbTv = (AutofitTextView) findViewById(R.id.glycemiaThumbTv);
        pressMsgBtn = (FrameLayout) findViewById(R.id.pressMsgBtn);
        assert pressMsgBtn != null;
        pressMsgBtn.setOnClickListener(this);
        glycemiaMsgBtn = (FrameLayout) findViewById(R.id.glycemiaMsgBtn);
        assert glycemiaMsgBtn != null;
        glycemiaMsgBtn.setOnClickListener(this);
        pressThbBtn = (LinearLayout) findViewById(R.id.pressThbBtn);
        assert pressThbBtn != null;
        pressThbBtn.setOnClickListener(this);
        pressThbBtn.setOnLongClickListener(this);
        glycemiaThbBtn = (LinearLayout) findViewById(R.id.glycemiaThbBtn);
        assert glycemiaThbBtn != null;
        glycemiaThbBtn.setOnClickListener(this);
        glycemiaThbBtn.setOnLongClickListener(this);
        pillLayout = (LinearLayout) findViewById(R.id.pillLayout);
        assert pillLayout != null;
        pillLayout.setOnClickListener(this);
        reportLayout = (LinearLayout) findViewById(R.id.reportLayout);
        assert reportLayout != null;
        reportLayout.setOnClickListener(this);
        payLayout = (LinearLayout) findViewById(R.id.payLayout);
        assert payLayout != null;
        payLayout.setOnClickListener(this);
        recordLayout = (LinearLayout) findViewById(R.id.recordLayout);
        assert recordLayout != null;
        recordLayout.setOnClickListener(this);
        grayV = findViewById(R.id.grayV);
    }

    /**
     * List選單 對話框
     */
    //之後改成加入參數(要塞入listView的資料)
    private void thumbAlertDialog(String Flag) {
        //之後把Layout改成listView(頭像+暱稱)，處理完按讚資料後，塞入listView
        View dialogView = LayoutInflater.from(FriendPersonalActivity.this).inflate(R.layout.dialog_thumd_aids, null);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        ListView aidsListView = (ListView) dialogView.findViewById(R.id.aidsListView);
        switch (Flag){
            case "P":
                aidsListView.setAdapter(new ThbAidsAdapter(FriendPersonalActivity.this, preThumbRows, Flag));
                break;
            case "G":
                aidsListView.setAdapter(new ThbAidsAdapter(FriendPersonalActivity.this, glyThumbRows, Flag));
                break;
        }
        final AlertDialog alertDialog = new AlertDialog.Builder(FriendPersonalActivity.this)
                .setTitle("按讚好友")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false)
                .create();
        alertDialog.show();

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private DialogInterface.OnKeyListener getOnKeyListener() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        };
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
     * 每次開啟FriPsnalAty，更新一次
     * 1.血壓按讚資料
     * 2.個人血壓留言PressMsgTable
     * 3.個人每日平均血壓PressAvgTable
     * 4.血糖按讚資料
     * 5.個人血糖留言GlycemiaMsgTable
     * 6.個人每日平均血糖GlycemiaAvgTable
     *
     * 7.個人用藥紀錄表ServiceMedicineTable
     * 8.個人健康報告書表ServiceReportTable
     * 9.個人繳費紀錄表ServicePayTable
     * 10.個人服務歷程紀錄表ServiceRecordTable
     */
    /**
     *
     */
    private void initData() {
        friAid = this.getIntent().getExtras().getString("friAid");

        myDateSFormat = new MyDateSFormat();
        signInShrPref = new SignInShrPref(this);

        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
        fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class).whereEquals(FRow.F_FRI_AID, friAid));
        fShrDataFlagRows = mainDB.query(new QueryBuilder<FShrDataFlagRow>(FShrDataFlagRow.class)
                .whereEquals(FShrDataFlagRow.FSHRDATA_FRI_AID, friAid));
        LiteOrm.releaseMemory();

        friSid = fRows.get(0).getF_fri_sid();
        nickName = fRows.get(0).getF_nickname();
        String title = fRows.get(0).getF_nickname();
        boolean shareAllCloseFlag = true;

        ArrayList<FWLevelRow> fwLevelRows = mainDB.query(new QueryBuilder<FWLevelRow>(FWLevelRow.class)
                .whereEquals(FWLevelRow.FWLEVEL_SID, friSid));
        if (fwLevelRows.size() != 0){
            fwLevelRow = fwLevelRows.get(0);
        } else {
            fwLevelRow.setFWLevel_Sid(friSid);
            fwLevelRow.setBP_SY_Max(140);
            fwLevelRow.setBP_SY_Min(110);
            fwLevelRow.setBP_DI_Max(90);
            fwLevelRow.setBP_DI_Min(60);
            fwLevelRow.setBP_HR_Max(100);
            fwLevelRow.setBP_HR_Min(60);
            fwLevelRow.setBP_SY_MaxDang(140);
            fwLevelRow.setBP_SY_MinDang(110);
            fwLevelRow.setBP_DI_MaxDang(90);
            fwLevelRow.setBP_DI_MinDang(110);
            fwLevelRow.setBP_HR_MaxDang(100);
            fwLevelRow.setBP_HR_MinDang(60);
            fwLevelRow.setBG_BM_Max(140);
            fwLevelRow.setBG_BM_Min(100);
            fwLevelRow.setBG_BM_MaxDang(140);
            fwLevelRow.setBG_BM_MinDang(100);
            fwLevelRow.setBG_AM_Max(140);
            fwLevelRow.setBG_AM_Min(100);
            fwLevelRow.setBG_AM_MaxDang(140);
            fwLevelRow.setBG_AM_MinDang(100);
            mainDB.save(fwLevelRow);
        }
        LiteOrm.releaseMemory();

        //對方刪除與自己的好友關係
        if (fRows.get(0).getF_relation_flag_fri() != 0) {
            initBar(title + " 關閉分享");
            grayV.setVisibility(View.VISIBLE);
            noShareData();
            noShareMed();
            noShareRprt();
            noSharePay();
            noShareRcrd();
        }
        //對方與自己是好友關係
        else {
            updateEndflag
                    = new Boolean[]{false, false, false, false, false,
                    false, false, false, false, false}; //重新啟動時初始化更新旗標 共10個

            mySyncingDialog = new MySyncingDialog(false, this, "正在更新好友資料，請稍後");
            mySyncingDialog.show();

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

            if (fShrDataFlagRows.get(0).getFShrData_data_flag().equals("Y")) {

                //* 1.血壓按讚資料
                ArrayList<PreThumbRow> preThumbRowArrayList = mainDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
                        .whereEquals(PreThumbRow.PDATA_THUMB_SID, friSid)
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
                            DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                            mainDB.delete(new WhereBuilder(PreThumbRow.class)
                                    .equals(PreThumbRow.PDATA_THUMB_SID, friSid));
                            if (preThumbRows.size() != 0) {
                                for (int i = 0; i < preThumbRows.size(); i++) {
                                    mainDB.save(preThumbRows.get(i));
                                }
                            }
                            LiteOrm.releaseMemory();
                            mainDB.close();
                        } else {
                            toast("好友血壓按讚資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(PreThumbRow.class);
//                        ArrayList<PreThumbRow> list1
//                                = mainDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
//                                .whereEquals(PreThumbRow.PDATA_THUMB_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getPData_thumb_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(0);
                    }
                }.execute(friSid, lastDataTime);

                //* 2.個人血壓留言PressMsgTable
                ArrayList<PreMsgRow> preMsgRowArrayList = mainDB.query(new QueryBuilder<PreMsgRow>(PreMsgRow.class)
                        .whereEquals(PreMsgRow.PMSG_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < preMsgRows.size(); i++) {
                                    mainDB.save(preMsgRows.get(i));
                                }
                                mainDB.delete(new WhereBuilder(PreMsgRow.class)
                                        .equals(PreMsgRow.PMSG_SID, friSid)
                                        .lessThan(PreMsgRow.PMSG_DATETIME, todayStr));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友血壓留言資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(PreMsgRow.class);
//                        ArrayList<PreMsgRow> list1
//                                = mainDB.query(new QueryBuilder<PreMsgRow>(PreMsgRow.class)
//                                .whereEquals(PreMsgRow.PMSG_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getPMsg_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(1);
                    }
                }.execute(friSid, lastDataTime);

                //* 3.個人每日平均血壓PressAvgTable
                ArrayList<PreAvgRow> preAvgRowArrayList = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                        .whereEquals(PreAvgRow.PAVG_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < preAvgRows.size(); i++) {
                                    mainDB.save(preAvgRows.get(i));
                                }
                                mainDB.delete(new WhereBuilder(PreAvgRow.class)
                                        .equals(PreAvgRow.PAVG_SID, friSid)
                                        .lessThan(PreAvgRow.PAVG_DATETIME,
                                                new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友平均血壓資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(PreAvgRow.class);
//                        ArrayList<PreAvgRow> list1
//                                = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
//                                .whereEquals(PreAvgRow.PAVG_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getPAvg_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(2);
                    }
                }.execute(friSid, lastDataTime);

                //* 4.血糖按讚資料
                ArrayList<GlyThumbRow> glyThumbRowArrayList = mainDB.query(new QueryBuilder<GlyThumbRow>(GlyThumbRow.class)
                        .whereEquals(GlyThumbRow.GDATA_THUMB_SID, friSid)
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
                            DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                            mainDB.delete(new WhereBuilder(GlyThumbRow.class)
                                    .equals(GlyThumbRow.GDATA_THUMB_SID, friSid));
                            if (glyThumbRows.size() != 0) {
                                for (int i = 0; i < glyThumbRows.size(); i++) {
                                    mainDB.save(glyThumbRows.get(i));
                                }
                            }
                            LiteOrm.releaseMemory();
                            mainDB.close();
                        } else {
                            toast("好友血糖按讚資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(GlyThumbRow.class);
//                        ArrayList<GlyThumbRow> list1
//                                = mainDB.query(new QueryBuilder<GlyThumbRow>(GlyThumbRow.class)
//                                .whereEquals(GlyThumbRow.GDATA_THUMB_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getGData_thumb_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(3);
                    }
                }.execute(friSid, lastDataTime);

                //* 5.個人血糖留言GlycemiaMsgTable
                ArrayList<GlyMsgRow> glyMsgRowArrayList = mainDB.query(new QueryBuilder<GlyMsgRow>(GlyMsgRow.class)
                        .whereEquals(GlyMsgRow.GMSG_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < glyMsgRows.size(); i++) {
                                    mainDB.save(glyMsgRows.get(i));
                                }
                                mainDB.delete(new WhereBuilder(GlyMsgRow.class)
                                        .equals(GlyMsgRow.GMSG_SID, friSid)
                                        .lessThan(GlyMsgRow.GMSG_DATETIME, todayStr));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友血糖留言資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(GlyMsgRow.class);
//                        ArrayList<GlyMsgRow> list1
//                                = mainDB.query(new QueryBuilder<GlyMsgRow>(GlyMsgRow.class)
//                                .whereEquals(GlyMsgRow.GMSG_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getGMsg_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(4);
                    }
                }.execute(friSid, lastDataTime);

                //* 6.個人每日平均血糖GlycemiaAvgTable
                ArrayList<GlyAvgRow> glyAvgRowArrayList = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                        .whereEquals(GlyAvgRow.GAVG_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < glyAvgRows.size(); i++) {
                                    mainDB.save(glyAvgRows.get(i));
                                }
                                mainDB.delete(new WhereBuilder(GlyAvgRow.class)
                                        .equals(GlyAvgRow.GAVG_SID, friSid)
                                        .lessThan(GlyAvgRow.GAVG_DATETIME,
                                                new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友平均血糖資料更新失敗");
                        }
//                       //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(GlyAvgRow.class);
//                        ArrayList<GlyAvgRow> list1
//                                = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
//                                .whereEquals(GlyAvgRow.GAVG_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getGAvg_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(5);
                    }
                }.execute(friSid, lastDataTime);

                shareAllCloseFlag = false;
            } else {
                noShareData();
                updateRnEndflagSetting(0);
                updateRnEndflagSetting(1);
                updateRnEndflagSetting(2);
                updateRnEndflagSetting(3);
                updateRnEndflagSetting(4);
                updateRnEndflagSetting(5);
            }

            if (fShrDataFlagRows.get(0).getFShrData_medicine_flag().equals("Y")) {
                //* 7.個人用藥紀錄表ServiceMedicineTable
                ArrayList<SMedRow> sMedRowArrayList = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
                        .whereEquals(SMedRow.SMED_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < sMedRows.size(); i++) {
                                    mainDB.save(sMedRows.get(i));
                                }
                                mainDB.delete(new WhereBuilder(SMedRow.class)
                                        .equals(SMedRow.SMED_SID, friSid)
                                        .and()
                                        .lessThan(SMedRow.SMED_DATETIME,
                                                new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友用藥資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(SMedRow.class);
//                        ArrayList<SMedRow> list1
//                                = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
//                                .whereEquals(SMedRow.SMED_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getSMed_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(6);
                    }
                }.execute(friSid, lastDataTime);

                shareAllCloseFlag = false;
            } else {
                noShareMed();
                updateRnEndflagSetting(6);
            }

            if (fShrDataFlagRows.get(0).getFShrData_report_flag().equals("Y")) {
                //* 8.個人健康報告書表ServiceReportTable
                ArrayList<SRprtRow> sRprtRowArrayList = mainDB.query(new QueryBuilder<SRprtRow>(SRprtRow.class)
                        .whereEquals(SRprtRow.SRPRT_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < sRprtRows.size(); i++) {
                                    mainDB.save(sRprtRows.get(i));
                                }
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
//                        toast("好友健康報告書資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(SRprtRow.class);
//                        ArrayList<SRprtRow> list1
//                                = mainDB.query(new QueryBuilder<SRprtRow>(SRprtRow.class)
//                                .whereEquals(SRprtRow.SRPRT_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getSRprt_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(7);
                    }
                }.execute(friSid, lastDataTime);

                shareAllCloseFlag = false;
            } else {
                noShareRprt();
                updateRnEndflagSetting(7);
            }

            if (fShrDataFlagRows.get(0).getFShrData_pay_flag().equals("Y")) {
                //* 9.個人繳費紀錄表ServicePayTable
                ArrayList<SPayRow> sPayRowArrayList = mainDB.query(new QueryBuilder<SPayRow>(SPayRow.class)
                        .whereEquals(SPayRow.SPAY_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < sPayRows.size(); i++) {
                                    mainDB.save(sPayRows.get(i));
                                }
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友繳費資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(SPayRow.class);
//                        ArrayList<SPayRow> list1
//                                = mainDB.query(new QueryBuilder<SPayRow>(SPayRow.class)
//                                .whereEquals(SPayRow.SPAY_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getSPay_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(8);
                    }
                }.execute(friSid, lastDataTime);

                shareAllCloseFlag = false;
            } else {
                noSharePay();
                updateRnEndflagSetting(8);
            }

            if (fShrDataFlagRows.get(0).getFShrData_record_flag().equals("Y")) {
                //* 10.個人服務歷程紀錄表ServiceRecordTable
                ArrayList<SRcrdRow> sRcrdRowArrayList = mainDB.query(new QueryBuilder<SRcrdRow>(SRcrdRow.class)
                        .whereEquals(SRcrdRow.SRCRD_SID, friSid)
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
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < sRcrdRows.size(); i++) {
                                    mainDB.save(sRcrdRows.get(i));
                                }

                                mainDB.delete(new WhereBuilder(SRcrdRow.class)
                                        .equals(SRcrdRow.SRCRD_SID, friSid)
                                        .lessThan(SMedRow.SMED_DATETIME,
                                                new MyDateSFormat().getFrmt_yMd().format(calendar.getTime())));
                                LiteOrm.releaseMemory();
                                mainDB.close();
                            }
                        } else {
                            toast("好友服務歷程資料更新失敗");
                        }
//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
//                        long count = mainDB.queryCount(SRcrdRow.class);
//                        ArrayList<SRcrdRow> list1
//                                = mainDB.query(new QueryBuilder<SRcrdRow>(SRcrdRow.class)
//                                .whereEquals(SRcrdRow.SRCRD_SID, friSid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        if (list1.size() != 0) {
//                            String str = String.valueOf(count) + "\n" + list1.get(0).getSRcrd_datetime();
//                            toast(str);
//                        } else {
//                            String str = String.valueOf(count);
//                            toast(str);
//                        }
//                        //test
                        updateRnEndflagSetting(9);
                    }
                }.execute(friSid, lastDataTime);

                shareAllCloseFlag = false;
            } else {
                noShareRcrd();
                updateRnEndflagSetting(9);
            }

            if (shareAllCloseFlag) {
                initBar(title + " 關閉分享");
                grayV.setVisibility(View.VISIBLE);
            } else {
                initBar(title);
                grayV.setVisibility(View.GONE);
            }
        }
        mainDB.close();
    }

    private void noShareData() {
        presslayout.setEnabled(false);
        pressThbBtn.setEnabled(false);
        pressMsgBtn.setEnabled(false);
        glycemialayout.setEnabled(false);
        glycemiaThbBtn.setEnabled(false);
        glycemiaMsgBtn.setEnabled(false);
        presslayout.setBackgroundResource(R.drawable.background_person_data_nodata_false);
        glycemialayout.setBackgroundResource(R.drawable.background_person_data_nodata_false);
    }

    private void noShareMed() {
        pillLayout.setEnabled(false);
        pillLayout.setBackgroundResource(R.drawable.background_person_service_button_close);
    }

    private void noShareRprt() {
        reportLayout.setEnabled(false);
        reportLayout.setBackgroundResource(R.drawable.background_person_service_button_close);
    }

    private void noSharePay() {
        payLayout.setEnabled(false);
        payLayout.setBackgroundResource(R.drawable.background_person_service_button_close);
    }

    private void noShareRcrd() {
        recordLayout.setEnabled(false);
        recordLayout.setBackgroundResource(R.drawable.background_person_service_button_close);
    }


    /**
     * d2
     */
    /**
     *
     */
    private void updateRnEndflagSetting(int Num) {
        updateEndflag[Num] = true;
        int endFlagCount = 0;

        for (Boolean anUpdateEndflag : updateEndflag) {
            if (anUpdateEndflag) {
                endFlagCount = endFlagCount + 1;
            }
        }

        if (endFlagCount >= updateEndflag.length) {
            mySyncingDialog.dismiss();
            initShowData();
        }
    }

    private void initShowData() {
        DataBase initShowDataDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.DAY_OF_MONTH, -1);
        String todayStr = myDateSFormat.getFrmt_yMd().format(new Date());
        todayStr = todayStr + " 00:00";

        /**
         * 血壓
         */
        ArrayList<PreDataRow> preDataRows = initShowDataDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, todayStr)
                .appendOrderDescBy(PreDataRow.PDATA_DATETIME)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        if (preDataRows.size() != 0) {
            //更新數據
            String pressValue = String.valueOf(preDataRows.get(0).getPData_sys()) + " / "
                    + String.valueOf(preDataRows.get(0).getPData_dia());
            pressValueText.setText(pressValue);
            String pressTime = preDataRows.get(0).getPData_datetime();
            try {
                pressTime = myDateSFormat.getFrmt_Mdahm()
                        .format(myDateSFormat.getFrmt_yMdHm().parse(pressTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pressDateText.setText(pressTime);
            pressUnitText.setVisibility(View.VISIBLE);

            //更新顏色
            FriPressPsnJudgment friPressPsnJudgment =
                    new FriPressPsnJudgment(fwLevelRow,
                            preDataRows.get(0).getPData_sys(),
                            preDataRows.get(0).getPData_dia());
            switch (friPressPsnJudgment.getResult()) {
                case "normal":
                    presslayout.setBackgroundResource(R.drawable.selector_presslayout);
                    break;
                case "warn":
                    presslayout.setBackgroundResource(R.drawable.selector_yellowlayout);
                    break;
                case "dang":
                    presslayout.setBackgroundResource(R.drawable.selector_redlayout);
                    break;
            }

            //更新按讚數
            preThumbRows = initShowDataDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
                    .whereEquals(PreThumbRow.PDATA_THUMB_TABLE_ID, preDataRows.get(0).getPData_table_id())
                    .appendOrderDescBy(PreThumbRow.PDATA_THUMB_DATETIME)
                    .limit(0, 1));
            LiteOrm.releaseMemory();
            if (preThumbRows.size() != 0) {
                if (preThumbRows.get(0).getPData_thumb_count() == 0) {
                    pressThumbTv.setVisibility(View.GONE);
                } else {
                    pressThumbTv.setVisibility(View.VISIBLE);
                    pressThumbTv.setText(String.valueOf(preThumbRows.get(0).getPData_thumb_count()));
                }

                //檢查是否按過讚
                String[] tokens = preThumbRows.get(0).getPData_thumb_aids().split(",");
                pressDataBtnsetting(true);
                for (String token : tokens) {
                    if (token.equals(signInShrPref.getAID())) {
                        setThbBtnFalseBackground(pressThbBtn);
                    }
                }
            } else {
                pressThumbTv.setVisibility(View.GONE);
                //設定按鈕
                pressDataBtnsetting(true);
            }
        } else {
            pressValueText.setText("今天未量測");
            presslayout.setBackgroundResource(R.drawable.selector_nodatalayout);
            pressDateText.setText("");
            pressUnitText.setVisibility(View.INVISIBLE);
            pressThumbTv.setVisibility(View.GONE);

            pressDataBtnsetting(false);
        }

        /**
         * 血糖
         */
        ArrayList<GlyDataRow> glyDataRows = initShowDataDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, todayStr)
                .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        if (glyDataRows.size() != 0) {
            //更新數據
            String glyData = glyDataRows.get(0).getGData_meal_flag();
            if (glyData.equals("af")) {
                glycemiaMealText.setText("飯後血糖");
            } else {
                glycemiaMealText.setText("飯前血糖");
            }
            String glyDataValue = String.valueOf(glyDataRows.get(0).getGData_value());
            glycemiaValueText.setText(glyDataValue);

            String glyDataTime = glyDataRows.get(0).getGData_datetime();
            try {
                glyDataTime = myDateSFormat.getFrmt_Mdahm()
                        .format(myDateSFormat.getFrmt_yMdHm().parse(glyDataTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            glycemiaDateText.setText(glyDataTime);
            glycemiaUnitText.setVisibility(View.VISIBLE);

            //更新顏色
            FriGlycemiaPsnJudgment friGlycemiaPsnJudgment =
                    new FriGlycemiaPsnJudgment(fwLevelRow,
                            glyDataRows.get(0).getGData_value(),
                            glyDataRows.get(0).getGData_meal_flag());
            switch (friGlycemiaPsnJudgment.getResult()) {
                case "normal":
                    glycemialayout.setBackgroundResource(R.drawable.selector_glycemialayout);
                    break;
                case "warn":
                    glycemialayout.setBackgroundResource(R.drawable.selector_yellowlayout);
                    break;
                case "dang":
                    glycemialayout.setBackgroundResource(R.drawable.selector_redlayout);
                    break;
            }

            //更新按讚數
            glyThumbRows = initShowDataDB.query(new QueryBuilder<GlyThumbRow>(GlyThumbRow.class)
                    .whereEquals(GlyThumbRow.GDATA_THUMB_TABLE_ID, glyDataRows.get(0).getGData_table_id())
                    .appendOrderDescBy(GlyThumbRow.GDATA_THUMB_DATETIME)
                    .limit(0, 1));
            LiteOrm.releaseMemory();
            if (glyThumbRows.size() != 0) {
                if (glyThumbRows.get(0).getGData_thumb_count() == 0) {
                    glycemiaThumbTv.setVisibility(View.GONE);
                } else {
                    glycemiaThumbTv.setVisibility(View.VISIBLE);
                    glycemiaThumbTv.setText(String.valueOf(glyThumbRows.get(0).getGData_thumb_count()));
                }

                //檢查是否按過讚
                String[] tokens = glyThumbRows.get(0).getGData_thumb_aids().split(",");
                glycemiaDataBtnsetting(true);
                for (String token : tokens) {
                    if (token.equals(signInShrPref.getAID())) {
                        setThbBtnFalseBackground(glycemiaThbBtn);
                    }
                }
            } else {
                glycemiaThumbTv.setVisibility(View.GONE);
                //更新按鈕
                glycemiaDataBtnsetting(true);
            }
        } else {
            glycemiaValueText.setText("今天未量測");
            glycemialayout.setBackgroundResource(R.drawable.selector_nodatalayout);
            glycemiaDateText.setText("");
            glycemiaUnitText.setVisibility(View.INVISIBLE);
            glycemiaThumbTv.setVisibility(View.GONE);

            glycemiaDataBtnsetting(false);
        }
        initShowDataDB.close();
    }


    /**
     * d3
     */
    /**
     *
     */
    private void pressDataBtnsetting(boolean bool) {
        pressThbBtn.setEnabled(bool);
        pressMsgBtn.setEnabled(bool);
        if (bool) {
            pressThbBtn.setBackgroundResource(R.drawable.selector_thumbbtn);
            pressMsgBtn.setBackgroundResource(R.drawable.selector_msgbtn);
        } else {
            pressThbBtn.setBackgroundResource(R.drawable.background_person_data_thumb_true);
            pressMsgBtn.setBackgroundResource(R.drawable.background_person_data_message_true);
        }
    }

    private void glycemiaDataBtnsetting(boolean bool) {
        glycemiaThbBtn.setEnabled(bool);
        glycemiaMsgBtn.setEnabled(bool);
        if (bool) {
            glycemiaThbBtn.setBackgroundResource(R.drawable.selector_thumbbtn);
            glycemiaMsgBtn.setBackgroundResource(R.drawable.selector_msgbtn);
        } else {
            glycemiaThbBtn.setBackgroundResource(R.drawable.background_person_data_thumb_true);
            glycemiaMsgBtn.setBackgroundResource(R.drawable.background_person_data_message_true);
        }
    }

    private void setThbBtnFalseBackground(LinearLayout linearLayout) {
        linearLayout.setBackgroundResource(R.drawable.background_person_data_thumb_true);
        linearLayout.setTag(R.drawable.background_person_data_thumb_true);
    }

    private void pressThbPlus() {
        if (getDrawableId(pressThbBtn) != R.drawable.background_person_data_thumb_true) {
            new AsyncTask<String, Void, ArrayList<PreThumbRow>>() {
                @Override
                protected ArrayList<PreThumbRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<PreThumbRow> preThumbRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.pressThumbPlus(params[0], params[1]);
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
                        if (preThumbRows.size() != 0){
                            DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                            mainDB.update(preThumbRows.get(0));
                            pressThumbTv.setText(String.valueOf(preThumbRows.get(0).getPData_thumb_count()));
                            pressThumbTv.setVisibility(View.VISIBLE);
                            setThbBtnFalseBackground(pressThbBtn);
                            FriendPersonalActivity.this.preThumbRows = new ArrayList<PreThumbRow>();
                            FriendPersonalActivity.this.preThumbRows.addAll(preThumbRows);
                            LiteOrm.releaseMemory();
                            mainDB.close();
                        }
                    } else {
                        toast("網路不穩，請稍後再試");
                    }
                }
            }.execute(String.valueOf(preThumbRows.get(0).getPData_thumb_table_id()), signInShrPref.getAID());
        }
    }

    private void glycemiaThbPlus() {
        if (getDrawableId(glycemiaThbBtn) != R.drawable.background_person_data_thumb_true) {
            new AsyncTask<String, Void, ArrayList<GlyThumbRow>>() {
                @Override
                protected ArrayList<GlyThumbRow> doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    ArrayList<GlyThumbRow> glyThumbRows = null;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.glycemiaThumbPlus(params[0], params[1]);
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
                        if (glyThumbRows.size() != 0){
                            DataBase mainDB = LiteOrm.newSingleInstance(FriendPersonalActivity.this, signInShrPref.getAID());
                            mainDB.update(glyThumbRows.get(0));
                            glycemiaThumbTv.setText(String.valueOf(glyThumbRows.get(0).getGData_thumb_count()));
                            glycemiaThumbTv.setVisibility(View.VISIBLE);
                            setThbBtnFalseBackground(glycemiaThbBtn);
                            FriendPersonalActivity.this.glyThumbRows = new ArrayList<GlyThumbRow>();
                            FriendPersonalActivity.this.glyThumbRows.addAll(glyThumbRows);
                            LiteOrm.releaseMemory();
                            mainDB.close();
                        }
                    } else {
                        toast("網路不穩，請稍後再試");
                    }
                }
            }.execute(String.valueOf(glyThumbRows.get(0).getGData_thumb_table_id()), signInShrPref.getAID());
        }
    }

    private int getDrawableId(LinearLayout linearLayout) {
        int result = 0;
        if(linearLayout.getTag() != null){
            result = (Integer) linearLayout.getTag();
        }
        return result;
    }

    private void toast(String msg) {
        Toast.makeText(FriendPersonalActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
