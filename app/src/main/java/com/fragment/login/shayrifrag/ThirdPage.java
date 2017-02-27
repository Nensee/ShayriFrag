package com.fragment.login.shayrifrag;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nensee on 2/16/17.
 */
public class ThirdPage extends Fragment
{
    String scnd;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_page, container, false);

        TextView textView;
        textView = (TextView) view.findViewById(R.id.txtvw6);

        scnd = getArguments().getString("pos");

        textView.setText(scnd);


        Button btn;
        btn = (Button) view.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                scnd = getArguments().getString("pos");
                shareIntent.getStringExtra("pos");
                shareIntent.putExtra(Intent.EXTRA_TEXT,scnd);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));

            }

        });
        return view;
    }
}
