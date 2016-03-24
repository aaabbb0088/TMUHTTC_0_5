package com.test.tonychuang.tmuhttc_0_5.SignIn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.secrettextview.SecretTextView;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class LogoPageActivity extends AppCompatActivity {

    private SecretTextView secretTextView;
    private SignInShrPref signInShrPref;
    private String result;
    private AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        initBar();
        initView();
        initData();
        autoSignIn();
        textShow();
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
            actionBar.hide();
        }
    }

    private void initView() {
        secretTextView = (SecretTextView) findViewById(R.id.textview);
    }

    /**
     * v2
     */
    /**
     *
     */
    private void textShow() {
        secretTextView.setIsVisible(true);
        secretTextView.show();
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(LogoPageActivity.this);
    }

    private void autoSignIn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyDateSFormat myDateSFormat = new MyDateSFormat();
                SignInShrPref signInShrPref = new SignInShrPref(LogoPageActivity.this);
                if (signInShrPref.getSignInStatus()) {
                    if (!signInShrPref.getPID().equals("error")) {
                        try {
                            if (7 >= differ(myDateSFormat.getM2CFrmt_yMd().parse(signInShrPref.getSignInDatetime()), new Date())) {
                                //處理TextShow與自動登入時間不定誰長問題(主要問題:登入時間短於2.5秒，TextShow尚未跑完)
                                asyncTask = new AsyncTask<String, Void, String>() {
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
                                        switch (resultStr) {
                                            case "trueMS":
                                                getSignInSuccessResult(aid, sid, true, true);
                                                break;
                                            case "trueUMS":
                                                getSignInSuccessResult(aid, sid, true, true);
                                                break;
                                            case "trueMD":
                                                getSignInSuccessResult(aid, sid, true, true);
                                                break;
                                            case "trueUMD":
                                                getSignInSuccessResult(aid, sid, true, true);
                                                break;
                                            case "false":
                                                getSignInFailResult();
                                                break;
                                            case "error":
                                                getSignInFailResult();
                                                break;
                                            case "test":
                                                getSignInFailResult();
                                                break;
                                        }
                                    }
                                }.execute(signInShrPref.getPID(), signInShrPref.getPWD(), Build.SERIAL);
                            } else {
                                getSignInFailResult();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        getSignInFailResult();
                    }
                } else {
                    getSignInFailResult();
                }
            }
        }, 3000);
    }


    /**
     * d2
     */
    /**
     *
     */
    public long differ(Date date1, Date date2) {
        //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000);
        return date2.getTime() / 86400000 - date1.getTime() / 86400000;  //用立即數，減少乘法計算的開銷
    }

    private void getSignInSuccessResult(String aid, String sid, Boolean memBerFlag, Boolean sameMachineFlag) {
        signInShrPref.setAID(aid);
        signInShrPref.setSID(sid);
        signInShrPref.setSignInDatetime(new MyDateSFormat().getM2CFrmt_yMdHm().format(new Date()));
        signInShrPref.setSignInStatus(true);
        signInShrPref.setMemberFlag(memBerFlag);
        signInShrPref.setSameSignInMachine(sameMachineFlag);

////        測試用，用來顯示APP端登入紀錄與使用者身分
//        String str = signInShrPref.getPID() + "\n"
//                + signInShrPref.getPWD() + "\n"
//                + signInShrPref.getAID() + "\n"
//                + signInShrPref.getSID() + "\n"
//                + signInShrPref.getSignInDatetime() + "\n"
//                + String.valueOf(signInShrPref.getSignInStatus()) + "\n"
//                + String.valueOf(signInShrPref.getMemberFlag()) + "\n"
//                + String.valueOf(signInShrPref.getSameSignInMachine());
//        Toast.makeText(LogoPageActivity.this, str, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LogoPageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        LogoPageActivity.this.finish();
    }

    private void getSignInFailResult() {
        Intent intent = new Intent(LogoPageActivity.this, SignInActivity.class);
        startActivity(intent);
        LogoPageActivity.this.finish();
    }
}
