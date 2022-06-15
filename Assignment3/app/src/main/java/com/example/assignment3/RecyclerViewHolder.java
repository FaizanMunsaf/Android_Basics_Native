package com.example.assignment3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView des,user,date;
    public RecyclerViewHolder(@NonNull View itemView) {

        super(itemView);
        date = itemView.findViewById(R.id.tvdate);
        des = itemView.findViewById(R.id.tvdes);
        user = itemView.findViewById(R.id.tvname);
    }
}
