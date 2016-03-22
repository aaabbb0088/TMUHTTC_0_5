package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血糖留言表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class GlyMsgRow {
    private String GMsg_table_id;
    private String GMsg_aid;
    private String GMsg_datetime;
    private String GMsg_content;
    private String GMsg_status;

    public GlyMsgRow() {
        this.GMsg_table_id = null;
        this.GMsg_aid = null;
        this.GMsg_datetime = null;
        this.GMsg_content = null;
        this.GMsg_status = null;
    }

    public GlyMsgRow(String GMsg_table_id, String GMsg_aid, String GMsg_datetime,
                     String GMsg_content, String GMsg_status) {
        this.GMsg_table_id = GMsg_table_id;
        this.GMsg_aid = GMsg_aid;
        this.GMsg_datetime = GMsg_datetime;
        this.GMsg_content = GMsg_content;
        this.GMsg_status = GMsg_status;
    }

    public String getGMsg_table_id() {
        return GMsg_table_id;
    }

    public void setGMsg_table_id(String GMsg_table_id) {
        this.GMsg_table_id = GMsg_table_id;
    }

    public String getGMsg_aid() {
        return GMsg_aid;
    }

    public void setGMsg_aid(String GMsg_aid) {
        this.GMsg_aid = GMsg_aid;
    }

    public String getGMsg_datetime() {
        return GMsg_datetime;
    }

    public void setGMsg_datetime(String GMsg_datetime) {
        this.GMsg_datetime = GMsg_datetime;
    }

    public String getGMsg_content() {
        return GMsg_content;
    }

    public void setGMsg_content(String GMsg_content) {
        this.GMsg_content = GMsg_content;
    }

    public String getGMsg_status() {
        return GMsg_status;
    }

    public void setGMsg_status(String GMsg_status) {
        this.GMsg_status = GMsg_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GMsg_table_id");
        arrayList.add("GMsg_aid");
        arrayList.add("GMsg_datetime");
        arrayList.add("GMsg_content");
        arrayList.add("GMsg_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
