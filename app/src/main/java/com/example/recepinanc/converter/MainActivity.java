package com.example.recepinanc.converter;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity implements ConvertFragment.Communicator{

    FragmentManager fm;
    ConvertFragment convertFragment;
    ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            convertFragment = new ConvertFragment();
            fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.container,convertFragment);
            transaction.commit();
        }
    }

    @Override
    public void passData(String inputText) {
        resultFragment = ResultFragment.newInstance(inputText);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container,resultFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (resultFragment != null) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.container,convertFragment);
            transaction.commit();
//            transaction.hide(resultFragment);
            resultFragment = null;
        }
    }
}
