package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP2_draw;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.FriendPersonalGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendPersonalGlycemiaDrawFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private ToggleButton sevenDayBtn;
    private ToggleButton forteenDayDayBtn;
    private ToggleButton thirtyDayBtn;
    private RadioGroup dataRadioGroup;
    private BarChart chartView;
    private Button resetChartBtn;
    private RelativeLayout drawLayout;
    private TextView loadStatusText;
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
    private ArrayList<GlyAvgRow> oneWeekData;
    private ArrayList<GlyAvgRow> twoWeekData;
    private ArrayList<GlyAvgRow> oneMounthData;
    private ArrayList<GlyAvgRow> nowGlyAvgRows;
    private SignInShrPref signInShrPref;
    private String friAid;
    private String friSid;
    private ArrayList<FRow> fRows;
    private FWLevelRow fwLevelRow;

    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_AM_Max;
    private int BG_AM_Min;

    /**
     * Charts的LimitLine
     */
    /**
     *
     */
    private LimitLine bULimitLine;
    private LimitLine bLLimitLine;
    private LimitLine aULimitLine;
    private LimitLine aLLimitLine;


    public FriendPersonalGlycemiaDrawFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend_personal_glycemia_draw, container, false);
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
        sevenDayBtn = (ToggleButton) view.findViewById(R.id.sevenDayBtn);
        sevenDayBtn.setOnClickListener(this);
        forteenDayDayBtn = (ToggleButton) view.findViewById(R.id.forteenDayDayBtn);
        forteenDayDayBtn.setOnClickListener(this);
        thirtyDayBtn = (ToggleButton) view.findViewById(R.id.thirtyDayBtn);
        thirtyDayBtn.setOnClickListener(this);
        dataRadioGroup = (RadioGroup) view.findViewById(R.id.dataRadioGroup);
        resetChartBtn = (Button) view.findViewById(R.id.resetChartBtn);
        resetChartBtn.setOnClickListener(this);

        chartView = (BarChart) view.findViewById(R.id.chartView);
        drawLayout = (RelativeLayout) view.findViewById(R.id.drawLayout);
        loadStatusText = (TextView) view.findViewById(R.id.loadStatusText);


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
        resetChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.fitScreen();
            }
        });

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
                nowGlyAvgRows = oneWeekData;
                startDateTv.setText(oneWeekDateStr);
                break;
            case R.id.forteenDayDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setEnabled(false);
                thirtyDayBtn.setChecked(false);
                showResult(twoWeekData);
                nowGlyAvgRows = twoWeekData;
                startDateTv.setText(twoWeekDateStr);
                break;
            case R.id.thirtyDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setChecked(false);
                thirtyDayBtn.setEnabled(false);
                showResult(oneMounthData);
                nowGlyAvgRows = oneMounthData;
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
                if (nowGlyAvgRows != null && nowGlyAvgRows.size() != 0) {
                    ArrayList<String> xData = new ArrayList<String>();
                    for (int index = 0; index < nowGlyAvgRows.size(); index++) {
                        Date date = new Date();
                        try {
                            date = myDateSFormat.getFrmt_yMd().parse(nowGlyAvgRows.get(index).getGAvg_datetime());
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        xData.add(index, myDateSFormat.getFrmt_Md().format(date));
                    }

                    YAxis leftAxis = chartView.getAxisLeft();
                    YAxis rightAxis = chartView.getAxisRight();
                    leftAxis.removeAllLimitLines();
                    ArrayList<IBarDataSet> ybarDataSets;
                    BarData barData = null;
                    float yMax;
                    switch (checkedId) {
                        case R.id.allBtn:
                            ybarDataSets = generateAllBarData(nowGlyAvgRows);
                            barData = new BarData(xData, ybarDataSets);
                            leftAxis.setDrawGridLines(true);
                            leftAxis.enableGridDashedLine(10f, 10f, 0f);
                            rightAxis.setDrawGridLines(true);
                            rightAxis.enableGridDashedLine(10f, 10f, 0f);

                            yMax = Math.max((float) BG_AM_Max, Math.max(ybarDataSets.get(0).getYMax(), ybarDataSets.get(1).getYMax()));
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(0f);
                            rightAxis.setDrawGridLines(true);
                            rightAxis.enableGridDashedLine(10f, 10f, 0f);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(0f);
                            break;
                        case R.id.bfBtn:
                            ybarDataSets = generateBfBarData(nowGlyAvgRows);
                            barData = new BarData(xData, ybarDataSets);
                            leftAxis.addLimitLine(bULimitLine);
                            leftAxis.addLimitLine(bLLimitLine);

                            yMax = Math.max((float) BG_BM_Max, ybarDataSets.get(0).getYMax());
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(0f);
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(0f);
                            break;
                        case R.id.afBtn:
                            ybarDataSets = generateAfBarData(nowGlyAvgRows);
                            barData = new BarData(xData, ybarDataSets);
                            leftAxis.addLimitLine(aULimitLine);
                            leftAxis.addLimitLine(aLLimitLine);

                            yMax = Math.max((float) BG_AM_Max, ybarDataSets.get(0).getYMax());
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(0f);
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(0f);
                            break;
                    }
                    chartView.setData(barData);
                    chartView.invalidate();
                    chartView.fitScreen();
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
    private void showResult(ArrayList<GlyAvgRow> glyAvgRows) {
        int dataSize = glyAvgRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            loadStatusText.setVisibility(View.VISIBLE);
            drawLayout.setVisibility(View.GONE);
            setRadioGroupTF(false);
        } else {
            loadStatusText.setVisibility(View.GONE);
            drawLayout.setVisibility(View.VISIBLE);
            DrawChart(glyAvgRows);
            setRadioGroupTF(true);
        }
    }

    private void setRadioGroupTF(boolean bool) {
        allBtn.setEnabled(bool);
        bfBtn.setEnabled(bool);
        afBtn.setEnabled(bool);
    }

    private void DrawChart(ArrayList<GlyAvgRow> glyAvgRows) {
        chartView.setDescription("");
        chartView.setBackgroundColor(Color.WHITE);
        chartView.setDrawGridBackground(false);
        chartView.setDrawBarShadow(false);
        chartView.setScaleYEnabled(false);
        chartView.setDoubleTapToZoomEnabled(false);

        YAxis rightAxis = chartView.getAxisRight();
        rightAxis.setStartAtZero(false);

        YAxis leftAxis = chartView.getAxisLeft();
        leftAxis.setStartAtZero(false);

        XAxis xAxis = chartView.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        ArrayList<String> xData = new ArrayList<String>();
        for (int index = 0; index < glyAvgRows.size(); index++) {
            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMd().parse(glyAvgRows.get(index).getGAvg_datetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xData.add(index, myDateSFormat.getFrmt_Md().format(date));
        }

        leftAxis.removeAllLimitLines();
        ArrayList<IBarDataSet> ybarDataSets;
        BarData barData = null;
        float yMax;
        switch (dataRadioGroup.getCheckedRadioButtonId()) {
            case R.id.allBtn:
                ybarDataSets = generateAllBarData(glyAvgRows);
                barData = new BarData(xData, ybarDataSets);
                leftAxis.setDrawGridLines(true);
                leftAxis.enableGridDashedLine(10f, 10f, 0f);
                rightAxis.setDrawGridLines(true);
                rightAxis.enableGridDashedLine(10f, 10f, 0f);

                yMax = Math.max(Float.valueOf(BG_AM_Max), Math.max(ybarDataSets.get(0).getYMax(), ybarDataSets.get(1).getYMax()));
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(0f);
                rightAxis.setDrawGridLines(true);
                rightAxis.enableGridDashedLine(10f, 10f, 0f);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(0f);
                break;
            case R.id.bfBtn:
                ybarDataSets = generateBfBarData(glyAvgRows);
                barData = new BarData(xData, ybarDataSets);
                leftAxis.addLimitLine(bULimitLine);
                leftAxis.addLimitLine(bLLimitLine);

                yMax = Math.max(Float.valueOf(BG_BM_Max), ybarDataSets.get(0).getYMax());
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(0f);
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(0f);
                break;
            case R.id.afBtn:
                ybarDataSets = generateAfBarData(glyAvgRows);
                barData = new BarData(xData, ybarDataSets);
                leftAxis.addLimitLine(aULimitLine);
                leftAxis.addLimitLine(aLLimitLine);

                yMax = Math.max(Float.valueOf(BG_AM_Max), ybarDataSets.get(0).getYMax());
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(0f);
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(0f);
                break;
        }
        MarkerView mv = new MyMarkerView(getActivity(), R.layout.marker_view);
        chartView.setMarkerView(mv);
        chartView.setAutoScaleMinMaxEnabled(true);
        chartView.setData(barData);
        chartView.invalidate();
        chartView.fitScreen();
    }

    /**
     * 構造一個類似Android Toast的彈出消息提示框。
     */
    private class MyMarkerView extends MarkerView {

        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            int n = (int) e.getVal();
            String content;
            if (n == 0) {
                content = "未量測";
            } else {
                content = String.valueOf(n);
            }
            tvContent.setText(content);
        }

        @Override
        public int getXOffset(float v) {
            return -(getWidth() / 2);
        }

        @Override
        public int getYOffset(float v) {
            return -getHeight();
        }
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
        BG_BM_Max = fwLevelRow.getBG_BM_Max();
        BG_BM_Min = fwLevelRow.getBG_BM_Min();
        BG_AM_Max = fwLevelRow.getBG_AM_Max();
        BG_AM_Min = fwLevelRow.getBG_AM_Min();

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        todayDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        oneWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        twoWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.MONTH, -1);
        oneMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());

        oneWeekData = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                .where(new WhereBuilder(PreAvgRow.class)
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .greaterThan(GlyAvgRow.GAVG_DATETIME, oneWeekDateStr)
                        .or()
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .equals(GlyAvgRow.GAVG_DATETIME, oneWeekDateStr))
                .appendOrderAscBy(GlyAvgRow.GAVG_DATETIME));
        LiteOrm.releaseMemory();
        twoWeekData = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                .where(new WhereBuilder(PreAvgRow.class)
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .greaterThan(GlyAvgRow.GAVG_DATETIME, twoWeekDateStr)
                        .or()
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .equals(GlyAvgRow.GAVG_DATETIME, twoWeekDateStr))
                .appendOrderAscBy(GlyAvgRow.GAVG_DATETIME));
        LiteOrm.releaseMemory();
        oneMounthData = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                .where(new WhereBuilder(PreAvgRow.class)
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .greaterThan(GlyAvgRow.GAVG_DATETIME, oneMounthDateStr)
                        .or()
                        .equals(GlyAvgRow.GAVG_SID, friSid)
                        .and()
                        .equals(GlyAvgRow.GAVG_DATETIME, oneMounthDateStr))
                .appendOrderAscBy(GlyAvgRow.GAVG_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();

        initLimitLines();
    }

    private void getPreData() {
        String sqlStartTime = startDateTv.getText().toString();
        String sqlEndTime = endDateTv.getText().toString();

        new AsyncTask<String, Void, ArrayList<GlyAvgRow>>() {
            @Override
            protected ArrayList<GlyAvgRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<GlyAvgRow> glyAvgRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateGlycemiaAvgdraweData(params[0], params[1], params[2]);
                    glyAvgRows = jsonParser.parseGlyAvgRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return glyAvgRows;
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
                drawLayout.setVisibility(View.GONE);
            }

            @Override
            protected void onPostExecute(ArrayList<GlyAvgRow> glyAvgRows) {
                super.onPostExecute(glyAvgRows);
                nowGlyAvgRows = glyAvgRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                dataRadioGroup.setEnabled(true);
                showResult(glyAvgRows);
            }
        }.execute(friSid, sqlStartTime, sqlEndTime);
    }


    /**
     * d2
     */
    /**
     *
     */
    private void initLimitLines() {
        bULimitLine = new LimitLine(BG_BM_Max, "飯前血糖上限 " + String.valueOf(BG_BM_Max));
        initUpLimitLine(bULimitLine);
        bLLimitLine = new LimitLine(BG_BM_Min, "飯前血糖下限 " + String.valueOf(BG_BM_Min));
        initLowLimitLine(bLLimitLine);
        aULimitLine = new LimitLine(BG_AM_Max, "飯後血糖上限 " + String.valueOf(BG_AM_Max));
        initUpLimitLine(aULimitLine);
        aLLimitLine = new LimitLine(BG_AM_Min, "飯後血糖下限 " + String.valueOf(BG_AM_Min));
        initLowLimitLine(aLLimitLine);
    }

    private void initLowLimitLine(LimitLine limitLine) {
        limitLine.setLineColor(Color.BLUE);
        limitLine.setLineWidth(2);
        limitLine.setTextColor(Color.BLACK);
        limitLine.setTextSize(12f);
        limitLine.enableDashedLine(10f, 10f, 0f);//設置為虛線
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);//設置Label位置
    }

    private void initUpLimitLine(LimitLine limitLine) {
        limitLine.setLineColor(Color.RED);
        limitLine.setLineWidth(2);
        limitLine.setTextColor(Color.BLACK);
        limitLine.setTextSize(12f);
        limitLine.enableDashedLine(10f, 10f, 0f);//設置為虛線
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);//設置Label位置
    }

    private ArrayList<IBarDataSet> generateAllBarData(ArrayList<GlyAvgRow> RowData) {

        ArrayList<IBarDataSet> d = new ArrayList<IBarDataSet>();

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        for (int index = 0; index < RowData.size(); index++) {
            entries1.add(new BarEntry(RowData.get(index).getGAvg_bm(), index));
        }
        BarDataSet set1 = new BarDataSet(entries1, "飯前血糖");
        set1.setColor(Color.rgb(65, 105, 225));
        set1.setValueTextColor(Color.rgb(65, 105, 225));
        set1.setValueTextSize(0);
        set1.setValueFormatter(new IntegerValueFormatter());
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.add(set1);

        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        for (int index = 0; index < RowData.size(); index++) {
            entries2.add(new BarEntry(RowData.get(index).getGAvg_am(), index));
        }
        BarDataSet set2 = new BarDataSet(entries2, "飯後血糖");
        set2.setColor(Color.rgb(255, 69, 0));
        set2.setValueTextColor(Color.rgb(255, 69, 0));
        set2.setValueTextSize(0);
        set2.setValueFormatter(new IntegerValueFormatter());
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.add(set2);

        return d;
    }

    private ArrayList<IBarDataSet> generateBfBarData(ArrayList<GlyAvgRow> RowData) {

        ArrayList<IBarDataSet> d = new ArrayList<IBarDataSet>();

        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
        for (int index = 0; index < RowData.size(); index++) {
            entries1.add(new BarEntry(RowData.get(index).getGAvg_bm(), index));
        }
        BarDataSet set1 = new BarDataSet(entries1, "飯前血糖");
        set1.setColor(Color.rgb(65, 105, 225));
        set1.setValueTextColor(Color.rgb(65, 105, 225));
        set1.setValueTextSize(0f);
        set1.setValueFormatter(new IntegerValueFormatter());
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.add(set1);

        return d;
    }

    private ArrayList<IBarDataSet> generateAfBarData(ArrayList<GlyAvgRow> RowData) {

        ArrayList<IBarDataSet> d = new ArrayList<IBarDataSet>();

        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        for (int index = 0; index < RowData.size(); index++) {
            entries2.add(new BarEntry(RowData.get(index).getGAvg_am(), index));
        }
        BarDataSet set2 = new BarDataSet(entries2, "飯後血糖");
        set2.setColor(Color.rgb(255, 69, 0));
        set2.setValueTextColor(Color.rgb(255, 69, 0));
        set2.setValueTextSize(0f);
        set2.setValueFormatter(new IntegerValueFormatter());
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.add(set2);

        return d;
    }

    private class IntegerValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public IntegerValueFormatter() {
            mFormat = new DecimalFormat("###");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
    }

}
