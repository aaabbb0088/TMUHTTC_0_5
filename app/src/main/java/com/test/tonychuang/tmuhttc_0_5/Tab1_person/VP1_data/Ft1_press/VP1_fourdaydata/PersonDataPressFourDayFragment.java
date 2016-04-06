package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP1_fourdaydata;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDataPressFourDayFragment extends Fragment {


    private TextView today_3WeekText;
    private TextView today_2WeekText;
    private AutofitTextView today_3DataHText;
    private AutofitTextView today_2DataHText;
    private AutofitTextView today_1DataHText;
    private AutofitTextView today_3DataLText;
    private AutofitTextView today_2DataLText;
    private AutofitTextView today_1DataLText;
    private TextView todayDataText_H;
    private TextView todayDataText_L;
    private TextView todayDataText_Hr;
    private TextView todayTimeText;
    private AutofitTextView personalInfoText;
    private ImageView today_3Image;
    private ImageView today_2Image;
    private ImageView today_1Image;
    private ImageView todayImage;
    private LinearLayout today_3DataLayout;
    private LinearLayout today_2DataLayout;
    private LinearLayout today_1DataLayout;
    private LinearLayout todayDataLayout;
    private LinearLayout todayTimeLayout;
    private TextView today_3NoDataText;
    private TextView today_2NoDataText;
    private TextView today_1NoDataText;
    private TextView todayNoDataText;

    private View view;

    private MyDateSFormat myDateSFormat;
    private DataBase mainDB;
    private SignInShrPref signInShrPref;
    private WLevelShrPref wLevelShrPref;


    public PersonDataPressFourDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data_press_four_day, container, false);
        initViews();
        initData();
        return view;
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initViews() {
        today_3WeekText = (TextView) view.findViewById(R.id.today_3WeekText);
        today_2WeekText = (TextView) view.findViewById(R.id.today_2WeekText);
        today_3DataHText = (AutofitTextView) view.findViewById(R.id.today_3DataHText);
        today_2DataHText = (AutofitTextView) view.findViewById(R.id.today_2DataHText);
        today_1DataHText = (AutofitTextView) view.findViewById(R.id.today_1DataHText);
        today_3DataLText = (AutofitTextView) view.findViewById(R.id.today_3DataLText);
        today_2DataLText = (AutofitTextView) view.findViewById(R.id.today_2DataLText);
        today_1DataLText = (AutofitTextView) view.findViewById(R.id.today_1DataLText);
        todayDataText_H = (TextView) view.findViewById(R.id.todayDataText_H);
        todayDataText_L = (TextView) view.findViewById(R.id.todayDataText_L);
        todayDataText_Hr = (TextView) view.findViewById(R.id.todayDataText_Hr);
        todayTimeText = (TextView) view.findViewById(R.id.todayTimeText);
        personalInfoText = (AutofitTextView) view.findViewById(R.id.personalInfoText);
        today_3Image = (ImageView) view.findViewById(R.id.today_3Image);
        today_2Image = (ImageView) view.findViewById(R.id.today_2Image);
        today_1Image = (ImageView) view.findViewById(R.id.today_1Image);
        todayImage = (ImageView) view.findViewById(R.id.todayImage);
        today_3DataLayout = (LinearLayout) view.findViewById(R.id.today_3DataLayout);
        today_2DataLayout = (LinearLayout) view.findViewById(R.id.today_2DataLayout);
        today_1DataLayout = (LinearLayout) view.findViewById(R.id.today_1DataLayout);
        todayDataLayout = (LinearLayout) view.findViewById(R.id.todayDataLayout);
        todayTimeLayout = (LinearLayout) view.findViewById(R.id.todayTimeLayout);
        today_3NoDataText = (TextView) view.findViewById(R.id.today_3NoDataText);
        today_2NoDataText = (TextView) view.findViewById(R.id.today_2NoDataText);
        today_1NoDataText = (TextView) view.findViewById(R.id.today_1NoDataText);
        todayNoDataText = (TextView) view.findViewById(R.id.todayNoDataText);
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
     *
     */
    private void initData() {
        myDateSFormat = new MyDateSFormat();
        signInShrPref = new SignInShrPref(getActivity());
        wLevelShrPref = new WLevelShrPref(getActivity());
        mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
        String warnDay = "";
        String noDataDay = "";

        //更新周數
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        String WeekName = myDateSFormat.getFrmt_E().format(calendar.getTime());
        today_2WeekText.setText(WeekName);
        calendar.add(Calendar.DATE, -1);
        WeekName = myDateSFormat.getFrmt_E().format(calendar.getTime());
        today_3WeekText.setText(WeekName);


        /**
         * 取得前三天數據(各天單筆)
         * 單日判斷
         */
        //更新前三天數據、顏色、圖示
        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        Date todayDate = clr.getTime();
        String todayStr = myDateSFormat.getFrmt_yMd().format(todayDate);
        todayStr = todayStr + " 00:00";

        clr.add(Calendar.DAY_OF_MONTH, -1);
        Date Date_1 = clr.getTime();
        String Date_1Str = myDateSFormat.getFrmt_yMd().format(Date_1);
        Date_1Str = Date_1Str + " 00:00";

        clr.add(Calendar.DAY_OF_MONTH, -1);
        Date Date_2 = clr.getTime();
        String Date_2Str = myDateSFormat.getFrmt_yMd().format(Date_2);
        Date_2Str = Date_2Str + " 00:00";

        clr.add(Calendar.DAY_OF_MONTH, -1);
        Date Date_3 = clr.getTime();
        String Date_3Str = myDateSFormat.getFrmt_yMd().format(Date_3);
        Date_3Str = Date_3Str + " 00:00";


        //大前天
        ArrayList<PreAvgRow> preAvgRows3 = getOnePreAvgRow(Date_3Str, Date_2Str);
        if (preAvgRows3.size() != 0) {
            today_3DataLayout.setVisibility(View.VISIBLE);
            today_3NoDataText.setVisibility(View.GONE);

            int sysValue = preAvgRows3.get(0).getPAvg_sys();
            int diaValue = preAvgRows3.get(0).getPAvg_dia();
            setTvValueColor(today_3DataHText, sysValue, "H");
            setTvValueColor(today_3DataLText, diaValue, "L");

            if (today_3DataHText.getCurrentTextColor() == Color.BLACK
                    && today_3DataLText.getCurrentTextColor() == Color.BLACK) {
                today_3Image.setImageResource(R.mipmap.happy_blue);
            } else {
                today_3Image.setImageResource(R.mipmap.sad_red);
                warnDay = warnDay + today_3WeekText.getText().toString() + ",";
            }

        } else {
            today_3Image.setImageResource(R.mipmap.question_yellow);
            noDataDay = noDataDay + today_3WeekText.getText().toString() + ",";
            today_3DataLayout.setVisibility(View.GONE);
            today_3NoDataText.setVisibility(View.VISIBLE);
        }

        //前天
        ArrayList<PreAvgRow> preAvgRows2 = getOnePreAvgRow(Date_2Str, Date_1Str);
        if (preAvgRows2.size() != 0) {
            today_2DataLayout.setVisibility(View.VISIBLE);
            today_2NoDataText.setVisibility(View.GONE);

            int sysValue = preAvgRows2.get(0).getPAvg_sys();
            int diaValue = preAvgRows2.get(0).getPAvg_dia();
            setTvValueColor(today_2DataHText, sysValue, "H");
            setTvValueColor(today_2DataLText, diaValue, "L");

            if (today_2DataHText.getCurrentTextColor() == Color.BLACK
                    && today_2DataLText.getCurrentTextColor() == Color.BLACK) {
                today_2Image.setImageResource(R.mipmap.happy_blue);
            } else {
                today_2Image.setImageResource(R.mipmap.sad_red);
                warnDay = warnDay + today_2WeekText.getText().toString() + ",";
            }

        } else {
            today_2Image.setImageResource(R.mipmap.question_yellow);
            noDataDay = noDataDay + today_2WeekText.getText().toString() + ",";
            today_2DataLayout.setVisibility(View.GONE);
            today_2NoDataText.setVisibility(View.VISIBLE);
        }

        //昨天
        ArrayList<PreAvgRow> preAvgRows1 = getOnePreAvgRow(Date_1Str, todayStr);
        if (preAvgRows1.size() != 0) {
            today_1DataLayout.setVisibility(View.VISIBLE);
            today_1NoDataText.setVisibility(View.GONE);

            int sysValue = preAvgRows1.get(0).getPAvg_sys();
            int diaValue = preAvgRows1.get(0).getPAvg_dia();
            setTvValueColor(today_1DataHText, sysValue, "H");
            setTvValueColor(today_1DataLText, diaValue, "L");

            if (today_1DataHText.getCurrentTextColor() == Color.BLACK
                    && today_1DataLText.getCurrentTextColor() == Color.BLACK) {
                today_1Image.setImageResource(R.mipmap.happy_blue);
            } else {
                today_1Image.setImageResource(R.mipmap.sad_red);
                warnDay = warnDay + "昨天,";
            }

        } else {
            today_1Image.setImageResource(R.mipmap.question_yellow);
            noDataDay = noDataDay + "昨天,";
            today_1DataLayout.setVisibility(View.GONE);
            today_1NoDataText.setVisibility(View.VISIBLE);
        }


        //更新當天數據、顏色、圖示
        ArrayList<PreDataRow> preDataRows = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, todayStr)
                .appendOrderDescBy(PreDataRow.PDATA_DATETIME)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        if (preDataRows.size() != 0) {
            todayDataLayout.setVisibility(View.VISIBLE);
            todayTimeLayout.setVisibility(View.VISIBLE);
            todayNoDataText.setVisibility(View.GONE);

            int sysValue = preDataRows.get(0).getPData_sys();
            int diaValue = preDataRows.get(0).getPData_dia();
            int hrValue = preDataRows.get(0).getPData_hr();
            setTvValueColor(todayDataText_H, sysValue, "H");
            setTvValueColor(todayDataText_L, diaValue, "L");
            setTvValueColor(todayDataText_Hr, hrValue, "Hr");

            if (todayDataText_H.getCurrentTextColor() == Color.BLACK
                    && todayDataText_L.getCurrentTextColor() == Color.BLACK
                    && todayDataText_Hr.getCurrentTextColor() == Color.BLACK) {
                todayImage.setImageResource(R.mipmap.happy_blue);
            } else {
                todayImage.setImageResource(R.mipmap.sad_red);
                warnDay = warnDay + "今天,";
            }

            String todayTime = preDataRows.get(0).getPData_datetime();
            try {
                todayTime = myDateSFormat.getFrmt_ahm()
                        .format(myDateSFormat.getFrmt_yMdHm().parse(todayTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            todayTimeText.setText(todayTime);

        } else {
            todayImage.setImageResource(R.mipmap.question_yellow);
            noDataDay = noDataDay + "今天,";
            todayDataLayout.setVisibility(View.GONE);
            todayTimeLayout.setVisibility(View.GONE);
            todayNoDataText.setVisibility(View.VISIBLE);
        }

        String suggestStr = "";
        if (preAvgRows3.size() == 0 && preAvgRows2.size() == 0 && preAvgRows1.size() == 0
                && preDataRows.size() == 0) {
            suggestStr = "最近都沒有測量血壓喔!\n現在就拿起血壓計，\n關心一下自己吧!";
            personalInfoText.setText(suggestStr);
        } else if (warnDay.equals("") && noDataDay.equals("")) {
            suggestStr = "最近血壓保持得很好!\n要繼續保持喔!";
            personalInfoText.setText(suggestStr);
        } else {
            if (!warnDay.equals("")) {
                suggestStr = suggestStr + warnDay + "血壓異常,\n";
            } else if (!noDataDay.equals("")) {
                suggestStr = suggestStr + noDataDay + "沒有量測血壓,\n";
            }
            suggestStr = suggestStr + "要多注意身體狀況喔!";
            personalInfoText.setText(suggestStr);
        }
        mainDB.close();
    }


    /**
     * d2
     */
    /**
     *
     */
    private ArrayList<PreAvgRow> getOnePreAvgRow(String startDate, String endDate) {
        ArrayList<PreAvgRow> arrayList;
        arrayList = mainDB.query(new QueryBuilder<PreAvgRow>(PreAvgRow.class)
                .whereEquals(PreAvgRow.PAVG_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(PreAvgRow.PAVG_DATETIME, startDate)
                .whereAppendAnd()
                .whereLessThan(PreAvgRow.PAVG_DATETIME, endDate)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        return arrayList;
    }

    private void setTvValueColor(TextView Tv, int Value, String valueFlag) {
        Tv.setText(String.valueOf(Value));

        int BP_SY_Max = wLevelShrPref.getBP_SY_Max();
        int BP_SY_Min = wLevelShrPref.getBP_SY_Min();
        int BP_DI_Max = wLevelShrPref.getBP_DI_Max();
        int BP_DI_Min = wLevelShrPref.getBP_DI_Min();
        int BP_HR_Max = wLevelShrPref.getBP_HR_Max();
        int BP_HR_Min = wLevelShrPref.getBP_HR_Min();
        switch (valueFlag) {
            case "H":
                if (Value < BP_SY_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_SY_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
            case "L":
                if (Value < BP_DI_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_DI_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
            case "Hr":
                if (Value < BP_HR_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_HR_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
        }
    }
}
