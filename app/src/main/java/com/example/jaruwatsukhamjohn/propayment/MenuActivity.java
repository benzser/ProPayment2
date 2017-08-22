package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MenuActivity extends ActionBarActivity {

    private ImageView BudgetImage,PaymentImage,ReportImage,DebtImage,SettingImage,GraphImaged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        bindwidget();
        setWidgetEventListener();

    }

    private void setWidgetEventListener() {


        //Payment Image
        PaymentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Payment.class));
            }
        });

        //Report Image
        ReportImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Report_choose.class));
            }
        });

        //Debt Image
        DebtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Debt.class));
            }
        });

        //Setting Image
        SettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Setting.class));
                finish();
            }
        });

        //Budget Image
        BudgetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Budget.class));
            }
        });
        //Graph Image
        GraphImaged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Graph.class));
            }
        });
    }

    private void bindwidget() {

        BudgetImage = (ImageView)findViewById(R.id.budget);
        PaymentImage = (ImageView)findViewById(R.id.payment_image);
        ReportImage = (ImageView)findViewById(R.id.ReportImage);
        DebtImage = (ImageView)findViewById(R.id.menu_debt);
        SettingImage = (ImageView)findViewById(R.id.menu_setting);
        GraphImaged = (ImageView)findViewById(R.id.GraphImage);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
}
