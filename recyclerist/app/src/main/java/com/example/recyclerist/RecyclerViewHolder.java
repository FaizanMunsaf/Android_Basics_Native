package com.example.recyclerist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerist.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView email,user;
    public RecyclerViewHolder(@NonNull View itemView) {

        super(itemView);
        image = itemView.findViewById(R.id.image);
        user = itemView.findViewById(R.id.tvusername);
        email = itemView.findViewById(R.id.tvemail);
    }
}
