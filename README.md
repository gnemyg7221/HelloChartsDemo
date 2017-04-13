# HelloChartsDemo
基于hellocharts开源库的柱状图详解demo

具体内容可查看博客http://blog.csdn.net/itermeng


## **1. xml布局** 
直接使用对应的控件，代码如下
```
  <lecho.lib.hellocharts.view.ColumnChartView
        android:id="@+id/chart"
        android:layout_width="match_parent"
        android:layout_height="400dp"
        android:layout_margin="10dp"/>
```


----------


## **2. 数据填充**

```
   /*========== 数据相关 ==========*/
    private ColumnChartData mColumnChartData;    //柱状图数据
```
在介绍之前先重点提一个类**ColumnChartData** ，查看这个类的字面意思可知<font color=#f00>**ColumnChartData**中含有所有与柱状图有关的数据，其中不管是柱子列表的值，还是坐标抽的有关设置，总之，所有柱状图的设置都包含其中。<font color=#000>试想，当我们将这个类的数据都填充好后，最后只需将数据设置到view中，大功即可告成，而现在的工作就是开始填充**ColumnChartData：**

### **（1）柱体列表数据设置**

既然是柱体列表，那么一定是ArrayList，主要是柱体数据这个类的认知，此类是**Column**，官方介绍是

> Single column for ColumnChart. **One column can be divided into multiple sub-columns**(ColumnValues) especially for
stacked ColumnChart.

<font color=#f00>大意为：**Column**是柱状图的一个单列，而且一个单列柱体**Column**可以被分成多个子柱体**SubcolumnValue**。<font color=#000>查看**Column**类内部源码可知，一个**Column**对象也是由**SubcolumnValue**列表组成。对比示意图如下：

![这里写图片描述](http://img.blog.csdn.net/20170412213422460?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSVRlcm1lbmc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


经过上面的图文讲解后，得知在创建**Column**对象时，我们先要填充它的子柱体**SubcolumnValue**。之所以这样设计也是为了满足各式的需求，但是我这里的需求暂时不涉及到子列，所以实现方法为上图第二种，代码如下：

```
  /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        for (int i = 0; i < 7; ++i) {
            subcolumnValueList = new ArrayList<>();
            subcolumnValueList.add(new SubcolumnValue((float) Math.random() * 50f, ChartUtils.pickColor()));

            Column column = new Column(subcolumnValueList);
            columnList.add(column);
            }

        mColumnChartData = new ColumnChartData(columnList);               //设置数据
```
最后填充好柱体列表**columnList**，回想我一开始所说的，柱状图所有有关数据存放到**ColumnChartData**类，也就是以上代码最后一句，此时，柱体列表数据填充完毕。

----------
### **（2）坐标轴数据设置**
往**ColumnChartData**类放置了柱体列表数据后，需要设置好坐标轴数据，比较简单，配有注释，看代码即可：

```
     /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Axis X");    //设置横轴名称
        axisY.setName("Axis Y");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        mColumnChartView.setColumnChartData(mColumnChartData);

```
