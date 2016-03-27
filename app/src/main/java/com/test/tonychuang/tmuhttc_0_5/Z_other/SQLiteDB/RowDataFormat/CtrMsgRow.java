package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者中心留言板表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("CenterMessageTable")
public class CtrMsgRow extends BaseModel {
    public static final String CTRMSG_TABLE_ID = "CtrMsg_table_id";
    public static final String CTRMSG_TYPE = "CtrMsg_type";
    public static final String CTRMSG_STATUS = "CtrMsg_status";
    public static final String CTRMSG_CONTENT = "CtrMsg_content";
    public static final String CTRMSG_STATUS_FLAG = "CtrMsg_status_flag";
    public static final String CTRMSG_SEND_SUCCES_FLAG = "CtrMsg_send_succes_flag";
    public static final String CTRMSG_DATETIME = "CtrMsg_datetime";

    @NotNull
    private long CtrMsg_table_id;
    @NotNull
    private int CtrMsg_type;
    @NotNull
    private int CtrMsg_status;
//    @NotNull
//    private String CtrMsg_se_name;
//    @NotNull
//    private String CtrMsg_se_avatar;
//    @NotNull
//    private String CtrMsg_re_aid;
//    @NotNull
//    private String CtrMsg_re_avatar;
    @NotNull
    private String CtrMsg_content;
    @NotNull
    private int CtrMsg_status_flag;
    @NotNull
    private String CtrMsg_send_succes_flag;
    @NotNull
    private String CtrMsg_datetime;

    public CtrMsgRow() {
    }

    public CtrMsgRow(long ctrMsg_table_id, int ctrMsg_type, int ctrMsg_status,
//                     String ctrMsg_se_name, String ctrMsg_se_avatar,
//                     String ctrMsg_re_aid, String ctrMsg_re_avatar,
                     String ctrMsg_content, int ctrMsg_status_flag,
                     String ctrMsg_send_succes_flag, String ctrMsg_datetime) {
        CtrMsg_table_id = ctrMsg_table_id;
        CtrMsg_type = ctrMsg_type;
        CtrMsg_status = ctrMsg_status;
//        CtrMsg_se_name = ctrMsg_se_name;
//        CtrMsg_se_avatar = ctrMsg_se_avatar;
//        CtrMsg_re_aid = ctrMsg_re_aid;
//        CtrMsg_re_avatar = ctrMsg_re_avatar;
        CtrMsg_content = ctrMsg_content;
        CtrMsg_status_flag = ctrMsg_status_flag;
        CtrMsg_send_succes_flag = ctrMsg_send_succes_flag;
        CtrMsg_datetime = ctrMsg_datetime;
    }

    public long getCtrMsg_table_id() {
        return CtrMsg_table_id;
    }

    public void setCtrMsg_table_id(long ctrMsg_table_id) {
        CtrMsg_table_id = ctrMsg_table_id;
    }

    public int getCtrMsg_type() {
        return CtrMsg_type;
    }

    public void setCtrMsg_type(int ctrMsg_type) {
        CtrMsg_type = ctrMsg_type;
    }

    public int getCtrMsg_status() {
        return CtrMsg_status;
    }

    public void setCtrMsg_status(int ctrMsg_status) {
        CtrMsg_status = ctrMsg_status;
    }

//    public String getCtrMsg_se_name() {
//        return CtrMsg_se_name;
//    }
//
//    public void setCtrMsg_se_name(String ctrMsg_se_name) {
//        CtrMsg_se_name = ctrMsg_se_name;
//    }
//
//    public String getCtrMsg_se_avatar() {
//        return CtrMsg_se_avatar;
//    }
//
//    public void setCtrMsg_se_avatar(String ctrMsg_se_avatar) {
//        CtrMsg_se_avatar = ctrMsg_se_avatar;
//    }
//
//    public String getCtrMsg_re_aid() {
//        return CtrMsg_re_aid;
//    }
//
//    public void setCtrMsg_re_aid(String ctrMsg_re_aid) {
//        CtrMsg_re_aid = ctrMsg_re_aid;
//    }
//
//    public String getCtrMsg_re_avatar() {
//        return CtrMsg_re_avatar;
//    }
//
//    public void setCtrMsg_re_avatar(String ctrMsg_re_avatar) {
//        CtrMsg_re_avatar = ctrMsg_re_avatar;
//    }

    public String getCtrMsg_content() {
        return CtrMsg_content;
    }

    public void setCtrMsg_content(String ctrMsg_content) {
        CtrMsg_content = ctrMsg_content;
    }

    public int getCtrMsg_status_flag() {
        return CtrMsg_status_flag;
    }

    public void setCtrMsg_status_flag(int ctrMsg_status_flag) {
        CtrMsg_status_flag = ctrMsg_status_flag;
    }

    public String getCtrMsg_send_succes_flag() {
        return CtrMsg_send_succes_flag;
    }

    public void setCtrMsg_send_succes_flag(String ctrMsg_send_succes_flag) {
        CtrMsg_send_succes_flag = ctrMsg_send_succes_flag;
    }

    public String getCtrMsg_datetime() {
        return CtrMsg_datetime;
    }

    public void setCtrMsg_datetime(String ctrMsg_datetime) {
        CtrMsg_datetime = ctrMsg_datetime;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CtrMsg_table_id");
        arrayList.add("CtrMsg_type");
        arrayList.add("CtrMsg_status");
//        arrayList.add("CtrMsg_se_name");
//        arrayList.add("CtrMsg_se_avatar");
//        arrayList.add("CtrMsg_re_aid");
//        arrayList.add("CtrMsg_re_avatar");
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
//        arrayList.add("TEXT");
//        arrayList.add("TEXT");
//        arrayList.add("TEXT");
//        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
