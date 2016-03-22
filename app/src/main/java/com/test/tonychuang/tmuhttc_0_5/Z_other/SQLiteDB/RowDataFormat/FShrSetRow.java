package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者個人資料分享好友設定表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FShrSetRow {
    private String FShrSet_fri_aid;
    private String FShrSet_data_flag;
    private String FShrSet_medine_flag;
    private String FShrSet_pay_flag;
    private String FShrSet_report_flag;
    private String FShrSet_record_flag;
    private String FShrSet_loction_flag;

    public FShrSetRow() {
        this.FShrSet_fri_aid = null;
        this.FShrSet_data_flag = null;
        this.FShrSet_medine_flag = null;
        this.FShrSet_pay_flag = null;
        this.FShrSet_report_flag = null;
        this.FShrSet_record_flag = null;
        this.FShrSet_loction_flag = null;
    }

    public FShrSetRow(String FShrSet_fri_aid, String FShrSet_data_flag, String FShrSet_medine_flag,
                      String FShrSet_pay_flag, String FShrSet_report_flag,
                      String FShrSet_record_flag, String FShrSet_loction_flag) {
        this.FShrSet_fri_aid = FShrSet_fri_aid;
        this.FShrSet_data_flag = FShrSet_data_flag;
        this.FShrSet_medine_flag = FShrSet_medine_flag;
        this.FShrSet_pay_flag = FShrSet_pay_flag;
        this.FShrSet_report_flag = FShrSet_report_flag;
        this.FShrSet_record_flag = FShrSet_record_flag;
        this.FShrSet_loction_flag = FShrSet_loction_flag;
    }

    public String getFShrSet_fri_aid() {
        return FShrSet_fri_aid;
    }

    public void setFShrSet_fri_aid(String FShrSet_fri_aid) {
        this.FShrSet_fri_aid = FShrSet_fri_aid;
    }

    public String getFShrSet_data_flag() {
        return FShrSet_data_flag;
    }

    public void setFShrSet_data_flag(String FShrSet_data_flag) {
        this.FShrSet_data_flag = FShrSet_data_flag;
    }

    public String getFShrSet_medine_flag() {
        return FShrSet_medine_flag;
    }

    public void setFShrSet_medine_flag(String FShrSet_medine_flag) {
        this.FShrSet_medine_flag = FShrSet_medine_flag;
    }

    public String getFShrSet_pay_flag() {
        return FShrSet_pay_flag;
    }

    public void setFShrSet_pay_flag(String FShrSet_pay_flag) {
        this.FShrSet_pay_flag = FShrSet_pay_flag;
    }

    public String getFShrSet_report_flag() {
        return FShrSet_report_flag;
    }

    public void setFShrSet_report_flag(String FShrSet_report_flag) {
        this.FShrSet_report_flag = FShrSet_report_flag;
    }

    public String getFShrSet_record_flag() {
        return FShrSet_record_flag;
    }

    public void setFShrSet_record_flag(String FShrSet_record_flag) {
        this.FShrSet_record_flag = FShrSet_record_flag;
    }

    public String getFShrSet_loction_flag() {
        return FShrSet_loction_flag;
    }

    public void setFShrSet_loction_flag(String FShrSet_loction_flag) {
        this.FShrSet_loction_flag = FShrSet_loction_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FShrSet_fri_aid");
        arrayList.add("FShrSet_data_flag");
        arrayList.add("FShrSet_medine_flag");
        arrayList.add("FShrSet_pay_flag");
        arrayList.add("FShrSet_report_flag");
        arrayList.add("FShrSet_record_flag");
        arrayList.add("FShrSet_loction_flag");
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
        arrayList.add("TEXT");
        return arrayList;
    }
}
