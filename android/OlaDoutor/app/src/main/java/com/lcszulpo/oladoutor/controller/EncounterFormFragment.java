package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Encounter;
import com.lcszulpo.oladoutor.model.Patient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;

public class EncounterFormFragment extends Fragment {

    private EditText editTextPulseRate;
    private EditText editTextRespiratoryRate;
    private EditText editTextTemperature;
    private EditText editTextWeight;

    private EditText editTextVomit;
    private EditText editTextDiarrhea;
    private Spinner spinnerPain;
    private Spinner spinnerPainDetail;
    private CheckBox checkBoxBleed;
    private Spinner spinnerBleed;
    private CheckBox checkBoxWeakness;
    private Spinner spinnerWeakness;
    private EditText editTextOtherSymptoms;

    private Spinner spinnerConsciousness;
    private Spinner spinnerMobility;
    private Spinner spinnerDiet;
    private Spinner spinnerHydration;
    private Spinner spinnerCondition;
    private Integer idPatient;
    private Patient patient;

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
            case android.R.id.home:
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
        editTextPulseRate = (EditText) getActivity().findViewById(R.id.editTextPulseRate);
        editTextRespiratoryRate = (EditText) getActivity().findViewById(R.id.editTextRespiratoryRate);
        editTextTemperature = (EditText) getActivity().findViewById(R.id.editTextTemperature);
        editTextWeight = (EditText) getActivity().findViewById(R.id.editTextWeight);

        editTextVomit = (EditText) getActivity().findViewById(R.id.editTextVomit);
        editTextDiarrhea = (EditText) getActivity().findViewById(R.id.editTextDiarrhea);
        spinnerPain = (Spinner) getActivity().findViewById(R.id.spinnerPain);
        spinnerPainDetail = (Spinner) getActivity().findViewById(R.id.spinnerPainDetail);
        checkBoxBleed = (CheckBox) getActivity().findViewById(R.id.checkBoxBleed);
        spinnerBleed = (Spinner) getActivity().findViewById(R.id.spinnerBleed);
        checkBoxWeakness = (CheckBox) getActivity().findViewById(R.id.checkBoxWeakness);
        spinnerWeakness = (Spinner) getActivity().findViewById(R.id.spinnerWeakness);
        editTextOtherSymptoms = (EditText) getActivity().findViewById(R.id.editTextOtherSymptoms);

