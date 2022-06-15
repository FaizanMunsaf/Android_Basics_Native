package com.example.practiceset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    String[] username;
    String[] email;
    int[] image;
    Context context;

    public CustomAdapter(String[] username, String[] email, int[] image, Context context) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.context = context;
    }


    @Override
    public int getCount() {
        return email.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.customlayout,viewGroup,false);
        TextView Email = view.findViewById(R.id.etemail);
        TextView tvusername = view.findViewById(R.id.tvusername);
        ImageView imgview = view.findViewById(R.id.image);

        tvEmail.setText(email[i]);
        tvusername.setText(username[i]);
        imgview.setImageResource(image[i]);

        return view;

    }
}
