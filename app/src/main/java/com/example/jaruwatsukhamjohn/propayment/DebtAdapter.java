package com.example.jaruwatsukhamjohn.propayment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;

import DB.myDBclass;

/**
 * Created by Ubu on 3/26/2015.
 */
public class DebtAdapter extends CursorAdapter {


    public DebtAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.debtlistview_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvdebtname = (TextView) view.findViewById(R.id.DebtnameListview);
        TextView tvdebtamount = (TextView) view.findViewById(R.id.DebtamountListview);
        // Extract properties from cursor
        String debtnames = cursor.getString(cursor.getColumnIndex(myDBclass.DEBT_NAME));
        String debtamounts = cursor.getString(cursor.getColumnIndex(myDBclass.DEBT_AMOUNT));
        // Populate fields with extracted properties
        double dAmountDouble = Double.parseDouble(debtamounts);
        DecimalFormat df = new DecimalFormat("#.00");
        debtamounts = df.format(dAmountDouble);
        tvdebtname.setText(debtnames);
        tvdebtamount.setText(debtamounts +" ฿");
    }
}

