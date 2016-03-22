package com.test.tonychuang.chat.keyboard;

import android.widget.AbsListView;
import android.widget.TextView;

import com.test.tonychuang.chat.R;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * emoji表情界面gridview适配器
 * Created by TonyChuang on 2016/3/15.
 */
public class EmojiAdapter  extends KJAdapter<Emojicon> {
    public EmojiAdapter(AbsListView view, Collection<Emojicon> mDatas) {
        super(view, mDatas, R.layout.chat_item_emoji);
    }

    @Override
    public void convert(AdapterHolder adapterHolder, Emojicon emojicon, boolean b) {
        TextView itemTvEmoji = adapterHolder.getView(R.id.itemEmoji);
        itemTvEmoji.setText(emojicon.getValue());
    }
}
