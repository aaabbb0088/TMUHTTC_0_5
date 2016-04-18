package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by TonyChuang on 2016/3/29.
 */
@Table("FriendShareDataFlagTable")
public class FShrDataFlagRow extends BaseModel  {
    public static final String FSHRDATA_FRI_AID = "FShrData_fri_aid";
    public static final String FSHRDATA_DATA_FLAG = "FShrData_data_flag";
    public static final String FSHRDATA_MEDICINE_FLAG = "FShrData_medicine_flag";
    public static final String FSHRDATA_PAY_FLAG = "FShrData_pay_flag";
    public static final String FSHRDATA_REPORT_FLAG = "FShrData_report_flag";
    public static final String FSHRDATA_RECORD_FLAG = "FShrData_record_flag";
    public static final String FSHRDATA_LOCATION_FLAG = "FShrData_location_flag";

    @NotNull
    private String FShrData_fri_aid;
    @NotNull
    @Default("Y")
    private String FShrData_data_flag;
    @NotNull
    @Default("N")
    private String FShrData_medicine_flag;
    @NotNull
    @Default("N")
    private String FShrData_pay_flag;
    @NotNull
    @Default("N")
    private String FShrData_report_flag;
    @NotNull
    @Default("N")
    private String FShrData_record_flag;
    @NotNull
    @Default("Y")
    private String FShrData_location_flag;

    public FShrDataFlagRow() {
    }

    public FShrDataFlagRow(String FShrData_fri_aid, String FShrData_data_flag,
                           String FShrData_medicine_flag, String FShrData_pay_flag,
                           String FShrData_report_flag, String FShrData_record_flag,
                           String FShrData_location_flag) {
        this.FShrData_fri_aid = FShrData_fri_aid;
        this.FShrData_data_flag = FShrData_data_flag;
        this.FShrData_medicine_flag = FShrData_medicine_flag;
        this.FShrData_pay_flag = FShrData_pay_flag;
        this.FShrData_report_flag = FShrData_report_flag;
        this.FShrData_record_flag = FShrData_record_flag;
        this.FShrData_location_flag = FShrData_location_flag;
    }

    public String getFShrData_fri_aid() {
        return FShrData_fri_aid;
    }

    public void setFShrData_fri_aid(String FShrData_fri_aid) {
        this.FShrData_fri_aid = FShrData_fri_aid;
    }

    public String getFShrData_data_flag() {
        return FShrData_data_flag;
    }

    public void setFShrData_data_flag(String FShrData_data_flag) {
        this.FShrData_data_flag = FShrData_data_flag;
    }

    public String getFShrData_medicine_flag() {
        return FShrData_medicine_flag;
    }

    public void setFShrData_medicine_flag(String FShrData_medicine_flag) {
        this.FShrData_medicine_flag = FShrData_medicine_flag;
    }

    public String getFShrData_pay_flag() {
        return FShrData_pay_flag;
    }

    public void setFShrData_pay_flag(String FShrData_pay_flag) {
        this.FShrData_pay_flag = FShrData_pay_flag;
    }

    public String getFShrData_report_flag() {
        return FShrData_report_flag;
    }

    public void setFShrData_report_flag(String FShrData_report_flag) {
        this.FShrData_report_flag = FShrData_report_flag;
    }

    public String getFShrData_record_flag() {
        return FShrData_record_flag;
    }

    public void setFShrData_record_flag(String FShrData_record_flag) {
        this.FShrData_record_flag = FShrData_record_flag;
    }

    public String getFShrData_location_flag() {
        return FShrData_location_flag;
    }

    public void setFShrData_location_flag(String FShrData_location_flag) {
        this.FShrData_location_flag = FShrData_location_flag;
    }
}
