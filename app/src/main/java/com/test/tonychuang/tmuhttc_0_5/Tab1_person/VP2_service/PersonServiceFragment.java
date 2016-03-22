package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft1_medicine.PersonServiceMedicineActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft2_report.PersonServiceReportActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft3_pay.PersonServicePayActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft4_record.PersonServiceRecordActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonServiceFragment extends Fragment implements View.OnClickListener {


    private View view;


    public PersonServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_service, container, false);
        initView();
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.pillLayout:
                intent = new Intent();
                intent.setClass(PersonServiceFragment.this.getActivity(), PersonServiceMedicineActivity.class);
                startActivity(intent);
                break;
            case R.id.reportLayout:
                intent = new Intent();
                intent.setClass(PersonServiceFragment.this.getActivity(), PersonServiceReportActivity.class);
                startActivity(intent);
                break;
            case R.id.payLayout:
                intent = new Intent();
                intent.setClass(PersonServiceFragment.this.getActivity(), PersonServicePayActivity.class);
                startActivity(intent);
                break;
            case R.id.recordLayout:
                intent = new Intent();
                intent.setClass(PersonServiceFragment.this.getActivity(), PersonServiceRecordActivity.class);
                startActivity(intent);
                break;
        }
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initView() {
        LinearLayout pillLayout = (LinearLayout) view.findViewById(R.id.pillLayout);
        pillLayout.setOnClickListener(this);
        LinearLayout reportLayout = (LinearLayout) view.findViewById(R.id.reportLayout);
        reportLayout.setOnClickListener(this);
        LinearLayout payLayout = (LinearLayout) view.findViewById(R.id.payLayout);
        payLayout.setOnClickListener(this);
        LinearLayout recordLayout = (LinearLayout) view.findViewById(R.id.recordLayout);
        recordLayout.setOnClickListener(this);
    }


    /**
     * v2
     */
    /**
     *
     */



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
