package com.example.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import static com.example.myapplication.Name.AppConstant.*;

public class Database extends SQLiteOpenHelper {
    public Database( Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_CAR+"("+ID_CAR+" text primary key not null, "+NAME_CAR+" text, "+CATEGORY_CAR+" text, "+DINPUT_CAR+" text, "+NOTE_CAR+" text, "+IMAGE_CAR+" Blob) ";
        db.execSQL(sql);

        db.execSQL("insert into "+TABLE_CAR+"("+ID_CAR+", "+NAME_CAR+","+CATEGORY_CAR+", "+DINPUT_CAR+","+NOTE_CAR+") values('PB1','Yamaha gentel','Sport','20-3-2020','Hàng hiếm')");
        db.execSQL("insert into "+TABLE_CAR+"("+ID_CAR+", "+NAME_CAR+","+CATEGORY_CAR+", "+DINPUT_CAR+","+NOTE_CAR+") values('PB2','Toyota black','Normal','20-8-2021','Hàng hiếm')");
        db.execSQL("insert into "+TABLE_CAR+"("+ID_CAR+", "+NAME_CAR+","+CATEGORY_CAR+", "+DINPUT_CAR+","+NOTE_CAR+") values('PB3','Testla X10','Sport','10-3-2020','Hàng hiếm')");
        db.execSQL("insert into "+TABLE_CAR+"("+ID_CAR+", "+NAME_CAR+","+CATEGORY_CAR+", "+DINPUT_CAR+","+NOTE_CAR+") values('PB4','Honda kaido','Sport','20-3-2020','Hàng hiếm')");

        sql = "create table "+TABLE_HISTORY+"("+HIS_ID+" text primary key not null, "+HIS_TENXE+" text, "+HIS_TENNHAN+" text, "+HIS_SDT+" text, "+HIS_DIACHINHAN+" text, "+HIS_DATEOUT+" text, "+HIS_SHIP+" text, "+HIS_IMAGE+" Blob, "+HIS_CATEGORY+" text)";
        db.execSQL(sql);


        sql = "create table "+TABLE_USER+"("+USER_NAME+" text primary key not null, "+USER_PASS+" text not null)";
        db.execSQL(sql);

        db.execSQL("INSERT INTO "+TABLE_USER+" VALUES('nghia','11111')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_CAR+"");
        db.execSQL("drop table if exists "+TABLE_USER+"");
        db.execSQL("drop table if exists "+TABLE_HISTORY+"");

        onCreate(db);
    }
}
