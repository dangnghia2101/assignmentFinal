package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.HisCarAdapter;
import com.example.myapplication.DAO.HISTORYDAO;
import com.example.myapplication.Model.XuatXe;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class CarOutFragment extends Fragment {
    View v;
    List<XuatXe> list;
    public CarOutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_outcar, container, false);
        RecyclerView rcView = (RecyclerView) v.findViewById(R.id.rc_history_car);


        HisCarAdapter adapter = new HisCarAdapter(list, getContext());
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(adapter);
        return v ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        list = (new HISTORYDAO(getContext()).get());
    }
}