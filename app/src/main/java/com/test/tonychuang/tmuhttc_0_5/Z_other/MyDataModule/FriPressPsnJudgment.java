package com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule;

import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;

/**
 * Created by TonyChuang on 2016/4/15.
 */
public class FriPressPsnJudgment {
    private int BP_SY_Max;
    private int BP_SY_Min;
    private int BP_DI_Max;
    private int BP_DI_Min;
    private int BP_SY_MaxDang;
    private int BP_SY_MinDang;
    private int BP_DI_MaxDang;
    private int BP_DI_MinDang;
    private int sysValue;
    private int diaValue;
    private String[] result;

    public FriPressPsnJudgment() {
    }

    public FriPressPsnJudgment(FWLevelRow fwLevelRow, int sysValue, int diaValue) {
        result = new String[]{"normal", "warn", "dang"};
        this.BP_SY_Max = fwLevelRow.getBP_SY_Max();
        this.BP_SY_Min = fwLevelRow.getBP_SY_Min();
        this.BP_DI_Max = fwLevelRow.getBP_DI_Max();
        this.BP_DI_Min = fwLevelRow.getBP_DI_Min();
        this.BP_SY_MaxDang = fwLevelRow.getBP_SY_MaxDang();
        this.BP_SY_MinDang = fwLevelRow.getBP_SY_MinDang();
        this.BP_DI_MaxDang = fwLevelRow.getBP_DI_MaxDang();
        this.BP_DI_MinDang = fwLevelRow.getBP_DI_MinDang();
        this.sysValue = sysValue;
        this.diaValue = diaValue;
    }

    public String getResult(){
        String resultstr;
        if (BP_SY_Min <= sysValue && sysValue <= BP_SY_Max
                && BP_DI_Min <= diaValue && diaValue <= BP_DI_Max) {
            resultstr = result[0];
        } else if (BP_SY_MaxDang < sysValue || BP_DI_MaxDang < diaValue ||
                sysValue < BP_SY_MinDang || diaValue < BP_DI_MinDang) {
            resultstr = result[2];
        } else {
            resultstr = result[1];;
        }
        return resultstr;
    }
}
