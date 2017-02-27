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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nensee on 2/16/17.
 */
public class CustomAdapterG extends BaseAdapter {

    Context context;
    ArrayList<Post> posts;
    LayoutInflater layoutInflater;



    CustomAdapterG(Context context, ArrayList<Post> posts)
    {
        this.context=context;
        this.posts=posts;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    static class ViewHolder
    {
        TextView uid;
        TextView desc;
    }

    private int[] tagCollection;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if(convertView==null) {
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = LayoutInflater.from(context).inflate(R.layout.gridview, parent, false);
            holder.uid = (TextView) convertView.findViewById(R.id.txtviw1);

            holder.desc = (TextView) convertView.findViewById(R.id.txtviw2);

            convertView.setTag(holder);



        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.desc.setText(posts.get(position).getDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SecondPage s=new SecondPage();

                int gid=posts.get(position).getId();
                String sa=posts.get(position).getDescription();

                Bundle args = new Bundle();
                args.putInt("key",gid);
                args.putString("desc",sa);
                s.setArguments(args);

                MainActivity activityGrid = (MainActivity) context;
                FragmentManager fm = activityGrid.getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                holder.uid.setText("");

                ft.replace(R.id.rltv,s);
                ft.addToBackStack("");
                ft.commit();

            }
        });

        return convertView;
    }

}
