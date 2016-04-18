package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft2_glycemia.VP3_table;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FWLevelRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/4/15.
 */
public class FriendDataSearchTableGlycemiaListviewAdapter extends BaseAdapter {
    private int BG_BM_Max;
    private int BG_BM_Min;
    private int BG_AM_Max;
    private int BG_AM_Min;

    private LayoutInflater myInflater;
    private ArrayList<GlyDataRow> Table = null;
    private String dataFlag;

    public FriendDataSearchTableGlycemiaListviewAdapter(Context ctxt, ArrayList<GlyDataRow> Table, String dataFlag, String friSid, String aid) {
        DataBase mainDB = LiteOrm.newSingleInstance(ctxt, aid);
        FWLevelRow fwLevelRow = mainDB.query(new QueryBuilder<FWLevelRow>(FWLevelRow.class)
                .whereEquals(FWLevelRow.FWLEVEL_SID, friSid)).get(0);
        BG_BM_Max = fwLevelRow.getBG_BM_Max();
        BG_BM_Min = fwLevelRow.getBG_BM_Min();
        BG_AM_Max = fwLevelRow.getBG_AM_Max();
        BG_AM_Min = fwLevelRow.getBG_AM_Min();

        myInflater = LayoutInflater.from(ctxt);
        this.dataFlag = dataFlag;
        this.Table = Table;

//        ArrayList<GlyDataRow> tmp = new ArrayList<>();
//        tmp.addAll(Table);
//        switch (dataFlag) {
//            case "allBtn":
//                break;
//            case "bfBtn":
//                for (int i = 0; i < tmp.size(); i++) {
//                    if (tmp.get(i).getGData_meal_flag().equals("af")) {
//                        tmp.remove(i);
//                    }
//                }
//                break;
//            case "afBtn":
//                for (int i = 0; i < tmp.size(); i++) {
//                    if (!tmp.get(i).getGData_meal_flag().equals("af")) {
//                        tmp.remove(i);
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//        this.Table = tmp;
    }

    @Override
    public int getCount() {
        return Table.size();
    }

    @Override
    public Object getItem(int position) {
        return Table.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewTag viewTag;
        if (convertView == null) {
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.listview_item_glycemia_data, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (LinearLayout) convertView.findViewById(R.id.itemLayout),
                    (TextView) convertView.findViewById(R.id.dataDate),
                    (TextView) convertView.findViewById(R.id.mealFlag),
                    (TextView) convertView.findViewById(R.id.gDataValue),
                    (TextView) convertView.findViewById(R.id.memo)
            );

            convertView.setTag(viewTag);
        } else {
            viewTag = (ViewTag) convertView.getTag();
        }

        MyDateSFormat myDateSFormat = new MyDateSFormat();
        SimpleDateFormat DateFormat = new SimpleDateFormat("M月d日\na h:mm", Locale.getDefault());
        Date date = null;
        try {
            date = myDateSFormat.getFrmt_yMdHm().parse(Table.get(position).getGData_datetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewTag.dataDate.setText(DateFormat.format(date));
        viewTag.dataDate.setTextSize(14f);
        if (Table.get(position).getGData_meal_flag().equals("af")) {
            viewTag.mealFlag.setText("飯後");
            if (Table.get(position).getGData_value() > BG_AM_Max) {
                viewTag.gDataValue.setTextColor(Color.RED);
            } else if (Table.get(position).getGData_value() < BG_AM_Min) {
                viewTag.gDataValue.setTextColor(Color.BLUE);
            } else {
                viewTag.gDataValue.setTextColor(Color.BLACK);
            }
        } else {
            viewTag.mealFlag.setText("飯前");
            if (Table.get(position).getGData_value() > BG_BM_Max) {
                viewTag.gDataValue.setTextColor(Color.RED);
            } else if (Table.get(position).getGData_value() < BG_BM_Min) {
                viewTag.gDataValue.setTextColor(Color.BLUE);
            } else {
                viewTag.gDataValue.setTextColor(Color.BLACK);
            }
        }
        viewTag.mealFlag.setTextSize(18f);
        viewTag.gDataValue.setText(String.valueOf(Table.get(position).getGData_value()));
        viewTag.gDataValue.setTextSize(18f);
        viewTag.memo.setText("");
        viewTag.memo.setTextSize(15f);

        viewTag.itemLayout.setVisibility(View.VISIBLE);
        switch (dataFlag) {
            case "allBtn":
                break;
            case "bfBtn":
                if (this.Table.get(position).getGData_meal_flag().equals("af")) {
                    viewTag.itemLayout.setVisibility(View.GONE);
                }
                break;
            case "afBtn":
                if (!this.Table.get(position).getGData_meal_flag().equals("af")) {
                    viewTag.itemLayout.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }


        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class ViewTag {
        LinearLayout itemLayout;
        TextView dataDate;
        TextView mealFlag;
        TextView gDataValue;
        TextView memo;

        public ViewTag(LinearLayout itemLayout, TextView dataDate, TextView mealFlag, TextView gDataValue, TextView memo) {
            this.itemLayout = itemLayout;
            this.dataDate = dataDate;
            this.mealFlag = mealFlag;
            this.gDataValue = gDataValue;
            this.memo = memo;
        }
    }
}
