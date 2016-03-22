package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 登入狀態紀錄設定檔
 * 整個APP只有一個
 * Created by TonyChuang on 2016/3/16.
 */
public class SignInShrPref {

    private SharedPreferences settings;
    private String SHARE_PREFERENCE_NAME = "App_SignIn_Status";
    private String PID = "pid";
    private String PWD = "pwd";
    private String SIGNINDATETIME = "signInDatetime";
    private String SIGNINSTATUS = "signInStatus";

    public SignInShrPref(Context context) {
        settings = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
    }

    public SignInShrPref(Context context, String pid, String pwd,
                         String signInDatetime, String signInStatus) {
        settings = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
        settings.edit()
                .putString(this.PID, pid)
                .putString(this.PWD, pwd)
                .putString(this.SIGNINDATETIME, signInDatetime)
                .putString(this.SIGNINSTATUS, signInStatus)
                .apply();
    }

    public String getPID() {
        return settings.getString(this.PID, "error");
    }

    public String getPWD() {
        return settings.getString(this.PWD, "error");
    }

    public String getSIGNINDATETIME() {
        return settings.getString(this.SIGNINDATETIME, "error");
    }

    public String getSIGNINSTATUS() {
        return settings.getString(this.SIGNINSTATUS, "error");
    }

    public void setPID(String pid) {
        settings.edit().putString(this.PID, pid).apply();
    }

    public void setPWD(String pwd) {
        settings.edit().putString(this.PWD, pwd).apply();
    }

    public void setSIGNINDATETIME(String signInDatetime) {
        settings.edit().putString(this.SIGNINDATETIME, signInDatetime).apply();
    }

    public void setSIGNINSTATUS(String signInStatus) {
        settings.edit().putString(this.SIGNINSTATUS, signInStatus).apply();
    }
}
