package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.maksim88.passwordedittext.PasswordEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SettingPersonalDataActivity extends AppCompatActivity implements View.OnClickListener {

    private MyInitReturnBar myInitReturnBar;
    private LinearLayout avatarLayout;
    private LinearLayout nicknameLayout;
    private LinearLayout sexLayout;
    private LinearLayout birthdayLayout;
    private LinearLayout changePwdLayout;
    private Button signOutBtn;
    private TextView myNickNameTv;
    private TextView mySexTv;
    private TextView myBirthdayTv;

    private DialogPlus dialog;
    private MaterialEditText editNickNameEd;
    private PasswordEditText oldPasswordEd;
    private PasswordEditText newPasswordEd;
    private PasswordEditText checkPasswordEd;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_personal_data);

        initBar();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatarLayout:
                Toast.makeText(SettingPersonalDataActivity.this, "avatar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nicknameLayout:
                editNickNameDialog();
                break;
            case R.id.sexLayout:
                settingSexDialog();
                break;
            case R.id.birthdayLayout:
                editBirthdayTimeWheel();
                break;
            case R.id.changePwdLayout:
                changePwdDialog();
                break;
            case R.id.signOutBtn:
                Toast.makeText(SettingPersonalDataActivity.this, "SignOut", Toast.LENGTH_SHORT).show();
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
     *
     */
    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "個人資料", 0);
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog == null || !dialog.isShowing()) {
                    SettingPersonalDataActivity.this.onBackPressed();
                }
            }
        });
    }
    private void initView() {
        imm = (InputMethodManager) getSystemService(SettingPersonalDataActivity.INPUT_METHOD_SERVICE);
        avatarLayout = (LinearLayout) findViewById(R.id.avatarLayout);
        avatarLayout.setOnClickListener(this);
        nicknameLayout = (LinearLayout) findViewById(R.id.nicknameLayout);
        nicknameLayout.setOnClickListener(this);
        sexLayout = (LinearLayout) findViewById(R.id.sexLayout);
        sexLayout.setOnClickListener(this);
        birthdayLayout = (LinearLayout) findViewById(R.id.birthdayLayout);
        birthdayLayout.setOnClickListener(this);
        changePwdLayout = (LinearLayout) findViewById(R.id.changePwdLayout);
        changePwdLayout.setOnClickListener(this);
        signOutBtn = (Button) findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(this);
        myNickNameTv = (TextView) findViewById(R.id.myNickNameTv);
        mySexTv = (TextView) findViewById(R.id.mySexTv);
        myBirthdayTv = (TextView) findViewById(R.id.myBirthdayTv);

    }
    private void editNickNameDialog() {
        View dialogTitleView = LayoutInflater.from(this).inflate(R.layout.dialog_setting_psn_nickname_title, null);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_setting_psn_nickname_body, null);
        editNickNameEd = (MaterialEditText) dialogView.findViewById(R.id.editNickNameEd);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);

        dialog = DialogPlus
                .newDialog(this)
                .setHeader(dialogTitleView)
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getOnClickListener_editNickName(dialogView))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
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
    private void settingSexDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sex_setting, null);
        dialog = DialogPlus
                .newDialog(this)
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getOnClickListener_settingSex())   //確認鍵、取消鍵
                .setOnBackPressListener(getOnBackPressListener())  //按返回鍵會縮下去
                .create();
        dialog.show();
    }
    private void editBirthdayTimeWheel() {
        String initDate;
        if (myBirthdayTv.getText().toString().equals("yyyy-MM-dd")) {
            initDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        } else {
            initDate = myBirthdayTv.getText().toString();
        }
        DatePickerPopWin datePickerPopWin;
        datePickerPopWin = new DatePickerPopWin.Builder(this, new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                myBirthdayTv.setText(dateDesc);
            }
        }).textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#D7A500"))//color of confirm button
                .minYear(1900) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR)) // max year in loop
                .dateChose(initDate) // date chose when init popwindow
                .build();
        datePickerPopWin.showPopWin(this);
        datePickerPopWin.contentView.setClickable(false); //點擊外部畫面,不會往下縮
    }
    private void changePwdDialog() {
        View dialogTitleView = LayoutInflater.from(this).inflate(R.layout.dialog_setting_chg_pwd_title, null);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_setting_chg_pwd_body, null);
        oldPasswordEd = (PasswordEditText) dialogView.findViewById(R.id.oldPasswordEd);
        newPasswordEd = (PasswordEditText) dialogView.findViewById(R.id.newPasswordEd);
        checkPasswordEd = (PasswordEditText) dialogView.findViewById(R.id.checkPasswordEd);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);

        dialog = DialogPlus
                .newDialog(this)
                .setHeader(dialogTitleView)
                .setContentHolder(new ViewHolder(dialogView))
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)                       //按主畫面不要縮下去
                .setOnClickListener(getOnClickListener_changePwd(dialogView))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
                .create();
        dialog.show();

