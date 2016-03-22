package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人繳費紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class SPayRow {
    private String SPay_table_id;
    private String SPay_datetime;
    private String SPay_money;
    private String SPay_status;

    public SPayRow() {
        this.SPay_table_id = null;
        this.SPay_datetime = null;
        this.SPay_money = null;
        this.SPay_status = null;
    }

    public SPayRow(String SPay_table_id, String SPay_datetime, String SPay_money, String SPay_status) {
        this.SPay_table_id = SPay_table_id;
        this.SPay_datetime = SPay_datetime;
        this.SPay_money = SPay_money;
        this.SPay_status = SPay_status;
    }

    public String getSPay_table_id() {
        return SPay_table_id;
    }

    public void setSPay_table_id(String SPay_table_id) {
        this.SPay_table_id = SPay_table_id;
    }

    public String getSPay_datetime() {
        return SPay_datetime;
    }

    public void setSPay_datetime(String SPay_datetime) {
        this.SPay_datetime = SPay_datetime;
    }

    public String getSPay_money() {
        return SPay_money;
    }

    public void setSPay_money(String SPay_money) {
        this.SPay_money = SPay_money;
    }

    public String getSPay_status() {
        return SPay_status;
    }

    public void setSPay_status(String SPay_status) {
        this.SPay_status = SPay_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SPay_table_id");
        arrayList.add("SPay_datetime");
        arrayList.add("SPay_money");
        arrayList.add("SPay_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
