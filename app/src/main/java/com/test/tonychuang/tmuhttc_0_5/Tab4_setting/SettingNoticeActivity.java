package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class SettingNoticeActivity extends AppCompatActivity{

    private MyInitReturnBar myInitReturnBar;
    private TextView wDataSettingTv;
    private TextView medSettingTv;
    private TextView paySettingTv;
    private TextView rptSettingTv;
    private TextView rcdSettingTv;
    private TextView hBoardSettingTv;
    private TextView hRspndSettingTv;
    private SwitchButton wDataSettingSb;
    private SwitchButton medSettingSb;
    private SwitchButton paySettingSb;
    private SwitchButton rptSettingSb;
    private SwitchButton rcdSettingSb;
    private SwitchButton hBoardSettingSb;
    private SwitchButton hRspndSettingSb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notice);

        initBar();
        initView();
        initListener();
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        wDataSettingTv = (TextView) findViewById(R.id.wDataSettingTv);
        medSettingTv = (TextView) findViewById(R.id.medSettingTv);
        paySettingTv = (TextView) findViewById(R.id.paySettingTv);
        rptSettingTv = (TextView) findViewById(R.id.rptSettingTv);
        rcdSettingTv = (TextView) findViewById(R.id.rcdSettingTv);
        hBoardSettingTv = (TextView) findViewById(R.id.hBoardSettingTv);
        hRspndSettingTv = (TextView) findViewById(R.id.hRspndSettingTv);
        wDataSettingSb = (SwitchButton) findViewById(R.id.wDataSettingSb);
        medSettingSb = (SwitchButton) findViewById(R.id.medSettingSb);
        paySettingSb = (SwitchButton) findViewById(R.id.paySettingSb);
        rptSettingSb = (SwitchButton) findViewById(R.id.rptSettingSb);
        rcdSettingSb = (SwitchButton) findViewById(R.id.rcdSettingSb);
        hBoardSettingSb = (SwitchButton) findViewById(R.id.hBoardSettingSb);
        hRspndSettingSb = (SwitchButton) findViewById(R.id.hRspndSettingSb);
    }
    private void initListener() {
        wDataSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wDataSettingTv.setText("開啟");
                }else{
                    wDataSettingTv.setText("關閉");
                }
            }
        });
        medSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    medSettingTv.setText("開啟");
                }else{
                    medSettingTv.setText("關閉");
                }
            }
        });
        paySettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    paySettingTv.setText("開啟");
                }else{
                    paySettingTv.setText("關閉");
                }
            }
        });
        rptSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rptSettingTv.setText("開啟");
                }else{
                    rptSettingTv.setText("關閉");
                }
            }
        });
        rcdSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rcdSettingTv.setText("開啟");
                }else{
                    rcdSettingTv.setText("關閉");
                }
            }
        });
        hBoardSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hBoardSettingTv.setText("開啟");
                }else{
                    hBoardSettingTv.setText("關閉");
                }
            }
        });
        hRspndSettingSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hRspndSettingTv.setText("開啟");
                }else{
                    hRspndSettingTv.setText("關閉");
                }
            }
        });
    }
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "提醒設定", 0);
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
