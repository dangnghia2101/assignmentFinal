package com.example.myapplication.Activity.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.ViewPageAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import me.ibrahimsn.lib.SmoothBottomBar;

public class GalleryFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    SmoothBottomBar smoothBottomBar;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        smoothBottomBar = (SmoothBottomBar) root.findViewById(R.id.smoothBarOutCar);

        mTabLayout = root.findViewById(R.id.tab_layout);
        mViewPager = root.findViewById(R.id.view_page);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getParentFragmentManager());
        mViewPager.setAdapter(viewPageAdapter);

        // Add fragment heres
        mTabLayout.setupWithViewPager(mViewPager);

        return root;

    }
}