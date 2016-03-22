package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人定位紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class PsnLocRow {
    private String PsnLoc_table_id;
    private String PsnLoc_datetime;
    private String PsnLoc_longitude;
    private String PsnLoc_latitude;
    private String PsnLoc_uploaded_flag;

    public PsnLocRow() {
        this.PsnLoc_table_id = null;
        this.PsnLoc_datetime = null;
        this.PsnLoc_longitude = null;
        this.PsnLoc_latitude = null;
        this.PsnLoc_uploaded_flag = null;
    }

    public PsnLocRow(String psnLoc_table_id, String psnLoc_datetime, String psnLoc_longitude,
                     String psnLoc_latitude, String psnLoc_uploaded_flag) {
        PsnLoc_table_id = psnLoc_table_id;
        PsnLoc_datetime = psnLoc_datetime;
        PsnLoc_longitude = psnLoc_longitude;
        PsnLoc_latitude = psnLoc_latitude;
        PsnLoc_uploaded_flag = psnLoc_uploaded_flag;
    }

    public String getPsnLoc_table_id() {
        return PsnLoc_table_id;
    }

    public void setPsnLoc_table_id(String psnLoc_table_id) {
        PsnLoc_table_id = psnLoc_table_id;
    }

    public String getPsnLoc_datetime() {
        return PsnLoc_datetime;
    }

    public void setPsnLoc_datetime(String psnLoc_datetime) {
        PsnLoc_datetime = psnLoc_datetime;
    }

    public String getPsnLoc_longitude() {
        return PsnLoc_longitude;
    }

    public void setPsnLoc_longitude(String psnLoc_longitude) {
        PsnLoc_longitude = psnLoc_longitude;
    }

    public String getPsnLoc_latitude() {
        return PsnLoc_latitude;
    }

    public void setPsnLoc_uploaded_flag(String psnLoc_uploaded_flag) {
        PsnLoc_uploaded_flag = psnLoc_uploaded_flag;
    }

    public String getPsnLoc_uploaded_flag() {
        return PsnLoc_uploaded_flag;
    }

    public void setPsnLoc_latitude(String psnLoc_latitude) {
        PsnLoc_latitude = psnLoc_latitude;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PsnLoc_table_id");
        arrayList.add("PsnLoc_datetime");
        arrayList.add("PsnLoc_longitude");
        arrayList.add("PsnLoc_latitude");
        arrayList.add("PsnLoc_uploaded_flag");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("REAL");
        arrayList.add("REAL");
        arrayList.add("TEXT");
        return arrayList;
    }
}
