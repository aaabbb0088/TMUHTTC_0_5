package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.maksim88.passwordedittext.PasswordEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget3Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;
    private PasswordEditText editPwdEd;
    private PasswordEditText editPwdCheckEd;
    private TextView sendTv;


    public SignInForget3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget3, container, false);
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
        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPwdEd.getText().toString().equals(editPwdCheckEd.getText().toString())) {
                    changePwd();
                } else {
                    Toast.makeText(getActivity(), "密碼不一致，請檢查", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        sendTv = (TextView) view.findViewById(R.id.sendTv);
        setTvEnabledFalse(sendTv);
        editPwdEd = (PasswordEditText) view.findViewById(R.id.editPwdEd);
        editPwdCheckEd = (PasswordEditText) view.findViewById(R.id.editPwdCheckEd);
        editPwdEd.addTextChangedListener(getTextWatch());
        editPwdCheckEd.addTextChangedListener(getTextWatch());
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
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("步驟 3 輸入新密碼");
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
    private void setTvEnabledFalse(TextView textView) {
        textView.setEnabled(false);
        textView.setBackgroundResource(R.drawable.background_active_btn_true);
    }

    private void setTvEnabledTrue(TextView textView) {
        textView.setEnabled(true);
        textView.setBackgroundResource(R.drawable.selector_active_forget_tv);
    }

    private TextWatcher getTextWatch() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editPwdEd.getText())
                        && !TextUtils.isEmpty(editPwdCheckEd.getText())) {
                    setTvEnabledTrue(sendTv);
                } else {
                    setTvEnabledFalse(sendTv);
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
     * 忘記密碼第三步-輸入新密碼
     * 1.輸入新密碼、確認新密碼，判斷兩者是否相等
     * 2.送出新密碼，接收結果(true-修改成功,跳到登入頁面，要求再次輸入帳號密碼 false-修改失敗,系統發生錯誤,請重新修改密碼或直接聯絡遠距照護中心)
     */
    /**
     *
     */
    private void changePwd() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您修改密碼，請稍後");
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aBoolean = false;

                try {
                    JSONObject jsonObject = httcjsonapi.changePwd(params[0], params[1]);
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
                    Toast.makeText(getActivity(), "密碼修改成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試\n" +
                            "或者，直接聯繫遠距照護中心，感謝您", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
//                                .addToBackStack(null)
                            .replace(R.id.content, new SignInForget1Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                            .commit();
                }
            }
        }.execute(SignInForgetActivity.pid, editPwdEd.getText().toString());
    }


    /**
     * d2
     */
    /**
     *
     */
}
