package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血壓流水資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class PreDataRow {
    private String PData_table_id;
    private String PData_datetime;
    private String PData_sys;
    private String PData_dia;
    private String PData_hr;
    private String PData_thumb_count;
    private String PData_thumb_aids;

    public PreDataRow() {
        this.PData_table_id = null;
        this.PData_datetime = null;
        this.PData_sys = null;
        this.PData_dia = null;
        this.PData_hr = null;
        this.PData_thumb_count = null;
        this.PData_thumb_aids = null;
    }

    public PreDataRow(String PData_table_id, String PData_datetime,
                      String PData_sys, String PData_dia, String PData_hr, String PData_thumb_count,
                      String PData_thumb_aids, String PData_msg_count, String PData_old_msg_coount) {
        this.PData_table_id = PData_table_id;
        this.PData_datetime = PData_datetime;
        this.PData_sys = PData_sys;
        this.PData_dia = PData_dia;
        this.PData_hr = PData_hr;
        this.PData_thumb_count = PData_thumb_count;
        this.PData_thumb_aids = PData_thumb_aids;
    }

    public String getPData_table_id() {
        return PData_table_id;
    }

    public void setPData_table_id(String PData_table_id) {
        this.PData_table_id = PData_table_id;
    }

    public String getPData_datetime() {
        return PData_datetime;
    }

    public void setPData_datetime(String PData_datetime) {
        this.PData_datetime = PData_datetime;
    }

    public String getPData_sys() {
        return PData_sys;
    }

    public void setPData_sys(String PData_sys) {
        this.PData_sys = PData_sys;
    }

    public String getPData_dia() {
        return PData_dia;
    }

    public void setPData_dia(String PData_dia) {
        this.PData_dia = PData_dia;
    }

    public String getPData_hr() {
        return PData_hr;
    }

    public void setPData_hr(String PData_hr) {
        this.PData_hr = PData_hr;
    }

    public String getPData_thumb_count() {
        return PData_thumb_count;
    }

    public void setPData_thumb_count(String PData_thumb_count) {
        this.PData_thumb_count = PData_thumb_count;
    }

    public String getPData_thumb_aids() {
        return PData_thumb_aids;
    }

    public void setPData_thumb_aids(String PData_thumb_aids) {
        this.PData_thumb_aids = PData_thumb_aids;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
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