        spinnerConsciousness = (Spinner) getActivity().findViewById(R.id.spinnerConsciousness);
        spinnerMobility = (Spinner) getActivity().findViewById(R.id.spinnerMobility);
        spinnerDiet = (Spinner) getActivity().findViewById(R.id.spinnerDiet);
        spinnerHydration = (Spinner) getActivity().findViewById(R.id.spinnerHydration);
        spinnerCondition = (Spinner) getActivity().findViewById(R.id.spinnerCondition);
    }

    private void initFields() {
        ArrayAdapter<Encounter.Pain> painAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Pain.values()));
        spinnerPain.setAdapter(painAdapter);

        ArrayAdapter<Encounter.PainDetail> painDetailAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.PainDetail.values()));
        spinnerPainDetail.setAdapter(painDetailAdapter);

        ArrayAdapter<Encounter.BleedingDetail> bleedAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.BleedingDetail.values()));
        spinnerBleed.setAdapter(bleedAdapter);

        ArrayAdapter<Encounter.WeaknessDetail> weaknessAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.WeaknessDetail.values()));
        spinnerWeakness.setAdapter(weaknessAdapter);

        ArrayAdapter<Encounter.Consciousness> consciousnessAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Consciousness.values()));
        spinnerConsciousness.setAdapter(consciousnessAdapter);

        ArrayAdapter<Encounter.Mobility> mobilityAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Mobility.values()));
        spinnerMobility.setAdapter(mobilityAdapter);

        ArrayAdapter<Encounter.Diet> dietAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Diet.values()));
        spinnerDiet.setAdapter(dietAdapter);

        ArrayAdapter<Encounter.Hydration> hydrationAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Hydration.values()));
        spinnerHydration.setAdapter(hydrationAdapter);

        ArrayAdapter<Encounter.Condition> conditionAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, Arrays.asList(Encounter.Condition.values()));
        spinnerCondition.setAdapter(conditionAdapter);
    }

    private void clear() {
        editTextPulseRate.setText("");
        editTextRespiratoryRate.setText("");
        editTextTemperature.setText("");
        editTextWeight.setText("");

        editTextVomit.setText("");
        editTextDiarrhea.setText("");
        spinnerPain.setSelection(0);
        spinnerPainDetail.setSelection(0);
        checkBoxBleed.setChecked(false);
        spinnerBleed.setSelection(0);
        checkBoxWeakness.setChecked(false);
        spinnerWeakness.setSelection(0);
        editTextOtherSymptoms.setText("");

        spinnerConsciousness.setSelection(0);
        spinnerMobility.setSelection(0);
        spinnerDiet.setSelection(0);
        spinnerHydration.setSelection(0);
        spinnerCondition.setSelection(0);

        editTextPulseRate.setError(null);
        editTextRespiratoryRate.setError(null);
        editTextTemperature.setError(null);
        editTextWeight.setError(null);
        editTextVomit.setError(null);
        editTextDiarrhea.setError(null);
    }

    private void initSaveRequest() {
        if(!validateRequiriedFields()) {
            return;
        }

        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_encounters_save);

        Encounter encounter = new Encounter();

        encounter.setDate(new Date());
        encounter.setPatient(patient);

        encounter.setPulseRate(Integer.valueOf(editTextPulseRate.getText().toString()));
        encounter.setRespiratoryRate(Integer.valueOf(editTextRespiratoryRate.getText().toString()));
        Double temperature = Double.valueOf(editTextTemperature.getText().toString().replace(",", "."));
        encounter.setTemperature(temperature);
        Double weight = Double.valueOf(editTextWeight.getText().toString().replace(",", "."));
        encounter.setWeight(weight);

        encounter.setVomit(Integer.valueOf(editTextVomit.getText().toString()));
        encounter.setDiarrhea(Integer.valueOf(editTextDiarrhea.getText().toString()));
        encounter.setPain((Encounter.Pain) spinnerPain.getSelectedItem());
        encounter.setPainDetail((Encounter.PainDetail) spinnerPainDetail.getSelectedItem());
        encounter.setHasBleeding(checkBoxBleed.isChecked());
        encounter.setBleedingDetail((Encounter.BleedingDetail) spinnerBleed.getSelectedItem());
        encounter.setHasWeakness(checkBoxWeakness.isChecked());
        encounter.setWeaknessDetail((Encounter.WeaknessDetail) spinnerWeakness.getSelectedItem());
        encounter.setOthersSymptoms(editTextOtherSymptoms.getText().toString());

        encounter.setConsciousness((Encounter.Consciousness) spinnerConsciousness.getSelectedItem());
        encounter.setMobility((Encounter.Mobility) spinnerMobility.getSelectedItem());
        encounter.setDiet((Encounter.Diet) spinnerDiet.getSelectedItem());
        encounter.setHydration((Encounter.Hydration) spinnerHydration.getSelectedItem());
        encounter.setCondition((Encounter.Condition) spinnerCondition.getSelectedItem());

        JSONObject jsonObject = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonObject = new JSONObject(objectMapper.writeValueAsString(encounter));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getActivity().onBackPressed();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao salvar o encontro");
                builder.setMessage("Nao foi possivel salvar o encontro.");
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    private Boolean validateRequiriedFields() {
        Boolean retorno = true;

        if (editTextPulseRate.getText().toString().isEmpty()) {
            editTextPulseRate.setError("Este campo deve ser informado");
            retorno = false;
        }

        if (editTextRespiratoryRate.getText().toString().isEmpty()) {
            editTextRespiratoryRate.setError("Este campo deve ser informado");
            retorno = false;
        }

        if (editTextTemperature.getText().toString().isEmpty()) {
            editTextTemperature.setError("Este campo deve ser informado");
            retorno = false;
        }

        if (editTextWeight.getText().toString().isEmpty()) {
            editTextWeight.setError("Este campo deve ser informado");
            retorno = false;
        }

        if (editTextVomit.getText().toString().isEmpty()) {
            editTextVomit.setError("Este campo deve ser informado");
            retorno = false;
        }

        if (editTextDiarrhea.getText().toString().isEmpty()) {
            editTextDiarrhea.setError("Este campo deve ser informado");
            retorno = false;
        }

        if(checkBoxBleed.isChecked()) {
            if(spinnerBleed.getSelectedItem() == null) {
                ((TextView) spinnerBleed.getSelectedView()).setError("Este campo deve ser informado");
                retorno = false;
            }
        }

        if(checkBoxWeakness.isChecked()) {
            if(spinnerWeakness.getSelectedItem() == null) {
                ((TextView) spinnerBleed.getSelectedView()).setError("Este campo deve ser informado");
                retorno = false;
            }
        }

        if(patient == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Erro ao salvar o encontro");
            builder.setMessage("Um paciente deve ser selecionado.");
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    getActivity().finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            retorno = false;
        }

        return retorno;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
