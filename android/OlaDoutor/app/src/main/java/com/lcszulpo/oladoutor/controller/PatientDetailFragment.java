package com.lcszulpo.oladoutor.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Encounter;

/**
 * Created by lcszulpo on 11/11/15.
 */
public class PatientDetailFragment extends Fragment {

    private static final String FIELD_ENCOUNTER = "ENCOUNTER";

    public static PatientDetailFragment newInstance(Encounter encounter) {
        PatientDetailFragment fragment = new PatientDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIELD_ENCOUNTER, encounter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_detail, container, false);
    }

}
