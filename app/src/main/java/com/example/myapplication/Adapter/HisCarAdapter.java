package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.XuatXe;
import com.example.myapplication.R;

import java.util.List;

public class HisCarAdapter extends RecyclerView.Adapter<HisCarAdapter.hisCarViewAdapter> {
    private List<XuatXe> list;
    private Context context;

    public HisCarAdapter(List<XuatXe> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HisCarAdapter.hisCarViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_history, parent, false);
        return new hisCarViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HisCarAdapter.hisCarViewAdapter holder, int position) {
        XuatXe xuatXe = list.get(position);

        holder.txtTenXe.setText(xuatXe.getTenXe());
        holder.txtTenNguoiNhan.setText(xuatXe.getTenNhan());
        holder.txtSdt.setText(xuatXe.getSdt());
        holder.txtPhiShip.setText(xuatXe.getTienPhi()+"");
        holder.txtNgayXuat.setText(xuatXe.getNgayXuat());
        holder.txtDiaChiNhan.setText(xuatXe.getDiaChi());
        if(xuatXe.getImage() == null){
            holder.imgXe.setImageResource(R.drawable.car1);
        }else{
            holder.imgXe.setImageBitmap(BitmapFactory.decodeByteArray(xuatXe.getImage(), 0, xuatXe.getImage().length));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hisCarViewAdapter extends RecyclerView.ViewHolder {
        public TextView txtTenXe;
        public TextView txtTenNguoiNhan;
        public TextView txtNgayXuat;
        public TextView txtDiaChiNhan;
        public TextView txtPhiShip;
        public TextView txtSdt;
        public ImageView imgXe;

        public hisCarViewAdapter(@NonNull View view) {
            super(view);

            txtDiaChiNhan = (TextView) view.findViewById(R.id.txtDiaChiNhanHisItem);
            txtNgayXuat = (TextView) view.findViewById(R.id.txtNgayXuatHisItem);
            txtPhiShip = (TextView) view.findViewById(R.id.txtShipHisItem);
            txtSdt = (TextView) view.findViewById(R.id.txtSdtHisCarItem);
            txtTenNguoiNhan = (TextView) view.findViewById(R.id.txtTenNguoiNhanHisCarItem);
            txtTenXe = (TextView) view.findViewById(R.id.txtNameHisCarItem);
            imgXe = (ImageView) view.findViewById(R.id.imvHisCarItem);
        }
    }
}
