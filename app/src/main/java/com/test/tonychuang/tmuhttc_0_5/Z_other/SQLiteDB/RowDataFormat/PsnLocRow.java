package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人定位紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("PersonalLocationTable")
public class PsnLocRow extends BaseModel {
    public static final String PSNLOC_AID = "PsnLoc_aid";
    public static final String PSNLOC_TABLE_ID = "PsnLoc_table_id";
    public static final String PSNLOC_DATETIME = "PsnLoc_datetime";
    public static final String PSNLOC_LONGITUDE = "PsnLoc_longitude";
    public static final String PSNLOC_LATITUDE = "PsnLoc_latitude";
    public static final String PSNLOC_UPLOADED_FLAG = "PsnLoc_uploaded_flag"; //此欄位SQL沒有，下載更新資料paser時，自訂填入空字串""

    @NotNull
    private String PsnLoc_aid;
    @NotNull
    private long PsnLoc_table_id;
    @NotNull
    private String PsnLoc_datetime;
    @NotNull
    private double PsnLoc_longitude;
    @NotNull
    private double PsnLoc_latitude;
    @NotNull
    private String PsnLoc_uploaded_flag; //此欄位SQL沒有，下載更新資料paser時，自訂填入空字串""

    public PsnLocRow() {
    }

    public PsnLocRow(String psnLoc_aid, long psnLoc_table_id, String psnLoc_datetime,
                     double psnLoc_longitude, double psnLoc_latitude, String psnLoc_uploaded_flag) {
        PsnLoc_aid = psnLoc_aid;
        PsnLoc_table_id = psnLoc_table_id;
        PsnLoc_datetime = psnLoc_datetime;
        PsnLoc_longitude = psnLoc_longitude;
        PsnLoc_latitude = psnLoc_latitude;
        PsnLoc_uploaded_flag = psnLoc_uploaded_flag;
    }

    public String getPsnLoc_aid() {
        return PsnLoc_aid;
    }

    public void setPsnLoc_aid(String psnLoc_aid) {
        PsnLoc_aid = psnLoc_aid;
    }

    public long getPsnLoc_table_id() {
        return PsnLoc_table_id;
    }

    public void setPsnLoc_table_id(long psnLoc_table_id) {
        PsnLoc_table_id = psnLoc_table_id;
    }

    public String getPsnLoc_datetime() {
        return PsnLoc_datetime;
    }

    public void setPsnLoc_datetime(String psnLoc_datetime) {
        PsnLoc_datetime = psnLoc_datetime;
    }

    public double getPsnLoc_longitude() {
        return PsnLoc_longitude;
    }

    public void setPsnLoc_longitude(double psnLoc_longitude) {
        PsnLoc_longitude = psnLoc_longitude;
    }

    public double getPsnLoc_latitude() {
        return PsnLoc_latitude;
    }

    public void setPsnLoc_latitude(double psnLoc_latitude) {
        PsnLoc_latitude = psnLoc_latitude;
    }

    public String getPsnLoc_uploaded_flag() {
        return PsnLoc_uploaded_flag;
    }

    public void setPsnLoc_uploaded_flag(String psnLoc_uploaded_flag) {
        PsnLoc_uploaded_flag = psnLoc_uploaded_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PsnLoc_aid");
        arrayList.add("PsnLoc_table_id");
        arrayList.add("PsnLoc_datetime");
        arrayList.add("PsnLoc_longitude");
        arrayList.add("PsnLoc_latitude");
        arrayList.add("PsnLoc_uploaded_flag");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("REAL");
        arrayList.add("REAL");
        arrayList.add("TEXT");
        return arrayList;
    }
}
