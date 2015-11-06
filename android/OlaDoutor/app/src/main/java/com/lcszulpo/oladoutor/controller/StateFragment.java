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

public class StateFragment extends Fragment {

    private static final String FIELD_ENCOUNTER = "ENCOUNTER";

    private Encounter encounter;

    private TextView textViewStateDate;
    private TextView textViewConsciousness;
    private TextView textViewMobility;
    private TextView textViewDiet;
    private TextView textViewHydration;
    private TextView textViewCondition;

    public static StateFragment newInstance(Encounter encounter) {
        StateFragment fragment = new StateFragment();
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
        return inflater.inflate(R.layout.fragment_state, container, false);
    }

    private void initFields() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        textViewStateDate.setText(format.format(encounter.getDate()));
        textViewConsciousness.setText(encounter.getConsciousness().toString());
        textViewMobility.setText(encounter.getMobility().toString());
        textViewDiet.setText(encounter.getDiet().toString());
        textViewHydration.setText(encounter.getHydration().toString());
        textViewCondition.setText(encounter.getCondition().toString());
    }

    private void findViewsById() {
        textViewStateDate = (TextView) getActivity().findViewById(R.id.textViewStateDate);
        textViewConsciousness = (TextView) getActivity().findViewById(R.id.textViewConsciousness);
        textViewMobility = (TextView) getActivity().findViewById(R.id.textViewMobility);
        textViewDiet = (TextView) getActivity().findViewById(R.id.textViewDiet);
        textViewHydration = (TextView) getActivity().findViewById(R.id.textViewHydration);
        textViewCondition = (TextView) getActivity().findViewById(R.id.textViewCondition);
    }

}
