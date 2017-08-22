package com.example.jaruwatsukhamjohn.propayment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DB.myDBclass;


public class Add_Debt extends ActionBarActivity {
    private ImageButton calendar_start, calendar_end;
    private EditText edit_debtname, edit_datestart, edit_dateend, edit_amount;
    private int dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd, temp1[], temp2[];
    private Calendar calendarStart, calendarEnd;
    private Button addbutton;
    private myDBclass mydb;
    private String dName,dAmount,title;
    String recname,recicon;
    SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__debt);
        bindWidget();
        bindListener();
    }

    private void bindWidget() {
        title=getApplicationContext().getResources().getString(R.string.addpayment);
        this.setTitle(title);
        mydb = new myDBclass(this);
        mydb.getWritableDatabase();
        edit_debtname = (EditText) findViewById(R.id.DebtnameEdittext);
        edit_amount = (EditText) findViewById(R.id.DebtamountEdittext);
        edit_amount.addTextChangedListener(new DecimalInputTextWatcher(edit_amount, 2));
        addbutton = (Button) findViewById(R.id.AddDebtbutton2);
        calendar_start = (ImageButton) findViewById(R.id.DebtStartCalendarImage);
        calendar_end = (ImageButton) findViewById(R.id.DebtEndCalendarImage);

        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String dateStartnow = ft.format(dNow).toString();
        String dateEndnow = ft.format(dNow).toString();
        String dateStartArray[] = dateStartnow.split("[/]");
        String dateEndArray[] = dateEndnow.split("[/]");
        temp1 = new int[3];
        temp2 = new int[3];
        String[] daynowStart = new String[3];
        String[] daynowEnd = new String[3];

        for (int i = 0; i < dateStartArray.length; i++) {
            temp1[i] = Integer.parseInt(dateStartArray[i]);
            daynowStart[i] = Integer.toString(temp1[i]);
        }

        for (int i = 0; i < dateEndArray.length; i++) {
            temp2[i] = Integer.parseInt(dateEndArray[i]);
            daynowEnd[i] = Integer.toString(temp2[i]);
        }

        edit_datestart = (EditText) findViewById(R.id.edit_dateStartText);
        edit_datestart.setText(daynowStart[0] + "/" + daynowStart[1] + "/" + daynowStart[2]);

        edit_dateend = (EditText) findViewById(R.id.edit_dateEndText);
        edit_dateend.setText(daynowEnd[0] + "/" + daynowEnd[1] + "/" + daynowEnd[2]);

        calendarStart = Calendar.getInstance();

        dayStart = calendarStart.get(Calendar.DAY_OF_MONTH);
        monthStart = calendarStart.get(Calendar.MONTH);
        yearStart = calendarStart.get(Calendar.YEAR);

        calendarEnd = Calendar.getInstance();
        dayEnd = calendarEnd.get(Calendar.DAY_OF_MONTH);
        monthEnd = calendarEnd.get(Calendar.MONTH);
        yearEnd = calendarEnd.get(Calendar.YEAR);

    }

    private void bindListener() {
        mydb = new myDBclass(this);
        mDb = mydb.getWritableDatabase();
        recname="Family";
        recicon="family";
        calendar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });
        calendar_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = edit_datestart.getText().toString();
                String datestart[] = temp.split("[/]");
                String temp2 = edit_dateend.getText().toString();
                String dateend[] = temp2.split("[/]");
                dName = edit_debtname.getText().toString();
                double dAmountDouble = Double.parseDouble(edit_amount.getText().toString());
                DecimalFormat df = new DecimalFormat("#.00");
                dAmount = df.format(dAmountDouble);
                mDb.execSQL("INSERT INTO " + myDBclass.TABLE_DEBT + " ("
                        + myDBclass.DEBT_NAME + ", " + myDBclass.DEBT_AMOUNT + ", "
                        + myDBclass.DEBT_DAYSTART + ", " + myDBclass.DEBT_MONTHSTART + ", "
                        + myDBclass.DEBT_YEARSTART + ", " + myDBclass.DEBT_DAYEND + ", "
                        + myDBclass.DEBT_MONTHEND + ", " + myDBclass.DEBT_YEAREND + ") VALUES ('" + dName
                        + "', '" + dAmount + "', '" + datestart[0]
                        + "', '" + datestart[1] + "', '" +datestart[2] +
                        "', '" + dateend[0] + "', '" + dateend[1] +
                        "', '" + dateend[2] + "');");
                mydb.InsertData(null, dAmountDouble, "false", dName,recname,recicon,datestart[0],datestart[1],datestart[2]);
                createNotification(view);
                finish();
            }
        });


    }


    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        if(id==1){
            return new DatePickerDialog(this, datePickerListener1, yearEnd, monthEnd, dayEnd);
        }
        return new DatePickerDialog(this, datePickerListener, yearStart, monthStart, dayStart);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            edit_datestart.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            edit_dateend.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };


    public void onStop() {
        super.onStop();
        mydb.close();
        mDb.close();
    }

    public void createNotification(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification(android.R.drawable.btn_star_big_on,
                "New notification", System.currentTimeMillis());

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        String Title = "Payment!";
        String Message = "You paid a payment of "+dAmount+" ฿ for "+dName+", next payment will pay on "+ dayStart +"/"+monthStart+1+"/"+yearStart;

        Intent intent = new Intent(this, MenuActivity.class);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, Title, Message, activity);
        notification.number += 1;

        notification.defaults = Notification.DEFAULT_SOUND; // Sound

        notificationManager.notify(1, notification);



    }


}

