package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft4_record;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRcrdRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PersonServiceRecordActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private MyInitReturnBar myInitReturnBar;
    private ToggleButton sevenDayBtn;
    private ToggleButton thirtyDayBtn;
    private TextView loadStatusText;
    private ListView listViewItemName;
    private ListView listViewData;

    private MyDateSFormat myDateSFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;

    private String todayDateStr;
    private String oneWeekDateStr;
    private String oneMounthDateStr;
    private ArrayList<SRcrdRow> oneWeekData;
    private ArrayList<SRcrdRow> oneMounthData;
    private ArrayList<SRcrdRow> nowRcdDataRows;

    private SignInShrPref signInShrPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_service_record);

        initBar();
        initData();
        initView();
        initBtn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(PersonServiceRecordActivity.this, startDateTv, endDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.endDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(PersonServiceRecordActivity.this, endDateTv, startDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.searchBtn:
                startDateTv.setTextColor(unChangeTextColor);
                endDateTv.setTextColor(unChangeTextColor);
                setDateBtntrue();
                getRcdData();
                break;
            case R.id.sevenDayBtn:
                setDateBtn(sevenDayBtn);
                break;
            case R.id.thirtyDayBtn:
                setDateBtn(thirtyDayBtn);
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
        myInitReturnBar = new MyInitReturnBar(this, "服務歷程", 0);
    }

    private void initView() {
        startDateTv = (TextView) findViewById(R.id.startDateTv);
        startDateTv.setOnClickListener(this);
        endDateTv = (TextView) findViewById(R.id.endDateTv);
        endDateTv.setOnClickListener(this);
        searchBtn = (ImageButton) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
        sevenDayBtn = (ToggleButton) findViewById(R.id.sevenDayBtn);
        sevenDayBtn.setOnClickListener(this);
        thirtyDayBtn = (ToggleButton) findViewById(R.id.thirtyDayBtn);
        thirtyDayBtn.setOnClickListener(this);
        loadStatusText = (TextView) findViewById(R.id.loadStatusText);
        listViewItemName = (ListView) findViewById(R.id.listViewItemName);
        listViewItemName.setEnabled(false);
        listViewData = (ListView) findViewById(R.id.listViewData);
    }

    private void initBtn() {
        setDateBtn(sevenDayBtn);
        sevenDayBtn.setChecked(true);
        searchBtn.setEnabled(false);

        startDateTv.setText(oneWeekDateStr);
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setText(todayDateStr);
        endDateTv.setTextColor(unChangeTextColor);
    }

    private void setDateBtn(ToggleButton Btn) {
        sevenDayBtn.setEnabled(true);
        thirtyDayBtn.setEnabled(true);
        switch (Btn.getId()) {
            case R.id.sevenDayBtn:
                sevenDayBtn.setEnabled(false);
                thirtyDayBtn.setChecked(false);
                showResult(oneWeekData);
                nowRcdDataRows = oneWeekData;
                startDateTv.setText(oneWeekDateStr);
                endDateTv.setText(todayDateStr);
                break;
            case R.id.thirtyDayBtn:
                sevenDayBtn.setChecked(false);
                thirtyDayBtn.setEnabled(false);
                showResult(oneMounthData);
                nowRcdDataRows = oneMounthData;
                startDateTv.setText(todayDateStr);
                endDateTv.setText(oneMounthDateStr);
                break;
        }
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setTextColor(unChangeTextColor);
    }

    private void setDateBtntrue() {
        sevenDayBtn.setEnabled(true);
        thirtyDayBtn.setEnabled(true);
        sevenDayBtn.setChecked(false);
        thirtyDayBtn.setChecked(false);
    }

    private void setDateBtnfalse() {
        sevenDayBtn.setEnabled(false);
        thirtyDayBtn.setEnabled(false);
        sevenDayBtn.setChecked(false);
        thirtyDayBtn.setChecked(false);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void showResult(ArrayList<SRcrdRow> sRcrdRows) {
        int dataSize = sRcrdRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            loadStatusText.setVisibility(View.VISIBLE);
            listViewData.setVisibility(View.GONE);
            listViewItemName.setAdapter(new PersonServiceRecordItemNameListviewAdapter(this));
        } else {
            loadStatusText.setVisibility(View.GONE);
            listViewData.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(new PersonServiceRecordItemNameListviewAdapter(this));
            listViewData.setAdapter(new PersonServiceRecordListviewAdapter(this, sRcrdRows));
        }
    }



    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        myDateSFormat = new MyDateSFormat();
        signInShrPref = new SignInShrPref(this);

        DataBase mainDB = LiteOrm.newSingleInstance(this, signInShrPref.getAID());

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        todayDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String todayDateEndStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        todayDateEndStr = todayDateEndStr + " 23:59";
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        oneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String  oneWeekDateTimeStr = oneWeekDateStr + " 00:00";
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.MONTH, -1);
        oneMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String  oneMounthDateTimeStr = oneMounthDateStr + " 00:00";

        oneWeekData = mainDB.query(new QueryBuilder<SRcrdRow>(SRcrdRow.class)
                .where(new WhereBuilder(SRcrdRow.class)
                        .equals(SRcrdRow.SRCRD_SID, signInShrPref.getSID())
                        .and()
                        .greaterThan(SRcrdRow.SRCRD_DATETIME, oneWeekDateTimeStr)
                        .and()
                        .lessThan(SRcrdRow.SRCRD_DATETIME, todayDateEndStr)
                        .or()
                        .equals(SRcrdRow.SRCRD_SID, signInShrPref.getSID())
                        .and()
                        .greaterThan(SRcrdRow.SRCRD_DATETIME, oneWeekDateTimeStr)
                        .and()
                        .equals(SRcrdRow.SRCRD_DATETIME, todayDateEndStr))
                .appendOrderDescBy(SRcrdRow.SRCRD_DATETIME));
        LiteOrm.releaseMemory();
        oneMounthData = mainDB.query(new QueryBuilder<SRcrdRow>(SRcrdRow.class)
                .where(new WhereBuilder(SRcrdRow.class)
                        .equals(SRcrdRow.SRCRD_SID, signInShrPref.getSID())
                        .and()
                        .greaterThan(SRcrdRow.SRCRD_DATETIME, oneMounthDateTimeStr)
                        .and()
                        .lessThan(SRcrdRow.SRCRD_DATETIME, todayDateEndStr)
                        .or()
                        .equals(SRcrdRow.SRCRD_SID, signInShrPref.getSID())
                        .and()
                        .greaterThan(SRcrdRow.SRCRD_DATETIME, oneMounthDateTimeStr)
                        .and()
                        .equals(SRcrdRow.SRCRD_DATETIME, todayDateEndStr))
                .appendOrderDescBy(SRcrdRow.SRCRD_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private void getRcdData() {
        String sqlStartTime = startDateTv.getText().toString() + " 00:00";
        String sqlEndTime = endDateTv.getText().toString() + " 23:59";

        new AsyncTask<String, Void, ArrayList<SRcrdRow>>() {
            @Override
            protected ArrayList<SRcrdRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<SRcrdRow> sRcrdRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateRecordListTableData(params[0], params[1], params[2]);
                    sRcrdRows = jsonParser.parseSRcrdRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sRcrdRows;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                searchBtn.setEnabled(false);
                searchBtn.setImageResource(R.mipmap.chartsearch_g);
                setDateBtnfalse();
                loadStatusText.setText("資料載入中");
                loadStatusText.setVisibility(View.VISIBLE);
                listViewData.setVisibility(View.GONE);
            }

            @Override
            protected void onPostExecute(ArrayList<SRcrdRow> sRcrdRows) {
                super.onPostExecute(sRcrdRows);
                nowRcdDataRows = sRcrdRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                showResult(sRcrdRows);
            }
        }.execute(signInShrPref.getSID(), sqlStartTime, sqlEndTime);
    }


    /**
     * d2
     */
    /**
     *
     */

}
