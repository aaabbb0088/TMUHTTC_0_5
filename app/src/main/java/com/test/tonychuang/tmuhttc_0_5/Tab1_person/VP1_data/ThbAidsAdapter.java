package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.FRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/4/6.
 */
public class ThbAidsAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private Context ctxt;
    private String[] aids;

    public ThbAidsAdapter(Context ctxt, ArrayList<?> ThumbRows, String Flag) {
        myInflater = LayoutInflater.from(ctxt);
        this.ctxt = ctxt;
        switch (Flag){
            case "P":
                PreThumbRow preThumbRow = (PreThumbRow)ThumbRows.get(0);
                aids = preThumbRow.getPData_thumb_aids().split(",");
                break;
            case "G":
                GlyThumbRow glyThumbRow = (GlyThumbRow)ThumbRows.get(0);
                aids = glyThumbRow.getGData_thumb_aids().split(",");
                break;
        }
    }

    @Override
    public int getCount() {
        return aids.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            //取得listItem容器 view
            convertView = myInflater.inflate(R.layout.listview_item_thumb, null);
            holder = new Holder();
            holder.nickNameTv = (AutofitTextView) convertView.findViewById(R.id.nickNameTv);
            holder.avatarIv = (CircleImageView) convertView.findViewById(R.id.avatarIv);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        SignInShrPref signInShrPref = new SignInShrPref(ctxt);
        if (aids[position].equals(signInShrPref.getAID())) {
            holder.nickNameTv.setText("我");
        } else {
            DataBase mainDB = LiteOrm.newSingleInstance(ctxt, signInShrPref.getAID());
            ArrayList<FRow> fRows = mainDB.query(new QueryBuilder<FRow>(FRow.class)
                    .whereEquals(FRow.F_FRI_AID, aids[position]));
            if (fRows.size() != 0) {
                holder.nickNameTv.setText(fRows.get(0).getF_nickname());
            } else {
                holder.nickNameTv.setText(aids[position] + "\n(尚未與該會員成為好友)");
            }
            LiteOrm.releaseMemory();
            mainDB.close();
        }
        return convertView;
    }

    class Holder {
        AutofitTextView nickNameTv;
        CircleImageView avatarIv;
    }
}
