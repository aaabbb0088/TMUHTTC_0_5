package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft4_record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SRcrdRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/4/6.
 */
public class PersonServiceRecordListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    ArrayList<SRcrdRow> servicesTable = null;

    public PersonServiceRecordListviewAdapter(Context ctxt, ArrayList<SRcrdRow> servicesTable) {
        myInflater = LayoutInflater.from(ctxt);
        this.servicesTable = servicesTable;
    }

    @Override
    public int getCount() {
        return servicesTable.size();
    }

    @Override
    public Object getItem(int position) {
        return servicesTable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewTag viewTag;

        if (convertView == null) {
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.listview_item_record, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (AutofitTextView) convertView.findViewById(R.id.ServicesDate),
                    (AutofitTextView) convertView.findViewById(R.id.ServicesTime),
                    (ImageView) convertView.findViewById(R.id.ServicesImage),
                    (AutofitTextView) convertView.findViewById(R.id.ServicesInfo)
            );
            convertView.setTag(viewTag);
        }else{
            viewTag = (ViewTag) convertView.getTag();
        }

        switch(servicesTable.get(position).getSRcrd_type()){
            case 7:
                viewTag.servicesicon.setImageResource(R.mipmap.service_calltocenter);
                viewTag.servicesinfo.setText("會員去電");
                break;
            case 8:
                viewTag.servicesicon.setImageResource(R.mipmap.service_calltomenber);
                viewTag.servicesinfo.setText("中心去電");
                break;
            case 9:
                viewTag.servicesicon.setImageResource(R.mipmap.service_track);
                viewTag.servicesinfo.setText("追蹤");
                break;
            case 10:
                viewTag.servicesicon.setImageResource(R.mipmap.service_emergency);
                viewTag.servicesinfo.setText("緊急通報");
                break;
            case 11:
                viewTag.servicesicon.setImageResource(R.mipmap.service_doctor);
                viewTag.servicesinfo.setText("聯絡醫師");
                break;
            case 12:
                viewTag.servicesicon.setImageResource(R.mipmap.service_health_info);
                viewTag.servicesinfo.setText("提供\n衛教資訊");
                break;
            case 13:
                viewTag.servicesicon.setImageResource(R.mipmap.service_live_info);
                viewTag.servicesinfo.setText("提供\n生活資源");
                break;
            case 14:
                viewTag.servicesicon.setImageResource(R.mipmap.service_pill_info);
                viewTag.servicesinfo.setText("提供\n用藥指導");
                break;
            case 15:
                viewTag.servicesicon.setImageResource(R.mipmap.service_outpatient);
                viewTag.servicesinfo.setText("安排門診");
                break;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        SimpleDateFormat DateFormat = new SimpleDateFormat("M月d日", Locale.getDefault());
        SimpleDateFormat TimeFormat = new SimpleDateFormat("a hh:mm ", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(servicesTable.get(position).getSRcrd_datetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewTag.servicesdate.setText(DateFormat.format(date));
        viewTag.servicesdate.setTextSize(15f);
        viewTag.servicestime.setText(TimeFormat.format(date));
        viewTag.servicestime.setTextSize(18f);

        return convertView;
    }
    class ViewTag {
        AutofitTextView servicesdate;
        AutofitTextView servicestime;
        ImageView servicesicon;
        AutofitTextView servicesinfo;

        public ViewTag(AutofitTextView servicesdate, AutofitTextView servicestime, ImageView servicesicon, AutofitTextView servicesinfo) {
            this.servicesdate = servicesdate;
            this.servicestime = servicestime;
            this.servicesicon = servicesicon;
            this.servicesinfo = servicesinfo;
        }
    }
}
