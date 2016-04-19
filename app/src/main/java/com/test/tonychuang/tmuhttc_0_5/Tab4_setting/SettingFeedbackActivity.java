package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

public class SettingFeedbackActivity extends AppCompatActivity implements View.OnClickListener {


    private Button sendFeedBtn;
    private EditText feedbackEd;

    private MyInitReturnBar myInitReturnBar;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_feedback);

        initBar();
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendFeedBtn:
                checkSendDialog();
                break;
        }
    }

    
    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        imm = (InputMethodManager) getSystemService(SettingPersonalDataActivity.INPUT_METHOD_SERVICE);
        sendFeedBtn = (Button) findViewById(R.id.sendFeedBtn);
        sendFeedBtn.setEnabled(false);
        feedbackEd = (EditText) findViewById(R.id.feedbackEd);

        //一進入Activity不彈出虛擬鍵盤 影響:多工鍵在回去時,鍵盤會自動隱藏
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "問題回報", 0);
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(feedbackEd.getText().toString().trim())) {
                    checkReturnDialog();
                } else {
                    SettingFeedbackActivity.this.finish();
                }
            }
        });
    }
    private void initListener() {
        sendFeedBtn.setOnClickListener(this);
        feedbackEd.addTextChangedListener(getTextWatcher());
    }
    private void checkSendDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_feed_send_body, null);
        TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false)
                .create();
        alertDialog.show();
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                feedbackEd.setText("");
                Toast.makeText(SettingFeedbackActivity.this, "您的寶貴意見已送出，感謝您。", Toast.LENGTH_SHORT).show();
                SettingFeedbackActivity.this.onBackPressed();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    private void checkReturnDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_feed_return_body, null);
        TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false)
                .create();
        alertDialog.show();
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                SettingFeedbackActivity.this.finish();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }



    /**
     * v2
     */
    /**
     *
     */
    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(feedbackEd.getText().toString().trim())) {
                    sendFeedBtn.setEnabled(true);
                } else {
                    sendFeedBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    private DialogInterface.OnKeyListener getOnKeyListener() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        };
    }



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

}
