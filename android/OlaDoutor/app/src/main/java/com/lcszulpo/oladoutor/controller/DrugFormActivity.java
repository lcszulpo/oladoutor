package com.lcszulpo.oladoutor.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Drug;

public class DrugFormActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_form);

        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Drug drug =
                (Drug) getIntent().getSerializableExtra(DrugListFragment.FIELD_DRUG);
        if(drug != null) {
            DrugFormFragment drugFormFragment = (DrugFormFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drug_form);
            drugFormFragment.setDrug(drug);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drug_form, menu);

        return true;
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle("Medicamento");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
