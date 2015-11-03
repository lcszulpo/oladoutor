package com.lcszulpo.oladoutor.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;

public class PatientListActivity extends AppCompatActivity
        implements PatientListFragment.Callbacks {

    private Toolbar toolbar;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        if (findViewById(R.id.patient_detail_container) != null) {
            mTwoPane = true;

            ((PatientListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.patient_list))
                    .setActivateOnItemClick(true);
        }

        initToolBar();
    }

    @Override
    public void onItemSelected(Patient patient) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(PatientDetailFragment.FIELD_PATIENT, patient);
            PatientDetailFragment fragment = new PatientDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.patient_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, PatientDetailActivity.class);
            detailIntent.putExtra(PatientDetailFragment.FIELD_PATIENT, patient);
            startActivity(detailIntent);
        }
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
