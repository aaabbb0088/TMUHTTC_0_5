package com.test.tonychuang.tmuhttc_0_5.SignIn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.maksim88.passwordedittext.PasswordEditText;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active.SignInActiveActivity;
import com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget.SignInForgetActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyValidator;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.util.Date;

public class SignInActivity extends AppCompatActivity {


    private Button activeBtn;
    private Button forgetBtn;
    private ActionProcessButton signInBtn;
    private MaterialEditText editPid;
    private PasswordEditText editPassword;
    private ImageView pleaseWaitImgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initBar();
        initView();
        initBtn();
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); // Specify that tabs should be displayed in the action bar.
            //禁用ActionBar標題
            actionBar.setDisplayShowTitleEnabled(false);
            //禁用ActionBar圖標
            actionBar.setDisplayUseLogoEnabled(false);
            //禁用ActionBar返回鍵
            actionBar.setDisplayShowHomeEnabled(false);

            //自定義 ActionBar
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title, null);
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("台北醫學大學 遠距照護中心");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initView() {
        activeBtn = (Button) findViewById(R.id.activeBtn);
        forgetBtn = (Button) findViewById(R.id.forgetBtn);
        signInBtn = (ActionProcessButton) findViewById(R.id.signInBtn);
        signInBtn.setEnabled(false);
        signInBtn.setTextColor(Color.BLACK);
        editPid = (MaterialEditText) findViewById(R.id.editPid);
        editPassword = (PasswordEditText) findViewById(R.id.editPassword);
        pleaseWaitImgView = (ImageView) findViewById(R.id.pleaseWaitImgView);

        editPid.addTextChangedListener(getTextWatch());
        editPassword.addTextChangedListener(getTextWatch());
        editPid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editPid.setError(null);
                } else {
                    if (TextUtils.isEmpty(editPid.getText())) {
                        editPid.setError("必填");
                    } else if (!MyValidator.isValidTWPID(editPid.getText().toString())) {
                        editPid.setError("身分證格式錯誤");
                    }
                }
            }
        });
    }

    private void initBtn() {
        signInBtn.setMode(ActionProcessButton.Mode.ENDLESS);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    int mProgress;
//
//                    @Override
//                    public void run() {
//                        mProgress += 10;
//                        if (mProgress < 100) {
//                            handler.postDelayed(this, new Random().nextInt(1000));
//                        } else {
//                            signInBtn.setProgress(100);
////                            Toast.makeText(SignInActivity.this, "Loading Complete, button is disabled", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            SignInActivity.this.finish();
//                        }
//                    }
//                }, new Random().nextInt(1000));
            }
        });

        activeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPid.setText("");
                editPassword.setText("");
                Intent intent = new Intent().setClass(SignInActivity.this, SignInActiveActivity.class);
                startActivity(intent);

            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPid.setText("");
                editPassword.setText("");
                Intent intent = new Intent().setClass(SignInActivity.this, SignInForgetActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private TextWatcher getTextWatch() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editPid.getText())
                        && MyValidator.isValidTWPID(editPid.getText().toString())
                        && !TextUtils.isEmpty(editPassword.getText())) {
                    signInBtn.setEnabled(true);
                    signInBtn.setTextColor(Color.WHITE);
                } else {
                    signInBtn.setEnabled(false);
                    signInBtn.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }


    /**
     * d1
     */
    /**
     * 登入動作
     * 1.輸入身分證字號、密碼，呼叫登入API
     * 2.回傳登入結果(1.成功、2.無此帳號、3.帳號或密碼錯誤、4.系統發生錯誤)
     * 3.登入成功，將登入結果寫入SignInShrPref(帳號[加密過]、密碼[加密過]、登入時間、登入狀態)
     *
     * 註冊
     *
     * 忘記密碼
     */
    /**
     *
     */
    private void signIn() {
        /**
         * 取得結果後
         * 1.正常登入
         *   1-1.將登入結果寫入SP(帳號、密碼、登入時間、登入狀態、是否是遠距會員、手機序號)
         * 2.密碼資料錯誤
         * 3.系統異常
         */
        /**
         *
         */
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, SignInActivity.this, "正在登入中，請稍後");
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                String string = "error";

                try {
                    JSONObject jsonObject = httcjsonapi.SignIn(params[0], params[1], params[2]);
                    string = jsonParser.parseString(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return string;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mySyncingDialog.show();
                signInBtn.setProgress(50);
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.BLACK);
                editPid.setEnabled(false);
                editPassword.setEnabled(false);
                activeBtn.setEnabled(false);
                forgetBtn.setEnabled(false);
                pleaseWaitImgView.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String[] token = s.split(",");
                String resultStr = token[0];
                String aid = "";
                String sid = "";
                if (token.length == 2) {
                    aid = token[1];
                }
                if (token.length == 3) {
                    aid = token[1];
                    sid = token[2];
                }
                mySyncingDialog.dismiss();
                switch (resultStr) {
                    case "trueMS":
                        getSignInSuccessResult(aid, sid, true, true);
                        break;
                    case "trueUMS":
                        getSignInSuccessResult(aid, sid, false, true);
                        break;
                    case "trueMD":
                        getSignInSuccessResult(aid, sid, true, false);
                        break;
                    case "trueUMD":
                        getSignInSuccessResult(aid, sid, false, false);
                        break;
                    case "false":
                        getSignInFailResult("帳號密碼錯誤，請確認登入資料");
                        break;
                    case "error":
                        getSignInFailResult("系統發生錯誤，請稍後再登入");
                        break;
                    case "test":
                        getSignInFailResult("測試點");
                        break;
                }
            }
        }.execute(editPid.getText().toString(), editPassword.getText().toString(), Build.SERIAL);
    }


    /**
     * d2
     */
    /**
     *
     */
    private void getSignInSuccessResult(String aid, String sid, Boolean memBerFlag, Boolean sameMachineFlag) {
        signInBtn.setProgress(100);//成功登入的Btn
        SignInShrPref signInShrPref = new SignInShrPref(SignInActivity.this,
                editPid.getText().toString(),
                editPassword.getText().toString(),
                aid,
                sid,
                new MyDateSFormat().getM2CFrmt_yMdHm().format(new Date()),
                true,
                memBerFlag,
                sameMachineFlag);

////        測試用，用來顯示APP端登入紀錄與使用者身分
//        String str = signInShrPref.getPID() + "\n"
//                + signInShrPref.getPWD() + "\n"
//                + signInShrPref.getAID() + "\n"
//                + signInShrPref.getSID() + "\n"
//                + signInShrPref.getSignInDatetime() + "\n"
//                + String.valueOf(signInShrPref.getSignInStatus()) + "\n"
//                + String.valueOf(signInShrPref.getMemberFlag()) + "\n"
//                + String.valueOf(signInShrPref.getSameSignInMachine());
//        Toast.makeText(SignInActivity.this, str, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        SignInActivity.this.finish();
    }

    private void getSignInFailResult(String content) {
        signInBtn.setProgress(0);//Btn初始狀態
        signInBtn.setEnabled(true);
        signInBtn.setTextColor(Color.WHITE);
        editPid.setEnabled(true);
        editPassword.setEnabled(true);
        activeBtn.setEnabled(true);
        forgetBtn.setEnabled(true);
        pleaseWaitImgView.setVisibility(View.INVISIBLE);
        Toast.makeText(SignInActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}
