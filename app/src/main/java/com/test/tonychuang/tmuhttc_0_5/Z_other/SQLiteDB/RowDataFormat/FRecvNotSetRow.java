package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者好友訊息接受設定表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FRecvNotSetRow {
    private String FRecvNotSet_fri_aid;
    private String FRecvNotSet_data_flag;
    private String FRecvNotSet_medine_flag;
    private String FRecvNotSet_pay_flag;
    private String FRecvNotSet_report_flag;
    private String FRecvNotSet_record_flag;

    public FRecvNotSetRow() {
        this.FRecvNotSet_fri_aid = null;
        this.FRecvNotSet_data_flag = null;
        this.FRecvNotSet_medine_flag = null;
        this.FRecvNotSet_pay_flag = null;
        this.FRecvNotSet_report_flag = null;
        this.FRecvNotSet_record_flag = null;
    }

    public FRecvNotSetRow(String FRecvNotSet_fri_aid, String FRecvNotSet_data_flag,
                          String FRecvNotSet_medine_flag, String FRecvNotSet_pay_flag,
                          String FRecvNotSet_report_flag, String FRecvNotSet_record_flag) {
        this.FRecvNotSet_fri_aid = FRecvNotSet_fri_aid;
        this.FRecvNotSet_data_flag = FRecvNotSet_data_flag;
        this.FRecvNotSet_medine_flag = FRecvNotSet_medine_flag;
        this.FRecvNotSet_pay_flag = FRecvNotSet_pay_flag;
        this.FRecvNotSet_report_flag = FRecvNotSet_report_flag;
        this.FRecvNotSet_record_flag = FRecvNotSet_record_flag;
    }

    public String getFRecvNotSet_fri_aid() {
        return FRecvNotSet_fri_aid;
    }

    public void setFRecvNotSet_fri_aid(String FRecvNotSet_fri_aid) {
        this.FRecvNotSet_fri_aid = FRecvNotSet_fri_aid;
    }

    public String getFRecvNotSet_data_flag() {
        return FRecvNotSet_data_flag;
    }

    public void setFRecvNotSet_data_flag(String FRecvNotSet_data_flag) {
        this.FRecvNotSet_data_flag = FRecvNotSet_data_flag;
    }

    public String getFRecvNotSet_medine_flag() {
        return FRecvNotSet_medine_flag;
    }

    public void setFRecvNotSet_medine_flag(String FRecvNotSet_medine_flag) {
        this.FRecvNotSet_medine_flag = FRecvNotSet_medine_flag;
    }

    public String getFRecvNotSet_pay_flag() {
        return FRecvNotSet_pay_flag;
    }

    public void setFRecvNotSet_pay_flag(String FRecvNotSet_pay_flag) {
        this.FRecvNotSet_pay_flag = FRecvNotSet_pay_flag;
    }

    public String getFRecvNotSet_report_flag() {
        return FRecvNotSet_report_flag;
    }

    public void setFRecvNotSet_report_flag(String FRecvNotSet_report_flag) {
        this.FRecvNotSet_report_flag = FRecvNotSet_report_flag;
    }

    public String getFRecvNotSet_record_flag() {
        return FRecvNotSet_record_flag;
    }

    public void setFRecvNotSet_record_flag(String FRecvNotSet_record_flag) {
        this.FRecvNotSet_record_flag = FRecvNotSet_record_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FRecvNotSet_fri_aid");
        arrayList.add("FRecvNotSet_data_flag");
        arrayList.add("FRecvNotSet_medine_flag");
        arrayList.add("FRecvNotSet_pay_flag");
        arrayList.add("FRecvNotSet_report_flag");
        arrayList.add("FRecvNotSet_record_flag");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
