package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人服務歷程紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("ServiceRecordTable")
public class SRcrdRow extends BaseModel {
    public static final String SRCRD_SID = "SRcrd_sid";
    public static final String SRCRD_TABLE_ID = "SRcrd_table_id";
    public static final String SRCRD_DATETIME = "SRcrd_datetime";
    public static final String SRCRD_TYPE = "SRcrd_type";

    @NotNull
    private String SRcrd_sid;
    @NotNull
    private long SRcrd_table_id;
    @NotNull
    private String SRcrd_datetime;
    @NotNull
    private int SRcrd_type;

    public SRcrdRow() {
    }

    public SRcrdRow(String SRcrd_sid, long SRcrd_table_id, String SRcrd_datetime, int SRcrd_type) {
        this.SRcrd_sid = SRcrd_sid;
        this.SRcrd_table_id = SRcrd_table_id;
        this.SRcrd_datetime = SRcrd_datetime;
        this.SRcrd_type = SRcrd_type;
    }

    public String getSRcrd_sid() {
        return SRcrd_sid;
    }

    public void setSRcrd_sid(String SRcrd_sid) {
        this.SRcrd_sid = SRcrd_sid;
    }

    public long getSRcrd_table_id() {
        return SRcrd_table_id;
    }

    public void setSRcrd_table_id(long SRcrd_table_id) {
        this.SRcrd_table_id = SRcrd_table_id;
    }

    public String getSRcrd_datetime() {
        return SRcrd_datetime;
    }

    public void setSRcrd_datetime(String SRcrd_datetime) {
        this.SRcrd_datetime = SRcrd_datetime;
    }

    public int getSRcrd_type() {
        return SRcrd_type;
    }

    public void setSRcrd_type(int SRcrd_type) {
        this.SRcrd_type = SRcrd_type;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SRcrd_sid");
        arrayList.add("SRcrd_table_id");
        arrayList.add("SRcrd_datetime");
        arrayList.add("SRcrd_type");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
