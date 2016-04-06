package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft4_record;

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
 * Created by TonyChuang on 2016/4/6.
 */
public class PersonServiceRecordItemNameListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;

    public PersonServiceRecordItemNameListviewAdapter(Context ctxt) {
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
            convertView = myInflater.inflate(R.layout.listview_item_record_name, null);
            holder = new Holder();
            holder.servicesDate = (AutofitTextView) convertView.findViewById(R.id.ServicesDate);
            holder.servicesTime = (AutofitTextView) convertView.findViewById(R.id.ServicesTime);
            holder.servicesImage = (AutofitTextView) convertView.findViewById(R.id.ServicesImage);
            holder.servicesInfo = (AutofitTextView) convertView.findViewById(R.id.ServicesInfo);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.servicesDate.setText("服務日期");
        holder.servicesTime.setText("服務時間");
        holder.servicesImage.setText("服務圖示");
        holder.servicesInfo.setText("服務說明");

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class Holder {
        AutofitTextView servicesDate, servicesTime, servicesImage, servicesInfo;
    }
}
