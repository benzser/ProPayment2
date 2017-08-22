package com.example.jaruwatsukhamjohn.propayment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DB.myDBclass;


public class debtedit extends ActionBarActivity {

    myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    String debtname, debtamount,startday,startmonth,startyear,endday,endmonth,endyear,title;
    private Calendar calendarStart, calendarEnd;
    private ImageButton calendar_start, calendar_end;
    private int dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd, temp1[], temp2[];
    private EditText editdatestart,editdateend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debtedit);

        title=getApplicationContext().getResources().getString(R.string.editpayment);
        this.setTitle(title);
        debtname = getIntent().getExtras().getString("DEBTNAME");
        debtamount = getIntent().getExtras().getString("DEBTLAMOUNT");
        startday = getIntent().getExtras().getString("STARTDAY");
        startmonth = getIntent().getExtras().getString("STARTMONTH");
        startyear = getIntent().getExtras().getString("STARTYEAR");
        endday = getIntent().getExtras().getString("ENDDAY");
        endmonth = getIntent().getExtras().getString("ENDMONTH");
        endyear = getIntent().getExtras().getString("ENDYEAR");

        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + myDBclass.TABLE_DEBT
                + " WHERE " + myDBclass.DEBT_NAME + "='" + debtname + "'"
                + " AND " + myDBclass.DEBT_AMOUNT  + "='" + debtamount + "'"
                + " AND " + myDBclass.DEBT_DAYSTART  + "='" + startday + "'"
                + " AND " + myDBclass.DEBT_MONTHSTART  + "='" + startmonth + "'"
                + " AND " + myDBclass.DEBT_YEARSTART  + "='" + startyear + "'"
                + " AND " + myDBclass.DEBT_DAYEND  + "='" + endday + "'"
                + " AND " + myDBclass.DEBT_MONTHEND  + "='" + endmonth + "'"
                + " AND " + myDBclass.DEBT_YEAREND + "='" + endyear  + "'", null);

        final EditText editnamedebt = (EditText)findViewById(R.id.DebtnameEdittextE);
        editnamedebt.setText(debtname);
        final EditText editamountdebt = (EditText)findViewById(R.id.DebtamountEdittextE);
        editamountdebt.setText(debtamount);
        editdateend = (EditText)findViewById(R.id.edit_dateEndTextE);
        editdateend.setText(endday+"/"+endmonth+"/"+endyear);
        calendar_end =(ImageButton)findViewById(R.id.DebtEndCalendarImageE);
        calendar_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String dateEndnow = ft.format(dNow).toString();
        String dateEndArray[] = dateEndnow.split("[/]");
        temp2 = new int[3];
        String[] daynowEnd = new String[3];


        for (int i = 0; i < dateEndArray.length; i++) {
            temp2[i] = Integer.parseInt(dateEndArray[i]);
            daynowEnd[i] = Integer.toString(temp2[i]);
        }


        editdateend.setText(daynowEnd[0] + "/" + daynowEnd[1] + "/" + daynowEnd[2]);
        editamountdebt.addTextChangedListener(new DecimalInputTextWatcher(editamountdebt, 2));

        calendarEnd = Calendar.getInstance();
        dayEnd = calendarEnd.get(Calendar.DAY_OF_MONTH);
        monthEnd = calendarEnd.get(Calendar.MONTH);
        yearEnd = calendarEnd.get(Calendar.YEAR);

        Button buttonUpdate = (Button)findViewById(R.id.EDITDebtbutton);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String debtnameUpdate = editnamedebt.getText().toString();
                String temp2 = editdateend.getText().toString();
                String dateend[] = temp2.split("[/]");
                double dAmountDouble = Double.parseDouble(editamountdebt.getText().toString());
                DecimalFormat df = new DecimalFormat("#.00");
                String debtamountUpdate = df.format(dAmountDouble);

                if(debtnameUpdate.length() != 0 && debtamountUpdate.length() != 0 ) {
                    mDb.execSQL("UPDATE " + myDBclass.TABLE_DEBT  + " SET "
                            + myDBclass.DEBT_NAME + "='" + debtnameUpdate + "', "
                            + myDBclass.DEBT_AMOUNT + "='" + debtamountUpdate + "', "
                            + myDBclass.DEBT_DAYEND + "='" + dateend[0] + "', "
                            + myDBclass.DEBT_MONTHEND + "='" + dateend[1] + "', "
                            +  myDBclass.DEBT_YEAREND + "='" + dateend[2]
                            + "' WHERE " + myDBclass.DEBT_NAME + "='" + debtname + "'"
                            + " AND " + myDBclass.DEBT_AMOUNT + "='" + debtamount + "'"
                            + " AND " + myDBclass.DEBT_DAYEND + "='" + endday + "'"
                            + " AND " + myDBclass.DEBT_MONTHEND + "='" + endmonth + "'"
                            + " AND " + myDBclass.DEBT_YEAREND + "='" + endyear + "';");

                    Toast.makeText(getApplicationContext(), "Edit Success"
                            , Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Name & Amount"
                            , Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debtedit, menu);
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

            return new DatePickerDialog(this, datePickerListener1, yearEnd, monthEnd, dayEnd);


    }


    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            editdateend.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };
}
