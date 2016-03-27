package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人血壓留言表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("PressMsgTable")
public class PreMsgRow extends BaseModel {
    public static final String PMSG_SID = "PMsg_sid";
    public static final String PMSG_TABLE_ID = "PMsg_table_id";
    public static final String PMSG_WRITER_AID = "PMsg_writer_aid";
    public static final String PMSG_DATETIME = "PMsg_datetime";
    public static final String PMSG_CONTENT = "PMsg_content";
    public static final String PMSG_STATUS = "PMsg_status";

    @NotNull
    private String PMsg_sid;
    @NotNull
    private long PMsg_table_id;
    @NotNull
    private String PMsg_writer_aid;
    @NotNull
    private String PMsg_datetime;
    @NotNull
    private String PMsg_content;
    @NotNull
    private int PMsg_status;

    public PreMsgRow() {
    }

    public PreMsgRow(String PMsg_sid, long PMsg_table_id, String PMsg_writer_aid, String PMsg_datetime,
                     String PMsg_content, int PMsg_status) {
        this.PMsg_sid = PMsg_sid;
        this.PMsg_table_id = PMsg_table_id;
        this.PMsg_writer_aid = PMsg_writer_aid;
        this.PMsg_datetime = PMsg_datetime;
        this.PMsg_content = PMsg_content;
        this.PMsg_status = PMsg_status;
    }

    public String getPMsg_sid() {
        return PMsg_sid;
    }

    public void setPMsg_sid(String PMsg_sid) {
        this.PMsg_sid = PMsg_sid;
    }

    public long getPMsg_table_id() {
        return PMsg_table_id;
    }

    public void setPMsg_table_id(long PMsg_table_id) {
        this.PMsg_table_id = PMsg_table_id;
    }

    public String getPMsg_writer_aid() {
        return PMsg_writer_aid;
    }

    public void setPMsg_writer_aid(String PMsg_writer_aid) {
        this.PMsg_writer_aid = PMsg_writer_aid;
    }

    public String getPMsg_datetime() {
        return PMsg_datetime;
    }

    public void setPMsg_datetime(String PMsg_datetime) {
        this.PMsg_datetime = PMsg_datetime;
    }

    public String getPMsg_content() {
        return PMsg_content;
    }

    public void setPMsg_content(String PMsg_content) {
        this.PMsg_content = PMsg_content;
    }

    public int getPMsg_status() {
        return PMsg_status;
    }

    public void setPMsg_status(int PMsg_status) {
        this.PMsg_status = PMsg_status;
    }

    public ArrayList<String> getColumnNameAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("PMsg_sid");
        arrayList.add("PMsg_table_id");
        arrayList.add("PMsg_writer_aid");
        arrayList.add("PMsg_datetime");
        arrayList.add("PMsg_content");
        arrayList.add("PMsg_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
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
