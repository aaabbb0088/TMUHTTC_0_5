package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by TonyChuang on 2016/3/30.
 */
@Table("FriendWLevelTable")
public class FWLevelRow extends BaseModel {
    public static final String FWLEVEL_SID = "FWLevel_Sid";
    public static final String BP_SY_MAX = "BP_SY_Max";
    public static final String BP_SY_MIN = "BP_SY_Min";
    public static final String BP_DI_MAX = "BP_DI_Max";
    public static final String BP_DI_MIN = "BP_DI_Min";
    public static final String BP_HR_MAX = "BP_HR_Max";
    public static final String BP_HR_MIN = "BP_HR_Min";
    public static final String BP_SY_MAXDANG = "BP_SY_MaxDang";
    public static final String BP_SY_MINDANG = "BP_SY_MinDang";
    public static final String BP_DI_MAXDANG = "BP_DI_MaxDang";
    public static final String BP_DI_MINDANG = "BP_DI_MinDang";
    public static final String BP_HR_MAXDANG = "BP_HR_MaxDang";
    public static final String BP_HR_MINDANG = "BP_HR_MinDang";
    public static final String BG_BM_MAX = "BG_BM_Max";
    public static final String BG_BM_MIN = "BG_BM_Min";
    public static final String BG_BM_MAXDANG = "BG_BM_MaxDang";
    public static final String BG_BM_MINDANG = "BG_BM_MinDang";
    public static final String BG_AM_MAX = "BG_AM_Max";
    public static final String BG_AM_MIN = "BG_AM_Min";
    public static final String BG_AM_MAXDANG = "BG_AM_MaxDang";
    public static final String BG_AM_MINDANG = "BG_AM_MinDang";

    @NotNull
    private String FWLevel_Sid;
    @NotNull
    private int BP_SY_Max;
    @NotNull
    private int BP_SY_Min;
    @NotNull
    private int BP_DI_Max;
    @NotNull
    private int BP_DI_Min;
    @NotNull
    private int BP_HR_Max;
    @NotNull
    private int BP_HR_Min;
    @NotNull
    private int BP_SY_MaxDang;
    @NotNull
    private int BP_SY_MinDang;
    @NotNull
    private int BP_DI_MaxDang;
    @NotNull
    private int BP_DI_MinDang;
    @NotNull
    private int BP_HR_MaxDang;
    @NotNull
    private int BP_HR_MinDang;
    @NotNull
    private int BG_BM_Max;
    @NotNull
    private int BG_BM_Min;
    @NotNull
    private int BG_BM_MaxDang;
    @NotNull
    private int BG_BM_MinDang;
    @NotNull
    private int BG_AM_Max;
    @NotNull
    private int BG_AM_Min;
    @NotNull
    private int BG_AM_MaxDang;
    @NotNull
    private int BG_AM_MinDang;

    public FWLevelRow() {
    }

    public FWLevelRow(String FWLevel_Sid, int BP_SY_Max, int BP_SY_Min, int BP_DI_Max,
                      int BP_DI_Min, int BP_HR_Max, int BP_HR_Min, int BP_SY_MaxDang,
                      int BP_SY_MinDang, int BP_DI_MaxDang, int BP_DI_MinDang, int BP_HR_MaxDang,
                      int BP_HR_MinDang, int BG_BM_Max, int BG_BM_Min, int BG_BM_MaxDang,
                      int BG_BM_MinDang, int BG_AM_Max, int BG_AM_Min, int BG_AM_MaxDang,
                      int BG_AM_MinDang) {
        this.FWLevel_Sid = FWLevel_Sid;
        this.BP_SY_Max = BP_SY_Max;
        this.BP_SY_Min = BP_SY_Min;
        this.BP_DI_Max = BP_DI_Max;
        this.BP_DI_Min = BP_DI_Min;
        this.BP_HR_Max = BP_HR_Max;
        this.BP_HR_Min = BP_HR_Min;
        this.BP_SY_MaxDang = BP_SY_MaxDang;
        this.BP_SY_MinDang = BP_SY_MinDang;
        this.BP_DI_MaxDang = BP_DI_MaxDang;
        this.BP_DI_MinDang = BP_DI_MinDang;
        this.BP_HR_MaxDang = BP_HR_MaxDang;
        this.BP_HR_MinDang = BP_HR_MinDang;
        this.BG_BM_Max = BG_BM_Max;
        this.BG_BM_Min = BG_BM_Min;
        this.BG_BM_MaxDang = BG_BM_MaxDang;
        this.BG_BM_MinDang = BG_BM_MinDang;
        this.BG_AM_Max = BG_AM_Max;
        this.BG_AM_Min = BG_AM_Min;
        this.BG_AM_MaxDang = BG_AM_MaxDang;
        this.BG_AM_MinDang = BG_AM_MinDang;
    }

