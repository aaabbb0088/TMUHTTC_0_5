package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft3_pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class PersonServicePayActivity extends AppCompatActivity {

    private MyInitReturnBar myInitReturnBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_service_pay);

        initBar();
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "繳費紀錄", 0);
    }


    /**
     * v2
     */
    /**
     *
     */



    /**
     * d1
     */
    /**
     * 更新資料
     * payUpdatedFlag = 0;更新資料
     * payUpdatedFlag = 1;已更新資料
     *
     * 個人繳費紀錄表ServicePayTable (僅登入者資料)
     */
    /**
     *
     */



    /**
     * d2
     */
    /**
     *
     */

}
