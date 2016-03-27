package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者好友個人訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("FriendNoticeTable")
public class FNotRow extends BaseModel {
    public static final String FNOT_TABLE_ID = "FNot_table_id";
    public static final String FNOT_FRI_SID = "FNot_fri_sid";
    public static final String FNOT_TYPE = "FNot_type";
    public static final String FNOT_TITLE = "FNot_title";
    public static final String FNOT_CONTENT = "FNot_content";
    public static final String FNOT_SENDR_NAME = "FNot_sendr_name";
    public static final String FNOT_DATETIME = "FNot_datetime";
    public static final String FNOT_STATUS = "FNot_status";

    @NotNull
    private long FNot_table_id;
    @NotNull
    private String FNot_fri_sid;
    @NotNull
    private int FNot_type;
    @NotNull
    private String FNot_title;
    @NotNull
    private String FNot_content;
    @NotNull
    private String FNot_sendr_name;
    @NotNull
    private String FNot_datetime;
    @NotNull
    private int FNot_status;

    public FNotRow() {
    }

    public FNotRow(long FNot_table_id, String FNot_fri_sid, int FNot_type, String FNot_title,
                   String FNot_content, String FNot_sendr_name, String FNot_datetime,
                   int FNot_status) {
        this.FNot_table_id = FNot_table_id;
        this.FNot_fri_sid = FNot_fri_sid;
        this.FNot_type = FNot_type;
        this.FNot_title = FNot_title;
        this.FNot_content = FNot_content;
        this.FNot_sendr_name = FNot_sendr_name;
        this.FNot_datetime = FNot_datetime;
        this.FNot_status = FNot_status;
    }

    public long getFNot_table_id() {
        return FNot_table_id;
    }

    public void setFNot_table_id(long FNot_table_id) {
        this.FNot_table_id = FNot_table_id;
    }

    public String getFNot_fri_sid() {
        return FNot_fri_sid;
    }

    public void setFNot_fri_sid(String FNot_fri_sid) {
        this.FNot_fri_sid = FNot_fri_sid;
    }

    public int getFNot_type() {
        return FNot_type;
    }

    public void setFNot_type(int FNot_type) {
        this.FNot_type = FNot_type;
    }

    public String getFNot_title() {
        return FNot_title;
    }

    public void setFNot_title(String FNot_title) {
        this.FNot_title = FNot_title;
    }

    public String getFNot_content() {
        return FNot_content;
    }

    public void setFNot_content(String FNot_content) {
        this.FNot_content = FNot_content;
    }

    public String getFNot_sendr_name() {
        return FNot_sendr_name;
    }

    public void setFNot_sendr_name(String FNot_sendr_name) {
        this.FNot_sendr_name = FNot_sendr_name;
    }

    public String getFNot_datetime() {
        return FNot_datetime;
    }

    public void setFNot_datetime(String FNot_datetime) {
        this.FNot_datetime = FNot_datetime;
    }

    public int getFNot_status() {
        return FNot_status;
    }

    public void setFNot_status(int FNot_status) {
        this.FNot_status = FNot_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FNot_table_id");
        arrayList.add("FNot_fri_aid");
        arrayList.add("FNot_type");
        arrayList.add("FNot_title");
        arrayList.add("FNot_content");
        arrayList.add("FNot_sendr_name");
        arrayList.add("FNot_datetime");
        arrayList.add("FNot_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
