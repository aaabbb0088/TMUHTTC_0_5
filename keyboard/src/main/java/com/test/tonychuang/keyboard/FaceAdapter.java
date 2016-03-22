package com.test.tonychuang.keyboard;

import android.widget.AbsListView;
import android.widget.ImageView;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by TonyChuang on 2016/3/13.
 */
public class FaceAdapter extends KJAdapter<Faceicon> {
    private KJBitmap kjb = new KJBitmap();

    public FaceAdapter(AbsListView view, Collection<Faceicon> mDatas) {
        super(view, mDatas, R.layout.chat_item_face);
    }

    @Override
    public void convert(AdapterHolder adapterHolder, Faceicon data, boolean b) {
        ImageView view = adapterHolder.getView(R.id.itemImage);
//        int id = view.getResources().getIdentifier(s,
//                "drawable", view.getContext().getPackageName());
//        view.setImageResource(id);
        kjb.display(view, data.getPath());
    }
}
