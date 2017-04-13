package com.gym.hellochartsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.util.FloatUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ColumnActivity extends AppCompatActivity {

    private ColumnChartView mColumnChartView;

    /*========== 数据相关 ==========*/
    private ColumnChartData mColumnChartData;               //柱状图数据
    public final static String[] xValues = new String[]{"语文", "数学", "英语", "音乐", "科学", "体育"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column);

        initView();
    }

    private void initView() {
        mColumnChartView = (ColumnChartView) findViewById(R.id.chart);
        mColumnChartView.setOnValueTouchListener(new ValueTouchListener());

        /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < xValues.length; ++i) {
            subcolumnValueList = new ArrayList<>();
            subcolumnValueList.add(new SubcolumnValue((float) Math.random() * 100f, ChartUtils.pickColor()));

            Column column = new Column(subcolumnValueList);
            column.setHasLabels(true);                    //设置列标签
//            column.setHasLabelsOnlyForSelected(true);       //只有当点击时才显示列标签

            columnList.add(column);

            //设置坐标值
            axisValues.add(new AxisValue(i).setLabel(xValues[i]));
        }

        mColumnChartData = new ColumnChartData(columnList);               //设置数据


        /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis(axisValues); //将自定义x轴显示值传入构造函数
        Axis axisY = new Axis().setHasLines(true); //setHasLines是设置线条
        axisX.setName("考试科目");    //设置横轴名称
        axisY.setName("成绩");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        mColumnChartView.setColumnChartData(mColumnChartData);


        /*===== 设置竖轴最大值 =====*/
        //法一：
        Viewport v = mColumnChartView.getMaximumViewport();
        v.top = 103;
        mColumnChartView.setCurrentViewport(v);
        /*法二：
        Viewport v = mColumnChartView.getCurrentViewport();
        v.top = 100;
        mColumnChartView.setMaximumViewport(v);
        mColumnChartView.setCurrentViewport(v);*/
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(ColumnActivity.this, xValues[columnIndex]+"成绩 : " +
                    (int)value.getValue(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
