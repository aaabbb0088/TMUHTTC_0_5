package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者好友邀請訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FAddNotRow {
    private String FAddNot_table_id;
    private String FAddNot_fri_aid;
    private String FAddNot_type;
    private String FAddNot_sender_name;
    private String FAddNot_datetime;
    private String FAddNot_status_flag;

    public FAddNotRow() {
        this.FAddNot_table_id = null;
        this.FAddNot_fri_aid = null;
        this.FAddNot_type = null;
        this.FAddNot_sender_name = null;
        this.FAddNot_datetime = null;
        this.FAddNot_status_flag = null;
    }

    public FAddNotRow(String FAddNot_table_id, String FAddNot_fri_aid, String FAddNot_type,
                      String FAddNot_sender_name, String FAddNot_datetime,
                      String FAddNot_status_flag) {
        this.FAddNot_table_id = FAddNot_table_id;
        this.FAddNot_fri_aid = FAddNot_fri_aid;
        this.FAddNot_type = FAddNot_type;
        this.FAddNot_sender_name = FAddNot_sender_name;
        this.FAddNot_datetime = FAddNot_datetime;
        this.FAddNot_status_flag = FAddNot_status_flag;
    }

    public String getFAddNot_table_id() {
        return FAddNot_table_id;
    }

    public void setFAddNot_table_id(String FAddNot_table_id) {
        this.FAddNot_table_id = FAddNot_table_id;
    }

    public String getFAddNot_fri_aid() {
        return FAddNot_fri_aid;
    }

    public void setFAddNot_fri_aid(String FAddNot_fri_aid) {
        this.FAddNot_fri_aid = FAddNot_fri_aid;
    }

    public String getFAddNot_type() {
        return FAddNot_type;
    }

    public void setFAddNot_type(String FAddNot_type) {
        this.FAddNot_type = FAddNot_type;
    }

    public String getFAddNot_sender_name() {
        return FAddNot_sender_name;
    }

    public void setFAddNot_sender_name(String FAddNot_sender_name) {
        this.FAddNot_sender_name = FAddNot_sender_name;
    }

    public String getFAddNot_datetime() {
        return FAddNot_datetime;
    }

    public void setFAddNot_datetime(String FAddNot_datetime) {
        this.FAddNot_datetime = FAddNot_datetime;
    }

    public String getFAddNot_status_flag() {
        return FAddNot_status_flag;
    }

    public void setFAddNot_status_flag(String FAddNot_status_flag) {
        this.FAddNot_status_flag = FAddNot_status_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FAddNot_table_id");
        arrayList.add("FAddNot_fri_aid");
        arrayList.add("FAddNot_type");
        arrayList.add("FAddNot_sender_name");
        arrayList.add("FAddNot_datetime");
        arrayList.add("FAddNot_status_flag");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
