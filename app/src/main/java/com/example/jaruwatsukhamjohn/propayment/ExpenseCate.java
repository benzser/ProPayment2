package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import DB.myDBclass;


public class ExpenseCate extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private myDBclass mHelper;
    private Cursor iCursor;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_cate);
        this.setTitle("Expense Category");
        bindwidget();
    }

    private void bindwidget() {
        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        iCursor = mDb.rawQuery("SELECT * FROM "
                + myDBclass.TABLE_EXPENSECATE
                +" ORDER BY "+myDBclass.INCATE_NAME, null);

        ListView listView1 = (ListView) findViewById(R.id.incomelistpay);
        CategoryIncomeAdapter incateadapter = new CategoryIncomeAdapter(this, iCursor);
        listView1.setAdapter(incateadapter);
        listView1.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_cate, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String sname = iCursor.getString(iCursor.getColumnIndex(myDBclass.EXPENSECATE_NAME));
        String sicon = iCursor.getString(iCursor.getColumnIndex(myDBclass.EXPENSECATE_ICON));


        //Toast.makeText(getApplicationContext(), "Hello " + position + " ID : " + id + " Name : " + sname + ",Icon:" + sicon, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("name",sname);
        intent.putExtra("icon",sicon);
        setResult(RESULT_OK, intent);
        finish();
    }
}
