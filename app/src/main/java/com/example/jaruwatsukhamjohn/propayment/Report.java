package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import DB.myDBclass;


public class Report extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    myDBclass mydb;
    private TextView maxcost;
    String tmp[],title;
    double extemp=0,intemp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        bindwidget();
        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM "
                + myDBclass.TABLE_TRANSACTIONS, null);

        ListView listView1 = (ListView) findViewById(R.id.ReportListview);
        ReportAdapter repadapter = new ReportAdapter(this, mCursor);
        listView1.setAdapter(repadapter);
        listView1.setOnItemClickListener(this);

    }

    private void bindwidget() {
        title=getApplicationContext().getResources().getString(R.string.history);
        this.setTitle(title);
        maxcost = (TextView) findViewById(R.id.tvmaxcost);

        mydb = new myDBclass(this);
        mydb.getWritableDatabase();

        //Check Table max not null
        int check = mydb.CheckTableNotNull(myDBclass.TABLE_MAX);

        if(check >0){  // Have Data in table

            String tmp[] = mydb.SelectMaxFromMonth("4","2015");
            Double sumMAX = Double.parseDouble(tmp[0]);
            DecimalFormat df = new DecimalFormat("#.00");
            tmp[0] = df.format(sumMAX);
            if(extemp >Double.parseDouble(tmp[0].toString())){

                maxcost.setTextColor(Color.parseColor("#FFFF0000"));

            }

            //true= income  false = expense  []amount[]type(true,false)
            String arrData[][] = mydb.SelectAllAmountData();


            for(int i =0; i < arrData.length;i++){
                if(arrData[i][1].equalsIgnoreCase("false")){
                    extemp += Double.parseDouble(arrData[i][0]); //expense total
                }else{
                    intemp += Double.parseDouble(arrData[i][0]); //income total
                }

            }

            maxcost.setText(tmp[0].toString()+" ฿");

        }else{


            maxcost.setText("0");
           // maxcost.setText();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String transid =    mCursor.getString(mCursor.getColumnIndex("_id"));
        String transamount = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_AMOUNT));
        String transtype = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_TYPE));
        String categoryname = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_CATE));
        String transdescription = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_DESCRIPTION));
        String transday = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_DAY));
        String transmonth = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_MONTH));
        String transyear = mCursor.getString(mCursor.getColumnIndex(myDBclass.TRAN_YEAR));

        Intent intent = new Intent(getApplicationContext(), ReportEdit.class);
        intent.putExtra("TRANSID", transid);
        intent.putExtra("TRANSAMOUNT", transamount);
        intent.putExtra("TRANSTYPE", transtype);
        intent.putExtra("CATEGORYNAME", categoryname);
        intent.putExtra("TRANSDESCRIPTION", transdescription);
        intent.putExtra("TRANSDAY", transday);
        intent.putExtra("TRANSMONTH", transmonth);
        intent.putExtra("TRANSYEAR", transyear);
        startActivity(intent);

    }

    public void onPause() {
        super.onPause();
        mHelper.close();
        mDb.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
