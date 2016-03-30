package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

/**
 * 1.用一資料格式容器載入原先設定，並顯示到畫面上
 * 2.修改同時，修改資料格式容器內的的值
 * 3.按下回上一頁時，將修改結果寫入SQLite，並同步寫入雲端資料庫
 * 4.身分與設定顯示
 * 資料分享(本身是遠距會員才有) : 量測、用藥、繳費、報告書、服務紀錄、定位紀錄(全部都有)
 * 願意接收(對方是遠距會員才有) : 數據、用藥、繳費、報告書、服務歷程
 */

public class FriendSettingActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView friNickNameTv;
    private MyInitReturnBar myInitReturnBar;
    private DialogPlus dialog;
    private MaterialEditText editNickNameEd;
    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_setting);

        initBar();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friNickNameTv:
                editFriNickNameDialog();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (dialog != null) {
            if (keyCode == KeyEvent.KEYCODE_BACK && dialog.isShowing()) {
                dialog.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * v1
     */
    /**
     * 好友非遠距會員("接收好友訊息"選單隱藏)、自己非遠距會員("分享給好友","讓好友收到"選單隱藏)
     */
    /**
     * 每次開啟FriSettingAty，判斷與上次使用的裝飾是否相同，相同不用更新，不同需要更新
     * APP使用者個人資料分享好友設定表FriendShareSettingTable
     * APP使用者個人訊息分享好友設定表FriendShareNoticeSettingTable
     * APP使用者好友訊息接受設定表FriendReceiveNoticeSettingTable
     */
    /**
     *
     */

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "好友設定 - 會員暱稱", 0);
    }
    private void initView() {
        imm = (InputMethodManager) getSystemService(FriendSettingActivity.INPUT_METHOD_SERVICE);
        friNickNameTv = (TextView) findViewById(R.id.friNickNameTv);
        friNickNameTv.setOnClickListener(this);
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog == null || !dialog.isShowing()) {
                    FriendSettingActivity.this.onBackPressed();
                }
            }
        });
    }
    private void editFriNickNameDialog() {
        View dialogTitleView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_fri_nickname_title, null);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_fri_nickname_body, null);
        editNickNameEd = (MaterialEditText) dialogView.findViewById(R.id.editNickNameEd);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);

        dialog = DialogPlus
                .newDialog(this)
                .setHeader(dialogTitleView)
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getOnClickListener(dialogView))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
                .create();
        dialog.show();

//        editNickNameEd.requestFocus();
//        imm.showSoftInput(editNickNameEd, InputMethodManager.SHOW_FORCED); //強制顯示鍵盤 會導致按home按多工鍵時 正常收起

        confirmTv.setEnabled(false);
        editNickNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(editNickNameEd.getText().toString().trim())) {
                    confirmTv.setEnabled(false);
                } else {
                    confirmTv.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private OnClickListener getOnClickListener(final View dialogView) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.confirmTv:
                        Toast.makeText(FriendSettingActivity.this, "confirm : " + editNickNameEd.getText().toString(), Toast.LENGTH_SHORT).show();
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                    case R.id.cancelTv:
                        Toast.makeText(FriendSettingActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                }
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
