package com.sakai.ug.sakaiapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.chatViewHolders.HolderReceived;
import com.sakai.ug.sakaiapp.chatViewHolders.HolderSent;

import java.util.List;

public class ChatConversationRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private List<ChatData> items;
    private Context mContext;

    private final int YOU = 0, ME = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatConversationRecyclerView(Context context, List<ChatData> items) {
        this.mContext = context;
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //More to come
        if (items.get(position).getType().equals("0")) {
            return YOU;
        } else if (items.get(position).getType().equals("1")) {
            return ME;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case YOU:
                View v1 = inflater.inflate(R.layout.item_message_received, viewGroup, false);
                viewHolder = new HolderReceived(v1);
                break;
            case ME:
                View v2 = inflater.inflate(R.layout.item_message_sent, viewGroup, false);
                viewHolder = new HolderSent(v2);
                break;
        }
        return viewHolder;
    }

    public void addItem(List<ChatData> item) {
        items.addAll(item);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ME:
                HolderSent vh1 = (HolderSent) viewHolder;
                configureViewHolder(vh1, position);
                break;
            case YOU:
                HolderReceived vh2 = (HolderReceived) viewHolder;
                configureViewHolder2(vh2, position);
                break;
        }
    }

    private void configureViewHolder(HolderSent vh1, int position) {
        vh1.getTime().setText(items.get(position).getTime());
        vh1.getChatText().setText(items.get(position).getText());
    }

    private void configureViewHolder2(HolderReceived vh1, int position) {
        vh1.getTime().setText(items.get(position).getTime());
        vh1.getChatText().setText(items.get(position).getText());
    }

}


