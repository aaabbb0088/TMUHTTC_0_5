package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP3_table;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDataSearchTablePressFragment extends Fragment implements View.OnClickListener {


    private View view;
    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private ToggleButton sevenDayBtn;
    private ToggleButton forteenDayDayBtn;
    private ToggleButton thirtyDayBtn;
    private RadioGroup dataRadioGroup;
    private LinearLayout tableLayout;
    private TextView loadStatusText;
    private ListView listViewItemName;
    private ListView listViewData;
    private RadioButton allBtn;
    private RadioButton hBtn;
    private RadioButton lBtn;
    private RadioButton hrBtn;

    private SimpleDateFormat dateFormat;
    private MyDateSFormat myDateSFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;

    private ArrayList<PreDataRow> oneWeekData;
    private ArrayList<PreDataRow> twoWeekData;
    private ArrayList<PreDataRow> oneMounthData;
    private ArrayList<PreDataRow> nowPreDataRows;
    private WLevelShrPref wLevelShrPref;
    private SignInShrPref signInShrPref;

    private String dataFlag = "allBtn";


    public PersonDataSearchTablePressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data_search_table_press, container, false);
        initData();
        initView();
        initBtn();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(getActivity(), startDateTv, endDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.endDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(getActivity(), endDateTv, startDateTv, 2, 1);
                searchBtn.setEnabled(true);
                break;
            case R.id.searchBtn:
                startDateTv.setTextColor(unChangeTextColor);
                endDateTv.setTextColor(unChangeTextColor);
                setDateBtntrue();
                getPreData();
                break;
            case R.id.sevenDayBtn:
                setDateBtn(sevenDayBtn);
                break;
            case R.id.forteenDayDayBtn:
                setDateBtn(forteenDayDayBtn);
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
    private void initView() {
        startDateTv = (TextView) view.findViewById(R.id.startDateTv);
        startDateTv.setOnClickListener(this);
        endDateTv = (TextView) view.findViewById(R.id.endDateTv);
        endDateTv.setOnClickListener(this);
        searchBtn = (ImageButton) view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);

        //<code>test code</code>
        startDateTv.setText(dateFormat.format(new Date()));
        startDateTv.setTextColor(Color.GRAY);
        endDateTv.setText(dateFormat.format(new Date()));
        endDateTv.setTextColor(Color.GRAY);

        searchBtn = (ImageButton) view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
        sevenDayBtn = (ToggleButton) view.findViewById(R.id.sevenDayBtn);
        sevenDayBtn.setOnClickListener(this);
        forteenDayDayBtn = (ToggleButton) view.findViewById(R.id.forteenDayDayBtn);
        forteenDayDayBtn.setOnClickListener(this);
        thirtyDayBtn = (ToggleButton) view.findViewById(R.id.thirtyDayBtn);
        thirtyDayBtn.setOnClickListener(this);
        dataRadioGroup = (RadioGroup) view.findViewById(R.id.dataRadioGroup);
        tableLayout = (LinearLayout) view.findViewById(R.id.tableLayout);
        loadStatusText = (TextView) view.findViewById(R.id.loadStatusText);
        listViewItemName = (ListView) view.findViewById(R.id.listViewItemName);
        listViewData = (ListView) view.findViewById(R.id.listViewData);

        allBtn = (RadioButton) view.findViewById(R.id.allBtn);
        hBtn = (RadioButton) view.findViewById(R.id.hBtn);
        lBtn = (RadioButton) view.findViewById(R.id.lBtn);
        hrBtn = (RadioButton) view.findViewById(R.id.hrBtn);
    }

    private void initBtn() {
        setDateBtn(sevenDayBtn);
        sevenDayBtn.setChecked(true);
        setDataRadioGroup();
        dataRadioGroup.check(R.id.allBtn);
        searchBtn.setEnabled(false);
    }

    private void setDateBtn(ToggleButton Btn) {
        sevenDayBtn.setEnabled(true);
        forteenDayDayBtn.setEnabled(true);
        thirtyDayBtn.setEnabled(true);
        switch (Btn.getId()) {
            case R.id.sevenDayBtn:
                sevenDayBtn.setEnabled(false);
                forteenDayDayBtn.setChecked(false);
                thirtyDayBtn.setChecked(false);
                showResult(oneWeekData);
                nowPreDataRows = oneWeekData;
                break;
            case R.id.forteenDayDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setEnabled(false);
                thirtyDayBtn.setChecked(false);
                showResult(twoWeekData);
                nowPreDataRows = twoWeekData;
                break;
            case R.id.thirtyDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setChecked(false);
                thirtyDayBtn.setEnabled(false);
                showResult(oneMounthData);
                nowPreDataRows = oneMounthData;
                break;
        }
        startDateTv.setText(nowPreDataRows.get(0).getPData_datetime().substring(0,10));
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setText(nowPreDataRows.get(nowPreDataRows.size() - 1).getPData_datetime().substring(0, 10));
        endDateTv.setTextColor(unChangeTextColor);
    }

    private void setDateBtntrue() {
        sevenDayBtn.setEnabled(true);
        forteenDayDayBtn.setEnabled(true);
        thirtyDayBtn.setEnabled(true);
        sevenDayBtn.setChecked(false);
        forteenDayDayBtn.setChecked(false);
        thirtyDayBtn.setChecked(false);
    }

    private void setDateBtnfalse() {
        sevenDayBtn.setEnabled(false);
        forteenDayDayBtn.setEnabled(false);
        thirtyDayBtn.setEnabled(false);
        sevenDayBtn.setChecked(false);
        forteenDayDayBtn.setChecked(false);
        thirtyDayBtn.setChecked(false);
    }

    private void setDataRadioGroup() {
        dataRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (nowPreDataRows != null) {
                    switch (checkedId) {
                        case R.id.allBtn:
                            dataFlag = "allBtn";
                            break;
                        case R.id.hBtn:
                            dataFlag = "hBtn";
                            break;
                        case R.id.lBtn:
                            dataFlag = "lBtn";
                            break;
                        case R.id.hrBtn:
                            dataFlag = "hrBtn";
                            break;
                        default:
                            dataFlag = "allBtn";
                            break;
                    }
                    listViewItemName.setAdapter(
                            new PersonDataSearchTablePressItemNameListviewAdapter(getActivity(), dataFlag));
                    listViewData.setAdapter(
                            new PersonDataSearchTablePressListviewAdapter(getActivity(), nowPreDataRows, dataFlag));
                }
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private void showResult(ArrayList<PreDataRow> preDataRows) {
        int dataSize = preDataRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            setRadioGroupTF(false);
        } else {
            loadStatusText.setVisibility(View.GONE);
            tableLayout.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(
                    new PersonDataSearchTablePressItemNameListviewAdapter(getActivity(), dataFlag));
            listViewData.setAdapter(
                    new PersonDataSearchTablePressListviewAdapter(getActivity(), preDataRows, dataFlag));
            setRadioGroupTF(true);
        }
    }

    private void setRadioGroupTF(boolean bool) {
        allBtn.setEnabled(bool);
        hBtn.setEnabled(bool);
        lBtn.setEnabled(bool);
        hrBtn.setEnabled(bool);
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        myDateSFormat = new MyDateSFormat();
        wLevelShrPref = new WLevelShrPref(getActivity());
        signInShrPref = new SignInShrPref(getActivity());

        DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        String oneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        oneWeekDateStr = oneWeekDateStr + " 00:00";
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        String twoWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        twoWeekDateStr = twoWeekDateStr + " 00:00";
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.MONTH, -1);
        String oneMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        oneMounthDateStr = oneMounthDateStr + " 00:00";

        oneWeekData = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, oneWeekDateStr)
                .appendOrderAscBy(PreDataRow.PDATA_DATETIME));
        LiteOrm.releaseMemory();
        twoWeekData = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, twoWeekDateStr)
                .appendOrderAscBy(PreDataRow.PDATA_DATETIME));
        LiteOrm.releaseMemory();
        oneMounthData = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, oneMounthDateStr)
                .appendOrderAscBy(PreDataRow.PDATA_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private void getPreData() {
        String sqlStartTime = startDateTv.getText().toString() + " 00:00";
        String sqlEndTime = endDateTv.getText().toString() + " 23:59";

        new AsyncTask<String, Void, ArrayList<PreDataRow>>() {
            @Override
            protected ArrayList<PreDataRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<PreDataRow> preDataRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdatePressListTableData(params[0], params[1], params[2]);
                    preDataRows = jsonParser.parsePreDataRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return preDataRows;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                searchBtn.setEnabled(false);
                searchBtn.setImageResource(R.mipmap.chartsearch_g);
                setDateBtnfalse();
                dataRadioGroup.setEnabled(false);
                loadStatusText.setText("資料載入中");
                loadStatusText.setVisibility(View.VISIBLE);
                tableLayout.setVisibility(View.GONE);
            }

            @Override
            protected void onPostExecute(ArrayList<PreDataRow> preDataRows) {
                super.onPostExecute(preDataRows);
                nowPreDataRows = preDataRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                dataRadioGroup.setEnabled(true);
                showResult(preDataRows);
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
