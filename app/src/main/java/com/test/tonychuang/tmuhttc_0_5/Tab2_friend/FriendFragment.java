package com.test.tonychuang.tmuhttc_0_5.Tab2_friend;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.WriterException;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft1_board.FriendBoardActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.AES.MyAES;
import com.test.tonychuang.tmuhttc_0_5.Z_other.QRCode.MyQRCodeCreate;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {


    static ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private FriendAdapter adapter;


    private View view;  //Fragment的佈局
    private ActionBar actionBar;
    private SignInShrPref signInShrPref;


    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend, container, false);
        initBar();
        initView();
        initData();
        return view;
    }


    /**
     * v1
     */
    /**
     *
     */
    public void initBar() {
        actionBar = MainActivity.actionBar;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //禁用系統ActionBar標題
        actionBar.setDisplayShowTitleEnabled(false);
        //禁用系統ActionBar圖標
        actionBar.setDisplayUseLogoEnabled(false);
        //禁用系統ActionBar返回鍵
        actionBar.setDisplayShowHomeEnabled(false);
        //清除所有已加入的Tab
        actionBar.removeAllTabs();

        //自定義 ActionBar
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_friend, null);
        AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
        final FrameLayout actionBarLeftLayout = (FrameLayout) titleView.findViewById(R.id.actionBarLeftLayout);
        FrameLayout actionBarRightLayput = (FrameLayout) titleView.findViewById(R.id.actionBarRightLayput);
        actionBarText.setText("好友列表");
        actionBarText.setTextColor(Color.WHITE);
        actionBarLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupmenu = new PopupMenu(getActivity(), actionBarLeftLayout);
                popupmenu.getMenuInflater().inflate(R.menu.add_friend_group, popupmenu.getMenu());
                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.add_friend:
                                addFriendAlertDialog();
                                break;
                            case R.id.add_group:
                                addGroupAlertDialog();
                                break;
                            case R.id.generate_qrcode:
                                generateQRcodeAlertDialog();
                                break;
                            case R.id.deleted_friend_list:
                                deletedFriendListAlertDialog();
                        }
                        return true;
                    }
                });
                setForceShowIcon(popupmenu);
                popupmenu.show();
            }
        });
        actionBarRightLayput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FriendBoardActivity.class);
                startActivity(intent);
            }
        });
        actionBar.setCustomView(titleView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    private void initView() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
    }

    private void addFriendAlertDialog() {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_friend_title, null);
        final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_friend_body, null);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        RadioGroup addWayRadioGroup = (RadioGroup) dialogView.findViewById(R.id.addWayRadioGroup);
        final LinearLayout idLayout = (LinearLayout) dialogView.findViewById(R.id.idLayout);
        final LinearLayout pidInputLayout = (LinearLayout) dialogView.findViewById(R.id.pidInputLayout);
        final MaterialEditText pidEd = (MaterialEditText) dialogView.findViewById(R.id.pidEd);
        final Button pidSearchBtn = (Button) dialogView.findViewById(R.id.pidSearchBtn);
        final LinearLayout aidInputLayout = (LinearLayout) dialogView.findViewById(R.id.aidInputLayout);
        final MaterialEditText aidEd = (MaterialEditText) dialogView.findViewById(R.id.aidEd);
        final Button aidSearchBtn = (Button) dialogView.findViewById(R.id.aidSearchBtn);
        final LinearLayout idResultLayout = (LinearLayout) dialogView.findViewById(R.id.idResultLayout);
        ImageView avatarImv = (ImageView) dialogView.findViewById(R.id.avatarImv);
        TextView nameTv = (TextView) dialogView.findViewById(R.id.nameTv);
        TextView nicknameTv = (TextView) dialogView.findViewById(R.id.nicknameTv);
        final LinearLayout qrLayout = (LinearLayout) dialogView.findViewById(R.id.qrLayout);
        final LinearLayout qrReadLayout = (LinearLayout) dialogView.findViewById(R.id.qrReadLayout);
        final QRCodeReaderView qrDecoderView = (QRCodeReaderView) dialogView.findViewById(R.id.qrDecoderView);
        final LinearLayout qrResultLayout = (LinearLayout) dialogView.findViewById(R.id.qrResultLayout);
        ImageView qrAvatarImv = (ImageView) dialogView.findViewById(R.id.qrAvatarImv);
        final TextView qrNameTv = (TextView) dialogView.findViewById(R.id.qrNameTv);
        final TextView qrNickNameTv = (TextView) dialogView.findViewById(R.id.qrNickNameTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        idLayout.setVisibility(View.GONE);
        qrLayout.setVisibility(View.GONE);
        addWayRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pidBtn:
                        confirmTv.setEnabled(false);
                        aidEd.setText("");
                        idLayout.setVisibility(View.VISIBLE);
                        qrLayout.setVisibility(View.GONE);
                        pidInputLayout.setVisibility(View.VISIBLE);
                        aidInputLayout.setVisibility(View.GONE);
                        idResultLayout.setVisibility(View.GONE);
                        qrDecoderView.setVisibility(View.GONE);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                qrDecoderView.getCameraManager().stopPreview();
                            }
                        });
                        break;
                    case R.id.aidBtn:
                        confirmTv.setEnabled(false);
                        pidEd.setText("");
                        idLayout.setVisibility(View.VISIBLE);
                        qrLayout.setVisibility(View.GONE);
                        pidInputLayout.setVisibility(View.GONE);
                        aidInputLayout.setVisibility(View.VISIBLE);
                        idResultLayout.setVisibility(View.GONE);
                        qrDecoderView.setVisibility(View.GONE);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                qrDecoderView.getCameraManager().stopPreview();
                            }
                        });
                        break;
                    case R.id.qrcodeBtn:
                        pidEd.setText("");
                        aidEd.setText("");
                        InputMethodManager imm = (InputMethodManager)dialogView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(aidEd.getWindowToken(),0);
                        confirmTv.setEnabled(false);
                        idLayout.setVisibility(View.GONE);
                        qrLayout.setVisibility(View.VISIBLE);
                        qrReadLayout.setVisibility(View.VISIBLE);
                        qrResultLayout.setVisibility(View.GONE);
                        qrDecoderView.setVisibility(View.VISIBLE);
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                qrDecoderView.getCameraManager().startPreview();
                            }
                        });
                        break;
                }
            }
        });
        pidSearchBtn.setEnabled(false);
        pidEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(pidEd.getText().toString().trim())) {
                    pidSearchBtn.setEnabled(true);
                } else {
                    pidSearchBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        aidSearchBtn.setEnabled(false);
        aidEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(aidEd.getText().toString().trim())) {
                    aidSearchBtn.setEnabled(true);
                } else {
                    aidSearchBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        qrDecoderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {
                qrDecoderView.setVisibility(View.GONE);
                qrDecoderView.getCameraManager().startPreview();
                qrReadLayout.setVisibility(View.GONE);
                qrResultLayout.setVisibility(View.VISIBLE);
                qrNameTv.setText(new MyAES().DecryptAES(text));
                qrNickNameTv.setText(text);
                confirmTv.setEnabled(true);
            }

            @Override
            public void cameraNotFound() {

            }

            @Override
            public void QRCodeNotFoundOnCamImage() {

            }
        });
        confirmTv.setEnabled(false);
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrDecoderView.getCameraManager().stopPreview();
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrDecoderView.getCameraManager().stopPreview();
                alertDialog.dismiss();
            }
        });
    }

    private void addGroupAlertDialog() {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_group_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_group_body, null);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final MaterialEditText groupNameEd = (MaterialEditText) dialogView.findViewById(R.id.groupNameEd);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        confirmTv.setEnabled(false);
        groupNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(groupNameEd.getText().toString().trim())) {
                    confirmTv.setEnabled(true);
                } else {
                    confirmTv.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void generateQRcodeAlertDialog() {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_geneate_qrcode_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_geneate_qrcode_body, null);
        ImageView qrcodeImv = (ImageView) dialogView.findViewById(R.id.qrcodeImv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        try {
            qrcodeImv.setImageBitmap(new MyQRCodeCreate("T111111111").getBitmap());
        } catch (WriterException e) {
            e.printStackTrace();
        }
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void deletedFriendListAlertDialog() {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_deleted_friend_list_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_deleted_friend_list_body, null);
        TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    //覆寫PopupMenu方法，使PopupMenu的icon顯示出來
    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private DialogInterface.OnKeyListener getOnKeyListener() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        };
    }


    /**
     * d1
     */
    /**
     *
     */

    private void initData() {
        lists = initList();
        adapter = new FriendAdapter(getActivity(), lists);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        expandableListView.setSelection(0);// 设置默认选中项
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
    }

    private List<GroupEntity> initList() {

        List<GroupEntity> groupList;
        //测试数据
        String[] groupArray = new String[]{"家人", "好友"};
        String[][] childTimeArray = new String[][]{
                {"測試帳戶1", "測試帳戶2", "測試帳戶3"}, {"測試帳戶", "測試帳戶", "測試帳戶", "測試帳戶", "測試帳戶", "測試帳戶", "測試帳戶"}};
        groupList = new ArrayList<GroupEntity>();
        for (int i = 0; i < groupArray.length; i++) {
            GroupEntity groupEntity = new GroupEntity(groupArray[i]);
            List<ChildEntity> childList = new ArrayList<ChildEntity>();
            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildEntity childStatusEntity = new ChildEntity(childTimeArray[i][j]);
                childList.add(childStatusEntity);
            }
            groupEntity.setChildEntities(childList);
            groupList.add(groupEntity);
        }
        return groupList;
    }


    /**
     * v2
     */
    /**
     *
     */
}
