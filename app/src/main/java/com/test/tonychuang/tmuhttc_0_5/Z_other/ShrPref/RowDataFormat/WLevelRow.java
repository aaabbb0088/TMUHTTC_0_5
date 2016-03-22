package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat;

/**
 * 個人警戒值上下限設定檔 資料格式
 * Created by TonyChuang on 2016/3/16.
 */
public class WLevelRow {
    private int BP_SY_Max;
    private int BP_SY_Min;
    private int BP_DI_Max;
    private int BP_DI_Min;
    private int BP_HR_Max;
    private int BP_HR_Min;
    private int BP_SY_MaxDang;
    private int BP_SY_MinDang;
    private int BP_DI_MaxDang;
    private int BP_DI_MinDang;
    private int BP_HR_MaxDang;
    private int BP_HR_MinDang;
    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_BM_MaxDang;
    private int BG_BM_MinDang;
    private int BG_AM_Max;
    private int BG_AM_Min;
    private int BG_AM_MaxDang;
    private int BG_AM_MinDang;

    public WLevelRow() {
        this.BP_SY_Max = 0;
        this.BP_SY_Min = 0;
        this.BP_DI_Max = 0;
        this.BP_DI_Min = 0;
        this.BP_HR_Max = 0;
        this.BP_HR_Min = 0;
        this.BP_SY_MaxDang = 0;
        this.BP_SY_MinDang = 0;
        this.BP_DI_MaxDang = 0;
        this.BP_DI_MinDang = 0;
        this.BP_HR_MaxDang = 0;
        this.BP_HR_MinDang = 0;
        this.BG_BM_Max = 0;
        this.BG_BM_Min = 0;
        this.BG_BM_MaxDang = 0;
        this.BG_BM_MinDang = 0;
        this.BG_AM_Max = 0;
        this.BG_AM_Min = 0;
        this.BG_AM_MaxDang = 0;
        this.BG_AM_MinDang = 0;
    }

    public WLevelRow(int BP_SY_Max, int BP_SY_Min, int BP_DI_Max, int BP_DI_Min,
                     int BP_HR_Max, int BP_HR_Min, int BP_SY_MaxDang, int BP_SY_MinDang,
                     int BP_DI_MaxDang, int BP_DI_MinDang, int BP_HR_MaxDang, int BP_HR_MinDang,
                     int BG_BM_Max, int BG_BM_Min, int BG_BM_MaxDang, int BG_BM_MinDang,
                     int BG_AM_Max, int BG_AM_Min, int BG_AM_MaxDang, int BG_AM_MinDang) {
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
