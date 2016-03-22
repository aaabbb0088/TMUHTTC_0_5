package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft4_report;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ProcessBtnGenerator.ProgressGenerator;


/**
 * 參考(Download_on_click_Button-master、SubmitDemo)
 */

/**
 * 報告書頁面下載鍵,下載完成狀態下,
 * 長按,跳出
 * (1)重新下載btn
 * (2)分享btn
 * 用dialog & ListView
 */


public class FriendPersonalReportActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener {

    private MyInitReturnBar myInitReturnBar;
    private SubmitProcessButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal_report);

        initBar();

        testDownloadBtn();
    }

    @Override
    public void onComplete() {
        btnSend.setEnabled(true);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FriendPersonalReportActivity.this, "Open the Report", Toast.LENGTH_SHORT).show();
            }
        });
        btnSend.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "會員暱稱 健康報告書", 0);
    }
    private void testDownloadBtn() {

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        btnSend = (SubmitProcessButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSend);
                btnSend.setEnabled(false);
            }
        });

//        final ProcessButton mProcessButton = (ProcessButton) findViewById(R.id.process_button);
//        if (mProcessButton != null) {
//            mProcessButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mProcessButton.setButtonState(ProcessButton.ButtonState.PROCESSING);
//                    new DownloadThread(mProcessButton).start();
//                    mProcessButton.setEnabled(false);
//                }
//            });
//        }
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



    /**
     * d2
     */
    /**
     *
     */


//    private class DownloadThread extends Thread {
//        Handler mHandler = new Handler();
//
//        private ProcessButton mInnerProcessButton;
//
//        public DownloadThread(ProcessButton processButton) {
//            mInnerProcessButton = processButton;
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            while (mInnerProcessButton.getButtonState() == ProcessButton.ButtonState.PROCESSING && mInnerProcessButton.getCurrentProgress() <= 100) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mInnerProcessButton.setProcess(mInnerProcessButton.getCurrentProgress() + 5);
//                    }
//                });
//                try {
//                    sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mInnerProcessButton.setEnabled(true);
//                    if (mInnerProcessButton.getCurrentProgress() >= 100) {
//                        mInnerProcessButton.setButtonState(ProcessButton.ButtonState.COMPLETE);
//                    }
//                }
//            });
//        }
//    }
}
