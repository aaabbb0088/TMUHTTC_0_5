package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用來記錄共有幾位使用者用這支手機登入遠距系統
 * 裡面的元素個數等同建立的資料庫檔案個數
 * Created by TonyChuang on 2016/3/16.
 */
public class UsersRecordShrPref {

    private SharedPreferences record;
    private String SHARE_PREFERENCE_NAME = "App_Users_Record";

    public UsersRecordShrPref(Context context) {
        record = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
    }

    public UsersRecordShrPref(Context context, String pid, String pwd) {
        record = context.getSharedPreferences(SHARE_PREFERENCE_NAME, 0);
        record.edit()
                .putString(pid, pwd)
                .apply();
    }

    public void saveUser(String pid, String pwd) {
        record.edit()
                .putString(pid, pwd)
                .apply();
    }

    public boolean isRecroded(String pid) {
        boolean result = true;
        if (record.getString(pid, "").equals(""))
            result = false;
        return result;
    }

    public int getUsersCount() {
        return record.getAll().size();
    }
}
