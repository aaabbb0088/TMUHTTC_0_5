package com.test.tonychuang.tmuhttc_0_5.Tab2_friend;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.AsyncTask;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.WriterException;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft1_board.FriendBoardActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map.FriendMapActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.FriendPersonalActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting.FriendSettingActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MySyncingDialog;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyValidator;
import com.test.tonychuang.tmuhttc_0_5.Z_other.QRCode.MyQRCodeCreate;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FAddNotRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FGRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.RowDataFormat.PsnDataSettingRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {


    private ExpandableListView expandableListView;
    private GFExListViewAdapter gfExListViewAdapter;

    private View view;  //Fragment的佈局
    private ActionBar actionBar;
    private SignInShrPref signInShrPref;
    private MyDateSFormat myDateSFormat;
    private DataBase mainDB;
    private ArrayList<FRow> fRows;
    private ArrayList<FGRow> fgRows;
    private ArrayList<ArrayList<FGRow>> fgRowsGroupList;
    private ArrayList<FAddNotRow> fAddSendRows; //自己發出邀請的名單
    private ArrayList<FAddNotRow> fAddRecvRows; //邀請自己的名單


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
        updateView();
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

    private void updateView() {
        gfExListViewAdapter = new GFExListViewAdapter(getActivity(), fgRows, fgRowsGroupList,
                fRows, fAddSendRows, fAddRecvRows);
        expandableListView.setAdapter(gfExListViewAdapter);

        expandableListView.setSelection(0);// 设置默认选中项
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
//        //箭頭移到右邊
//        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
//        expandableListView.setIndicatorBounds(width-70, width-20);
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount - 3; i++) {
            expandableListView.expandGroup(i);
        }
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
        final ImageView avatarImv = (ImageView) dialogView.findViewById(R.id.avatarImv);
        final TextView nameTv = (TextView) dialogView.findViewById(R.id.nameTv);
        final TextView nicknameTv = (TextView) dialogView.findViewById(R.id.nicknameTv);
        final LinearLayout qrLayout = (LinearLayout) dialogView.findViewById(R.id.qrLayout);
        final LinearLayout qrReadLayout = (LinearLayout) dialogView.findViewById(R.id.qrReadLayout);
        final QRCodeReaderView qrDecoderView = (QRCodeReaderView) dialogView.findViewById(R.id.qrDecoderView);
        final LinearLayout qrResultLayout = (LinearLayout) dialogView.findViewById(R.id.qrResultLayout);
        final ImageView qrAvatarImv = (ImageView) dialogView.findViewById(R.id.qrAvatarImv);
        final TextView qrNameTv = (TextView) dialogView.findViewById(R.id.qrNameTv);
        final TextView qrNickNameTv = (TextView) dialogView.findViewById(R.id.qrNickNameTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        final InputMethodManager imm = (InputMethodManager) dialogView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        idLayout.setVisibility(View.GONE);
        qrLayout.setVisibility(View.GONE);
        final int[] addWayFlag = {0};
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
                        addWayFlag[0] = 0;
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
                        addWayFlag[0] = 1;
                        break;
                    case R.id.qrcodeBtn:
                        pidEd.setText("");
                        aidEd.setText("");
                        imm.hideSoftInputFromWindow(aidEd.getWindowToken(), 0);
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
                        addWayFlag[0] = 2;
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

        /**
         * 新增好友
         * (發出邀請端)
         * 1.透過Pid、Aid、QRcode尋找好友
         *   APP端先檢查是否已是好友(不論是否已刪除)，情況分成:未加過->檢查是否曾發出邀請、已是好友->顯示已經是好友、已經刪除->顯示已經刪除好友
         *   若雙方不曾是好友，APP端檢查對方是否已經發出邀請，情況分成:未發出過邀請->call 搜尋好友資料 Webservice、已發出過邀請->等同接受對方邀請
         *   傳出好友AID或好友身分證字號,加好友的方法Flag ; 搜尋是否有這樣的會員 ; 回傳好友AID,好友姓名,好友暱稱,好友頭像 ; 顯示搜尋結果
         * 2.按下確定 : 傳出自己AID、好友AID、加好友的方法(0身分證,1AID,2QRcode) ;
         *             回傳FAddNotRow資料，寫入APPDB，更新頁面資料
         */
        final String[] addFriPid = new String[1];
        final String[] addFriAid = new String[1];

        pidSearchBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                addFriPid[0] = pidEd.getText().toString();
                if (MyValidator.isValidTWPID(addFriPid[0])) {
                    if (!addFriPid[0].equals(signInShrPref.getPID())) {
                        mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                        ArrayList<FRow> fRowArrayList = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                                .whereEquals(FRow.F_FRI_PID, addFriPid[0]));
                        LiteOrm.releaseMemory();
                        if (fRowArrayList.size() != 0) { //已是好友
                            nameTv.setText(fRowArrayList.get(0).getF_name());
//                            avatarImv.setImageResource();
                            confirmTv.setEnabled(false);
                            switch (fRowArrayList.get(0).getF_relation_flag()) {
                                case 0:
                                    nicknameTv.setText(fRowArrayList.get(0).getF_nickname() + " 已是好友");
                                    break;
                                case 1:
                                    nicknameTv.setText("已刪除與 " + fRowArrayList.get(0).getF_nickname() + " 的好友關係");
                                    break;
                            }
                            idResultLayout.setVisibility(View.VISIBLE);
                        } else { //還不是好友
                            final ArrayList<FAddNotRow> fAddNotRowArrayListRecv = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                    .whereEquals(FAddNotRow.FADDNOT_SEND_PID, addFriPid[0]));
                            LiteOrm.releaseMemory();
                            final ArrayList<FAddNotRow> fAddNotRowArrayListSend = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                    .whereEquals(FAddNotRow.FADDNOT_RECV_PID, addFriPid[0]));
                            LiteOrm.releaseMemory();
                            if (fAddNotRowArrayListRecv.size() != 0) { //對方曾發出過邀請訊息
                                nameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name());
                                nicknameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name() + " 曾發送邀請訊息給您,\n是否接受對方邀請?");
                                confirmTv.setEnabled(true);
                                confirmTv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        qrDecoderView.getCameraManager().stopPreview();
                                        alertDialog.dismiss();
                                        agreeInviteAsyncTask(signInShrPref.getAID(), fAddNotRowArrayListRecv.get(0).getFAddNot_send_aid());
                                        //test
                                        Toast.makeText(getActivity(), "接受對方邀請", Toast.LENGTH_SHORT).show();
                                        //test
                                    }
                                });
