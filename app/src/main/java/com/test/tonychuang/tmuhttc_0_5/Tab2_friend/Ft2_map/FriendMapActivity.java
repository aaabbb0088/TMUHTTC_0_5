package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.timeruler.RulerWheel;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import me.grantland.widget.AutofitTextView;


/**
 * 1.日期選擇 : TEXTVIEW -> datepicker dialog
 * 2.時間選擇 : Ruler -> 外部控件
 * 3.Bar顯示被追蹤者暱稱
 * <p/>
 * <p/>
 * 開啟MapActivity時，先更新，補齊到最新資料(從上次最後一筆資料往後同步)，如果沒有資料，抓2天資料
 * 之後用多執行緒，每3分鐘下載一次資料
 */


public class FriendMapActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private MyInitReturnBar myInitReturnBar;
    private AutofitTextView dateMTv;
    private AutofitTextView dateDTv;
    private LinearLayout dateLy;
    private RulerWheel rulerView;
    private TextView tvCurValue;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private View grayV;

    //地圖相關
    //台北醫學大學附設醫院經緯度
    private LatLng sydney;
    private double logValue;
    private double latValue;
    private static double INITLAT = 25.026764;
    private static double INITLOG= 121.563142;

    private Date selectDate;
    private String selectDateStr;
    private int selectYint;
    private int selectMint;
    private int selectDint;

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
    private HashMap<String, Integer> timeToDataIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_map);

        initView();
        initViewData();
        updateAndShowData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateLy:
                clrDialog();
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initBar(String title) {
        myInitReturnBar = new MyInitReturnBar(this, title, 0);
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendMapActivity.this.finish();
            }
        });
    }

    private void initView() {
        dateMTv = (AutofitTextView) findViewById(R.id.dateMTv);
        dateDTv = (AutofitTextView) findViewById(R.id.dateDTv);
        dateLy = (LinearLayout) findViewById(R.id.dateLy);
        dateLy.setOnClickListener(this);
        tvCurValue = (TextView) findViewById(R.id.curValue_tv);
        rulerView = (RulerWheel) findViewById(R.id.ruler_view);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        grayV = findViewById(R.id.grayV);
    }

    private void clrDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(FriendMapActivity.this).create();
        dialog.show();
        DatePicker picker = new DatePicker(FriendMapActivity.this);
        picker.setDate(selectYint, selectMint);
        picker.setMode(DPMode.SINGLE);
        picker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.parseColor("#6673ADED"));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2.5F, paint);
            }
        });
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDatePicked(String date) {
                //格式 yyyy-M-d
                Date selectDate = new Date();
                try {
                    selectDate = myDateSFormat.getFrmt_GPS_yMd_Clr().parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String selectDateStr = myDateSFormat.getFrmt_yMd().format(selectDate);
                selectYint = Integer.valueOf(selectDateStr.substring(0, 4));
                selectMint = Integer.valueOf(selectDateStr.substring(5, 7));
                selectDint = Integer.valueOf(selectDateStr.substring(8));
                dateMTv.setText(numMonthToChiMonth(selectMint - 1) + "月");
                dateDTv.setText(String.valueOf(selectDint));

                updateTimeRuler(selectDateStr);

                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void updateTimeRuler(String selectDateStr) {
        DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());
        ArrayList<PsnLocRow> oneDayDataRows = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                .where(new WhereBuilder(PsnLocRow.class)
                        .equals(PsnLocRow.PSNLOC_AID, friAid)
                        .and()
                        .equals(PsnLocRow.PSNLOC_DATE, selectDateStr))
                .groupBy(PsnLocRow.PSNLOC_TIME)
                .orderBy(PsnLocRow.PSNLOC_TIME));
        LiteOrm.releaseMemory();
        mainDB.close();
        this.psnLocRows.clear();
        this.psnLocRows.addAll(oneDayDataRows);

        timeToDataIndex.clear();
        final List<String> oneDataTimelist = new ArrayList<>();
        for (int i = 0; i < oneDayDataRows.size(); i++) {
            oneDataTimelist.add(oneDayDataRows.get(i).getPsnLoc_time());
            timeToDataIndex.put(oneDayDataRows.get(i).getPsnLoc_time(), i);
        }
        initRulerViewData(oneDataTimelist);
        initRulerViewSelect(oneDataTimelist);
        rulerView.setScrollingListener(getOnWheelScrollListener(oneDataTimelist));

        initMapLocation(psnLocRows);
    }

    private RulerWheel.OnWheelScrollListener<String> getOnWheelScrollListener(final List<String> oneDataTimelist){
        return new RulerWheel.OnWheelScrollListener<String>() {
            @Override
            public void onChanged(RulerWheel wheel, String oldValue, String newValue) {
                tvCurValue.setText(newValue);
                if (oneDataTimelist.contains(newValue)) {
                    tvCurValue.setTextColor(Color.parseColor("#ff0989"));
                    int dataIndex = timeToDataIndex.get(newValue);
                    latValue = psnLocRows.get(dataIndex).getPsnLoc_latitude();
                    logValue = psnLocRows.get(dataIndex).getPsnLoc_longitude();
                    sydney = new LatLng(latValue, logValue);
                    mMap.clear();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    mMap.addMarker(new MarkerOptions().position(sydney)
                            .title(friendNickName)
                            .snippet("定位時間 " + psnLocRows.get(dataIndex).getPsnLoc_time()));
                } else {
                    tvCurValue.setTextColor(Color.GRAY);
                    mMap.clear();
                }
            }

            @Override
            public void onScrollingStarted(RulerWheel wheel) {

            }

            @Override
            public void onScrollingFinished(RulerWheel wheel) {

            }
        };
    }

    private String threeNumToDateString(int Y, int M, int D) {
        String dateStr = String.valueOf(Y) + "-";
        if (M < 10) {
            dateStr = dateStr + "0" + String.valueOf(M) + "-";
        } else {
            dateStr = dateStr + String.valueOf(M) + "-";
        }

        if (D < 10) {
            dateStr = dateStr + "0" + String.valueOf(D);
        } else {
            dateStr = dateStr + String.valueOf(D);
        }
        return dateStr;
    }


    /**
     * v2
     */
    /**
     *
     */
    private void initRulerView() {
        List<String> listTime = new ArrayList<>();
        String HH;
        String mm;
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                HH = "0" + String.valueOf(i);
            } else {
                HH = String.valueOf(i);
            }
            for (int j = 0; j < 60; j++) {
                if (j < 10) {
                    mm = "0" + String.valueOf(j);
                } else {
                    mm = String.valueOf(j);
                }
                listTime.add(HH + ":" + mm);
            }
        }
        rulerView.setData(listTime);
    }

    private void initMapLocation(ArrayList<PsnLocRow> psnLocRows){
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        if (psnLocRows.size() != 0) {
            sydney = new LatLng(psnLocRows.get(psnLocRows.size()-1).getPsnLoc_latitude()
                    ,psnLocRows.get(psnLocRows.size()-1).getPsnLoc_longitude());
            markerOptions.position(sydney).title(friendNickName).snippet("定位時間 " + psnLocRows.get(psnLocRows.size()-1).getPsnLoc_time());
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f));
        } else {
            sydney = new LatLng(INITLAT, INITLOG);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12f));
