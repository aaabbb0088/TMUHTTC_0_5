package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting;

/**
 * Created by TonyChuang on 2016/4/15.
 */
public class FriShrSetting {
    private String nickName;
    private String shrData;
    private String shrMed;
    private String shrPay;
    private String shrRprt;
    private String shrRcrd;
    private String shrLct;

    public FriShrSetting() {
    }

    public FriShrSetting(String nickName, String shrData, String shrMed, String shrPay, String shrRprt, String shrRcrd, String shrLct) {
        this.nickName = nickName;
        this.shrData = shrData;
        this.shrMed = shrMed;
        this.shrPay = shrPay;
        this.shrRprt = shrRprt;
        this.shrRcrd = shrRcrd;
        this.shrLct = shrLct;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getShrData() {
        return shrData;
    }

    public void setShrData(String shrData) {
        this.shrData = shrData;
    }

    public String getShrMed() {
        return shrMed;
    }

    public void setShrMed(String shrMed) {
        this.shrMed = shrMed;
    }

    public String getShrPay() {
        return shrPay;
    }

    public void setShrPay(String shrPay) {
        this.shrPay = shrPay;
    }

    public String getShrRprt() {
        return shrRprt;
    }

    public void setShrRprt(String shrRprt) {
        this.shrRprt = shrRprt;
    }

    public String getShrRcrd() {
        return shrRcrd;
    }

    public void setShrRcrd(String shrRcrd) {
        this.shrRcrd = shrRcrd;
    }

    public String getShrLct() {
        return shrLct;
    }

    public void setShrLct(String shrLct) {
        this.shrLct = shrLct;
    }
}
