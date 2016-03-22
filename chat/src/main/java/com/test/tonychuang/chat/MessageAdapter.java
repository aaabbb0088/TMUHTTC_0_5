package com.test.tonychuang.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.tonychuang.chat.ChatActivity.OnChatItemClickListener;

import org.kymjs.kjframe.KJBitmap;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TonyChuang on 2016/3/15.
 */
public class MessageAdapter extends BaseAdapter {

    private KJBitmap kjb;
    private OnChatItemClickListener listener;

    private Context context;
    private List<Message> data = null;

    public MessageAdapter(Context context, List<Message> list, OnChatItemClickListener listener) {
        super();
        this.context = context;
        this.data = list;
        kjb = new KJBitmap();
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return this.data.get(position).getIsSend() ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }



    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Message message = data.get(position);
        boolean isSend = message.getIsSend();
        boolean readflag = message.getReadflag();

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (isSend) {
                convertView = LayoutInflater.from(context).inflate(R.layout.msg_item_right, null);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.msg_item_left, null);
            }
            viewHolder.sendDateTextView = (TextView) convertView.findViewById(R.id.sendDateTextView);
            viewHolder.sendTimeTextView = (TextView) convertView.findViewById(R.id.sendTimeTextView);
            viewHolder.userAvatarImageView = (CircleImageView) convertView.findViewById(R.id.userAvatarImageView);
            viewHolder.userNameTextView = (TextView) convertView.findViewById(R.id.userNameTextView);
            viewHolder.textTextView = (TextView) convertView.findViewById(R.id.textTextView);
            viewHolder.photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
            viewHolder.faceImageView = (ImageView) convertView.findViewById(R.id.faceImageView);
            viewHolder.failImageView = (ImageView) convertView.findViewById(R.id.failImageView);
            viewHolder.sendingProgressBar = (ProgressBar) convertView.findViewById(R.id.sendingProgressBar);
            viewHolder.readflagTextView = (TextView) convertView.findViewById(R.id.readflagTextView);

            viewHolder.isSend = isSend;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            String dateString = DateFormat.format("yyyy-MM-dd aah:mm", message.getTime()).toString();
            String [] t = dateString.split(" ");
            viewHolder.sendDateTextView.setText(t[0]);
            viewHolder.sendTimeTextView.setText(t[1]);

            if(position == 0){
                viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
            }else{
                //TODO is same day ?
                Message pmsg = data.get(position-1);
                if(inSameDay(pmsg.getTime(), message.getTime())){
                    viewHolder.sendDateTextView.setVisibility(View.GONE);
                }else{
                    viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
                }

            }

        } catch (Exception e) {
        }

//		viewHolder.userNameTextView.setText(message.getFromUserName());  //固定會員自己與中心對話，所以只顯示遠距照護中心稱呼

        if (isSend && readflag) {
            viewHolder.readflagTextView.setVisibility(View.VISIBLE); //顯示已讀未讀
        }


        switch (message.getType()) {
            case 0://text
                if (message.getContent().contains("href")) {
                    viewHolder.textTextView = UrlUtils.handleHtmlText(viewHolder.textTextView, message.getContent());
                } else {
                    viewHolder.textTextView = UrlUtils.handleText(viewHolder.textTextView, message.getContent());
                }
//                viewHolder.textTextView.setText(message.getContent()); 用上面做替換，支援點擊連結切換

                viewHolder.textTextView.setVisibility(View.VISIBLE);
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);

                if(message.getIsSend()){
                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.textTextView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.textTextView);
                    if( message.getSendSucces() != null && !message.getSendSucces()){
                        viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if(message.getState() != null && message.getState() == 0){
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                }else{
                    viewHolder.failImageView.setVisibility(View.GONE);
                    viewHolder.sendingProgressBar.setVisibility(View.GONE);

                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.textTextView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }


                break;
            case 1://photo
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.photoImageView.setVisibility(View.VISIBLE);
                viewHolder.faceImageView.setVisibility(View.GONE);


                //如果内存缓存中有要显示的图片，且要显示的图片不是holder复用的图片，则什么也不做，否则显示一张加载中的图片
                if (kjb.getMemoryCache(message.getContent()) != null && message.getContent() != null &&
                        message.getContent().equals(viewHolder.photoImageView.getTag())) {
                } else {
                    viewHolder.photoImageView.setImageResource(R.drawable.default_head);
                }
                kjb.display(viewHolder.photoImageView, message.getContent(), 300, 300);


//                //TODO set image
//                int id = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
//                viewHolder.photoImageView.setImageResource(id);


                if(message.getIsSend() ){
                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
                    if(message.getSendSucces() != null && !message.getSendSucces()){
                        viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if(message.getState() != null && message.getState() == 0){
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                }else{
                    viewHolder.failImageView.setVisibility(View.GONE);
                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.photoImageView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }


                break;

            case 2://face
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.textTextView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.VISIBLE);
                int resId = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
                viewHolder.faceImageView.setImageResource(resId);

                if(message.getIsSend()){
                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.failImageView.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
                    if(message.getSendSucces() != null && !message.getSendSucces()){
                        viewHolder.failImageView.setVisibility(View.VISIBLE);
                        viewHolder.failImageView.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.failImageView.setVisibility(View.GONE);
                    }

                    if(message.getState() != null && message.getState() == 0){
                        viewHolder.sendingProgressBar.setVisibility(View.VISIBLE);
                        viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                    }else{
                        viewHolder.sendingProgressBar.setVisibility(View.GONE);
                    }

                }else{
                    viewHolder.failImageView.setVisibility(View.GONE);

                    RelativeLayout.LayoutParams sendTimeTextViewLayoutParams = (RelativeLayout.LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.faceImageView);
                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                }

                break;

            default:
                viewHolder.textTextView.setText(message.getContent());
                viewHolder.photoImageView.setVisibility(View.GONE);
                viewHolder.faceImageView.setVisibility(View.GONE);
                break;
        }

        if (listener != null) {
            viewHolder.textTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTextClick(position);
                }
            });
            viewHolder.photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPhotoClick(position);
                }
            });
            viewHolder.faceImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFaceClick(position);
                }
            });
        }

//		viewHolder.textTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        return convertView;
    }


    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }


    public static boolean inSameDay(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        if ((year1 == year2) && (day1 == day2)) {
            return true;
        }
        return false;
    }


    static class ViewHolder {

        public CircleImageView userAvatarImageView;
        public TextView 	sendDateTextView;
        public TextView 	userNameTextView;
        public TextView     readflagTextView;

        public TextView 	textTextView;
        public ImageView 	photoImageView;
        public ImageView 	faceImageView;

        public ImageView 	failImageView;
        public TextView 	sendTimeTextView;
        public ProgressBar 	sendingProgressBar;

        public boolean 		isSend = true;
    }
}
