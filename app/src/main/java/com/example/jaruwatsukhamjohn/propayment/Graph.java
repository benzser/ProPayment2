package com.example.jaruwatsukhamjohn.propayment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.LargeValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DB.myDBclass;


public class Graph extends ActionBarActivity{
    private myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    String[] monthname = {"Jan", "Feb", "Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    Spinner spinnertype;
    private EditText edit_date;
    private int day, month, year, temp[],typeif,monthday,dayone;
    private ImageButton calendar_button;
    private Calendar calendarG;
    PieChart mChart;
    BarChart MonthChart;
    private Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        bindWidget();
        bindListener();


    }

    private void bindListener() {
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0){
                    typeif=0;
                    MonthChart.clear();
                    double incomesum=getTotalincomedaily(day);
                    double expensesum=getTotalexpensedaily(day);

                    ArrayList<Entry> yVals1 = new ArrayList<Entry>();
                    yVals1.add(new Entry((float) incomesum, 0));
                    yVals1.add(new Entry((float) expensesum, 1));

                    ArrayList<String> xVals = new ArrayList<String>();
                    xVals.add(new String("Income"));
                    xVals.add(new String("Expense"));
                    ArrayList colors = new ArrayList();
                    colors.add(Color.parseColor("#9DD142"));
                    colors.add(Color.parseColor("#F20F0F"));

                    PieDataSet dataSet = new PieDataSet(yVals1, "");
                    dataSet.setColors(colors);
                    dataSet.setSliceSpace(3f);
                    PieData data = new PieData(xVals, dataSet);
                    data.setValueTextSize(11f);
                    data.setValueTextColor(Color.WHITE);
                    mChart.setData(data);
                    mChart.animateXY(1500, 1500);
                    mChart.invalidate();
                }
                if(position==1){
                    typeif=1;
                    mChart.clear();
                    ArrayList<String> xVals = new ArrayList<String>();
                    for (int i = 0; i < monthday; i++) {
                        xVals.add((i + 1) + "");
                    }
                    ArrayList<BarEntry> yValsM1 = new ArrayList<BarEntry>();
                    ArrayList<BarEntry> yValsM2 = new ArrayList<BarEntry>();
                    dayone =1;
                    for (int i = 0; i < monthday; i++) {
                        double incomemonth=getTotalincomedaily(dayone);
                        double expensemonth=getTotalexpensedaily(dayone);
                        yValsM1.add(new BarEntry((float) incomemonth, i));
                        yValsM2.add(new BarEntry((float) expensemonth, i));
                        dayone++;
                    }
                    BarDataSet set1 = new BarDataSet(yValsM1, "Income");
                    set1.setColor(Color.rgb(157, 209, 66));
                    BarDataSet set2 = new BarDataSet(yValsM2, "Expense");
                    set2.setColor(Color.rgb(242, 15,15));
                    ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
                    dataSets.add(set1);
                    dataSets.add(set2);
                    BarData data = new BarData(xVals, dataSets);
                    data.setGroupSpace(80f);
                    data.setValueTypeface(tf);
                    MonthChart.setData(data);
                    MonthChart.animateY(1500);
                    MonthChart.invalidate();
                }
                if(position==2){
                    typeif=2;
                    mChart.clear();
                    MonthChart.clear();
                    ArrayList<String> xVals = new ArrayList<String>();
                    for (int i = 0; i < 12; i++) {
                        xVals.add(monthname[i]);
                    }
                    ArrayList<BarEntry> yValsM1 = new ArrayList<BarEntry>();
                    ArrayList<BarEntry> yValsM2 = new ArrayList<BarEntry>();
                    dayone =1;
                    for (int i = 0; i < 12; i++) {
                        double incomeyear=getTotalincomemonth(dayone);
                        double expenseyear=getTotalexpensemonth(dayone);
                        yValsM1.add(new BarEntry((float) incomeyear, i));
                        yValsM2.add(new BarEntry((float) expenseyear, i));
                        dayone++;
                    }
                    BarDataSet set1 = new BarDataSet(yValsM1, "Income");
                    set1.setColor(Color.rgb(157, 209, 66));
                    BarDataSet set2 = new BarDataSet(yValsM2, "Expense");
                    set2.setColor(Color.rgb(242, 15,15));

                    ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
                    dataSets.add(set1);
                    dataSets.add(set2);
                    BarData data = new BarData(xVals, dataSets);
                    data.setGroupSpace(80f);
                    data.setValueTypeface(tf);
                    MonthChart.setData(data);
                    MonthChart.animateY(1500);
                    MonthChart.invalidate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private void bindWidget() {
        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();
        //Spinner
        spinnertype = (Spinner) findViewById(R.id.graphtypespinner);
        List type = new ArrayList();
        type.add("Day");
        type.add("Month");
        type.add("Year");
        ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertype.setAdapter(dataAdapter);
        spinnertype.setSelection(0);

        edit_date = (EditText) findViewById(R.id.edit_dategraph);
        calendar_button = (ImageButton) findViewById(R.id.calendarGraph);
        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String dateStartnow = ft.format(dNow).toString();
        String dateStartArray[] = dateStartnow.split("[/]");
        temp = new int[3];
        String[] daynowStart = new String[3];

        for (int i = 0; i < dateStartArray.length; i++) {
            temp[i] = Integer.parseInt(dateStartArray[i]);
            daynowStart[i] = Integer.toString(temp[i]);
        }


        edit_date.setText(daynowStart[0] + "/" + daynowStart[1] + "/" + daynowStart[2]);
        calendarG = Calendar.getInstance();
        day = calendarG.get(Calendar.DAY_OF_MONTH);
        month = calendarG.get(Calendar.MONTH);
        year = calendarG.get(Calendar.YEAR);


        // GRAPH
        mChart = (PieChart) findViewById(R.id.chart);
        MonthChart = (BarChart) findViewById(R.id.chart1);
        MonthChart.setNoDataText("");
        mChart.setNoDataText("");
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(50f);
        mChart.setDescription("");
        mChart.setDrawCenterText(true);
        mChart.setDrawHoleEnabled(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        month++;
        double incomesum=getTotalincomedaily(day);
        double expensesum=getTotalexpensedaily(day);

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry((float) incomesum, 0));
        yVals1.add(new Entry((float) expensesum, 1));

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(new String("Income"));
        xVals.add(new String("Expense"));
        ArrayList colors = new ArrayList();
        colors.add(Color.parseColor("#9DD142"));
        colors.add(Color.parseColor("#F20F0F"));

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(xVals, dataSet);
        //data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);
        mChart.animateXY(1500, 1500);
        month--;

        //GRAPH MONTH
        MonthChart.setPinchZoom(false);
        MonthChart.setDrawBarShadow(false);
        MonthChart.setDrawGridBackground(false);
        MonthChart.setDescription("");
        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xl = MonthChart.getXAxis();
        xl.setTypeface(tf);
        YAxis leftAxis = MonthChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(25f);
        MonthChart.getAxisRight().setEnabled(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            edit_date.setText(selectedDay + "/" + (selectedMonth+1) + "/"
                    + selectedYear);
            day=selectedDay;
            month=selectedMonth+1;
            year=selectedYear;
            if (month == 4 || month == 6 || month == 9 || month == 11)
                monthday = 30;
            if (month == 2)
                monthday=isLeapYear(year);
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                monthday = 31;

            //GRAPH DAY
            if(typeif==0){
                MonthChart.clear();
                double incomesum=getTotalincomedaily(day);
                double expensesum=getTotalexpensedaily(day);

                ArrayList<Entry> yVals1 = new ArrayList<Entry>();
                yVals1.add(new Entry((float) incomesum, 0));
                yVals1.add(new Entry((float) expensesum, 1));

                ArrayList<String> xVals = new ArrayList<String>();
                xVals.add(new String("Income"));
                xVals.add(new String("Expense"));
                ArrayList colors = new ArrayList();
                colors.add(Color.parseColor("#9DD142"));
                colors.add(Color.parseColor("#F20F0F"));

                PieDataSet dataSet = new PieDataSet(yVals1, "");
                dataSet.setColors(colors);
                dataSet.setSliceSpace(3f);
                PieData data = new PieData(xVals, dataSet);
                data.setValueTextSize(11f);
                data.setValueTextColor(Color.WHITE);
                mChart.setData(data);
                mChart.animateXY(1500, 1500);
                mChart.invalidate();}

            //GRAPH MONTH
            if(typeif==1){
                mChart.clear();
                ArrayList<String> xVals = new ArrayList<String>();
                for (int i = 0; i < monthday; i++) {
                    xVals.add((i + 1) + "");
                }
                ArrayList<BarEntry> yValsM1 = new ArrayList<BarEntry>();
                ArrayList<BarEntry> yValsM2 = new ArrayList<BarEntry>();
                dayone =1;
                for (int i = 0; i < monthday; i++) {
                    double incomemonth=getTotalincomedaily(dayone);
                    double expensemonth=getTotalexpensedaily(dayone);
                    yValsM1.add(new BarEntry((float) incomemonth, i));
                    yValsM2.add(new BarEntry((float) expensemonth, i));
                    dayone++;
                }
                BarDataSet set1 = new BarDataSet(yValsM1, "Income");
                set1.setColor(Color.rgb(157, 209, 66));
                BarDataSet set2 = new BarDataSet(yValsM2, "Expense");
                set2.setColor(Color.rgb(242, 15,15));
                ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
                dataSets.add(set1);
                dataSets.add(set2);
                BarData data = new BarData(xVals, dataSets);
                data.setGroupSpace(80f);
                data.setValueTypeface(tf);
                MonthChart.setData(data);
                MonthChart.animateY(1500);
                MonthChart.invalidate();

            }
            //GRAPH YEAR
            if(typeif==2){
                mChart.clear();
                MonthChart.clear();
                ArrayList<String> xVals = new ArrayList<String>();
                for (int i = 0; i < 12; i++) {
                    xVals.add(monthname[i]);
                }
                ArrayList<BarEntry> yValsM1 = new ArrayList<BarEntry>();
                ArrayList<BarEntry> yValsM2 = new ArrayList<BarEntry>();
                dayone =1;
                for (int i = 0; i < 12; i++) {
                    double incomeyear=getTotalincomemonth(dayone);
                    double expenseyear=getTotalexpensemonth(dayone);
                    yValsM1.add(new BarEntry((float) incomeyear, i));
                    yValsM2.add(new BarEntry((float) expenseyear, i));
                    dayone++;
                }
                BarDataSet set1 = new BarDataSet(yValsM1, "Income");
                set1.setColor(Color.rgb(157, 209, 66));
                BarDataSet set2 = new BarDataSet(yValsM2, "Expense");
                set2.setColor(Color.rgb(242, 15,15));

                ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
                dataSets.add(set1);
                dataSets.add(set2);
                BarData data = new BarData(xVals, dataSets);
                data.setGroupSpace(80f);
                data.setValueTypeface(tf);
                MonthChart.setData(data);
                MonthChart.animateY(1500);
                MonthChart.invalidate();
            }

        }
    };

    public double getTotalincomedaily(int chooseday) {
// TODO Auto-generated method stub
        double sum = 0;
        Cursor cursor = mDb.rawQuery(
                "SELECT SUM(amount) FROM transactions WHERE type = ? AND day = ? AND month = ? AND year = ?", new String[]{"true", String.valueOf(chooseday), String.valueOf(month), String.valueOf(year)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sum = cursor.getDouble(0);
        }
        return sum;
    }
    public double getTotalexpensedaily(int chooseday) {
// TODO Auto-generated method stub
        double sum = 0;
        Cursor cursor = mDb.rawQuery(
                "SELECT SUM(amount) FROM transactions WHERE type = ? AND day = ? AND month = ? AND year = ?", new String[]{"false", String.valueOf(chooseday), String.valueOf(month), String.valueOf(year)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sum = cursor.getDouble(0);
        }
        return sum;
    }
    public double getTotalincomemonth(int chooseday) {
// TODO Auto-generated method stub
        double sum = 0;
        Cursor cursor = mDb.rawQuery(
                "SELECT SUM(amount) FROM transactions WHERE type = ? AND month = ? AND year = ?", new String[]{"true", String.valueOf(chooseday), String.valueOf(year)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sum = cursor.getDouble(0);
        }
        return sum;
    }
    public double getTotalexpensemonth(int chooseday) {
// TODO Auto-generated method stub
        double sum = 0;
        Cursor cursor = mDb.rawQuery(
                "SELECT SUM(amount) FROM transactions WHERE type = ? AND month = ? AND year = ?", new String[]{"false", String.valueOf(chooseday), String.valueOf(year)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sum = cursor.getDouble(0);
        }
        return sum;
    }

    public static int isLeapYear(int year) {
        if (year % 4 != 0) {
            return 28;
        } else if (year % 400 == 0) {
            return 29;
        } else if (year % 100 == 0) {
            return 28;
        } else {
            return 29;
        }
    }
}
