package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血糖流水資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class GlyDataRow {
    private String GData_table_id;
    private String GData_datetime;
    private String GData_value;
    private String GData_meal_flag;
    private String GData_thumb_count;
    private String GData_thumb_aids;

    public GlyDataRow() {
        this.GData_table_id = null;
        this.GData_datetime = null;
        this.GData_value = null;
        this.GData_meal_flag = null;
        this.GData_thumb_count = null;
        this.GData_thumb_aids = null;
    }

    public GlyDataRow(String GData_table_id, String GData_datetime, String GData_value,
                      String GData_meal_flag, String GData_thumb_count, String GData_thumb_aids,
                      String GData_msg_count, String GData_old_msg_coount) {
        this.GData_table_id = GData_table_id;
        this.GData_datetime = GData_datetime;
        this.GData_value = GData_value;
        this.GData_meal_flag = GData_meal_flag;
        this.GData_thumb_count = GData_thumb_count;
        this.GData_thumb_aids = GData_thumb_aids;
    }

    public String getGData_table_id() {
        return GData_table_id;
    }

    public void setGData_table_id(String GData_table_id) {
        this.GData_table_id = GData_table_id;
    }

    public String getGData_datetime() {
        return GData_datetime;
    }

    public void setGData_datetime(String GData_datetime) {
        this.GData_datetime = GData_datetime;
    }

    public String getGData_value() {
        return GData_value;
    }

    public void setGData_value(String GData_value) {
        this.GData_value = GData_value;
    }

    public String getGData_meal_flag() {
        return GData_meal_flag;
    }

    public void setGData_meal_flag(String GData_meal_flag) {
        this.GData_meal_flag = GData_meal_flag;
    }

    public String getGData_thumb_count() {
        return GData_thumb_count;
    }

    public void setGData_thumb_count(String GData_thumb_count) {
        this.GData_thumb_count = GData_thumb_count;
    }

    public String getGData_thumb_aids() {
        return GData_thumb_aids;
    }

    public void setGData_thumb_aids(String GData_thumb_aids) {
        this.GData_thumb_aids = GData_thumb_aids;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GData_table_id");
        arrayList.add("GData_datetime");
        arrayList.add("GData_value");
        arrayList.add("GData_meal_flag");
        arrayList.add("GData_thumb_count");
        arrayList.add("GData_thumb_aids");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        return arrayList;
    }
}
