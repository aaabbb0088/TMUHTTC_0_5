package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft3_medicine;

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
 * Created by TonyChuang on 2016/4/15.
 */
public class FriendServiceMedicineItemNameListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;

    public FriendServiceMedicineItemNameListviewAdapter(Context ctxt) {
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
            convertView = myInflater.inflate(R.layout.listview_item_medicine_name, null);
            holder = new Holder();
            holder.medicinedate = (AutofitTextView) convertView.findViewById(R.id.medicinedate);
            holder.medicinetime = (AutofitTextView) convertView.findViewById(R.id.medicinetime);
            holder.medicinename = (AutofitTextView) convertView.findViewById(R.id.medicinename);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.medicinedate.setText("用藥日期");
        holder.medicinetime.setText("用藥時間");
        holder.medicinename.setText("藥物名稱");

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class Holder {
        AutofitTextView medicinedate, medicinetime, medicinename;
    }
}
