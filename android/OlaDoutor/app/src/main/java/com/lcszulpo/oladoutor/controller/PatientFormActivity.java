package com.lcszulpo.oladoutor.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;

public class PatientFormActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        initToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_form, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Patient patient =
                (Patient) getIntent().getSerializableExtra(PatientDetailActivity.FIELD_PATIENT);
        if(patient != null) {
            PatientFormFragment patientFormFragment =
                    (PatientFormFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_patient_form);
            patientFormFragment.setPatient(patient);
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.title_patient_form));
        this.setSupportActionBar(toolbar);
    }

}
