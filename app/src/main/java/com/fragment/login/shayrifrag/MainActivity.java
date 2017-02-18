package com.fragment.login.shayrifrag;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton() {
        FirstPage fp = new FirstPage();
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft = fm1.beginTransaction();
        ft.add(R.id.rltv, fp);
        ft.commit();
    }
}
