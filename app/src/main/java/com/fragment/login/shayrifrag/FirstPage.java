package com.fragment.login.shayrifrag;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
import android.support.v4.app.FragmentManager;
/**
 * Created by nensee on 2/16/17.
 */
public class FirstPage extends Fragment
{
    GridView gridView;
    URL url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.first_page, container, false);

        //GridView gridView = (GridView) gridView.findViewById();

        new MyFirstClass().execute("http://rapidans.esy.es/test/getallcat.php");

        return view;
    }



    class MyFirstClass extends AsyncTask<String, Void, String> {

            ProgressDialog dialog;
            ArrayList<Post> postArrayList = new ArrayList<>();
            CustomAdapterG adapterG;

            Context context;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialogFragment.show(FirstPage,"Fp");
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                HttpURLConnection connection;
                try {
                    url = new URL(params[0]);
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
                postArrayList = new ArrayList<>();

                try {

                    JSONObject jsonObject1 = new JSONObject(s);


                    JSONArray jsonArray = jsonObject1.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        Post p = new Post();
                        p.setId(jsonObject.getInt("id"));

                        p.setDescription(jsonObject.getString("name"));

                        postArrayList.add(p);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapterG = new CustomAdapterG(context,postArrayList);

                gridView.setAdapter(adapterG);
            }

        }
    }
