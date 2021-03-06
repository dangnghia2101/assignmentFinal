package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.UserDAO;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ImageButton btnDangNhap;
    TextInputLayout textIpUser, textIpPass;
    TextInputEditText edtUser, edtPass, edtUserDK, edtPassDK, edtPassDK2;
    TextInputLayout textIpUserDK, textIpPassDK, textIpPass2DK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDangNhap =(ImageButton) findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //intent_detail();
                    startActivity(new Intent(MainActivity.this, NavigationDrawble.class));
            }
        });

    }


    public void intent_detail(){
       // startActivity(new Intent(MainActivity.this, ListCarActivity.class));
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                MainActivity.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.dialog_dangnhap,
                        (ConstraintLayout) findViewById(R.id.bottomSheetContainer)
                );
        bottomSheetDialog.setContentView(bottomSheetView);

        edtUser = (TextInputEditText) bottomSheetDialog.findViewById(R.id.edtUserName);
        edtPass = (TextInputEditText) bottomSheetDialog.findViewById(R.id.edtPassword);
        Button btnLogin = (Button) bottomSheetDialog.findViewById(R.id.btnDangNhapDialog);
        TextView createUser = (TextView) bottomSheetDialog.findViewById(R.id.createUser);

         textIpUser = bottomSheetDialog.findViewById(R.id.inputLayoutUser);
         textIpPass = bottomSheetDialog.findViewById(R.id.inputLayoutPass);

         //T???o t??i kho???n
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                dialogDangKiTaiKhoan();
            }
        });

        //Ki???m l???i username
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateUserName(edtUser.getText().toString().trim());
            }
        });

        //Ki???m l???i password
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePass();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(!isValidate()){
                    Toast.makeText(MainActivity.this, "K?? t??? nh???p kh??ng h???p l???", Toast.LENGTH_SHORT).show();
                }else{
                    UserDAO userDAO = new UserDAO(MainActivity.this);
                    if(userDAO.login(user, pass)){
                        startActivity(new Intent(MainActivity.this, NavigationDrawble.class));
                    }else{
                        bottomSheetDialog.dismiss();
                        Toast.makeText(MainActivity.this, "T??i kho???n ho???c m???t kh???u kh??ng ????ng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bottomSheetDialog.show();

    }

    private void dialogDangKiTaiKhoan(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_dangki);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.9);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.8);
        dialog.getWindow().setLayout(width,height);

        textIpUserDK = dialog.findViewById(R.id.inputLayoutUserDangki);
        textIpPassDK = dialog.findViewById(R.id.inputLayoutPassDangKi);
        textIpPass2DK = dialog.findViewById(R.id.inputLayoutPass2DangKi);

        edtUserDK = dialog.findViewById(R.id.edtUserNameDangKi);
        edtPassDK = dialog.findViewById(R.id.edtPasswordDangKi);
        edtPassDK2 = dialog.findViewById(R.id.edtPassword2DangKi);

        Button btnDangKi = dialog.findViewById(R.id.btnDangKiTaiKhoan);

        edtUserDK.addTextChangedListener(new TextWatcher()  {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateUserNameDK(edtUserDK.getText().toString().trim());
            }
        });

        edtPassDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePass1DK();
            }
        });

        edtPassDK2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePass2DK();
            }
        });

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidateDK()){
                    UserDAO userDAO = new UserDAO(MainActivity.this);
                    userDAO.insert(new User(edtUserDK.getText().toString(), edtPassDK.getText().toString()));
                    Toast.makeText(MainActivity.this, "????ng k?? t??i kho???n th??nh c??ng", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "????ng k?? t??i kho???n th???t b???i", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private Boolean isValidate(){return validateUserName(edtUser.getText().toString().trim()) && validatePass(); };

    private Boolean isValidateDK(){return validateUserNameDK(edtUserDK.getText().toString().trim()) && validatePass1DK() && validatePass2DK();};

    //Ki???m tra userName
    private Boolean validateUserName(String user){
        if(user.isEmpty()){
            textIpUser.setError("Kh??ng ???????c ????? tr???ng");
            edtUser.requestFocus();
            return false;
        }else if(user.length()>20){
            textIpUser.setError("Nh???p qu?? 20 k?? t???");
            return false;
        }else{
            textIpUser.setErrorEnabled(false);
        }
        return true;
    }

    private Boolean validateUserNameDK(String user){
        if(user.isEmpty()){
            textIpUserDK.setError("Kh??ng ???????c ????? tr???ng");
            edtUserDK.requestFocus();
            return false;
        }else if(user.length()>20){
            textIpUserDK.setError("Nh???p qu?? 20 k?? t???");
            return false;
        }else{
            textIpUserDK.setErrorEnabled(false);
        }
        return true;
    }


    private Boolean validatePass(){
        String pass = edtPass.getText().toString().trim();
        if(pass.isEmpty()){
            textIpPass.setError("Kh??ng ???????c ????? tr???ng");
            edtPass.requestFocus();
            return false;
        }
//        else if(pass.length()<6){
//            textIpPass.setError("M???t kh???u qu?? ng???n");
//            return false;
//        }
        else{
            textIpPass.setErrorEnabled(false);
        }
        return true;
    }

    private Boolean validatePass1DK(){
        String pass = edtPassDK.getText().toString().trim();
        if(pass.isEmpty()){
            textIpPassDK.setError("Kh??ng ???????c ????? tr???ng");
            edtPassDK.requestFocus();
            return false;
        }
        else if(pass.length()<6){
            textIpPassDK.setError("M???t kh???u qu?? ng???n");
            return false;
        }
        else{
            textIpPassDK.setErrorEnabled(false);
        }
        return true;
    }

    private Boolean validatePass2DK(){
        String pass = edtPassDK2.getText().toString().trim();
        if(pass.isEmpty()){
            textIpPass2DK.setError("Kh??ng ???????c ????? tr???ng");
            edtPassDK2.requestFocus();
            return false;
        }
        else if(pass.length()<6){
            textIpPass2DK.setError("M???t kh???u qu?? ng???n");
            return false;
        }
        else{
            textIpPass2DK.setErrorEnabled(false);
        }
        return true;
    }

}