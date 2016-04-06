package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft3_pay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SPayRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/4/6.
 */
public class PersonServicePayListviewAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    ArrayList<SPayRow> payTable = null;

    public PersonServicePayListviewAdapter(Context ctxt, ArrayList<SPayRow> payTable) {
        myInflater = LayoutInflater.from(ctxt);
        this.payTable = payTable;
    }

    @Override
    public int getCount() {
        return payTable.size();
    }

    @Override
    public Object getItem(int position) {
        return payTable.get(position);
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
            convertView = myInflater.inflate(R.layout.listview_item_pay, null);

            //建構listItem內容view
            viewTag = new ViewTag(
                    (AutofitTextView) convertView.findViewById(R.id.paymonth),
                    (AutofitTextView) convertView.findViewById(R.id.paymoney),
                    (AutofitTextView) convertView.findViewById(R.id.paystatus)
            );
            convertView.setTag(viewTag);
        }else{
            viewTag = (ViewTag) convertView.getTag();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy年\nM月", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(payTable.get(position).getSPay_datetime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewTag.paymonth.setText(DateFormat.format(date));
        viewTag.paymoney.setText("  " + payTable.get(position).getSPay_money());
        viewTag.paystatus.setText(payTable.get(position).getSPay_status());

        /*以下为新增部分*/ //調整listview item寬度
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 80);
        convertView.setLayoutParams(lp);
        /*以上为新增部分*/

        return convertView;
    }

    class ViewTag {
        AutofitTextView paymonth;
        AutofitTextView paymoney;
        AutofitTextView paystatus;

        public ViewTag(AutofitTextView paymonth, AutofitTextView paymoney, AutofitTextView paystatus) {
            this.paymonth = paymonth;
            this.paymoney = paymoney;
            this.paystatus = paystatus;
        }
    }
}
