package com.example.myapplication.Activity.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.myapplication.Activity.AddCarActivity;
import com.example.myapplication.Activity.ListCarActivity;
import com.example.myapplication.Adapter.CarAdapter;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.Topview;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeFragment extends Fragment {
    CarAdapter adapter;
    List<Car> listCar;
    List<Topview> listTop;
    SmoothBottomBar smoothBottomBar;
    //    ListView listView;
    SwipeMenuListView listView;
    ImageButton btnAddCar;
    RecyclerView recyclerView;

    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        anhxa(root);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        //Tạo swipmenu
        swipmenu();

        //Cập nhật lại listview
        upgrateListView();

        //Cập nhật recycleview
        upgrateRecycleview();

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddCarActivity.class));
                upgrateRecycleview();
            }
        });

        // Xử lí sự kiện click menu của listview
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.dialog_fixlistcar);

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.8);
                        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.7);
                        dialog.getWindow().setLayout(width,height);

                        // Ánh xạ
                        ImageView edtImage = (ImageView) dialog.findViewById(R.id.imvFixListCar);
                        EditText edtName = (EditText) dialog.findViewById(R.id.edtFixNameListCar);
                        EditText edtLoaiXe = (EditText) dialog.findViewById(R.id.edtFixLoaiListCar);
                        EditText edtGhiChu = (EditText) dialog.findViewById(R.id.edtFixGhiChuListCar);
                        EditText edtNgayNhap = (EditText) dialog.findViewById(R.id.edtFixNgayNhapListCar);
                        Button btnSave = (Button) dialog.findViewById(R.id.btnFixLuuListCar);

                        //Gán hint cho các editext
                        edtName.setHint(listCar.get(position).getName());
                        edtLoaiXe.setHint(listCar.get(position).getCategory());
                        edtGhiChu.setHint(listCar.get(position).getNote());
                        edtNgayNhap.setHint(listCar.get(position).getDInput());

                        byte[] imageByte = listCar.get(position).getImage();
                        if(imageByte == null){
                            edtImage.setImageResource(R.drawable.car1);
                        }else{
                            edtImage.setImageBitmap(BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length));
                        }

                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String id = listCar.get(position).getId();
                                byte[] image = listCar.get(position).getImage();
                                String name = edtName.getText().toString();
                                String loaiXe = edtLoaiXe.getText().toString();
                                String ghiChu = edtGhiChu.getText().toString();
                                String ngayNhap = edtNgayNhap.getText().toString();

                                if(name.isEmpty()) name = listCar.get(position).getName();
                                if(loaiXe.isEmpty()) loaiXe = listCar.get(position).getCategory();
                                if(ghiChu.isEmpty()) ghiChu = listCar.get(position).getNote();
                                if(ngayNhap.isEmpty()) ngayNhap = listCar.get(position).getDInput();


                                //sqlite
                                Car congTy = new Car(id, name, loaiXe, ngayNhap, ghiChu, image);
                                CarDAO congtynote = new CarDAO(getContext());
                                congtynote.update(congTy);

                                upgrateListView();

                                Toast.makeText(getContext(), "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                        break;

                    case 1:
                        CarDAO dao = new CarDAO(getContext());
                        dao.delete(listCar.get(position).getId());

                        Toast.makeText(getContext(), "Đã xóa car khỏi danh sách", Toast.LENGTH_SHORT).show();
                        //Cập nhật lại listView
                        upgrateListView();

                        //Cập nhật lại recycleview
                        upgrateRecycleview();
                        break;
                }

                return false;
            }
        });

        return root;
    }


    // SwipeMenuListView listView

    public void swipmenu() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());

                // set item width
                openItem.setWidth(200);
                // set item icon
                openItem.setIcon(R.drawable.ic_baseline_auto_fix_high_24);

                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_baseline_delete_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
    }

    //Load recycleview
    private void upgrateRecycleview(){
        listTop = new ArrayList<>();
        for(int i=listCar.size()-1; i>0;i--){
            listTop.add(new Topview(listCar.get(i).getName(), listCar.get(i).getImage()));
            Log.i("===>", listCar.get(i).getImage()+"");
        }

        MyAdapter myAdapter = new MyAdapter(getContext(), listTop);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    //Load lại listview
    private void upgrateListView(){
        listCar =  new ArrayList<>();
        listCar =(new CarDAO(getContext())).get();
        adapter = new CarAdapter( getContext(), R.layout.line_list_car, listCar);
        listView.setAdapter(adapter);
    }


    private void anhxa(View view){
        listView = (SwipeMenuListView) view.findViewById(R.id.listViewListCar);
        btnAddCar = (ImageButton) view.findViewById(R.id.btnAddCar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerListcar);
        //smoothBottomBar = view.findViewById(R.id.smoothBottomBarListCarAc);
    }

    //Lấy danh sách
    public List<Topview> getList(){
        listCar =  new ArrayList<>();
        listCar =(new CarDAO(getContext())).get();

        listTop = new ArrayList<>();
        for(int i=listCar.size()-1; i>0;i--){
            listTop.add(new Topview(listCar.get(i).getName(), listCar.get(i).getImage()));
        }

        return listTop;
    }
}