    public String getFWLevel_Sid() {
        return FWLevel_Sid;
    }

    public void setFWLevel_Sid(String FWLevel_Sid) {
        this.FWLevel_Sid = FWLevel_Sid;
    }

    public int getBP_SY_Max() {
        return BP_SY_Max;
    }

    public void setBP_SY_Max(int BP_SY_Max) {
        this.BP_SY_Max = BP_SY_Max;
    }

    public int getBP_SY_Min() {
        return BP_SY_Min;
    }

    public void setBP_SY_Min(int BP_SY_Min) {
        this.BP_SY_Min = BP_SY_Min;
    }

    public int getBP_DI_Max() {
        return BP_DI_Max;
    }

    public void setBP_DI_Max(int BP_DI_Max) {
        this.BP_DI_Max = BP_DI_Max;
    }

    public int getBP_DI_Min() {
        return BP_DI_Min;
    }

    public void setBP_DI_Min(int BP_DI_Min) {
        this.BP_DI_Min = BP_DI_Min;
    }

    public int getBP_HR_Max() {
        return BP_HR_Max;
    }

    public void setBP_HR_Max(int BP_HR_Max) {
        this.BP_HR_Max = BP_HR_Max;
    }

    public int getBP_HR_Min() {
        return BP_HR_Min;
    }

    public void setBP_HR_Min(int BP_HR_Min) {
        this.BP_HR_Min = BP_HR_Min;
    }

    public int getBP_SY_MaxDang() {
        return BP_SY_MaxDang;
    }

    public void setBP_SY_MaxDang(int BP_SY_MaxDang) {
        this.BP_SY_MaxDang = BP_SY_MaxDang;
    }

    public int getBP_SY_MinDang() {
        return BP_SY_MinDang;
    }

    public void setBP_SY_MinDang(int BP_SY_MinDang) {
        this.BP_SY_MinDang = BP_SY_MinDang;
    }

    public int getBP_DI_MaxDang() {
        return BP_DI_MaxDang;
    }

    public void setBP_DI_MaxDang(int BP_DI_MaxDang) {
        this.BP_DI_MaxDang = BP_DI_MaxDang;
    }

    public int getBP_DI_MinDang() {
        return BP_DI_MinDang;
    }

    public void setBP_DI_MinDang(int BP_DI_MinDang) {
        this.BP_DI_MinDang = BP_DI_MinDang;
    }

    public int getBP_HR_MaxDang() {
        return BP_HR_MaxDang;
    }

    public void setBP_HR_MaxDang(int BP_HR_MaxDang) {
        this.BP_HR_MaxDang = BP_HR_MaxDang;
    }

    public int getBP_HR_MinDang() {
        return BP_HR_MinDang;
    }

    public void setBP_HR_MinDang(int BP_HR_MinDang) {
        this.BP_HR_MinDang = BP_HR_MinDang;
    }

    public int getBG_BM_Max() {
        return BG_BM_Max;
    }

    public void setBG_BM_Max(int BG_BM_Max) {
        this.BG_BM_Max = BG_BM_Max;
    }

    public int getBG_BM_Min() {
        return BG_BM_Min;
    }

    public void setBG_BM_Min(int BG_BM_Min) {
        this.BG_BM_Min = BG_BM_Min;
    }

    public int getBG_BM_MaxDang() {
        return BG_BM_MaxDang;
    }

    public void setBG_BM_MaxDang(int BG_BM_MaxDang) {
        this.BG_BM_MaxDang = BG_BM_MaxDang;
    }

    public int getBG_BM_MinDang() {
        return BG_BM_MinDang;
    }

    public void setBG_BM_MinDang(int BG_BM_MinDang) {
        this.BG_BM_MinDang = BG_BM_MinDang;
    }

    public int getBG_AM_Max() {
        return BG_AM_Max;
    }

    public void setBG_AM_Max(int BG_AM_Max) {
        this.BG_AM_Max = BG_AM_Max;
    }

    public int getBG_AM_Min() {
        return BG_AM_Min;
    }

    public void setBG_AM_Min(int BG_AM_Min) {
        this.BG_AM_Min = BG_AM_Min;
    }

    public int getBG_AM_MaxDang() {
        return BG_AM_MaxDang;
    }

    public void setBG_AM_MaxDang(int BG_AM_MaxDang) {
        this.BG_AM_MaxDang = BG_AM_MaxDang;
    }

    public int getBG_AM_MinDang() {
        return BG_AM_MinDang;
    }

    public void setBG_AM_MinDang(int BG_AM_MinDang) {
        this.BG_AM_MinDang = BG_AM_MinDang;
    }
}
