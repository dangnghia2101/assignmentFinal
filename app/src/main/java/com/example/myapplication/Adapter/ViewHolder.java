package com.example.myapplication.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ViewHolder extends  RecyclerView.ViewHolder{
    public TextView txtName;
    public ImageView img;

    public ViewHolder(View itemView) {
        super(itemView);
        this.txtName = itemView.findViewById(R.id.txtNameCountry);
        this.img = itemView.findViewById(R.id.imvCar);
    }
}
