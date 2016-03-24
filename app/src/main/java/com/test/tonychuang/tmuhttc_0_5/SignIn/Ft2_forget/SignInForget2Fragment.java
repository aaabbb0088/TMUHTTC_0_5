package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.AES.MyAES;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget2Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;
    private EditText RegNumEd;
    private Button RequestCodeBtn;
    private TextView sendTv;
    private CountDownTimer countDownTimer;


    public SignInForget2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget2, container, false);
        initBar();
        initViews();
        initBtn();

        return view;
    }


    /**
     * v1
     */
    /**
     *
     */
    void initBar() {
        actionBar = SignInForgetActivity.actionBar;
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
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_active, null);
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("步驟 2 輸入驗證碼");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initViews() {
        RegNumEd = (EditText) view.findViewById(R.id.RegNumEd);
        RequestCodeBtn = (Button) view.findViewById(R.id.RequestCodeBtn);
        RequestCodeBtn.setEnabled(false);
        sendTv = (TextView) view.findViewById(R.id.sendTv);
        setTvEnabledFalse(sendTv);
        initcountdown();
    }

    private void initBtn() {
        RegNumEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (4 == RegNumEd.length()) {
                    setTvEnabledTrue(sendTv);
                } else {
                    setTvEnabledFalse(sendTv);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegNumEd.setEnabled(false);
                sendReId();
            }
        });
        RequestCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode();
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private void setTvEnabledFalse(TextView textView) {
        textView.setEnabled(false);
        textView.setBackgroundResource(R.drawable.background_active_btn_true);
    }

    private void setTvEnabledTrue(TextView textView) {
        textView.setEnabled(true);
        textView.setBackgroundResource(R.drawable.selector_active_forget_tv);
    }

    private void initcountdown() {
        RegNumEd.setEnabled(true);
        RegNumEd.setText("");
        RequestCodeBtn.setEnabled(false);

        countDownTimer = new CountDownTimer(300000, 1000) {
            //把	秒 轉換成 分秒 格式
            long mm = 0;
            long ss = 0;

            // 第一個參數是總的倒計時時間
            // 第二個參數是每隔多少時間(ms)調用一次onTick()方法
            public void onTick(long millisUntilFinished) {
                mm = millisUntilFinished / 60000;   //分鐘
                ss = (millisUntilFinished - mm * 60000) / 1000;   //秒
                RequestCodeBtn.setText(mm + " : " + ss + " 後重新發送");
            }

            public void onFinish() {
                RequestCodeBtn.setText("重新獲取驗證碼");
                RequestCodeBtn.setEnabled(true);
                RegNumEd.setEnabled(false);
                setTvEnabledFalse(sendTv);
                countDownTimer.cancel();
            }
        }.start();
    }


    /**
     * d1
     */
    /**
     * 忘記密第二步-輸入驗證碼
     * 1.輸入驗證碼
     * 2.如果超時，可按重新獲得驗證碼鍵(驗證碼鍵有倒數計時)
     * 3.輸入驗證碼，送出驗證碼，取得結果(1.成功,跳到下一頁，2.驗證碼錯誤,重新申請驗證碼，3.系統發生異常)
     */
    /**
     *
     */
    private void sendReId() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您註冊中，請稍後");
        MyAES myAES = new MyAES();
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aBoolean = false;

                try {
                    JSONObject jsonObject = httcjsonapi.InputRegNumFrgt(params[0], params[1]);
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
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                if (aBoolean) {
                    getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                            .replace(R.id.content, new SignInForget3Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                            .commit();
                } else {
                    Toast.makeText(getActivity(), "驗證碼錯誤，請重新申請驗證碼", Toast.LENGTH_LONG).show();
                    countDownTimer.onFinish();
                }
            }
        }
                .execute(SignInForgetActivity.pid, RegNumEd.getText().toString());
    }

    private void requestCode() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您申請新驗證碼，請稍後");
        MyAES myAES = new MyAES();
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                boolean aboolean = false;

                try {
                    JSONObject jsonObject = httcjsonapi.reSendreg(params[0], params[1]);
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
                    Toast.makeText(getActivity(), "新註冊碼已寄出", Toast.LENGTH_LONG).show();
                    initcountdown();
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試\n" +
                            "或者，直接聯繫遠距照護中心，感謝您", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                            .replace(R.id.content, new SignInForget1Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                            .commit();
                }
            }
        }
                .execute(SignInForgetActivity.pid,
                        SignInForgetActivity.email);
    }


    /**
     * d2
     */
    /**
     *
     */
}
