package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.AES.MyAES;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyValidator;

import org.json.JSONObject;

import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget1Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;

    private Button changOnLineBtn;
    private Button changOnPhoneBtn;
    private MaterialEditText editPid;
    private MaterialEditText editEmail;

    private final static String CALL = "android.intent.action.CALL";


    public SignInForget1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget1, container, false);
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

    private void initBtn() {
        changOnLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode();
            }
        });

        changOnPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "撥號中... 請稍後", Toast.LENGTH_LONG).show();
                Intent call = new Intent(CALL, Uri.parse("tel:" + "0221765226"));
                startActivity(call);
            }
        });
    }

    private void initViews() {
        changOnLineBtn = (Button) view.findViewById(R.id.changOnLineBtn);
        changOnLineBtn.setEnabled(false);
        changOnPhoneBtn = (Button) view.findViewById(R.id.changOnPhoneBtn);
        editPid = (MaterialEditText) view.findViewById(R.id.editPid);
        editEmail = (MaterialEditText) view.findViewById(R.id.editEmail);

        editPid.addTextChangedListener(getTextWatch());
        editEmail.addTextChangedListener(getTextWatch());
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

        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editEmail.setError(null);
                } else {
                    if (TextUtils.isEmpty(editEmail.getText())) {
                        editEmail.setError("必填");
                    } else if (!MyValidator.isValidEmail(editEmail.getText().toString())) {
                        editEmail.setError("Email格式錯誤");
                    }
                }
            }
        });
    }

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
            AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("步驟 1 選擇修改密碼方法");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
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
                if (MyValidator.isValidTWPID(editPid.getText().toString())
                        && MyValidator.isValidEmail(editEmail.getText().toString())) {
                    changOnLineBtn.setEnabled(true);
                } else {
                    changOnLineBtn.setEnabled(false);
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
     * 忘記密碼第一步-選擇修改密碼方法
     * 1.線上修改密碼
     *   輸入身分證、Email，比對是否正確，接收結果(1.正確,成功產生註冊碼，並寄出信件，跳到下一頁 2.不正確,顯示不正確,顯聯絡遠距照護中心 3.系統發生錯誤,請聯絡遠距中心)
     * 2.直接連絡遠距中心,關閉APP
     */
    /**
     *
     */
    private void requestCode() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您申請驗證碼，請稍後");
        MyAES myAES = new MyAES();
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                String string = "error";

                try {
                    JSONObject jsonObject = httcjsonapi.frgtReSendreg(params[0], params[1]);
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
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                mySyncingDialog.dismiss();
                switch (string) {
                    case "true":
                        SignInForgetActivity.pid = editPid.getText().toString();
                        SignInForgetActivity.email = editEmail.getText().toString();
                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                                .replace(R.id.content, new SignInForget2Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                                .commit();
                        break;
                    case "false":
                        Toast.makeText(getActivity(), "資料錯誤，請確認", Toast.LENGTH_LONG).show();
                        break;
                    case "error":
                        Toast.makeText(getActivity(), "系統發生錯誤，請稍後再註冊，感謝您", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                }
            }
        }
                .execute(editPid.getText().toString(),
                        editEmail.getText().toString());
    }


    /**
     * d2
     */
    /**
     *
     */
}
