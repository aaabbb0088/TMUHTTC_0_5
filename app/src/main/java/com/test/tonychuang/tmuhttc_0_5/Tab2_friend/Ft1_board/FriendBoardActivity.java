package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft1_board;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class FriendBoardActivity extends AppCompatActivity {

    private MyInitReturnBar myInitReturnBar;
    private Boolean[] updateEndflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_board);

        initBar();
        initData();
    }

    private void initData() {
        updateEndflag = new Boolean[]{false, false};
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "好友訊息", 0);
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
     *
     */
    //好友邀請訊息表


    //好友訊息表



    /**
     * d2
     */
    /**
     *
     */

}
