package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人健康報告書表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class SRprtRow {
    private String SRprt_table_id;
    private String SRprt_datetime;
    private String SRprt_URL;
    private String SRprt_path;

    public SRprtRow() {
        this.SRprt_table_id = null;
        this.SRprt_datetime = null;
        this.SRprt_URL = null;
        this.SRprt_path = null;
    }

    public SRprtRow(String SRprt_table_id, String SRprt_datetime, String SRprt_URL,
                    String SRprt_path) {
        this.SRprt_table_id = SRprt_table_id;
        this.SRprt_datetime = SRprt_datetime;
        this.SRprt_URL = SRprt_URL;
        this.SRprt_path = SRprt_path;
    }

    public String getSRprt_table_id() {
        return SRprt_table_id;
    }

    public void setSRprt_table_id(String SRprt_table_id) {
        this.SRprt_table_id = SRprt_table_id;
    }

    public String getSRprt_datetime() {
        return SRprt_datetime;
    }

    public void setSRprt_datetime(String SRprt_datetime) {
        this.SRprt_datetime = SRprt_datetime;
    }

    public String getSRprt_URL() {
        return SRprt_URL;
    }

    public void setSRprt_URL(String SRprt_URL) {
        this.SRprt_URL = SRprt_URL;
    }

    public String getSRprt_path() {
        return SRprt_path;
    }

    public void setSRprt_path(String SRprt_path) {
        this.SRprt_path = SRprt_path;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SRprt_table_id");
        arrayList.add("SRprt_datetime");
        arrayList.add("SRprt_URL");
        arrayList.add("SRprt_path");
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
