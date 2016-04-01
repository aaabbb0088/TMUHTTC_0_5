package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP3_table;

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

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/4/1.
 */
public class PersonDataSearchTablePressListviewAdapter extends BaseAdapter {
    private int BP_SY_Max;
    private int BP_SY_Min;
    private int BP_DI_Max;
    private int BP_DI_Min;
    private int BP_HR_Max;
    private int BP_HR_Min;

    private LayoutInflater myInflater;
    ArrayList<PreDataRow> pressTable = null;

    private String dataFlag;

    public PersonDataSearchTablePressListviewAdapter(Context ctxt, ArrayList<PreDataRow> pressTable, String dataFlag) {
        WLevelShrPref wLevelShrPref = new WLevelShrPref(ctxt);
        BP_SY_Max = wLevelShrPref.getBP_SY_Max();
        BP_SY_Min = wLevelShrPref.getBP_SY_Min();
        BP_DI_Max = wLevelShrPref.getBP_DI_Max();
        BP_DI_Min = wLevelShrPref.getBP_DI_Min();
        BP_HR_Max = wLevelShrPref.getBP_HR_Max();
        BP_HR_Min = wLevelShrPref.getBP_HR_Min();

        myInflater = LayoutInflater.from(ctxt);
        this.pressTable = pressTable;
        this.dataFlag = dataFlag;
    }

    @Override
    public int getCount() {
        return pressTable.size();
    }

    @Override
    public Object getItem(int position) {
        return pressTable.get(position);
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
            convertView = myInflater.inflate(R.layout.listview_item_press_data, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (TextView) convertView.findViewById(R.id.dataDate),
                    (TextView) convertView.findViewById(R.id.hData),
                    (TextView) convertView.findViewById(R.id.lData),
                    (TextView) convertView.findViewById(R.id.hrData),
                    (TextView) convertView.findViewById(R.id.memo),
                    convertView.findViewById(R.id.hDataLayout),
                    convertView.findViewById(R.id.lDataLayout),
                    convertView.findViewById(R.id.hrDataLayout)
            );

            convertView.setTag(viewTag);
        } else {
            viewTag = (ViewTag) convertView.getTag();
        }

        MyDateSFormat myDateSFormat = new MyDateSFormat();
        SimpleDateFormat DateFormat = new SimpleDateFormat("M月d日\na h:mm", Locale.getDefault());
        Date date = null;
        try {
            date = myDateSFormat.getFrmt_yMdHm().parse(pressTable.get(position).getPData_datetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewTag.dataDate.setText(DateFormat.format(date));
        viewTag.dataDate.setTextSize(14f);
        viewTag.hData.setText(String.valueOf(pressTable.get(position).getPData_sys()));
        viewTag.hData.setTextSize(18f);
        viewTag.lData.setText(String.valueOf(pressTable.get(position).getPData_dia()));
        viewTag.lData.setTextSize(18f);
        viewTag.hrData.setText(String.valueOf(pressTable.get(position).getPData_hr()));
        viewTag.hrData.setTextSize(18f);
        viewTag.memo.setText("");
        viewTag.memo.setTextSize(15f);

        if (pressTable.get(position).getPData_sys() > BP_SY_Max) {
            viewTag.hData.setTextColor(Color.RED);
        } else if (pressTable.get(position).getPData_sys() < BP_SY_Min) {
            viewTag.hData.setTextColor(Color.BLUE);
        } else {
            viewTag.hData.setTextColor(Color.BLACK);
        }

        if (pressTable.get(position).getPData_dia() > BP_DI_Max) {
            viewTag.lData.setTextColor(Color.RED);
        } else if (pressTable.get(position).getPData_dia() < BP_DI_Min) {
            viewTag.lData.setTextColor(Color.BLUE);
        } else {
            viewTag.lData.setTextColor(Color.BLACK);
        }

        if (pressTable.get(position).getPData_hr() > BP_HR_Max) {
            viewTag.hrData.setTextColor(Color.RED);
        } else if (pressTable.get(position).getPData_hr() < BP_HR_Min) {
            viewTag.hrData.setTextColor(Color.BLUE);
        } else {
            viewTag.hrData.setTextColor(Color.BLACK);
        }

        switch (dataFlag) {
            case "allBtn":
                viewTag.hDataLayout.setVisibility(View.VISIBLE);
                viewTag.lDataLayout.setVisibility(View.VISIBLE);
                viewTag.hrDataLayout.setVisibility(View.VISIBLE);
                break;
            case "hBtn":
                viewTag.hDataLayout.setVisibility(View.VISIBLE);
                viewTag.lDataLayout.setVisibility(View.GONE);
                viewTag.hrDataLayout.setVisibility(View.GONE);
                break;
            case "lBtn":
                viewTag.hDataLayout.setVisibility(View.GONE);
                viewTag.lDataLayout.setVisibility(View.VISIBLE);
                viewTag.hrDataLayout.setVisibility(View.GONE);
                break;
            case "hrBtn":
                viewTag.hDataLayout.setVisibility(View.GONE);
                viewTag.lDataLayout.setVisibility(View.GONE);
                viewTag.hrDataLayout.setVisibility(View.VISIBLE);
                break;
            default:
                viewTag.hDataLayout.setVisibility(View.VISIBLE);
                viewTag.lDataLayout.setVisibility(View.VISIBLE);
                viewTag.hrDataLayout.setVisibility(View.VISIBLE);
                break;
        }

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class ViewTag {
        TextView dataDate;
        TextView hData;
        TextView lData;
        TextView hrData;
        TextView memo;
        View hDataLayout;
        View lDataLayout;
        View hrDataLayout;

        public ViewTag(TextView dataDate, TextView hData, TextView lData, TextView hrData, TextView memo,
                       View hDataLayout, View lDataLayout, View hrDataLayout) {
            this.dataDate = dataDate;
            this.hData = hData;
            this.lData = lData;
            this.hrData = hrData;
            this.memo = memo;
            this.hDataLayout = hDataLayout;
            this.lDataLayout = lDataLayout;
            this.hrDataLayout = hrDataLayout;
        }
    }
}
