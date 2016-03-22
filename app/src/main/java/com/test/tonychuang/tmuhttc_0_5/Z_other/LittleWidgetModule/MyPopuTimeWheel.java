package com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ParseException;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.test.tonychuang.tmuhttc_0_5.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/3/17.
 */
public class MyPopuTimeWheel {
    private SimpleDateFormat dateFormat;
    private DatePickerPopWin datePickerPopWin;

    public MyPopuTimeWheel(final Context context, final TextView thisTextView, final TextView theOtherTextView,
                           int pastYears, int lastYears) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        if ("".equals(thisTextView.getText().toString().trim()))
            thisTextView.setText(dateFormat.format(new Date()));
        if ("".equals(theOtherTextView.getText().toString().trim()))
            theOtherTextView.setText(dateFormat.format(new Date()));

        datePickerPopWin = new DatePickerPopWin.Builder(context, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                if (theOtherTextView.getCurrentTextColor() == context.getResources().getColor(R.color.unChangeTextColor)) {
                    thisTextView.setText(dateDesc);
                    thisTextView.setTextColor(context.getResources().getColor(R.color.changeTextColor));
                    theOtherTextView.setText(dateDesc);
                    theOtherTextView.setTextColor(context.getResources().getColor(R.color.changeTextColor));
                } else {
                    try {
                        try {
                            if ((thisTextView.getId() == R.id.startDateTv ?
                                    dateFormat.parse(dateDesc).before(dateFormat.parse(theOtherTextView.getText().toString())) :
                                    dateFormat.parse(dateDesc).after(dateFormat.parse(theOtherTextView.getText().toString()))) ||
                                            dateFormat.parse(dateDesc).equals(dateFormat.parse(theOtherTextView.getText().toString()))) {
                                thisTextView.setText(dateDesc);
                                thisTextView.setTextColor(context.getResources().getColor(R.color.changeTextColor));
                                theOtherTextView.setTextColor(context.getResources().getColor(R.color.changeTextColor));
                            } else {
                                thisTextView.setTextColor(context.getResources().getColor(R.color.warningTextColor));
                                theOtherTextView.setTextColor(context.getResources().getColor(R.color.changeTextColor));
                                Toast.makeText(context, "設定錯誤，起始時間晚於結束時間", Toast.LENGTH_SHORT).show();
                            }
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#D7A500"))//color of confirm button
                .minYear(Calendar.getInstance().get(Calendar.YEAR) - pastYears) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR) + lastYears) // max year in loop
                .dateChose(thisTextView.getText().toString()) // date chose when init popwindow
                .build();
        datePickerPopWin.showPopWin((Activity) context);
        datePickerPopWin.contentView.setClickable(false); //點擊外部畫面,不會往下縮
    }

    public DatePickerPopWin getDatePickerPopWin() {
        return datePickerPopWin;
    }
}
