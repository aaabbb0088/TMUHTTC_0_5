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
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.WriterException;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft1_board.FriendBoardActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map.FriendMapActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.FriendPersonalActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting.FriendSettingActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.AES.MyAES;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.QRCode.MyQRCodeCreate;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FGRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {


    private ExpandableListView expandableListView;

    private View view;  //Fragment的佈局
    private ActionBar actionBar;
    private SignInShrPref signInShrPref;
    private MyDateSFormat myDateSFormat;
    private DataBase mainDB;
    private ArrayList<FRow> fRows;
    private ArrayList<FGRow> fgRows;
    private List<String[]> fgRowsAidsList;


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
        expandableListView.setAdapter(new GFListViewAdapter(getActivity(), fgRows, fgRowsAidsList, fRows));

//        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
//        expandableListView.setSelection(0);// 设置默认选中项
        //箭頭移到右邊
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        expandableListView.setIndicatorBounds(width-70, width-20);
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
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
                        InputMethodManager imm = (InputMethodManager) dialogView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
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
        signInShrPref = new SignInShrPref(getActivity());
        myDateSFormat = new MyDateSFormat();
        mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());

        fgRows = mainDB.query(FGRow.class);
        fgRowsAidsList = new ArrayList<>();
        if (fgRows.size() != 0) {
            String[] fAidsArray;
            for (int i = 0; i < fgRows.size(); i++) {
                fAidsArray = fgRows.get(i).getFG_fri_aids().split(",");
                fgRowsAidsList.add(fAidsArray);
            }
        }
        fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                .whereEquals(FRow.F_RELATION_FLAG, 1));
        LiteOrm.releaseMemory();
        mainDB.close();
    }

    private class GFListViewAdapter extends BaseExpandableListAdapter {
        private LayoutInflater myInflater;
        private Context context;
        private ArrayList<FGRow> fgRows;
        private List<String[]> fgRowsAidsList;
        private ArrayList<FRow> fRows;

        public GFListViewAdapter(Context context, ArrayList<FGRow> fgRows,
                                 List<String[]> fgRowsAidsList, ArrayList<FRow> fRows) {
            myInflater = LayoutInflater.from(context);
            this.context = context;
            this.fgRows = fgRows;
            this.fgRowsAidsList = fgRowsAidsList;
            this.fRows = fRows;
        }

        @Override
        public int getGroupCount() {
            return fgRows.size() + 1;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (groupPosition < fgRows.size()){
                return fgRowsAidsList.get(groupPosition).length;
            } else {
                return fRows.size();
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            if (groupPosition < fgRows.size()){
                return fgRowsAidsList.get(groupPosition);
            } else {
                return fRows;
            }
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (groupPosition < fgRows.size()){
                return fgRowsAidsList.get(groupPosition)[childPosition];
            } else {
                return fRows.get(childPosition);
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
                holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
                holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewWithTag("Edit"));
                holder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
                holder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
                convertView.setTag(holder);
            }

            final String groupName;
            if (groupPosition < fgRows.size()){
                holder.swipeLayout.setSwipeEnabled(true);
                groupName = fgRows.get(groupPosition).getFG_group_name();
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fgRowsAidsList.get(groupPosition).length + ")");
            } else {
                holder.swipeLayout.setSwipeEnabled(false);
                groupName = "全部好友";
                holder.groupName.setText(groupName);
                holder.groupCount.setText("(" + fRows.size() + ")");
            }

            convertView.findViewById(R.id.item_surface).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isExpanded) {
                        expandableListView.collapseGroup(groupPosition);
                    } else {
                        expandableListView.expandGroup(groupPosition);
                    }
                }
            });

            holder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editGroupAlertDialog(groupName);
                    holder.swipeLayout.close();
                }
            });

            holder.iv_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletGroupCheckAlertDialog(groupName);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            final ChildViewHolder viewHolder;
            if (convertView != null) {
                viewHolder = (ChildViewHolder) convertView.getTag();
            } else {
                viewHolder = new ChildViewHolder();
                convertView = myInflater.inflate(R.layout.friend_listview_item_child, null);
                viewHolder.childTitle = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.lastDateTv = (TextView) convertView.findViewById(R.id.lastDateTv);
                viewHolder.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
                viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
                viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewWithTag("Edit"));
                viewHolder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
                viewHolder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
                viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewWithTag("Map"));
                viewHolder.iv_map = (ImageView) convertView.findViewById(R.id.map);
                convertView.setTag(viewHolder);
            }

            String lastDateStr = "";
            final String friendNickName;
            if (groupPosition < fgRows.size()){
                DataBase mainDB = LiteOrm.newSingleInstance(context, signInShrPref.getAID());
                ArrayList<FRow> fRowArrayList = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                        .whereEquals(FRow.F_FRI_AID, fgRowsAidsList.get(groupPosition)[childPosition]));
                LiteOrm.releaseMemory();
                if (fRowArrayList.get(0).getF_member_flag().equals("Y")) { //是遠距會員，可能有最後量測時間
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
                    if (!"".equals(lastDateStr)){
                        lastDateStr = "最近量測時間 " + lastDateStr;
                    }
                }
                mainDB.close();
                friendNickName = fRowArrayList.get(0).getF_nickname();
            } else {
                friendNickName = fRows.get(childPosition).getF_nickname();
                if (fRows.get(childPosition).getF_member_flag().equals("Y")) {
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
                    if (preDataRows.size() != 0 && glyDataRows.size() != 0) {
                        if (lastPreDate.after(lastGlyDate)) {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                        } else {
                            lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                        }
                    } else if (preDataRows.size() != 0) {
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastPreDate);
                    } else if (glyDataRows.size() != 0) {
                        lastDateStr = myDateSFormat.getFrmt_Mdahm().format(lastGlyDate);
                    }
                    lastDateStr = "最近量測時間 " + lastDateStr;
                }
            }
            viewHolder.childTitle.setText(friendNickName);
            viewHolder.lastDateTv.setText(lastDateStr);
