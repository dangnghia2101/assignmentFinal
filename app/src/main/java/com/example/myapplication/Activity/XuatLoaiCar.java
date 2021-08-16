package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Adapter.MyAdapterKhoXe;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.R;

import java.util.List;

public class XuatLoaiCar extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Car> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_loai_car);

        recyclerView = findViewById(R.id.fargment_car_recyclerview);

        MyAdapterKhoXe adapter = new MyAdapterKhoXe(XuatLoaiCar.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(XuatLoaiCar.this));
        recyclerView.setAdapter(adapter);


    }

    public void xuatList(String maLoai){
        CarDAO dao = new CarDAO(getApplication());
        list = dao.get(maLoai);

        MyAdapterKhoXe adapter = new MyAdapterKhoXe(XuatLoaiCar.this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(XuatLoaiCar.this));
        recyclerView.setAdapter(adapter);
    }
}