//                            avatarImv.setImageResource();
                                idResultLayout.setVisibility(View.VISIBLE);
                            } else if (fAddNotRowArrayListSend.size() != 0) { //曾經邀請過對方
                                nameTv.setText(fAddNotRowArrayListSend.get(0).getFAddNot_recv_name());
                                nicknameTv.setText("已對 " + fAddNotRowArrayListSend.get(0).getFAddNot_recv_name() + " 發過邀請");
                                confirmTv.setEnabled(false);
//                            avatarImv.setImageResource();
                                idResultLayout.setVisibility(View.VISIBLE);
                            } else { //對方不曾發出過邀請訊息
                                new AsyncTask<String, Void, ArrayList<PsnDataSettingRow>>() {
                                    @Override
                                    protected ArrayList<PsnDataSettingRow> doInBackground(String... params) {
                                        HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                        JSONParser jsonParser = new JSONParser();
                                        ArrayList<PsnDataSettingRow> psnDataSettingRows = null;

                                        JSONObject jsonObject;
                                        try {
                                            jsonObject = httcjsonapi.SearchAddingFriend(params[0], params[1]);
                                            psnDataSettingRows = jsonParser.parsePsnDataSettingRow(jsonObject);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        return psnDataSettingRows;
                                    }

                                    @Override
                                    protected void onPostExecute(final ArrayList<PsnDataSettingRow> psnDataSettingRows) {
                                        super.onPostExecute(psnDataSettingRows);
                                        if (psnDataSettingRows != null) {
                                            if (psnDataSettingRows.size() != 0) {
                                                nameTv.setText(psnDataSettingRows.get(0).getName());
                                                nicknameTv.setText(psnDataSettingRows.get(0).getNickname());
                                                confirmTv.setEnabled(true);
                                                confirmTv.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        qrDecoderView.getCameraManager().stopPreview();
                                                        alertDialog.dismiss();
                                                        sendInviteAsyncTask(signInShrPref.getAID(),
                                                                psnDataSettingRows.get(0).getAid(),
                                                                addWayFlag[0]);
//                                                        //test
//                                                        Toast.makeText(getActivity(), "發出邀請", Toast.LENGTH_SHORT).show();
//                                                        //test
                                                    }
                                                });
                                            } else { //沒有該會員
                                                nameTv.setText("沒有此會員");
                                                nicknameTv.setText("資料是否輸入錯誤");
                                                confirmTv.setEnabled(false);
                                            }
                                            idResultLayout.setVisibility(View.VISIBLE);
                                        } else {
                                            Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }.execute(addFriPid[0], String.valueOf(addWayFlag[0]));
                            }
                        }
                        mainDB.close();
                    } else {
                        nameTv.setText("自己");
                        nicknameTv.setText("這是自己的帳號");
//                        avatarImv.setImageResource();
                        confirmTv.setEnabled(false);
                        idResultLayout.setVisibility(View.VISIBLE);
                    }
                    imm.hideSoftInputFromWindow(pidEd.getWindowToken(), 0);
                    pidEd.clearFocus();
                } else {
                    pidEd.setError("身分證字號格式錯誤");
                }
            }
        });

        aidSearchBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                addFriAid[0] = aidEd.getText().toString();
                if (!addFriAid[0].equals(signInShrPref.getAID())) {
                    mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    ArrayList<FRow> fRowArrayList = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                            .whereEquals(FRow.F_FRI_AID, addFriAid[0]));
                    LiteOrm.releaseMemory();
                    if (fRowArrayList.size() != 0) { //已是好友
                        nameTv.setText(fRowArrayList.get(0).getF_name());
//                    avatarImv.setImageResource();
                        confirmTv.setEnabled(false);
                        switch (fRowArrayList.get(0).getF_relation_flag()) {
                            case 0:
                                nicknameTv.setText(fRowArrayList.get(0).getF_nickname() + " 已是好友");
                                break;
                            case 1:
                                nicknameTv.setText("已刪除與 " + fRowArrayList.get(0).getF_nickname() + " 的好友關係");
                                break;
                        }
                        idResultLayout.setVisibility(View.VISIBLE);
                    } else { //還不是好友
                        final ArrayList<FAddNotRow> fAddNotRowArrayListRecv = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                .whereEquals(FAddNotRow.FADDNOT_SEND_AID, addFriAid[0]));
                        LiteOrm.releaseMemory();
                        final ArrayList<FAddNotRow> fAddNotRowArrayListSend = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                .whereEquals(FAddNotRow.FADDNOT_RECV_AID, addFriAid[0]));
                        LiteOrm.releaseMemory();
                        if (fAddNotRowArrayListRecv.size() != 0) { //對方曾發出過邀請訊息
                            nameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name());
                            nicknameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name() + " 曾發送邀請訊息給您,\n是否接受對方邀請?");
                            confirmTv.setEnabled(true);
                            confirmTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    qrDecoderView.getCameraManager().stopPreview();
                                    alertDialog.dismiss();
                                    agreeInviteAsyncTask(signInShrPref.getAID(), addFriAid[0]);
                                    //test
                                    Toast.makeText(getActivity(), "接受對方邀請", Toast.LENGTH_SHORT).show();
                                    //test
                                }
                            });
//                            avatarImv.setImageResource();
                            idResultLayout.setVisibility(View.VISIBLE);
                        } else if (fAddNotRowArrayListSend.size() != 0) { //曾經邀請過對方
                            nameTv.setText(fAddNotRowArrayListSend.get(0).getFAddNot_recv_name());
                            nicknameTv.setText("已對 " + fAddNotRowArrayListSend.get(0).getFAddNot_recv_name() + " 發過邀請");
                            confirmTv.setEnabled(false);
//                            avatarImv.setImageResource();
                            idResultLayout.setVisibility(View.VISIBLE);
                        } else { //對方不曾發出過邀請訊息
                            new AsyncTask<String, Void, ArrayList<PsnDataSettingRow>>() {
                                @Override
                                protected ArrayList<PsnDataSettingRow> doInBackground(String... params) {
                                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                    JSONParser jsonParser = new JSONParser();
                                    ArrayList<PsnDataSettingRow> psnDataSettingRows = null;

                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = httcjsonapi.SearchAddingFriend(params[0], params[1]);
                                        psnDataSettingRows = jsonParser.parsePsnDataSettingRow(jsonObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return psnDataSettingRows;
                                }

                                @Override
                                protected void onPostExecute(final ArrayList<PsnDataSettingRow> psnDataSettingRows) {
                                    super.onPostExecute(psnDataSettingRows);
                                    if (psnDataSettingRows != null) {
                                        if (psnDataSettingRows.size() != 0) {
                                            nameTv.setText(psnDataSettingRows.get(0).getName());
                                            nicknameTv.setText(psnDataSettingRows.get(0).getNickname());
                                            confirmTv.setEnabled(true);
                                            confirmTv.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    qrDecoderView.getCameraManager().stopPreview();
                                                    alertDialog.dismiss();
                                                    sendInviteAsyncTask(signInShrPref.getAID(),
                                                            addFriAid[0], addWayFlag[0]);
//                                                    //test
//                                                    Toast.makeText(getActivity(), "發出邀請", Toast.LENGTH_SHORT).show();
//                                                    //test
                                                }
                                            });
                                        } else { //沒有該會員
                                            nameTv.setText("沒有此會員");
                                            nicknameTv.setText("資料是否輸入錯誤");
                                            confirmTv.setEnabled(false);
                                        }
                                        idResultLayout.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }.execute(addFriAid[0], String.valueOf(addWayFlag[0]));
                        }
                    }
                    mainDB.close();
                } else {
                    nameTv.setText("自己");
                    nicknameTv.setText("這是自己的帳號");
//                    avatarImv.setImageResource();
                    confirmTv.setEnabled(false);
                    idResultLayout.setVisibility(View.VISIBLE);
                }
                imm.hideSoftInputFromWindow(aidEd.getWindowToken(), 0);
                aidEd.clearFocus();
            }
        });

        qrDecoderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onQRCodeRead(final String text, PointF[] points) {
                qrDecoderView.setVisibility(View.GONE);
                qrDecoderView.getCameraManager().startPreview();
                qrReadLayout.setVisibility(View.GONE);
                if (!text.equals(signInShrPref.getAID())) {
                    mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    ArrayList<FRow> fRowArrayList = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                            .whereEquals(FRow.F_FRI_AID, text));
                    LiteOrm.releaseMemory();
                    if (fRowArrayList.size() != 0) { //已是好友
                        qrNameTv.setText(fRowArrayList.get(0).getF_name());
//                        qrAvatarImv.setImageResource();
                        confirmTv.setEnabled(false);
                        switch (fRowArrayList.get(0).getF_relation_flag()) {
                            case 0:
                                qrNickNameTv.setText(fRowArrayList.get(0).getF_nickname() + " 已是好友");
                                break;
                            case 1:
                                qrNickNameTv.setText("已刪除與 " + fRowArrayList.get(0).getF_nickname() + " 的好友關係");
                                break;
                        }
                        qrResultLayout.setVisibility(View.VISIBLE);
                    } else { //還不是好友
                        final ArrayList<FAddNotRow> fAddNotRowArrayListRecv = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                .whereEquals(FAddNotRow.FADDNOT_SEND_AID, text));
                        LiteOrm.releaseMemory();
                        final ArrayList<FAddNotRow> fAddNotRowArrayListSend = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                                .whereEquals(FAddNotRow.FADDNOT_RECV_AID, text));
                        LiteOrm.releaseMemory();
                        if (fAddNotRowArrayListRecv.size() != 0) { //對方曾發出過邀請訊息
                            qrNameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name());
                            qrNickNameTv.setText(fAddNotRowArrayListRecv.get(0).getFAddNot_send_name() + " 曾發送邀請訊息給您,\n是否接受對方邀請?");
                            confirmTv.setEnabled(true);
                            confirmTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    qrDecoderView.getCameraManager().stopPreview();
                                    alertDialog.dismiss();
                                    agreeInviteAsyncTask(signInShrPref.getAID(), text);
                                    //test
                                    Toast.makeText(getActivity(), "接受對方邀請", Toast.LENGTH_SHORT).show();
                                    //test
                                }
                            });