//            markerOptions.position(sydney).title("台北醫學大學附設醫院");
        }
    }


    /**
     * d1
     */
    /**
     *
     */
    @SuppressLint("SetTextI18n")
    private void initViewData() {
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

        Calendar clr = Calendar.getInstance();
        selectDate = clr.getTime();
        selectDateStr = myDateSFormat.getFrmt_yMdHm().format(selectDate);
        int Mounth = clr.get(Calendar.MONTH);
        int Day = clr.get(Calendar.DAY_OF_MONTH);
        dateMTv.setText(numMonthToChiMonth(Mounth) + "月");
        dateDTv.setText(String.valueOf(Day));
        selectYint = clr.get(Calendar.YEAR);
        selectMint = clr.get(Calendar.MONTH) + 1;

        timeToDataIndex = new HashMap<>();

        initRulerView();
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
                initBar(friendNickName + " - 定位資訊");
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
                                .appendOrderDescBy(PsnLocRow.ID)
                                .limit(0, 1));
                LiteOrm.releaseMemory();
                if (psnLocRowArrayList.size() != 0) {
                    String dataLastDateTime = psnLocRowArrayList.get(0).getPsnLoc_date() + " " + psnLocRowArrayList.get(0).getPsnLoc_time();
                    try {
                        lastTime = new MyDateSFormat().getFrmt_yMdHm().parse(dataLastDateTime);
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
                        mySyncingDialog = new MySyncingDialog(true, FriendMapActivity.this, "正在更新好友資料，請稍後");
                        mySyncingDialog.show();
                    }

                    @Override
                    protected void onPostExecute(ArrayList<PsnLocRow> psnLocRows) {
                        super.onPostExecute(psnLocRows);
                        if (psnLocRows != null) {
                            if (psnLocRows.size() != 0) {
                                DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());
                                for (int i = 0; i < psnLocRows.size(); i++) {
                                    mainDB.save(psnLocRows.get(i));
                                }
                                LiteOrm.releaseMemory();
                                mainDB.close();
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
                        showData();
                        mySyncingDialog.dismiss();
                    }
                }.execute(friAid, lastDataTime);


                mainDB.close();
            }
        }
    }

    private void showData() {
        DataBase mainDB = LiteOrm.newSingleInstance(FriendMapActivity.this, signInShrPref.getAID());

        //在月曆中標示有定位資料的日期
        ArrayList<PsnLocRow> dataDataFlagRows = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                .whereEquals(PsnLocRow.PSNLOC_AID, friAid)
                .groupBy(PsnLocRow.PSNLOC_DATE).orderBy(PsnLocRow.PSNLOC_DATE));
        LiteOrm.releaseMemory();
        ArrayList<String> dataFlagDates = new ArrayList<>();
        if (dataDataFlagRows.size() != 0) {
            String dataDateStr;
            Date dataDate = new Date();
            for (int i = 0; i < dataDataFlagRows.size(); i++) {
                dataDateStr = dataDataFlagRows.get(i).getPsnLoc_date();
                try {
                    dataDate = myDateSFormat.getFrmt_yMd().parse(dataDateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dataDateStr = myDateSFormat.getFrmt_GPS_yMd_Clr().format(dataDate);
                dataFlagDates.add(dataDateStr);
            }
        }
        DPCManager.getInstance().clearCache();
        DPCManager.getInstance().setDecorBG(dataFlagDates);
        MyClrTheme myClrTheme = new MyClrTheme();
        DPTManager.getInstance().initCalendar(myClrTheme);


        //初始，畫出時間軸，並在當天的時間軸中標示有資料的時刻
        Date todayDate = new Date();
        String todayStr = myDateSFormat.getFrmt_yMd().format(todayDate);
        ArrayList<PsnLocRow> todayDataRows = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                .where(new WhereBuilder(PsnLocRow.class)
                        .equals(PsnLocRow.PSNLOC_AID, friAid)
                        .and()
                        .equals(PsnLocRow.PSNLOC_DATE, todayStr))
                .groupBy(PsnLocRow.PSNLOC_TIME)
                .orderBy(PsnLocRow.PSNLOC_TIME));
        LiteOrm.releaseMemory();
        mainDB.close();
        this.psnLocRows = new ArrayList<>();
        this.psnLocRows.addAll(todayDataRows);

        timeToDataIndex.clear();
        final List<String> todayDataTimelist = new ArrayList<>();
        for (int i = 0; i < todayDataRows.size(); i++) {
            todayDataTimelist.add(todayDataRows.get(i).getPsnLoc_time());
            timeToDataIndex.put(todayDataRows.get(i).getPsnLoc_time(), i);
        }
        initRulerViewData(todayDataTimelist);
        initRulerViewSelect(todayDataTimelist);
        rulerView.setScrollingListener(getOnWheelScrollListener(todayDataTimelist));


        //初始地圖畫面
        initMapLocation(psnLocRows);
    }

    private void initRulerViewData(List<String> datalist) {
        List<String> listTime = new ArrayList<>();
        String HH;
        String mm;
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                HH = "0" + String.valueOf(i);
            } else {
                HH = String.valueOf(i);
            }
            for (int j = 0; j < 60; j++) {
                if (j < 10) {
                    mm = "0" + String.valueOf(j);
                } else {
                    mm = String.valueOf(j);
                }
                listTime.add(HH + ":" + mm);
            }
        }

        if (datalist.size() != 0) {
            List<Integer> listIndex = new ArrayList<>();
            for (int i = 0; i < datalist.size(); i++) {
                listIndex.add(listTime.indexOf(datalist.get(i)));
            }
            rulerView.setData(listTime, listIndex);
        } else {
            rulerView.setData(listTime);
        }
    }

    private void initRulerViewSelect(List<String> datalist) {
        tvCurValue.setTextColor(Color.parseColor("#797979"));
        if (datalist != null && datalist.size() != 0) {
            rulerView.setSelectedValue(datalist.get(datalist.size() - 1));
            tvCurValue.setText(datalist.get(datalist.size() - 1));
            tvCurValue.setTextColor(Color.parseColor("#ff0989"));
        } else {
            rulerView.setSelectedValue("12:00");
            tvCurValue.setText("12:00");
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    private void setUI(boolean bool) {
        dateLy.setEnabled(bool);
    }

    private String numMonthToChiMonth(int num) {
        String[] month = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        return month[num];
    }

    private void toast(String msg) {
        Toast.makeText(FriendMapActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
