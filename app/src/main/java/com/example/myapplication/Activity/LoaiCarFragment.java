package com.example.myapplication.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.Adapter.MyAdapterKhoXe;
import com.example.myapplication.Adapter.MyAdapterLoaiXe;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.DAO.LoaiCarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.LoaiCar;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiCarFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    private RecyclerView myRecyclerView;
    private ImageButton btnAdd;
    private List<LoaiCar> list;

    Dialog myDialog;

    public LoaiCarFragment() {
        // Required empty public constructor
    }

    public static LoaiCarFragment newInstance(String param1, String param2) {
        LoaiCarFragment fragment = new LoaiCarFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list =(new LoaiCarDAO(getContext())).get();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_car, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.rc_loaiCar);

        btnAdd = view.findViewById(R.id.btnAddLoaiCar);

        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dialog_add_loaicar);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        int width = (int)(getContext().getResources().getDisplayMetrics().widthPixels*0.8);
        int height = (int)(getContext().getResources().getDisplayMetrics().heightPixels*0.4);
        myDialog.getWindow().setLayout(width,height);
        EditText edtName = myDialog.findViewById(R.id.edtAddNameLoaiXe);
        EditText edtDetail = myDialog.findViewById(R.id.edtAddDetailLoaiCar);
        Button btnAddDB = myDialog.findViewById(R.id.btnHoanTatAddLoaiCar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddDB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString().trim();
                        String detail = edtDetail.getText().toString().trim();

                        if(name.isEmpty() && detail.isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            LoaiCarDAO dao = new LoaiCarDAO(getContext());
                            dao.insert(new LoaiCar(name, detail));
                            MyAdapterLoaiXe adapter = new MyAdapterLoaiXe(dao.get(), getContext());
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            myRecyclerView.setAdapter(adapter);
                            Toast.makeText(getContext(), "Add thành công", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            myDialog.dismiss();
                        }
                    }
                });

                myDialog.show();
            }
        });

        MyAdapterLoaiXe adapter = new MyAdapterLoaiXe(list, getContext());
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(adapter);

        return view;
    }

    public void upgrateRcView(){

    }

    public void chuyenmanhinh(){
        startActivity(new Intent(getContext(), XuatLoaiCar.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), id+"", Toast.LENGTH_SHORT).show();
    }
}