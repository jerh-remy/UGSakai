package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.Chat;
import com.sakai.ug.sakaiapp.R;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

public class ChatListAdapter extends SelectableAdapter<ChatListAdapter.ViewHolder> {

    private List<Chat> mArrayList;
    private Context mContext;
    private ViewHolder.ClickListener clickListener;


    public ChatListAdapter(Context context, List<Chat> arrayList, ViewHolder.ClickListener clickListener) {
        this.mArrayList = arrayList;
        this.mContext = context;
        this.clickListener = clickListener;

    }

    // Create new views
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_chat, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView, clickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.tvName.setText(mArrayList.get(position).getName());
        viewHolder.tvTime.setText(mArrayList.get(position).getTime());
        viewHolder.userPhoto.setImageResource(mArrayList.get(position).getImage());
        viewHolder.tvLastChat.setText(mArrayList.get(position).getLastChat());

      /*  if (isSelected(position)) {
            viewHolder.checked.setChecked(true);
            viewHolder.checked.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checked.setChecked(false);
            viewHolder.checked.setVisibility(View.GONE);
        }

        if (mArrayList.get(position).getOnline()) {
            viewHolder.onlineView.setVisibility(View.VISIBLE);
        } else
            viewHolder.onlineView.setVisibility(View.INVISIBLE);*/

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvName;
        public TextView tvTime;
        public TextView tvLastChat;
        public ImageView userPhoto;
        //public boolean online = false;
        //private final View onlineView;
        //public CheckBox checked;
        private ClickListener listener;
        //private final View selectedOverlay;


        public ViewHolder(View itemLayoutView, ClickListener listener) {
            super(itemLayoutView);

            this.listener = listener;

            tvName = itemLayoutView.findViewById(R.id.tv_user_name);
            //selectedOverlay = (View) itemView.findViewById(R.id.selected_overlay);
            tvTime = itemLayoutView.findViewById(R.id.tv_time);
            tvLastChat = itemLayoutView.findViewById(R.id.tv_last_chat);
            userPhoto = itemLayoutView.findViewById(R.id.iv_user_photo);
            //onlineView = itemLayoutView.findViewById(R.id.online_indicator);
            //checked = (CheckBox) itemLayoutView.findViewById(R.id.chk_list);

            itemLayoutView.setOnClickListener(this);

            itemLayoutView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener != null) {
                return listener.onItemLongClicked(getAdapterPosition());
            }
            return false;
        }

        public interface ClickListener {
            public void onItemClicked(int position);

            public boolean onItemLongClicked(int position);

            //boolean onCreateOptionsMenu(Menu menu);
        }
    }
}


