package com.example.jaruwatsukhamjohn.propayment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DB.myDBclass;


public class Budget extends ActionBarActivity {

    private Button  conbutton;
    private EditText edtMax,edit_date;
    private int[] temp2;
    String[] daynow = new String[3];
    private ImageButton calendar_button;
    private Calendar calendar;
    private int day, month, year;
    private myDBclass mydb;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        bindwidget();
        blidlistener();
    }

    private void blidlistener() {

        String temp = edit_date.getText().toString();
        final String date[] = temp.split("[/]");

        //Confirm Button
        conbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dmax = Double.parseDouble(edtMax.getText().toString());
                mydb.InsertMaxTable(null,dmax,date[0],date[1],date[2]);
                Toast.makeText(getApplicationContext(),"Added Max Success ",Toast.LENGTH_LONG);
                finish();
            }
        });

        //calendar Image Button
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });


    }

    private void bindwidget() {
        title=getApplicationContext().getResources().getString(R.string.Limit_Amount);
        this.setTitle(title);
        mydb = new myDBclass(this);
        mydb.getWritableDatabase();

        //Date
        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String datenow = ft.format(dNow).toString();
        String date[] = datenow.split("[/]");
        temp2 = new int[3];


        for (int i = 0; i < date.length; i++) {
            temp2[i] = Integer.parseInt(date[i]);
            daynow[i] = Integer.toString(temp2[i]);
        }



        conbutton = (Button) findViewById(R.id.confirm_budget);
        edtMax = (EditText) findViewById(R.id.edtMaximum);
        edtMax.addTextChangedListener(new DecimalInputTextWatcher(edtMax, 2));


        //Calendar
        edit_date = (EditText) findViewById(R.id.edtCalendar_budget);
        edit_date.setText(daynow[0] + "/" + daynow[1] + "/" + daynow[2]);


        calendar_button = (ImageButton) findViewById(R.id.Calendar_budget);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
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
            edit_date.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };
}
