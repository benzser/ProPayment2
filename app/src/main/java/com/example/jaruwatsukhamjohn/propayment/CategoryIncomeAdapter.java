package com.example.jaruwatsukhamjohn.propayment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import DB.myDBclass;


public class CategoryIncomeAdapter extends CursorAdapter {

    ImageView cateicon;
    private TextView cate;

    public CategoryIncomeAdapter(Context context, Cursor cursor) {

        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.category_adapter, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        cate = (TextView) view.findViewById(R.id.category_catetv);
        cateicon = (ImageView) view.findViewById(R.id.category_cateicon);

        String sname = cursor.getString(cursor.getColumnIndex(myDBclass.INCATE_NAME));
        String sicon = cursor.getString(cursor.getColumnIndex(myDBclass.INCATE_ICON));
        String sstatus = cursor.getString(cursor.getColumnIndex(myDBclass.INCATE_STATUS));


        cate.setText(sname);
        checkimage(sicon);

    }

    public void checkimage(String image){
        switch (image) {
            case "award":
                cateicon.setBackgroundResource(R.drawable.ic_award);
                break;
            case "basketball":
                cateicon.setBackgroundResource(R.drawable.ic_basketball);
                break;
            case "book":
                cateicon.setBackgroundResource(R.drawable.ic_book);
                break;
            case "box":
                cateicon.setBackgroundResource(R.drawable.ic_box);;
                break;
            case "cake":
                cateicon.setBackgroundResource(R.drawable.ic_cake);
                break;
            case "coffee":
                cateicon.setBackgroundResource(R.drawable.ic_coffee);
                break;
            case "debt":
                cateicon.setBackgroundResource(R.drawable.ic_debt);
                break;
            case "diamond":
                cateicon.setBackgroundResource(R.drawable.ic_diamond);
                break;
            case "dice":
                cateicon.setBackgroundResource(R.drawable.ic_dice);
                break;
            case "divide":
                cateicon.setBackgroundResource(R.drawable.ic_divide);
                break;
            case "drink":
                cateicon.setBackgroundResource(R.drawable.ic_drink);
                break;
            case "education":
                cateicon.setBackgroundResource(R.drawable.ic_education);
                break;
            case "entertainment":
                cateicon.setBackgroundResource(R.drawable.ic_entertainment);
                break;
            case "family":
                cateicon.setBackgroundResource(R.drawable.ic_family);
                break;
            case "food":
                cateicon.setBackgroundResource(R.drawable.ic_food);
                break;
            case "football":
                cateicon.setBackgroundResource(R.drawable.ic_football);
                break;
            case "give":
                cateicon.setBackgroundResource(R.drawable.ic_give);
                break;
            case "icecream":
                cateicon.setBackgroundResource(R.drawable.ic_icecream);
                break;
            case "invest":
                cateicon.setBackgroundResource(R.drawable.ic_invest);
                break;
            case "loan":
                cateicon.setBackgroundResource(R.drawable.ic_loan);
                break;
            case "music":
                cateicon.setBackgroundResource(R.drawable.ic_music);
                break;
            case "phone":
                cateicon.setBackgroundResource(R.drawable.ic_phone);
                break;
            case "picture":
                cateicon.setBackgroundResource(R.drawable.ic_picture);
                break;
            case "salary":
                cateicon.setBackgroundResource(R.drawable.ic_salary);
                break;
            case "selling":
                cateicon.setBackgroundResource(R.drawable.ic_selling);
                break;
            case "shopping":
                cateicon.setBackgroundResource(R.drawable.ic_shopping);
                break;
            case "transport":
                cateicon.setBackgroundResource(R.drawable.ic_transport);
                break;
            case "travel":
                cateicon.setBackgroundResource(R.drawable.ic_travel);
                break;
            case "tv":
                cateicon.setBackgroundResource(R.drawable.ic_tv);
            default:
                cateicon.setBackgroundResource(R.drawable.ic_launcher);
                break;
        }
    }
}