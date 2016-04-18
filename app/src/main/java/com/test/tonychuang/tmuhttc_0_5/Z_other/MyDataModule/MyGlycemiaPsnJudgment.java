package com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule;

import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

/**
 * Created by TonyChuang on 2016/3/31.
 */
public class MyGlycemiaPsnJudgment {
    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_AM_Max;
    private int BG_AM_Min;
    private int BG_BM_MaxDang;
    private int BG_BM_MinDang;
    private int BG_AM_MaxDang;
    private int BG_AM_MinDang;
    private int glyValue;
    private String glyMealFlag;
    private String[] result;

    public MyGlycemiaPsnJudgment() {
    }

    public MyGlycemiaPsnJudgment(WLevelShrPref wLevelShrPref, int glyValue, String glyMealFlag) {
        result = new String[]{"normal", "warn", "dang"};
        this.BG_BM_Max = wLevelShrPref.getBG_BM_Max();
        this.BG_BM_Min = wLevelShrPref.getBG_BM_Min();
        this.BG_AM_Max = wLevelShrPref.getBG_AM_Max();
        this.BG_AM_Min = wLevelShrPref.getBG_AM_Min();
        this.BG_BM_MaxDang = wLevelShrPref.getBG_BM_MaxDang();
        this.BG_BM_MinDang = wLevelShrPref.getBG_BM_MinDang();
        this.BG_AM_MaxDang = wLevelShrPref.getBG_AM_MaxDang();
        this.BG_AM_MinDang = wLevelShrPref.getBG_AM_MinDang();
        this.glyValue = glyValue;
        this.glyMealFlag = glyMealFlag;
    }

    public String getResult() {
        String resultstr;
        switch (glyMealFlag) {
            case "af":
                if (BG_AM_Min <= glyValue && glyValue <= BG_AM_Max) {
                    resultstr = result[0];
                } else if (glyValue < BG_AM_MinDang || BG_AM_MaxDang < glyValue) {
                    resultstr = result[2];
                } else {
                    resultstr = result[1];
                }
                break;
            default:
                if (BG_BM_Min <= glyValue && glyValue <= BG_BM_Max) {
                    resultstr = result[0];
                } else if (glyValue < BG_BM_MinDang || BG_BM_MaxDang < glyValue) {
                    resultstr = result[2];
                } else {
                    resultstr = result[1];
                }
                break;
        }
        return resultstr;
    }
}
