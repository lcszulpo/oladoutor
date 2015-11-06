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

public class SymptomsFragment extends Fragment {

    private static final String FIELD_ENCOUNTER = "ENCOUNTER";

    private Encounter encounter;

    private TextView textViewSymptomsDate;
    private TextView textViewVomit;
    private TextView textViewDiarrhea;
    private TextView textViewPain;
    private TextView textViewBleed;
    private TextView textViewWeakness;

    public static SymptomsFragment newInstance(Encounter encounter) {
        SymptomsFragment fragment = new SymptomsFragment();
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
        return inflater.inflate(R.layout.fragment_symptoms, container, false);
    }

    private void findViewsById() {
        textViewSymptomsDate = (TextView) getActivity().findViewById(R.id.textViewSymptomsDate);
        textViewVomit = (TextView) getActivity().findViewById(R.id.textViewVomit);
        textViewDiarrhea = (TextView) getActivity().findViewById(R.id.textViewDiarrhea);
        textViewPain = (TextView) getActivity().findViewById(R.id.textViewPain);
        textViewBleed = (TextView) getActivity().findViewById(R.id.textViewBleed);
        textViewWeakness = (TextView) getActivity().findViewById(R.id.textViewWeakness);
    }

    private void initFields() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        textViewSymptomsDate.setText(format.format(encounter.getDate()));
        textViewVomit.setText(String.valueOf(encounter.getVomit()) + " x em 24H");
        textViewDiarrhea.setText(String.valueOf(encounter.getDiarrhea()) + " x em 24H");
        textViewPain.setText(encounter.getPain().toString());
        textViewBleed.setText(encounter.getBleedingDetail().toString());
        textViewWeakness.setText(encounter.getWeaknessDetail().toString());
    }

}