//        oldPasswordEd.requestFocus();
//        imm.showSoftInput(oldPasswordEd, InputMethodManager.SHOW_FORCED); //強制顯示鍵盤 會導致按home按多工鍵時 正常收起

        confirmTv.setEnabled(false);
        oldPasswordEd.addTextChangedListener(getPwdTvWatcher(confirmTv));
        newPasswordEd.addTextChangedListener(getPwdTvWatcher(confirmTv));
        checkPasswordEd.addTextChangedListener(getPwdTvWatcher(confirmTv));
    }


    /**
     * v2
     */
    /**
     *
     */
    private OnClickListener getOnClickListener_editNickName(final View dialogView) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.confirmTv:
                        Toast.makeText(SettingPersonalDataActivity.this, "confirm : "
                                + editNickNameEd.getText().toString(), Toast.LENGTH_SHORT).show();
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        if (!"".equals(editNickNameEd.getText().toString().trim())) {
                            myNickNameTv.setText(editNickNameEd.getText().toString());
                        }
                        dialog.dismiss();
                        break;
                    case R.id.cancelTv:
                        Toast.makeText(SettingPersonalDataActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                }
            }
        };
    }
    private OnClickListener getOnClickListener_settingSex() {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.sexOnText:
                        Toast.makeText(SettingPersonalDataActivity.this, "male", Toast.LENGTH_SHORT).show();
                        mySexTv.setText("男");
                        dialog.dismiss();
                        break;
                    case R.id.sexOffText:
                        Toast.makeText(SettingPersonalDataActivity.this, "female", Toast.LENGTH_SHORT).show();
                        mySexTv.setText("女");
                        dialog.dismiss();
                        break;
                }
            }
        };
    }
    private OnBackPressListener getOnBackPressListener() {
        return new OnBackPressListener() {
            @Override
            public void onBackPressed(DialogPlus dialogPlus) {
                dialogPlus.dismiss();
            }
        };
    }
    private TextWatcher getPwdTvWatcher(final TextView confirmTv) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(oldPasswordEd.getText().toString().trim())
                        && !"".equals(newPasswordEd.getText().toString().trim())
                        && !"".equals(checkPasswordEd.getText().toString().trim())) {
                    confirmTv.setEnabled(true);
                } else {
                    confirmTv.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    private OnClickListener getOnClickListener_changePwd(final View dialogView) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.confirmTv:
                        /**
                         * 此處判斷
                         * 1.先判斷原密碼是否輸入正確(from SQLiteDB)
                         * 2.判斷新密碼是否輸入一致
                         */
                        String resutltpwd = "原密碼 : " + oldPasswordEd.getText().toString() + "\n"
                                + "新密碼 : " + newPasswordEd.getText().toString() + "\n"
                                + "確認密碼 : " + checkPasswordEd.getText().toString();
                        Toast.makeText(SettingPersonalDataActivity.this, "confirm : \n" + resutltpwd,
                                Toast.LENGTH_SHORT).show();
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                    case R.id.cancelTv:
                        Toast.makeText(SettingPersonalDataActivity.this, "cancel", Toast.LENGTH_SHORT).show();
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
