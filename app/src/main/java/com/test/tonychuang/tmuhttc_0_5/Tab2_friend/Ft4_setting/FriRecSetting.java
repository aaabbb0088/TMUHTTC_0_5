package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting;

/**
 * Created by TonyChuang on 2016/4/15.
 */
public class FriRecSetting {
    private String nickName;
    private String recData;
    private String recMed;
    private String recPay;
    private String recRprt;
    private String recRcrd;


    public FriRecSetting() {
    }

    public FriRecSetting(String nickName, String recData, String recMed, String recPay, String recRprt, String recRcrd) {
        this.nickName = nickName;
        this.recData = recData;
        this.recMed = recMed;
        this.recPay = recPay;
        this.recRprt = recRprt;
        this.recRcrd = recRcrd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRecData() {
        return recData;
    }

    public void setRecData(String recData) {
        this.recData = recData;
    }

    public String getRecMed() {
        return recMed;
    }

    public void setRecMed(String recMed) {
        this.recMed = recMed;
    }

    public String getRecPay() {
        return recPay;
    }

    public void setRecPay(String recPay) {
        this.recPay = recPay;
    }

    public String getRecRprt() {
        return recRprt;
    }

    public void setRecRprt(String recRprt) {
        this.recRprt = recRprt;
    }

    public String getRecRcrd() {
        return recRcrd;
    }

    public void setRecRcrd(String recRcrd) {
        this.recRcrd = recRcrd;
    }
}
