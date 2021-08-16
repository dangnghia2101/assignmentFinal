package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Model.Car;
import com.example.myapplication.Model.LoaiCar;
import com.example.myapplication.Name.AppConstant;

import static com.example.myapplication.Name.AppConstant.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LoaiCarDAO implements ILoaiCarDAO{
    Database database;
    SQLiteDatabase db;

    public LoaiCarDAO(Context context) {
        database = new Database(context);
        db = database.getWritableDatabase();
    }


    @Override
    public List<LoaiCar> get() {
        List<LoaiCar> list = new ArrayList<>();
        SQLiteDatabase db=database.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_LOAICAR +"",null);
        while (cursor.moveToNext()){
            String NameLoaiCar=cursor.getString(0);
            String DetailLoaiCar=cursor.getString(1);
            list.add(new LoaiCar(NameLoaiCar, DetailLoaiCar));
        }
        cursor.close();
        return list;
    }

    @Override
    public void insert(LoaiCar car) {
        db = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_LOAICAR , car.getNameCar());
        values.put(DETAIL_LOAICAR , car.getDetailCar());
        db.insert(TABLE_LOAICAR, null,values);
    }

    @Override
    public void update(LoaiCar car) {
        db = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_LOAICAR , car.getNameCar());
        values.put(DETAIL_LOAICAR, car.getDetailCar());
        db.update(TABLE_LOAICAR, values, NAME_LOAICAR + " = ?", new String[]{car.getNameCar()});
    }

    @Override
    public void delete(String Id_LoaiCar) {
        db= database.getReadableDatabase();
        String[] params = new String[]{Id_LoaiCar};
        db.delete(TABLE_LOAICAR, ""+NAME_LOAICAR+" = ?", params);
    }
}
