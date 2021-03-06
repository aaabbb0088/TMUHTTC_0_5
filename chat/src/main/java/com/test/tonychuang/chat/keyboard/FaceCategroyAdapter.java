package com.test.tonychuang.chat.keyboard;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageView;

import com.test.tonychuang.chat.R;

import org.kymjs.kjframe.bitmap.BitmapCreate;

import java.io.File;
import java.util.List;

/**
 * 控件分类的viewpager适配器
 * Created by TonyChuang on 2016/3/15.
 */
public class FaceCategroyAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private final int sMode;

    //每个item表示一个folder绝对路径，每个folder中存放的是一套face图片
    private List<String> datas;
    private OnOperationListener listener;

    public FaceCategroyAdapter(FragmentManager fm, int mode) {
        super(fm);
        sMode = mode;
    }

    @Override
    public void setPageIcon(int position, ImageView image) {
        if (position == 0) {
            image.setImageResource(R.drawable.icon_face_click);
            return;
        }
        File file = new File(datas.get(position - 1));
        String path = null;
        for (int i = 0; i < file.list().length; i++) {
            path = file.list()[i];
            if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                break;
            }
        }
        Bitmap bitmap = BitmapCreate.bitmapFromFile(file.getAbsolutePath() + "/" + path, 40, 40);
        image.setImageBitmap(bitmap);
    }

    @Override
    public int getCount() {
        if (sMode == KJChatKeyboard.LAYOUT_TYPE_FACE) {
            int count = datas == null ? 0 : datas.size();
            //加1是因为有默认的emoji表情分类
            return (count + 1);
        } else {
            return 1;
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (sMode == KJChatKeyboard.LAYOUT_TYPE_FACE) {
            if (position == 0) {
                f = new EmojiPageFragment();
                ((EmojiPageFragment) f).setOnOperationListener(listener);
            } else {
                position--;
                f = new FacePageFragment();
                ((FacePageFragment) f).setOnOperationListener(listener);
                Bundle bundle = new Bundle();
                bundle.putString(FacePageFragment.FACE_FOLDER_PATH, datas.get(position));
                f.setArguments(bundle);
            }
        } else {
            f = new ChatFunctionFragment();
            ((ChatFunctionFragment) f).setOnOperationListener(listener);
        }
        return f;
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
