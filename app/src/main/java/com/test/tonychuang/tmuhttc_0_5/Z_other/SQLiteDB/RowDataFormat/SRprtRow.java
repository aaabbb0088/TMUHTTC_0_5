package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * 個人健康報告書表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("ServiceReportTable")
public class SRprtRow extends BaseModel {
    public static final String SRPRT_SID = "SRprt_sid";
    public static final String SRPRT_TABLE_ID = "SRprt_table_id";
    public static final String SRPRT_DATETIME = "SRprt_datetime";
    public static final String SRPRT_URL = "SRprt_URL";
    public static final String SRPRT_PATH = "SRprt_path"; //此欄位SQL沒有，下載更新資料paser時，自訂填入檔案儲存路徑

    @NotNull
    private String SRprt_sid;
    @NotNull
    private long SRprt_table_id;
    @NotNull
    private String SRprt_datetime;
    @NotNull
    private String SRprt_URL;
    @NotNull
    private String SRprt_path; //此欄位SQL沒有，下載更新資料paser時，自訂填入檔案儲存路徑

    public SRprtRow() {
    }

    public SRprtRow(String SRprt_sid, long SRprt_table_id, String SRprt_datetime, String SRprt_URL, String SRprt_path) {
        this.SRprt_sid = SRprt_sid;
        this.SRprt_table_id = SRprt_table_id;
        this.SRprt_datetime = SRprt_datetime;
        this.SRprt_URL = SRprt_URL;
        this.SRprt_path = SRprt_path;
    }

    public String getSRprt_sid() {
        return SRprt_sid;
    }

    public void setSRprt_sid(String SRprt_sid) {
        this.SRprt_sid = SRprt_sid;
    }

    public long getSRprt_table_id() {
        return SRprt_table_id;
    }

    public void setSRprt_table_id(long SRprt_table_id) {
        this.SRprt_table_id = SRprt_table_id;
    }

    public String getSRprt_datetime() {
        return SRprt_datetime;
    }

    public void setSRprt_datetime(String SRprt_datetime) {
        this.SRprt_datetime = SRprt_datetime;
    }

    public String getSRprt_URL() {
        return SRprt_URL;
    }

    public void setSRprt_URL(String SRprt_URL) {
        this.SRprt_URL = SRprt_URL;
    }

    public String getSRprt_path() {
        return SRprt_path;
    }

    public void setSRprt_path(String SRprt_path) {
        this.SRprt_path = SRprt_path;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("SRprt_sid");
        arrayList.add("SRprt_table_id");
        arrayList.add("SRprt_datetime");
        arrayList.add("SRprt_URL");
        arrayList.add("SRprt_path");
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
