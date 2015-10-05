package com.lcszulpo.oladoutor.controller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcszulpo.oladoutor.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PatientFormActivityFragment extends Fragment {

    public PatientFormActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_form, container, false);
    }
}
