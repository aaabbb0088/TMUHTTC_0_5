package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人血壓留言表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class PreMsgRow {
    private String PMsg_table_id;
    private String PMsg_aid;
    private String PMsg_datetime;
    private String PMsg_content;
    private String PMsg_status;
    private String TableName = " PressMsgTable";

    public PreMsgRow() {
        this.PMsg_table_id = null;
        this.PMsg_aid = null;
        this.PMsg_datetime = null;
        this.PMsg_content = null;
        this.PMsg_status = null;
    }

    public PreMsgRow(String PMsg_table_id, String PMsg_aid, String PMsg_datetime,
                     String PMsg_content, String PMsg_status) {
        this.PMsg_table_id = PMsg_table_id;
        this.PMsg_aid = PMsg_aid;
        this.PMsg_datetime = PMsg_datetime;
        this.PMsg_content = PMsg_content;
        this.PMsg_status = PMsg_status;
    }

    public String getTableName() {
        return this.TableName;
    }

    public String getPMsg_table_id() {
        return PMsg_table_id;
    }

    public void setPMsg_table_id(String PMsg_table_id) {
        this.PMsg_table_id = PMsg_table_id;
    }

    public String getPMsg_aid() {
        return PMsg_aid;
    }

    public void setPMsg_aid(String PMsg_aid) {
        this.PMsg_aid = PMsg_aid;
    }

    public String getPMsg_datetime() {
        return PMsg_datetime;
    }

    public void setPMsg_datetime(String PMsg_datetime) {
        this.PMsg_datetime = PMsg_datetime;
    }

    public String getPMsg_content() {
        return PMsg_content;
    }

    public void setPMsg_content(String PMsg_content) {
        this.PMsg_content = PMsg_content;
    }

    public String getPMsg_status() {
        return PMsg_status;
    }

    public void setPMsg_status(String PMsg_status) {
        this.PMsg_status = PMsg_status;
    }

    public ArrayList<String> getColumnNameAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PMsg_table_id");
        arrayList.add("PMsg_aid");
        arrayList.add("PMsg_datetime");
        arrayList.add("PMsg_content");
        arrayList.add("PMsg_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
