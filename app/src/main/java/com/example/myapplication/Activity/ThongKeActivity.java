package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Float2;
import android.widget.TextView;

import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.DAO.HISTORYDAO;
import com.example.myapplication.Model.Car;
import com.example.myapplication.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvTongCarKho, tvTongSieuXeKho, tvTongXeThuongKho, tvTongCarHis, tvSieuXeHis, tvXeThuongHis, tvTongThu;
    SmoothBottomBar smoothBottomBar;
    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        anhxa();

        int tongCarKho = (new CarDAO(ThongKeActivity.this)).coutCar();
        tvTongCarKho.setText(tongCarKho+"");
        tvTongSieuXeKho.setText((new CarDAO(ThongKeActivity.this)).countSport()+"");
        tvTongXeThuongKho.setText((new CarDAO(ThongKeActivity.this)).countNormal()+"");

        tvTongCarHis.setText((new HISTORYDAO(ThongKeActivity.this)).courtCar()+"");
        tvSieuXeHis.setText((new HISTORYDAO(ThongKeActivity.this)).countSport()+"");
        tvXeThuongHis.setText((new HISTORYDAO(ThongKeActivity.this)).countNormal()+"");

        tvTongThu.setText((new HISTORYDAO(ThongKeActivity.this)).tongDoanhThu()+"");
        menuBottom();
    }


    //Xủ lí menu
    private void menuBottom(){
        Intent intent = getIntent();
        int vt = intent.getIntExtra("locationMenu",2);
        smoothBottomBar.setActiveItem(vt);

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(ThongKeActivity.this, ListCarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ThongKeActivity.this, OutCarActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ThongKeActivity.this, ThongKeActivity.class));
                        break;
                    default:

                }
            }
        });
    }

    private void anhxa(){
        tvSieuXeHis = (TextView) findViewById(R.id.tvSieuXeHisTK);
        tvXeThuongHis = (TextView) findViewById(R.id.tvXeThuongHisTK);
        tvTongCarHis = (TextView) findViewById(R.id.tvTongXuatHisTK);

        tvTongCarKho = (TextView) findViewById(R.id.tvTongKhoXeTK);
        tvTongSieuXeKho = (TextView) findViewById(R.id.tvSieuXeKhoTK);
        tvTongXeThuongKho = (TextView) findViewById(R.id.tvTongXeThuongKhoTK);

        tvTongThu = (TextView) findViewById(R.id.tvTongDoanhThu);

        smoothBottomBar = findViewById(R.id.smoothBottomBarThongKe);

        // Chart
        chart = findViewById(R.id.activity_thongke_chart);
        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        chart.setDrawHoleEnabled(true);
        chart.setUsePercentValues(true);
        chart.setEntryLabelTextSize(12);
        chart.setEntryLabelColor(Color.WHITE);
        chart.setCenterText("Super Car");
        chart.setCenterTextSize(20);
        chart.getDescription().setEnabled(false);

        // Chú thíchs
        Legend l = chart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setTextSize(15f);
//        l.setTextColor(Color.WHITE);
//        l.setDrawInside(false);
        l.setEnabled(false);
    }

    // Xử lí circle chart
    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        float sieuXe = (new CarDAO(ThongKeActivity.this)).countSport();
        float xeThuong = (new CarDAO(ThongKeActivity.this)).countNormal();

        float xeThuongKho = (new HISTORYDAO(ThongKeActivity.this)).countNormal();
        float sieuXeKho = (new HISTORYDAO(ThongKeActivity.this)).countSport();

        float tong = sieuXe + xeThuong + xeThuongKho + sieuXeKho;

        float mot =sieuXe/ tong;
        float hai = xeThuong / tong;
        float ba = xeThuongKho / tong;
        float bon = sieuXeKho / tong;

        if(mot != 0.0)
            entries.add(new PieEntry(mot));
        if(hai != 0.0)
            entries.add(new PieEntry((hai)));
        if(ba != 0.0)
            entries.add(new PieEntry((ba)));
        if(bon != 0.0)
            entries.add(new PieEntry(bon));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Kho");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();

        chart.animateY(1400, Easing.EaseInOutQuad);
    }
}