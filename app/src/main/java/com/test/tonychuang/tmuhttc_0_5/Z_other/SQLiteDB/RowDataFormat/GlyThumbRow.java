package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by TonyChuang on 2016/3/27.
 */
@Table("GlycemiaThumbTable")
public class GlyThumbRow extends BaseModel {
    public static final String GDATA_THUMB_TABLE_ID = "GData_thumb_table_id";
    public static final String GDATA_THUMB_SID = "GData_thumb_sid";
    public static final String GDATA_THUMB_DATETIME = "GData_thumb_datetime";
    public static final String GDATA_THUMB_COUNT = "GData_thumb_count";
    public static final String GDATA_THUMB_AIDS = "GData_thumb_aids";

    @NotNull
    private long GData_thumb_table_id;
    @NotNull
    private String GData_thumb_sid;
    @NotNull
    private String GData_thumb_datetime;
    @NotNull
    private int GData_thumb_count;
    @NotNull
    private String GData_thumb_aids;


    public GlyThumbRow() {
    }

    public GlyThumbRow(long GData_thumb_table_id, String GData_thumb_sid,
                       String GData_thumb_datetime, int GData_thumb_count, String GData_thumb_aids) {
        this.GData_thumb_table_id = GData_thumb_table_id;
        this.GData_thumb_sid = GData_thumb_sid;
        this.GData_thumb_datetime = GData_thumb_datetime;
        this.GData_thumb_count = GData_thumb_count;
        this.GData_thumb_aids = GData_thumb_aids;
    }

    public long getGData_thumb_table_id() {
        return GData_thumb_table_id;
    }

    public void setGData_thumb_table_id(long GData_thumb_table_id) {
        this.GData_thumb_table_id = GData_thumb_table_id;
    }

    public String getGData_thumb_sid() {
        return GData_thumb_sid;
    }

    public void setGData_thumb_sid(String GData_thumb_sid) {
        this.GData_thumb_sid = GData_thumb_sid;
    }

    public String getGData_thumb_datetime() {
        return GData_thumb_datetime;
    }

    public void setGData_thumb_datetime(String GData_thumb_datetime) {
        this.GData_thumb_datetime = GData_thumb_datetime;
    }

    public int getGData_thumb_count() {
        return GData_thumb_count;
    }

    public void setGData_thumb_count(int GData_thumb_count) {
        this.GData_thumb_count = GData_thumb_count;
    }

    public String getGData_thumb_aids() {
        return GData_thumb_aids;
    }

    public void setGData_thumb_aids(String GData_thumb_aids) {
        this.GData_thumb_aids = GData_thumb_aids;
    }
}
