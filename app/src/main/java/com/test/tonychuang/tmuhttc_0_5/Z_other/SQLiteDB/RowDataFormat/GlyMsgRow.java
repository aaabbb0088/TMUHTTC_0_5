package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血糖留言表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("GlycemiaMsgTable")
public class GlyMsgRow extends BaseModel {
    public static final String GMSG_SID = "GMsg_sid";
    public static final String GMSG_TABLE_ID = "GMsg_table_id";
    public static final String GMSG_WRITER_AID = "GMsg_writer_aid";
    public static final String GMSG_DATETIME = "GMsg_datetime";
    public static final String GMSG_CONTENT = "GMsg_content";
    public static final String GMSG_STATUS = "GMsg_status";

    @NotNull
    private String GMsg_sid;
    @NotNull
    private long GMsg_table_id;
    @NotNull
    private String GMsg_writer_aid;
    @NotNull
    private String GMsg_datetime;
    @NotNull
    private String GMsg_content;
    @NotNull
    private int GMsg_status;

    public GlyMsgRow() {
    }

    public GlyMsgRow(String GMsg_sid, long GMsg_table_id, String GMsg_writer_aid, String GMsg_datetime,
                     String GMsg_content, int GMsg_status) {
        this.GMsg_sid = GMsg_sid;
        this.GMsg_table_id = GMsg_table_id;
        this.GMsg_writer_aid = GMsg_writer_aid;
        this.GMsg_datetime = GMsg_datetime;
        this.GMsg_content = GMsg_content;
        this.GMsg_status = GMsg_status;
    }

    public String getGMsg_sid() {
        return GMsg_sid;
    }

    public void setGMsg_sid(String GMsg_sid) {
        this.GMsg_sid = GMsg_sid;
    }

    public long getGMsg_table_id() {
        return GMsg_table_id;
    }

    public void setGMsg_table_id(long GMsg_table_id) {
        this.GMsg_table_id = GMsg_table_id;
    }

    public String getGMsg_writer_aid() {
        return GMsg_writer_aid;
    }

    public void setGMsg_writer_aid(String GMsg_writer_aid) {
        this.GMsg_writer_aid = GMsg_writer_aid;
    }

    public String getGMsg_datetime() {
        return GMsg_datetime;
    }

    public void setGMsg_datetime(String GMsg_datetime) {
        this.GMsg_datetime = GMsg_datetime;
    }

    public String getGMsg_content() {
        return GMsg_content;
    }

    public void setGMsg_content(String GMsg_content) {
        this.GMsg_content = GMsg_content;
    }

    public int getGMsg_status() {
        return GMsg_status;
    }

    public void setGMsg_status(int GMsg_status) {
        this.GMsg_status = GMsg_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("GMsg_sid");
        arrayList.add("GMsg_table_id");
        arrayList.add("GMsg_writer_aid");
        arrayList.add("GMsg_datetime");
        arrayList.add("GMsg_content");
        arrayList.add("GMsg_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
