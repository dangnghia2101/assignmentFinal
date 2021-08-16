package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Model.User;

import static com.example.myapplication.Name.AppConstant.*;


public class UserDAO implements IUserDAO{
    Database database;
    SQLiteDatabase sqLiteDatabase;

    public UserDAO(Context context){ database = new Database(context);}

    @Override
    public Boolean login(String _userName, String _password) {
        sqLiteDatabase = database.getReadableDatabase();
        String sql ="select * from "+TABLE_USER+" where "+USER_NAME+" = ? and "+USER_PASS+" = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{_userName, _password});
        int count = cursor.getCount();
        cursor.close();
        return count>0;
    }

    @Override
    public void insert(User user) {
        sqLiteDatabase = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getUserName());
        values.put(USER_PASS, user.getPassword());
        sqLiteDatabase.insert(TABLE_USER, null, values);
    }
}
