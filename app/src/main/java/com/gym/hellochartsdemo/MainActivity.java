package com.gym.hellochartsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    private LineChartView chartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chartView = (LineChartView) findViewById(R.id.chart);
    }
}
