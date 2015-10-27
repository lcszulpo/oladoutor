package com.lcszulpo.oladoutor.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lcszulpo.oladoutor.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, PatientListActivity.class);
        startActivity(i);
        finish();
    }

}
