package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.timescroller.Ruler;
import com.test.tonychuang.timescroller.RulerError;
import com.test.tonychuang.timescroller.RulerHandler;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrDataFlagRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PsnLocRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 1.日期選擇 : TEXTVIEW -> datepicker dialog
 * 2.時間選擇 : Ruler -> 外部控件
 * 3.Bar顯示被追蹤者暱稱
 * <p/>
 * <p/>
 * 開啟MapActivity時，先更新，補齊到最新資料(從上次最後一筆資料往後同步)，如果沒有資料，抓2天資料
 * 之後用多執行緒，每3分鐘下載一次資料
 */


public class FriendMapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView dateTv;
    private MyInitReturnBar myInitReturnBar;
    private Ruler timeRuler;
    private View grayV;

    private SignInShrPref signInShrPref;
    private String friAid;
    private String friName;
    private String friendNickName;
    private boolean memberFlag;
    private MyDateSFormat myDateSFormat;
    private MySyncingDialog mySyncingDialog;
    private ArrayList<FRow> fRows;
    private ArrayList<FShrDataFlagRow> fShrDataFlagRows;
    private ArrayList<PsnLocRow> psnLocRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_map);

        initView();
        initData();
        updateAndShowData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateTv:
                dateWheel();
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initBar(String title) {
        myInitReturnBar = new MyInitReturnBar(this, title, 0);
    }

    private void initView() {
        dateTv = (TextView) findViewById(R.id.dateTv);
        dateTv.setOnClickListener(this);
        timeRuler = (Ruler) findViewById(R.id.timeRuler);
        timeRuler.setRulerTag("          ");
        timeRuler.setRulerHandler(new RulerHandler() {
            @Override
            public void markText(String text) {

            }

            @Override
            public void error(RulerError error) {

            }
        });
        grayV = findViewById(R.id.grayV);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void dateWheel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DatePickerPopWin datePickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                Toast.makeText(FriendMapActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
            }
        }).textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#D7A500"))//color of confirm button
                .minYear(Calendar.getInstance().get(Calendar.YEAR)) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR)) // max year in loop
                .dateChose(dateFormat.format(new Date())) // date chose when init popwindow
                .build();
        datePickerPopWin.yearLoopView.setVisibility(View.GONE);
        datePickerPopWin.showPopWin(FriendMapActivity.this);
        datePickerPopWin.contentView.setClickable(false);
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(this);
        myDateSFormat = new MyDateSFormat();
        friAid = this.getIntent().getStringExtra("friAid");
        friName = this.getIntent().getStringExtra("friName");
        friendNickName = this.getIntent().getStringExtra("friendNickName");
        memberFlag = this.getIntent().getBooleanExtra("memberFlag", true);

        DataBase mainDB = LiteOrm.newSingleInstance(this, signInShrPref.getAID());
        fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class).whereEquals(FRow.F_FRI_AID, friAid));
        fShrDataFlagRows = mainDB.query(new QueryBuilder<FShrDataFlagRow>(FShrDataFlagRow.class)
                .whereEquals(FShrDataFlagRow.FSHRDATA_FRI_AID, friAid));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private void updateAndShowData() {
        if (fRows.get(0).getF_relation_flag_fri() != 0) {
            initBar(friendNickName + " 關閉定位分享");
            grayV.setVisibility(View.VISIBLE);
            setUI(false);
        } else {
            if (fShrDataFlagRows.get(0).getFShrData_location_flag().equals("N")) {
                initBar(friendNickName + " 關閉定位分享");
                grayV.setVisibility(View.VISIBLE);
                setUI(false);
            } else {
                initBar(friendNickName + "-定位資訊");
                grayV.setVisibility(View.GONE);
                setUI(true);

                final Calendar calendar = Calendar.getInstance(Locale.TAIWAN);
                calendar.add(Calendar.MONTH, -1);
                String lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());
                Date lastTime;

                final DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());
                ArrayList<PsnLocRow> psnLocRowArrayList =
                        mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                                .whereEquals(PsnLocRow.PSNLOC_AID, friAid)
                                .appendOrderDescBy(PsnLocRow.PSNLOC_DATETIME)
                                .limit(0, 1));
                LiteOrm.releaseMemory();
                if (psnLocRowArrayList.size() != 0) {
                    try {
                        lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(psnLocRowArrayList.get(0).getPsnLoc_datetime());
                        if (lastTime.after(calendar.getTime())) {
                            lastDataTime = new MyDateSFormat().getFrmt_yMdHm().format(lastTime);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                new AsyncTask<String, Void, ArrayList<PsnLocRow>>() {
                    @Override
                    protected ArrayList<PsnLocRow> doInBackground(String... params) {
                        HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                        JSONParser jsonParser = new JSONParser();
                        ArrayList<PsnLocRow> psnLocRows = null;

                        JSONObject jsonObject;
                        try {
                            jsonObject = httcjsonapi.LoadFriLocData(params[0], params[1]);
                            psnLocRows = jsonParser.parsePsnLocRow(jsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return psnLocRows;
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        mySyncingDialog = new MySyncingDialog(false, FriendMapActivity.this, "正在更新好友資料，請稍後");
                        mySyncingDialog.show();
                    }

                    @Override
                    protected void onPostExecute(ArrayList<PsnLocRow> psnLocRows) {
                        super.onPostExecute(psnLocRows);
                        mySyncingDialog.dismiss();
                        if (psnLocRows != null) {
                            if (psnLocRows.size() != 0) {
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < psnLocRows.size(); i++) {
                                    mainDB.save(psnLocRows.get(i));
                                }
                                LiteOrm.releaseMemory();
                                mainDB.close();
                                showData();
                            }
                        } else {
                            toast("好友定位資料更新失敗");
                        }

//                        //test
//                        DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());
//                        ArrayList<PsnLocRow> list = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
//                                .whereEquals(PsnLocRow.PSNLOC_AID,friAid));
//                        LiteOrm.releaseMemory();
//                        mainDB.close();
//                        toast("筆數 : " + String.valueOf(list.size()));
//                        //test

                    }
                }.execute(friAid, lastDataTime);


                mainDB.close();
            }
        }
    }

    private void showData() {

    }


    /**
     * d2
     */
    /**
     *
     */
    private void setUI(boolean bool) {
        dateTv.setEnabled(bool);
        timeRuler.setEnabled(bool);
    }

    private void toast(String msg) {
        Toast.makeText(FriendMapActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
