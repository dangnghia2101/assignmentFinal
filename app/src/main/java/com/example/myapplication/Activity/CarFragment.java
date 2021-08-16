package com.example.myapplication.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.myapplication.Adapter.CarAdapter;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Adapter.MyAdapterKhoXe;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.Topview;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class  CarFragment extends Fragment {
    View view;
    private RecyclerView myRecyclerView;
    private List<Car> list;

    public CarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.fargment_car_recyclerview);

        MyAdapterKhoXe adapter = new MyAdapterKhoXe(getContext(), list);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        list =(new CarDAO(getContext())).get();
    }

    public void updateList(List<Car> list){

        myRecyclerView = (RecyclerView) view.findViewById(R.id.fargment_car_recyclerview);

        MyAdapterKhoXe adapter = new MyAdapterKhoXe(getContext(), list);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(adapter);
    }
}