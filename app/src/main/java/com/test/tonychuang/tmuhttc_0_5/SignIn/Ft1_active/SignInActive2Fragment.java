package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active;


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

import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.SignInActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.AES.MyAES;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;

import org.json.JSONObject;

import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInActive2Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;
    private TextView nextTv;
    private MaterialEditText editName;
    private MaterialEditText editPhone;


    public SignInActive2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_active2, container, false);
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
            AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("步驟 1 輸入基本資料2");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initViews() {
        editName = (MaterialEditText) view.findViewById(R.id.editName);
        editPhone = (MaterialEditText) view.findViewById(R.id.editPhone);
        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editName.setError(null);
                } else {
                    if (TextUtils.isEmpty(editName.getText().toString())) {
                        editName.setError("必填");
                    }
                }
            }
        });
        editPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editPhone.setError(null);
                } else {
                    if (TextUtils.isEmpty(editName.getText().toString())) {
                        editPhone.setError("必填");
                    }
                }
            }
        });
        editName.addTextChangedListener(getTextWatcher());
        editPhone.addTextChangedListener(getTextWatcher());
    }

    private void initBottomBar() {
        nextTv = (TextView) view.findViewById(R.id.nextTv);
        setTvEnabledFalse(nextTv);
    }

    private void initBtn() {
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputNamePhome();
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
                if (!TextUtils.isEmpty(editName.getText().toString())
                        && !TextUtils.isEmpty(editPhone.getText().toString())) {
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
     * 註冊帳號第一步-輸入基本資料2
     * 1.填基本資料
     * 2.送出基本資料，接收結果(true-成功產生註冊碼，並寄出信件，跳到下一頁、false-發生錯誤,稍後再試)
     */
    /**
     *
     */
    private void InputNamePhome() {
        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "正在為您註冊中，請稍後");
        MyAES myAES = new MyAES();
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                boolean aboolean = false;

                try {
                    JSONObject jsonObject = httcjsonapi.InputNamePhome(params[0], params[1], params[2]);
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
                if(aBoolean){
                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .addToBackStack(null)
                            .replace(R.id.content, new SignInActive3Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                            .commit();
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再註冊，感謝您", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        }.execute(SignInActiveActivity.pid,
                editName.getText().toString(),
                editPhone.getText().toString());

        /**
         myAES.EncryptAES(SignInActiveActivity.pid),
         myAES.EncryptAES(editName.getText().toString()),
         myAES.EncryptAES(editPhone.getText().toString())
         */
    }


    /**
     * d2
     */
    /**
     *
     */


}
