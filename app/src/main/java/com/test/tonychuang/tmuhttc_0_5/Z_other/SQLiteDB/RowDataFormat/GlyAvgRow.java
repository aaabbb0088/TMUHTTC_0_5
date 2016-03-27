package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血糖每日平均資料表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("GlycemiaAvgTable")
public class GlyAvgRow extends BaseModel {
    public static final String GAVG_SID = "GAvg_sid";
    public static final String GAVG_DATETIME = "GAvg_datetime";
    public static final String GAVG_BM = "GAvg_bm";
    public static final String GAVG_AM = "GAvg_am";

    @NotNull
    private String GAvg_sid;
    @NotNull
    private String GAvg_datetime;
    @NotNull
    private int GAvg_bm;
    @NotNull
    private int GAvg_am;

    public GlyAvgRow() {
    }

    public GlyAvgRow(String GAvg_sid, String GAvg_datetime, int GAvg_bm, int GAvg_am) {
        this.GAvg_sid = GAvg_sid;
        this.GAvg_datetime = GAvg_datetime;
        this.GAvg_bm = GAvg_bm;
        this.GAvg_am = GAvg_am;
    }

    public String getGAvg_sid() {
        return GAvg_sid;
    }

    public void setGAvg_sid(String GAvg_sid) {
        this.GAvg_sid = GAvg_sid;
    }

    public String getGAvg_datetime() {
        return GAvg_datetime;
    }

    public void setGAvg_datetime(String GAvg_datetime) {
        this.GAvg_datetime = GAvg_datetime;
    }

    public int getGAvg_bm() {
        return GAvg_bm;
    }

    public void setGAvg_bm(int GAvg_bm) {
        this.GAvg_bm = GAvg_bm;
    }

    public int getGAvg_am() {
        return GAvg_am;
    }

    public void setGAvg_am(int GAvg_am) {
        this.GAvg_am = GAvg_am;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GAvg_sid");
        arrayList.add("GAvg_datetime");
        arrayList.add("GAvg_bm");
        arrayList.add("GAvg_am");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
