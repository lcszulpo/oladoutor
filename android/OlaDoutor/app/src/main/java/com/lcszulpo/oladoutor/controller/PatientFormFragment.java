package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Locale;
import com.lcszulpo.oladoutor.model.Patient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PatientFormFragment extends Fragment {

    private List<Locale> locales;

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private ToggleButton toggleButtonAgeType;
    private Switch switchSex;
    private Spinner spinnerLocale;
    private Patient patient;
    private ToggleButton toggleButtonSituacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patient_form, container, false);

        setHasOptionsMenu(true);

        return rootView ;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        findViewsById();
        initListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                initSaveRequest();
                break;
            case R.id.action_return:
                getActivity().onBackPressed();
                break;
            case R.id.action_clear:
                clear();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        initLocaleListRequest();
    }

    private void initListeners() {
        switchSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchSex.setText("Feminino");
                } else {
                    switchSex.setText("Masculino");
                }
            }
        });
    }

    private void findViewsById() {
        editTextName = (EditText) getActivity().findViewById(R.id.editTextName);
        editTextLastName = (EditText) getActivity().findViewById(R.id.editTextLastName);
        editTextAge = (EditText) getActivity().findViewById(R.id.editTextAge);
        toggleButtonAgeType = (ToggleButton) getActivity().findViewById(R.id.toggleButtonAgeType);
        switchSex = (Switch) getActivity().findViewById(R.id.switchSex);
        spinnerLocale = (Spinner) getActivity().findViewById(R.id.spinnerLocale);
        toggleButtonSituacao = (ToggleButton) getActivity().findViewById(R.id.toggleButtonSituacao);
    }

    private void initLocaleListRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_locales_list_actives);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            locales = Arrays.asList(mapper.readValue(response.toString(), Locale[].class));
                            ArrayAdapter<Locale> adapter = new ArrayAdapter<Locale>(getActivity(),
                                    android.R.layout.simple_spinner_item, locales);
                            spinnerLocale.setAdapter(adapter);

                            if(patient != null) {
                                editTextName.setText(patient.getName());
                                editTextLastName.setText(patient.getLastName());
                                editTextAge.setText(patient.getAge());
                                toggleButtonAgeType.setChecked(patient.getAgeType() == Patient.AgeType.YEARS ? true : false);
                                switchSex.setChecked(patient.getSex() == Patient.Sex.F ? true : false);
                                spinnerLocale.setSelection(getLocale(patient.getLocale()));
                                toggleButtonSituacao.setChecked(patient.getStatus() == Patient.Status.ACTIVE ? true : false);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao consultar localizações");
                builder.setMessage("Nao foi possivel consultar as localizações do servidor");
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

    private void initSaveRequest() {
        if(!validateRequiriedFields()) {
            return;
        }

        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_patients_save);

        patient.setName(editTextName.getText().toString());
        patient.setLastName(editTextLastName.getText().toString());
        patient.setAge(editTextAge.getText().toString());
        if(toggleButtonAgeType.isChecked()) {
            patient.setAgeType(Patient.AgeType.YEARS);
        } else {
            patient.setAgeType(Patient.AgeType.MONTHS);
        }
        if(switchSex.isChecked()) {
            patient.setSex(Patient.Sex.F);
        } else {
            patient.setSex(Patient.Sex.M);
        }
        patient.setStatus(Patient.Status.ACTIVE);
        patient.setLocale((Locale) spinnerLocale.getSelectedItem());
        if(toggleButtonSituacao.isChecked()) {
            patient.setStatus(Patient.Status.ACTIVE);
        } else {
            patient.setStatus(Patient.Status.INACTIVE);
        }

        JSONObject jsonObject = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonObject = new JSONObject(objectMapper.writeValueAsString(patient));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao salvar o paciente");
                builder.setMessage("Nao foi possivel salvar o paciente.");
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

        if(editTextName.getText().toString().isEmpty()) {
            editTextName.setError("O nome deve ser informado");
            retorno = false;
        }

        if(editTextLastName.getText().toString().isEmpty()) {
            editTextLastName.setError("O sobrenome deve ser informado");
            retorno = false;
        }

        if(editTextAge.getText().toString().isEmpty()) {
            editTextAge.setError("A idade do paciente deve ser informada");
            retorno = false;
        }

        if(spinnerLocale.getSelectedItem() == null) {
            ((TextView) spinnerLocale.getSelectedView()).setError("A localização do paciente deve ser informada");
            retorno = false;
        }

        return retorno;
    }

    private void clear() {
        patient = null;

        editTextName.getText().clear();
        editTextLastName.getText().clear();
        editTextAge.getText().clear();
        toggleButtonAgeType.setChecked(false);
        switchSex.setChecked(false);
        spinnerLocale.setSelection(0);
        toggleButtonSituacao.setChecked(true);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Integer getLocale(Locale locale) {
        Integer position = -1;
        for (Locale localeFromList : locales) {
            if(locale.getId().equals(localeFromList.getId())) {
                position++;
            }
        }
        return position;
    }

}
