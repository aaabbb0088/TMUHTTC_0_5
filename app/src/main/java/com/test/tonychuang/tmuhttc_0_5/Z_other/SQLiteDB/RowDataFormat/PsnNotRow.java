package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者個人訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class PsnNotRow {
    private String PsnNot_table_id;
    private String PsnNot_type;
    private String PsnNot_title;
    private String PsnNot_content;
    private String PsnNot_sendr_name;
    private String PsnNot_datetime;
    private String PsnNot_status;

    public PsnNotRow() {
        this.PsnNot_table_id = null;
        this.PsnNot_type = null;
        this.PsnNot_title = null;
        this.PsnNot_content = null;
        this.PsnNot_sendr_name = null;
        this.PsnNot_datetime = null;
        this.PsnNot_status = null;
    }

    public PsnNotRow(String psnNot_table_id, String psnNot_type, String psnNot_title,
                     String psnNot_content, String psnNot_sendr_name, String psnNot_datetime,
                     String psnNot_status) {
        PsnNot_table_id = psnNot_table_id;
        PsnNot_type = psnNot_type;
        PsnNot_title = psnNot_title;
        PsnNot_content = psnNot_content;
        PsnNot_sendr_name = psnNot_sendr_name;
        PsnNot_datetime = psnNot_datetime;
        PsnNot_status = psnNot_status;
    }

    public String getPsnNot_table_id() {
        return PsnNot_table_id;
    }

    public void setPsnNot_table_id(String psnNot_table_id) {
        PsnNot_table_id = psnNot_table_id;
    }

    public String getPsnNot_type() {
        return PsnNot_type;
    }

    public void setPsnNot_type(String psnNot_type) {
        PsnNot_type = psnNot_type;
    }

    public String getPsnNot_title() {
        return PsnNot_title;
    }

    public void setPsnNot_title(String psnNot_title) {
        PsnNot_title = psnNot_title;
    }

    public String getPsnNot_content() {
        return PsnNot_content;
    }

    public void setPsnNot_content(String psnNot_content) {
        PsnNot_content = psnNot_content;
    }

    public String getPsnNot_sendr_name() {
        return PsnNot_sendr_name;
    }

    public void setPsnNot_sendr_name(String psnNot_sendr_name) {
        PsnNot_sendr_name = psnNot_sendr_name;
    }

    public String getPsnNot_datetime() {
        return PsnNot_datetime;
    }

    public void setPsnNot_datetime(String psnNot_datetime) {
        PsnNot_datetime = psnNot_datetime;
    }

    public String getPsnNot_status() {
        return PsnNot_status;
    }

    public void setPsnNot_status(String psnNot_status) {
        PsnNot_status = psnNot_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PsnNot_table_id");
        arrayList.add("PsnNot_type");
        arrayList.add("PsnNot_title");
        arrayList.add("PsnNot_content");
        arrayList.add("PsnNot_sendr_name");
        arrayList.add("PsnNot_datetime");
        arrayList.add("PsnNot_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
