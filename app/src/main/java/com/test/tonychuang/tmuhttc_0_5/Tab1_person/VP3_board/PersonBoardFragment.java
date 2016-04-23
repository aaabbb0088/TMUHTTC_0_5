package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP3_board;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonBoardFragment extends Fragment {


    private ListView CtrNotLv;
    private ListView PsnNotLv;
    private RadioGroup NotRGroup;
    private View view;


    public PersonBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person_board, container, false);

        initView();
        initListener();

        return view;
    }


    /**
     * v1
     */
    /**
     *
     */
    private void initView() {
        CtrNotLv = (ListView) view.findViewById(R.id.CtrNotLv);
        PsnNotLv = (ListView) view.findViewById(R.id.PsnNotLv);
        NotRGroup = (RadioGroup) view.findViewById(R.id.NotRGroup);
        NotRGroup.check(R.id.CtrButton);
    }

    private void initListener() {
        NotRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.CtrButton:
                        CtrNotLv.setVisibility(View.VISIBLE);
                        PsnNotLv.setVisibility(View.GONE);
                        break;
                    case R.id.PsnButton:
                        CtrNotLv.setVisibility(View.GONE);
                        PsnNotLv.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
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
