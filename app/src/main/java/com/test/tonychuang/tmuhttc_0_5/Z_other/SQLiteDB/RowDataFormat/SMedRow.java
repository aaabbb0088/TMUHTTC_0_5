package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人用藥紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("ServiceMedicineTable")
public class SMedRow extends BaseModel {
    public static final String SMED_SID = "SMed_sid";
    public static final String SMED_TABLE_ID = "SMed_table_id";
    public static final String SMED_DATETIME = "SMed_datetime";
    public static final String SMED_MEDNAME = "SMed_medname";

    @NotNull
    private String SMed_sid;
    @NotNull
    private long SMed_table_id;
    @NotNull
    private String SMed_datetime;
    @NotNull
    private String SMed_medname;

    public SMedRow() {
    }

    public SMedRow(String SMed_sid, long SMed_table_id, String SMed_datetime, String SMed_medname) {
        this.SMed_sid = SMed_sid;
        this.SMed_table_id = SMed_table_id;
        this.SMed_datetime = SMed_datetime;
        this.SMed_medname = SMed_medname;
    }

    public String getSMed_sid() {
        return SMed_sid;
    }

    public void setSMed_sid(String SMed_sid) {
        this.SMed_sid = SMed_sid;
    }

    public long getSMed_table_id() {
        return SMed_table_id;
    }

    public void setSMed_table_id(long SMed_table_id) {
        this.SMed_table_id = SMed_table_id;
    }

    public String getSMed_datetime() {
        return SMed_datetime;
    }

    public void setSMed_datetime(String SMed_datetime) {
        this.SMed_datetime = SMed_datetime;
    }

    public String getSMed_medname() {
        return SMed_medname;
    }

    public void setSMed_medname(String SMed_medname) {
        this.SMed_medname = SMed_medname;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SMed_sid");
        arrayList.add("SMed_table_id");
        arrayList.add("SMed_datetime");
        arrayList.add("SMed_medname");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
