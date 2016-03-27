package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者個人訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("PersonalNoticeTable")
public class PsnNotRow extends BaseModel {
    public static final String PSNNOT_TABLE_ID = "PsnNot_table_id";
    public static final String PSNNOT_TYPE = "PsnNot_type";
    public static final String PSNNOT_TITLE = "PsnNot_title";
    public static final String PSNNOT_CONTENT = "PsnNot_content";
    public static final String PSNNOT_SENDR_NAME = "PsnNot_sendr_name";
    public static final String PSNNOT_DATETIME = "PsnNot_datetime";
    public static final String PSNNOT_STATUS = "PsnNot_status";

    @NotNull
    private long PsnNot_table_id;
    @NotNull
    private int PsnNot_type;
    @NotNull
    private String PsnNot_title;
    @NotNull
    private String PsnNot_content;
    @NotNull
    private String PsnNot_sendr_name;
    @NotNull
    private String PsnNot_datetime;
    @NotNull
    private int PsnNot_status;

    public PsnNotRow() {
    }

    public PsnNotRow(long psnNot_table_id, int psnNot_type, String psnNot_title,
                     String psnNot_content, String psnNot_sendr_name,
                     String psnNot_datetime, int psnNot_status) {
        PsnNot_table_id = psnNot_table_id;
        PsnNot_type = psnNot_type;
        PsnNot_title = psnNot_title;
        PsnNot_content = psnNot_content;
        PsnNot_sendr_name = psnNot_sendr_name;
        PsnNot_datetime = psnNot_datetime;
        PsnNot_status = psnNot_status;
    }

    public long getPsnNot_table_id() {
        return PsnNot_table_id;
    }

    public void setPsnNot_table_id(long psnNot_table_id) {
        PsnNot_table_id = psnNot_table_id;
    }

    public int getPsnNot_type() {
        return PsnNot_type;
    }

    public void setPsnNot_type(int psnNot_type) {
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

    public int getPsnNot_status() {
        return PsnNot_status;
    }

    public void setPsnNot_status(int psnNot_status) {
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
