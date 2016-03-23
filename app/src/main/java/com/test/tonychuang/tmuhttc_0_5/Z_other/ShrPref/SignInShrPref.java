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
    private String SignInDatetime = "signInDatetime";
    private String SignInStatus = "signInStatus";
    private String MemberFlag = "MemberFlag";
    private String SameSignInMachine = "SameSignInMachine";

    public SignInShrPref(Context context) {
        settings = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
    }

    public SignInShrPref(Context context, String pid, String pwd, String signInDatetime,
                         boolean signInStatus, boolean memberflag, boolean SameSignInMachine) {
        settings = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
        settings.edit()
                .putString(this.PID, pid)
                .putString(this.PWD, pwd)
                .putString(this.SignInDatetime, signInDatetime)
                .putBoolean(this.SignInStatus, signInStatus)
                .putBoolean(this.MemberFlag, memberflag)
                .putBoolean(this.SameSignInMachine, SameSignInMachine)
                .apply();
    }

    public String getPID() {
        return settings.getString(this.PID, "error");
    }

    public void setPID(String pid) {
        settings.edit().putString(this.PID, pid).apply();
    }

    public String getPWD() {
        return settings.getString(this.PWD, "error");
    }

    public void setPWD(String pwd) {
        settings.edit().putString(this.PWD, pwd).apply();
    }

    public String getSignInDatetime() {
        return settings.getString(this.SignInDatetime, "1900-01-01");
    }

    public void setSignInDatetime(String signInDatetime) {
        settings.edit().putString(this.SignInDatetime, signInDatetime).apply();
    }

    public boolean getSignInStatus() {
        return settings.getBoolean(this.SignInStatus, false);
    }

    public void setSignInStatus(boolean signInStatus) {
        settings.edit().putBoolean(this.SignInStatus, signInStatus).apply();
    }

    public boolean getMemberFlag() {
        return settings.getBoolean(this.MemberFlag, false);
    }

    public void setMemberFlag(boolean flag) {
        settings.edit().putBoolean(this.MemberFlag, flag).apply();
    }

    public boolean getSameSignInMachine() {
        return settings.getBoolean(this.SameSignInMachine, true);
    }

    public void setSameSignInMachine(boolean sameSignInMachine) {
        settings.edit().putBoolean(this.SameSignInMachine, sameSignInMachine).apply();
    }
}
