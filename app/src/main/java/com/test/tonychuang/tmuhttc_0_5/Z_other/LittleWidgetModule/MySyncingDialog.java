package com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * Created by TonyChuang on 2016/3/20.
 */
public class MySyncingDialog {

    private MaterialDialog materialDialog;

    public MySyncingDialog(boolean horizontal, Context context, String content) {
        materialDialog = new MaterialDialog.Builder(context)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .cancelable(false)
                .widgetColorRes(R.color.colorPrimaryDark)
                .build();
    }

    public void show() {
        materialDialog.show();
    }

    public void dismiss(){
        materialDialog.dismiss();
    }
}
