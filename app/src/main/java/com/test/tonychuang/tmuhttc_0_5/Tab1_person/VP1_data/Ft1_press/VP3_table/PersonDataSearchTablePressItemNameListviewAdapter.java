package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.VP3_table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.test.tonychuang.tmuhttc_0_5.R;

import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/4/1.
 */
public class PersonDataSearchTablePressItemNameListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private String dataFlag;

    public PersonDataSearchTablePressItemNameListviewAdapter(Context ctxt, String dataFlag) {
        myInflater = LayoutInflater.from(ctxt);
        this.dataFlag = dataFlag;
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
            convertView = myInflater.inflate(R.layout.listview_item_press_data_name, null);
            holder = new Holder();
            holder.dataDate = (AutofitTextView) convertView.findViewById(R.id.dataDate);
            holder.hData = (AutofitTextView) convertView.findViewById(R.id.hData);
            holder.lData = (AutofitTextView) convertView.findViewById(R.id.lData);
            holder.hrData = (AutofitTextView) convertView.findViewById(R.id.hrData);
            holder.memo = (AutofitTextView) convertView.findViewById(R.id.memo);
            holder.hDataLayout = convertView.findViewById(R.id.hDataLayout);
            holder.lDataLayout = convertView.findViewById(R.id.lDataLayout);
            holder.hrDataLayout = convertView.findViewById(R.id.hrDataLayout);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.dataDate.setText("日期");
        holder.dataDate.setTextSize(18f);
        holder.hData.setText("收縮壓");
        holder.hData.setTextSize(18f);
        holder.lData.setText("舒張壓");
        holder.lData.setTextSize(18f);
        holder.hrData.setText("脈搏");
        holder.hrData.setTextSize(18f);
        holder.memo.setText("備註");
        holder.memo.setTextSize(18f);

        switch (dataFlag){
            case "allBtn":
                holder.hDataLayout.setVisibility(View.VISIBLE);
                holder.lDataLayout.setVisibility(View.VISIBLE);
                holder.hrDataLayout.setVisibility(View.VISIBLE);
                break;
            case "hBtn":
                holder.hDataLayout.setVisibility(View.VISIBLE);
                holder.lDataLayout.setVisibility(View.GONE);
                holder.hrDataLayout.setVisibility(View.GONE);
                break;
            case "lBtn":
                holder.hDataLayout.setVisibility(View.GONE);
                holder.lDataLayout.setVisibility(View.VISIBLE);
                holder.hrDataLayout.setVisibility(View.GONE);
                break;
            case "hrBtn":
                holder.hDataLayout.setVisibility(View.GONE);
                holder.lDataLayout.setVisibility(View.GONE);
                holder.hrDataLayout.setVisibility(View.VISIBLE);
                break;
            default:
                holder.hDataLayout.setVisibility(View.VISIBLE);
                holder.lDataLayout.setVisibility(View.VISIBLE);
                holder.hrDataLayout.setVisibility(View.VISIBLE);
                break;
        }

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 85);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class Holder {
        AutofitTextView dataDate, hData, lData, hrData, memo;
        View hDataLayout, lDataLayout, hrDataLayout;
    }
}
