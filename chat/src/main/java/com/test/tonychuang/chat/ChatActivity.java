package com.test.tonychuang.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.test.tonychuang.chat.keyboard.DisplayRules;
import com.test.tonychuang.chat.keyboard.Emojicon;
import com.test.tonychuang.chat.keyboard.Faceicon;
import com.test.tonychuang.chat.keyboard.KJChatKeyboard;
import com.test.tonychuang.chat.keyboard.OnOperationListener;

import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.KJLoger;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by TonyChuang on 2016/3/15.
 */
public class ChatActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 0x1;

    private KJChatKeyboard box;
    private ListView listView;

    private MessageAdapter adapter;
    private List<Message> messages = new ArrayList<Message>();

    private int mListViewHeight;
    private boolean listViewBottomFlag = false;
    private boolean moveFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initBar();
        initView();
        initMessageInputToolBox();
        initListView();
        initListener();
    }

    private void initBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); // Specify that tabs should be displayed in the action bar.
            //禁用ActionBar標題
            actionBar.setDisplayShowTitleEnabled(false);
            //禁用ActionBar圖標
            actionBar.setDisplayUseLogoEnabled(false);
            //禁用ActionBar返回鍵
            actionBar.setDisplayShowHomeEnabled(false);

            //自定義 ActionBar
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_return, null);
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            FrameLayout actionBarLeftLayout = (FrameLayout) titleView.findViewById(R.id.actionBarLeftLayout);
            actionBarLeftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            actionBarText.setText("遠距照護中心");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initView() {
        box = (KJChatKeyboard) findViewById(R.id.messageInputToolBox);
        listView = (ListView) findViewById(R.id.messageListview);
    }

    private void initListView() {

        //create Data
        Message message = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "Hi", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), true);
        Message message1 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "Hello World", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 8), true);
//        Message message2 = new Message(Message.MSG_TYPE_PHOTO, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "device_2014_08_21_215311", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7), true);
        Message message3 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "Haha", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7), true);
//        Message message4 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "big3", false, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 7), false);
//        Message message5 = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", "big2", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6), true);
        Message message6 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_FAIL, "遠距照護中心", "avatar", "Jerry", "avatar", "test send fail", true, false, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6), false);
        Message message7 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SENDING, "遠距照護中心", "avatar", "Jerry", "avatar", "test sending", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6), false);
        Message message8 = new Message(Message.MSG_TYPE_TEXT, Message.MSG_STATE_SENDING, "遠距照護中心", "avatar", "Jerry", "avatar", "test\n\n\n\n\n\n\n sending", true, true, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 6), false);

        messages.add(message);
        messages.add(message1);
//        messages.add(message2);
        messages.add(message3);
//        messages.add(message4);
//        messages.add(message5);
        messages.add(message6);
        messages.add(message7);
        messages.add(message8);

        adapter = new MessageAdapter(this, messages, getOnChatItemClickListener());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setSelection(adapter.getCount() - 1); //let listview scroll to bottom
    }

    private void initListener() {
        listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mListViewHeight = listView.getHeight();

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    listView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    listView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                moveFlag = scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (firstVisibleItem == 0) {
//                    View firstVisibleItemView = listView.getChildAt(0);
//                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
//                        Log.d("ListView", "<----滾動到頂部----->");
//                    }
//                }
//                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
//                    View lastVisibleItemView = listView.getChildAt(listView.getChildCount() - 1);
//                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == mListViewHeight) {
//                        Log.d("ListView", "#####滾動到底部######");
//                        listViewBottomFlag = true;
//                    }
//                }
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = listView.getChildAt(listView.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == mListViewHeight) {
                        Log.d("ListView", "#####滾動到底部######");
                        listViewBottomFlag = true;
                    } else {
                        listViewBottomFlag = false;
                    }
                } else {
                    listViewBottomFlag = false;
                }
            }
        });

        /**
         * 若软键盘或表情键盘弹起，点击上端空白处应该隐藏输入法键盘
         *
         * @return 会隐藏输入法键盘的触摸事件监听器
         */
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_UP && !moveFlag) {
                    box.hideLayout();
                    box.hideKeyboard(ChatActivity.this);
                    box.getEditTextBox().clearFocus();
                }
                return false;
            }
        });

        box.getEditTextBox().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && listViewBottomFlag) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.setSelection(listView.getBottom());
                        }
                    }, 200);
                }
            }
        });
    }

    private void initMessageInputToolBox() {
        box.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                Message message = new Message(0, 1, "遠距照護中心", "avatar", "Jerry", "avatar", content, true, true, new Date(), true);
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom()); //let listview scroll to bottom

                //Just demo
                createReplayMsg(message);
            }

            @Override
            public void selectedFace(Faceicon content) {
                Message message = new Message(Message.MSG_TYPE_FACE, Message.MSG_STATE_SUCCESS, "遠距照護中心", "avatar", "Jerry", "avatar", content.getPath(), true, true, new Date(), true);
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());

                //Just demo
                createReplayMsg(message);
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
                switch (index) {
                    case 0:
                        goToAlbum();
                        break;
                    case 1:
                        ViewInject.toast("啟動相機");
                        break;
                }
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

        //嘗試設定的code
