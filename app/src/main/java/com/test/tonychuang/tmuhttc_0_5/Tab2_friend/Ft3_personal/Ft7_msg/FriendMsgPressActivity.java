package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft7_msg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.chat.keyboard.DisplayRules;
import com.test.tonychuang.chat.keyboard.Emojicon;
import com.test.tonychuang.chat.keyboard.Faceicon;
import com.test.tonychuang.chat.keyboard.KJChatKeyboard;
import com.test.tonychuang.chat.keyboard.OnOperationListener;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class FriendMsgPressActivity extends AppCompatActivity {

    private ExpandableListView exListView;
    private KJChatKeyboard box;
    private MyInitReturnBar myInitReturnBar;

    private SignInShrPref signInShrPref;
    private MyDateSFormat myDateSFormat;
    private FWLevelRow fwLevelRow;

    private int BP_SY_Max;
    private int BP_SY_Min;
    private int BP_DI_Max;
    private int BP_DI_Min;
    private int BP_HR_Max;
    private int BP_HR_Min;

    private String friAid;
    private String friSid;
    private ArrayList<FRow> fRows;
    private ArrayList<PreDataRow> preDataRows;
    private ArrayList<PreMsgRow> preMsgRows;
    private List<ArrayList<PreMsgRow>> preMsgRowsList;
    private ExLVAdapter exLVAdapter;

    private int msgSelectItem = -1;

    private boolean exListViewBottomFlag = false;
    private boolean moveFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_msg_press);

        initView();
        initListener();
        initData();
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        exListView = (ExpandableListView) findViewById(R.id.exListView);
        box = (KJChatKeyboard) findViewById(R.id.chat_msg_input_box);
        initMsgInputToolBox();
        boxSetting(false);
    }

    private void initBar(String nickName) {
        myInitReturnBar = new MyInitReturnBar(this, nickName + " 的本日血壓留言板", 0);
    }

    private void initListener() {
        exListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                moveFlag = scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (msgSelectItem != -1) {
                    if ((firstVisibleItem + visibleItemCount) >= (totalItemCount - exLVAdapter.getGroupCount()) + 1) {
                        exListViewBottomFlag = true;
                        Log.d("ListView", "#####滾動到選中的group的最後一個item######");
                    } else {
                        exListViewBottomFlag = false;
                    }
                }
            }
        });

        exListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!moveFlag) {
                            box.hideLayout();
                            box.hideKeyboard(FriendMsgPressActivity.this);
                            box.getEditTextBox().clearFocus();
                        }
                        break;
                }
                return false;
            }
        });

        box.getEditTextBox().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && exListViewBottomFlag) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exListView.setSelectedChild(msgSelectItem, exLVAdapter.getChildrenCount(msgSelectItem) - 1, true);
                        }
                    }, 200);
                }
            }
        });
    }

    private void initMsgInputToolBox() {
        box.getmBtnMore().setVisibility(View.GONE);
        box.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                //按下send鍵後會做的事
                writeMsg(content);
            }

            @Override
            public void selectedFace(Faceicon content) {
            }

            @Override
            public void selectedEmoji(Emojicon emoji) {
                box.getEditTextBox().append(emoji.getValue());
            }

            @Override
            public void selectedBackSpace(Emojicon back) {
                DisplayRules.backspace(box.getEditTextBox());
            }

            @Override
            public void selectedFunction(int index) {
            }
        });

        List<String> faceCagegory = new ArrayList<>();
