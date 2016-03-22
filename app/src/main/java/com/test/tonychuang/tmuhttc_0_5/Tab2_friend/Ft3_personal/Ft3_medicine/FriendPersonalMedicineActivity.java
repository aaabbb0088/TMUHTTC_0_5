package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft3_medicine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyPopuTimeWheel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FriendPersonalMedicineActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView startDateTv;
    private TextView endDateTv;
    private ImageButton searchBtn;
    private MyPopuTimeWheel myPopuTimeWheel;
    private MyInitReturnBar myInitReturnBar;

    private SimpleDateFormat dateFormat;
    private int unChangeTextColor = Color.GRAY;
    private int changeTextColor = Color.BLACK;
    private int warningTextColor = Color.RED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal_medicine);

        initBar();
        initData();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(FriendPersonalMedicineActivity.this, startDateTv, endDateTv, 2, 1);
                break;
            case R.id.endDateTv:
                myPopuTimeWheel = new MyPopuTimeWheel(FriendPersonalMedicineActivity.this, endDateTv, startDateTv, 2, 1);
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
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "會員暱稱 用藥紀錄", 0);
    }
    private void initView() {
        startDateTv = (TextView) findViewById(R.id.startDateTv);
        startDateTv.setOnClickListener(this);
        endDateTv = (TextView) findViewById(R.id.endDateTv);
        endDateTv.setOnClickListener(this);
        searchBtn = (ImageButton) findViewById(R.id.searchBtn);
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
    private void initData() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }



    /**
     * d2
     */
    /**
     *
     */

}
