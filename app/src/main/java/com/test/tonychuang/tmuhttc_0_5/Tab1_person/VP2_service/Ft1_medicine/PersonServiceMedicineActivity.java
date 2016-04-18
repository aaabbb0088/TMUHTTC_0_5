package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft1_medicine;

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
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SMedRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PersonServiceMedicineActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private MyInitReturnBar myInitReturnBar;
    private ToggleButton sevenDayBtn;
    private ToggleButton forteenDayDayBtn;
    private ToggleButton fOneWeekBtn;
    private TextView loadStatusText;
    private ListView listViewItemName;
    private ListView listViewData;

    private MyDateSFormat myDateSFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;

    private String todayDateStr;
    private String oneWeekDateStr;
    private String twoWeekDateStr;
    private String fOneWeekDateStr;
    private ArrayList<SMedRow> oneWeekData;
    private ArrayList<SMedRow> twoWeekData;
    private ArrayList<SMedRow> fOneWeekData;
    private ArrayList<SMedRow> nowMedDataRows;

    private SignInShrPref signInShrPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_service_medicine);

        initView();
        initData();
        initBar();
        initBtn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(PersonServiceMedicineActivity.this, startDateTv, endDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.endDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(PersonServiceMedicineActivity.this, endDateTv, startDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.searchBtn:
                startDateTv.setTextColor(unChangeTextColor);
                endDateTv.setTextColor(unChangeTextColor);
                setDateBtntrue();
                getMedData();
                break;
            case R.id.sevenDayBtn:
                setDateBtn(sevenDayBtn);
                break;
            case R.id.forteenDayDayBtn:
                setDateBtn(forteenDayDayBtn);
                break;
            case R.id.fOneWeekBtn:
                setDateBtn(fOneWeekBtn);
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
        myInitReturnBar = new MyInitReturnBar(this, "用藥紀錄", 0);
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
        forteenDayDayBtn = (ToggleButton) findViewById(R.id.forteenDayDayBtn);
        forteenDayDayBtn.setOnClickListener(this);
        fOneWeekBtn = (ToggleButton) findViewById(R.id.fOneWeekBtn);
        fOneWeekBtn.setOnClickListener(this);
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
        forteenDayDayBtn.setEnabled(true);
        fOneWeekBtn.setEnabled(true);
        switch (Btn.getId()) {
            case R.id.sevenDayBtn:
                sevenDayBtn.setEnabled(false);
                forteenDayDayBtn.setChecked(false);
                fOneWeekBtn.setChecked(false);
                showResult(oneWeekData);
                nowMedDataRows = oneWeekData;
                startDateTv.setText(oneWeekDateStr);
                endDateTv.setText(todayDateStr);
                break;
            case R.id.forteenDayDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setEnabled(false);
                fOneWeekBtn.setChecked(false);
                showResult(twoWeekData);
                nowMedDataRows = twoWeekData;
                startDateTv.setText(twoWeekDateStr);
                endDateTv.setText(todayDateStr);
                break;
            case R.id.fOneWeekBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setChecked(false);
                fOneWeekBtn.setEnabled(false);
                showResult(fOneWeekData);
                nowMedDataRows = fOneWeekData;
                startDateTv.setText(todayDateStr);
                endDateTv.setText(fOneWeekDateStr);
                break;
        }
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setTextColor(unChangeTextColor);
    }

    private void setDateBtntrue() {
        sevenDayBtn.setEnabled(true);
        forteenDayDayBtn.setEnabled(true);
        fOneWeekBtn.setEnabled(true);
        sevenDayBtn.setChecked(false);
        forteenDayDayBtn.setChecked(false);
        fOneWeekBtn.setChecked(false);
    }

    private void setDateBtnfalse() {
        sevenDayBtn.setEnabled(false);
        forteenDayDayBtn.setEnabled(false);
        fOneWeekBtn.setEnabled(false);
        sevenDayBtn.setChecked(false);
        forteenDayDayBtn.setChecked(false);
        fOneWeekBtn.setChecked(false);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void showResult(ArrayList<SMedRow> sMedRows) {
        int dataSize = sMedRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            loadStatusText.setVisibility(View.VISIBLE);
            listViewData.setVisibility(View.GONE);
            listViewItemName.setAdapter(new PersonServiceMedicineItemNameListviewAdapter(PersonServiceMedicineActivity.this));
        } else {
            loadStatusText.setVisibility(View.GONE);
            listViewData.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(new PersonServiceMedicineItemNameListviewAdapter(PersonServiceMedicineActivity.this));
            listViewData.setAdapter(new PersonServiceMedicineListviewAdapter(PersonServiceMedicineActivity.this, sMedRows));
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
        String todayDateTimeStr = todayDateStr + " 00:00";
        String todayDateEndStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        todayDateEndStr = todayDateEndStr + " 23:59";
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        oneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String oneWeekDateTimeStr = oneWeekDateStr + " 00:00";
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        twoWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String twoWeekDateTimeStr = twoWeekDateStr + " 00:00";
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.WEEK_OF_MONTH, +1);
        fOneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String fOneWeekDateTimeStr = fOneWeekDateStr + " 23:59";

        oneWeekData = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
                .whereEquals(SMedRow.SMED_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(SMedRow.SMED_DATETIME, oneWeekDateTimeStr)
                .whereAppendAnd()
                .whereLessThan(SMedRow.SMED_DATETIME, todayDateEndStr)
                .appendOrderAscBy(SMedRow.SMED_DATETIME));
        LiteOrm.releaseMemory();
        twoWeekData = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
                .whereEquals(SMedRow.SMED_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(SMedRow.SMED_DATETIME, twoWeekDateTimeStr)
                .whereAppendAnd()
                .whereLessThan(SMedRow.SMED_DATETIME, todayDateEndStr)
                .appendOrderAscBy(SMedRow.SMED_DATETIME));
        LiteOrm.releaseMemory();
        fOneWeekData = mainDB.query(new QueryBuilder<SMedRow>(SMedRow.class)
                .whereEquals(SMedRow.SMED_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereLessThan(SMedRow.SMED_DATETIME, fOneWeekDateTimeStr)
                .whereAppendAnd()
                .whereGreaterThan(SMedRow.SMED_DATETIME, todayDateTimeStr)
                .appendOrderAscBy(SMedRow.SMED_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private void getMedData() {
        String sqlStartTime = startDateTv.getText().toString() + " 00:00";
        String sqlEndTime = endDateTv.getText().toString() + " 23:59";

        new AsyncTask<String, Void, ArrayList<SMedRow>>() {
            @Override
            protected ArrayList<SMedRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<SMedRow> sMedRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateMedicineListTableData(params[0], params[1], params[2]);
                    sMedRows = jsonParser.parseSMedRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sMedRows;
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
            protected void onPostExecute(ArrayList<SMedRow> sMedRows) {
                super.onPostExecute(sMedRows);
                nowMedDataRows = sMedRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                showResult(sMedRows);
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
