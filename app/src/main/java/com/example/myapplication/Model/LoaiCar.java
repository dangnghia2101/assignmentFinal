package com.example.myapplication.Model;

public class LoaiCar {
    String nameCar;
    String detailCar;

    public LoaiCar(String nameCar, String detailCar) {
        this.nameCar = nameCar;
        this.detailCar = detailCar;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getDetailCar() {
        return detailCar;
    }

    public void setDetailCar(String detailCar) {
        this.detailCar = detailCar;
    }
}
