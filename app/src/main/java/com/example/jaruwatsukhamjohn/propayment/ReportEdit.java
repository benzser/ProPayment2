package com.example.jaruwatsukhamjohn.propayment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DB.myDBclass;


public class ReportEdit extends ActionBarActivity {

    myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    private Calendar calendarreport;
    private ImageButton calendarreportIMG,plus,minus;
    private int day, month, year, temp1[];
    private EditText editdate;
    String transamount,transtype,categoryname,transdescription,transday,transmonth,transyear,transid;
    Spinner spinnerMenu;
    private Button deletebutton;
    private String title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_edit);
        title=getApplicationContext().getResources().getString(R.string.editreport);
        this.setTitle(title);
        transid =  getIntent().getExtras().getString("TRANSID");
        transamount = getIntent().getExtras().getString("TRANSAMOUNT");
        transtype = getIntent().getExtras().getString("TRANSTYPE");
        categoryname = getIntent().getExtras().getString("CATEGORYNAME");
        transdescription = getIntent().getExtras().getString("TRANSDESCRIPTION");
        transday = getIntent().getExtras().getString("TRANSDAY");
        transmonth = getIntent().getExtras().getString("TRANSMONTH");
        transyear = getIntent().getExtras().getString("TRANSYEAR");

        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM " + myDBclass.TABLE_TRANSACTIONS
                + " WHERE " + myDBclass.TRAN_AMOUNT + "='" + transamount + "'"
                + " AND " + myDBclass.TRAN_TYPE  + "='" + transtype + "'"
                + " AND " + myDBclass.TRAN_CATE  + "='" + categoryname + "'"
                + " AND " + myDBclass.TRAN_DESCRIPTION  + "='" + transdescription + "'"
                + " AND " + myDBclass.TRAN_DAY  + "='" + transday + "'"
                + " AND " + myDBclass.TRAN_MONTH  + "='" + transmonth + "'"
                + " AND " + myDBclass.TRAN_YEAR  + "='" + transyear + "'", null);

        final EditText reportamount = (EditText)findViewById(R.id.ReportamountET);
        reportamount.setText(transamount);
        reportamount.addTextChangedListener(new DecimalInputTextWatcher(reportamount, 2));
        final EditText reportdesc = (EditText)findViewById(R.id.edit_des);
        reportdesc.setText(transdescription);
        editdate = (EditText)findViewById(R.id.reportedit_date);
        editdate.setText(transday+"/"+transmonth+"/"+transyear);
        calendarreportIMG = (ImageButton) findViewById(R.id.CalendarImageButtonReport);

        calendarreportIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });
        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String dateStartnow = ft.format(dNow).toString();
        String dateStartArray[] = dateStartnow.split("[/]");
        temp1 = new int[3];
        String[] daynowStart = new String[3];

        for (int i = 0; i < dateStartArray.length; i++) {
            temp1[i] = Integer.parseInt(dateStartArray[i]);
            daynowStart[i] = Integer.toString(temp1[i]);
        }

        editdate.setText(daynowStart[0] + "/" + daynowStart[1] + "/" + daynowStart[2]);

        calendarreport = Calendar.getInstance();
        day = calendarreport.get(Calendar.DAY_OF_MONTH);
        month = calendarreport.get(Calendar.MONTH);
        year = calendarreport.get(Calendar.YEAR);

        spinnerMenu = (Spinner) findViewById(R.id.spinner_menu_income_payment);
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            data.add("Menu#" + i);
        }

        customAdapterSp customAdapter = new customAdapterSp(this, data);
        spinnerMenu.setAdapter(customAdapter);

        // Delete button
        deletebutton =(Button) findViewById(R.id.reportdelbutton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReportEdit.this);
                builder.setTitle("Delete Report");
                builder.setMessage("Do you want to delete?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        mHelper.DeleteData(transid);


                        finish();

                        Toast.makeText(getApplicationContext(), "Deleted"
                                , Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();



            }
        });

        plus = (ImageButton) findViewById(R.id.ReportPlusimageButton);
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String reportDesUpdate = reportdesc.getText().toString();
                String temp = editdate.getText().toString();
                String datestart[] = temp.split("[/]");
                double damount = Double.parseDouble(reportamount.getText().toString());

                String type = checkSpinner(spinnerMenu.getSelectedItemPosition());

                if (reportamount.length() != 0) {
                    mDb.execSQL("UPDATE " + myDBclass.TABLE_TRANSACTIONS + " SET "
                            + myDBclass.TRAN_AMOUNT + "='" + damount + "', "
                            + myDBclass.TRAN_TYPE + "='" + true + "', "
                            + myDBclass.TRAN_CATE + "='" + type + "', "
                            + myDBclass.TRAN_DESCRIPTION + "='" + reportDesUpdate + "', "
                            + myDBclass.TRAN_DAY + "='" + datestart[0] + "', "
                            + myDBclass.TRAN_MONTH + "='" + datestart[1] + "', "
                            + myDBclass.TRAN_YEAR + "='" + datestart[2]
                            + "' WHERE " + myDBclass.TRAN_AMOUNT + "='" + transamount + "'"
                            + " AND " + myDBclass.TRAN_TYPE + "='" + transtype + "'"
                            + " AND " + myDBclass.TRAN_CATE + "='" + categoryname + "'"
                            + " AND " + myDBclass.TRAN_DESCRIPTION + "='" + transdescription + "'"
                            + " AND " + myDBclass.TRAN_DAY + "='" + transday + "'"
                            + " AND " + myDBclass.TRAN_MONTH + "='" + transmonth + "'"
                            + " AND " + myDBclass.TRAN_YEAR + "='" + transyear + "';");

                Toast.makeText(getApplicationContext(), "Edit Success"
                        , Toast.LENGTH_SHORT).show();

                finish();

            }

            else

            {
                Toast.makeText(getApplicationContext(), "Please Enter Amount"
                        , Toast.LENGTH_SHORT).show();
            }



        }
    });
        minus = (ImageButton) findViewById(R.id.ReportMinusimageButton);
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String reportDesUpdate = reportdesc.getText().toString();
                String temp = editdate.getText().toString();
                String datestart[] = temp.split("[/]");
                double damount = Double.parseDouble(reportamount.getText().toString());

                String type = checkSpinner(spinnerMenu.getSelectedItemPosition());

                if (reportamount.length() != 0) {
                    mDb.execSQL("UPDATE " + myDBclass.TABLE_TRANSACTIONS + " SET "
                            + myDBclass.TRAN_AMOUNT + "='" + damount + "', "
                            + myDBclass.TRAN_TYPE + "='" + false + "', "
                            + myDBclass.TRAN_CATE + "='" + type + "', "
                            + myDBclass.TRAN_DESCRIPTION + "='" + reportDesUpdate + "', "
                            + myDBclass.TRAN_DAY + "='" + datestart[0] + "', "
                            + myDBclass.TRAN_MONTH + "='" + datestart[1] + "', "
                            + myDBclass.TRAN_YEAR + "='" + datestart[2]
                            + "' WHERE " + myDBclass.TRAN_AMOUNT + "='" + transamount + "'"
                            + " AND " + myDBclass.TRAN_TYPE + "='" + transtype + "'"
                            + " AND " + myDBclass.TRAN_CATE + "='" + categoryname + "'"
                            + " AND " + myDBclass.TRAN_DESCRIPTION + "='" + transdescription + "'"
                            + " AND " + myDBclass.TRAN_DAY + "='" + transday + "'"
                            + " AND " + myDBclass.TRAN_MONTH + "='" + transmonth + "'"
                            + " AND " + myDBclass.TRAN_YEAR + "='" + transyear + "';");

                    Toast.makeText(getApplicationContext(), "Edit Success"
                            , Toast.LENGTH_SHORT).show();

                    finish();

                }

                else

                {
                    Toast.makeText(getApplicationContext(), "Please Enter Amount"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_edit, menu);
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
            editdate.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };

    private String checkSpinner(int index) {
        String temp = null;
        switch (index) {
            case 0:
                temp = "Drink";
                break;
            case 1:
                temp = "Food";
                break;
            case 2:
                temp = "Game";
                break;
            case 3:
                temp = "Gift";
                break;
            case 4:
                temp = "Travel";
                break;
            default:
                break;
        }
        return temp;
    }
}
