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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft1_press.PersonDataPressActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft2_glycemia.PersonDataGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg.PersonMsgGlycemiaActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg.PersonMsgPressActivity;

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
    private ImageButton pressMsgBtn;
    private ImageButton glycemiaMsgBtn;
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
        pressMsgBtn = (ImageButton) view.findViewById(R.id.pressMsgBtn);
        pressMsgBtn.setOnClickListener(this);
        glycemiaMsgBtn = (ImageButton) view.findViewById(R.id.glycemiaMsgBtn);
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

    private DialogInterface.OnKeyListener getOnKeyListener(){
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
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


    /**
     * d2
     */
    /**
     *
     */
}
