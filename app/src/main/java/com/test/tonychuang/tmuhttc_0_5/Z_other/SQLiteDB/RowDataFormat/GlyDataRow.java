package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血糖流水資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("GlycemiaDataTable")
public class GlyDataRow extends BaseModel {
    public static final String GDATA_SID = "GData_sid";
    public static final String GDATA_TABLE_ID = "GData_table_id";
    public static final String GDATA_DATETIME = "GData_datetime";
    public static final String GDATA_VALUE = "GData_value";
    public static final String GDATA_MEAL_FLAG = "GData_meal_flag";

    @NotNull
    private String GData_sid;
    @NotNull
    private long GData_table_id;
    @NotNull
    private String GData_datetime;
    @NotNull
    private int GData_value;
    @NotNull
    private String GData_meal_flag;


    public GlyDataRow() {
    }

    public GlyDataRow(String GData_sid, long GData_table_id, String GData_datetime,
                      int GData_value, String GData_meal_flag) {
        this.GData_sid = GData_sid;
        this.GData_table_id = GData_table_id;
        this.GData_datetime = GData_datetime;
        this.GData_value = GData_value;
        this.GData_meal_flag = GData_meal_flag;
    }

    public String getGData_sid() {
        return GData_sid;
    }

    public void setGData_sid(String GData_sid) {
        this.GData_sid = GData_sid;
    }

    public long getGData_table_id() {
        return GData_table_id;
    }

    public void setGData_table_id(long GData_table_id) {
        this.GData_table_id = GData_table_id;
    }

    public String getGData_datetime() {
        return GData_datetime;
    }

    public void setGData_datetime(String GData_datetime) {
        this.GData_datetime = GData_datetime;
    }

    public int getGData_value() {
        return GData_value;
    }

    public void setGData_value(int GData_value) {
        this.GData_value = GData_value;
    }

    public String getGData_meal_flag() {
        return GData_meal_flag;
    }

    public void setGData_meal_flag(String GData_meal_flag) {
        this.GData_meal_flag = GData_meal_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GData_sid");
        arrayList.add("GData_table_id");
        arrayList.add("GData_datetime");
        arrayList.add("GData_value");
        arrayList.add("GData_meal_flag");
        arrayList.add("GData_thumb_count");
        arrayList.add("GData_thumb_aids");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        return arrayList;
    }
}
