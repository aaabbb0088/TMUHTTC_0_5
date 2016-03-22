package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * 個人服務歷程紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class SRcrdRow {
    private String SRcrd_table_id;
    private String SRcrd_datetime;
    private String SRcrd_type;

    public SRcrdRow() {
        this.SRcrd_table_id = null;
        this.SRcrd_datetime = null;
        this.SRcrd_type = null;
    }

    public SRcrdRow(String SRcrd_table_id, String SRcrd_datetime, String SRcrd_type) {
        this.SRcrd_table_id = SRcrd_table_id;
        this.SRcrd_datetime = SRcrd_datetime;
        this.SRcrd_type = SRcrd_type;
    }

    public String getSRcrd_table_id() {
        return SRcrd_table_id;
    }

    public void setSRcrd_table_id(String SRcrd_table_id) {
        this.SRcrd_table_id = SRcrd_table_id;
    }

    public String getSRcrd_datetime() {
        return SRcrd_datetime;
    }

    public void setSRcrd_datetime(String SRcrd_datetime) {
        this.SRcrd_datetime = SRcrd_datetime;
    }

    public String getSRcrd_type() {
        return SRcrd_type;
    }

    public void setSRcrd_type(String SRcrd_type) {
        this.SRcrd_type = SRcrd_type;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SRcrd_table_id");
        arrayList.add("SRcrd_datetime");
        arrayList.add("SRcrd_type");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
