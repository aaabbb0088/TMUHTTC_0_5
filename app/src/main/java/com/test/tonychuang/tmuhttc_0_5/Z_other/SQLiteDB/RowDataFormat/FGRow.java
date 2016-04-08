package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.ArrayList;

/**
 * APP使用者好友群組表 資料格式
 * Created by TonyChuang on 2016/3/17.
 */
@Table("FriendGroupTable")
public class FGRow extends BaseModel {
    public static final String FG_GROUP_NAME = "FG_group_name";
    public static final String FG_FRI_AIDS = "FG_fri_aids";

    @NotNull
    private String FG_group_name;
    @NotNull
    private String FG_fri_aids;

    public FGRow() {
        this.FG_group_name = null;
        this.FG_fri_aids = null;
    }

    public FGRow(String FG_group_name, String FG_fri_aids) {
        this.FG_group_name = FG_group_name;
        this.FG_fri_aids = FG_fri_aids;
    }

    public String getFG_group_name() {
        return FG_group_name;
    }

    public void setFG_group_name(String FG_group_name) {
        this.FG_group_name = FG_group_name;
    }

    public String getFG_fri_aids() {
        return FG_fri_aids;
    }

    public void setFG_fri_aids(String FG_fri_aids) {
        this.FG_fri_aids = FG_fri_aids;
    }

    public ArrayList<String> getColumnNameAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FG_group_name");
        arrayList.add("FG_fri_aids");
        return arrayList;
    }

    public ArrayList<String> getColumnTypeAry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("TEXT");
        arrayList.add("TEXT");
        return arrayList;
    }
}
