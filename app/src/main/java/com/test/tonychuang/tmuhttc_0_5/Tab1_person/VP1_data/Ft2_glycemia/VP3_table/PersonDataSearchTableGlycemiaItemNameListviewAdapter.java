package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft2_glycemia.VP3_table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * Created by TonyChuang on 2016/4/6.
 */
public class PersonDataSearchTableGlycemiaItemNameListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;

    public PersonDataSearchTableGlycemiaItemNameListviewAdapter(Context ctxt) {
        myInflater = LayoutInflater.from(ctxt);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.listview_item_glycemia_data_name, null);
            holder = new Holder();
            holder.dataDate = (TextView) convertView.findViewById(R.id.dataDate);
            holder.mealFlag = (TextView) convertView.findViewById(R.id.mealFlag);
            holder.gDataValue = (TextView) convertView.findViewById(R.id.gDataValue);
            holder.memo = (TextView) convertView.findViewById(R.id.memo);
            holder.bfDataLayout = convertView.findViewById(R.id.bfDataLayout);
            holder.afDataLayout = convertView.findViewById(R.id.afDataLayout);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.dataDate.setText("日期");
        holder.dataDate.setTextSize(18f);
        holder.mealFlag.setText("血糖種類");
        holder.mealFlag.setTextSize(18f);
        holder.gDataValue.setText("血糖值");
        holder.gDataValue.setTextSize(18f);
        holder.memo.setText("備註");
        holder.memo.setTextSize(18f);

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 85);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class Holder {
        TextView dataDate, mealFlag, gDataValue, memo;
        View bfDataLayout, afDataLayout;
    }
}
