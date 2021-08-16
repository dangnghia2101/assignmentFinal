package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.DAO.HISTORYDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.XuatXe;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class MyAdapterKhoXe extends RecyclerView.Adapter<ViewHolderKhoXe>{
    Context context;
    List<Car> list;
    Dialog myDialog;
    List<XuatXe> listHis;

    public MyAdapterKhoXe(Context context, List<Car> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderKhoXe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_listcar_khoxe, parent, false);
        ViewHolderKhoXe vHolder = new ViewHolderKhoXe(v);

        //Dialog ini
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_xuatxe);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.8);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.8);
        myDialog.getWindow().setLayout(width,height);


        vHolder.ibtnXuatKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnXuat = (Button) myDialog.findViewById(R.id.btnDialogXuat);
                EditText dTenNhan = (EditText) myDialog.findViewById(R.id.edtTenNhanDialogXuat);
                EditText dSdt = (EditText) myDialog.findViewById(R.id.edtSdtNhanDialogXuat);
                EditText dDiachi = (EditText) myDialog.findViewById(R.id.edtDiaChiDialogXuat);
                EditText dPhi = (EditText) myDialog.findViewById(R.id.edtPhiDialogXuat);
                TextView dTenXe = (TextView) myDialog.findViewById(R.id.txtNameCarXuatXe);
                ImageView dImv = (ImageView) myDialog.findViewById(R.id.imvImgDialogXuat);

                // Custom EditText nhập tiền tẹ
                dPhi.addTextChangedListener(new TextWatcher() {
                    private String current = "";
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals("")) {
                            if (!s.toString().equals(current)) {

                                String cleanString = s.toString().replaceAll("[,.]", "");

                                double parsed = Double.parseDouble(cleanString);

                                String formated = NumberFormat.getInstance().format((parsed));

                                current = formated;

                                dPhi.setText(formated);
                                dPhi.setSelection(formated.length());
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                // Gán giá trị cho hình và tên xe
                int vt = vHolder.getAdapterPosition();
                dTenXe.setText(list.get(vt).getName());
                if(list.get(vt).getImage() == null){
                    dImv.setImageResource(R.drawable.car1);
                }else {
                    dImv.setImageBitmap(BitmapFactory.decodeByteArray(list.get(vt).getImage(), 0, list.get(vt).getImage().length));
                }

                //CLick xác nhận xuất xe
                btnXuat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Kiểm tra ô nhập
                        String tenNguoiNhan = dTenNhan.getText().toString().trim();
                        String tenXe = dTenXe.getText().toString().trim();
                        String diaChiNhan = dDiachi.getText().toString().trim();

                        String tienPhiCoPhay = dPhi.getText().toString().trim();
                        tienPhiCoPhay = tienPhiCoPhay.replace(",","");
                        int tienPhi = Integer.parseInt(tienPhiCoPhay);

                        //Loại xe
                        String categry = list.get(vt).getCategory();
                        // SDT
                        String soDienThoai = dSdt.getText().toString().trim();
                        //Lấy ngày
                        long millis=System.currentTimeMillis();
                        java.sql.Date date=new java.sql.Date(millis);

                        if(tenNguoiNhan.isEmpty() || diaChiNhan.isEmpty() || tienPhi == 0 || soDienThoai.isEmpty()){
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            //Xóa xe khỏi database CAR
                            CarDAO carDAO = new CarDAO(context);
                            carDAO.delete(list.get(vt).getId());

                            //Thêm vào lịch sử
                            HISTORYDAO historydao = new HISTORYDAO(context);
                            historydao.insert(new XuatXe(UUID.randomUUID().toString(), tenNguoiNhan, tenXe, soDienThoai, diaChiNhan, tienPhi, date+"", imageViewToByte(dImv), categry));

                            //new OutCarActivity();

                            myDialog.dismiss();
                            Toast.makeText(context, "Xuất xe thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myDialog.show();
            }
        });

        return vHolder;
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKhoXe holder, int position) {
        Car car = list.get(position);

        if(car.getImage() == null){
            holder.imgCar.setImageResource(R.drawable.car1);
        }else{
            holder.imgCar.setImageBitmap(BitmapFactory.decodeByteArray(car.getImage(), 0, car.getImage().length));
        }

        holder.txtNameCar.setText(car.getName());
        holder.txtLoaiXe.setText(car.getCategory());
        holder.txtGhiChu.setText(car.getNote());
        holder.txtNgayNhap.setText(car.getDInput());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
