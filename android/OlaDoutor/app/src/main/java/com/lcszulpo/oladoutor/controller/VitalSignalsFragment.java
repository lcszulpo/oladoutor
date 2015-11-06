package com.lcszulpo.oladoutor.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Encounter;

import java.text.SimpleDateFormat;

public class VitalSignalsFragment extends Fragment {

    private static final String FIELD_ENCOUNTER = "ENCOUNTER";

    private Encounter encounter;

    private TextView textViewVitalSignalsDate;
    private TextView textViewPulseRate;
    private TextView textViewRespiratoryRate;
    private TextView textViewTemperature;
    private TextView textViewWeight;

    public static VitalSignalsFragment newInstance(Encounter encounter) {
        VitalSignalsFragment fragment = new VitalSignalsFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIELD_ENCOUNTER, encounter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            encounter = (Encounter) getArguments().getSerializable(FIELD_ENCOUNTER);
        }

        findViewsById();
        initFields();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_signals, container, false);
    }

    private void findViewsById() {
        textViewVitalSignalsDate = (TextView) getActivity().findViewById(R.id.textViewVitalSignalsDate);
        textViewPulseRate = (TextView) getActivity().findViewById(R.id.textViewPulseRate);
        textViewRespiratoryRate = (TextView) getActivity().findViewById(R.id.textViewRespiratoryRate);
        textViewTemperature = (TextView) getActivity().findViewById(R.id.textViewTemperature);
        textViewWeight = (TextView) getActivity().findViewById(R.id.textViewWeight);
    }

    private void initFields() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        textViewVitalSignalsDate.setText(format.format(encounter.getDate()));
        textViewPulseRate.setText(encounter.getPulseRate() + " BPM");
        textViewRespiratoryRate.setText(encounter.getRespiratoryRate() + " MRPM");
        textViewTemperature.setText(String.valueOf(encounter.getTemperature()).replace(".", ",") + " ºC");
        textViewWeight.setText(String.valueOf(encounter.getWeight()).replace(".", ",") + " KG");
    }

}
