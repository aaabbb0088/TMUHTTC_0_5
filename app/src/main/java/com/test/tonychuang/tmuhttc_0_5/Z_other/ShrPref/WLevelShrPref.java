package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.WLevelRow;

/**
 * 個人警戒值上下限設定檔
 * 所有APP使用者與所有好友聯集中的遠距會員個數
 * Created by TonyChuang on 2016/3/16.
 */
public class WLevelShrPref {

    private SharedPreferences levels;
    private String SHARE_PREFERENCE_NAME = "App_W_Level";
    private String BP_SY_Max = "BP_SY_Max";
    private String BP_SY_Min = "BP_SY_Min";
    private String BP_DI_Max = "BP_DI_Max";
    private String BP_DI_Min = "BP_DI_Min";
    private String BP_HR_Max = "BP_HR_Max";
    private String BP_HR_Min = "BP_HR_Min";
    private String BP_SY_MaxDang = "BP_SY_MaxDang";
    private String BP_SY_MinDang = "BP_SY_MinDang";
    private String BP_DI_MaxDang = "BP_DI_MaxDang";
    private String BP_DI_MinDang = "BP_DI_MinDang";
    private String BP_HR_MaxDang = "BP_HR_MaxDang";
    private String BP_HR_MinDang = "BP_HR_MinDang";
    private String BG_BM_Max = "BG_BM_Max";
    private String BG_BM_Min = "BG_BM_Min";
    private String BG_BM_MaxDang = "BG_BM_MaxDang";
    private String BG_BM_MinDang = "BG_BM_MinDang";
    private String BG_AM_Max = "BG_AM_Max";
    private String BG_AM_Min = "BG_AM_Min";
    private String BG_AM_MaxDang = "BG_AM_MaxDang";
    private String BG_AM_MinDang = "BG_AM_MinDang";

    public WLevelShrPref(Context context, String aid) {
        levels = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
    }

    public WLevelShrPref(Context context, String aid, WLevelRow wLevelRow) {
        levels = context.getSharedPreferences(aid + SHARE_PREFERENCE_NAME, 0);
        levels.edit()
                .putInt(this.BP_SY_Max, wLevelRow.getBP_SY_Max())
                .putInt(this.BP_SY_Min, wLevelRow.getBP_SY_Min())
                .putInt(this.BP_DI_Max, wLevelRow.getBP_DI_Max())
                .putInt(this.BP_DI_Min, wLevelRow.getBP_DI_Min())
                .putInt(this.BP_HR_Max, wLevelRow.getBP_HR_Max())
                .putInt(this.BP_HR_Min, wLevelRow.getBP_HR_Min())
                .putInt(this.BP_SY_MaxDang, wLevelRow.getBP_SY_MaxDang())
                .putInt(this.BP_SY_MinDang, wLevelRow.getBP_SY_MinDang())
                .putInt(this.BP_DI_MaxDang, wLevelRow.getBP_DI_MaxDang())
                .putInt(this.BP_DI_MinDang, wLevelRow.getBP_DI_MinDang())
                .putInt(this.BP_HR_MaxDang, wLevelRow.getBP_HR_MaxDang())
                .putInt(this.BP_HR_MinDang, wLevelRow.getBP_HR_MinDang())
                .putInt(this.BG_BM_Max, wLevelRow.getBG_BM_Max())
                .putInt(this.BG_BM_Min, wLevelRow.getBG_BM_Min())
                .putInt(this.BG_BM_MaxDang, wLevelRow.getBG_BM_MaxDang())
                .putInt(this.BG_BM_MinDang, wLevelRow.getBG_BM_MinDang())
                .putInt(this.BG_AM_Max, wLevelRow.getBG_AM_Max())
                .putInt(this.BG_AM_Min, wLevelRow.getBG_AM_Min())
                .putInt(this.BG_AM_MaxDang, wLevelRow.getBG_AM_MaxDang())
                .putInt(this.BG_AM_MinDang, wLevelRow.getBG_AM_MinDang())
                .apply();
    }

    public int getBP_SY_Max() {
        return levels.getInt(this.BP_SY_Max, 0);
    }

    public int getBP_SY_Min() {
        return levels.getInt(this.BP_SY_Min, 0);
    }

    public int getBP_DI_Max() {
        return levels.getInt(this.BP_DI_Max, 0);
    }

    public int getBP_DI_Min() {
        return levels.getInt(this.BP_DI_Min, 0);
    }

    public int getBP_HR_Max() {
        return levels.getInt(this.BP_HR_Max, 0);
    }

    public int getBP_HR_Min() {
        return levels.getInt(this.BP_HR_Min, 0);
    }

    public int getBP_SY_MaxDang() {
        return levels.getInt(this.BP_SY_MaxDang, 0);
    }

    public int getBP_SY_MinDang() {
        return levels.getInt(this.BP_SY_MinDang, 0);
    }

    public int getBP_DI_MaxDang() {
        return levels.getInt(this.BP_DI_MaxDang, 0);
    }

    public int getBP_DI_MinDang() {
        return levels.getInt(this.BP_DI_MinDang, 0);
    }

    public int getBP_HR_MaxDang() {
        return levels.getInt(this.BP_HR_MaxDang, 0);
    }

    public int getBP_HR_MinDang() {
        return levels.getInt(this.BP_HR_MinDang, 0);
    }

    public int getBG_BM_Max() {
        return levels.getInt(this.BG_BM_Max, 0);
    }

    public int getBG_BM_Min() {
        return levels.getInt(this.BG_BM_Min, 0);
    }

    public int getBG_BM_MaxDang() {
        return levels.getInt(this.BG_BM_MaxDang, 0);
    }

    public int getBG_BM_MinDang() {
        return levels.getInt(this.BG_BM_MinDang, 0);
    }

    public int getBG_AM_Max() {
        return levels.getInt(this.BG_AM_Max, 0);
    }

    public int getBG_AM_Min() {
        return levels.getInt(this.BG_AM_Min, 0);
    }

    public int getBG_AM_MaxDang() {
        return levels.getInt(this.BG_AM_MaxDang, 0);
    }

    public int getBG_AM_MinDang() {
        return levels.getInt(this.BG_AM_MinDang, 0);
    }
}
