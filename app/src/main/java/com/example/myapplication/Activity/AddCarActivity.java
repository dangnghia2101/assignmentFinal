package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CarAdapter;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCarActivity extends AppCompatActivity {
    Button btnAddCar;
    EditText edtIdCar, edtNameCar, edtLoaiXe, edtNgayNhap, edtGhiChu;
    ImageButton imageCar;
    ImageView imgLoadCar;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        anhxa();

        imageCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                    AddCarActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GALLERY
                );
            }
        });

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameCar = edtNameCar.getText().toString();
                String IdCar = edtIdCar.getText().toString();
                String LoaiXe = edtLoaiXe.getText().toString();
                String NgayNhap = edtNgayNhap.getText().toString();
                String GhiChu = edtGhiChu.getText().toString();

                byte[] image = imageViewToByte(imgLoadCar);

                if(NameCar.isEmpty() || IdCar.isEmpty() || LoaiXe.isEmpty() || NgayNhap.isEmpty() || GhiChu.isEmpty() || image==null){
                    Toast.makeText(AddCarActivity.this, "Ô nhập không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    Car car = new Car(IdCar, NameCar, LoaiXe, NgayNhap, GhiChu, image);
                    CarDAO dao = new CarDAO(AddCarActivity.this);
                    dao.insert(car);
                    Toast.makeText(AddCarActivity.this, "Đã thêm xe "+NameCar, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCarActivity.this, NavigationDrawble.class));
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(this, "Bạn chưa cấp quyền", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data!=null){
            Uri uri =data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgLoadCar.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void anhxa(){
        btnAddCar =(Button) findViewById(R.id.btnAddCarDialog);
        edtIdCar =(EditText) findViewById(R.id.addIdCar);
        edtNameCar =(EditText) findViewById(R.id.addNameCar);
        edtLoaiXe =(EditText) findViewById(R.id.addLoaiXe);
        edtNgayNhap =(EditText) findViewById(R.id.addNgayNhap);
        edtGhiChu =(EditText) findViewById(R.id.addGhiChu);
        imageCar =(ImageButton) findViewById(R.id.btnAddImageCar);
        imgLoadCar = (ImageView) findViewById(R.id.imgLoadCar);
    }
}