package com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/3/23.
 */
public class MyDateSFormat {

    public MyDateSFormat() {
    }

    public SimpleDateFormat getC2MDateFormat() {
        return new SimpleDateFormat("yyyy/MM/dd a hh:mm", Locale.TAIWAN);
    }

    public SimpleDateFormat getM2CDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
    }

    public SimpleDateFormat getM2CDateFormatDetail() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN);
    }
}
