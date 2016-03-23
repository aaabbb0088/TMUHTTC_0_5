package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import com.maksim88.passwordedittext.PasswordEditText;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyValidator;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInActive1Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;
    private TextView nextTv;
    private MaterialEditText editPid;
    private PasswordEditText editPassword;
    private MaterialEditText editEmail;


    public SignInActive1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_active1, container, false);
        initBar();
        initViews();
        initBottomBar();
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
        actionBar = SignInActiveActivity.actionBar;
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
            actionBarText.setText("步驟 1 輸入基本資料");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initViews() {
        editPid = (MaterialEditText) view.findViewById(R.id.editPid);
        editPassword = (PasswordEditText) view.findViewById(R.id.editPassword);
        editEmail = (MaterialEditText) view.findViewById(R.id.editEmail);

        editPid.addTextChangedListener(getTextWatcher());
        editPassword.addTextChangedListener(getTextWatcher());
        editEmail.addTextChangedListener(getTextWatcher());
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

    private void initBottomBar() {
        nextTv = (TextView) view.findViewById(R.id.nextTv);
        setTvEnabledFalse(nextTv);
    }

    private void initBtn() {
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Input3Data();
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

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MyValidator.isValidTWPID(editPid.getText().toString())
                        && !TextUtils.isEmpty(editPassword.getText().toString())
                        && MyValidator.isValidEmail(editEmail.getText().toString())) {
                    setTvEnabledTrue(nextTv);
                } else {
                    setTvEnabledFalse(nextTv);
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
     * 註冊帳號第一步-輸入基本資料
     * 1.填基本資料
     * 2.送出基本資料，接收結果(1.非遠距會員,跳到F2,填寫姓名,電話、2.遠距會員,成功產生註冊碼，並寄出信件，跳到輸入驗證碼頁 3.身分證已開通過
     *                        4.系統發生錯誤,稍後再試)
     */
    /**
     *
     */
    private void Input3Data() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您註冊中，請稍後");
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                String string = "false";

                try {
                    JSONObject jsonObject = httcjsonapi.Input3Data(params[0], params[1], params[2], params[3]);
                    string = jsonParser.parseResultStrig(jsonObject);
                    if (string.equals("true")) {
                        string = jsonParser.parseString(jsonObject);
                    }
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
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                mySyncingDialog.dismiss();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                switch (s) {
                    case "unmember":
                        SignInActiveActivity.pid = editPid.getText().toString();
                        SignInActiveActivity.email = editEmail.getText().toString();
                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                                .replace(R.id.content, new SignInActive2Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                                .commit();
                        break;
                    case "member":
                        SignInActiveActivity.pid = editPid.getText().toString();
                        SignInActiveActivity.email = editEmail.getText().toString();
                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                                .replace(R.id.content, new SignInActive3Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                                .commit();
                        break;
                    case "existed":
                        Toast.makeText(getActivity(), "此身分證已經註冊過", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        getActivity().finish();
                    case "error":
                        Toast.makeText(getActivity(), "系統發生錯誤，請稍後再註冊，感謝您", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case "false":
                        Toast.makeText(getActivity(), "services錯誤", Toast.LENGTH_LONG).show();
                        break;
                    case "test":
                        Toast.makeText(getActivity(), "services測試點", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }.execute(editPid.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString(), Build.SERIAL);
    }


    /**
     * d2
     */
    /**
     *
     */
}
