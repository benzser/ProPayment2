package com.example.jaruwatsukhamjohn.propayment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapterSp extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mData;
    private LayoutInflater mInflater;


    public customAdapterSp(Context context, ArrayList<String> data) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.spinner_item_adapter, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.logo = (ImageView) convertView.findViewById(R.id.logo);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int imgResourceId;
        String temp;
        switch (position) {
            case 0:
               // imgResourceId = R.drawable.ic_drink;
                holder.name.setText(R.string.drink);
                break;
            case 1:
                imgResourceId = R.drawable.ic_food;
                holder.name.setText(R.string.food);
                break;
            case 2:
                imgResourceId = R.drawable.ic_entertainment;
                holder.name.setText(R.string.game);
                break;
            case 3:
             //   imgResourceId = R.drawable.ic_gift;
                holder.name.setText(R.string.gift);
                break;
            default:
                imgResourceId = R.drawable.ic_travel;
                holder.name.setText(R.string.travel);
                break;
        }


       // holder.logo.setImageResource(imgResourceId);

        convertView.setTag(holder);

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        ImageView logo;
    }
}