//                            qrAvatarImv.setImageResource();
                            qrResultLayout.setVisibility(View.VISIBLE);
                        } else if (fAddNotRowArrayListSend.size() != 0) { //曾經邀請過對方
                            qrNameTv.setText(fAddNotRowArrayListSend.get(0).getFAddNot_recv_name());
                            qrNickNameTv.setText("已對 " + fAddNotRowArrayListSend.get(0).getFAddNot_recv_name() + " 發過邀請");
                            confirmTv.setEnabled(false);
//                            avatarImv.setImageResource();
                            qrResultLayout.setVisibility(View.VISIBLE);
                        } else { //對方不曾發出過邀請訊息
                            new AsyncTask<String, Void, ArrayList<PsnDataSettingRow>>() {
                                @Override
                                protected ArrayList<PsnDataSettingRow> doInBackground(String... params) {
                                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                                    JSONParser jsonParser = new JSONParser();
                                    ArrayList<PsnDataSettingRow> psnDataSettingRows = null;

                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = httcjsonapi.SearchAddingFriend(params[0], params[1]);
                                        psnDataSettingRows = jsonParser.parsePsnDataSettingRow(jsonObject);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return psnDataSettingRows;
                                }

                                @Override
                                protected void onPostExecute(ArrayList<PsnDataSettingRow> psnDataSettingRows) {
                                    super.onPostExecute(psnDataSettingRows);
                                    if (psnDataSettingRows != null) {
                                        if (psnDataSettingRows.size() != 0) {
                                            qrNameTv.setText(psnDataSettingRows.get(0).getName());
                                            qrNickNameTv.setText(psnDataSettingRows.get(0).getNickname());
                                            confirmTv.setEnabled(true);
                                            confirmTv.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    qrDecoderView.getCameraManager().stopPreview();
                                                    alertDialog.dismiss();
                                                    sendInviteAsyncTask(signInShrPref.getAID(), text, addWayFlag[0]);
//                                                    //test
//                                                    Toast.makeText(getActivity(), "發出邀請", Toast.LENGTH_SHORT).show();
//                                                    //test
                                                }
                                            });
                                        } else { //沒有該會員
                                            qrNameTv.setText("沒有此會員");
                                            qrNickNameTv.setText("資料是否輸入錯誤");
                                            confirmTv.setEnabled(false);
                                        }
                                        qrResultLayout.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }.execute(text, String.valueOf(addWayFlag[0]));
                        }
                    }
                    mainDB.close();
                } else {
                    qrNameTv.setText("自己");
                    qrNickNameTv.setText("這是自己的帳號");
//                    qrAvatarImv.setImageResource();
                    confirmTv.setEnabled(false);
                    qrResultLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void cameraNotFound() {

            }

            @Override
            public void QRCodeNotFoundOnCamImage() {

            }
        });
    }

    private void addGroupAlertDialog() {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_group_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_group_body, null);
        final MaterialEditText groupNameEd = (MaterialEditText) dialogView.findViewById(R.id.groupNameEd);
        final TextView countTv = (TextView) dialogView.findViewById(R.id.countTv);
        ListView friLv = (ListView) dialogView.findViewById(R.id.friLv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final InputMethodManager imm = (InputMethodManager) dialogView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();

        final boolean[] booleen = {false, false};
        final int[] checkNum = new int[1];
        friLv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    groupNameEd.clearFocus();
                    imm.hideSoftInputFromWindow(groupNameEd.getWindowToken(), 0);
                }
                return false;
            }
        });
        final AddGroupAdapter addGroupAdapter = new AddGroupAdapter(getActivity(), fRows, checkNum);
        setConfirmTv(confirmTv, booleen);
        friLv.setAdapter(addGroupAdapter);
        friLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder對象，這樣就省去了通過層層的findViewById去實例化我們需要的cb實例的步驟
                AddGroupAdapter.ViewHolder holder = (AddGroupAdapter.ViewHolder) view.getTag();
                // 改變CheckBox的狀態
                holder.item_cb.toggle();
                // 將CheckBox的選中狀況記錄下來
                addGroupAdapter.getIsSelected().put(position, holder.item_cb.isChecked());
                // 調整選定條目
                if (holder.item_cb.isChecked()) {
                    checkNum[0]++;
                } else {
                    checkNum[0]--;
                }
                // 用TextView顯示
                countTv.setText("已選中" + checkNum[0] + "項");
                booleen[0] = checkNum[0] != 0;
                setConfirmTv(confirmTv, booleen);
            }
        });

        groupNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(groupNameEd.getText().toString().trim())) {
                    booleen[1] = true;
                    setConfirmTv(confirmTv, booleen);
                } else {
                    booleen[1] = false;
                    setConfirmTv(confirmTv, booleen);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        groupNameEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    groupNameEd.setError(null);
                }
            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = groupNameEd.getText().toString();
                Boolean aboolen = false;
                for (int i = 0; i < fgRows.size(); i++) {
                    if (groupName.equals(fgRows.get(i).getFG_group_name())) {
                        aboolen = true;
                        break;
                    }
                }
                if (aboolen) {
                    groupNameEd.setError("\"" + groupName + "\" 群組已存在");
                } else {
                    addGroupAndUpdateView(groupName, addGroupAdapter.getIsSelected());
                    alertDialog.dismiss();
                }
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
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
            qrcodeImv.setImageBitmap(new MyQRCodeCreate(signInShrPref.getAID()).getBitmap());
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

    @SuppressLint("SetTextI18n")
    private void replyFriendInviteDialog(String name, final String friAid) {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_reply_add_friend_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_reply_add_friend_body, null);
        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
        TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView ignoreTv = (TextView) dialogView.findViewById(R.id.ignoreTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);

        msgTv.setText("是否接受 " + name + " 的好友邀請?\n");

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreeInviteAsyncTask(signInShrPref.getAID(), friAid);
                alertDialog.dismiss();
            }
        });
        ignoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refuseInviteAsyncTask(signInShrPref.getAID(), friAid);
                alertDialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void editGroupAlertDialog(String groupName, ArrayList<FGRow> fGroupAids, final int groupPosition) {
        View dialogTitleView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_group_title, null);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_group_body, null);
        final MaterialEditText groupNameEd = (MaterialEditText) dialogView.findViewById(R.id.groupNameEd);
        final TextView countTv = (TextView) dialogView.findViewById(R.id.countTv);
        ListView friLv = (ListView) dialogView.findViewById(R.id.friLv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final InputMethodManager imm = (InputMethodManager) dialogView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        final boolean[] booleen = {false, false};
        final int[] checkNum = new int[1];
        final HashMap<Integer, Boolean> beforeChangeMap = new HashMap<>();
        friLv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    groupNameEd.clearFocus();
                    imm.hideSoftInputFromWindow(groupNameEd.getWindowToken(), 0);
                }
                return false;
            }
        });
        final EditGroupAdapter editGroupAdapter = new EditGroupAdapter(getActivity(), fRows, fGroupAids, checkNum);
        beforeChangeMap.putAll(editGroupAdapter.getIsSelected());
        friLv.setAdapter(editGroupAdapter);
        countTv.setText("已選中" + checkNum[0] + "項");
        friLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder對象，這樣就省去了通過層層的findViewById去實例化我們需要的cb實例的步驟
                EditGroupAdapter.ViewHolder holder = (EditGroupAdapter.ViewHolder) view.getTag();
                // 改變CheckBox的狀態
                holder.item_cb.toggle();
                // 將CheckBox的選中狀況記錄下來
                editGroupAdapter.getIsSelected().put(position, holder.item_cb.isChecked());
                // 調整選定條目
                if (holder.item_cb.isChecked()) {
                    checkNum[0]++;
                } else {
                    checkNum[0]--;
                }
                // 用TextView顯示
                countTv.setText("已選中" + checkNum[0] + "項");
                booleen[0] = checkNum[0] != 0;
                setConfirmTv(confirmTv, booleen);
            }
        });

        booleen[0] = checkNum[0] != 0;
        booleen[1] = true;
        setConfirmTv(confirmTv, booleen);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        groupNameEd.setHint(groupName);
        groupNameEd.setText(groupName);
        groupNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(groupNameEd.getText().toString().trim())) {
                    booleen[1] = true;
                    setConfirmTv(confirmTv, booleen);
                } else {
                    booleen[1] = false;
                    setConfirmTv(confirmTv, booleen);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        groupNameEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    groupNameEd.setError(null);
                }
            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean aboolen = false;
                String beforeChangeName = fgRows.get(groupPosition).getFG_group_name();
                String afterChangeName = groupNameEd.getText().toString();
                ArrayList<FGRow> fgRowArrayList = new ArrayList<>();
                fgRowArrayList.addAll(fgRows);

                //test
