package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import java.util.ArrayList;

/**
 * APP使用者好友群組表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
public class FGRow {
    private String FG_group_name;
    private String FG_fri_aid;

    public FGRow() {
        this.FG_group_name = null;
        this.FG_fri_aid = null;
    }

    public FGRow(String FG_group_name, String FG_fri_aid) {
        this.FG_group_name = FG_group_name;
        this.FG_fri_aid = FG_fri_aid;
    }

    public String getFG_group_name() {
        return FG_group_name;
    }

    public void setFG_group_name(String FG_group_name) {
        this.FG_group_name = FG_group_name;
    }

    public String getFG_fri_aid() {
        return FG_fri_aid;
    }

    public void setFG_fri_aid(String FG_fri_aid) {
        this.FG_fri_aid = FG_fri_aid;
    }

    public ArrayList<String> getColumnNameAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FG_group_name");
        arrayList.add("FG_fri_aid");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
