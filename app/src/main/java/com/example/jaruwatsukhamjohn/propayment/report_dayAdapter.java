package com.example.jaruwatsukhamjohn.propayment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class report_dayAdapter extends CursorAdapter {

    public report_dayAdapter(Context context, Cursor cursor) {

        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.report_adapter, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView Balance = (TextView) view.findViewById(R.id.BalanceTV);
        TextView limit = (TextView) view.findViewById(R.id.limitTV);
        TextView numday = (TextView) view.findViewById(R.id.numDayTV);
        TextView day = (TextView) view.findViewById(R.id.dayTV);
        TextView month = (TextView) view.findViewById(R.id.MonthTV);
        TextView year = (TextView) view.findViewById(R.id.YearTV);

    }

}


