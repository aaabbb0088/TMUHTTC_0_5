package com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.Ft5_pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft3_pay.PersonServicePayItemNameListviewAdapter;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft3_pay.PersonServicePayListviewAdapter;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.MyDataModule.MyDateSFormat;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.SPayRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * ListView 的 Adapter 調用 PersonServicePayActivity 的 Adapter
 */
/**
 *
 */

public class FriendPersonalPayActivity extends AppCompatActivity implements View.OnClickListener {

    private MyInitReturnBar myInitReturnBar;
    private ToggleButton sixMounthBtn;
    private ToggleButton allDataBtn;
    private TextView loadStatusText;
    private ListView listViewItemName;
    private ListView listViewData;

    private SimpleDateFormat dateFormat;
    private MyDateSFormat myDateSFormat;

    private ArrayList<SPayRow> sixMounthData;
    private ArrayList<SPayRow> allData;

    private SignInShrPref signInShrPref;
    private String friAid;
    private String friSid;
    private String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_personal_pay);
        initView();
        initData();
        initBar(nickName);
        initBtn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sixMounthBtn:
                setDateBtn(sixMounthBtn);
                break;
            case R.id.allDataBtn:
                setDateBtn(allDataBtn);
                break;
        }
    }

    /**
     * v1
     */
    /**
     *
     */
    private void initBar(String nickName) {
        myInitReturnBar = new MyInitReturnBar(this, nickName + "的繳費紀錄", 0);
    }

    private void initView() {
        sixMounthBtn = (ToggleButton) findViewById(R.id.sixMounthBtn);
        sixMounthBtn.setOnClickListener(this);
        allDataBtn = (ToggleButton) findViewById(R.id.allDataBtn);
        allDataBtn.setOnClickListener(this);
        loadStatusText = (TextView) findViewById(R.id.loadStatusText);
        listViewItemName = (ListView) findViewById(R.id.listViewItemName);
        listViewItemName.setEnabled(false);
        listViewData = (ListView) findViewById(R.id.listViewData);
    }

    private void initBtn() {
        sixMounthBtn.setChecked(true);
        setDateBtn(sixMounthBtn);
    }

    private void setDateBtn(ToggleButton Btn) {
        sixMounthBtn.setEnabled(true);
        allDataBtn.setEnabled(true);
        switch (Btn.getId()) {
            case R.id.sixMounthBtn:
                sixMounthBtn.setEnabled(false);
                allDataBtn.setChecked(false);
                showResult(sixMounthData);
                break;
            case R.id.allDataBtn:
                sixMounthBtn.setChecked(false);
                allDataBtn.setEnabled(false);
                showResult(allData);
                break;
        }
    }


    /**
     * v2
     */
    /**
     *
     */
    private void showResult(ArrayList<SPayRow> sPayRows) {
        int dataSize = sPayRows.size();
        if (dataSize == 0) {
            loadStatusText.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(new PersonServicePayItemNameListviewAdapter(this));
            listViewData.setVisibility(View.GONE);
            loadStatusText.setText("無資料");
        } else {
            loadStatusText.setVisibility(View.GONE);
            listViewData.setVisibility(View.VISIBLE);
            listViewItemName.setAdapter(new PersonServicePayItemNameListviewAdapter(this));
            listViewData.setAdapter(new PersonServicePayListviewAdapter(this, sPayRows));
        }
    }



    /**
     * d1
     */
    /**
     *
     */
    private void initData() {
        friAid = this.getIntent().getExtras().getString("friAid");
        friSid = this.getIntent().getExtras().getString("friSid");
        nickName = this.getIntent().getExtras().getString("nickName");

        myDateSFormat = new MyDateSFormat();
        signInShrPref = new SignInShrPref(this);

        DataBase mainDB = LiteOrm.newSingleInstance(this, signInShrPref.getAID());

        Calendar clr = Calendar.getInstance(Locale.TAIWAN);
        String todayDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        todayDateStr = todayDateStr + " 23:59";
        clr.add(Calendar.MONTH, -6);
        String sixMounthDateStr = myDateSFormat.getFrmt_yMd().format(clr.getTime());
        sixMounthDateStr = sixMounthDateStr + " 00:00";

        sixMounthData = mainDB.query(new QueryBuilder<SPayRow>(SPayRow.class)
                .where(new WhereBuilder(SPayRow.class)
                        .equals(SPayRow.SPAY_SID, friSid)
                        .and()
                        .greaterThan(SPayRow.SPAY_DATETIME, sixMounthDateStr)
                        .and()
                        .lessThan(SPayRow.SPAY_DATETIME, todayDateStr)
                        .or()
                        .equals(SPayRow.SPAY_SID, friSid)
                        .and()
                        .greaterThan(SPayRow.SPAY_DATETIME, sixMounthDateStr)
                        .and()
                        .equals(SPayRow.SPAY_DATETIME, todayDateStr))
                .appendOrderDescBy(SPayRow.SPAY_DATETIME));
        LiteOrm.releaseMemory();
        allData = mainDB.query(new QueryBuilder<SPayRow>(SPayRow.class)
                .where(new WhereBuilder(SPayRow.class)
                        .equals(SPayRow.SPAY_SID, friSid)
                        .and()
                        .lessThan(SPayRow.SPAY_DATETIME, todayDateStr)
                        .or()
                        .equals(SPayRow.SPAY_SID, friSid)
                        .and()
                        .equals(SPayRow.SPAY_DATETIME, todayDateStr))
                .appendOrderDescBy(SPayRow.SPAY_DATETIME));
        LiteOrm.releaseMemory();
        mainDB.close();
    }



    /**
     * d2
     */
    /**
     *
     */

}
