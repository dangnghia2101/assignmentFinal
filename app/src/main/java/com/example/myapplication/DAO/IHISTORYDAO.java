package com.example.myapplication.DAO;

import com.example.myapplication.Model.XuatXe;

import java.util.List;

public interface IHISTORYDAO {
    List<XuatXe> get();
    void insert(XuatXe _xuatXe);
    void delete(String _id);
    int courtCar();
    int countSport();
    int countNormal();
    String tongDoanhThu();
}
