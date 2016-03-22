package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人用藥紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class SMedRow {
    private String SMed_table_id;
    private String SMed_datetime;
    private String SMed_bm;

    public SMedRow() {
        this.SMed_table_id = null;
        this.SMed_datetime = null;
        this.SMed_bm = null;
    }

    public SMedRow(String SMed_table_id, String SMed_datetime, String SMed_bm) {
        this.SMed_table_id = SMed_table_id;
        this.SMed_datetime = SMed_datetime;
        this.SMed_bm = SMed_bm;
    }

    public String getSMed_table_id() {
        return SMed_table_id;
    }

    public void setSMed_table_id(String SMed_table_id) {
        this.SMed_table_id = SMed_table_id;
    }

    public String getSMed_datetime() {
        return SMed_datetime;
    }

    public void setSMed_datetime(String SMed_datetime) {
        this.SMed_datetime = SMed_datetime;
    }

    public String getSMed_bm() {
        return SMed_bm;
    }

    public void setSMed_bm(String SMed_bm) {
        this.SMed_bm = SMed_bm;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SMed_table_id");
        arrayList.add("SMed_datetime");
        arrayList.add("SMed_bm");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
