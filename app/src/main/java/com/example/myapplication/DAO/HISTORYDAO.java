package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Model.XuatXe;
import com.example.myapplication.Name.AppConstant;

import static com.example.myapplication.Name.AppConstant.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HISTORYDAO implements IHISTORYDAO {
    Database database;
    SQLiteDatabase db;


    public HISTORYDAO(Context context) {
        this.database = new Database(context);
        this.db = db;
    }

    @Override
    public List<XuatXe> get() {
        List<XuatXe> list = new ArrayList<>();
        db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM "+TABLE_HISTORY+"", null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String tenXe = cursor.getString(1);
            String tenNhan = cursor.getString(2);
            String sdt = cursor.getString(3);
            String diaChi = cursor.getString(4);
            String ngayXuat = cursor.getString(5);
            int ship = cursor.getInt(6);
            byte[] image = cursor.getBlob(7);
            String category = cursor.getString(8);

            list.add(new XuatXe(id, tenNhan, tenXe, sdt, diaChi, ship, ngayXuat, image, category));
        }

        cursor.close();
        return list;
    }

    @Override
    public void insert(XuatXe _xuatXe) {
        db = database.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put( HIS_ID, _xuatXe.getId());
        values.put( HIS_TENXE, _xuatXe.getTenXe());
        values.put( HIS_TENNHAN, _xuatXe.getTenNhan());
        values.put( HIS_SDT, _xuatXe.getSdt());
        values.put( HIS_DIACHINHAN, _xuatXe.getDiaChi());
        values.put( HIS_DATEOUT, _xuatXe.getNgayXuat());
        values.put( HIS_SHIP, _xuatXe.getTienPhi());
        values.put( HIS_IMAGE, _xuatXe.getImage());
        values.put( HIS_CATEGORY, _xuatXe.getCategory());

        db.insert(TABLE_HISTORY, null, values);

    }

    @Override
    public void delete(String _id) {
        db = database.getReadableDatabase();
        db.delete(TABLE_HISTORY, "where "+HIS_ID+" = ?", new String[]{ _id});
    }

    @Override
    public int courtCar() {
        db = database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+ TABLE_HISTORY+"", null);
    }

    @Override
    public int countSport() {
        db = database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+ TABLE_HISTORY+" where "+HIS_CATEGORY+" = 'Sport'", null);
    }

    @Override
    public int countNormal() {
        db = database.getReadableDatabase();
        return (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM "+TABLE_HISTORY+" where "+ HIS_CATEGORY+" = 'Normal'", null);
    }

    @Override
    public String tongDoanhThu() {
        db = database.getReadableDatabase();
        int dt = (int) DatabaseUtils.longForQuery(db, "SELECT SUM("+HIS_SHIP+") FROM "+ TABLE_HISTORY+" ", null);
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(dt);
        return formattedNumber;
    }
}
