package com.example.myapplication.DAO;

import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.Topview;

import java.util.List;

public interface ITopDAO {
    List<Topview> get();
    Car get(String Id_Car);
    void insert(Topview car);
    void update(Topview car);
    void delete(String Id_Car);
}
