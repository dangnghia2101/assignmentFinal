package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Car;
import com.example.myapplication.R;

import java.util.List;

public class   CarAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Car> listCar;

    public CarAdapter(Context context, int layout, List<Car> listCar) {
        this.context = context;
        this.layout = layout;
        this.listCar = listCar;
    }

    @Override
    public int getCount() {
        return listCar.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNameCar, txtLoaiXe, txtNgayNhap, txtGhiChu;
        ImageView imgCar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtNameCar = (TextView) convertView.findViewById(R.id.txtNameCarItem);
            holder.txtLoaiXe = (TextView) convertView.findViewById(R.id.txtLoaiXeItem);
            holder.txtGhiChu = (TextView) convertView.findViewById(R.id.txtGhiChuItem);
            holder.txtNgayNhap = (TextView) convertView.findViewById(R.id.txtNgayNhapItem);
            holder.imgCar = (ImageView) convertView.findViewById(R.id.imvCarItem);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();

        Car car = listCar.get(position);
        holder.txtNameCar.setText(car.getName());
        holder.txtLoaiXe.setText(car.getCategory());
        holder.txtGhiChu.setText(car.getNote());
        holder.txtNgayNhap.setText(car.getDInput());
        byte[] image = car.getImage();
        if(image == null){
            holder.imgCar.setImageResource(R.drawable.car1);
        }else{
            holder.imgCar.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }

        return convertView;
    }
}
