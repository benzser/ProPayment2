package com.example.jaruwatsukhamjohn.propayment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DB.myDBclass;


public class ExpensePayment extends ActionBarActivity {


    private EditText edit_date, edit_amount, edit_des, cate_expense;
    private ImageButton calendar_button;
    private Button confirm,back;
    private Calendar calendar;
    private int day, month, year, temp2[];
    private myDBclass mydb;
    String[] daynow = new String[3];
    ImageView imagecategory;
    String recname,recicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_payment);
        this.setTitle("Expense");
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

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double damount = Double.parseDouble(edit_amount.getText().toString());


                String des = edit_des.getText().toString();

                //date
                String temp = edit_date.getText().toString();
                String date[] = temp.split("[/]");


               Toast.makeText(getApplication(), "Expense Added", Toast.LENGTH_SHORT).show();

                //String arrData[] = mydb.SelectDailyID("1");
                mydb.InsertData(null, damount, "false", des,recname,recicon,date[0],date[1],date[2]);
                edit_amount.setText("");
                edit_des.setText("");
                cate_expense.setText("");
                recicon="";
                imagecategory.setBackgroundResource(R.drawable.ic_question);

            }
        });

        imagecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseCate.class);
                startActivityForResult(intent, 2);

            }
        });

        //Back Button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void bindWidget() {


        mydb = new myDBclass(this);
        mydb.getWritableDatabase();


        //Iamge

        imagecategory = (ImageView) findViewById(R.id.image_cateexpense);


        //Button
        confirm = (Button)findViewById(R.id.Confirm_Expense);
        back = (Button) findViewById(R.id.Back_Expense);

        //Edit Text
        cate_expense = (EditText) findViewById(R.id.select_cateexpense);
        edit_amount = (EditText) findViewById(R.id.edit_amount_expense_payment);
        edit_amount.addTextChangedListener(new DecimalInputTextWatcher(edit_amount, 2));
        edit_des = (EditText) findViewById(R.id.edit_des_expense_payment);



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


        //Calendar
        edit_date = (EditText) findViewById(R.id.edit_date_expense_payment);
        edit_date.setText(daynow[0] + "/" + daynow[1] + "/" + daynow[2]);
        calendar_button = (ImageButton) findViewById(R.id.Calendar_expense_payment);
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                recname = data.getStringExtra("name");
                recicon = data.getStringExtra("icon");
                cate_expense.setText(recname);
                checkimage(recicon);
            }
        }

    }

    public void checkimage(String image){
        switch (image) {
            case "award":
                imagecategory.setBackgroundResource(R.drawable.ic_award);
                break;
            case "basketball":
                imagecategory.setBackgroundResource(R.drawable.ic_basketball);
                break;
            case "book":
                imagecategory.setBackgroundResource(R.drawable.ic_book);
                break;
            case "box":
                imagecategory.setBackgroundResource(R.drawable.ic_box);;
                break;
            case "cake":
                imagecategory.setBackgroundResource(R.drawable.ic_cake);
                break;
            case "coffee":
                imagecategory.setBackgroundResource(R.drawable.ic_coffee);
                break;
            case "debt":
                imagecategory.setBackgroundResource(R.drawable.ic_debt);
                break;
            case "diamond":
                imagecategory.setBackgroundResource(R.drawable.ic_diamond);
                break;
            case "dice":
                imagecategory.setBackgroundResource(R.drawable.ic_dice);
                break;
            case "divide":
                imagecategory.setBackgroundResource(R.drawable.ic_divide);
                break;
            case "drink":
                imagecategory.setBackgroundResource(R.drawable.ic_drink);
                break;
            case "education":
                imagecategory.setBackgroundResource(R.drawable.ic_education);
                break;
            case "entertainment":
                imagecategory.setBackgroundResource(R.drawable.ic_entertainment);
                break;
            case "family":
                imagecategory.setBackgroundResource(R.drawable.ic_family);
                break;
            case "food":
                imagecategory.setBackgroundResource(R.drawable.ic_food);
                break;
            case "football":
                imagecategory.setBackgroundResource(R.drawable.ic_football);
                break;
            case "give":
                imagecategory.setBackgroundResource(R.drawable.ic_give);
                break;
            case "icecream":
                imagecategory.setBackgroundResource(R.drawable.ic_icecream);
                break;
            case "invest":
                imagecategory.setBackgroundResource(R.drawable.ic_invest);
                break;
            case "loan":
                imagecategory.setBackgroundResource(R.drawable.ic_loan);
                break;
            case "music":
                imagecategory.setBackgroundResource(R.drawable.ic_music);
                break;
            case "phone":
                imagecategory.setBackgroundResource(R.drawable.ic_phone);
                break;
            case "picture":
                imagecategory.setBackgroundResource(R.drawable.ic_picture);
                break;
            case "salary":
                imagecategory.setBackgroundResource(R.drawable.ic_salary);
                break;
            case "selling":
                imagecategory.setBackgroundResource(R.drawable.ic_selling);
                break;
            case "shopping":
                imagecategory.setBackgroundResource(R.drawable.ic_shopping);
                break;
            case "transport":
                imagecategory.setBackgroundResource(R.drawable.ic_transport);
                break;
            case "travel":
                imagecategory.setBackgroundResource(R.drawable.ic_travel);
                break;
            case "tv":
                imagecategory.setBackgroundResource(R.drawable.ic_tv);
            default:
                imagecategory.setBackgroundResource(R.drawable.ic_other);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_payment, menu);
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
