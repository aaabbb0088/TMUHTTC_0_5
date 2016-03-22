package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP1_fourdaydata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.R;

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
    private TextView personalInfoText;
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
    private TextView MsTimeText;
    private TextView hUnitText;
    private TextView lUnitText;
    private TextView hrUnitText;

    private View view;


    public PersonDataPressFourDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data_press_four_day, container, false);
        initViews();
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
        personalInfoText = (TextView) view.findViewById(R.id.personalInfoText);
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
        MsTimeText = (TextView) view.findViewById(R.id.MsTimeText);
        hUnitText = (TextView) view.findViewById(R.id.hUnitText);
        lUnitText = (TextView) view.findViewById(R.id.lUnitText);
        hrUnitText = (TextView) view.findViewById(R.id.hrUnitText);
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


    /**
     * d2
     */
    /**
     *
     */
}
