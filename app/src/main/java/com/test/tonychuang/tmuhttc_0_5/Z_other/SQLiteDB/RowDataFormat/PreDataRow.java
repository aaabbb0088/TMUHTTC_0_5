package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血壓流水資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("PressDataTable")
public class PreDataRow extends BaseModel {
    public static final String PDATA_SID = "PData_sid";
    public static final String PDATA_TABLE_ID = "PData_table_id";
    public static final String PDATA_DATETIME = "PData_datetime";
    public static final String PDATA_SYS = "PData_sys";
    public static final String PDATA_DIA = "PData_dia";
    public static final String PDATA_HR = "PData_hr";

    @NotNull
    private String PData_sid;
    @NotNull
    private long PData_table_id;
    @NotNull
    private String PData_datetime;
    @NotNull
    private int PData_sys;
    @NotNull
    private int PData_dia;
    @NotNull
    private int PData_hr;


    public PreDataRow() {
    }

    public PreDataRow(String PData_sid, long PData_table_id, String PData_datetime,
                      int PData_sys, int PData_dia, int PData_hr) {
        this.PData_sid = PData_sid;
        this.PData_table_id = PData_table_id;
        this.PData_datetime = PData_datetime;
        this.PData_sys = PData_sys;
        this.PData_dia = PData_dia;
        this.PData_hr = PData_hr;
    }

    public String getPData_sid() {
        return PData_sid;
    }

    public void setPData_sid(String PData_sid) {
        this.PData_sid = PData_sid;
    }

    public long getPData_table_id() {
        return PData_table_id;
    }

    public void setPData_table_id(long PData_table_id) {
        this.PData_table_id = PData_table_id;
    }

    public String getPData_datetime() {
        return PData_datetime;
    }

    public void setPData_datetime(String PData_datetime) {
        this.PData_datetime = PData_datetime;
    }

    public int getPData_sys() {
        return PData_sys;
    }

    public void setPData_sys(int PData_sys) {
        this.PData_sys = PData_sys;
    }

    public int getPData_dia() {
        return PData_dia;
    }

    public void setPData_dia(int PData_dia) {
        this.PData_dia = PData_dia;
    }

    public int getPData_hr() {
        return PData_hr;
    }

    public void setPData_hr(int PData_hr) {
        this.PData_hr = PData_hr;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PData_sid");
        arrayList.add("PData_table_id");
        arrayList.add("PData_datetime");
        arrayList.add("PData_sys");
        arrayList.add("PData_dia");
        arrayList.add("PData_hr");
        arrayList.add("PData_thumb_count");
        arrayList.add("PData_thumb_aids");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        return arrayList;
    }
}