//                String s = "";
//                for (int i = 0; i < fgRows.size(); i++) {
//                    s = s + String.valueOf(fgRows.get(i).getId()) + " , ";
//                }
//                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();


                fgRowArrayList.remove(groupPosition);
                for (int i = 0; i < fgRowArrayList.size(); i++) {
                    if (afterChangeName.equals(fgRowArrayList.get(i).getFG_group_name())) {
                        aboolen = true;
                        break;
                    }
                }
                if (aboolen) {
                    groupNameEd.setError("\"" + afterChangeName + "\" 群組已存在");
                } else {
                    if (!compareSameMap(beforeChangeMap, editGroupAdapter.getIsSelected())
                            || !beforeChangeName.equals(afterChangeName)) {
//                        Toast.makeText(getActivity(), "diff", Toast.LENGTH_SHORT).show();
                        editGroupAndUpdateView(beforeChangeName, afterChangeName, editGroupAdapter.getIsSelected());
                    }
                    alertDialog.dismiss();
                }
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void deletGroupCheckAlertDialog(final String groupName) {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_friend_group_body, null);
        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("刪除群組")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        msgTv.setText("確定要刪除 \"" + groupName + "\" 群組 嗎?");
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGroupAndUpdateView(groupName);
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

    @SuppressLint("SetTextI18n")
    private void deletfriendCheckAlertDialog(String friendName, final String friAid) {
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_friend_group_body, null);
        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("刪除好友")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        msgTv.setText("確定要刪除 \"" + friendName + "\" 嗎?");
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefriendAndUpdateView(friAid);
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

