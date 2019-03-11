package com.sakai.ug.sakaiapp.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakai.ug.sakaiapp.Chat;
import com.sakai.ug.sakaiapp.Conversation;
import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.adapters.ChatListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatFragmentGroup extends Fragment implements ChatListAdapter.ViewHolder.ClickListener {

    private RecyclerView mRecyclerView;
    private ChatListAdapter mAdapter;


   /* public void onCreate(Bundle a) {
        super.onCreate(a);
        setHasOptionsMenu(true);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_group, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ChatListAdapter(getContext(), setData(), this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public List<Chat> setData() {
        List<Chat> data = new ArrayList<>();
        String name[] = { "Julia Harris", "Laura Owens", "Angela Price", "Donald Turner", "Robert Kelly", "Jim Harris", "Laura Owens", "Angela Price", "Donald Turner", "Kelly"};
        String lastchat[] = {"Hi Laura Owens", "Hi there how are you", "How are you?", "How are you doing?", "Owh this awesome", "How are you?", "Can we meet?", "Ow this awesome", "How are you?", "Ow this awesome"};
        @DrawableRes int img[] = {R.drawable.userpic, R.drawable.user1, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.userpic, R.drawable.user1, R.drawable.user2, R.drawable.user3, R.drawable.user4};
        //boolean online[] = {true, false, true, false, true, true, true, false, false, true};

        for (int i = 0; i < 10; i++) {
            Chat chat = new Chat();
            chat.setmTime("5:04pm");
            chat.setName(name[i]);
            chat.setImage(img[i]);
            //chat.setOnline(online[i]);
            chat.setLastChat(lastchat[i]);
            data.add(chat);
        }
        return data;
    }

    @Override
    public void onItemClicked(int position) {
        startActivity(new Intent(getActivity(), Conversation.class));
    }

    @Override
    public boolean onItemLongClicked(int position) {
        //toggleSelection(position);
        return true;
    }


    /*private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        if (mAdapter.getSelectedItemCount() > 0) {
            tv_selection.setVisibility(View.VISIBLE);
        } else
            tv_selection.setVisibility(View.GONE);


        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                tv_selection.setText("Delete (" + mAdapter.getSelectedItemCount() + ")");
            }
        });

    }*/

    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_edit, menu);
    }*/
}

