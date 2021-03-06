package com.test.tonychuang.chat.keyboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.tonychuang.chat.R;

import org.kymjs.kjframe.ui.SupportFragment;

/**
 * 聊天键盘功能界面
 * Created by TonyChuang on 2016/3/15.
 */
public class ChatFunctionFragment extends SupportFragment {

    private LinearLayout layout1;
    private LinearLayout layout2;

    private OnOperationListener listener;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.chat_item_menu, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        layout1 = (LinearLayout) parentView.findViewById(R.id.chat_menu_images);
        layout2 = (LinearLayout) parentView.findViewById(R.id.chat_menu_photo);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        if (v == layout1) {
            clickMenu(0);
        } else if (v == layout2) {
            clickMenu(1);
        }
    }

    private void clickMenu(int i) {
        if (listener != null) {
            listener.selectedFunction(i);
        }
    }
}
