package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.lcszulpo.oladoutor.model.Drug;
import com.lcszulpo.oladoutor.model.Patient;
import com.lcszulpo.oladoutor.model.PatientDrug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddDrugFragment extends Fragment {

    private Spinner spinnerMedicamento;
    private EditText editTextIntervalo;
    private ToggleButton toggleButtonTipoIntervalo;
    private FloatingActionButton fab;
    private CoordinatorLayout cordinatorLayout;
    private List<Drug> drugs;

    private Patient patient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_drug, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        findViewsById();
        initFields();
        initListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        patient = (Patient) getActivity().getIntent().getSerializableExtra(PatientDetailActivity.FIELD_PATIENT);
    }

    private void initListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSaveRequest();
            }
        });
    }

    private void initFields() {
        toggleButtonTipoIntervalo.setChecked(true);
        initDrugsRequest();
    }

    private void findViewsById() {
        spinnerMedicamento = (Spinner) getActivity().findViewById(R.id.spinnerMedicamento);
        editTextIntervalo = (EditText) getActivity().findViewById(R.id.editTextIntervalo);
        toggleButtonTipoIntervalo = (ToggleButton) getActivity().findViewById(R.id.toggleButtonTipoIntervalo);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        cordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.cordinatorLayout);
    }

    private void initDrugsRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_drugs_list_actives);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            drugs = Arrays.asList(mapper.readValue(response.toString(), Drug[].class));
                            ArrayAdapter<Drug> adapter = new ArrayAdapter<Drug>(getActivity(),
                                    android.R.layout.simple_spinner_item, drugs);
                            spinnerMedicamento.setAdapter(adapter);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao consultar medicamentos");
                builder.setMessage("Nao foi possivel consultar os medicamentos do servidor");
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
                        getString(R.string.res_patient_drugs_save);

        PatientDrug patientDrug = new PatientDrug();
        patientDrug.setDrug((Drug) spinnerMedicamento.getSelectedItem());
        patientDrug.setPatient(patient);
        patientDrug.setInterval(Integer.valueOf(editTextIntervalo.getText().toString()));
        patientDrug.setTime(new Date());

        if(toggleButtonTipoIntervalo.isChecked()) {
            patientDrug.setIntervalType(PatientDrug.IntervalType.MINUTES);
        } else {
            patientDrug.setIntervalType(PatientDrug.IntervalType.HOURS);
        }

        JSONObject jsonObject = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonObject = new JSONObject(objectMapper.writeValueAsString(patientDrug));
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
                builder.setTitle("Erro ao salvar o medicamento");
                builder.setMessage("Nao foi possivel salvar o medicamento.");
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

        if(editTextIntervalo.getText().toString().isEmpty()) {
            editTextIntervalo.setError("O intervalo deve ser informado");
            retorno = false;
        }

        if(spinnerMedicamento.getSelectedItem() == null) {
            ((TextView) spinnerMedicamento.getSelectedView()).setError("O medicamento deve ser informado");
            retorno = false;
        }

        if(patient == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Erro ao salvar o medicamento");
            builder.setMessage("Selecione um paciente.");
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

    private void clear() {
        editTextIntervalo.getText().clear();
        spinnerMedicamento.setSelection(0);
        toggleButtonTipoIntervalo.setChecked(true);

        Snackbar.make(cordinatorLayout, "Operação realizada com sucesso.", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

}
