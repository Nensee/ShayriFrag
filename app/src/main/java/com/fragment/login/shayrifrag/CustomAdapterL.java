package com.fragment.login.shayrifrag;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nensee on 2/16/17.
 */
public class CustomAdapterL extends BaseAdapter {

    Context context;
    ArrayList<Quotes> quots;
    LayoutInflater layoutInflater;
    ListView listView;
    CustomAdapterL(Context context, ArrayList<Quotes> quots)
    {
        this.context=context;
        this.quots=quots;
    }

    @Override
    public int getCount() {
        return quots.size();
    }

    @Override
    public Object getItem(int position) {
        return quots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    static class ViewHolder
    {
        TextView id;
        TextView cat_id;
        TextView quotes;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;


        if(convertView==null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
            holder.id = (TextView) convertView.findViewById(R.id.textvw3);
            holder.cat_id = (TextView) convertView.findViewById(R.id.textvw4);
            holder.quotes=(TextView)convertView.findViewById(R.id.textvw5);

            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.quotes.setText(quots.get(position).getQuotes());


        holder.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThirdPage t=new ThirdPage();

                String sa=quots.get(position).getQuotes();

                FragmentManager fm = getFragmentManager();

                Bundle args = new Bundle();
                FragmentTransaction ft = fm.beginTransaction();
                args.putString("pos", sa);

                t.setArguments(args);

                ft.replace(R.id.rltv,t);
                ft.addToBackStack("");
                ft.commit();

            }
        });

        return convertView;
    }
}
