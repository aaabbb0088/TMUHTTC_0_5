package com.test.tonychuang.tmuhttc_0_5.Tab4_setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
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
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.GPS.MyGPSRecordService;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnDataSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.PsnSettingShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

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
    private TextView myAidTv;

    private DialogPlus dialog;
    private MaterialEditText editNickNameEd;
    private PasswordEditText oldPasswordEd;
    private PasswordEditText newPasswordEd;
    private PasswordEditText checkPasswordEd;
    private InputMethodManager imm;

    private SignInShrPref signInShrPref;
    private PsnDataSettingShrPref psnDataSettingShrPref;
    private MyDateSFormat myDateSFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_personal_data);

        initBar();
        initView();
        initData();
        updateView();
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
                signOot();
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
        myAidTv = (TextView) findViewById(R.id.myAidTv);
    }

    private void updateView() {
        myNickNameTv.setText(psnDataSettingShrPref.getNICKNAME());
        switch (psnDataSettingShrPref.getSEX()) {
            case 0:
                mySexTv.setText("女");
                break;
            case 1:
                mySexTv.setText("男");
                break;
            default:
                break;
        }
        String birthday = psnDataSettingShrPref.getBIRTHDAY();
        if (!birthday.equals("1900-01-01")) {
            myBirthdayTv.setText(birthday);
        }
        myAidTv.setText(signInShrPref.getAID());
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
                .setOnClickListener(getOnClickListener_editNickName(dialogView, editNickNameEd))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
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
        String initDate = psnDataSettingShrPref.getBIRTHDAY();
        if (initDate.equals("1900-01-01")) {
            initDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        }
        DatePickerPopWin datePickerPopWin;
        datePickerPopWin = new DatePickerPopWin
                .Builder(this, getOnDatePickedListener())
                .textConfirm("確定") //text of confirm button
                .textCancel("取消") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#D7A500"))//color of confirm button
                .minYear(1900) //min year in loop
                .maxYear(Calendar.getInstance().get(Calendar.YEAR) + 1) // max year in loop
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
                .setOnClickListener(getOnClickListener_changePwd(dialogView, oldPasswordEd, newPasswordEd, checkPasswordEd))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
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
    private OnClickListener getOnClickListener_editNickName(final View dialogView, final MaterialEditText editNickNameEd) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog1, View view) {
                switch (view.getId()) {
                    case R.id.confirmTv:
                        final String nickName = editNickNameEd.getText().toString();
                        if (!psnDataSettingShrPref.getNICKNAME().equals(nickName)) {
                            final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SettingPersonalDataActivity.this, "資料同步中，請稍後");
                            new AsyncTask<String, Void, Boolean>() {
                                @Override
                                protected Boolean doInBackground(String... params) {
                                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                    JSONParser jsonParser = new JSONParser();
                                    Boolean aBoolean = false;

                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = httcjsonapi.EditPsnNickName(params[0], params[1]);
                                        aBoolean = jsonParser.parseBoolean(jsonObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return aBoolean;
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    mySyncingDialog.show();
                                }

                                @Override
                                protected void onPostExecute(Boolean aBoolean) {
                                    super.onPostExecute(aBoolean);
                                    mySyncingDialog.dismiss();
                                    if (aBoolean) {
                                        psnDataSettingShrPref.setNICKNAME(nickName);
                                        myNickNameTv.setText(nickName);
                                    } else {
                                        toast("系統發生錯誤，請稍後再嘗試");
                                    }
                                }
                            }.execute(signInShrPref.getAID(), nickName);
                        }
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog1.dismiss();
                        break;
                    case R.id.cancelTv:
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog1.dismiss();
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
                        if (psnDataSettingShrPref.getSEX() != 1) {
                            final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SettingPersonalDataActivity.this, "資料同步中，請稍後");
                            new AsyncTask<String, Void, Boolean>() {
                                @Override
                                protected Boolean doInBackground(String... params) {
                                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                    JSONParser jsonParser = new JSONParser();
                                    Boolean aBoolean = false;

                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = httcjsonapi.EditPsnSex(params[0], params[1]);
                                        aBoolean = jsonParser.parseBoolean(jsonObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return aBoolean;
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    mySyncingDialog.show();
                                }

                                @Override
                                protected void onPostExecute(Boolean aBoolean) {
                                    super.onPostExecute(aBoolean);
                                    mySyncingDialog.dismiss();
                                    if (aBoolean) {
                                        psnDataSettingShrPref.setSEX(1);
                                        mySexTv.setText("男");
                                    } else {
                                        toast("系統發生錯誤，請稍後再嘗試");
                                    }
                                }
                            }.execute(signInShrPref.getAID(), "1");
                        }
                        dialog.dismiss();
                        break;
                    case R.id.sexOffText:
                        if (psnDataSettingShrPref.getSEX() != 0) {
                            final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SettingPersonalDataActivity.this, "資料同步中，請稍後");
                            new AsyncTask<String, Void, Boolean>() {
                                @Override
                                protected Boolean doInBackground(String... params) {
                                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                    JSONParser jsonParser = new JSONParser();
                                    Boolean aBoolean = false;

                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = httcjsonapi.EditPsnSex(params[0], params[1]);
                                        aBoolean = jsonParser.parseBoolean(jsonObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return aBoolean;
                                }

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    mySyncingDialog.show();
                                }

                                @Override
                                protected void onPostExecute(Boolean aBoolean) {
                                    super.onPostExecute(aBoolean);
                                    mySyncingDialog.dismiss();
                                    if (aBoolean) {
                                        psnDataSettingShrPref.setSEX(0);
                                        mySexTv.setText("女");
                                    } else {
                                        toast("系統發生錯誤，請稍後再嘗試");
                                    }
                                }
                            }.execute(signInShrPref.getAID(), "0");
                        }
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

    private DatePickerPopWin.OnDatePickedListener getOnDatePickedListener() {
        return new DatePickerPopWin.OnDatePickedListener() {
            @Override
            public void onDatePickCompleted(int year, int month, int day, final String dateDesc) {
                if ("-".equals(myBirthdayTv.getText().toString().trim())
                        || !myBirthdayTv.getText().toString().equals(dateDesc)) {
                    final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SettingPersonalDataActivity.this, "資料同步中，請稍後");
                    new AsyncTask<String, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(String... params) {
                            HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                            JSONParser jsonParser = new JSONParser();
                            Boolean aBoolean = false;

                            JSONObject jsonObject;
                            try {
                                jsonObject = httcjsonapi.EditPsnBirthday(params[0], params[1]);
                                aBoolean = jsonParser.parseBoolean(jsonObject);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return aBoolean;
                        }

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            mySyncingDialog.show();
                        }

                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            super.onPostExecute(aBoolean);
                            mySyncingDialog.dismiss();
                            if (aBoolean) {
                                psnDataSettingShrPref.setBIRTHDAY(dateDesc);
                                myBirthdayTv.setText(dateDesc);
                            } else {
                                toast("系統發生錯誤，請稍後再嘗試");
                            }
                        }
                    }.execute(signInShrPref.getAID(), dateDesc);
                }
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

    private OnClickListener getOnClickListener_changePwd(final View dialogView,
                                                         final PasswordEditText oldPasswordEd,
                                                         final PasswordEditText newPasswordEd,
                                                         final PasswordEditText checkPasswordEd) {
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
                        String oldPwd = oldPasswordEd.getText().toString();
                        final String newPwd = newPasswordEd.getText().toString();
                        String chkPwd = checkPasswordEd.getText().toString();
                        if (oldPwd.equals(psnDataSettingShrPref.getPWD())) {
                            if (newPwd.equals(chkPwd)) {
                                if (!newPwd.equals(oldPwd)) {
                                    final MySyncingDialog mySyncingDialog =
                                            new MySyncingDialog(false,
                                                    SettingPersonalDataActivity.this, "資料同步中，請稍後");
                                    new AsyncTask<String, Void, Boolean>() {
                                        @Override
                                        protected Boolean doInBackground(String... params) {
                                            HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                            JSONParser jsonParser = new JSONParser();
                                            Boolean aBoolean = false;

                                            JSONObject jsonObject;
                                            try {
                                                jsonObject = httcjsonapi.EditPsnPwd(params[0], params[1], params[2]);
                                                aBoolean = jsonParser.parseBoolean(jsonObject);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            return aBoolean;
                                        }

                                        @Override
                                        protected void onPreExecute() {
                                            super.onPreExecute();
                                            mySyncingDialog.show();
                                        }

                                        @Override
                                        protected void onPostExecute(Boolean aBoolean) {
                                            super.onPostExecute(aBoolean);
                                            mySyncingDialog.dismiss();
                                            if (aBoolean) {
                                                psnDataSettingShrPref.setPWD(newPwd);
                                                toast("密碼修改完成");
                                            } else {
                                                toast("系統發生錯誤，請稍後再嘗試");
                                            }
                                        }
                                    }.execute(signInShrPref.getAID(), newPwd, psnDataSettingShrPref.getEMAIL());
                                }
                                imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                                dialog.dismiss();
                            } else {
                                toast("新密碼不一致");
                            }
                        } else {
                            toast("原密碼錯誤");
                        }
                        break;
                    case R.id.cancelTv:
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                }
            }
        };
    }

    private void toast(String msg) {
        Toast.makeText(SettingPersonalDataActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(this);
        psnDataSettingShrPref = new PsnDataSettingShrPref(this, signInShrPref.getAID());
        myDateSFormat = new MyDateSFormat();
    }

    private void signOot() {
        signOutBtn.setEnabled(false);
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SettingPersonalDataActivity.this, "正在登出中，請稍後");
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aBoolean = false;

                try {
                    JSONObject jsonObject = httcjsonapi.SignOut(params[0], params[1]);
                    aBoolean = jsonParser.parseBoolean(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return aBoolean;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mySyncingDialog.show();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                mySyncingDialog.dismiss();
                if (aBoolean) {
                    //關閉GPS
                    PsnSettingShrPref psnSettingShrPref = new PsnSettingShrPref(SettingPersonalDataActivity.this, signInShrPref.getAID());
                    psnSettingShrPref.setLOCATION_FLAG("N");
                    Intent stopintent = new Intent(SettingPersonalDataActivity.this, MyGPSRecordService.class);
                    stopService(stopintent);

                    signInShrPref.setSignInStatus(false);
                    signInShrPref.setSignInDatetime(new MyDateSFormat().getFrmt_yMdHm().format(new Date()));
                    Intent intent = new Intent(SettingPersonalDataActivity.this, SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    SettingPersonalDataActivity.this.finish();
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                } else {
                    Toast.makeText(SettingPersonalDataActivity.this, "登出失敗，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                    signOutBtn.setEnabled(true);
                }
            }
        }.execute(signInShrPref.getAID(), Build.SERIAL);
    }


    /**
     * d2
     */
    /**
     *
     */


}
