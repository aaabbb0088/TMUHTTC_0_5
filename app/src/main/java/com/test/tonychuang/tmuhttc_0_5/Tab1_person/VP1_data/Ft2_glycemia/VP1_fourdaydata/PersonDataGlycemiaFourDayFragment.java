package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft2_glycemia.VP1_fourdaydata;


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
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyAvgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
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
public class PersonDataGlycemiaFourDayFragment extends Fragment {


    private TextView today_3WeekText;
    private TextView today_2WeekText;
    private AutofitTextView today_3DataBfText;
    private AutofitTextView today_2DataBfText;
    private AutofitTextView today_1DataBfText;
    private AutofitTextView today_3DataAfText;
    private AutofitTextView today_2DataAfText;
    private AutofitTextView today_1DataAfText;
    private TextView todayDataBfText;
    private TextView todayDataAfText;
    private TextView todayTimeBfText;
    private TextView todayTimeAfText;
    private AutofitTextView personalInfoText;
    private ImageView today_3Image;
    private ImageView today_2Image;
    private ImageView today_1Image;
    private ImageView todayImage;
    private LinearLayout today_3DataLayout;
    private LinearLayout today_2DataLayout;
    private LinearLayout today_1DataLayout;
    private LinearLayout todayDataLayout;
    private TextView today_3NoDataText;
    private TextView today_2NoDataText;
    private TextView today_1NoDataText;
    private TextView todayNoDataText;

    private View view;

    private MyDateSFormat myDateSFormat;
    private DataBase mainDB;
    private SignInShrPref signInShrPref;
    private WLevelShrPref wLevelShrPref;

    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_AM_Max;
    private int BG_AM_Min;


    public PersonDataGlycemiaFourDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person_data_glycemia_four_day, container, false);
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
        today_3DataBfText = (AutofitTextView) view.findViewById(R.id.today_3DataBfText);
        today_2DataBfText = (AutofitTextView) view.findViewById(R.id.today_2DataBfText);
        today_1DataBfText = (AutofitTextView) view.findViewById(R.id.today_1DataBfText);
        today_3DataAfText = (AutofitTextView) view.findViewById(R.id.today_3DataAfText);
        today_2DataAfText = (AutofitTextView) view.findViewById(R.id.today_2DataAfText);
        today_1DataAfText = (AutofitTextView) view.findViewById(R.id.today_1DataAfText);
        todayDataBfText = (TextView) view.findViewById(R.id.todayDataBfText);
        todayDataAfText = (TextView) view.findViewById(R.id.todayDataAfText);
        todayTimeBfText = (TextView) view.findViewById(R.id.todayTimeBfText);
        todayTimeAfText = (TextView) view.findViewById(R.id.todayTimeAfText);
        personalInfoText = (AutofitTextView) view.findViewById(R.id.personalInfoText);
        today_3Image = (ImageView) view.findViewById(R.id.today_3Image);
        today_2Image = (ImageView) view.findViewById(R.id.today_2Image);
        today_1Image = (ImageView) view.findViewById(R.id.today_1Image);
        todayImage = (ImageView) view.findViewById(R.id.todayImage);
        today_3DataLayout = (LinearLayout) view.findViewById(R.id.today_3DataLayout);
        today_2DataLayout = (LinearLayout) view.findViewById(R.id.today_2DataLayout);
        today_1DataLayout = (LinearLayout) view.findViewById(R.id.today_1DataLayout);
        todayDataLayout = (LinearLayout) view.findViewById(R.id.todayDataLayout);
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

        BG_BM_Max = wLevelShrPref.getBG_BM_Max();
        BG_BM_Min = wLevelShrPref.getBG_BM_Min();
        BG_AM_Max = wLevelShrPref.getBG_AM_Max();
        BG_AM_Min = wLevelShrPref.getBG_AM_Min();

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
        ArrayList<GlyAvgRow> glyAvgRows3 = getOneGlyAvgRow(Date_3Str, Date_2Str);
        if (glyAvgRows3.size() != 0) {
            today_3DataLayout.setVisibility(View.VISIBLE);
            today_3NoDataText.setVisibility(View.GONE);

            int bmValue = glyAvgRows3.get(0).getGAvg_bm();
            int amValue = glyAvgRows3.get(0).getGAvg_am();
            setTvValueColor(today_3DataBfText, bmValue, "B");
            setTvValueColor(today_3DataAfText, amValue, "A");

            if (today_3DataBfText.getCurrentTextColor() == Color.BLACK
                    && today_3DataAfText.getCurrentTextColor() == Color.BLACK) {
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
        ArrayList<GlyAvgRow> glyAvgRows2 = getOneGlyAvgRow(Date_2Str, Date_1Str);
        if (glyAvgRows2.size() != 0) {
            today_2DataLayout.setVisibility(View.VISIBLE);
            today_2NoDataText.setVisibility(View.GONE);

            int bmValue = glyAvgRows2.get(0).getGAvg_bm();
            int amValue = glyAvgRows2.get(0).getGAvg_am();
            setTvValueColor(today_2DataBfText, bmValue, "B");
            setTvValueColor(today_2DataAfText, amValue, "A");

            if (today_2DataBfText.getCurrentTextColor() == Color.BLACK
                    && today_2DataAfText.getCurrentTextColor() == Color.BLACK) {
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
        ArrayList<GlyAvgRow> glyAvgRows1 = getOneGlyAvgRow(Date_1Str, todayStr);
        if (glyAvgRows1.size() != 0) {
            today_1DataLayout.setVisibility(View.VISIBLE);
            today_1NoDataText.setVisibility(View.GONE);

            int bmValue = glyAvgRows1.get(0).getGAvg_bm();
            int amValue = glyAvgRows1.get(0).getGAvg_am();
            setTvValueColor(today_1DataBfText, bmValue, "B");
            setTvValueColor(today_1DataAfText, amValue, "A");

            if (today_1DataBfText.getCurrentTextColor() == Color.BLACK
                    && today_1DataAfText.getCurrentTextColor() == Color.BLACK) {
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
        ArrayList<GlyDataRow> glyBfDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, todayStr)
                .whereAppendAnd()
                .whereNoEquals(GlyDataRow.GDATA_MEAL_FLAG, "af")
                .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        ArrayList<GlyDataRow> glyAfDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, todayStr)
                .whereAppendAnd()
                .whereEquals(GlyDataRow.GDATA_MEAL_FLAG, "af")
                .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                .limit(0, 1));
        LiteOrm.releaseMemory();

        if (glyBfDataRows.size() == 0 && glyAfDataRows.size() == 0) {
            todayImage.setImageResource(R.mipmap.question_yellow);
            noDataDay = noDataDay + "今天,";
            todayDataLayout.setVisibility(View.GONE);
            todayNoDataText.setVisibility(View.VISIBLE);
        } else {
            todayDataBfText.setTextColor(Color.BLACK);
            todayDataAfText.setTextColor(Color.BLACK);
            if (glyBfDataRows.size() != 0) {
                todayDataLayout.setVisibility(View.VISIBLE);
                todayNoDataText.setVisibility(View.GONE);

                int bfValue = glyBfDataRows.get(0).getGData_value();
                setTvValueColor(todayDataBfText, bfValue, "B");

                String todayTime = glyBfDataRows.get(0).getGData_datetime();
                try {
                    todayTime = myDateSFormat.getFrmt_ahm()
                            .format(myDateSFormat.getFrmt_yMdHm().parse(todayTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                todayTimeBfText.setText(todayTime);
            }

            if (glyAfDataRows.size() != 0) {
                todayDataLayout.setVisibility(View.VISIBLE);
                todayNoDataText.setVisibility(View.GONE);

                int afValue = glyAfDataRows.get(0).getGData_value();
                setTvValueColor(todayDataAfText, afValue, "A");

                String todayTime = glyAfDataRows.get(0).getGData_datetime();
                try {
                    todayTime = myDateSFormat.getFrmt_ahm()
                            .format(myDateSFormat.getFrmt_yMdHm().parse(todayTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                todayTimeAfText.setText(todayTime);
            }

            if (todayDataBfText.getCurrentTextColor() == Color.BLACK
                    && todayDataAfText.getCurrentTextColor() == Color.BLACK) {
                todayImage.setImageResource(R.mipmap.happy_blue);
            } else {
                todayImage.setImageResource(R.mipmap.sad_red);
                warnDay = warnDay + "今天,";
            }
        }

        String suggestStr = "";
        if (glyAvgRows3.size() == 0 && glyAvgRows2.size() == 0 && glyAvgRows1.size() == 0
                && (glyBfDataRows.size() == 0 || glyAfDataRows.size() == 0)) {
            suggestStr = "最近都沒有測量血糖喔!\n現在就拿起血糖計，\n關心一下自己吧!";
            personalInfoText.setText(suggestStr);
        } else if (warnDay.equals("") && noDataDay.equals("")) {
            suggestStr = "最近血糖保持得很好!\n要繼續保持喔!";
            personalInfoText.setText(suggestStr);
        } else {
            if (!warnDay.equals("")) {
                suggestStr = suggestStr + warnDay + "血糖異常,\n";
            }

            if (!noDataDay.equals("")) {
                suggestStr = suggestStr + noDataDay + "沒有量測血糖,\n";
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
    private ArrayList<GlyAvgRow> getOneGlyAvgRow(String startDate, String endDate) {
        ArrayList<GlyAvgRow> arrayList;
        arrayList = mainDB.query(new QueryBuilder<GlyAvgRow>(GlyAvgRow.class)
                .whereEquals(GlyAvgRow.GAVG_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(GlyAvgRow.GAVG_DATETIME, startDate)
                .whereAppendAnd()
                .whereLessThan(GlyAvgRow.GAVG_DATETIME, endDate)
                .limit(0, 1));
        LiteOrm.releaseMemory();
        return arrayList;
    }

    private void setTvValueColor(TextView Tv, int Value, String valueFlag) {
        if (Value != 0) {
            Tv.setText(String.valueOf(Value));
            switch (valueFlag) {
                case "B":
                    if (Value < BG_BM_Min) {
                        Tv.setTextColor(Color.rgb(3, 168, 158));
                    } else if (Value > BG_BM_Max) {
                        Tv.setTextColor(Color.RED);
                    } else {
                        Tv.setTextColor(Color.BLACK);
                    }
                    break;
                case "A":
                    if (Value < BG_AM_Min) {
                        Tv.setTextColor(Color.rgb(3, 168, 158));
                    } else if (Value > BG_AM_Max) {
                        Tv.setTextColor(Color.RED);
                    } else {
                        Tv.setTextColor(Color.BLACK);
                    }
                    break;
            }
        } else {
            Tv.setText("-");
            Tv.setTextColor(Color.BLACK);
        }
    }
}
