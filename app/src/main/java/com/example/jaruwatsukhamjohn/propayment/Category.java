package com.example.jaruwatsukhamjohn.propayment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import DB.myDBclass;


public class Category extends ActionBarActivity {


    private myDBclass mHelper;
    private SQLiteDatabase mDb;
    private Cursor iCursor,eCursor;
    private ListView listView1,listView2;
    private Button addincomecate,addexpensecate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindwidget();
        bindlistener();
    }

    private void bindlistener() {
        listView1.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        listView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        addincomecate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategory.class);
                intent.putExtra("type","in");
                startActivityForResult(intent, 2);
            }
        });

        addexpensecate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategory.class);
                setResult(RESULT_OK, intent);
                intent.putExtra("type","ex");
                startActivityForResult(intent, 3);
            }
        });


    }

    private void bindwidget() {

        addincomecate = (Button) findViewById(R.id.addincome);
        addexpensecate = (Button) findViewById(R.id.addexpense);


        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        iCursor = mDb.rawQuery("SELECT * FROM "
                + myDBclass.TABLE_INCATE+" ORDER BY "+myDBclass.INCATE_NAME, null);

        listView1 = (ListView) findViewById(R.id.incomelist);

        ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView1.getLayoutParams();
        listViewParams.height = 840;
        listView1.requestLayout();

        CategoryIncomeAdapter incateadapter = new CategoryIncomeAdapter(this, iCursor);
        listView1.setAdapter(incateadapter);



        eCursor = mDb.rawQuery("SELECT * FROM "
                + myDBclass.TABLE_EXPENSECATE+" ORDER BY "+myDBclass.EXPENSECATE_NAME, null);

        listView2 = (ListView) findViewById(R.id.expenselist);

        ViewGroup.LayoutParams listViewParams2 = (ViewGroup.LayoutParams)listView2.getLayoutParams();
        listViewParams2.height = 840;
        listView2.requestLayout();

        CategoryExpenseAdapter excateadapter = new CategoryExpenseAdapter(this, eCursor);
        listView2.setAdapter(excateadapter);

    }


    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("Add");
        ret_val.add("Edit");
        ret_val.add("Cancel");
        return ret_val;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                bindwidget();
            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                bindwidget();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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
