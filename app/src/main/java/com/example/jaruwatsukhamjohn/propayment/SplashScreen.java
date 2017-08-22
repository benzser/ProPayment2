package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import DB.myDBclass;


public class SplashScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myDBclass mydb = new myDBclass(this);
        mydb.getWritableDatabase();

        int checkincome = mydb.CheckTableNotNull(myDBclass.TABLE_INCATE);
        int checkexpense = mydb.CheckTableNotNull(myDBclass.TABLE_EXPENSECATE);
       // Toast.makeText(getApplicationContext(),"in "+checkincome+" ex "+checkexpense,Toast.LENGTH_SHORT).show();

        if(checkincome > 0 && checkexpense >0 ) {  // Have Data in table

        }else{
            //Add Income Category
            mydb.INSERTINCATE(null,"Award","true","award");
            mydb.INSERTINCATE(null,"Debt","true","debt");
            mydb.INSERTINCATE(null,"Give","true","give");
            mydb.INSERTINCATE(null,"Salary","true","salary");
            mydb.INSERTINCATE(null,"Selling","true","selling");
            mydb.INSERTINCATE(null,"Other","true","other");
            mydb.INSERTEXCATE(null,"Education","true","education");
            mydb.INSERTEXCATE(null,"Entertainment","true","entertainment");
            mydb.INSERTEXCATE(null,"Family","true","family");
            mydb.INSERTEXCATE(null,"Food","true","food");
            mydb.INSERTEXCATE(null,"Drink","true","drink");
            mydb.INSERTEXCATE(null,"Invest","true","invest");
            mydb.INSERTEXCATE(null,"Loan","true","loan");
            mydb.INSERTEXCATE(null,"Shopping","true","shopping");
            mydb.INSERTEXCATE(null,"Transport","true","transport");
            mydb.INSERTEXCATE(null,"Travel","true","travel");
            mydb.INSERTEXCATE(null,"Other","true","other");
        }



        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }
        },3000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
