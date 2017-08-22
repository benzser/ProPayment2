package com.example.jaruwatsukhamjohn.propayment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import DB.myDBclass;

/**
 * Created by Ubu on 3/31/2015.
 */
public class ReportAdapter extends CursorAdapter {

    String categoryname;
    ImageView imagecategory;

    public ReportAdapter(Context context, Cursor cursor) {

        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.report_adapter, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView transamount = (TextView) view.findViewById(R.id.report_amounttv);
        TextView transcategory = (TextView) view.findViewById(R.id.report_catetv);
        TextView transdescription = (TextView) view.findViewById(R.id.report_destv);
        imagecategory = (ImageView) view.findViewById(R.id.report_cateicon);
        // Extract properties from cursor
        String sdescription = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_DESCRIPTION));
        String scategory = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_CATE));
        String samount = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_AMOUNT.toString()));
        String scateicon = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_CATEICON));

        //
        double damount = Double.parseDouble(samount);
        DecimalFormat df = new DecimalFormat("#,###,###.00");
        samount = df.format(damount);

        //type
        String trantype = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_TYPE));


        categoryname = cursor.getString(cursor.getColumnIndex(myDBclass.TRAN_CATE));
        if (trantype.equalsIgnoreCase("true")) {
            transamount.setTextColor(Color.parseColor("#3366ff"));
            transamount.setText("+"+samount+ " ฿");
        } else {
            transamount.setTextColor(Color.parseColor("#FFFF0000"));
            transamount.setText("-"+samount+ " ฿");
        }


        // Populate fields with extracted properties
        transcategory.setText(scategory);
        transdescription.setText(sdescription);
        checkimage(scateicon);

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

}
