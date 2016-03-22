package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnSettingRow;

/**
 * APP使用者個人設定檔
 * 有幾個APP使用者，就有幾個設定檔
 * Created by TonyChuang on 2016/3/16.
 */
public class PsnSettingShrPref {

    private SharedPreferences settings;
    private String SHARE_PREFERENCE_NAME = "App_Psn_Setting";
    private String DATA_NOT_FLAG = "data_not_flag";
    private String MEDINE_NOT_FLAG = "medine_not_flag";
    private String PAY_NOT_FLAG = "pay_not_flag";
    private String REPORT_NOT_FLAG = "report_not_flag";
    private String RECORD_NOT_FLAG = "record_not_flag";
    private String CENTER_NOT_FLAG = "center_not_flag";
    private String CENTER_MSG_FLAG = "center_msg_flag";
    private String LOCATION_FLAG = "location_flag";
    private String UPDATETIME = "updatetime";


    public PsnSettingShrPref(Context context, String aid) {
        settings = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
    }

    public PsnSettingShrPref(Context context, String aid, PsnSettingRow psnSettingRow) {
        settings = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
        settings.edit()
                .putString(this.DATA_NOT_FLAG, psnSettingRow.getData_not_flag())
                .putString(this.MEDINE_NOT_FLAG, psnSettingRow.getMedine_not_flag())
                .putString(this.PAY_NOT_FLAG, psnSettingRow.getPay_not_flag())
                .putString(this.REPORT_NOT_FLAG, psnSettingRow.getReport_not_flag())
                .putString(this.RECORD_NOT_FLAG, psnSettingRow.getRecord_not_flag())
                .putString(this.CENTER_NOT_FLAG, psnSettingRow.getCenter_not_flag())
                .putString(this.CENTER_MSG_FLAG, psnSettingRow.getCenter_msg_flag())
                .putString(this.LOCATION_FLAG, psnSettingRow.getLocation_flag())
                .putString(this.UPDATETIME, psnSettingRow.getUpdatetime())
                .apply();
    }

    public String getDATA_NOT_FLAG() {
        return settings.getString(this.DATA_NOT_FLAG, "error");
    }

    public String getMEDINE_NOT_FLAG() {
        return settings.getString(this.MEDINE_NOT_FLAG, "error");
    }

    public String getPAY_NOT_FLAG() {
        return settings.getString(this.PAY_NOT_FLAG, "error");
    }

    public String getREPORT_NOT_FLAG() {
        return settings.getString(this.REPORT_NOT_FLAG, "error");
    }

    public String getRECORD_NOT_FLAG() {
        return settings.getString(this.RECORD_NOT_FLAG, "error");
    }

    public String getCENTER_NOT_FLAG() {
        return settings.getString(this.CENTER_NOT_FLAG, "error");
    }

    public String getCENTER_MSG_FLAG() {
        return settings.getString(this.CENTER_MSG_FLAG, "error");
    }

    public String getLOCATION_FLAG() {
        return settings.getString(this.LOCATION_FLAG, "error");
    }

    public String getUPDATETIME() {
        return settings.getString(this.UPDATETIME, "error");
    }

    public void setDATA_NOT_FLAG(String DATA_NOT_FLAG) {
        settings.edit().putString(this.DATA_NOT_FLAG, DATA_NOT_FLAG).apply();
    }

    public void setMEDINE_NOT_FLAG(String MEDINE_NOT_FLAG) {
        settings.edit().putString(this.MEDINE_NOT_FLAG, MEDINE_NOT_FLAG).apply();
    }

    public void setPAY_NOT_FLAG(String PAY_NOT_FLAG) {
        settings.edit().putString(this.PAY_NOT_FLAG, PAY_NOT_FLAG).apply();
    }

    public void setREPORT_NOT_FLAG(String REPORT_NOT_FLAG) {
        settings.edit().putString(this.REPORT_NOT_FLAG, REPORT_NOT_FLAG).apply();
    }

    public void setRECORD_NOT_FLAG(String RECORD_NOT_FLAG) {
        settings.edit().putString(this.RECORD_NOT_FLAG, RECORD_NOT_FLAG).apply();
    }

    public void setCENTER_NOT_FLAG(String CENTER_NOT_FLAG) {
        settings.edit().putString(this.CENTER_NOT_FLAG, CENTER_NOT_FLAG).apply();
    }

    public void setCENTER_MSG_FLAG(String CENTER_MSG_FLAG) {
        settings.edit().putString(this.CENTER_MSG_FLAG, CENTER_MSG_FLAG).apply();
    }

    public void setLOCATION_FLAG(String LOCATION_FLAG) {
        settings.edit().putString(this.LOCATION_FLAG, LOCATION_FLAG).apply();
    }

    public void setUPDATETIME(String UPDATETIME) {
        settings.edit().putString(this.UPDATETIME, UPDATETIME).apply();
    }
}
