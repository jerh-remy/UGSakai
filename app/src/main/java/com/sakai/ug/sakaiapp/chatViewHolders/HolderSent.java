package com.sakai.ug.sakaiapp.chatViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;

public class HolderSent extends RecyclerView.ViewHolder {

    private TextView time, chatText;

    public HolderSent(View v) {
        super(v);
        time = v.findViewById(R.id.text_message_time);
        chatText = v.findViewById(R.id.text_message_body);
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public TextView getChatText() {
        return chatText;
    }

    public void setChatText(TextView chatText) {
        this.chatText = chatText;
    }
}
