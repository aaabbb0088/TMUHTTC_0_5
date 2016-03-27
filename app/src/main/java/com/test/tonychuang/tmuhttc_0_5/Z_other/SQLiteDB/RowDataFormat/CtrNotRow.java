package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者中心訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("CenterNoticeTable")
public class CtrNotRow extends BaseModel {
    public static final String CTRNOT_TABLE_ID = "CtrNot_table_id";
    public static final String CTRNOT_TYPE = "CtrNot_type";
    public static final String CTRNOT_TITLE = "CtrNot_title";
    public static final String CTRNOT_CONTENT = "CtrNot_content";
    public static final String CTRNOT_SENDR_NAME = "CtrNot_sendr_name";
    public static final String CTRNOT_DATETIME = "CtrNot_datetime";
    public static final String CTRNOT_STATUS = "CtrNot_status";

    @NotNull
    private long CtrNot_table_id;
    @NotNull
    private int CtrNot_type;
    @NotNull
    private String CtrNot_title;
    @NotNull
    private String CtrNot_content;
    @NotNull
    private String CtrNot_sendr_name;
    @NotNull
    private String CtrNot_datetime;
    @NotNull
    private int CtrNot_status;

    public CtrNotRow() {
    }

    public CtrNotRow(long ctrNot_table_id, int ctrNot_type, String ctrNot_title,
                     String ctrNot_content, String ctrNot_sendr_name, String ctrNot_datetime,
                     int ctrNot_status) {
        CtrNot_table_id = ctrNot_table_id;
        CtrNot_type = ctrNot_type;
        CtrNot_title = ctrNot_title;
        CtrNot_content = ctrNot_content;
        CtrNot_sendr_name = ctrNot_sendr_name;
        CtrNot_datetime = ctrNot_datetime;
        CtrNot_status = ctrNot_status;
    }

    public long getCtrNot_table_id() {
        return CtrNot_table_id;
    }

    public void setCtrNot_table_id(long ctrNot_table_id) {
        CtrNot_table_id = ctrNot_table_id;
    }

    public int getCtrNot_type() {
        return CtrNot_type;
    }

    public void setCtrNot_type(int ctrNot_type) {
        CtrNot_type = ctrNot_type;
    }

    public String getCtrNot_title() {
        return CtrNot_title;
    }

    public void setCtrNot_title(String ctrNot_title) {
        CtrNot_title = ctrNot_title;
    }

    public String getCtrNot_content() {
        return CtrNot_content;
    }

    public void setCtrNot_content(String ctrNot_content) {
        CtrNot_content = ctrNot_content;
    }

    public String getCtrNot_sendr_name() {
        return CtrNot_sendr_name;
    }

    public void setCtrNot_sendr_name(String ctrNot_sendr_name) {
        CtrNot_sendr_name = ctrNot_sendr_name;
    }

    public String getCtrNot_datetime() {
        return CtrNot_datetime;
    }

    public void setCtrNot_datetime(String ctrNot_datetime) {
        CtrNot_datetime = ctrNot_datetime;
    }

    public int getCtrNot_status() {
        return CtrNot_status;
    }

    public void setCtrNot_status(int ctrNot_status) {
        CtrNot_status = ctrNot_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CtrMsg_table_id");
        arrayList.add("CtrMsg_type");
        arrayList.add("CtrMsg_status");
        arrayList.add("CtrMsg_se_name");
        arrayList.add("CtrMsg_se_avatar");
        arrayList.add("CtrMsg_re_aid");
        arrayList.add("CtrMsg_re_avatar");
        arrayList.add("CtrMsg_content");
        arrayList.add("CtrMsg_status_flag");
        arrayList.add("CtrMsg_send_succes_flag");
        arrayList.add("CtrMsg_datetime");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
