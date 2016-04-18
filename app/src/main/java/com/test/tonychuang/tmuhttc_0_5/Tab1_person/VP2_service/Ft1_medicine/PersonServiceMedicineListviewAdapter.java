package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft1_medicine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SMedRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/4/6.
 */
public class PersonServiceMedicineListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    ArrayList<SMedRow> medicineTable = null;

    public PersonServiceMedicineListviewAdapter(Context ctxt, ArrayList<SMedRow> medicineTable) {
        myInflater = LayoutInflater.from(ctxt);
        this.medicineTable = medicineTable;
    }

    @Override
    public int getCount() {
        return medicineTable.size();
    }

    @Override
    public Object getItem(int position) {
        return medicineTable.get(position);
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
            convertView = myInflater.inflate(R.layout.listview_item_medicine, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (AutofitTextView) convertView.findViewById(R.id.medicinedate),
                    (AutofitTextView) convertView.findViewById(R.id.medicinetime),
                    (AutofitTextView) convertView.findViewById(R.id.medicinename)
            );
            convertView.setTag(viewTag);
        } else {
            viewTag = (ViewTag) convertView.getTag();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy年\nM月d日", Locale.getDefault());
        SimpleDateFormat TimeFormat = new SimpleDateFormat("a h:mm ", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(medicineTable.get(position).getSMed_datetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewTag.medicinedate.setText(DateFormat.format(date));
        viewTag.medicinetime.setText(TimeFormat.format(date));
        viewTag.medicinename.setText(" " + medicineTable.get(position).getSMed_medname() + " ");

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class ViewTag {
        AutofitTextView medicinedate;
        AutofitTextView medicinetime;
        AutofitTextView medicinename;

        public ViewTag(AutofitTextView medicinedate, AutofitTextView medicinetime, AutofitTextView medicinename) {
            this.medicinedate = medicinedate;
            this.medicinetime = medicinetime;
            this.medicinename = medicinename;
        }
    }
}
