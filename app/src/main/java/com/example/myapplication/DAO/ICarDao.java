package com.example.myapplication.DAO;

import com.example.myapplication.Model.Car;

import java.util.List;

public interface ICarDao {
    List<Car> get();
    List<Car> get(String loai_xe);
    void insert(Car car);
    void update(Car car);
    void delete(String Id_Car);
    int coutCar();
    int countSport();
    int countNormal();
}
