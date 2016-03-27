package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血壓每日平均資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("PressAvgTable")
public class PreAvgRow extends BaseModel {
    public static final String PAVG_SID = "PAvg_sid";
    public static final String PAVG_DATETIME = "PAvg_datetime";
    public static final String PAVG_SYS = "PAvg_sys";
    public static final String PAVG_DIA = "PAvg_dia";
    public static final String PAVG_HR = "PAvg_hr";

    @NotNull
    private String PAvg_sid;
    @NotNull
    private String PAvg_datetime;
    @NotNull
    private int PAvg_sys;
    @NotNull
    private int PAvg_dia;
    @NotNull
    private int PAvg_hr;

    public PreAvgRow() {
    }

    public PreAvgRow(String PAvg_sid, String PAvg_datetime, int PAvg_sys, int PAvg_dia, int PAvg_hr) {
        this.PAvg_sid = PAvg_sid;
        this.PAvg_datetime = PAvg_datetime;
        this.PAvg_sys = PAvg_sys;
        this.PAvg_dia = PAvg_dia;
        this.PAvg_hr = PAvg_hr;
    }

    public String getPAvg_sid() {
        return PAvg_sid;
    }

    public void setPAvg_sid(String PAvg_sid) {
        this.PAvg_sid = PAvg_sid;
    }

    public String getPAvg_datetime() {
        return PAvg_datetime;
    }

    public void setPAvg_datetime(String PAvg_datetime) {
        this.PAvg_datetime = PAvg_datetime;
    }

    public int getPAvg_sys() {
        return PAvg_sys;
    }

    public void setPAvg_sys(int PAvg_sys) {
        this.PAvg_sys = PAvg_sys;
    }

    public int getPAvg_dia() {
        return PAvg_dia;
    }

    public void setPAvg_dia(int PAvg_dia) {
        this.PAvg_dia = PAvg_dia;
    }

    public int getPAvg_hr() {
        return PAvg_hr;
    }

    public void setPAvg_hr(int PAvg_hr) {
        this.PAvg_hr = PAvg_hr;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PAvg_sid");
        arrayList.add("PAvg_datetime");
        arrayList.add("PAvg_sys");
        arrayList.add("PAvg_dia");
        arrayList.add("PAvg_hr");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
