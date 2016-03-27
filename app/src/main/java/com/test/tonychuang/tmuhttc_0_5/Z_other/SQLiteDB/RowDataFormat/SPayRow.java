package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人繳費紀錄表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("ServicePayTable")
public class SPayRow extends BaseModel {
    public static final String SPAY_SID = "SPay_sid";
    public static final String SPAY_TABLE_ID = "SPay_table_id";
    public static final String SPAY_DATETIME = "SPay_datetime";
    public static final String SPAY_MONEY = "SPay_money";
    public static final String SPAY_STATUS = "SPay_status";

    @NotNull
    private String SPay_sid;
    @NotNull
    private long SPay_table_id;
    @NotNull
    private String SPay_datetime;
    @NotNull
    private String SPay_money;
    @NotNull
    private String SPay_status;

    public SPayRow() {
    }

    public SPayRow(String SPay_sid, long SPay_table_id, String SPay_datetime, String SPay_money, String SPay_status) {
        this.SPay_sid = SPay_sid;
        this.SPay_table_id = SPay_table_id;
        this.SPay_datetime = SPay_datetime;
        this.SPay_money = SPay_money;
        this.SPay_status = SPay_status;
    }

    public String getSPay_sid() {
        return SPay_sid;
    }

    public void setSPay_sid(String SPay_sid) {
        this.SPay_sid = SPay_sid;
    }

    public long getSPay_table_id() {
        return SPay_table_id;
    }

    public void setSPay_table_id(long SPay_table_id) {
        this.SPay_table_id = SPay_table_id;
    }

    public String getSPay_datetime() {
        return SPay_datetime;
    }

    public void setSPay_datetime(String SPay_datetime) {
        this.SPay_datetime = SPay_datetime;
    }

    public String getSPay_money() {
        return SPay_money;
    }

    public void setSPay_money(String SPay_money) {
        this.SPay_money = SPay_money;
    }

    public String getSPay_status() {
        return SPay_status;
    }

    public void setSPay_status(String SPay_status) {
        this.SPay_status = SPay_status;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SPay_sid");
        arrayList.add("SPay_table_id");
        arrayList.add("SPay_datetime");
        arrayList.add("SPay_money");
        arrayList.add("SPay_status");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("INTEGER");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
