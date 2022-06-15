package com.example.recyclerist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context context;
    ArrayList<UserModel> mylist;

    public RecyclerAdapter(Context context, ArrayList<UserModel> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
    holder.image.setImageResource(mylist.get(position).getImage());
    holder.user.setText(mylist.get(position).getName());
    holder.email.setText(mylist.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }
}
