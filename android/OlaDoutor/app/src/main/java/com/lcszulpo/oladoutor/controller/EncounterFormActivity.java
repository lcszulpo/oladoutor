package com.lcszulpo.oladoutor.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;

public class EncounterFormActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter_form);

        initToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_encounter_form, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Patient patient =
                (Patient) getIntent().getSerializableExtra(PatientDetailActivity.FIELD_PATIENT);
        if(patient != null) {
            EncounterFormFragment encounterFormFragment =
                    (EncounterFormFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentEncounterForm);
            encounterFormFragment.setPatient(patient);
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.title_encounter_form));
        this.setSupportActionBar(toolbar);
    }

}