//        File faceList = FileUtils.getSaveFolder("chat");
        File faceList = new File("");
        if (faceList.isDirectory()) {
            File[] faceFolderArray = faceList.listFiles();
            for (File folder : faceFolderArray) {
                if (!folder.isHidden()) {
                    faceCagegory.add(folder.getAbsolutePath());
                }
            }
        }
        box.setFaceData(faceCagegory);
    }


    /**
     * v2
     */
    /**
     *
     */
    private void boxSetting(boolean bool) {
        if (bool) {
            box.getmBtnSend().setEnabled(true);
            box.getEditTextBox().setEnabled(true);
            box.getEditTextBox().setHint("開始留言");
            box.getmBtnFace().setEnabled(true);
        } else {
            box.getmBtnSend().setEnabled(false);
            box.getEditTextBox().setEnabled(false);
            box.getEditTextBox().setHint("請點選想留言的量測記錄");
            box.getmBtnFace().setEnabled(false);
        }
    }



    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        friAid = this.getIntent().getExtras().getString("friAid");
        friSid = this.getIntent().getExtras().getString("friSid");

        signInShrPref = new SignInShrPref(this);
        myDateSFormat = new MyDateSFormat();

        DataBase mainDB = LiteOrm.newSingleInstance(FriendMsgPressActivity.this, signInShrPref.getAID());
        fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class).whereEquals(FRow.F_FRI_AID, friAid));
        fwLevelRow = mainDB.query(new QueryBuilder<FWLevelRow>(FWLevelRow.class)
                .whereEquals(FWLevelRow.FWLEVEL_SID, friSid)).get(0);
        BP_SY_Max = fwLevelRow.getBP_SY_Max();
        BP_SY_Min = fwLevelRow.getBP_SY_Min();
        BP_DI_Max = fwLevelRow.getBP_DI_Max();
        BP_DI_Min = fwLevelRow.getBP_DI_Min();
        BP_HR_Max = fwLevelRow.getBP_HR_Max();
        BP_HR_Min = fwLevelRow.getBP_HR_Min();

        initBar(fRows.get(0).getF_nickname());

        String todayStr = myDateSFormat.getFrmt_yMd().format(new Date());
        todayStr = todayStr + " 00:00";
        preDataRows = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                .whereEquals(PreDataRow.PDATA_SID, friSid)
                .whereAppendAnd()
                .whereGreaterThan(PreDataRow.PDATA_DATETIME, todayStr)
                .appendOrderAscBy(PreDataRow.PDATA_DATETIME));
        LiteOrm.releaseMemory();

        preMsgRowsList = new ArrayList<>();

        for (int i = 0; i < preDataRows.size(); i++) {
            preMsgRows = mainDB.query(new QueryBuilder<PreMsgRow>(PreMsgRow.class)
                    .whereEquals(PreMsgRow.PMSG_TABLE_ID, preDataRows.get(i).getPData_table_id())
                    .appendOrderAscBy(PreMsgRow.PMSG_DATETIME));
            LiteOrm.releaseMemory();
            preMsgRowsList.add(preMsgRows);
        }
        mainDB.close();
        exListViewSetting();
    }

    private void setTvValueColor(TextView Tv, int Value, String valueFlag) {
        Tv.setText(String.valueOf(Value));
        switch (valueFlag) {
            case "H":
                if (Value < BP_SY_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_SY_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
            case "L":
                if (Value < BP_DI_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_DI_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
            case "Hr":
                if (Value < BP_HR_Min) {
                    Tv.setTextColor(Color.rgb(3, 168, 158));
                } else if (Value > BP_HR_Max) {
                    Tv.setTextColor(Color.RED);
                } else {
                    Tv.setTextColor(Color.BLACK);
                }
                break;
        }
    }

    private void writeMsg(final String content) {
        if (msgSelectItem != -1) {
            final long PMsg_table_id = preDataRows.get(msgSelectItem).getPData_table_id();
            final String PMsg_sid = preDataRows.get(msgSelectItem).getPData_sid();
            final String PMsg_writer_aid = signInShrPref.getAID();
            final String PMsg_datetime = myDateSFormat.getFrmt_yMdHm().format(new Date());
            final int PMsg_status_flag = 0;

            new AsyncTask<String, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    Boolean aBoolean = false;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.WritePressMsg(params[0], params[1], params[2],
                                params[3], params[4], params[5]);
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
                        PreMsgRow preMsgRow = new PreMsgRow();
                        preMsgRow.setPMsg_sid(PMsg_sid);
                        preMsgRow.setPMsg_table_id(PMsg_table_id);
                        preMsgRow.setPMsg_writer_aid(PMsg_writer_aid);
                        preMsgRow.setPMsg_datetime(PMsg_datetime);
                        preMsgRow.setPMsg_content(content);
                        preMsgRow.setPMsg_status(PMsg_status_flag);

                        DataBase mainDB = LiteOrm.newSingleInstance(FriendMsgPressActivity.this, signInShrPref.getAID());
                        mainDB.save(preMsgRow);
                        preMsgRowsList.clear();
                        for (int i = 0; i < preDataRows.size(); i++) {
                            preMsgRows = mainDB.query(new QueryBuilder<PreMsgRow>(PreMsgRow.class)
                                    .whereEquals(PreMsgRow.PMSG_TABLE_ID, preDataRows.get(i).getPData_table_id())
                                    .appendOrderAscBy(PreMsgRow.PMSG_DATETIME));
                            LiteOrm.releaseMemory();
                            preMsgRowsList.add(preMsgRows);
                        }
                        mainDB.close();
                        exListViewSetting();

                        //完成留言資料後，保持展開group，移動到新增的child
                        exLVAdapter.setSelectItem(msgSelectItem);
                        exLVAdapter.notifyDataSetChanged();
                        exListView.expandGroup(msgSelectItem);
                        exListView.setSelectedChild(msgSelectItem, preMsgRowsList.get(msgSelectItem).size(), true);
                    } else {
                        Toast.makeText(FriendMsgPressActivity.this, "網路不穩，請稍後留言", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute(String.valueOf(PMsg_table_id), PMsg_sid,
                    PMsg_writer_aid, PMsg_datetime, content, String.valueOf(PMsg_status_flag));
            boxSetting(false);
            box.hideLayout();
            box.hideKeyboard(FriendMsgPressActivity.this);
        }
    }



    /**
     * d2
     */
    /**
     *
     */
    private class ExLVAdapter extends BaseExpandableListAdapter {
        private LayoutInflater myInflater;
        private Context context;
        private ArrayList<PreDataRow> preDataRows;
        private List<ArrayList<PreMsgRow>> preMsgRowsList;

        private int selectItem = -1;

        public ExLVAdapter(Context context, ArrayList<PreDataRow> preDataRows,
                           List<ArrayList<PreMsgRow>> preMsgRowsList) {
            myInflater = LayoutInflater.from(context);
            this.context = context;
            this.preDataRows = preDataRows;
            this.preMsgRowsList = preMsgRowsList;
        }

        @Override
        public int getGroupCount() {
            return preDataRows.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return preMsgRowsList.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return preDataRows.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return preMsgRowsList.get(groupPosition).get(childPosition);
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

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupView groupView;
            if (convertView == null) {
                //取得listItem容器 view
                convertView = myInflater.inflate(R.layout.listview_item_data_msg_group_press, null);
                groupView = new GroupView();
                groupView.sysTv = (AutofitTextView) convertView.findViewById(R.id.sysTv);
                groupView.diaTv = (AutofitTextView) convertView.findViewById(R.id.diaTv);
                groupView.hrTv = (AutofitTextView) convertView.findViewById(R.id.hrTv);
                groupView.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
                groupView.msgCountTv = (TextView) convertView.findViewById(R.id.msgCountTv);
                groupView.arrowIv = (ImageView) convertView.findViewById(R.id.arrowIv);
                convertView.setTag(groupView);
            } else {
                groupView = (GroupView) convertView.getTag();
            }

            setTvValueColor(groupView.sysTv, preDataRows.get(groupPosition).getPData_sys(), "H");
            setTvValueColor(groupView.diaTv, preDataRows.get(groupPosition).getPData_dia(), "L");
            setTvValueColor(groupView.hrTv, preDataRows.get(groupPosition).getPData_hr(), "Hr");

            groupView.msgCountTv.setText(String.valueOf(getChildrenCount(groupPosition)));

            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMdHm().parse(preDataRows.get(groupPosition).getPData_datetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            groupView.dateTv.setText(myDateSFormat.getFrmt_ahm().format(date));

            //點擊變色
            if (groupPosition == selectItem) {
                convertView.setBackgroundColor(Color.LTGRAY);
                groupView.arrowIv.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
                groupView.arrowIv.setImageResource(android.R.drawable.arrow_up_float);
            }
            return convertView;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildView childView;
            if (convertView == null) {
                //取得listItem容器 view
                convertView = myInflater.inflate(R.layout.listview_item_data_msg_child, null);
                childView = new ChildView();
                childView.nickNameTv = (TextView) convertView.findViewById(R.id.nickNameTv);
                childView.contentTv = (TextView) convertView.findViewById(R.id.contentTv);
                childView.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
                childView.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
                convertView.setTag(childView);
            } else {
                childView = (ChildView) convertView.getTag();
            }

            SignInShrPref signInShrPref = new SignInShrPref(context);
            String aid = preMsgRowsList.get(groupPosition).get(childPosition).getPMsg_writer_aid();
            if (aid.equals(signInShrPref.getAID())) {
                childView.nickNameTv.setText("我");
            } else {
                DataBase mainDB = LiteOrm.newSingleInstance(context, signInShrPref.getAID());
                ArrayList<FRow> fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                        .whereEquals(FRow.F_FRI_AID, aid));
                LiteOrm.releaseMemory();
                mainDB.close();
                if (fRows.size() != 0) {
                    childView.nickNameTv.setText(fRows.get(0).getF_nickname());
                } else {
                    childView.nickNameTv.setText(aid + "(尚未與該會員成為好友)");
                }
            }
            childView.contentTv.setText(preMsgRowsList.get(groupPosition).get(childPosition).getPMsg_content());
            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMdHm().parse(preMsgRowsList.get(groupPosition).get(childPosition).getPMsg_datetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            childView.dateTv.setText(myDateSFormat.getFrmt_ahm().format(date));
//            childView.avatarIv;

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        public void setSelectItem(int selectItem) {
            if (selectItem != this.selectItem) {
                this.selectItem = selectItem;
                msgSelectItem = selectItem;
                boxSetting(true);
            } else {
                this.selectItem = -1;
                msgSelectItem = -1;
                boxSetting(false);
            }
        }
    }

    private class GroupView {
        AutofitTextView sysTv, diaTv, hrTv;
        TextView dateTv, msgCountTv;
        ImageView arrowIv;
    }

    private class ChildView {
        TextView nickNameTv, contentTv, dateTv;
        CircleImageView avatarIv;
    }

    private void exListViewSetting() {
        if (preDataRows != null && preDataRows.size() != 0) {
            exLVAdapter = new ExLVAdapter(this, preDataRows, preMsgRowsList);
            exListView.setAdapter(exLVAdapter);
            exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    for (int i = 0; i < exLVAdapter.getGroupCount(); i++) {
                        if (groupPosition != i) {
                            exListView.collapseGroup(i);
                        }
                    }
                }
            });
            exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    exLVAdapter.setSelectItem(groupPosition);
                    exLVAdapter.notifyDataSetChanged();
                    return false; //true 點擊不收起
                }
            });
        }
    }

}
