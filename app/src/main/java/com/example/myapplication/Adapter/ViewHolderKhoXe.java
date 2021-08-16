package com.example.myapplication.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ViewHolderKhoXe extends RecyclerView.ViewHolder {
    public TextView txtNameCar, txtLoaiXe, txtNgayNhap, txtGhiChu;
    public ImageView imgCar;
    public ImageButton ibtnXuatKho;


    public ViewHolderKhoXe(View itemView) {
        super(itemView);
        ibtnXuatKho = itemView.findViewById(R.id.ibtnItemKho);
        txtNameCar = itemView.findViewById(R.id.txtNameCarItemKho);
        txtLoaiXe = itemView.findViewById(R.id.txtLoaiXeItemKho);
        txtNgayNhap = itemView.findViewById(R.id.txtNgayNhapItemKho);
        txtGhiChu = itemView.findViewById(R.id.txtGhiChuItemKho);
        imgCar = itemView.findViewById(R.id.imvCarItemKho);
    }
}
