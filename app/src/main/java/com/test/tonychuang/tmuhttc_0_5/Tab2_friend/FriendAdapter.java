package com.test.tonychuang.tmuhttc_0_5.Tab2_friend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft2_map.FriendMapActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft3_personal.FriendPersonalActivity;
import com.test.tonychuang.tmuhttc_0_5.Tab2_friend.Ft4_setting.FriendSettingActivity;

import java.util.List;

/**
 * Created by vienan on 2015/9/17.
 */
public class FriendAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater = null;
    private List<GroupEntity> groupList;
    private Context context;
    SwipeLayout currentExpandedSwipeLayout;

    private Handler handler;

    /**
     * 构造方法
     *
     * @param context
     * @param group_list
     */
    public FriendAdapter(Context context, List<GroupEntity> group_list) {
        this.context = context;
        this.groupList = group_list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /**
         * 更新adapt資料
         */
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                notifyDataSetChanged();
//                super.handleMessage(msg);
//            }
//        };
    }

    /**
     * 更新adapt資料調用函式
     */
//    public void refresh() {
//        handler.sendMessage(new Message());
//    }


    /**
     * 返回一级Item总数
     */
    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return groupList.size();
    }

    /**
     * 返回二级Item总数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupList.get(groupPosition).getChildEntities() == null) {
            return 0;
        } else {
            return groupList.get(groupPosition).getChildEntities().size();
        }
    }

    /**
     * 获取一级Item内容
     */
    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return groupList.get(groupPosition);
    }

    /**
     * 获取二级Item内容
     */
    @Override
    public ChildEntity getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).getChildEntities().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final GroupViewHolder holder;
        if (convertView != null) {
            holder = (GroupViewHolder) convertView.getTag();
        } else {
            holder = new GroupViewHolder();
            convertView = inflater.inflate(R.layout.friend_listview_item_group, null);
            holder.groupName = (TextView) convertView.findViewById(R.id.one_status_name);
            holder.groupName.setText(groupList.get(groupPosition).getGroupName());
            holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewWithTag("Edit"));
            holder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
            holder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
            convertView.setTag(holder);
        }

        convertView.findViewById(R.id.item_surface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FriendFragment.expandableListView.isGroupExpanded(groupPosition)) {
                    FriendFragment.expandableListView.collapseGroup(groupPosition);
                } else {
                    FriendFragment.expandableListView.expandGroup(groupPosition);
                }
            }
        });

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGroupAlertDialog();
                holder.swipeLayout.close();
            }
        });

        holder.iv_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletGroupCheckAlertDialog();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder viewHolder;
        final ChildEntity entity = getChild(groupPosition, childPosition);
        if (convertView != null) {
            viewHolder = (ChildViewHolder) convertView.getTag();
        } else {
            viewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.friend_listview_item_child, null);
            viewHolder.childTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
            viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewWithTag("Edit"));
            viewHolder.iv_edit = (ImageView) convertView.findViewById(R.id.edit);
            viewHolder.iv_trash = (ImageView) convertView.findViewById(R.id.trash);
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewWithTag("Map"));
            viewHolder.iv_map = (ImageView) convertView.findViewById(R.id.map);
            convertView.setTag(viewHolder);
        }
        if (entity != null) {
            viewHolder.childTitle.setText(entity.getChildTitle());
            viewHolder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    toast("click edit");
                    //do something
                    new AsyncTask<Class<?>, Void, String>() {
                        @Override
                        protected String doInBackground(Class<?>... params) {
                            Intent intent = new Intent(context, params[0]);
                            context.startActivity(intent);
                            return "finish";
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            viewHolder.swipeLayout.close();
                        }
                    }.execute(FriendSettingActivity.class);
                }
            });
            viewHolder.iv_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletfriendCheckAlertDialog();
                    notifyDataSetChanged();
                    //do something
                }
            });
            viewHolder.iv_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    toast("click map");
                    //do something
                    new AsyncTask<Class<?>, Void, String>() {
                        @Override
                        protected String doInBackground(Class<?>... params) {
                            Intent intent = new Intent(context, params[0]);
                            context.startActivity(intent);
                            return "finish";
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            viewHolder.swipeLayout.close();
                        }
                    }.execute(FriendMapActivity.class);
                }
            });
            convertView.findViewById(R.id.item_surface).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast(entity.getChildTitle());
                    //do something
                    Intent intent = new Intent(context, FriendPersonalActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }

    public static class GroupViewHolder {
        public SwipeLayout swipeLayout;
        public ImageView iv_trash, iv_edit;
        public TextView groupName;
    }

    public static class ChildViewHolder {
        public SwipeLayout swipeLayout;
        public ImageView iv_trash, iv_edit, iv_map;
        public TextView childTitle;
    }

    private void editGroupAlertDialog() {
        View dialogTitleView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_group_title, null);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_group_body, null);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final MaterialEditText groupNameEd = (MaterialEditText) dialogView.findViewById(R.id.groupNameEd);
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setCustomTitle(dialogTitleView)
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        groupNameEd.setHint("群組原本名稱");
        confirmTv.setEnabled(false);
        groupNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(groupNameEd.getText().toString().trim())) {
                    confirmTv.setEnabled(true);
                } else {
                    confirmTv.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void deletGroupCheckAlertDialog() {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_friend_group_body, null);
        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("刪除群組")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        msgTv.setText("確定要刪除 \"群組名稱\"群組 嗎?");
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void deletfriendCheckAlertDialog() {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_friend_group_body, null);
        TextView msgTv = (TextView) dialogView.findViewById(R.id.msgTv);
        final TextView confirmTv = (TextView) dialogView.findViewById(R.id.confirmTv);
        TextView cancelTv = (TextView) dialogView.findViewById(R.id.cancelTv);
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("刪除群組")
                .setView(dialogView)
                .setOnKeyListener(getOnKeyListener())
                .setCancelable(false).create();
        alertDialog.show();

        msgTv.setText("確定要刪除 \"好友暱稱\" 嗎?");
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    private DialogInterface.OnKeyListener getOnKeyListener(){
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    dialog.dismiss();
                }
                return false;
            }
        };
    }
}