package com.example.myapplication.DAO;

import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.LoaiCar;

import java.util.List;

public interface ILoaiCarDAO {
    List<LoaiCar> get();
    void insert(LoaiCar car);
    void update(LoaiCar car);
    void delete(String Id_LoaiCar);
}
