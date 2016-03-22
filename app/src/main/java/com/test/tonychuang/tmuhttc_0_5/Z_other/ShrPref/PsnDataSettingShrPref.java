package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnDataSettingRow;

/**
 * APP使用者個人基本資料設定檔
 * 有幾個APP使用者，就有幾個基本資料設定檔
 * Created by TonyChuang on 2016/3/16.
 */
public class PsnDataSettingShrPref {

    private SharedPreferences settings;
    private String SHARE_PREFERENCE_NAME = "App_Psn_Data_Setting";
    private String PID = "pid";
    private String PWD = "pwd";
    private String AID = "aid";
    private String SID = "sid";
    private String MEMBERFLAG = "memberflag";
    private String AVATAR = "avatar";
    private String NAME = "name";
    private String NICKNAME = "nickname";
    private String SEX = "sex";
    private String BIRTHDAY = "birthday";
    private String EMAIL = "email";
    private String PHONE = "phone";
    private String GCMID = "gcmId";
    private String UPDATETIME = "updatetime";


    public PsnDataSettingShrPref(Context context, String aid) {
        settings = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
    }

    public PsnDataSettingShrPref(Context context, String aid, PsnDataSettingRow psnDataSettingRow) {
        settings = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
        settings.edit()
                .putString(this.PID, psnDataSettingRow.getPid())
                .putString(this.PWD, psnDataSettingRow.getPwd())
                .putString(this.AID,psnDataSettingRow.getAid())
                .putString(this.SID,psnDataSettingRow.getAid())
                .putString(this.MEMBERFLAG,psnDataSettingRow.getMemberflag())
                .putString(this.AVATAR,psnDataSettingRow.getAvatar())
                .putString(this.NAME,psnDataSettingRow.getName())
                .putString(this.NICKNAME,psnDataSettingRow.getNickname())
                .putInt(this.SEX,psnDataSettingRow.getSex())
                .putString(this.BIRTHDAY,psnDataSettingRow.getBirthday())
                .putString(this.EMAIL,psnDataSettingRow.getEmail())
                .putString(this.PHONE,psnDataSettingRow.getPhone())
                .putString(this.GCMID,psnDataSettingRow.getGcmId())
                .putString(this.UPDATETIME,psnDataSettingRow.getUpdatetime())
                .apply();
    }

    public String getPID() {
        return settings.getString(this.PID, "error");
    }

    public String getPWD() {
        return settings.getString(this.PWD, "error");
    }

    public String getAID() {
        return settings.getString(this.AID, "error");
    }

    public String getSID() {
        return settings.getString(this.SID, "error");
    }

    public String getMEMBERFLAG() {
        return settings.getString(this.MEMBERFLAG, "error");
    }

    public String getAVATAR() {
        return settings.getString(this.AVATAR, "error");
    }

    public String getNAME() {
        return settings.getString(this.NAME, "error");
    }

    public String getNICKNAME() {
        return settings.getString(this.NICKNAME, "error");
    }

    public String getSEX() {
        return settings.getString(this.SEX, "error");
    }

    public String getBIRTHDAY() {
        return settings.getString(this.BIRTHDAY, "error");
    }

    public String getEMAIL() {
        return settings.getString(this.EMAIL, "error");
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getGCMID() {
        return settings.getString(this.GCMID, "error");
    }

    public String getUPDATETIME() {
        return settings.getString(this.UPDATETIME, "error");
    }

    public void setPID(String PID) {
        settings.edit().putString(this.PID, PID).apply();
    }

    public void setPWD(String PWD) {
        settings.edit().putString(this.PWD, PWD).apply();
    }

    public void setAID(String AID) {
        settings.edit().putString(this.AID, AID).apply();
    }

    public void setSID(String SID) {
        settings.edit().putString(this.SID, SID).apply();
    }

    public void setMEMBERFLAG(String MEMBERFLAG) {
        settings.edit().putString(this.MEMBERFLAG, MEMBERFLAG).apply();
    }

    public void setAVATAR(String AVATAR) {
        settings.edit().putString(this.AVATAR, AVATAR).apply();
    }

    public void setNAME(String NAME) {
        settings.edit().putString(this.NAME, NAME).apply();
    }

    public void setNICKNAME(String NICKNAME) {
        settings.edit().putString(this.NICKNAME, NICKNAME).apply();
    }

    public void setSEX(int SEX) {
        settings.edit().putInt(this.SEX, SEX).apply();
    }

    public void setBIRTHDAY(String BIRTHDAY) {
        settings.edit().putString(this.BIRTHDAY, BIRTHDAY).apply();
    }

    public void setEMAIL(String EMAIL) {
        settings.edit().putString(this.EMAIL, EMAIL).apply();
    }

    public void setPHONE(String PHONE) {
        settings.edit().putString(this.PHONE, PHONE).apply();
    }

    public void setGCMID(String GCMID) {
        settings.edit().putString(this.GCMID, GCMID).apply();
    }

    public void setUPDATETIME(String UPDATETIME) {
        settings.edit().putString(this.UPDATETIME, UPDATETIME).apply();
    }
}