//                viewHolder.avatarIv.setImageResource();

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

            viewHolder.iv_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletfriendCheckAlertDialog(friendNickName);
                    notifyDataSetChanged();
                    //do something
                }
            });

            convertView.findViewById(R.id.item_surface).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //do something
                    Intent intent = new Intent(context, FriendPersonalActivity.class);
                    context.startActivity(intent);
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
            public ImageView iv_trash, iv_edit;
            public TextView groupName, groupCount;
        }

        public class ChildViewHolder {
            public SwipeLayout swipeLayout;
            public ImageView iv_trash, iv_edit, iv_map;
            public TextView childTitle, lastDateTv;
            public CircleImageView avatarIv;
        }

        private void editGroupAlertDialog(String groupName) {
            View dialogTitleView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_group_title, null);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_group_body, null);
            final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
            TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
            final MaterialEditText groupNameEd = (MaterialEditText) dialogView.findViewById(R.id.groupNameEd);
            final AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setCustomTitle(dialogTitleView)
                    .setView(dialogView)
                    .setOnKeyListener(getOnKeyListener())
                    .setCancelable(false).create();
            alertDialog.show();

            groupNameEd.setHint(groupName);
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

        @SuppressLint("SetTextI18n")
        private void deletGroupCheckAlertDialog(String groupName) {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_friend_group_body, null);
            TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
            final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
            TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
            final AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("刪除群組")
                    .setView(dialogView)
                    .setOnKeyListener(getOnKeyListener())
                    .setCancelable(false).create();
            alertDialog.show();

            msgTv.setText("確定要刪除 \"" + groupName + "\" 群組 嗎?");
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

        private void deletfriendCheckAlertDialog(String friendName) {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_friend_group_body, null);
            TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
            final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
            TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
            final AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("刪除好友")
                    .setView(dialogView)
                    .setOnKeyListener(getOnKeyListener())
                    .setCancelable(false).create();
            alertDialog.show();

            msgTv.setText("確定要刪除 \"" + friendName + "\" 嗎?");
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

        private void toast(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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
    }


    /**
     * d2
     */
    /**
     *
     */
}
