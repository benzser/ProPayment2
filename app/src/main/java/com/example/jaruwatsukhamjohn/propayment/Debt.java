package com.example.jaruwatsukhamjohn.propayment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import DB.myDBclass;


public class Debt extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private Button adddebt_button;
    private myDBclass mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    TextView debttotalamount,debtamounttextview;
    String namepay,amountpay,startpay,endpay,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        bindWidget();
        bindListener();
        namepay=getApplicationContext().getResources().getString(R.string.name);
        amountpay=getApplicationContext().getResources().getString(R.string.Amount);
        startpay=getApplicationContext().getResources().getString(R.string.starttime);
        endpay=getApplicationContext().getResources().getString(R.string.endtime);
        mHelper = new myDBclass(this);
        mDb = mHelper.getWritableDatabase();

        mCursor = mDb.rawQuery("SELECT * FROM "
                + myDBclass.TABLE_DEBT, null);

        ListView listView1 = (ListView) findViewById(R.id.debtListview);
        DebtAdapter debtadapter = new DebtAdapter(this, mCursor);
        listView1.setAdapter(debtadapter);
        listView1.setOnItemClickListener(this);
        double sum=getTotal();
        DecimalFormat df = new DecimalFormat("#.00");
        String dAmount = df.format(sum);
        debttotalamount = (TextView) findViewById(R.id.totaldebtamount);
        debttotalamount.setText(""+dAmount+" ฿");


    }

    private void bindWidget() {
        title=getApplicationContext().getResources().getString(R.string.debt);
        this.setTitle(title);
        adddebt_button = (Button) findViewById(R.id.AddDebtbutton);
    }

    private void bindListener() {
        adddebt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add_Debt.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debt, menu);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Dialog dialog = new Dialog(Debt.this);
        dialog.requestWindowFeature
                (dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.debtdialog_data);
        TextView debtNameTV =
                (TextView) dialog.findViewById(R.id.debtnameDialog);

        debtNameTV.setText(namepay+" : "
                + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_NAME)));

        TextView debtAmountTV =
                (TextView) dialog.findViewById(R.id.debtamountDialog);
        debtAmountTV.setText(amountpay+" : "
                + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_AMOUNT)));

        TextView debtStartTV =
                (TextView) dialog.findViewById(R.id.debtdateStartDialog);
        debtStartTV.setText( startpay+" : "
                + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_DAYSTART)) + " / " + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_MONTHSTART)) + " / " + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_YEARSTART)));

        TextView debtEndTV =
                (TextView) dialog.findViewById(R.id.debtdateEndDialog);
        debtEndTV.setText(endpay+" : "
                + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_DAYEND)) + " / " + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_MONTHEND)) + " / " + mCursor.getString(mCursor.getColumnIndex
                (myDBclass.DEBT_YEAREND)));

        Button buttonOK =
                (Button) dialog.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        Button buttonedit =
                (Button) dialog.findViewById(R.id.buttoneditdebt);
        buttonedit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
                String debtname = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_NAME));
                String debtamount = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_AMOUNT));
                String startday = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_DAYSTART));
                String startmonth = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_MONTHSTART));
                String startyear = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_YEARSTART));
                String endday = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_DAYEND));
                String endmonth = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_MONTHEND));
                String endyear = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_YEAREND));

                Intent intent = new Intent(getApplicationContext(), debtedit.class);
                intent.putExtra("DEBTNAME", debtname);
                intent.putExtra("DEBTLAMOUNT", debtamount);
                intent.putExtra("STARTDAY", startday);
                intent.putExtra("STARTMONTH", startmonth);
                intent.putExtra("STARTYEAR", startyear);
                intent.putExtra("ENDDAY", endday);
                intent.putExtra("ENDMONTH", endmonth);
                intent.putExtra("ENDYEAR", endyear);
                startActivity(intent);
            }
        });

        // Delete Debt
        Button buttondel =
                (Button) dialog.findViewById(R.id.buttondeletedebt);
        buttondel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Debt.this);
                builder.setTitle("Delete Debt");
                builder.setMessage("Do you want to delete?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        String debtname = mCursor.getString(mCursor.getColumnIndex(myDBclass.DEBT_NAME));
                        mDb.execSQL("delete from "+myDBclass.TABLE_DEBT+ " WHERE " + myDBclass.DEBT_NAME + "='" + debtname + "';");

                        finish();
                        startActivity(getIntent());

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

        dialog.show();
    }

    public double getTotal() {
// TODO Auto-generated method stub
        double sum=0;

        Cursor cursor = mDb.rawQuery(
                "SELECT SUM(debt_amount) FROM debt ", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            sum=cursor.getDouble(0);
        }
        return sum;
    }
}
