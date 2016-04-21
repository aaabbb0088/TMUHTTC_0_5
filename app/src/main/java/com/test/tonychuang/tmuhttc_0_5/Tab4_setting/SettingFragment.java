package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.GPS.MyGPSRecordService;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnDataSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import me.grantland.widget.AutofitTextView;


/**
 * 個人設定
 * 1.個人資訊 : 大頭照、暱稱、性別、出生年月日、修改密碼(輸入舊密碼、輸入新密碼、確認新密碼)、登出鍵 -> 登入頁面
 * 2.數據異常推撥flag
 * 藥物提醒推撥flag
 * 繳費提醒推撥flag
 * 健康報告書推撥flag
 * 服務歷程推撥flag
 * 健康公佈欄推撥flag
 * 遠距中心留言板回覆推撥flag
 * <p/>
 * 開啟自身定位紀錄flag
 * <p/>
 * 3.問題回報鍵 -> 問題回報頁面
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    private LinearLayout personalDataSettingLayout;
    private LinearLayout noticeLayout;
    private LinearLayout gpsLayout;
    private LinearLayout feedbackLayout;

    private View view;
    private ActionBar actionBar;
    private TextView gpsSettingTv;
    private ImageView gpsSettingImv;
    private TextView NameTv;
    private TextView NickNameTv;

    private SignInShrPref signInShrPref;
    private PsnDataSettingShrPref psnDataSettingShrPref;
    private PsnSettingShrPref psnSettingShrPref;

    private static DialogPlus dialog; //MainActivity 需要利用這個dialog做判斷


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initBar();
        initView();
        initData();
        showInitData();
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.personalDataSettingLayout:
                intent = new Intent().setClass(getActivity(), SettingPersonalDataActivity.class);
                startActivity(intent);
                break;
            case R.id.noticeLayout:
                intent = new Intent().setClass(getActivity(), SettingNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.gpsLayout:
                settingGPSBottomDialog();
                break;
            case R.id.feedbackLayout:
                intent = new Intent().setClass(getActivity(), SettingFeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }


    /**
     * v1
     */
    /**
     * 自己非遠距會員("提醒設定"按鈕隱藏)
     */
    /**
     *
     */
    public void initBar() {
        actionBar = MainActivity.actionBar;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //禁用系統ActionBar標題
        actionBar.setDisplayShowTitleEnabled(false);
        //禁用系統ActionBar圖標
        actionBar.setDisplayUseLogoEnabled(false);
        //禁用系統ActionBar返回鍵
        actionBar.setDisplayShowHomeEnabled(false);
        //清除所有已加入的Tab
        actionBar.removeAllTabs();

        //自定義 ActionBar
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title, null);
        AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
        actionBarText.setText("個人設定");
        actionBarText.setTextColor(Color.WHITE);
        actionBar.setCustomView(titleView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    private void initView() {
        personalDataSettingLayout = (LinearLayout) view.findViewById(R.id.personalDataSettingLayout);
        personalDataSettingLayout.setOnClickListener(SettingFragment.this);
        noticeLayout = (LinearLayout) view.findViewById(R.id.noticeLayout);
        noticeLayout.setOnClickListener(SettingFragment.this);
        gpsLayout = (LinearLayout) view.findViewById(R.id.gpsLayout);
        gpsLayout.setOnClickListener(SettingFragment.this);
        feedbackLayout = (LinearLayout) view.findViewById(R.id.feedbackLayout);
        feedbackLayout.setOnClickListener(SettingFragment.this);
        gpsSettingTv = (TextView) view.findViewById(R.id.gpsSettingTv);
        gpsSettingImv = (ImageView) view.findViewById(R.id.gpsSettingImv);
        NameTv = (TextView) view.findViewById(R.id.NameTv);
        NickNameTv = (TextView) view.findViewById(R.id.NickNameTv);

    }

    private void showInitData() {
        if (psnSettingShrPref.getLOCATION_FLAG().equals("Y")) {
            setGPSTv(true);
        } else {
            setGPSTv(false);
        }
    }

    private void settingGPSBottomDialog() {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_gps_setting, null);
        dialog = DialogPlus
                .newDialog(getActivity())
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getGPSOnClickListener())   //確認鍵、取消鍵
                .setOnBackPressListener(getOnBackPressListener())  //按返回鍵會縮下去
                .create();
        dialog.show();
    }


    /**
     * v2
     */
    /**
     *
     */
    private OnClickListener getGPSOnClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.gpsOnText:
                        if (isOpenGps()){
                            upDateSetting("Y");
                            dialog.dismiss();
                        } else {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            toast("尚未開啟定位功能。\n請修改GPS設定後，再開啟定位功能。");
                        }
                        break;
                    case R.id.gpsOffText:
                        upDateSetting("N");
                        dialog.dismiss();
                        break;
                }
            }
        };
    }

    private OnBackPressListener getOnBackPressListener() {
        return new OnBackPressListener() {
            @Override
            public void onBackPressed(DialogPlus dialogPlus) {
                dialogPlus.dismiss();
            }
        };
    }

    public static DialogPlus getDialog() {
        return dialog;
    }

    private void setGPSTv(boolean bool) {
        if (bool) {
            gpsSettingTv.setText("開啟");
            gpsSettingImv.setImageResource(R.mipmap.setting_gps_on);
        } else {
            gpsSettingTv.setText("關閉");
            gpsSettingImv.setImageResource(R.mipmap.setting_gps_off);
        }
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(getActivity());
        psnDataSettingShrPref = new PsnDataSettingShrPref(getActivity(), signInShrPref.getAID());
        psnSettingShrPref = new PsnSettingShrPref(getActivity(), signInShrPref.getAID());
        NameTv.setText(psnDataSettingShrPref.getNAME());
        NickNameTv.setText(psnDataSettingShrPref.getNICKNAME());
    }

    private void upDateSetting(final String GPSSettingStr) {
        if (!GPSSettingStr.equals(psnSettingShrPref.getLOCATION_FLAG())){
            final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "資料同步中，請稍後");
            new AsyncTask<String, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    Boolean aBoolean = false;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.EditPsnGPSSetting(params[0], params[1]);
                        aBoolean = jsonParser.parseBoolean(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return aBoolean;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    mySyncingDialog.show();
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    mySyncingDialog.dismiss();
                    if (aBoolean) {
                        psnSettingShrPref.setLOCATION_FLAG(GPSSettingStr);
                        if (GPSSettingStr.equals("Y")){
                            Intent startintent = new Intent(getActivity(), MyGPSRecordService.class);
                            getActivity().startService(startintent);
                            setGPSTv(true);
                        } else {
                            Intent stopintent = new Intent(getActivity(), MyGPSRecordService.class);
                            getActivity().stopService(stopintent);
                            setGPSTv(false);
                        }
                    } else {
                        toast("系統發生錯誤，請稍後再嘗試");
                    }
                }
            }.execute(signInShrPref.getAID(), GPSSettingStr);
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    /**
     * 判斷GPS是否開啟，GPS或者AGPS開啟一個就認為是開啟的
     */
    private boolean isOpenGps() {
        LocationManager locationManager
                = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // 通過GPS衛星定位，定位級別可以精確到街（通過24顆衛星定位，在室外和空曠的地方定位準確、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通過WLAN或移動網路(3G/2G)確定的位置（也稱作AGPS，輔助GPS定位。主要用於在室內或遮蓋物（建築群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

}
