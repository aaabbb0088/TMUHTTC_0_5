package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP2_draw;


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

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDataSearchDrawPressFragment extends Fragment implements View.OnClickListener {


    private View view;
    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private ToggleButton sevenDayBtn;
    private ToggleButton forteenDayDayBtn;
    private ToggleButton thirtyDayBtn;
    private RadioGroup dataRadioGroup;
    private CombinedChart chartView;
    private Button resetChartBtn;
    private RelativeLayout drawLayout;
    private TextView loadStatusText;
    private RadioButton allBtn;
    private RadioButton hBtn;
    private RadioButton lBtn;
    private RadioButton hrBtn;

    private SimpleDateFormat dateFormat;
    private MyDateSFormat myDateSFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;

    private ArrayList<PreAvgRow> oneWeekData;
    private ArrayList<PreAvgRow> twoWeekData;
    private ArrayList<PreAvgRow> oneMounthData;
    private ArrayList<PreAvgRow> nowPreAvgRows;
    private WLevelShrPref wLevelShrPref;
    private SignInShrPref signInShrPref;


    private int BP_SY_Max;
    private int BP_SY_Min;
    private int BP_DI_Max;
    private int BP_DI_Min;
    private int BP_HR_Max;
    private int BP_HR_Min;

    /**
     * Charts的LimitLine
     */
    /**
     *
     */
    private LimitLine hULimitLine;
    private LimitLine hLLimitLine;
    private LimitLine lULimitLine;
    private LimitLine lLLimitLine;
    private LimitLine hrULimitLine;
    private LimitLine hrLLimitLine;


    public PersonDataSearchDrawPressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data_search_draw_press, container, false);
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
        sevenDayBtn = (ToggleButton) view.findViewById(R.id.sevenDayBtn);
        sevenDayBtn.setOnClickListener(this);
        forteenDayDayBtn = (ToggleButton) view.findViewById(R.id.forteenDayDayBtn);
        forteenDayDayBtn.setOnClickListener(this);
        thirtyDayBtn = (ToggleButton) view.findViewById(R.id.thirtyDayBtn);
        thirtyDayBtn.setOnClickListener(this);
        dataRadioGroup = (RadioGroup) view.findViewById(R.id.dataRadioGroup);
        resetChartBtn = (Button) view.findViewById(R.id.resetChartBtn);
        resetChartBtn.setOnClickListener(this);

        chartView = (CombinedChart) view.findViewById(R.id.chartView);
        drawLayout = (RelativeLayout) view.findViewById(R.id.drawLayout);
        loadStatusText = (TextView) view.findViewById(R.id.loadStatusText);

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
        resetChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.fitScreen();
            }
        });
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
                nowPreAvgRows = oneWeekData;
                break;
            case R.id.forteenDayDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setEnabled(false);
                thirtyDayBtn.setChecked(false);
                showResult(twoWeekData);
                nowPreAvgRows = twoWeekData;
                break;
            case R.id.thirtyDayBtn:
                sevenDayBtn.setChecked(false);
                forteenDayDayBtn.setChecked(false);
                thirtyDayBtn.setEnabled(false);
                showResult(oneMounthData);
                nowPreAvgRows = oneMounthData;
                break;
        }
        startDateTv.setText(nowPreAvgRows.get(0).getPAvg_datetime());
        startDateTv.setTextColor(unChangeTextColor);
        endDateTv.setText(nowPreAvgRows.get(nowPreAvgRows.size() - 1).getPAvg_datetime());
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
                if (nowPreAvgRows != null) {
                    ArrayList<String> xData = new ArrayList<String>();
                    for (int index = 0; index < nowPreAvgRows.size(); index++) {
                        Date date = new Date();
                        try {
                            date = myDateSFormat.getFrmt_yMd().parse(nowPreAvgRows.get(index).getPAvg_datetime());
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        xData.add(index, myDateSFormat.getFrmt_Md().format(date));
                    }
                    CombinedData yData = new CombinedData(xData);

                    YAxis leftAxis = chartView.getAxisLeft();
                    YAxis rightAxis = chartView.getAxisRight();
                    leftAxis.removeAllLimitLines();
                    LineData lineData;
                    BarData barData;
                    float yMax;
                    float yMin;
                    switch (checkedId) {
                        case R.id.allBtn:
                            lineData = generateHLLineData(nowPreAvgRows);
                            barData = generateHrBarDataAll(nowPreAvgRows);
                            yData.setData(lineData);
                            yData.setData(barData);
                            leftAxis.setDrawGridLines(true);
                            leftAxis.enableGridDashedLine(10f, 10f, 0f);
                            rightAxis.setDrawGridLines(true);
                            rightAxis.enableGridDashedLine(10f, 10f, 0f);

                            yMax = Math.max(Math.max(Float.valueOf(BP_SY_Max), lineData.getYMax()), barData.getYMax());
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(0f);
                            rightAxis.setDrawGridLines(true);
                            rightAxis.enableGridDashedLine(10f, 10f, 0f);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(0f);
                            break;
                        case R.id.hBtn:
                            lineData = generateHLineData(nowPreAvgRows);
                            yData.setData(lineData);
                            leftAxis.addLimitLine(hULimitLine);
                            leftAxis.addLimitLine(hLLimitLine);

                            yMax = Math.max(Float.valueOf(BP_SY_Max), lineData.getYMax());
                            yMin = Math.min(Float.valueOf(BP_SY_Min), lineData.getYMax());
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(yMin - 30f);
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(yMin - 30f);
                            break;
                        case R.id.lBtn:
                            lineData = generateLLineData(nowPreAvgRows);
                            yData.setData(lineData);
                            leftAxis.addLimitLine(lULimitLine);
                            leftAxis.addLimitLine(lLLimitLine);

                            yMax = Math.max(Float.valueOf(BP_DI_Max), lineData.getYMax());
                            yMin = Math.min(Float.valueOf(BP_DI_Min), lineData.getYMax());
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(yMin - 30f);
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(yMin - 30f);
                            break;
                        case R.id.hrBtn:
                            barData = generateHrBarData(nowPreAvgRows);
                            yData.setData(barData);
                            leftAxis.addLimitLine(hrULimitLine);
                            leftAxis.addLimitLine(hrLLimitLine);

                            yMax = Math.max(Float.valueOf(BP_HR_Max), barData.getYMax());
                            yMin = Math.min(Float.valueOf(BP_HR_Min), barData.getYMax());
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMaxValue(yMax + 30f);
                            leftAxis.setAxisMinValue(yMin - 30f);
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMaxValue(yMax + 30f);
                            rightAxis.setAxisMinValue(yMin - 30f);
                            break;
                    }
                    chartView.setData(yData);
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
    private void showResult(ArrayList<PreAvgRow> preAvgRows) {
        int dataSize = preAvgRows.size();
        if (dataSize == 0) {
            loadStatusText.setText("無資料");
            setRadioGroupTF(false);
        } else {
            loadStatusText.setVisibility(View.GONE);
            drawLayout.setVisibility(View.VISIBLE);
            DrawChart(preAvgRows);
            setRadioGroupTF(true);
        }
    }

    private void setRadioGroupTF(boolean bool) {
        allBtn.setEnabled(bool);
        hBtn.setEnabled(bool);
        lBtn.setEnabled(bool);
        hrBtn.setEnabled(bool);
    }

    private void DrawChart(ArrayList<PreAvgRow> preAvgRows) {
        chartView.setDescription("");
        chartView.setBackgroundColor(Color.WHITE);
        chartView.setDrawGridBackground(false);
        chartView.setDrawBarShadow(false);
        chartView.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE,
                CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER});
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
        for (int index = 0; index < preAvgRows.size(); index++) {
            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMd().parse(preAvgRows.get(index).getPAvg_datetime());
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            xData.add(index, myDateSFormat.getFrmt_Md().format(date));
        }
        CombinedData yData = new CombinedData(xData);

        leftAxis.removeAllLimitLines();
        LineData lineData;
        BarData barData;
        float yMax;
        float yMin;
        switch (dataRadioGroup.getCheckedRadioButtonId()) {
            case R.id.allBtn:
                lineData = generateHLLineData(preAvgRows);
                barData = generateHrBarDataAll(preAvgRows);
                yData.setData(lineData);
                yData.setData(barData);
                leftAxis.setDrawGridLines(true);
                leftAxis.enableGridDashedLine(10f, 10f, 0f);
                rightAxis.setDrawGridLines(true);
                rightAxis.enableGridDashedLine(10f, 10f, 0f);

                yMax = Math.max(Math.max(Float.valueOf(BP_SY_Max), lineData.getYMax()), barData.getYMax());
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(0f);
                rightAxis.setDrawGridLines(true);
                rightAxis.enableGridDashedLine(10f, 10f, 0f);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(0f);
                break;
            case R.id.hBtn:
                lineData = generateHLineData(preAvgRows);
                yData.setData(lineData);
                leftAxis.addLimitLine(hULimitLine);
                leftAxis.addLimitLine(hLLimitLine);

                yMax = Math.max(Float.valueOf(BP_SY_Max), lineData.getYMax());
                yMin = Math.min(Float.valueOf(BP_SY_Min), lineData.getYMin());
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(yMin - 30f);
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(yMin - 30f);
                break;
            case R.id.lBtn:
                lineData = generateLLineData(preAvgRows);
                yData.setData(lineData);
                leftAxis.addLimitLine(lULimitLine);
                leftAxis.addLimitLine(lLLimitLine);

                yMax = Math.max(Float.valueOf(BP_DI_Max), lineData.getYMax());
                yMin = Math.min(Float.valueOf(BP_DI_Min), lineData.getYMin());
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(yMin - 30f);
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(yMin - 30f);
                break;
            case R.id.hrBtn:
                barData = generateHrBarData(preAvgRows);
                yData.setData(barData);
                leftAxis.addLimitLine(hrULimitLine);
                leftAxis.addLimitLine(hrLLimitLine);

                yMax = Math.max(Float.valueOf(BP_HR_Max), barData.getYMax());
                yMin = Math.min(Float.valueOf(BP_HR_Min), barData.getYMin());
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaxValue(yMax + 30f);
                leftAxis.setAxisMinValue(yMin - 30f);
                rightAxis.setDrawGridLines(false);
                rightAxis.setAxisMaxValue(yMax + 30f);
                rightAxis.setAxisMinValue(yMin - 30f);
                break;
        }
        chartView.setAutoScaleMinMaxEnabled(true);
        chartView.setData(yData);
        chartView.invalidate();
        chartView.fitScreen();
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
        clr.add(Calendar.WEEK_OF_MONTH, -1);
        String twoWeekDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        clr = Calendar.getInstance(Locale.TAIWAN);
        clr.add(Calendar.MONTH, -1);
        String oneMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());

        oneWeekData = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .where(new WhereBuilder(PreAvgRow.class)
                        .greaterThan(PreAvgRow.PAVG_DATETIME, oneWeekDateStr)
                        .or()
                        .equals(PreAvgRow.PAVG_DATETIME, oneWeekDateStr))
                .appendOrderAscBy(PreAvgRow.PAVG_DATETIME));
        LiteOrm.releaseMemory();
        twoWeekData = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .where(new WhereBuilder(PreAvgRow.class)
                        .greaterThan(PreAvgRow.PAVG_DATETIME, twoWeekDateStr)
                        .or()
                        .equals(PreAvgRow.PAVG_DATETIME, twoWeekDateStr))
                .appendOrderAscBy(PreAvgRow.PAVG_DATETIME));
        LiteOrm.releaseMemory();
        oneMounthData = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .where(new WhereBuilder(PreAvgRow.class)
                        .greaterThan(PreAvgRow.PAVG_DATETIME, oneMounthDateStr)
                        .or()
                        .equals(PreAvgRow.PAVG_DATETIME, oneMounthDateStr))
                .appendOrderAscBy(PreAvgRow.PAVG_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();

        BP_SY_Max = wLevelShrPref.getBP_SY_Max();
        BP_SY_Min = wLevelShrPref.getBP_SY_Min();
        BP_DI_Max = wLevelShrPref.getBP_DI_Max();
        BP_DI_Min = wLevelShrPref.getBP_DI_Min();
        BP_HR_Max = wLevelShrPref.getBP_HR_Max();
        BP_HR_Min = wLevelShrPref.getBP_HR_Min();

        initLimitLines();
    }

    private void getPreData() {
        String sqlStartTime = startDateTv.getText().toString();
        String sqlEndTime = endDateTv.getText().toString();

        new AsyncTask<String, Void, ArrayList<PreAvgRow>>() {
            @Override
            protected ArrayList<PreAvgRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<PreAvgRow> preAvgRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdatePressAvgdraweData(params[0], params[1], params[2]);
                    preAvgRows = jsonParser.parsePreAvgRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return preAvgRows;
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
            protected void onPostExecute(ArrayList<PreAvgRow> preAvgRows) {
                super.onPostExecute(preAvgRows);
                nowPreAvgRows = preAvgRows;
                searchBtn.setImageResource(R.mipmap.chartsearch_wi_g);
                setDateBtntrue();
                dataRadioGroup.setEnabled(true);
                showResult(preAvgRows);
            }
        }.execute(signInShrPref.getSID(), sqlStartTime, sqlEndTime);
    }


    /**
     * d2
     */
    /**
     *
     */
    private void initLimitLines() {
        hULimitLine = new LimitLine(BP_SY_Max, "高血壓線 " + String.valueOf(BP_SY_Max));
        initUpLimitLine(hULimitLine);
        hLLimitLine = new LimitLine(BP_SY_Min, "低血壓線 " + String.valueOf(BP_SY_Min));
        initLowLimitLine(hLLimitLine);
        lULimitLine = new LimitLine(BP_DI_Max, "高血壓線 " + String.valueOf(BP_DI_Max));
        initUpLimitLine(lULimitLine);
        lLLimitLine = new LimitLine(BP_DI_Min, "低血壓線 " + String.valueOf(BP_DI_Min));
        initLowLimitLine(lLLimitLine);
        hrULimitLine = new LimitLine(BP_HR_Max, "脈搏過快 " + String.valueOf(BP_HR_Max));
        initUpLimitLine(hrULimitLine);
        hrLLimitLine = new LimitLine(BP_HR_Min, "脈搏過慢 " + String.valueOf(BP_HR_Min));
        initLowLimitLine(hrLLimitLine);
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

    private LineData generateHLLineData(ArrayList<PreAvgRow> pressRowData) {

        LineData d = new LineData();

        ArrayList<Entry> entries1 = new ArrayList<Entry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries1.add(new Entry(pressRowData.get(index).getPAvg_sys(), index));
        }

        LineDataSet set1 = new LineDataSet(entries1, "收縮壓");
        set1.setColor(Color.rgb(255, 69, 0));
        set1.setLineWidth(2.5f);
        set1.setCircleColor(Color.rgb(255, 69, 0));
        set1.setCircleRadius(5f);
        set1.setFillColor(Color.rgb(255, 69, 0));
        set1.setDrawCubic(true);
        set1.setDrawValues(true);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.rgb(255, 69, 0));
        set1.setValueFormatter(new IntegerValueFormatter());

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set1);


        ArrayList<Entry> entries2 = new ArrayList<Entry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries2.add(new Entry(pressRowData.get(index).getPAvg_dia(), index));
        }

        LineDataSet set2 = new LineDataSet(entries2, "舒張壓");
        set2.setColor(Color.rgb(65, 105, 225));
        set2.setLineWidth(2.5f);
        set2.setCircleColor(Color.rgb(65, 105, 225));
        set2.setCircleRadius(5f);
        set2.setFillColor(Color.rgb(65, 105, 225));
        set2.setDrawCubic(true);
        set2.setDrawValues(true);
        set2.setValueTextSize(10f);
        set2.setValueTextColor(Color.rgb(65, 105, 225));
        set2.setValueFormatter(new IntegerValueFormatter());

        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set2);

        return d;
    }

    private LineData generateHLineData(ArrayList<PreAvgRow> pressRowData) {

        LineData d = new LineData();

        ArrayList<Entry> entries1 = new ArrayList<Entry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries1.add(new Entry(pressRowData.get(index).getPAvg_sys(), index));
        }

        LineDataSet set1 = new LineDataSet(entries1, "收縮壓");
        set1.setColor(Color.rgb(255, 69, 0));
        set1.setLineWidth(2.5f);
        set1.setCircleColor(Color.rgb(255, 69, 0));
        set1.setCircleRadius(5f);
        set1.setFillColor(Color.rgb(255, 69, 0));
        set1.setDrawCubic(true);
        set1.setDrawValues(true);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.rgb(255, 69, 0));
        set1.setValueFormatter(new IntegerValueFormatter());

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set1);

        return d;
    }

    private LineData generateLLineData(ArrayList<PreAvgRow> pressRowData) {

        LineData d = new LineData();

        ArrayList<Entry> entries2 = new ArrayList<Entry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries2.add(new Entry(pressRowData.get(index).getPAvg_dia(), index));
        }

        LineDataSet set2 = new LineDataSet(entries2, "舒張壓");
        set2.setColor(Color.rgb(65, 105, 225));
        set2.setLineWidth(2.5f);
        set2.setCircleColor(Color.rgb(65, 105, 225));
        set2.setCircleRadius(5f);
        set2.setFillColor(Color.rgb(65, 105, 225));
        set2.setDrawCubic(true);
        set2.setDrawValues(true);
        set2.setValueTextSize(10f);
        set2.setValueTextColor(Color.rgb(65, 105, 225));
        set2.setValueFormatter(new IntegerValueFormatter());

        set2.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set2);

        return d;
    }

    private BarData generateHrBarDataAll(ArrayList<PreAvgRow> pressRowData) {

        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries.add(new BarEntry(pressRowData.get(index).getPAvg_hr(), index));
        }

        BarDataSet set = new BarDataSet(entries, "脈搏");
        set.setColor(Color.rgb(60, 220, 78));
        set.setValueTextColor(Color.rgb(60, 220, 78));
        set.setValueTextSize(0);
        set.setValueFormatter(new IntegerValueFormatter());
        d.addDataSet(set);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return d;
    }

    private BarData generateHrBarData(ArrayList<PreAvgRow> pressRowData) {

        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < pressRowData.size(); index++) {
            entries.add(new BarEntry(pressRowData.get(index).getPAvg_hr(), index));
        }

        BarDataSet set = new BarDataSet(entries, "脈搏");
        set.setColor(Color.rgb(60, 220, 78));
        set.setValueTextColor(Color.rgb(60, 220, 78));
        set.setValueTextSize(10f);
        set.setValueFormatter(new IntegerValueFormatter());
        d.addDataSet(set);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

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
