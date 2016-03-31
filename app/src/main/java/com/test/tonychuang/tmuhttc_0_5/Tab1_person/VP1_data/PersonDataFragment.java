package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.PersonDataPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft2_glycemia.PersonDataGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg.PersonMsgGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg.PersonMsgPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyGlycemiaPsnJudgment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyPressPsnJudgment;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.GlyThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreDataRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PreThumbRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.WLevelShrPref;

import java.text.ParseException;
import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDataFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    private LinearLayout presslayout;
    private LinearLayout glycemialayout;
    private TextView pressValueText;
    private TextView glycemiaValueText;
    private TextView glycemiaMealText;
    private TextView pressDateText;
    private TextView glycemiaDateText;
    private TextView pressUnitText;
    private TextView glycemiaUnitText;
    private AutofitTextView pressThumbTv;
    private AutofitTextView glycemiaThumbTv;
    private FrameLayout pressMsgBtn;
    private FrameLayout glycemiaMsgBtn;
    private LinearLayout pressThbBtn;
    private LinearLayout glycemiaThbBtn;
    private View view;


    public PersonDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_data, container, false);
        initView();
        initData();
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.presslayout:
                intent.setClass(PersonDataFragment.this.getActivity(), PersonDataPressActivity.class);
                startActivity(intent);
                break;
            case R.id.glycemialayout:
                intent.setClass(PersonDataFragment.this.getActivity(), PersonDataGlycemiaActivity.class);
                startActivity(intent);
                break;
            case R.id.pressMsgBtn:
                intent.setClass(PersonDataFragment.this.getActivity(), PersonMsgPressActivity.class);
                startActivity(intent);
                break;
            case R.id.glycemiaMsgBtn:
                intent.setClass(PersonDataFragment.this.getActivity(), PersonMsgGlycemiaActivity.class);
                startActivity(intent);
                break;
            case R.id.pressThbBtn:
                Toast.makeText(getActivity(), "press +1 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.glycemiaThbBtn:
                Toast.makeText(getActivity(), "glycemia +1", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.pressThbBtn:
                thumbAlertDialog();
                break;
            case R.id.glycemiaThbBtn:
                thumbAlertDialog();
                break;
        }
        return true;
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        presslayout = (LinearLayout) view.findViewById(R.id.presslayout);
        presslayout.setOnClickListener(this);
        glycemialayout = (LinearLayout) view.findViewById(R.id.glycemialayout);
        glycemialayout.setOnClickListener(this);
        pressValueText = (TextView) view.findViewById(R.id.pressValueText);
        glycemiaValueText = (TextView) view.findViewById(R.id.glycemiaValueText);
        glycemiaMealText = (TextView) view.findViewById(R.id.glycemiaMealText);
        pressDateText = (TextView) view.findViewById(R.id.pressDateText);
        glycemiaDateText = (TextView) view.findViewById(R.id.glycemiaDateText);
        pressUnitText = (TextView) view.findViewById(R.id.pressUnitText);
        glycemiaUnitText = (TextView) view.findViewById(R.id.glycemiaUnitText);
        pressThumbTv = (AutofitTextView) view.findViewById(R.id.pressThumbTv);
        glycemiaThumbTv = (AutofitTextView) view.findViewById(R.id.glycemiaThumbTv);
        pressMsgBtn = (FrameLayout) view.findViewById(R.id.pressMsgBtn);
        pressMsgBtn.setOnClickListener(this);
        glycemiaMsgBtn = (FrameLayout) view.findViewById(R.id.glycemiaMsgBtn);
        glycemiaMsgBtn.setOnClickListener(this);
        pressThbBtn = (LinearLayout) view.findViewById(R.id.pressThbBtn);
        pressThbBtn.setOnClickListener(this);
        pressThbBtn.setOnLongClickListener(this);
        glycemiaThbBtn = (LinearLayout) view.findViewById(R.id.glycemiaThbBtn);
        glycemiaThbBtn.setOnClickListener(this);
        glycemiaThbBtn.setOnLongClickListener(this);
    }

    /**
     * List選單 對話框
     */
    //之後改成加入參數(要塞入listView的資料)
    private void thumbAlertDialog() {
        //之後把Layout改成listView(頭像+暱稱)，處理完按讚資料後，塞入listView
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_thumd_aids, null);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("按讚好友")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false)
                .create();
        alertDialog.show();

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private DialogInterface.OnKeyListener getOnKeyListener() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return false;
            }
        };
    }


    /**
     * d1
     */
    /**
     *
     */
    public void initData() {
        SignInShrPref signInShrPref = new SignInShrPref(getActivity());
        if (signInShrPref.getMemberFlag()) {

            //更新Tab0 數據資料
            DataBase mainDB = LiteOrm.newSingleInstance(getActivity(), signInShrPref.getAID());
            MyDateSFormat myDateSFormat = new MyDateSFormat();

            /**
             * 血壓
             */
            ArrayList<PreDataRow> preDataRows = mainDB.query(new QueryBuilder<PreDataRow>(PreDataRow.class)
                    .whereEquals(PreDataRow.PDATA_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreDataRow.PDATA_DATETIME)
                    .limit(0, 1));
            if (preDataRows.size() != 0) {
                String pressValue = String.valueOf(preDataRows.get(0).getPData_sys()) + " / "
                        + String.valueOf(preDataRows.get(0).getPData_dia());
                pressValueText.setText(pressValue);

                String pressTime = preDataRows.get(0).getPData_datetime();
                try {
                    pressTime = myDateSFormat.getFrmt_Mdahm()
                            .format(myDateSFormat.getFrmt_yMdHm().parse(pressTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pressDateText.setText(pressTime);
            } else {
                pressValueText.setText("沒有資料");
                pressDateText.setText("");
            }


            ArrayList<PreThumbRow> preThumbRows = mainDB.query(new QueryBuilder<PreThumbRow>(PreThumbRow.class)
                    .whereEquals(PreThumbRow.PDATA_THUMB_SID, signInShrPref.getSID())
                    .appendOrderDescBy(PreThumbRow.PDATA_THUMB_DATETIME)
                    .limit(0, 1));
            if (preThumbRows.size() != 0) {
                if (preThumbRows.get(0).getPData_thumb_count() == 0) {
                    pressThumbTv.setVisibility(View.GONE);
                } else {
                    pressThumbTv.setVisibility(View.VISIBLE);
                    pressThumbTv.setText(String.valueOf(preThumbRows.get(0).getPData_thumb_count()));
                }
            } else {
                pressThumbTv.setVisibility(View.GONE);
            }


            /**
             * 血糖
             */
            ArrayList<GlyDataRow> glyDataRows = mainDB.query(new QueryBuilder<GlyDataRow>(GlyDataRow.class)
                    .whereEquals(GlyDataRow.GDATA_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyDataRow.GDATA_DATETIME)
                    .limit(0, 1));
            if (glyDataRows.size() != 0) {
                String glyData = glyDataRows.get(0).getGData_meal_flag();
                if (glyData.equals("af")) {
                    glycemiaMealText.setText("飯後血糖");
                } else {
                    glycemiaMealText.setText("飯前血糖");
                }

                String glyDataValue = String.valueOf(glyDataRows.get(0).getGData_value());
                glycemiaValueText.setText(glyDataValue);

                String glyDataTime = glyDataRows.get(0).getGData_datetime();
                try {
                    glyDataTime = myDateSFormat.getFrmt_Mdahm()
                            .format(myDateSFormat.getFrmt_yMdHm().parse(glyDataTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                glycemiaDateText.setText(glyDataTime);
            } else {
                glycemiaValueText.setText("沒有資料");
                glycemiaDateText.setText("");
            }

            ArrayList<GlyThumbRow> glyThumbRows = mainDB.query(new QueryBuilder<GlyThumbRow>(GlyThumbRow.class)
                    .whereEquals(GlyThumbRow.GDATA_THUMB_SID, signInShrPref.getSID())
                    .appendOrderDescBy(GlyThumbRow.GDATA_THUMB_DATETIME)
                    .limit(0, 1));
            if (glyThumbRows.size() != 0) {
                if (glyThumbRows.get(0).getGData_thumb_count() == 0) {
                    glycemiaThumbTv.setVisibility(View.GONE);
                } else {
                    glycemiaThumbTv.setVisibility(View.VISIBLE);
                    glycemiaThumbTv.setText(String.valueOf(glyThumbRows.get(0).getGData_thumb_count()));
                }
            } else {
                glycemiaThumbTv.setVisibility(View.GONE);
            }

            //調整Tab0 警示顏色
            WLevelShrPref wLevelShrPref = new WLevelShrPref(getActivity());
            MyPressPsnJudgment myPressPsnJudgment =
                    new MyPressPsnJudgment(wLevelShrPref,
                            preDataRows.get(0).getPData_sys(),
                            preDataRows.get(0).getPData_dia());
            switch (myPressPsnJudgment.getResult()) {
                case "normal":
                    presslayout.setBackgroundResource(R.drawable.selector_presslayout);
                    break;
                case "warn":
                    presslayout.setBackgroundResource(R.drawable.selector_yellowlayout);
                    break;
                case "dang":
                    presslayout.setBackgroundResource(R.drawable.selector_redlayout);
                    break;
            }
//            int BP_SY_Max = wLevelShrPref.getBP_SY_Max();
//            int BP_SY_Min = wLevelShrPref.getBP_SY_Min();
//            int BP_DI_Max = wLevelShrPref.getBP_DI_Max();
//            int BP_DI_Min = wLevelShrPref.getBP_DI_Min();
//            int BP_SY_MaxDang = wLevelShrPref.getBP_SY_MaxDang();
//            int BP_SY_MinDang = wLevelShrPref.getBP_SY_MinDang();
//            int BP_DI_MaxDang = wLevelShrPref.getBP_DI_MaxDang();
//            int BP_DI_MinDang = wLevelShrPref.getBP_DI_MinDang();
//            int sysValue = preDataRows.get(0).getPData_sys();
//            int diaValue = preDataRows.get(0).getPData_dia();
//            if (BP_SY_Min <= sysValue && sysValue <= BP_SY_Max
//                    && BP_DI_Min <= diaValue && diaValue <= BP_DI_Max) {
//                presslayout.setBackgroundResource(R.drawable.selector_presslayout);
//            } else if (BP_SY_MaxDang < sysValue || BP_DI_MaxDang < diaValue ||
//                    sysValue < BP_SY_MinDang || diaValue < BP_DI_MinDang) {
//                presslayout.setBackgroundResource(R.drawable.selector_redlayout);
//            } else {
//                presslayout.setBackgroundResource(R.drawable.selector_yellowlayout);
//            }

            MyGlycemiaPsnJudgment myGlycemiaPsnJudgment =
                    new MyGlycemiaPsnJudgment(wLevelShrPref,
                            glyDataRows.get(0).getGData_value(),
                            glyDataRows.get(0).getGData_meal_flag());
            switch (myGlycemiaPsnJudgment.getResult()) {
                case "normal":
                    glycemialayout.setBackgroundResource(R.drawable.selector_glycemialayout);
                    break;
                case "warn":
                    glycemialayout.setBackgroundResource(R.drawable.selector_yellowlayout);
                    break;
                case "dang":
                    glycemialayout.setBackgroundResource(R.drawable.selector_redlayout);
                    break;
            }
//            int BG_BM_Max = wLevelShrPref.getBG_BM_Max();
//            int BG_BM_Min = wLevelShrPref.getBG_BM_Min();
//            int BG_AM_Max = wLevelShrPref.getBG_AM_Max();
//            int BG_AM_Min = wLevelShrPref.getBG_AM_Min();
//            int BG_BM_MaxDang = wLevelShrPref.getBG_BM_MaxDang();
//            int BG_BM_MinDang = wLevelShrPref.getBG_BM_MinDang();
//            int BG_AM_MaxDang = wLevelShrPref.getBG_AM_MaxDang();
//            int BG_AM_MinDang = wLevelShrPref.getBG_AM_MinDang();
//            int glyValue = glyDataRows.get(0).getGData_value();
//            String glyMealFlag = glyDataRows.get(0).getGData_meal_flag();
//            switch (glyMealFlag) {
//                case "af":
//                    if (BG_AM_Min <= glyValue && glyValue <= BG_AM_Max) {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_glycemialayout);
//                    } else if (glyValue < BG_AM_MinDang || BG_AM_MaxDang < glyValue) {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_redlayout);
//                    } else {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_yellowlayout);
//                    }
//                    break;
//                default:
//                    if (BG_BM_Min <= glyValue && glyValue <= BG_BM_Max) {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_glycemialayout);
//                    } else if (glyValue < BG_BM_MinDang || BG_BM_MaxDang < glyValue) {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_redlayout);
//                    } else {
//                        glycemialayout.setBackgroundResource(R.drawable.selector_yellowlayout);
//                    }
//                    break;
//            }
        }
    }


    /**
     * d2
     */
    /**
     *
     */
}
