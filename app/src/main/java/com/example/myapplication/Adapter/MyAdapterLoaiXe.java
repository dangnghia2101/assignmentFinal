package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.CarFragment;
import com.example.myapplication.Activity.LoaiCarFragment;
import com.example.myapplication.Activity.XuatLoaiCar;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.DAO.LoaiCarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.LoaiCar;
import com.example.myapplication.Model.XuatXe;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterLoaiXe extends RecyclerView.Adapter<MyAdapterLoaiXe.myAdapterLoaiXe> implements View.OnClickListener {
    private List<LoaiCar> list;
    private Context context;
    Dialog myDialog;

    public MyAdapterLoaiXe(List<LoaiCar> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public myAdapterLoaiXe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loaicar, parent, false);

        return new myAdapterLoaiXe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapterLoaiXe holder, int position) {
        LoaiCar loaiCar = list.get(position);

        holder.txtName.setText(loaiCar.getNameCar());
        holder.txtDetaiCar.setText(loaiCar.getDetailCar());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int vt = holder.getAdapterPosition();
                LoaiCarDAO dao =  new LoaiCarDAO(context);
                dao.delete(list.get(vt).getNameCar());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                removeItem(vt);
            }
        });

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_chinhsua_loaixe);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.8);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.3);
        myDialog.getWindow().setLayout(width,height);

        holder.btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtDetail = myDialog.findViewById(R.id.edtDetailDialogLoaiXe);
                Button btnSua =   myDialog.findViewById(R.id.btnHoanTatSuaLoaiCar);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String detail = edtDetail.getText().toString().trim();
                        if(detail.isEmpty()){
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            int vt = holder.getAdapterPosition();
                            LoaiCarDAO dao = new LoaiCarDAO(context);
                            dao.update(new LoaiCar(list.get(vt).getNameCar(), detail));
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            myDialog.dismiss();
                            updateData(dao.get());
                        }
                    }
                });

                myDialog.show();
            }

        });

        holder.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog myDialog1 = new Dialog(context);
                myDialog1.setContentView(R.layout.dialog_xecualoai);

                myDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.8);
                int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.8);
                myDialog1.getWindow().setLayout(width,height);

                RecyclerView recyclerView = myDialog1.findViewById(R.id.fargment_car_recyclerview);

                int vt = holder.getAdapterPosition();
                XuatLoaiCar xuatLoaiCar = new XuatLoaiCar();
                xuatLoaiCar.xuatList(list.get(vt).getNameCar());


//                MyAdapterKhoXe adapter = new MyAdapterKhoXe(context, list);
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                recyclerView.setAdapter(adapter);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class myAdapterLoaiXe extends RecyclerView.ViewHolder {
        TextView txtName, txtDetaiCar;
        ImageButton btnDelete, btnChinhSua, btnSearch;
        public myAdapterLoaiXe(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_nameLoaiCar);
            txtDetaiCar = itemView.findViewById(R.id.item_detailLoaiCar);
            btnChinhSua = itemView.findViewById(R.id.btnChinhSuaLoaiCar);
            btnDelete = itemView.findViewById(R.id.btnDeleteLoaiCar);
            btnSearch = itemView.findViewById(R.id.btnSearchItemLoaiCar);
        }
    }


    public void updateData(List<LoaiCar> viewModels) {
        list.clear();
        list.addAll(viewModels);
        notifyDataSetChanged();
    }
    public void addItem(int position, LoaiCar viewModel) {
        list.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
