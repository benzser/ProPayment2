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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import DB.myDBclass;


public class report_day extends ActionBarActivity implements AdapterView.OnItemClickListener {

    EditText edit_date_day;
    Button search;
    private myDBclass mydb;
    String[] daynow = new String[3];
    String balance;
    private int day, month, year, temp2[];
    RadioButton radioincome, radioexpense, radioboth;
    private ImageButton calendar_button;
    private Calendar calendar;
    double allincome=0,allexpenese=0;
    private myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    private TextView Balance, numday, tvday, tvmonth, tvyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_day);
        bindwidget();
        bindlistener();
        this.setTitle("Report");

    }

    private void bindlistener() {
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get day month year to set Header
                String sday = edit_date_day.getText().toString();
                String[] aday = sday.split("[/]");
                setHeader(aday[0], aday[1], aday[2]);

                if (radioincome.isChecked()) { // if click will return ture

                    mCursor = mDb.rawQuery("SELECT * FROM "
                            + myDBclass.TABLE_TRANSACTIONS + " WHERE " + myDBclass.TRAN_TYPE + " = 'true' AND " + myDBclass.TRAN_DAY + " = " + aday[0] + " AND " +
                            myDBclass.TRAN_MONTH + " = " + aday[1] + " AND " + myDBclass.TRAN_YEAR + " = " + aday[2], null);

                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
                } else if (radioexpense.isChecked()) {

                    mCursor = mDb.rawQuery("SELECT * FROM "
                            + myDBclass.TABLE_TRANSACTIONS + " WHERE " + myDBclass.TRAN_TYPE + " = 'false' AND " + myDBclass.TRAN_DAY + " = " + aday[0] + " AND " +
                            myDBclass.TRAN_MONTH + " = " + aday[1] + " AND " + myDBclass.TRAN_YEAR + " = " + aday[2], null);

                } else if (radioboth.isChecked()) {
                    mCursor = mDb.rawQuery("SELECT * FROM "
                            + myDBclass.TABLE_TRANSACTIONS + " WHERE " + myDBclass.TRAN_DAY + " = " + aday[0] + " AND " +
                            myDBclass.TRAN_MONTH + " = " + aday[1] + " AND " + myDBclass.TRAN_YEAR + " = " + aday[2], null);

                }


                ListView listView1 = (ListView) findViewById(R.id.report_daylist);
                ReportAdapter repadapter = new ReportAdapter(getApplicationContext(), mCursor);
                listView1.setAdapter(repadapter);

                String[][] transaction = mydb.SelectAllAmountData();

                for (int i = 0; i < transaction.length; i++) {


                    /*transaction[i][6] day
                    transaction[i][7] month      &&AND
                    transaction[i][8] year*/

                    if (transaction[i][2].equalsIgnoreCase(aday[0]) &&
                        transaction[i][3].equalsIgnoreCase(aday[1]) &&
                        transaction[i][4].equalsIgnoreCase(aday[2])) {

                        if(transaction[i][1].equalsIgnoreCase("true")){
                            allincome += Double.parseDouble(transaction[i][0]);
                        }else{
                            allexpenese += Double.parseDouble(transaction[i][0]);
                        }

                        double tmp = allincome-allexpenese;
                        DecimalFormat df = new DecimalFormat("#,###,###.00");
                        balance = df.format(tmp);
                        Balance.setText(balance);
                    }
                }
               // Toast.makeText(getApplicationContext(),transaction[0][2]+"/"+aday[0]+" month "+transaction[0][3]+" year "+transaction[0][4], Toast.LENGTH_LONG).show();
            }


        });

    }

    private void setHeader(String day, String month, String year) {
        //numday
        String snumday = day;
        //month
        String smonth = month;
        String namemonth = checkmonth(smonth);
        //year
        String syear = year;

        //nameday
        int iday;
        Calendar cal = Calendar.getInstance();
        int tmpday = Integer.valueOf(snumday);
        int tmpmonth = Integer.valueOf(smonth);
        int tmpyear = Integer.valueOf(syear);

        String dateString = String.format("%d-%d-%d", tmpyear, tmpmonth, tmpday);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);


        numday.setText(snumday);
        tvmonth.setText(namemonth);
        tvyear.setText(syear);
        tvday.setText(dayOfWeek);
    }

    private void bindwidget() {
        mydb = new myDBclass(this);
        mydb.getWritableDatabase();


        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();


        edit_date_day = (EditText) findViewById(R.id.edit_date_day);
        search = (Button) findViewById(R.id.testbutton);
        radioincome = (RadioButton) findViewById(R.id.radio_income);
        radioexpense = (RadioButton) findViewById(R.id.radio_expense);
        radioboth = (RadioButton) findViewById(R.id.radio_both);

        //set default check
        radioboth.setChecked(true);


        //Date
        Date dNow = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String datenow = ft.format(dNow).toString();
        String[] date = datenow.split("[/]");
        temp2 = new int[3];


        for (int i = 0; i < date.length; i++) {
            temp2[i] = Integer.parseInt(date[i]);
            daynow[i] = Integer.toString(temp2[i]);
        }


        //Calendar
        edit_date_day = (EditText) findViewById(R.id.edit_date_day);
        edit_date_day.setText(daynow[0] + "/" + daynow[1] + "/" + daynow[2]);
        calendar_button = (ImageButton) findViewById(R.id.calendar_day);
        calendar = Calendar.getInstance();

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        //Header
        Balance = (TextView) findViewById(R.id.BalanceTV);
        Balance.setText("0");

        numday = (TextView) findViewById(R.id.numDayTV);
        tvday = (TextView) findViewById(R.id.dayTV);
        tvmonth = (TextView) findViewById(R.id.MonthTV);
        tvyear = (TextView) findViewById(R.id.YearTV);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_week, menu);
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
            edit_date_day.setText(selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear);
        }
    };

    private String checkmonth(String smonth) {
        String temp = "";
        int tmp = Integer.valueOf(smonth);

        switch (tmp) {
            case 1:
                temp = "January";
                break;
            case 2:
                temp = "February";
                break;
            case 3:
                temp = "March";
                break;
            case 4:
                temp = "April";
                break;
            case 5:
                temp = "May";
                break;
            case 6:
                temp = "June";
                break;
            case 7:
                temp = "July";
                break;
            case 8:
                temp = "August";
                break;
            case 9:
                temp = "September";
                break;
            case 10:
                temp = "October";
                break;
            case 11:
                temp = "November";
                break;
            case 12:
                temp = "December";
                break;
            default:
                temp = "Invalid month";
                break;
        }
        return temp;
    }

    private String checknameday(int iday) {
        String temp = "";
        switch (iday) {
            case 1:
                temp = "Sunday";
                break;
            case 2:
                temp = "Monday";
                break;
            case 3:
                temp = "Tueseday";
                break;
            case 4:
                temp = "Wednesday";
                break;
            case 5:
                temp = "Thursday";
                break;
            case 6:
                temp = "Friday";
                break;
            case 7:
                temp = "Saturday";
                break;
            default:
                break;
        }
        return temp;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
