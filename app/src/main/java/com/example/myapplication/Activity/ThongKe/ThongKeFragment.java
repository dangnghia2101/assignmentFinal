package com.example.myapplication.Activity.ThongKe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Activity.ThongKeActivity;
import com.example.myapplication.DAO.CarDAO;
import com.example.myapplication.DAO.HISTORYDAO;
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

import me.ibrahimsn.lib.SmoothBottomBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {
    TextView tvTongCarKho, tvTongSieuXeKho, tvTongXeThuongKho, tvTongCarHis, tvSieuXeHis, tvXeThuongHis, tvTongThu;
    SmoothBottomBar smoothBottomBar;
    private PieChart chart;


    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        anhxa(v);

        int tongCarKho = (new CarDAO(getContext())).coutCar();
        tvTongCarKho.setText(tongCarKho+"");
        tvTongSieuXeKho.setText((new CarDAO(getContext())).countSport()+"");
        tvTongXeThuongKho.setText((new CarDAO(getContext())).countNormal()+"");

        tvTongCarHis.setText((new HISTORYDAO(getContext())).courtCar()+"");
        tvSieuXeHis.setText((new HISTORYDAO(getContext())).countSport()+"");
        tvXeThuongHis.setText((new HISTORYDAO(getContext())).countNormal()+"");

        tvTongThu.setText((new HISTORYDAO(getContext())).tongDoanhThu()+"");
        // Inflate the layout for this fragment
        return v;
    }

    private void anhxa(View view){
        tvSieuXeHis = (TextView) view.findViewById(R.id.tvSieuXeHisTK);
        tvXeThuongHis = (TextView) view.findViewById(R.id.tvXeThuongHisTK);
        tvTongCarHis = (TextView) view.findViewById(R.id.tvTongXuatHisTK);

        tvTongCarKho = (TextView) view.findViewById(R.id.tvTongKhoXeTK);
        tvTongSieuXeKho = (TextView) view.findViewById(R.id.tvSieuXeKhoTK);
        tvTongXeThuongKho = (TextView) view.findViewById(R.id.tvTongXeThuongKhoTK);

        tvTongThu = (TextView) view.findViewById(R.id.tvTongDoanhThu);

        smoothBottomBar = view.findViewById(R.id.smoothBottomBarThongKe);

        // Chart
        chart = view.findViewById(R.id.activity_thongke_chart);
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
        float sieuXe = (new CarDAO(getContext()).countSport());
        float xeThuong = (new CarDAO(getContext())).countNormal();

        float xeThuongKho = (new HISTORYDAO(getContext())).countNormal();
        float sieuXeKho = (new HISTORYDAO(getContext())).countSport();

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