//        final boolean[] isLastRow = {false};
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //滾動時一直回調，直到停止滾動時才停止回調。單擊時回調一次。
//                //firstVisibleItem：當前能看見的第一個列表項ID（從0開始）
//                //visibleItemCount：當前能看見的列表項個數（小半個也算）
//                //totalItemCount：列表項共數
//
//                //判斷是否滾到最後一行
//                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
//                    isLastRow[0] = true;
//                }else{
//                    isLastRow[0] = false;
//                }
//            }
//        });
//
//        box.getmEtMsg().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isLastRow[0]){
//                    listView.setSelection(listView.getBottom());
//                }
//            }
//        });

    }


    /**
     * 跳转到选择相册界面
     */
    private void goToAlbum() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_GETIMAGE_BYSDCARD);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_GETIMAGE_BYSDCARD);
        }

    }


    private void createReplayMsg(Message message) {

        final Message reMessage = new Message(message.getType(), Message.MSG_STATE_SUCCESS, "Tom",
                "avatar", "Jerry", "avatar", message.getType() == Message.MSG_TYPE_TEXT ? "返回:"
                + message.getContent() : message.getContent(), false,
                true, new Date(), true);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * (new Random().nextInt(3) + 1));
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            adapter.getData().add(reMessage);
                            listView.setSelection(listView.getBottom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * @return 聊天列表内存点击事件监听器
     */
    private OnChatItemClickListener getOnChatItemClickListener() {
        return new OnChatItemClickListener() {
            @Override
            public void onPhotoClick(int position) {
                KJLoger.debug("圖片路徑" + messages.get(position).getContent());
                ViewInject.toast(ChatActivity.this, "圖片路徑" + messages.get(position).getContent());

                box.hideLayout();
                box.hideKeyboard(ChatActivity.this);
                box.getEditTextBox().clearFocus();
            }

            @Override
            public void onTextClick(int position) {

                box.hideLayout();
                box.hideKeyboard(ChatActivity.this);
                box.getEditTextBox().clearFocus();
            }

            @Override
            public void onFaceClick(int position) {

                box.hideLayout();
                box.hideKeyboard(ChatActivity.this);
                box.getEditTextBox().clearFocus();
            }
        };
    }


    /**
     * 聊天列表中对内容的点击事件监听
     */
    public interface OnChatItemClickListener {
        void onPhotoClick(int position);

        void onTextClick(int position);

        void onFaceClick(int position);
    }


    @Override  //切換activity時，傳送圖片訊息
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_GETIMAGE_BYSDCARD) {
            Uri dataUri = data.getData();
            if (dataUri != null) {
                File file = FileUtils.uri2File(ChatActivity.this, dataUri);
                Message message = new Message(Message.MSG_TYPE_PHOTO, Message.MSG_STATE_SUCCESS,
                        "Tom", "avatar", "Jerry",
                        "avatar", file.getAbsolutePath(), true, true, new Date(), true);
                adapter.getData().add(message);
                listView.setSelection(listView.getBottom());
                createReplayMsg(message);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && box.isShow()) {
            box.hideLayout();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
