package com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat;

/**
 * APP使用者個人設定檔 資料格式
 * Created by TonyChuang on 2016/3/16.
 */
public class PsnSettingRow {
    private String data_not_flag;
    private String medine_not_flag;
    private String pay_not_flag;
    private String report_not_flag;
    private String record_not_flag;
    private String center_not_flag;
    private String center_msg_flag;
    private String location_flag;
    private String updatetime;

    public PsnSettingRow() {
        this.data_not_flag = null;
        this.medine_not_flag = null;
        this.pay_not_flag = null;
        this.report_not_flag = null;
        this.record_not_flag = null;
        this.center_not_flag = null;
        this.center_msg_flag = null;
        this.location_flag = null;
        this.updatetime = null;
    }

    public PsnSettingRow(String data_not_flag, String medine_not_flag, String pay_not_flag,
                         String report_not_flag, String record_not_flag, String center_not_flag,
                         String center_msg_flag, String location_flag, String updatetime) {
        this.data_not_flag = data_not_flag;
        this.medine_not_flag = medine_not_flag;
        this.pay_not_flag = pay_not_flag;
        this.report_not_flag = report_not_flag;
        this.record_not_flag = record_not_flag;
        this.center_not_flag = center_not_flag;
        this.center_msg_flag = center_msg_flag;
        this.location_flag = location_flag;
        this.updatetime = updatetime;
    }

    public String getData_not_flag() {
        return data_not_flag;
    }

    public void setData_not_flag(String data_not_flag) {
        this.data_not_flag = data_not_flag;
    }

    public String getMedine_not_flag() {
        return medine_not_flag;
    }

    public void setMedine_not_flag(String medine_not_flag) {
        this.medine_not_flag = medine_not_flag;
    }

    public String getPay_not_flag() {
        return pay_not_flag;
    }

    public void setPay_not_flag(String pay_not_flag) {
        this.pay_not_flag = pay_not_flag;
    }

    public String getReport_not_flag() {
        return report_not_flag;
    }

    public void setReport_not_flag(String report_not_flag) {
        this.report_not_flag = report_not_flag;
    }

    public String getRecord_not_flag() {
        return record_not_flag;
    }

    public void setRecord_not_flag(String record_not_flag) {
        this.record_not_flag = record_not_flag;
    }

    public String getCenter_not_flag() {
        return center_not_flag;
    }

    public void setCenter_not_flag(String center_not_flag) {
        this.center_not_flag = center_not_flag;
    }

    public String getCenter_msg_flag() {
        return center_msg_flag;
    }

    public void setCenter_msg_flag(String center_msg_flag) {
        this.center_msg_flag = center_msg_flag;
    }

    public String getLocation_flag() {
        return location_flag;
    }

    public void setLocation_flag(String location_flag) {
        this.location_flag = location_flag;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
