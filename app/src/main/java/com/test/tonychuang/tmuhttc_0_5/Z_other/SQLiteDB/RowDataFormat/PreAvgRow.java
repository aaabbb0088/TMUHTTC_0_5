package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血壓每日平均資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class PreAvgRow {
    private String PAvg_datetime;
    private String PAvg_sys;
    private String PAvg_dia;
    private String PAvg_hr;

    public PreAvgRow() {
        this.PAvg_datetime = null;
        this.PAvg_sys = null;
        this.PAvg_dia = null;
        this.PAvg_hr = null;
    }

    public PreAvgRow(String PAvg_datetime,
                     String PAvg_sys, String PAvg_dia, String PAvg_hr) {
        this.PAvg_datetime = PAvg_datetime;
        this.PAvg_sys = PAvg_sys;
        this.PAvg_dia = PAvg_dia;
        this.PAvg_hr = PAvg_hr;
    }

    public String getPAvg_datetime() {
        return PAvg_datetime;
    }

    public void setPAvg_datetime(String PAvg_datetime) {
        this.PAvg_datetime = PAvg_datetime;
    }

    public String getPAvg_sys() {
        return PAvg_sys;
    }

    public void setPAvg_sys(String PAvg_sys) {
        this.PAvg_sys = PAvg_sys;
    }

    public String getPAvg_dia() {
        return PAvg_dia;
    }

    public void setPAvg_dia(String PAvg_dia) {
        this.PAvg_dia = PAvg_dia;
    }

    public String getPAvg_hr() {
        return PAvg_hr;
    }

    public void setPAvg_hr(String PAvg_hr) {
        this.PAvg_hr = PAvg_hr;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PAvg_datetime");
        arrayList.add("PAvg_sys");
        arrayList.add("PAvg_dia");
        arrayList.add("PAvg_hr");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
