package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血糖每日平均資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class GlyAvgRow {
    private String GAvg_datetime;
    private String GAvg_bm;
    private String GAvg_am;

    public GlyAvgRow() {
        this.GAvg_datetime = null;
        this.GAvg_bm = null;
        this.GAvg_am = null;
    }

    public GlyAvgRow(String GAvg_datetime, String GAvg_bm, String GAvg_am) {
        this.GAvg_datetime = GAvg_datetime;
        this.GAvg_bm = GAvg_bm;
        this.GAvg_am = GAvg_am;
    }

    public String getGAvg_datetime() {
        return GAvg_datetime;
    }

    public void setGAvg_datetime(String GAvg_datetime) {
        this.GAvg_datetime = GAvg_datetime;
    }

    public String getGAvg_bm() {
        return GAvg_bm;
    }

    public void setGAvg_bm(String GAvg_bm) {
        this.GAvg_bm = GAvg_bm;
    }

    public String getGAvg_am() {
        return GAvg_am;
    }

    public void setGAvg_am(String GAvg_am) {
        this.GAvg_am = GAvg_am;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GAvg_datetime");
        arrayList.add("GAvg_bm");
        arrayList.add("GAvg_am");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
