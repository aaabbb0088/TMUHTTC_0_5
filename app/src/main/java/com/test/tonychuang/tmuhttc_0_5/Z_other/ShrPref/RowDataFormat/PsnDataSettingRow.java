package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat;

/**
 * APP使用者個人基本資料設定檔 資料格式
 * Created by TonyChuang on 2016/3/16.
 */
public class PsnDataSettingRow {
    private String pid;
    private String pwd;
    private String aid;
    private String sid;
    private String memberflag;
    private String avatar;
    private String name;
    private String nickname;
    private int sex;
    private String birthday;
    private String email;
    private String phone;
    private String gcmId;
    private String updatetime;

    public PsnDataSettingRow() {
        this.pid = null;
        this.pwd = null;
        this.aid = null;
        this.sid = null;
        this.memberflag = null;
        this.avatar = null;
        this.name = null;
        this.nickname = null;
        this.sex = 0;
        this.birthday = null;
        this.email = null;
        this.phone = null;
        this.gcmId = null;
        this.updatetime = null;
    }

    public PsnDataSettingRow(String pid, String pwd, String aid, String sid,
                             String memberflag, String avatar, String name,
                             String nickname, int sex, String birthday,
                             String email, String phone, String gcmId, String updatetime) {
        this.pid = pid;
        this.pwd = pwd;
        this.aid = aid;
        this.sid = sid;
        this.memberflag = memberflag;
        this.avatar = avatar;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.gcmId = gcmId;
        this.updatetime = updatetime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMemberflag() {
        return memberflag;
    }

    public void setMemberflag(String memberflag) {
        this.memberflag = memberflag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGcmId() {
        return gcmId;
    }

    public void setGcmId(String gcmId) {
        this.gcmId = gcmId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
