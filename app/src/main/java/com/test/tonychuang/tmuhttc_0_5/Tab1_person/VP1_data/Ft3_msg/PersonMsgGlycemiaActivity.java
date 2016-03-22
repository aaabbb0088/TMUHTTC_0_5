package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP1_data.Ft3_msg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.test.tonychuang.chat.keyboard.DisplayRules;
import com.test.tonychuang.chat.keyboard.Emojicon;
import com.test.tonychuang.chat.keyboard.Faceicon;
import com.test.tonychuang.chat.keyboard.KJChatKeyboard;
import com.test.tonychuang.chat.keyboard.OnOperationListener;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonMsgGlycemiaActivity extends AppCompatActivity {


    private KJChatKeyboard box;
    private MyInitReturnBar myInitReturnBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg_glycemia);

        initBar();
        initView();
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initView() {
        box = (KJChatKeyboard) findViewById(R.id.chat_msg_input_box);
        initMsgInputToolBox();
    }

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "我的血糖留言板", 0);
    }

    private void initMsgInputToolBox() {
        box.getmBtnMore().setVisibility(View.GONE);
        final TextView textView45 = (TextView) findViewById(R.id.textView134);
        box.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                textView45.setText(content);
            }

            @Override
            public void selectedFace(Faceicon content) {
            }

            @Override
            public void selectedEmoji(Emojicon emoji) {
                box.getEditTextBox().append(emoji.getValue());
            }

            @Override
            public void selectedBackSpace(Emojicon back) {
                DisplayRules.backspace(box.getEditTextBox());
            }

            @Override
            public void selectedFunction(int index) {
            }
        });

        List<String> faceCagegory = new ArrayList<>();
//        File faceList = FileUtils.getSaveFolder("chat");
        File faceList = new File("");
        if (faceList.isDirectory()) {
            File[] faceFolderArray = faceList.listFiles();
            for (File folder : faceFolderArray) {
                if (!folder.isHidden()) {
                    faceCagegory.add(folder.getAbsolutePath());
                }
            }
        }
        box.setFaceData(faceCagegory);
        textView45.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hideLayout();
                box.hideKeyboard(PersonMsgGlycemiaActivity.this);
                return false;
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

