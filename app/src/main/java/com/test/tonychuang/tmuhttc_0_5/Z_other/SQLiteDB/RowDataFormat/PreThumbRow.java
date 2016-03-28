package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by TonyChuang on 2016/3/27.
 */
@Table("PressThumbTable")
public class PreThumbRow extends BaseModel {
    public static final String PDATA_THUMB_TABLE_ID = "PData_thumb_table_id";
    public static final String PDATA_THUMB_SID = "PData_thumb_sid";
    public static final String PDATA_THUMB_DATETIME = "PData_thumb_datetime";
    public static final String PDATA_THUMB_COUNT = "PData_thumb_count";
    public static final String PDATA_THUMB_AIDS = "PData_thumb_aids";

    @NotNull
    private long PData_thumb_table_id;
    @NotNull
    private String PData_thumb_sid;
    @NotNull
    private  String PData_thumb_datetime;
    @NotNull
    private int PData_thumb_count;
    @NotNull
    private String PData_thumb_aids;


    public PreThumbRow() {
    }

    public PreThumbRow(long PData_thumb_table_id, String PData_thumb_sid, String PData_thumb_datetime,
                       int PData_thumb_count, String PData_thumb_aids) {
        this.PData_thumb_table_id = PData_thumb_table_id;
        this.PData_thumb_sid = PData_thumb_sid;
        this.PData_thumb_datetime = PData_thumb_datetime;
        this.PData_thumb_count = PData_thumb_count;
        this.PData_thumb_aids = PData_thumb_aids;
    }

    public long getPData_thumb_table_id() {
        return PData_thumb_table_id;
    }

    public void setPData_thumb_table_id(long PData_thumb_table_id) {
        this.PData_thumb_table_id = PData_thumb_table_id;
    }

    public String getPData_thumb_sid() {
        return PData_thumb_sid;
    }

    public void setPData_thumb_sid(String PData_thumb_sid) {
        this.PData_thumb_sid = PData_thumb_sid;
    }

    public String getPData_thumb_datetime() {
        return PData_thumb_datetime;
    }

    public void setPData_thumb_datetime(String PData_thumb_datetime) {
        this.PData_thumb_datetime = PData_thumb_datetime;
    }

    public int getPData_thumb_count() {
        return PData_thumb_count;
    }

    public void setPData_thumb_count(int PData_thumb_count) {
        this.PData_thumb_count = PData_thumb_count;
    }

    public String getPData_thumb_aids() {
        return PData_thumb_aids;
    }

    public void setPData_thumb_aids(String PData_thumb_aids) {
        this.PData_thumb_aids = PData_thumb_aids;
    }
}