//    @SuppressLint("SetTextI18n")
//    private void deletfriendInGroupCheckAlertDialog(String friendName, String groupName) {
//        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_friend_group_body, null);
//        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
//        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
//        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
//        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
//                .setTitle("將 " + friendName + " 移除群組")
//                .setView(dialogView)
//                .setOnKeyListener(getOnKeyListener())
//                .setCancelable(false).create();
//        alertDialog.show();
//
//        msgTv.setText("確定要將 \"" + friendName + "\" \n從 \"" + groupName + "\" 群組中移除嗎?");
//        confirmTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        cancelTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//    }

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


    private void setConfirmTv(TextView confirmTv, boolean[] booleen) {
        if (booleen[0] && booleen[1]) {
            confirmTv.setEnabled(true);
        } else {
            confirmTv.setEnabled(false);
        }
    }

    private void addGroupAndUpdateView(final String groupName, HashMap<Integer, Boolean> isSelected) {
        String aids = "";
        final ArrayList<String> groupAidsList = new ArrayList<>();
        for (int i = 0; i < isSelected.size(); i++) {
            if (isSelected.get(i)) {
                aids = aids + fRows.get(i).getF_fri_aid() + ",";
                groupAidsList.add(fRows.get(i).getF_fri_aid());
            }
        }
        aids = aids.substring(0, aids.length() - 1);
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aboolean = false;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.AddFriendGroup(params[0], params[1], params[2]);
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
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    for (int i = 0; i < groupAidsList.size(); i++) {
                        mainDB.save(new FGRow(groupName, groupAidsList.get(i)));
                    }
                    LiteOrm.releaseMemory();
                    fgRows.clear();
                    fgRows.addAll(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                            .groupBy(FGRow.FG_GROUP_NAME)
                            .appendOrderAscBy(FGRow.FG_GROUP_NAME)));
                    LiteOrm.releaseMemory();
                    fgRowsGroupList.clear();
                    for (int i = 0; i < fgRows.size(); i++) {
                        fgRowsGroupList.add(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                                .whereEquals(FGRow.FG_GROUP_NAME, fgRows.get(i).getFG_group_name())
                                .appendOrderAscBy(FGRow.ID)));
                    }
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    gfExListViewAdapter.notifyDataSetChanged();
                    int count;
                    for (count = 0; count < fgRows.size(); count++) {
                        if (fgRows.get(count).getFG_group_name().equals(groupName)) {
                            break;
                        }
                    }
                    expandableListView.expandGroup(count);
                    expandableListView.setSelectedGroup(count);
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(signInShrPref.getAID(), groupName, aids);
    }

    private void editGroupAndUpdateView(final String beforeChangeName, final String afterChangeName, HashMap<Integer, Boolean> isSelected) {
        String aids = "";
        final ArrayList<String> groupAidsList = new ArrayList<>();
        for (int i = 0; i < isSelected.size(); i++) {
            if (isSelected.get(i)) {
                aids = aids + fRows.get(i).getF_fri_aid() + ",";
                groupAidsList.add(fRows.get(i).getF_fri_aid());
            }
        }
        aids = aids.substring(0, aids.length() - 1);
        final String finalAids = aids;
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aboolean = false;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.EditFriendGroup(params[0], params[1], params[2], params[3]);
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
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    mainDB.delete(new WhereBuilder(FGRow.class).andEquals(FGRow.FG_GROUP_NAME, beforeChangeName));
                    for (int i = 0; i < groupAidsList.size(); i++) {
                        mainDB.save(new FGRow(afterChangeName, groupAidsList.get(i)));
                    }
                    LiteOrm.releaseMemory();
                    fgRows.clear();
                    fgRows.addAll(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                            .groupBy(FGRow.FG_GROUP_NAME)
                            .appendOrderAscBy(FGRow.FG_GROUP_NAME)));
                    LiteOrm.releaseMemory();
                    fgRowsGroupList.clear();
                    for (int i = 0; i < fgRows.size(); i++) {
                        fgRowsGroupList.add(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                                .whereEquals(FGRow.FG_GROUP_NAME, fgRows.get(i).getFG_group_name())
                                .appendOrderAscBy(FGRow.ID)));
                    }
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    gfExListViewAdapter.notifyDataSetChanged();
                    int count;
                    for (count = 0; count < fgRows.size(); count++) {
                        if (fgRows.get(count).getFG_group_name().equals(afterChangeName)) {
                            break;
                        }
                    }
                    expandableListView.expandGroup(count);
                    expandableListView.setSelectedGroup(count);
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(signInShrPref.getAID(), beforeChangeName, afterChangeName, aids);
    }

    private void deleteGroupAndUpdateView(final String groupName) {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aboolean = false;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.DeleteFriendGroup(params[0], params[1]);
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
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    mainDB.delete(new WhereBuilder(FGRow.class).andEquals(FGRow.FG_GROUP_NAME, groupName));
                    LiteOrm.releaseMemory();
                    fgRows.clear();
                    fgRows.addAll(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                            .groupBy(FGRow.FG_GROUP_NAME)
                            .appendOrderAscBy(FGRow.FG_GROUP_NAME)));
                    LiteOrm.releaseMemory();
                    fgRowsGroupList.clear();
                    for (int i = 0; i < fgRows.size(); i++) {
                        fgRowsGroupList.add(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                                .whereEquals(FGRow.FG_GROUP_NAME, fgRows.get(i).getFG_group_name())
                                .appendOrderAscBy(FGRow.ID)));
                    }
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    gfExListViewAdapter.notifyDataSetChanged();
                    for (int i = 0; i < gfExListViewAdapter.getGroupCount(); i++) {
                        expandableListView.collapseGroup(i);
                    }
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(signInShrPref.getAID(), groupName);
    }

    private void deletefriendAndUpdateView(final String friAid) {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aboolean = false;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.DeleteFriend(params[0], params[1]);
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
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    ArrayList<FRow> fRowsTmp = mainDB.query(new QueryBuilder<FRow>(FRow.class).whereEquals(FRow.F_FRI_AID, friAid));
                    fRowsTmp.get(0).setF_relation_flag(1);
                    mainDB.update(fRowsTmp.get(0));
                    LiteOrm.releaseMemory();
                    mainDB.delete(new WhereBuilder(FGRow.class).equals(FGRow.FG_FRI_AID, friAid));
                    LiteOrm.releaseMemory();
                    fRows.clear();
                    fRows.addAll(mainDB.query(new QueryBuilder<FRow>(FRow.class)
                            .whereEquals(FRow.F_RELATION_FLAG, 0)
                            .appendOrderAscBy(FRow.F_NAME)));
                    LiteOrm.releaseMemory();
                    fgRows.clear();
                    fgRows.addAll(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                            .groupBy(FGRow.FG_GROUP_NAME)
                            .appendOrderAscBy(FGRow.FG_GROUP_NAME)));
                    LiteOrm.releaseMemory();
                    fgRowsGroupList.clear();
                    for (int i = 0; i < fgRows.size(); i++) {
                        fgRowsGroupList.add(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                                .whereEquals(FGRow.FG_GROUP_NAME, fgRows.get(i).getFG_group_name())
                                .appendOrderAscBy(FGRow.ID)));
                    }
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    gfExListViewAdapter.notifyDataSetChanged();

                    //測試是否調整gfExListViewAdapter開啟收起


                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後在嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(signInShrPref.getAID(), friAid);
    }

    /**
     * 用map的entrySet()的迭代器(性能效率較高)
     */
    private boolean compareSameMap(HashMap<Integer, Boolean> hm1, HashMap<Integer, Boolean> hm2) {
        boolean bool = false;
        int sameCount = 0;
        Iterator<Map.Entry<Integer, Boolean>> iter1 = hm1.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry<Integer, Boolean> entry1 = iter1.next();
            Boolean m1value = entry1.getValue();
            Boolean m2value = hm2.get(entry1.getKey());

            if (m1value.equals(m2value)) {//若兩個map中相同key對應的value相等
                sameCount++;
            }
        }
        if (sameCount == hm1.size()) {
            bool = true;
        }
        return bool;
    }


    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        signInShrPref = new SignInShrPref(getActivity());
        myDateSFormat = new MyDateSFormat();
        mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());

        fgRows = mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                .groupBy(FGRow.FG_GROUP_NAME)
                .appendOrderAscBy(FGRow.FG_GROUP_NAME));
        LiteOrm.releaseMemory();
        fgRowsGroupList = new ArrayList<>();
        for (int i = 0; i < fgRows.size(); i++) {
            fgRowsGroupList.add(mainDB.query(new QueryBuilder<FGRow>(FGRow.class)
                    .whereEquals(FGRow.FG_GROUP_NAME, fgRows.get(i).getFG_group_name())
                    .appendOrderAscBy(FGRow.ID)));
            LiteOrm.releaseMemory();
        }
        fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                .whereEquals(FRow.F_RELATION_FLAG, 0)
                .appendOrderAscBy(FRow.F_NAME));
        LiteOrm.releaseMemory();

        fAddSendRows = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                .whereEquals(FAddNotRow.FADDNOT_SEND_AID, signInShrPref.getAID())
                .whereAppendAnd()
                .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME));
        fAddRecvRows = mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                .whereEquals(FAddNotRow.FADDNOT_RECV_AID, signInShrPref.getAID())
                .whereAppendAnd()
                .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME));

        mainDB.close();
    }

    private class GFExListViewAdapter extends BaseExpandableListAdapter {
        private LayoutInflater myInflater;
        private Context context;
        private ArrayList<FGRow> fgRows;
        private ArrayList<ArrayList<FGRow>> fgRowsGroupList;
        private ArrayList<FRow> fRows;
        private ArrayList<FAddNotRow> fAddSendRows;
        private ArrayList<FAddNotRow> fAddRecvRows;

        public GFExListViewAdapter(Context context, ArrayList<FGRow> fgRows,
                                   ArrayList<ArrayList<FGRow>> fgRowsGroupList, ArrayList<FRow> fRows,
                                   ArrayList<FAddNotRow> fAddSendRows,
                                   ArrayList<FAddNotRow> fAddRecvRows) {
            myInflater = LayoutInflater.from(context);
            this.context = context;
            this.fgRows = fgRows;
            this.fgRowsGroupList = fgRowsGroupList;
            this.fRows = fRows;
            this.fAddSendRows = fAddSendRows;
            this.fAddRecvRows = fAddRecvRows;
        }

        @Override
        public int getGroupCount() {
            return fgRows.size() + 3;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition < fgRows.size()) {
                return fgRowsGroupList.get(groupPosition).size();
            } else if (groupPosition == fgRows.size()) {
                return fRows.size();
            } else if (groupPosition == fgRows.size() + 1) {
                return fAddSendRows.size();
            } else {
                return fAddRecvRows.size();
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            if (groupPosition < fgRows.size()) {
                return fgRowsGroupList.get(groupPosition);
            } else if (groupPosition == fgRows.size()) {
                return fRows;
            } else if (groupPosition == fgRows.size() + 1) {
                return fAddSendRows;
            } else {
                return fAddRecvRows;
            }
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (groupPosition < fgRows.size()) {
                return fgRowsGroupList.get(groupPosition).get(childPosition);
            } else if (groupPosition == fgRows.size()) {
                return fRows.get(childPosition);
            } else if (groupPosition == fgRows.size() + 1) {
                return fAddSendRows.get(childPosition);
            } else {
                return fAddRecvRows.get(childPosition);
            }
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getGroupView(final int groupPosition, final boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            final GroupViewHolder holder;
            if (convertView != null) {
                holder = (GroupViewHolder) convertView.getTag();
            } else {
                holder = new GroupViewHolder();
                convertView = myInflater.inflate(R.layout.friend_listview_item_group, null);
                holder.groupName = (TextView) convertView.findViewById(R.id.one_status_name);
                holder.groupCount = (TextView) convertView.findViewById(R.id.groupCount);
                holder.exFlagIv = (ImageView) convertView.findViewById(R.id.exFlagIv);
                holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
                holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewWithTag("Edit"));
                holder.item_surface = (LinearLayout) convertView.findViewById(R.id.item_surface);
                holder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
                holder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
                convertView.setTag(holder);
            }

            final String groupName;
            if (groupPosition < fgRows.size()) {
                holder.swipeLayout.setSwipeEnabled(true);
                groupName = fgRows.get(groupPosition).getFG_group_name();
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fgRowsGroupList.get(groupPosition).size() + ")");
                holder.item_surface.setBackgroundColor(Color.TRANSPARENT);
            } else if (groupPosition == fgRows.size()) {
                holder.swipeLayout.setSwipeEnabled(false);
                groupName = "全部好友";
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fRows.size() + ")");
                holder.item_surface.setBackgroundColor(Color.parseColor("#3CB371"));
            } else if (groupPosition == fgRows.size() + 1) {
                holder.swipeLayout.setSwipeEnabled(false);
                groupName = "邀請中的好友";
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fAddSendRows.size() + ")");
                holder.item_surface.setBackgroundColor(Color.parseColor("#3CB371"));
            } else {
                holder.swipeLayout.setSwipeEnabled(false);
                groupName = "可能認識的好友";
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fAddRecvRows.size() + ")");
                holder.item_surface.setBackgroundColor(Color.parseColor("#3CB371"));
            }
            if (isExpanded) {
                holder.exFlagIv.setImageResource(R.drawable.arrow_down);
            } else {
                holder.exFlagIv.setImageResource(R.drawable.arrow_up);
            }


            /*****設定Btn*****/
            holder.item_surface.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isExpanded) {
                        expandableListView.collapseGroup(groupPosition);
                    } else {
                        expandableListView.expandGroup(groupPosition);
                    }
                    notifyDataSetChanged();
                }
            });

            holder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (groupPosition < fgRows.size()) {
                        editGroupAlertDialog(groupName, fgRowsGroupList.get(groupPosition), groupPosition);
                    }
                    holder.swipeLayout.close();
                }
            });

            holder.iv_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletGroupCheckAlertDialog(groupName);
                }
            });

            return convertView;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            final ChildViewHolder viewHolder;
            OnClick listener;
            if (convertView != null) {
                viewHolder = (ChildViewHolder) convertView.getTag();
                listener = (OnClick) convertView.getTag(viewHolder.item_surface.getId());//重新獲得監聽對象
            } else {
                viewHolder = new ChildViewHolder();
                convertView = myInflater.inflate(R.layout.friend_listview_item_child, null);
                viewHolder.childTitle = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.lastDateTv = (TextView) convertView.findViewById(R.id.lastDateTv);
                viewHolder.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
                viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
                viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewWithTag("Edit"));
                viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewWithTag("Map"));
                viewHolder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
                viewHolder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
                viewHolder.iv_map = (ImageView) convertView.findViewById(R.id.map);
                viewHolder.memberFlagIv = (ImageView) convertView.findViewById(R.id.memberFlagIv);
                viewHolder.item_surface = (LinearLayout) convertView.findViewById(R.id.item_surface);
                listener = new OnClick();//在這裏新建監聽對象
                viewHolder.item_surface.setOnClickListener(listener);
                convertView.setTag(viewHolder);
                convertView.setTag(viewHolder.item_surface.getId(), listener);//對監聽對象保存
            }

            boolean memberFlag = false;
            String lastDateStr = "";
            final String friendNickName;
            if (groupPosition < fgRows.size()) { //群組部分
                DataBase mainDB = LiteOrm.newSingleInstance(context, signInShrPref.getAID());
                ArrayList<FRow> fRowArrayList = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                        .whereEquals(FRow.F_FRI_AID, fgRowsGroupList.get(groupPosition).get(childPosition).getFG_fri_aid()));
                LiteOrm.releaseMemory();
                if (fRowArrayList.get(0).getF_member_flag().equals("Y")) { //群組部分，是遠距會員，可能有最後量測時間
                    memberFlag = true;
                    ArrayList<PreDataRow> preDataRows = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                            .whereEquals(PreDataRow.PDATA_SID, fRowArrayList.get(0).getF_fri_sid())
                            .appendOrderDescBy(PreDataRow.PDATA_DATETIME)
                            .limit(0, 1));
                    LiteOrm.releaseMemory();
                    ArrayList<GlyDataRow> glyDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                            .whereEquals(GlyDataRow.GDATA_SID, fRowArrayList.get(0).getF_fri_sid())
                            .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                            .limit(0, 1));
                    LiteOrm.releaseMemory();
                    Date lastPreDate = new Date();
                    Date lastGlyDate = new Date();
                    if (preDataRows.size() != 0 && glyDataRows.size() != 0) {
                        try {
                            lastPreDate = myDateSFormat.getFrmt_yMdHm().parse(preDataRows.get(0).getPData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            lastGlyDate = myDateSFormat.getFrmt_yMdHm().parse(glyDataRows.get(0).getGData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (lastPreDate.after(lastGlyDate)) {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                        } else {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                        }
                    } else if (preDataRows.size() != 0) {
                        try {
                            lastPreDate = myDateSFormat.getFrmt_yMdHm().parse(preDataRows.get(0).getPData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                    } else if (glyDataRows.size() != 0) {
                        try {
                            lastGlyDate = myDateSFormat.getFrmt_yMdHm().parse(glyDataRows.get(0).getGData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                    }
                    if (!"".equals(lastDateStr)) {
                        lastDateStr = "最近量測時間 " + lastDateStr;
                    }
                    listener.setOnClick("groupSelf", true, childPosition);
                    viewHolder.item_surface.setEnabled(true);
                } else { //群組部分，非遠距會員
                    listener.setOnClick("groupSelf", false, childPosition);
                    viewHolder.item_surface.setEnabled(false);
                }
                mainDB.close();
                friendNickName = fRowArrayList.get(0).getF_nickname();
                viewHolder.iv_trash.setVisibility(View.GONE);
                viewHolder.swipeLayout.setSwipeEnabled(true);
            } else if (groupPosition == fgRows.size()) { //所有好友部分
                friendNickName = fRows.get(childPosition).getF_nickname();
                if (fRows.get(childPosition).getF_member_flag().equals("Y")) { //所有好友部分，是遠距會員
                    memberFlag = true;
                    DataBase mainDB = LiteOrm.newSingleInstance(context, signInShrPref.getAID());
                    ArrayList<PreDataRow> preDataRows = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                            .whereEquals(PreDataRow.PDATA_SID, fRows.get(childPosition).getF_fri_sid())
                            .appendOrderDescBy(PreDataRow.PDATA_DATETIME)
                            .limit(0, 1));
                    LiteOrm.releaseMemory();
                    ArrayList<GlyDataRow> glyDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                            .whereEquals(GlyDataRow.GDATA_SID, fRows.get(childPosition).getF_fri_sid())
                            .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                            .limit(0, 1));
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    Date lastPreDate = new Date();
                    Date lastGlyDate = new Date();
                    if (preDataRows.size() != 0 && glyDataRows.size() != 0) {
                        try {
                            lastPreDate = myDateSFormat.getFrmt_yMdHm().parse(preDataRows.get(0).getPData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            lastGlyDate = myDateSFormat.getFrmt_yMdHm().parse(glyDataRows.get(0).getGData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (lastPreDate.after(lastGlyDate)) {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                        } else {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                        }
                    } else if (preDataRows.size() != 0) {
                        try {
                            lastPreDate = myDateSFormat.getFrmt_yMdHm().parse(preDataRows.get(0).getPData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                    } else if (glyDataRows.size() != 0) {
                        try {
                            lastGlyDate = myDateSFormat.getFrmt_yMdHm().parse(glyDataRows.get(0).getGData_datetime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                    }
                    if (!"".equals(lastDateStr.trim())) {
                        lastDateStr = "最近量測時間 " + lastDateStr;
                    }
                    listener.setOnClick("allFriend", true, childPosition);
                    viewHolder.item_surface.setEnabled(true);
                } else { //所有好友部分，非遠距會員
                    listener.setOnClick("allFriend", false, childPosition);
                    viewHolder.item_surface.setEnabled(false);
                }
                viewHolder.iv_trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deletfriendCheckAlertDialog(friendNickName, fRows.get(childPosition).getF_fri_aid());
                    }
                });
                viewHolder.iv_trash.setVisibility(View.VISIBLE);
                viewHolder.swipeLayout.setSwipeEnabled(true);
            } else if (groupPosition == fgRows.size() + 1) { //自己邀請的好友
                friendNickName = fAddSendRows.get(childPosition).getFAddNot_recv_name();
                switch (fAddSendRows.get(childPosition).getFAddNot_add_way()) {
                    case 0:
                        lastDateStr = "您透過 身分證字號 邀請對方成為好友";
                        break;
                    case 1:
                        lastDateStr = "您透過 APP系統編碼 邀請對方成為好友";
                        break;
                    case 2:
                        lastDateStr = "您透過 QRCode 邀請對方成為好友";
                        break;
                }
                viewHolder.item_surface.setEnabled(false);
                viewHolder.swipeLayout.setSwipeEnabled(false);
            } else { //邀請自己的好友
                friendNickName = fAddRecvRows.get(childPosition).getFAddNot_send_name();
                switch (fAddRecvRows.get(childPosition).getFAddNot_add_way()) {
                    case 0:
                        lastDateStr = "透過 身分證字號 邀請您成為好友";
                        break;
                    case 1:
                        lastDateStr = "透過 APP系統編碼 邀請您成為好友";
                        break;
                    case 2:
                        lastDateStr = "透過 QRCode 邀請您成為好友";
                        break;
                }
                listener.setOnClick("inviteSlef", true, childPosition);
                viewHolder.item_surface.setEnabled(true);
                viewHolder.swipeLayout.setSwipeEnabled(false);
            }

            viewHolder.childTitle.setText(friendNickName);
            viewHolder.lastDateTv.setText(lastDateStr);
            if (memberFlag) {
                viewHolder.memberFlagIv.setVisibility(View.VISIBLE);
            } else {
                viewHolder.memberFlagIv.setVisibility(View.GONE);
            }
            if (!memberFlag && !signInShrPref.getMemberFlag()) {
                viewHolder.iv_edit.setVisibility(View.GONE);
            } else {
                viewHolder.iv_edit.setVisibility(View.VISIBLE);
            }
//                viewHolder.avatarIv.setImageResource();


            /*****設定Btn*****/
            viewHolder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    toast("click edit");
                    //do something
                    new AsyncTask<Class<?>, Void, String>() {
                        @Override
                        protected String doInBackground(Class<?>... params) {
                            Intent intent = new Intent(context, params[0]);
                            context.startActivity(intent);
                            return "finish";
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            viewHolder.swipeLayout.close();
                        }
                    }.execute(FriendSettingActivity.class);
                }
            });

            viewHolder.iv_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    toast("click map");
                    //do something
                    new AsyncTask<Class<?>, Void, String>() {
                        @Override
                        protected String doInBackground(Class<?>... params) {
                            Intent intent = new Intent(context, params[0]);
                            context.startActivity(intent);
                            return "finish";
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            viewHolder.swipeLayout.close();
                        }
                    }.execute(FriendMapActivity.class);
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        public class GroupViewHolder {
            public SwipeLayout swipeLayout;
            public ImageView iv_trash, iv_edit, exFlagIv;
            public TextView groupName, groupCount;
            public LinearLayout item_surface;
        }

        public class ChildViewHolder {
            public SwipeLayout swipeLayout;
            public ImageView iv_trash, iv_edit, iv_map, memberFlagIv;
            public TextView childTitle, lastDateTv;
            public CircleImageView avatarIv;
            public LinearLayout item_surface;
        }

        private class OnClick implements View.OnClickListener {
            String groupKind;
            boolean memberFlag;
            int childPosition;

            public void setOnClick(String groupKind, boolean memberFlag, int childPosition) {
                this.groupKind = groupKind;
                this.memberFlag = memberFlag;
                this.childPosition = childPosition;
            }

            @Override
            public void onClick(View v) {
                switch (groupKind) {
                    case "groupSelf":
                        if (memberFlag) {
//                            toast("自訂群組");
                            Intent intent = new Intent(context, FriendPersonalActivity.class);
                            context.startActivity(intent);
                        } else {
                            toast("非遠距會員");
                        }
                        break;
                    case "allFriend":
                        if (memberFlag) {
//                            toast("所有好友");
                            Intent intent = new Intent(context, FriendPersonalActivity.class);
                            context.startActivity(intent);
                        } else {
                            toast("非遠距會員");
                        }
                        break;
                    case "inviteSlef":
//                        toast("好友邀請");
                        replyFriendInviteDialog(fAddRecvRows.get(childPosition).getFAddNot_send_name()
                                , fAddRecvRows.get(childPosition).getFAddNot_send_aid());
                        break;
                }
            }
        }

        private void toast(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        }
    }

    public class AddGroupAdapter extends BaseAdapter {
        // 填充數據的list
        private ArrayList<FRow> fRows;
        // 用來控制CheckBox的選中狀況
        private HashMap<Integer, Boolean> isSelected;
        // 上下文
        private Context context;

        private int[] checkNum;
        // 用來導入佈局
        private LayoutInflater inflater = null;

        // 構造器
        @SuppressLint("UseSparseArrays")
        public AddGroupAdapter(Context context, ArrayList<FRow> fRows, int[] checkNum) {
            this.context = context;
            this.fRows = fRows;
            this.checkNum = checkNum;
            inflater = LayoutInflater.from(context);
            isSelected = new HashMap<Integer, Boolean>();
            // 初始化數據
            initDate();
        }

        // 初始化isSelected的數據
        private void initDate() {
            for (int i = 0; i < fRows.size(); i++) {
                getIsSelected().put(i, false);
            }
            checkNum[0] = 0;
        }

        @Override
        public int getCount() {
            return fRows.size();
        }

        @Override
        public Object getItem(int position) {
            return fRows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                // 獲得ViewHolder對象
                holder = new ViewHolder();
                // 導入佈局並賦值給convertview
                convertView = inflater.inflate(R.layout.dialog_add_group_listview_item, null);
                holder.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
                holder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
                holder.nickNameTv = (TextView) convertView.findViewById(R.id.nickNameTv);
                holder.item_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
                // 為view設置標籤
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }


            // 設置list顯示
//            holder.avatarIv.setImageResource();
            holder.nameTv.setText(fRows.get(position).getF_name());
            holder.nickNameTv.setText(fRows.get(position).getF_nickname());
            // 根據isSelected來設置checkbox的選中狀況
            holder.item_cb.setChecked(getIsSelected().get(position));
            return convertView;
        }

        public HashMap<Integer, Boolean> getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
        }

        public class ViewHolder {
            public CircleImageView avatarIv;
            public TextView nameTv, nickNameTv;
            public CheckBox item_cb;
        }
    }

    public class EditGroupAdapter extends BaseAdapter {
        // 上下文
        private Context context;
        // 填充數據的list
        private ArrayList<FRow> fRows;

        private ArrayList<FGRow> fgRowArrayList;

        private int[] checkNum;
        // 用來控制CheckBox的選中狀況
        private HashMap<Integer, Boolean> isSelected;
        // 用來導入佈局
        private LayoutInflater inflater = null;

        // 構造器
        @SuppressLint("UseSparseArrays")
        public EditGroupAdapter(Context context, ArrayList<FRow> fRows, ArrayList<FGRow> fgRowArrayList, int[] checkNum) {
            this.context = context;
            this.fRows = fRows;
            this.fgRowArrayList = fgRowArrayList;
            this.checkNum = checkNum;
            inflater = LayoutInflater.from(context);
            isSelected = new HashMap<Integer, Boolean>();
            // 初始化數據
            initDate();
        }

        // 初始化isSelected的數據
        private void initDate() {
            for (int i = 0; i < fRows.size(); i++) {
                Boolean aboolean = false;
                for (int j = 0; j < fgRowArrayList.size(); j++) {
                    if (fRows.get(i).getF_fri_aid().equals(fgRowArrayList.get(j).getFG_fri_aid())) {
                        aboolean = true;
                        checkNum[0]++;
                        break;
                    }
                }
                if (aboolean) {
                    getIsSelected().put(i, true);
                } else {
                    getIsSelected().put(i, false);
                }
            }
        }

        @Override
        public int getCount() {
            return fRows.size();
        }

        @Override
        public Object getItem(int position) {
            return fRows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                // 獲得ViewHolder對象
                holder = new ViewHolder();
                // 導入佈局並賦值給convertview
                convertView = inflater.inflate(R.layout.dialog_add_group_listview_item, null);
                holder.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
                holder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
                holder.nickNameTv = (TextView) convertView.findViewById(R.id.nickNameTv);
                holder.item_cb = (CheckBox) convertView.findViewById(R.id.item_cb);
                // 為view設置標籤
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }


            // 設置list顯示
//            holder.avatarIv.setImageResource();
            holder.nameTv.setText(fRows.get(position).getF_name());
            holder.nickNameTv.setText(fRows.get(position).getF_nickname());
            // 根據isSelected來設置checkbox的選中狀況
            holder.item_cb.setChecked(getIsSelected().get(position));
            return convertView;
        }

        public HashMap<Integer, Boolean> getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
        }

        public class ViewHolder {
            public CircleImageView avatarIv;
            public TextView nameTv, nickNameTv;
            public CheckBox item_cb;
        }
    }


    /**
     * d2
     */
    /**
     *
     */
    private void sendInviteAsyncTask(String aid, String friAid, int addWayFlag) {
        new AsyncTask<String, Void, ArrayList<FAddNotRow>>() {
            @Override
            protected ArrayList<FAddNotRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<FAddNotRow> fAddNotRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.InviteAddingFriend(params[0], params[1], params[2]);
                    fAddNotRows = jsonParser.parseFAddNotRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return fAddNotRows;
            }

            @Override
            protected void onPostExecute(ArrayList<FAddNotRow> fAddNotRows) {
                super.onPostExecute(fAddNotRows);
                if (fAddNotRows != null && fAddNotRows.size() != 0) {
                    mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    mainDB.save(fAddNotRows.get(0));
                    LiteOrm.releaseMemory();
                    fAddSendRows.clear();
                    fAddSendRows.addAll(mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                            .whereEquals(FAddNotRow.FADDNOT_SEND_AID, signInShrPref.getAID())
                            .whereAppendAnd()
                            .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                            .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME)));
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    gfExListViewAdapter.notifyDataSetChanged();
                    expandableListView.expandGroup(expandableListView.getCount() - 2);
                    expandableListView.setSelectedGroup(expandableListView.getCount() - 2);
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(aid, friAid, String.valueOf(addWayFlag));
    }

    private void agreeInviteAsyncTask(final String aid, final String friAid) {
        new AsyncTask<String, Void, ArrayList<FRow>>() {
            @Override
            protected ArrayList<FRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<FRow> fRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.AgreeAddingFriend(params[0], params[1]);
                    fRows = jsonParser.parseFRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return fRows;
            }

            @Override
            protected void onPostExecute(ArrayList<FRow> arrayList) {
                super.onPostExecute(arrayList);
                if (arrayList != null && arrayList.size() != 0) {
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    mainDB.save(arrayList.get(0));
                    mainDB.delete(new WhereBuilder(FAddNotRow.class)
                            .equals(FAddNotRow.FADDNOT_SEND_AID, aid)
                            .andEquals(FAddNotRow.FADDNOT_RECV_AID, friAid));
                    mainDB.delete(new WhereBuilder(FAddNotRow.class)
                            .equals(FAddNotRow.FADDNOT_SEND_AID, friAid)
                            .andEquals(FAddNotRow.FADDNOT_RECV_AID, aid));
                    LiteOrm.releaseMemory();
                    fRows.clear();
                    fRows.addAll(mainDB.query(new QueryBuilder<FRow>(FRow.class)
                            .whereEquals(FRow.F_RELATION_FLAG, 0)
                            .appendOrderAscBy(FRow.F_NAME)));
                    LiteOrm.releaseMemory();
                    fAddSendRows.clear();
                    fAddSendRows.addAll(mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                            .whereEquals(FAddNotRow.FADDNOT_SEND_AID, signInShrPref.getAID())
                            .whereAppendAnd()
                            .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                            .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME)));
                    LiteOrm.releaseMemory();
                    fAddRecvRows.clear();
                    fAddRecvRows.addAll(mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                            .whereEquals(FAddNotRow.FADDNOT_RECV_AID, signInShrPref.getAID())
                            .whereAppendAnd()
                            .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                            .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME)));
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    if ("Y".equals(arrayList.get(0).getF_member_flag())) {
                        updateFriendData(arrayList.get(0).getF_fri_sid());
                    } else {
                        gfExListViewAdapter.notifyDataSetChanged();
                        expandableListView.expandGroup(expandableListView.getCount() - 3);
                        expandableListView.setSelectedGroup(expandableListView.getCount() - 3);
                    }
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(aid, friAid);
    }

    private void updateFriendData(String sid) {
        final DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
        final Boolean[] updateEndflag = new Boolean[]{false, false, false};

        final MySyncingDialog mySyncingDialog = new MySyncingDialog(false, getActivity(), "同步好友資料中，請稍後");
        mySyncingDialog.show();

        new AsyncTask<String, Void, ArrayList<FWLevelRow>>() {
            @Override
            protected ArrayList<FWLevelRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<FWLevelRow> fwLevelRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateNewFWLevelShrPref(params[0]);
                    fwLevelRows = jsonParser.parseFWLevelRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return fwLevelRows;
            }

            @Override
            protected void onPostExecute(ArrayList<FWLevelRow> fwLevelRows) {
                super.onPostExecute(fwLevelRows);
                if (fwLevelRows != null && fwLevelRows.size() != 0) {
                    for (int i = 0; i < fwLevelRows.size(); i++) {
                        mainDB.save(fwLevelRows.get(i));
                    }
                    LiteOrm.releaseMemory();
                } else {
                    Toast.makeText(getActivity(), "好友警戒值更新失敗", Toast.LENGTH_SHORT).show();
                }
                updateFriendDataEndflagSetting(updateEndflag, 0, mainDB, mySyncingDialog);
            }
        }.execute(sid);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String dateStr = new MyDateSFormat().getFrmt_yMdHm().format(calendar.getTime());

        new AsyncTask<String, Void, ArrayList<PreDataRow>>() {
            @Override
            protected ArrayList<PreDataRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<PreDataRow> preDataRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateNewFPressDataTable(params[0], params[1]);
                    preDataRows = jsonParser.parsePreDataRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return preDataRows;
            }

            @Override
            protected void onPostExecute(ArrayList<PreDataRow> preDataRows) {
                super.onPostExecute(preDataRows);
                if (preDataRows != null) {
                    if (preDataRows.size() != 0) {
                        for (int i = 0; i < preDataRows.size(); i++) {
                            mainDB.save(preDataRows.get(i));
                        }
                        LiteOrm.releaseMemory();
                    }
                } else {
                    Toast.makeText(getActivity(), "好友血壓量測資料更新失敗", Toast.LENGTH_SHORT).show();
                }
                updateFriendDataEndflagSetting(updateEndflag, 1, mainDB, mySyncingDialog);
            }
        }.execute(sid, dateStr);

        new AsyncTask<String, Void, ArrayList<GlyDataRow>>() {
            @Override
            protected ArrayList<GlyDataRow> doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                ArrayList<GlyDataRow> glyDataRows = null;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.UpdateNewFGlycemiaDataTable(params[0], params[1]);
                    glyDataRows = jsonParser.parseGlyDataRow(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return glyDataRows;
            }

            @Override
            protected void onPostExecute(ArrayList<GlyDataRow> glyDataRows) {
                super.onPostExecute(glyDataRows);
                if (glyDataRows != null) {
                    if (glyDataRows.size() != 0) {
                        for (int i = 0; i < glyDataRows.size(); i++) {
                            mainDB.save(glyDataRows.get(i));
                        }
                        LiteOrm.releaseMemory();
                    }
                } else {
                    Toast.makeText(getActivity(), "好友血糖量測資料更新失敗", Toast.LENGTH_SHORT).show();
                }
                updateFriendDataEndflagSetting(updateEndflag, 2, mainDB, mySyncingDialog);
            }
        }.execute(sid, dateStr);
    }

    private void updateFriendDataEndflagSetting(Boolean[] updateEndflag, int i, DataBase mainDB, MySyncingDialog mySyncingDialog) {
        updateEndflag[i] = true;
        if (updateEndflag[0] && updateEndflag[1] && updateEndflag[2]) {
            mainDB.close();
            gfExListViewAdapter.notifyDataSetChanged();
            expandableListView.expandGroup(expandableListView.getCount() - 3);
            expandableListView.setSelectedGroup(expandableListView.getCount() - 3);
            mySyncingDialog.dismiss();
        }
    }

    private void refuseInviteAsyncTask(final String aid, final String friAid) {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                JSONParser jsonParser = new JSONParser();
                Boolean aBoolean = false;

                JSONObject jsonObject;
                try {
                    jsonObject = httcjsonapi.RefuseAddingFriend(params[0], params[1]);
                    aBoolean = jsonParser.parseBoolean(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return aBoolean;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
                    mainDB.delete(new WhereBuilder(FAddNotRow.class)
                            .equals(FAddNotRow.FADDNOT_SEND_AID, aid)
                            .andEquals(FAddNotRow.FADDNOT_RECV_AID, friAid));
                    mainDB.delete(new WhereBuilder(FAddNotRow.class)
                            .equals(FAddNotRow.FADDNOT_SEND_AID, friAid)
                            .andEquals(FAddNotRow.FADDNOT_RECV_AID, aid));
                    LiteOrm.releaseMemory();
                    fAddRecvRows.clear();
                    fAddRecvRows.addAll(mainDB.query(new QueryBuilder<FAddNotRow>(FAddNotRow.class)
                            .whereEquals(FAddNotRow.FADDNOT_RECV_AID, signInShrPref.getAID())
                            .whereAppendAnd()
                            .whereEquals(FAddNotRow.FADDNOT_STATUS_FLAG, 0)
                            .appendOrderAscBy(FAddNotRow.FADDNOT_DATETIME)));
                    LiteOrm.releaseMemory();
                    gfExListViewAdapter.notifyDataSetChanged();
                    expandableListView.expandGroup(expandableListView.getCount() - 3);
                    expandableListView.setSelectedGroup(expandableListView.getCount() - 3);
                } else {
                    Toast.makeText(getActivity(), "系統發生錯誤，請稍後再嘗試", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(aid, friAid);
    }
}
