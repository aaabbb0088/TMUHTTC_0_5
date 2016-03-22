package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.test.tonychuang.timescroller.Ruler;
import com.test.tonychuang.timescroller.RulerError;
import com.test.tonychuang.timescroller.RulerHandler;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 1.日期選擇 : TEXTVIEW -> datepicker dialog
 * 2.時間選擇 : Ruler -> 外部控件
 * 3.Bar顯示被追蹤者暱稱
 */


public class FriendMapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView dateTv;
    private MyInitReturnBar myInitReturnBar;
    private Ruler timeRuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_map);

        initBar();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateTv:
                dateWheel();
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
        myInitReturnBar = new MyInitReturnBar(this, "會員暱稱", 0);
    }

    private void initView() {
        dateTv = (TextView) findViewById(R.id.dateTv);
        dateTv.setOnClickListener(this);
        timeRuler = (Ruler) findViewById(R.id.timeRuler);
        timeRuler.setRulerTag("          ");
        timeRuler.setRulerHandler(new RulerHandler() {
            @Override
            public void markText(String text) {

            }

            @Override
            public void error(RulerError error) {

            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private void dateWheel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DatePickerPopWin datePickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                Toast.makeText(FriendMapActivity.this, dateDesc, Toast.LENGTH_SHORT).show();
            }
        }).textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#D7A500"))//color of confirm button
                .minYear(Calendar.getInstance().get(Calendar.YEAR)) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR)) // max year in loop
                .dateChose(dateFormat.format(new Date())) // date chose when init popwindow
                .build();
        datePickerPopWin.yearLoopView.setVisibility(View.GONE);
        datePickerPopWin.showPopWin(FriendMapActivity.this);
        datePickerPopWin.contentView.setClickable(false);
    }


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
