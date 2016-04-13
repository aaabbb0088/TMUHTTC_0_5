package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者好友邀請訊息表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("FriendAddNoticeTable")
public class FAddNotRow extends BaseModel {
    public static final String FADDNOT_TABLE_ID = "FAddNot_table_id";
    public static final String FADDNOT_SEND_AID = "FAddNot_send_aid";
    public static final String FADDNOT_SEND_PID = "FAddNot_send_pid";
    public static final String FADDNOT_SEND_NAME = "FAddNot_send_name";
    public static final String FADDNOT_SEND_AVATAR = "FAddNot_send_avatar";
    public static final String FADDNOT_RECV_AID = "FAddNot_recv_aid";
    public static final String FADDNOT_RECV_PID = "FAddNot_recv_pid";
    public static final String FADDNOT_RECV_NAME = "FAddNot_recv_name";
    public static final String FADDNOT_RECV_AVATAR = "FAddNot_recv_avatar";
    public static final String FADDNOT_ADD_WAY = "FAddNot_add_way";
    public static final String FADDNOT_SENDER_NAME = "FAddNot_sender_name";
    public static final String FADDNOT_DATETIME = "FAddNot_datetime";
    public static final String FADDNOT_STATUS_FLAG = "FAddNot_status_flag";

    @NotNull
    private long FAddNot_table_id;
    @NotNull
    private String FAddNot_send_aid;
    @NotNull
    private String FAddNot_send_pid;
    @NotNull
    private String FAddNot_send_avatar;
    @NotNull
    private String FAddNot_send_name;
    @NotNull
    private String FAddNot_recv_aid;
    @NotNull
    private String FAddNot_recv_pid;
    @NotNull
    private String FAddNot_recv_name;
    @NotNull
    private String FAddNot_recv_avatar;
    @NotNull
    private int FAddNot_add_way;
    @NotNull
    private String FAddNot_sender_name;
    @NotNull
    private String FAddNot_datetime;
    @NotNull
    private int FAddNot_status_flag;

    public FAddNotRow() {
    }

    public FAddNotRow(long FAddNot_table_id, String FAddNot_send_aid,String FAddNot_send_pid,
                      String FAddNot_send_name, String FAddNot_send_avatar,
                      String FAddNot_recv_aid, String FAddNot_recv_pid,
                      String FAddNot_recv_name, String FAddNot_recv_avatar, int FAddNot_add_way,
                      String FAddNot_sender_name, String FAddNot_datetime, int FAddNot_status_flag) {
        this.FAddNot_table_id = FAddNot_table_id;
        this.FAddNot_send_aid = FAddNot_send_aid;
        this.FAddNot_send_pid = FAddNot_send_pid;
        this.FAddNot_send_name = FAddNot_send_name;
        this.FAddNot_send_avatar = FAddNot_send_avatar;
        this.FAddNot_recv_aid = FAddNot_recv_aid;
        this.FAddNot_recv_pid = FAddNot_recv_pid;
        this.FAddNot_recv_name = FAddNot_recv_name;
        this.FAddNot_recv_avatar = FAddNot_recv_avatar;
        this.FAddNot_add_way = FAddNot_add_way;
        this.FAddNot_sender_name = FAddNot_sender_name;
        this.FAddNot_datetime = FAddNot_datetime;
        this.FAddNot_status_flag = FAddNot_status_flag;
    }

    public long getFAddNot_table_id() {
        return FAddNot_table_id;
    }

    public void setFAddNot_table_id(long FAddNot_table_id) {
        this.FAddNot_table_id = FAddNot_table_id;
    }

    public String getFAddNot_send_aid() {
        return FAddNot_send_aid;
    }

    public void setFAddNot_send_aid(String FAddNot_send_aid) {
        this.FAddNot_send_aid = FAddNot_send_aid;
    }

    public String getFAddNot_send_pid() {
        return FAddNot_send_pid;
    }

    public void setFAddNot_send_pid(String FAddNot_send_pid) {
        this.FAddNot_send_pid = FAddNot_send_pid;
    }

    public String getFAddNot_send_name() {
        return FAddNot_send_name;
    }

    public void setFAddNot_send_name(String FAddNot_send_name) {
        this.FAddNot_send_name = FAddNot_send_name;
    }

    public String getFAddNot_send_avatar() {
        return FAddNot_send_avatar;
    }

    public void setFAddNot_send_avatar(String FAddNot_send_avatar) {
        this.FAddNot_send_avatar = FAddNot_send_avatar;
    }

    public String getFAddNot_recv_aid() {
        return FAddNot_recv_aid;
    }

    public void setFAddNot_recv_aid(String FAddNot_recv_aid) {
        this.FAddNot_recv_aid = FAddNot_recv_aid;
    }

    public String getFAddNot_recv_pid() {
        return FAddNot_recv_pid;
    }

    public void setFAddNot_recv_pid(String FAddNot_recv_pid) {
        this.FAddNot_recv_pid = FAddNot_recv_pid;
    }

    public String getFAddNot_recv_name() {
        return FAddNot_recv_name;
    }

    public void setFAddNot_recv_name(String FAddNot_recv_name) {
        this.FAddNot_recv_name = FAddNot_recv_name;
    }

    public String getFAddNot_recv_avatar() {
        return FAddNot_recv_avatar;
    }

    public void setFAddNot_recv_avatar(String FAddNot_recv_avatar) {
        this.FAddNot_recv_avatar = FAddNot_recv_avatar;
    }

    public int getFAddNot_add_way() {
        return FAddNot_add_way;
    }

    public void setFAddNot_add_way(int FAddNot_add_way) {
        this.FAddNot_add_way = FAddNot_add_way;
    }

    public String getFAddNot_sender_name() {
        return FAddNot_sender_name;
    }

    public void setFAddNot_sender_name(String FAddNot_sender_name) {
        this.FAddNot_sender_name = FAddNot_sender_name;
    }

    public String getFAddNot_datetime() {
        return FAddNot_datetime;
    }

    public void setFAddNot_datetime(String FAddNot_datetime) {
        this.FAddNot_datetime = FAddNot_datetime;
    }

    public int getFAddNot_status_flag() {
        return FAddNot_status_flag;
    }

    public void setFAddNot_status_flag(int FAddNot_status_flag) {
        this.FAddNot_status_flag = FAddNot_status_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FAddNot_table_id");
        arrayList.add("FAddNot_send_aid");
        arrayList.add("FAddNot_send_name");
        arrayList.add("FAddNot_send_avatar");
        arrayList.add("FAddNot_recv_aid");
        arrayList.add("FAddNot_recv_name");
        arrayList.add("FAddNot_recv_avatar");
        arrayList.add("FAddNot_add_way");
        arrayList.add("FAddNot_sender_name");
        arrayList.add("FAddNot_datetime");
        arrayList.add("FAddNot_status_flag");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        return arrayList;
    }
}
