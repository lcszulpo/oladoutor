package com.lcszulpo.oladoutor.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.lcszulpo.oladoutor.R;

public class EncounterFormFragment extends Fragment {

    private EditText editTextPulseRate;
    private EditText editTextRespiratoryRate;
    private EditText editTextTemperature;
    private EditText editTextWeight;

    private EditText editTextVomit;
    private EditText editTextDiarrhea;
    private Spinner spinnerBleed;
    private Spinner spinnerWeakness;

    private Spinner spinnerConsciousness;
    private Spinner spinnerDiet;
    private Spinner spinnerHydration;
    private Spinner spinnerCondition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_encounter_form, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        findViewsById();
        initFields();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return:
                getActivity().onBackPressed();
                break;
            case R.id.action_save:
                initSaveRequest();
                break;
            case R.id.action_clear:
                clear();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViewsById() {

    }

    private void initFields() {

    }

    private void clear() {

    }

    private void initSaveRequest() {

    }

}
