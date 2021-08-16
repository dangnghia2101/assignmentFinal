package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Activity.CarFragment;
import com.example.myapplication.Activity.CarOutFragment;
import com.example.myapplication.Activity.LoaiCarFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter{

    public ViewPageAdapter( FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoaiCarFragment();
            case 1:
                return new CarFragment();
            case 2:
                return new CarOutFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Loại xe";
                break;
            case 1:
                title = "Kho xe";
                break;
            case 2:
                title = "Lịch sử";
                break;
        }
        return title;
    }
}
