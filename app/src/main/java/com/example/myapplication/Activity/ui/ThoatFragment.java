package com.example.myapplication.Activity.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThoatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThoatFragment extends Fragment {

    public ThoatFragment() {
        // Required empty public constructor
    }

    public static ThoatFragment newInstance(String param1, String param2) {
        ThoatFragment fragment = new ThoatFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Bạn chắc chắn muốn thoát không")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thoat, container, false);
    }
}