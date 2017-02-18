package com.fragment.login.shayrifrag;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nensee on 2/16/17.
 */
public class SecondPage extends Fragment {

    ListView listView;
    Quotes q;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.second_page, container, false);

        final String  scnd = getArguments().getString("key");

        //Intent ii=getIntent();
    //int gotid = ii.getIntExtra("pos", 1);

    new MySecondClass().execute("http://rapidans.esy.es/test/getquotes.php?cat_id="+scnd);

    TextView textView;
    textView=(TextView)findViewById(R.id.txtvw7);


    //  Intent intent=getIntent();
    //   nme=intent.getStringExtra("name");
        final String  nme = getArguments().getString("desc");
        textView.setText(nme);

        return view;
}

class MySecondClass extends AsyncTask<String,Void,String> {

    private ProgressDialog dialog;
    ArrayList<Quotes> quotesArrayList = new ArrayList<>();
    CustomAdapterL adapterL;

    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection;
        try {
            URL url = new URL(params[0]);
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String bufferString = buffer.toString();
                return bufferString;


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        quotesArrayList = new ArrayList<>();

        try {

            JSONObject jsonObject1 = new JSONObject(s);

            JSONArray jsonArray = jsonObject1.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                q = new Quotes();
                q.setId(jsonObject.getInt("id"));
                q.setCat_id(jsonObject.getInt("cat_id"));
                q.setQuotes(jsonObject.getString("quotes"));

                quotesArrayList.add(q);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapterL = new CustomAdapterL(context,quotesArrayList);

        listView = (ListView) findViewById(R.id.listview1);

        listView.setAdapter(adapterL);

        String passedVar=null;



    }
}
}
