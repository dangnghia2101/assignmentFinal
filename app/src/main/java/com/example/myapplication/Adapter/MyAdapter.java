package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Topview;
import com.example.myapplication.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<Topview> list;

    public MyAdapter(Context context, List<Topview> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.custum_spiner_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topview topview =list.get(position);
        if(topview.getImage() == null){
            holder.img.setImageResource(R.drawable.car1);
        }else{
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(topview.getImage(), 0, topview.getImage().length));
        }
        holder.txtName.setText(topview.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
