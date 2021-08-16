package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.myapplication.Adapter.ViewPageAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class  OutCarActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    SmoothBottomBar smoothBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_car);

        smoothBottomBar = (SmoothBottomBar) findViewById(R.id.smoothBarOutCar);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_page);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager()   );
        mViewPager.setAdapter(viewPageAdapter);

        // Add fragment heres
        mTabLayout.setupWithViewPager(mViewPager);

        // GỌi hàm menu
        menuBottom();
    }

    //Xủ lí menu
    private void menuBottom(){
        Intent intent = getIntent();
        int vt = intent.getIntExtra("locationMenu",1);
        smoothBottomBar.setActiveItem(vt);

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(OutCarActivity.this, ListCarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(OutCarActivity.this, OutCarActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(OutCarActivity.this, ThongKeActivity.class));
                        break;
                    default:

                }
            }
        });
    }
}