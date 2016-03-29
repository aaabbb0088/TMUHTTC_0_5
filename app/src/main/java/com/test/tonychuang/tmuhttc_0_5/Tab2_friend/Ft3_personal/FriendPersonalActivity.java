package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft1_press.FriendPersonalPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.FriendPersonalGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft3_medicine.FriendPersonalMedicineActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft4_report.FriendPersonalReportActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft5_pay.FriendPersonalPayActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft6_record.FriendPersonalRecordActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft7_msg.FriendMsgGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft7_msg.FriendMsgPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class FriendPersonalActivity extends AppCompatActivity implements View.OnClickListener ,View.OnLongClickListener{

    private LinearLayout presslayout;
    private LinearLayout glycemialayout;
    private TextView pressValueText;
    private TextView glycemiaValueText;
    private TextView glycemiaMealText;
    private TextView pressDateText;
    private TextView glycemiaDateText;
    private TextView pressUnitText;
    private TextView glycemiaUnitText;
    private FrameLayout pressMsgBtn;
    private FrameLayout glycemiaMsgBtn;
    private LinearLayout pressThbBtn;
    private LinearLayout glycemiaThbBtn;
    private ActionBar actionBar;
    private MyInitReturnBar myInitReturnBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal);

        initBar();
        initView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.presslayout:
                intent.setClass(FriendPersonalActivity.this, FriendPersonalPressActivity.class);
                startActivity(intent);
                break;
            case R.id.glycemialayout:
                intent.setClass(FriendPersonalActivity.this, FriendPersonalGlycemiaActivity.class);
                startActivity(intent);
                break;
            case R.id.pressMsgBtn:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendMsgPressActivity.class);
                startActivity(intent);
                break;
            case R.id.glycemiaMsgBtn:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendMsgGlycemiaActivity.class);
                startActivity(intent);
                break;
            case R.id.pressThbBtn:
                Toast.makeText(FriendPersonalActivity.this, "press +1 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.glycemiaThbBtn:
                Toast.makeText(FriendPersonalActivity.this, "glycemia +1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pillLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalMedicineActivity.class);
                startActivity(intent);
                break;
            case R.id.reportLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalReportActivity.class);
                startActivity(intent);
                break;
            case R.id.payLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalPayActivity.class);
                startActivity(intent);
                break;
            case R.id.recordLayout:
                intent = new Intent();
                intent.setClass(FriendPersonalActivity.this, FriendPersonalRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.pressThbBtn:
                thumbAlertDialog();
                break;
            case R.id.glycemiaThbBtn:
                thumbAlertDialog();
                break;
        }
        return true;
    }


    /**
     * v1
     */
    /**
     *
     */

    public void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "會員暱稱", 0);
        actionBar = myInitReturnBar.getActionBar();
    }

    private void initView() {
        presslayout = (LinearLayout) findViewById(R.id.presslayout);
        presslayout.setOnClickListener(FriendPersonalActivity.this);
        glycemialayout = (LinearLayout) findViewById(R.id.glycemialayout);
        glycemialayout.setOnClickListener(FriendPersonalActivity.this);
        pressValueText = (TextView) findViewById(R.id.pressValueText);
        glycemiaValueText = (TextView) findViewById(R.id.glycemiaValueText);
        glycemiaMealText = (TextView) findViewById(R.id.glycemiaMealText);
        pressDateText = (TextView) findViewById(R.id.pressDateText);
        glycemiaDateText = (TextView) findViewById(R.id.glycemiaDateText);
        pressUnitText = (TextView) findViewById(R.id.pressUnitText);
        glycemiaUnitText = (TextView) findViewById(R.id.glycemiaUnitText);
        pressMsgBtn = (FrameLayout) findViewById(R.id.pressMsgBtn);
        pressMsgBtn.setOnClickListener(FriendPersonalActivity.this);
        glycemiaMsgBtn = (FrameLayout) findViewById(R.id.glycemiaMsgBtn);
        glycemiaMsgBtn.setOnClickListener(FriendPersonalActivity.this);
        pressThbBtn = (LinearLayout) findViewById(R.id.pressThbBtn);
        pressThbBtn.setOnClickListener(FriendPersonalActivity.this);
        pressThbBtn.setOnLongClickListener(FriendPersonalActivity.this);
        glycemiaThbBtn = (LinearLayout) findViewById(R.id.glycemiaThbBtn);
        glycemiaThbBtn.setOnClickListener(FriendPersonalActivity.this);
        glycemiaThbBtn.setOnLongClickListener(FriendPersonalActivity.this);
        LinearLayout pillLayout = (LinearLayout) findViewById(R.id.pillLayout);
        pillLayout.setOnClickListener(FriendPersonalActivity.this);
        LinearLayout reportLayout = (LinearLayout) findViewById(R.id.reportLayout);
        reportLayout.setOnClickListener(FriendPersonalActivity.this);
        LinearLayout payLayout = (LinearLayout) findViewById(R.id.payLayout);
        payLayout.setOnClickListener(FriendPersonalActivity.this);
        LinearLayout recordLayout = (LinearLayout) findViewById(R.id.recordLayout);
        recordLayout.setOnClickListener(FriendPersonalActivity.this);
    }

    /**
     * List選單 對話框
     * */
    //之後改成加入參數(要塞入listView的資料)
    private void thumbAlertDialog() {
        //之後把Layout改成listView(頭像+暱稱)，處理完按讚資料後，塞入listView
        View dialogView = LayoutInflater.from(FriendPersonalActivity.this).inflate(R.layout.dialog_thumd_aids, null);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);

        final AlertDialog alertDialog = new AlertDialog.Builder(FriendPersonalActivity.this)
                .setTitle("按讚好友")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false)
                .create();
        alertDialog.show();

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private DialogInterface.OnKeyListener getOnKeyListener(){
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    dialog.dismiss();
                }
                return false;
            }
        };
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
     * 每次開啟FriPsnalAty，更新一次
     * 1.個人警戒值上下限設定檔WarningLevelSetting
     * 2.個人血壓流水資料PressDataTable、血壓按讚資料
     * 3.個人血壓留言PressMsgTable
     * 4.個人每日平均血壓PressAvgTable
     * 5.個人血糖流水資料GlycemiaDataTable、血糖按讚資料
     * 6.個人血糖留言GlycemiaMsgTable
     * 7.個人每日平均血壓GlycemiaAvgTable
     * 8.個人用藥紀錄表ServiceMedicineTable
     * 9.個人健康報告書表ServiceReportTable
     * 10.個人繳費紀錄表ServicePayTable
     * 11.個人服務歷程紀錄表ServiceRecordTable
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
