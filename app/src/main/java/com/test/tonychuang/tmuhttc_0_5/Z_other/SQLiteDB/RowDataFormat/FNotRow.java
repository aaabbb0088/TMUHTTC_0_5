package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者好友個人訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FNotRow {
    private String FNot_table_id;
    private String FNot_fri_aid;
    private String FNot_type;
    private String FNot_title;
    private String FNot_content;
    private String FNot_sendr_name;
    private String FNot_datetime;
    private String FNot_status;

    public FNotRow() {
        this.FNot_table_id = null;
        this.FNot_fri_aid = null;
        this.FNot_type = null;
        this.FNot_title = null;
        this.FNot_content = null;
        this.FNot_sendr_name = null;
        this.FNot_datetime = null;
        this.FNot_status = null;
    }

    public FNotRow(String FNot_table_id, String FNot_fri_aid, String FNot_type, String FNot_title,
                   String FNot_content, String FNot_sendr_name, String FNot_datetime,
                   String FNot_status) {
        this.FNot_table_id = FNot_table_id;
        this.FNot_fri_aid = FNot_fri_aid;
        this.FNot_type = FNot_type;
        this.FNot_title = FNot_title;
        this.FNot_content = FNot_content;
        this.FNot_sendr_name = FNot_sendr_name;
        this.FNot_datetime = FNot_datetime;
        this.FNot_status = FNot_status;
    }

    public String getFNot_table_id() {
        return FNot_table_id;
    }

    public void setFNot_table_id(String FNot_table_id) {
        this.FNot_table_id = FNot_table_id;
    }

    public String getFNot_fri_aid() {
        return FNot_fri_aid;
    }

    public void setFNot_fri_aid(String FNot_fri_aid) {
        this.FNot_fri_aid = FNot_fri_aid;
    }

    public String getFNot_type() {
        return FNot_type;
    }

    public void setFNot_type(String FNot_type) {
        this.FNot_type = FNot_type;
    }

    public String getFNot_title() {
        return FNot_title;
    }

    public void setFNot_title(String FNot_title) {
        this.FNot_title = FNot_title;
    }

    public String getFNot_content() {
        return FNot_content;
    }

    public void setFNot_content(String FNot_content) {
        this.FNot_content = FNot_content;
    }

    public String getFNot_sendr_name() {
        return FNot_sendr_name;
    }

    public void setFNot_sendr_name(String FNot_sendr_name) {
        this.FNot_sendr_name = FNot_sendr_name;
    }

    public String getFNot_datetime() {
        return FNot_datetime;
    }

    public void setFNot_datetime(String FNot_datetime) {
        this.FNot_datetime = FNot_datetime;
    }

    public String getFNot_status() {
        return FNot_status;
    }

    public void setFNot_status(String FNot_status) {
        this.FNot_status = FNot_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FNot_table_id");
        arrayList.add("FNot_fri_aid");
        arrayList.add("FNot_type");
        arrayList.add("FNot_title");
        arrayList.add("FNot_content");
        arrayList.add("FNot_sendr_name");
        arrayList.add("FNot_datetime");
        arrayList.add("FNot_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
