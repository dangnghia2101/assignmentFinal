package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Name.AppConstant;
import android.database.CursorWindow;
import java.lang.reflect.Field;


import java.util.ArrayList;
import java.util.List;

public class CarDAO implements ICarDao{
    Database database;
    SQLiteDatabase db;

    public CarDAO(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();
    }

    @Override
    public List<Car> get() {
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {

        }

        List<Car> list = new ArrayList<>();
        SQLiteDatabase db=database.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ AppConstant.TABLE_CAR +"",null);
        while (cursor.moveToNext()){
            String Id=cursor.getString(0);
            String NameCar=cursor.getString(1);
            String LoaiXe=cursor.getString(2);
            String DInput=cursor.getString(3);
            String Note=cursor.getString(4);
            byte[] Image=cursor.getBlob(5);
            list.add(new Car(Id, NameCar, LoaiXe, DInput, Note, Image));
        }
        cursor.close();
        return list;
    }

    @Override
    public List<Car> get(String loai_car) {
        Car car = null;
        List<Car> list = new ArrayList<>();
        SQLiteDatabase db=database.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ AppConstant.TABLE_CAR +" where "+AppConstant.CATEGORY_CAR+" = ?",new String[]{loai_car});
        while (cursor.moveToNext()){
            String Id=cursor.getString(0);
            String NameCar=cursor.getString(1);
            String LoaiXe=cursor.getString(2);
            String DInput=cursor.getString(3);
            String Note=cursor.getString(4);
            byte[] Image=cursor.getBlob(5);
            list.add(new Car(Id, NameCar, LoaiXe, DInput, Note, Image));
        }
        cursor.close();
        return list;
    }

    @Override
    public void insert(Car car) {
        db = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstant.ID_CAR , car.getId());
        values.put(AppConstant.NAME_CAR , car.getName());
        values.put(AppConstant.CATEGORY_CAR, car.getCategory());
        values.put(AppConstant.DINPUT_CAR, car.getDInput());
        values.put(AppConstant.NOTE_CAR, car.getNote());
        values.put(AppConstant.IMAGE_CAR, car.getImage());
        db.insert(AppConstant.TABLE_CAR, null,values);
    }

    @Override
    public void update(Car car) {
        db = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppConstant.ID_CAR , car.getId());
        values.put(AppConstant.NAME_CAR , car.getName());
        values.put(AppConstant.CATEGORY_CAR, car.getCategory());
        values.put(AppConstant.DINPUT_CAR, car.getDInput());
        values.put(AppConstant.NOTE_CAR, car.getNote());
        values.put(AppConstant.IMAGE_CAR, car.getImage());
        db.update(AppConstant.TABLE_CAR, values, AppConstant.ID_CAR + " = ?", new String[]{car.getId()});
    }

    @Override
    public void delete(String Id) {
        db= database.getReadableDatabase();
        String[] params = new String[]{Id};
        db.delete(AppConstant.TABLE_CAR, ""+AppConstant.ID_CAR+" = ?", params);
    }

    @Override
    public int coutCar() {
        db= database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+AppConstant.TABLE_CAR+"", null);
    }

    @Override
    public int countSport() {
        db = database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+AppConstant.TABLE_CAR+" where "+ AppConstant.CATEGORY_CAR+" = 'Sport'", null);
    }

    @Override
    public int countNormal() {
        db = database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+AppConstant.TABLE_CAR+" where "+ AppConstant.CATEGORY_CAR+" = 'Normal'", null);
    }
}
