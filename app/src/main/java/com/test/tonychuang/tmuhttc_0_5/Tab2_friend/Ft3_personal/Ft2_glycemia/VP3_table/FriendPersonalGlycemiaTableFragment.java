package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP3_table;


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
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.FriendPersonalGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendPersonalGlycemiaTableFragment extends Fragment implements View.OnClickListener {

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
    private RadioButton bfBtn;
    private RadioButton afBtn;

    private MyDateSFormat myDateSFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;

    private String todayDateStr;
    private String oneWeekDateStr;
    private String twoWeekDateStr;
    private String oneMounthDateStr;
    private ArrayList<GlyDataRow> oneWeekData;
    private ArrayList<GlyDataRow> twoWeekData;
    private ArrayList<GlyDataRow> oneMounthData;
    private ArrayList<GlyDataRow> nowGlyDataRows;
    private SignInShrPref signInShrPref;
    private String friAid;
    private String friSid;
    private ArrayList<FRow> fRows;
    private FWLevelRow fwLevelRow;

    private String dataFlag = "allBtn";


    public FriendPersonalGlycemiaTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend_personal_glycemia_table, container, false);
        initView();
        initData();
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
                getGlyData();
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
        listViewItemName.setEnabled(false);
        listViewData = (ListView) view.findViewById(R.id.listViewData);

        allBtn = (RadioButton) view.findViewById(R.id.allBtn);
        bfBtn = (RadioButton) view.findViewById(R.id.bfBtn);
        afBtn = (RadioButton) view.findViewById(R.id.afBtn);
    }

    private void initBtn() {
        setDateBtn(sevenDayBtn);
        sevenDayBtn.setChecked(true);
        setDataRadioGroup();
        dataRadioGroup.check(R.id.allBtn);
        searchBtn.setEnabled(false);

        startDateTv.setText(oneWeekDateStr);
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setText(todayDateStr);
        endDateTv.setTextColor(unChangeTextColor);
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
                nowGlyDataRows = oneWeekData;
                startDateTv.setText(oneWeekDateStr);
                break;
            case R.id.forteenDayDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setEnabled(false);
                thirtyDayBtn.setChecked(false);
                showResult(twoWeekData);
                nowGlyDataRows = twoWeekData;
                startDateTv.setText(twoWeekDateStr);
                break;
            case R.id.thirtyDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setChecked(false);
                thirtyDayBtn.setEnabled(false);
                showResult(oneMounthData);
                nowGlyDataRows = oneMounthData;
                startDateTv.setText(oneMounthDateStr);
                break;
        }
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setText(todayDateStr);
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
                if (nowGlyDataRows != null) {
                    switch (checkedId) {
                        case R.id.allBtn:
                            dataFlag = "allBtn";
                            break;
                        case R.id.bfBtn:
                            dataFlag = "bfBtn";
                            break;
                        case R.id.afBtn:
                            dataFlag = "afBtn";
                            break;
                        default:
                            dataFlag = "allBtn";
                            break;
                    }
                    listViewItemName.setAdapter(
                            new FriendDataSearchTableGlycemiaItemNameListviewAdapter(getActivity()));
                    listViewData.setAdapter(
                            new FriendDataSearchTableGlycemiaListviewAdapter(getActivity(), nowGlyDataRows, dataFlag, friSid, signInShrPref.getAID()));
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
    private void showResult(ArrayList<GlyDataRow> glyDataRows) {
        int dataSize = glyDataRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            loadStatusText.setVisibility(View.VISIBLE);
            tableLayout.setVisibility(View.GONE);
            listViewItemName.setAdapter(
                    new FriendDataSearchTableGlycemiaItemNameListviewAdapter(getActivity()));
            setRadioGroupTF(false);
        } else {
            loadStatusText.setVisibility(View.GONE);
            tableLayout.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(
                    new FriendDataSearchTableGlycemiaItemNameListviewAdapter(getActivity()));
            listViewData.setAdapter(
                    new FriendDataSearchTableGlycemiaListviewAdapter(getActivity(), glyDataRows, dataFlag, friSid, signInShrPref.getAID()));
            setRadioGroupTF(true);
        }
    }

    private void setRadioGroupTF(boolean bool) {
        allBtn.setEnabled(bool);
        bfBtn.setEnabled(bool);
        afBtn.setEnabled(bool);
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        friAid = FriendPersonalGlycemiaActivity.friAid;
        friSid = FriendPersonalGlycemiaActivity.friSid;

        myDateSFormat = new MyDateSFormat();
        signInShrPref = new SignInShrPref(getActivity());

        DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
        fwLevelRow = mainDB.query(new QueryBuilder<FWLevelRow>(FWLevelRow.class)
                .whereEquals(FWLevelRow.FWLEVEL_SID, friSid)).get(0);

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        todayDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        oneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String oneWeekDateTimeStr = oneWeekDateStr + " 00:00";
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        twoWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String twoWeekDateTimeStr = twoWeekDateStr + " 00:00";
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.MONTH, -1);
        oneMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        String oneMounthDateTimeStr = oneMounthDateStr + " 00:00";

        oneWeekData = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, oneWeekDateTimeStr)
                .appendOrderAscBy(GlyDataRow.GDATA_DATETIME));
        LiteOrm.releaseMemory();
        twoWeekData = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, twoWeekDateTimeStr)
                .appendOrderAscBy(GlyDataRow.GDATA_DATETIME));
        LiteOrm.releaseMemory();
        oneMounthData = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, oneMounthDateTimeStr)
                .appendOrderAscBy(GlyDataRow.GDATA_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private void getGlyData() {
        String sqlStartTime = startDateTv.getText().toString() + " 00:00";
        String sqlEndTime = endDateTv.getText().toString() + " 23:59";

        new AsyncTask<String, Void, ArrayList<GlyDataRow>>() {
            @Override
            protected ArrayList<GlyDataRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<GlyDataRow> glyDataRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateGlycemiaListTableData(params[0], params[1], params[2]);
                    glyDataRows = jsonParser.parseGlyDataRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return glyDataRows;
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
            protected void onPostExecute(ArrayList<GlyDataRow> glyDataRows) {
                super.onPostExecute(glyDataRows);
                nowGlyDataRows = glyDataRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                dataRadioGroup.setEnabled(true);
                showResult(glyDataRows);
            }
        }.execute(friSid, sqlStartTime, sqlEndTime);
    }


    /**
     * d2
     */
    /**
     *
     */

}
