package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者好友關係表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("FriendTable")
public class FRow extends BaseModel {
    public static final String F_TABLE_ID = "F_table_id";
    public static final String F_FRI_AID = "F_fri_aid";
    public static final String F_FRI_SID = "F_fri_sid";
    public static final String F_FRI_PID = "F_fri_pid";
    public static final String F_RELATION_FLAG = "F_relation_flag";
    public static final String F_RELATION_FLAG_FRI = "F_relation_flag_fri";
    public static final String F_ACTIVE_DATETIME = "F_active_datetime";
    public static final String F_MEMBER_FLAG = "F_member_flag";
    public static final String F_AVATAR = "F_avatar";
    public static final String F_NAME = "F_name";
    public static final String F_NICKNAME = "F_nickname";
    public static final String F_NICKNAME_FLAG = "F_nickname_flag";
    public static final String F_SEX = "F_sex";
    public static final String F_BIRTHDAY = "F_birthday";
    public static final String F_PHONE = "F_phone";
    public static final String F_EMAIL = "F_email";

    @NotNull
    private long F_table_id;
    @NotNull
    private String F_fri_aid;
    @NotNull
    private String F_fri_sid;
    @NotNull
    private String F_fri_pid;
    @NotNull
    private int F_relation_flag;
    @NotNull
    private int F_relation_flag_fri;
    @NotNull
    private String F_active_datetime;
    @NotNull
    private String F_member_flag;
    @NotNull
    private String F_avatar;
    @NotNull
    private String F_name;
    @NotNull
    private String F_nickname;
    @NotNull
    private String F_nickname_flag;
    @NotNull
    private int F_sex;
    @NotNull
    private String F_birthday;
    @NotNull
    private String F_phone;
    @NotNull
    private String F_email;

    public FRow() {
    }

    public FRow(long f_table_id, String f_fri_aid, String f_fri_sid, String f_fri_pid,int f_relation_flag,
                int f_relation_flag_fri, String f_active_datetime, String f_member_flag, String f_avatar,
                String f_name, String f_nickname, String f_nickname_flag,
                int f_sex, String f_birthday, String f_phone, String f_email) {
        F_table_id = f_table_id;
        F_fri_aid = f_fri_aid;
        F_fri_sid = f_fri_sid;
        F_fri_pid = f_fri_pid;
        F_relation_flag = f_relation_flag;
        F_relation_flag_fri = f_relation_flag_fri;
        F_active_datetime = f_active_datetime;
        F_member_flag = f_member_flag;
        F_avatar = f_avatar;
        F_name = f_name;
        F_nickname = f_nickname;
        F_nickname_flag = f_nickname_flag;
        F_sex = f_sex;
        F_birthday = f_birthday;
        F_phone = f_phone;
        F_email = f_email;
    }

    public long getF_table_id() {
        return F_table_id;
    }

    public void setF_table_id(long f_table_id) {
        F_table_id = f_table_id;
    }

    public String getF_fri_aid() {
        return F_fri_aid;
    }

    public void setF_fri_aid(String f_fri_aid) {
        F_fri_aid = f_fri_aid;
    }

    public String getF_fri_sid() {
        return F_fri_sid;
    }

    public void setF_fri_sid(String f_fri_sid) {
        F_fri_sid = f_fri_sid;
    }

    public String getF_fri_pid() {
        return F_fri_pid;
    }

    public void setF_fri_pid(String f_fri_pid) {
        F_fri_pid = f_fri_pid;
    }

    public int getF_relation_flag() {
        return F_relation_flag;
    }

    public void setF_relation_flag(int f_relation_flag) {
        F_relation_flag = f_relation_flag;
    }

    public int getF_relation_flag_fri() {
        return F_relation_flag_fri;
    }

    public void setF_relation_flag_fri(int f_relation_flag_fri) {
        F_relation_flag_fri = f_relation_flag_fri;
    }

    public String getF_active_datetime() {
        return F_active_datetime;
    }

    public void setF_active_datetime(String f_active_datetime) {
        F_active_datetime = f_active_datetime;
    }

    public String getF_member_flag() {
        return F_member_flag;
    }

    public void setF_member_flag(String f_member_flag) {
        F_member_flag = f_member_flag;
    }

    public String getF_avatar() {
        return F_avatar;
    }

    public void setF_avatar(String f_avatar) {
        F_avatar = f_avatar;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public String getF_nickname() {
        return F_nickname;
    }

    public void setF_nickname(String f_nickname) {
        F_nickname = f_nickname;
    }

    public String getF_nickname_flag() {
        return F_nickname_flag;
    }

    public void setF_nickname_flag(String f_nickname_flag) {
        F_nickname_flag = f_nickname_flag;
    }

    public int getF_sex() {
        return F_sex;
    }

    public void setF_sex(int f_sex) {
        F_sex = f_sex;
    }

    public String getF_birthday() {
        return F_birthday;
    }

    public void setF_birthday(String f_birthday) {
        F_birthday = f_birthday;
    }

    public String getF_phone() {
        return F_phone;
    }

    public void setF_phone(String f_phone) {
        F_phone = f_phone;
    }

    public String getF_email() {
        return F_email;
    }

    public void setF_email(String f_email) {
        F_email = f_email;
    }

    public ArrayList<String> getColumnNameAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("F_table_id");
        arrayList.add("F_fri_aid");
        arrayList.add("F_fri_sid");
        arrayList.add("F_fri_pid");
        arrayList.add("F_relation_flag");
        arrayList.add("F_relation_flag_fri");
        arrayList.add("F_active_datetime");
        arrayList.add("F_member_flag");
        arrayList.add("F_avatar");
        arrayList.add("F_name");
        arrayList.add("F_nickname");
        arrayList.add("F_nickname_flag");
        arrayList.add("F_sex");
        arrayList.add("F_birthday");
        arrayList.add("F_phone");
        arrayList.add("F_email");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
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
        arrayList.add("TEXT");
        return arrayList;
    }
}
