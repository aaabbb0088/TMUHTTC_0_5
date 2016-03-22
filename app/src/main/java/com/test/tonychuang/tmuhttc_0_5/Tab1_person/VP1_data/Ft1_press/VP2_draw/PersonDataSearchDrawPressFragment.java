package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP2_draw;


import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;

import java.text.SimpleDateFormat;
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

    private SimpleDateFormat dateFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;


    public PersonDataSearchDrawPressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data_search_draw_press, container, false);
        initData();
        initView();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(getActivity(), startDateTv, endDateTv, 2, 1);
                break;
            case R.id.endDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(getActivity(), endDateTv, startDateTv, 2, 1);
                break;
            case R.id.searchBtn:
                startDateTv.setTextColor(unChangeTextColor);
                endDateTv.setTextColor(unChangeTextColor);
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initData() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

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


    //region PickerPopWin的Ccode，已寫成MyPopuTimeWheel.class
    //時間選擇方式
    //未選時，Tv皆為灰色，點選任一Tv，皆會跳出時間滾輪，同時，點選的Tv變黑色
    //確認選擇時間時，另一Tv若是灰色，則時間一起變化，若另一Tv為黑色，則只有自己變化
    //滾輪初始時間為Tv原本顯示時間
    private void datePickerTest(final TextView textView) {
        DatePickerPopWin pickerPopWin;
        pickerPopWin = new DatePickerPopWin.Builder(getActivity(), new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                TextView thisTextView = textView;
                TextView theOtherTextView = null;
                switch (textView.getId()) {
                    case R.id.startDateTv:
                        theOtherTextView = endDateTv;
                        break;
                    case R.id.endDateTv:
                        theOtherTextView = startDateTv;
                        break;
                }
                if (theOtherTextView != null) {
                    if (theOtherTextView.getCurrentTextColor() == unChangeTextColor) {
                        textView.setText(dateDesc);
                        textView.setTextColor(changeTextColor);
                        theOtherTextView.setText(dateDesc);
                        theOtherTextView.setTextColor(changeTextColor);
                    } else {
                        try {
                            try {
                                if (thisTextView.getId() == R.id.startDateTv ?
                                        dateFormat.parse(dateDesc).before(dateFormat.parse(theOtherTextView.getText().toString())) :
                                        dateFormat.parse(dateDesc).after(dateFormat.parse(theOtherTextView.getText().toString())) ||
                                                dateFormat.parse(dateDesc).equals(dateFormat.parse(theOtherTextView.getText().toString()))) {

                                    //                            if (dateFormat.parse(dateDesc).before(dateFormat.parse(theOtherTextView.getText().toString())) ||
                                    //                                    dateFormat.parse(dateDesc).equals(dateFormat.parse(theOtherTextView.getText().toString()))) {
                                    textView.setText(dateDesc);
                                    textView.setTextColor(changeTextColor);
                                    theOtherTextView.setTextColor(changeTextColor);
                                } else {
                                    textView.setTextColor(warningTextColor);
                                    theOtherTextView.setTextColor(changeTextColor);
                                    Toast.makeText(getActivity(), "設定錯誤，起始時間晚於結束時間", Toast.LENGTH_SHORT).show();
                                }
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    //null ref 報錯處理
                }
                Toast.makeText(getActivity(), dateDesc, Toast.LENGTH_SHORT).show();
            }
        }).textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                .minYear(Calendar.getInstance().get(Calendar.YEAR) - 2) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR) + 1) // max year in loop
                .dateChose(textView.getText().toString()) // date chose when init popwindow
                .build();

        //PopupWindow doesn't dismiss when touching outside
        pickerPopWin.showPopWin(getActivity());
    }
    //endregion
}
