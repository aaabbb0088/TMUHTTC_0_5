package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者個人資料分享好友設定表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("FriendShareSettingTable")
public class FShrSetRow extends BaseModel {
    public static final String FSHRSET_FRI_AID = "FShrSet_fri_aid";
    public static final String FSHRSET_DATA_FLAG = "FShrSet_data_flag";
    public static final String FSHRSET_MEDICINE_FLAG = "FShrSet_medicine_flag";
    public static final String FSHRSET_PAY_FLAG = "FShrSet_pay_flag";
    public static final String FSHRSET_REPORT_FLAG = "FShrSet_report_flag";
    public static final String FSHRSET_RECORD_FLAG = "FShrSet_record_flag";
    public static final String FSHRSET_LOCATION_FLAG = "FShrSet_location_flag";

    @NotNull
    private String FShrSet_fri_aid;
    @NotNull
    @Default("N")
    private String FShrSet_data_flag;
    @NotNull
    @Default("N")
    private String FShrSet_medicine_flag;
    @NotNull
    @Default("N")
    private String FShrSet_pay_flag;
    @NotNull
    @Default("N")
    private String FShrSet_report_flag;
    @NotNull
    @Default("N")
    private String FShrSet_record_flag;
    @NotNull
    @Default("Y")
    private String FShrSet_location_flag;

    public FShrSetRow() {
        this.FShrSet_fri_aid = null;
        this.FShrSet_data_flag = null;
        this.FShrSet_medicine_flag = null;
        this.FShrSet_pay_flag = null;
        this.FShrSet_report_flag = null;
        this.FShrSet_record_flag = null;
        this.FShrSet_location_flag = null;
    }

    public FShrSetRow(String FShrSet_fri_aid, String FShrSet_data_flag, String FShrSet_medicine_flag,
                      String FShrSet_pay_flag, String FShrSet_report_flag,
                      String FShrSet_record_flag, String FShrSet_location_flag) {
        this.FShrSet_fri_aid = FShrSet_fri_aid;
        this.FShrSet_data_flag = FShrSet_data_flag;
        this.FShrSet_medicine_flag = FShrSet_medicine_flag;
        this.FShrSet_pay_flag = FShrSet_pay_flag;
        this.FShrSet_report_flag = FShrSet_report_flag;
        this.FShrSet_record_flag = FShrSet_record_flag;
        this.FShrSet_location_flag = FShrSet_location_flag;
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

    public String getFShrSet_medicine_flag() {
        return FShrSet_medicine_flag;
    }

    public void setFShrSet_medicine_flag(String FShrSet_medicine_flag) {
        this.FShrSet_medicine_flag = FShrSet_medicine_flag;
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

    public String getFShrSet_location_flag() {
        return FShrSet_location_flag;
    }

    public void setFShrSet_location_flag(String FShrSet_location_flag) {
        this.FShrSet_location_flag = FShrSet_location_flag;
    }

    public ArrayList<String> getColumnNameAry(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FShrSet_fri_aid");
        arrayList.add("FShrSet_data_flag");
        arrayList.add("FShrSet_medicine_flag");
        arrayList.add("FShrSet_pay_flag");
        arrayList.add("FShrSet_report_flag");
        arrayList.add("FShrSet_record_flag");
        arrayList.add("FShrSet_location_flag");
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
