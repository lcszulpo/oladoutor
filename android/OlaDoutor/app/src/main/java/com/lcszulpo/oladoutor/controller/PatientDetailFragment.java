package com.lcszulpo.oladoutor.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Encounter;

/**
 * Created by lcszulpo on 11/11/15.
 */
public class PatientDetailFragment extends Fragment {

    private TextView textViewPulseRate;
    private TextView textViewRespiratoryRate;
    private TextView textViewTemperature;
    private TextView textViewWeight;

    private TextView textViewVomit;
    private TextView textViewDiarrhea;
    private TextView textViewPain;
    private TextView textViewBleed;
    private TextView textViewWeakness;

    private TextView textViewConsciousness;
    private TextView textViewMobility;
    private TextView textViewDiet;
    private TextView textViewHydration;
    private TextView textViewCondition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViewsById();
    }

    public void fillEncounter(Encounter encounter) {
        textViewPulseRate.setText(encounter.getPulseRate() + " BPM");
        textViewRespiratoryRate.setText(encounter.getRespiratoryRate() + " MRPM");
        textViewTemperature.setText(String.valueOf(encounter.getTemperature()).replace(".", ",") + " ºC");
        textViewWeight.setText(String.valueOf(encounter.getWeight()).replace(".", ",") + " KG");

        textViewVomit.setText(String.valueOf(encounter.getVomit()) + " x em 24H");
        textViewDiarrhea.setText(String.valueOf(encounter.getDiarrhea()) + " x em 24H");
        textViewPain.setText(encounter.getPain().toString());
        textViewBleed.setText(encounter.getBleedingDetail().toString());
        textViewWeakness.setText(encounter.getWeaknessDetail().toString());

        textViewConsciousness.setText(encounter.getConsciousness().toString());
        textViewMobility.setText(encounter.getMobility().toString());
        textViewDiet.setText(encounter.getDiet().toString());
        textViewHydration.setText(encounter.getHydration().toString());
        textViewCondition.setText(encounter.getCondition().toString());
    }

    private void findViewsById() {
        textViewPulseRate = (TextView) getActivity().findViewById(R.id.textViewPulseRate);
        textViewRespiratoryRate = (TextView) getActivity().findViewById(R.id.textViewRespiratoryRate);
        textViewTemperature = (TextView) getActivity().findViewById(R.id.textViewTemperature);
        textViewWeight = (TextView) getActivity().findViewById(R.id.textViewWeight);

        textViewVomit = (TextView) getActivity().findViewById(R.id.textViewVomit);
        textViewDiarrhea = (TextView) getActivity().findViewById(R.id.textViewDiarrhea);
        textViewPain = (TextView) getActivity().findViewById(R.id.textViewPain);
        textViewBleed = (TextView) getActivity().findViewById(R.id.textViewBleed);
        textViewWeakness = (TextView) getActivity().findViewById(R.id.textViewWeakness);

        textViewConsciousness = (TextView) getActivity().findViewById(R.id.textViewConsciousness);
        textViewMobility = (TextView) getActivity().findViewById(R.id.textViewMobility);
        textViewDiet = (TextView) getActivity().findViewById(R.id.textViewDiet);
        textViewHydration = (TextView) getActivity().findViewById(R.id.textViewHydration);
        textViewCondition = (TextView) getActivity().findViewById(R.id.textViewCondition);
    }

}
