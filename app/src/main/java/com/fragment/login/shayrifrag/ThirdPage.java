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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_page, container, false);


        TextView textView;

        textView = (TextView) findViewById(R.id.txtvw6);

        final String quotes;

        final String scnd = getArguments().getString("pos");


        textView.setText(quotes);


        Button btn;
        btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST123", "okeyeeyeyeyey");
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(Intent.EXTRA_TEXT, quotes);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "QUOTES DONE");
                startActivity(sharingIntent);

            }

        });
        return view;
    }
}
