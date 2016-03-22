package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者個人訊息分享好友設定表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FShrNotSetRow {
    private String FShrNotSet_fri_aid;
    private String FShrNotSet_data_flag;
    private String FShrNotSet_medine_flag;
    private String FShrNotSet_pay_flag;
    private String FShrNotSet_report_flag;
    private String FShrNotSet_record_flag;

    public FShrNotSetRow() {
        this.FShrNotSet_fri_aid = null;
        this.FShrNotSet_data_flag = null;
        this.FShrNotSet_medine_flag = null;
        this.FShrNotSet_pay_flag = null;
        this.FShrNotSet_report_flag = null;
        this.FShrNotSet_record_flag = null;
    }

    public FShrNotSetRow(String FShrNotSet_fri_aid, String FShrNotSet_data_flag,
                         String FShrNotSet_medine_flag, String FShrNotSet_pay_flag,
                         String FShrNotSet_report_flag, String FShrNotSet_record_flag) {
        this.FShrNotSet_fri_aid = FShrNotSet_fri_aid;
        this.FShrNotSet_data_flag = FShrNotSet_data_flag;
        this.FShrNotSet_medine_flag = FShrNotSet_medine_flag;
        this.FShrNotSet_pay_flag = FShrNotSet_pay_flag;
        this.FShrNotSet_report_flag = FShrNotSet_report_flag;
        this.FShrNotSet_record_flag = FShrNotSet_record_flag;
    }

    public String getFShrNotSet_fri_aid() {
        return FShrNotSet_fri_aid;
    }

    public void setFShrNotSet_fri_aid(String FShrNotSet_fri_aid) {
        this.FShrNotSet_fri_aid = FShrNotSet_fri_aid;
    }

    public String getFShrNotSet_data_flag() {
        return FShrNotSet_data_flag;
    }

    public void setFShrNotSet_data_flag(String FShrNotSet_data_flag) {
        this.FShrNotSet_data_flag = FShrNotSet_data_flag;
    }

    public String getFShrNotSet_medine_flag() {
        return FShrNotSet_medine_flag;
    }

    public void setFShrNotSet_medine_flag(String FShrNotSet_medine_flag) {
        this.FShrNotSet_medine_flag = FShrNotSet_medine_flag;
    }

    public String getFShrNotSet_pay_flag() {
        return FShrNotSet_pay_flag;
    }

    public void setFShrNotSet_pay_flag(String FShrNotSet_pay_flag) {
        this.FShrNotSet_pay_flag = FShrNotSet_pay_flag;
    }

    public String getFShrNotSet_report_flag() {
        return FShrNotSet_report_flag;
    }

    public void setFShrNotSet_report_flag(String FShrNotSet_report_flag) {
        this.FShrNotSet_report_flag = FShrNotSet_report_flag;
    }

    public String getFShrNotSet_record_flag() {
        return FShrNotSet_record_flag;
    }

    public void setFShrNotSet_record_flag(String FShrNotSet_record_flag) {
        this.FShrNotSet_record_flag = FShrNotSet_record_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FShrNotSet_fri_aid");
        arrayList.add("FShrNotSet_data_flag");
        arrayList.add("FShrNotSet_medine_flag");
        arrayList.add("FShrNotSet_pay_flag");
        arrayList.add("FShrNotSet_report_flag");
        arrayList.add("FShrNotSet_record_flag");
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
