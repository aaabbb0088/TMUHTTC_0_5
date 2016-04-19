package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.ArrayList;

public class SettingNoticeActivity extends AppCompatActivity implements View.OnClickListener {

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
    private SignInShrPref signInShrPref;
    private PsnSettingShrPref psnSettingShrPref;
    private ArrayList<String> bfPsnSetting;
    private ArrayList<String> afPsnSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notice);

        initBar();
        initView();
        initData();
        showInitData();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wDataSettingSb:
                if (wDataSettingSb.isChecked()){
                    afPsnSetting.set(0, "Y");
                } else {
                    afPsnSetting.set(0, "N");
                }
                break;
            case R.id.medSettingSb:
                if (medSettingSb.isChecked()){
                    afPsnSetting.set(1, "Y");
                } else {
                    afPsnSetting.set(1, "N");
                }
                break;
            case R.id.paySettingSb:
                if (paySettingSb.isChecked()){
                    afPsnSetting.set(2, "Y");
                } else {
                    afPsnSetting.set(2, "N");
                }
                break;
            case R.id.rptSettingSb:
                if (rptSettingSb.isChecked()){
                    afPsnSetting.set(3, "Y");
                } else {
                    afPsnSetting.set(3, "N");
                }
                break;
            case R.id.rcdSettingSb:
                if (rcdSettingSb.isChecked()){
                    afPsnSetting.set(4, "Y");
                } else {
                    afPsnSetting.set(4, "N");
                }
                break;
            case R.id.hBoardSettingSb:
                if (hBoardSettingSb.isChecked()){
                    afPsnSetting.set(5, "Y");
                } else {
                    afPsnSetting.set(5, "N");
                }
                break;
            case R.id.hRspndSettingSb:
                if (hRspndSettingSb.isChecked()){
                    afPsnSetting.set(6, "Y");
                } else {
                    afPsnSetting.set(6, "N");
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        upDateSetting();
        return super.onKeyDown(keyCode, event);
    }

    /**
     * v1
     */
    /**
     *
     */
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "提醒設定", 0);
    }

    private void initView() {
        wDataSettingTv = (TextView) findViewById(R.id.wDataSettingTv);
        medSettingTv = (TextView) findViewById(R.id.medSettingTv);
        paySettingTv = (TextView) findViewById(R.id.paySettingTv);
        rptSettingTv = (TextView) findViewById(R.id.rptSettingTv);
        rcdSettingTv = (TextView) findViewById(R.id.rcdSettingTv);
        hBoardSettingTv = (TextView) findViewById(R.id.hBoardSettingTv);
        hRspndSettingTv = (TextView) findViewById(R.id.hRspndSettingTv);
        wDataSettingSb = (SwitchButton) findViewById(R.id.wDataSettingSb);
        wDataSettingSb.setOnClickListener(this);
        medSettingSb = (SwitchButton) findViewById(R.id.medSettingSb);
        medSettingSb.setOnClickListener(this);
        paySettingSb = (SwitchButton) findViewById(R.id.paySettingSb);
        paySettingSb.setOnClickListener(this);
        rptSettingSb = (SwitchButton) findViewById(R.id.rptSettingSb);
        rptSettingSb.setOnClickListener(this);
        rcdSettingSb = (SwitchButton) findViewById(R.id.rcdSettingSb);
        rcdSettingSb.setOnClickListener(this);
        hBoardSettingSb = (SwitchButton) findViewById(R.id.hBoardSettingSb);
        hBoardSettingSb.setOnClickListener(this);
        hRspndSettingSb = (SwitchButton) findViewById(R.id.hRspndSettingSb);
        hRspndSettingSb.setOnClickListener(this);
    }

    private void initListener() {
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDateSetting();
                SettingNoticeActivity.this.onBackPressed();
            }
        });
    }

    private void showInitData() {
        if (psnSettingShrPref.getDATA_NOT_FLAG().equals("Y")) {
            setwDataSetting(true);
            bfPsnSetting.add("Y");
        } else {
            setwDataSetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getMEDICINE_NOT_FLAG().equals("Y")) {
            setmedSetting(true);
            bfPsnSetting.add("Y");
        } else {
            setmedSetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getPAY_NOT_FLAG().equals("Y")) {
            setpaySetting(true);
            bfPsnSetting.add("Y");
        } else {
            setpaySetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getREPORT_NOT_FLAG().equals("Y")) {
            setrptSetting(true);
            bfPsnSetting.add("Y");
        } else {
            setrptSetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getRECORD_NOT_FLAG().equals("Y")) {
            setrcdSetting(true);
            bfPsnSetting.add("Y");
        } else {
            setrcdSetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getCENTER_NOT_FLAG().equals("Y")) {
            sethBoardSetting(true);
            bfPsnSetting.add("Y");
        } else {
            sethBoardSetting(false);
            bfPsnSetting.add("N");
        }
        if (psnSettingShrPref.getCENTER_MSG_FLAG().equals("Y")) {
            sethRspndSetting(true);
            bfPsnSetting.add("Y");
        } else {
            sethRspndSetting(false);
            bfPsnSetting.add("N");
        }
        afPsnSetting.addAll(bfPsnSetting);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void setwDataSetting(boolean bool) {
        wDataSettingSb.setCheckedImmediately(bool);
        if (bool) {
            wDataSettingTv.setText("開啟");
        } else {
            wDataSettingTv.setText("關閉");
        }
    }

    private void setmedSetting(boolean bool) {
        medSettingSb.setCheckedImmediately(bool);
        if (bool) {
            medSettingTv.setText("開啟");
        } else {
            medSettingTv.setText("關閉");
        }
    }

    private void setpaySetting(boolean bool) {
        paySettingSb.setCheckedImmediately(bool);
        if (bool) {
            paySettingTv.setText("開啟");
        } else {
            paySettingTv.setText("關閉");
        }
    }

    private void setrptSetting(boolean bool) {
        rptSettingSb.setCheckedImmediately(bool);
        if (bool) {
            rptSettingTv.setText("開啟");
        } else {
            rptSettingTv.setText("關閉");
        }
    }

    private void setrcdSetting(boolean bool) {
        rcdSettingSb.setCheckedImmediately(bool);
        if (bool) {
            rcdSettingTv.setText("開啟");
        } else {
            rcdSettingTv.setText("關閉");
        }
    }

    private void sethBoardSetting(boolean bool) {
        hBoardSettingSb.setCheckedImmediately(bool);
        if (bool) {
            hBoardSettingTv.setText("開啟");
        } else {
            hBoardSettingTv.setText("關閉");
        }
    }

    private void sethRspndSetting(boolean bool) {
        hRspndSettingSb.setCheckedImmediately(bool);
        if (bool) {
            hRspndSettingTv.setText("開啟");
        } else {
            hRspndSettingTv.setText("關閉");
        }
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(this);
        psnSettingShrPref = new PsnSettingShrPref(this, signInShrPref.getAID());
        bfPsnSetting = new ArrayList<>();
        afPsnSetting = new ArrayList<>();
    }

    private void upDateSetting() {
        String settingSameFlag = compareArrayList(bfPsnSetting, afPsnSetting);
        if (settingSameFlag.equals("N")){
            new AsyncTask<Object, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Object... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    Boolean aboolean = false;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.EditPsnNotSetting((String) params[0],(ArrayList<String>) params[1]);
                        aboolean = jsonParser.parseBoolean(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return aboolean;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        psnSettingShrPref.setDATA_NOT_FLAG(afPsnSetting.get(0));
                        psnSettingShrPref.setMEDICINE_NOT_FLAG(afPsnSetting.get(1));
                        psnSettingShrPref.setPAY_NOT_FLAG(afPsnSetting.get(2));
                        psnSettingShrPref.setREPORT_NOT_FLAG(afPsnSetting.get(3));
                        psnSettingShrPref.setRECORD_NOT_FLAG(afPsnSetting.get(4));
                        psnSettingShrPref.setCENTER_NOT_FLAG(afPsnSetting.get(5));
                        psnSettingShrPref.setCENTER_MSG_FLAG(afPsnSetting.get(6));
                    } else {
                        toast("系統發生錯誤，請稍後再嘗試");
                    }
                }
            }.execute(signInShrPref.getAID(), afPsnSetting);
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    private String compareArrayList(ArrayList<String> bfSetting, ArrayList<String> afSetting) {
        String result = "Y";
        if (bfSetting.size() == afSetting.size()) {
            for (int i = 0; i < bfSetting.size(); i++) {
                if (!bfSetting.get(i).equals(afSetting.get(i))) {
                    result = "N";
                    break;
                }
            }
        } else {
            result = "N";
        }
        return result;
    }

    private void toast(String msg){
        Toast.makeText(SettingNoticeActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
