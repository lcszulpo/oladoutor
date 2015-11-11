package com.lcszulpo.oladoutor.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;

public class PatientListActivity extends AppCompatActivity
        implements PatientListFragment.Callbacks {

    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        initToolBar();
        findViewsById();
        addListeners();
    }

    private void addListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPatient = new Intent(PatientListActivity.this, PatientFormActivity.class);
                startActivity(intentPatient);
            }
        });
    }

    private void findViewsById() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onItemSelected(Patient patient) {
        Intent detailIntent = new Intent(this, PatientDetailActivity.class);
        detailIntent.putExtra(PatientDetailActivity.FIELD_PATIENT, patient);
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_list, menu);

        return true;
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.title_patient_list));
        this.setSupportActionBar(toolbar);
    }

}
