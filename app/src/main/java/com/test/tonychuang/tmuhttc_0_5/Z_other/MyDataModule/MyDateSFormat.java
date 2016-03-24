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

    public SimpleDateFormat getM2CFrmt_yMd() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
    }

    public SimpleDateFormat getM2CFrmt_yMdHm() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN);
    }
}
