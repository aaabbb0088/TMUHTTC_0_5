package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg;

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
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyMsgRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class PersonMsgGlycemiaActivity extends AppCompatActivity {

    private ExpandableListView exListView;

    private KJChatKeyboard box;
    private MyInitReturnBar myInitReturnBar;

    private SignInShrPref signInShrPref;
    private MyDateSFormat myDateSFormat;
    private DataBase mainDB;
    private WLevelShrPref wLevelShrPref;

    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_AM_Max;
    private int BG_AM_Min;

    private ArrayList<GlyDataRow> glyDataRows;
    private ArrayList<GlyMsgRow> glyMsgRows;
    private List<ArrayList<GlyMsgRow>> glyMsgRowsList;
    private ExLVAdapter exLVAdapter;

    private int msgSelectItem = -1;

    private boolean exListViewBottomFlag = false;
    private boolean moveFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg_glycemia);

        initBar();
        initView();
        initData();
        initListener();
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

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "我的今日血糖留言板", 0);
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
                            box.hideKeyboard(PersonMsgGlycemiaActivity.this);
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
        signInShrPref = new SignInShrPref(this);
        myDateSFormat = new MyDateSFormat();
        wLevelShrPref = new WLevelShrPref(this);

        BG_BM_Max = wLevelShrPref.getBG_BM_Max();
        BG_BM_Min = wLevelShrPref.getBG_BM_Min();
        BG_AM_Max = wLevelShrPref.getBG_AM_Max();
        BG_AM_Min = wLevelShrPref.getBG_AM_Min();

        mainDB = LiteOrm.newSingleInstance(this, signInShrPref.getAID());
        String todayStr = myDateSFormat.getFrmt_yMd().format(new Date());
        todayStr = todayStr + " 00:00";
        glyDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                .whereEquals(GlyDataRow.GDATA_SID, signInShrPref.getSID())
                .whereAppendAnd()
                .whereGreaterThan(GlyDataRow.GDATA_DATETIME, todayStr)
                .appendOrderAscBy(GlyDataRow.GDATA_DATETIME));
        LiteOrm.releaseMemory();

        glyMsgRowsList = new ArrayList<>();

        for (int i = 0; i < glyDataRows.size(); i++) {
            glyMsgRows = mainDB.query(new QueryBuilder<GlyMsgRow>(GlyMsgRow.class)
                    .whereEquals(GlyMsgRow.GMSG_TABLE_ID, glyDataRows.get(i).getGData_table_id())
                    .appendOrderAscBy(GlyMsgRow.GMSG_DATETIME));
            LiteOrm.releaseMemory();
            glyMsgRowsList.add(glyMsgRows);
        }
        mainDB.close();
        exListViewSetting();
    }

    private void setTvValueColor(TextView Tv, int Value, String valueFlag) {
        if (Value != 0) {
            Tv.setText(String.valueOf(Value));
            switch (valueFlag) {
                case "B":
                    if (Value < BG_BM_Min) {
                        Tv.setTextColor(Color.rgb(3, 168, 158));
                    } else if (Value > BG_BM_Max) {
                        Tv.setTextColor(Color.RED);
                    } else {
                        Tv.setTextColor(Color.BLACK);
                    }
                    break;
                case "A":
                    if (Value < BG_AM_Min) {
                        Tv.setTextColor(Color.rgb(3, 168, 158));
                    } else if (Value > BG_AM_Max) {
                        Tv.setTextColor(Color.RED);
                    } else {
                        Tv.setTextColor(Color.BLACK);
                    }
                    break;
            }
        } else {
            Tv.setText("-");
            Tv.setTextColor(Color.BLACK);
        }
    }

    private void writeMsg(final String content) {
        if (msgSelectItem != -1) {
            final long GMsg_table_id = glyDataRows.get(msgSelectItem).getGData_table_id();
            final String GMsg_sid = glyDataRows.get(msgSelectItem).getGData_sid();
            final String GMsg_writer_aid = signInShrPref.getAID();
            final String GMsg_datetime = myDateSFormat.getFrmt_yMdHm().format(new Date());
            final int GMsg_status_flag = 0;

            new AsyncTask<String, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
                    JSONParser jsonParser = new JSONParser();
                    Boolean aBoolean = false;

                    JSONObject jsonObject;
                    try {
                        jsonObject = httcjsonapi.WriteGlycemiaMsg(params[0], params[1], params[2],
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
                        GlyMsgRow glyMsgRow = new GlyMsgRow();
                        glyMsgRow.setGMsg_sid(GMsg_sid);
                        glyMsgRow.setGMsg_table_id(GMsg_table_id);
                        glyMsgRow.setGMsg_writer_aid(GMsg_writer_aid);
                        glyMsgRow.setGMsg_datetime(GMsg_datetime);
                        glyMsgRow.setGMsg_content(content);
                        glyMsgRow.setGMsg_status(GMsg_status_flag);

                        DataBase mainDB = LiteOrm.newSingleInstance(PersonMsgGlycemiaActivity.this, signInShrPref.getAID());
                        mainDB.save(glyMsgRow);
                        glyMsgRowsList.clear();
                        for (int i = 0; i < glyDataRows.size(); i++) {
                            glyMsgRows = mainDB.query(new QueryBuilder<GlyMsgRow>(GlyMsgRow.class)
                                    .whereEquals(GlyMsgRow.GMSG_TABLE_ID, glyDataRows.get(i).getGData_table_id())
                                    .appendOrderAscBy(GlyMsgRow.GMSG_DATETIME));
                            LiteOrm.releaseMemory();
                            glyMsgRowsList.add(glyMsgRows);
                        }
                        mainDB.close();
                        exListViewSetting();

                        //完成留言資料後，保持展開group，移動到新增的child
                        exLVAdapter.setSelectItem(msgSelectItem);
                        exLVAdapter.notifyDataSetChanged();
                        exListView.expandGroup(msgSelectItem);
                        exListView.setSelectedChild(msgSelectItem, glyMsgRowsList.get(msgSelectItem).size(), true);
                    } else {
                        Toast.makeText(PersonMsgGlycemiaActivity.this, "網路不穩，請稍後留言", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute(String.valueOf(GMsg_table_id), GMsg_sid,
                    GMsg_writer_aid, GMsg_datetime, content, String.valueOf(GMsg_status_flag));
            boxSetting(false);
            box.hideLayout();
            box.hideKeyboard(PersonMsgGlycemiaActivity.this);
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
        private ArrayList<GlyDataRow> glyDataRows;
        private List<ArrayList<GlyMsgRow>> glyMsgRowsList;

        private int selectItem = -1;

        public ExLVAdapter(Context context, ArrayList<GlyDataRow> glyDataRows,
                           List<ArrayList<GlyMsgRow>> glyMsgRowsList) {
            myInflater = LayoutInflater.from(context);
            this.context = context;
            this.glyDataRows = glyDataRows;
            this.glyMsgRowsList = glyMsgRowsList;
        }

        @Override
        public int getGroupCount() {
            return glyDataRows.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return glyMsgRowsList.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return glyDataRows.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return glyMsgRowsList.get(groupPosition).get(childPosition);
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
                convertView = myInflater.inflate(R.layout.listview_item_data_msg_group_glycemia, null);
                groupView = new GroupView();
                groupView.gMealTv = (AutofitTextView) convertView.findViewById(R.id.gMealTv);
                groupView.gValueTv = (AutofitTextView) convertView.findViewById(R.id.gValueTv);
                groupView.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
                groupView.msgCountTv = (TextView) convertView.findViewById(R.id.msgCountTv);
                groupView.arrowIv = (ImageView) convertView.findViewById(R.id.arrowIv);
                convertView.setTag(groupView);
            } else {
                groupView = (GroupView) convertView.getTag();
            }

            if (!glyDataRows.get(groupPosition).getGData_meal_flag().equals("af")){
                groupView.gMealTv.setText("飯前血糖 :");
                setTvValueColor(groupView.gValueTv, glyDataRows.get(groupPosition).getGData_value(), "B");
            } else {
                groupView.gMealTv.setText("飯後血糖 :");
                setTvValueColor(groupView.gValueTv, glyDataRows.get(groupPosition).getGData_value(), "A");
            }

            groupView.msgCountTv.setText(String.valueOf(getChildrenCount(groupPosition)));

            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMdHm().parse(glyDataRows.get(groupPosition).getGData_datetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            groupView.dateTv.setText(myDateSFormat.getFrmt_ahm().format(date));

            if (groupPosition == selectItem) { //點擊變色
                convertView.setBackgroundColor(Color.LTGRAY);
                groupView.arrowIv.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
                groupView.arrowIv.setImageResource(android.R.drawable.arrow_up_float);
            }
            return convertView;
        }

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
            String aid = glyMsgRowsList.get(groupPosition).get(childPosition).getGMsg_writer_aid();
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
                    childView.nickNameTv.setText(aid);
                }
            }
            childView.contentTv.setText(glyMsgRowsList.get(groupPosition).get(childPosition).getGMsg_content());
            Date date = new Date();
            try {
                date = myDateSFormat.getFrmt_yMdHm().parse(glyMsgRowsList.get(groupPosition).get(childPosition).getGMsg_datetime());
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
        AutofitTextView gMealTv, gValueTv;
        TextView dateTv, msgCountTv;
        ImageView arrowIv;
    }

    private class ChildView {
        TextView nickNameTv, contentTv, dateTv;
        CircleImageView avatarIv;
    }

    private void exListViewSetting() {
        if (glyDataRows != null && glyDataRows.size() != 0) {
            exLVAdapter = new ExLVAdapter(this, glyDataRows, glyMsgRowsList);
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

