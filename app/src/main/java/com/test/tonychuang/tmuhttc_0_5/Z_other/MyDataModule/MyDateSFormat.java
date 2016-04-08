package com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/3/23.
 */
public class MyDateSFormat {

    public MyDateSFormat() {
    }

    public SimpleDateFormat getC2MFrmt_yMdahm() {
        return new SimpleDateFormat("yyyy/MM/dd a hh:mm", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_yMd() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_yMdHm() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_Mdahm() {
        return new SimpleDateFormat("M月d日 a h:mm", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_E() {
        return new SimpleDateFormat("E", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_ahm() {
        return new SimpleDateFormat("a h:mm", Locale.TAIWAN);
    }

    public SimpleDateFormat getFrmt_Md() {
        return new SimpleDateFormat("M/d", Locale.TAIWAN);
    }
}
