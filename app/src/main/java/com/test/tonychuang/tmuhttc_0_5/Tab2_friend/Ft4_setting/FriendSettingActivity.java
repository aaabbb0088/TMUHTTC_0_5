package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRecvNotSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FShrSetRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.ArrayList;

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
    private LinearLayout shareDataLayout;
    private LinearLayout receiveNoticeLayout;
    private SwitchButton shrDataSw;
    private SwitchButton shrMedSw;
    private SwitchButton shrPaySw;
    private SwitchButton shrRprtSw;
    private SwitchButton shrRcrdSw;
    private SwitchButton shrLctSw;
    private SwitchButton recDataSw;
    private SwitchButton recMedSw;
    private SwitchButton recPaySw;
    private SwitchButton recRprtSw;
    private SwitchButton recRcrdSw;
    private MyInitReturnBar myInitReturnBar;
    private DialogPlus dialog;
    private MaterialEditText editNickNameEd;
    private InputMethodManager imm;

    private SignInShrPref signInShrPref;
    private String friAid;
    private String friName;
    private String friendNickName;
    private boolean memberFlag;
    private ArrayList<String> bfFriShrSetting;
    private ArrayList<String> afFriShrSetting;
    private ArrayList<String> bfFriRecSetting;
    private ArrayList<String> afFriRecSetting;
    private String ShrSettingSameFlag;
    private String RecSettingSameFlag;

    private ArrayList<FShrSetRow> fShrSetRows;
    private ArrayList<FRecvNotSetRow> fRecvNotSetRows;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_setting);

        initView();
        initData();
        initBar(friName);
        showData();
        initListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friNickNameTv:
                editFriNickNameDialog();
                break;
            case R.id.shrDataSw:
                if (shrDataSw.isChecked()) {
                    afFriShrSetting.set(0, "Y");
                } else {
                    afFriShrSetting.set(0, "N");
                }
                break;
            case R.id.shrMedSw:
                if (shrMedSw.isChecked()) {
                    afFriShrSetting.set(1, "Y");
                } else {
                    afFriShrSetting.set(1, "N");
                }
                break;
            case R.id.shrPaySw:
                if (shrPaySw.isChecked()) {
                    afFriShrSetting.set(2, "Y");
                } else {
                    afFriShrSetting.set(2, "N");
                }
                break;
            case R.id.shrRprtSw:
                if (shrRprtSw.isChecked()) {
                    afFriShrSetting.set(3, "Y");
                } else {
                    afFriShrSetting.set(3, "N");
                }
                break;
            case R.id.shrRcrdSw:
                if (shrRcrdSw.isChecked()) {
                    afFriShrSetting.set(4, "Y");
                } else {
                    afFriShrSetting.set(4, "N");
                }
                break;
            case R.id.shrLctSw:
                if (shrLctSw.isChecked()) {
                    afFriShrSetting.set(5, "Y");
                } else {
                    afFriShrSetting.set(5, "N");
                }
                break;
            case R.id.recDataSw:
                if (recDataSw.isChecked()) {
                    afFriRecSetting.set(0, "Y");
                } else {
                    afFriRecSetting.set(0, "N");
                }
                break;
            case R.id.recMedSw:
                if (recMedSw.isChecked()) {
                    afFriRecSetting.set(1, "Y");
                } else {
                    afFriRecSetting.set(1, "N");
                }
                break;
            case R.id.recPaySw:
                if (recPaySw.isChecked()) {
                    afFriRecSetting.set(2, "Y");
                } else {
                    afFriRecSetting.set(2, "N");
                }
                break;
            case R.id.recRprtSw:
                if (recRprtSw.isChecked()) {
                    afFriRecSetting.set(3, "Y");
                } else {
                    afFriRecSetting.set(3, "N");
                }
                break;
            case R.id.recRcrdSw:
                if (recRcrdSw.isChecked()) {
                    afFriRecSetting.set(4, "Y");
                } else {
                    afFriRecSetting.set(4, "N");
                }
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
        upDateSetting();
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
    private void initBar(String friName) {
        myInitReturnBar = new MyInitReturnBar(this, "好友設定 - " + friName, 0);
    }

    private void initView() {
        imm = (InputMethodManager) getSystemService(FriendSettingActivity.INPUT_METHOD_SERVICE);
        friNickNameTv = (TextView) findViewById(R.id.friNickNameTv);
        friNickNameTv.setOnClickListener(this);
        shareDataLayout = (LinearLayout) findViewById(R.id.shareDataLayout);
        receiveNoticeLayout = (LinearLayout) findViewById(R.id.receiveNoticeLayout);
        shrDataSw = (SwitchButton) findViewById(R.id.shrDataSw);
        shrDataSw.setOnClickListener(this);
        shrMedSw = (SwitchButton) findViewById(R.id.shrMedSw);
        shrMedSw.setOnClickListener(this);
        shrPaySw = (SwitchButton) findViewById(R.id.shrPaySw);
        shrPaySw.setOnClickListener(this);
        shrRprtSw = (SwitchButton) findViewById(R.id.shrRprtSw);
        shrRprtSw.setOnClickListener(this);
        shrRcrdSw = (SwitchButton) findViewById(R.id.shrRcrdSw);
        shrRcrdSw.setOnClickListener(this);
        shrLctSw = (SwitchButton) findViewById(R.id.shrLctSw);
        shrLctSw.setOnClickListener(this);
        recDataSw = (SwitchButton) findViewById(R.id.recDataSw);
        recDataSw.setOnClickListener(this);
        recMedSw = (SwitchButton) findViewById(R.id.recMedSw);
        recMedSw.setOnClickListener(this);
        recPaySw = (SwitchButton) findViewById(R.id.recPaySw);
        recPaySw.setOnClickListener(this);
        recRprtSw = (SwitchButton) findViewById(R.id.recRprtSw);
        recRprtSw.setOnClickListener(this);
        recRcrdSw = (SwitchButton) findViewById(R.id.recRcrdSw);
        recRcrdSw.setOnClickListener(this);
    }

    private void initListener() {
        myInitReturnBar.getActionBarLeftLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog == null || !dialog.isShowing()) {
                    upDateSetting();
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
                .setOnClickListener(getOnClickListener(dialogView, editNickNameEd))   //確認鍵、取消鍵、按返回鍵鍵盤會縮下去
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

    private OnClickListener getOnClickListener(final View dialogView, final MaterialEditText editNickNameEd) {
        return new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.confirmTv:
//                        toast("confirm : " + editNickNameEd.getText().toString());
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        friendNickName = editNickNameEd.getText().toString();
                        friNickNameTv.setText(friendNickName);
                        upDateNickName();
                        dialog.dismiss();
                        break;
                    case R.id.cancelTv:
//                        toast("cancel");
                        imm.hideSoftInputFromWindow(dialogView.getWindowToken(), 0);
                        dialog.dismiss();
                        break;
                }
            }

            private void upDateNickName() {
                final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, FriendSettingActivity.this, "更新好友暱稱中，請稍後");
                new AsyncTask<String, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(String... params) {
                        HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                        JSONParser jsonParser = new JSONParser();
                        Boolean aboolean = false;

                        JSONObject jsonObject;
                        try {
                            jsonObject = httcjsonapi.EditFriendNickName(params[0], params[1], params[2]);
                            aboolean = jsonParser.parseBoolean(jsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return aboolean;
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
                            DataBase mainDB = LiteOrm.newSingleInstance(FriendSettingActivity.this, signInShrPref.getAID());
                            ArrayList<FRow> fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                                    .whereEquals(FRow.F_FRI_AID, friAid));
                            if (fRows.size() != 0) {
                                fRows.get(0).setF_nickname(friendNickName);
                                fRows.get(0).setF_nickname_flag("Y");
                                mainDB.update(fRows);
                            }
                            LiteOrm.releaseMemory();
                            mainDB.close();
                        } else {
                            Toast.makeText(FriendSettingActivity.this, "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(signInShrPref.getAID(), friAid, friendNickName);
            }
        };
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
    private void initData() {
        signInShrPref = new SignInShrPref(this);
        friAid = this.getIntent().getStringExtra("friAid");
        friName = this.getIntent().getStringExtra("friName");
        friendNickName = this.getIntent().getStringExtra("friendNickName");
        memberFlag = this.getIntent().getBooleanExtra("memberFlag", true);

        DataBase mainDB = LiteOrm.newSingleInstance(this, signInShrPref.getAID());
        fShrSetRows = mainDB.query(new QueryBuilder<FShrSetRow>(FShrSetRow.class)
                .whereEquals(FShrSetRow.FSHRSET_FRI_AID, friAid));
        fRecvNotSetRows = mainDB.query(new QueryBuilder<FRecvNotSetRow>(FRecvNotSetRow.class)
                .whereEquals(FRecvNotSetRow.FRECVNOTSET_FRI_AID, friAid));
        LiteOrm.releaseMemory();
        mainDB.close();

        bfFriShrSetting = new ArrayList<>();
        bfFriShrSetting.add("N");
        bfFriShrSetting.add("N");
        bfFriShrSetting.add("N");
        bfFriShrSetting.add("N");
        bfFriShrSetting.add("N");
        bfFriShrSetting.add("N");
        afFriShrSetting = new ArrayList<>();
        bfFriRecSetting = new ArrayList<>();
        bfFriRecSetting.add("N");
        bfFriRecSetting.add("N");
        bfFriRecSetting.add("N");
        bfFriRecSetting.add("N");
        bfFriRecSetting.add("N");
        afFriRecSetting = new ArrayList<>();
    }

    private void showData() {
        friNickNameTv.setText(friendNickName);
        if (signInShrPref.getMemberFlag() && fShrSetRows.size() != 0) {
            FShrSetRow fShrSetRow = fShrSetRows.get(0);
            if (fShrSetRow.getFShrSet_data_flag().equals("Y")) {
                bfFriShrSetting.set(0, "Y");
                shrDataSw.setCheckedImmediately(true);
            }
            if (fShrSetRow.getFShrSet_medicine_flag().equals("Y")) {
                bfFriShrSetting.set(1, "Y");
                shrMedSw.setCheckedImmediately(true);
            }
            if (fShrSetRow.getFShrSet_pay_flag().equals("Y")) {
                bfFriShrSetting.set(2, "Y");
                shrPaySw.setCheckedImmediately(true);
            }
            if (fShrSetRow.getFShrSet_report_flag().equals("Y")) {
                bfFriShrSetting.set(3, "Y");
                shrRprtSw.setCheckedImmediately(true);
            }
            if (fShrSetRow.getFShrSet_record_flag().equals("Y")) {
                bfFriShrSetting.set(4, "Y");
                shrRcrdSw.setCheckedImmediately(true);
            }
            if (fShrSetRow.getFShrSet_location_flag().equals("Y")) {
                bfFriShrSetting.set(5, "Y");
                shrLctSw.setCheckedImmediately(true);
            }
            shareDataLayout.setVisibility(View.VISIBLE);
        } else {
            shareDataLayout.setVisibility(View.GONE);
        }
        afFriShrSetting.addAll(bfFriShrSetting);

        if (memberFlag && fRecvNotSetRows.size() != 0) {
            FRecvNotSetRow fRecvNotSetRow = fRecvNotSetRows.get(0);
            if (fRecvNotSetRow.getFRecvNotSet_data_flag().equals("Y")) {
                bfFriRecSetting.set(0, "Y");
                recDataSw.setCheckedImmediately(true);
            }
            if (fRecvNotSetRow.getFRecvNotSet_medicine_flag().equals("Y")) {
                bfFriRecSetting.set(1, "Y");
                recMedSw.setCheckedImmediately(true);
            }
            if (fRecvNotSetRow.getFRecvNotSet_pay_flag().equals("Y")) {
                bfFriRecSetting.set(2, "Y");
                recPaySw.setCheckedImmediately(true);
            }
            if (fRecvNotSetRow.getFRecvNotSet_report_flag().equals("Y")) {
                bfFriRecSetting.set(3, "Y");
                recRprtSw.setCheckedImmediately(true);
            }
            if (fRecvNotSetRow.getFRecvNotSet_record_flag().equals("Y")) {
                bfFriRecSetting.set(4, "Y");
                recRcrdSw.setCheckedImmediately(true);
            }
            receiveNoticeLayout.setVisibility(View.VISIBLE);
        } else {
            receiveNoticeLayout.setVisibility(View.GONE);
        }
        afFriRecSetting.addAll(bfFriRecSetting);
    }

    private void upDateSetting() {
        ShrSettingSameFlag = compareArrayList(bfFriShrSetting, afFriShrSetting);
        RecSettingSameFlag = compareArrayList(bfFriRecSetting, afFriRecSetting);
        if (ShrSettingSameFlag.equals("N") || RecSettingSameFlag.equals("N")){
            new AsyncTask<Object, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Object... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    Boolean aboolean = false;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.EditFriendShrRecvSetting((String) params[0],
                                (String) params[1],(String) params[2],(ArrayList<String>) params[3],(String) params[4],(ArrayList<String>) params[5]);
                        aboolean = jsonParser.parseBoolean(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return aboolean;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        DataBase mainDB = LiteOrm.newSingleInstance(FriendSettingActivity.this, signInShrPref.getAID());
                        if (ShrSettingSameFlag.equals("N")) {
                            fShrSetRows.get(0).setFShrSet_data_flag(afFriShrSetting.get(0));
                            fShrSetRows.get(0).setFShrSet_medicine_flag(afFriShrSetting.get(1));
                            fShrSetRows.get(0).setFShrSet_pay_flag(afFriShrSetting.get(2));
                            fShrSetRows.get(0).setFShrSet_report_flag(afFriShrSetting.get(3));
                            fShrSetRows.get(0).setFShrSet_record_flag(afFriShrSetting.get(4));
                            fShrSetRows.get(0).setFShrSet_location_flag(afFriShrSetting.get(5));
                            mainDB.update(fShrSetRows);
                        }
                        if (RecSettingSameFlag.equals("N")) {
                            fRecvNotSetRows.get(0).setFRecvNotSet_data_flag(afFriRecSetting.get(0));
                            fRecvNotSetRows.get(0).setFRecvNotSet_medicine_flag(afFriRecSetting.get(1));
                            fRecvNotSetRows.get(0).setFRecvNotSet_pay_flag(afFriRecSetting.get(2));
                            fRecvNotSetRows.get(0).setFRecvNotSet_report_flag(afFriRecSetting.get(3));
                            fRecvNotSetRows.get(0).setFRecvNotSet_record_flag(afFriRecSetting.get(4));
                            mainDB.update(fRecvNotSetRows);
                        }
                        LiteOrm.releaseMemory();
                        mainDB.close();
                    } else {
                        Toast.makeText(FriendSettingActivity.this, "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute(signInShrPref.getAID(), friAid, ShrSettingSameFlag, afFriShrSetting, RecSettingSameFlag, afFriRecSetting);
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    private String compareArrayList(ArrayList<String> bfSetting, ArrayList<String> afSetting) {
        String result = "Y";
        if (bfSetting.size() == afSetting.size()) {
            for (int i = 0; i < bfSetting.size(); i++) {
                if (!bfSetting.get(i).equals(afSetting.get(i))) {
                    result = "N";
                    break;
                }
            }
        } else {
            result = "N";
        }
        return result;
    }

    private void toast(String msg) {
        Toast.makeText(FriendSettingActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
