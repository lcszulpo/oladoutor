package com.lcszulpo.oladoutor.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;

public class PatientDetailFragment extends Fragment {

    public static final String FIELD_PATIENT = "PATIENT";

    private Patient patient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(FIELD_PATIENT)) {
            patient = (Patient) getArguments().getSerializable(FIELD_PATIENT);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return:
                getActivity().onBackPressed();
                break;
            case R.id.action_edit:
                Intent intentPatient = new Intent(getActivity(), PatientFormActivity.class);
                intentPatient.putExtra(FIELD_PATIENT, patient);
                startActivity(intentPatient);
                break;
            case R.id.action_new:
                Intent intentEncounter = new Intent(getActivity(), EncounterFormActivity.class);
                startActivity(intentEncounter);
                break;
            case R.id.action_delete:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patient_detail, container, false);

        setHasOptionsMenu(true);

        if (patient != null) {
            ((TextView) rootView.findViewById(R.id.patient_detail)).setText(patient.getName());
        }

        return rootView;
    }
}
