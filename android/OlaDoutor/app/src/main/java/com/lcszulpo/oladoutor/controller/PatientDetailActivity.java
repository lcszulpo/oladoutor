package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Encounter;
import com.lcszulpo.oladoutor.model.Patient;
import com.lcszulpo.oladoutor.model.PatientDrug;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PatientDetailActivity extends AppCompatActivity {

    public static final String FIELD_PATIENT = "PATIENT";

    private Toolbar toolbar;
    private Patient patient;
    private Encounter encounter;
    private FrameLayout frameLayout;
    private FrameLayout frameLayoutFragment;
    private Spinner spinnerEncounters;
    private List<Encounter> encounters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        initToolBar();
        initFields();
        findViewsById();
        initListeners();
    }

    private void initListeners() {
        spinnerEncounters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                encounter = (Encounter) spinnerEncounters.getSelectedItem();
                PatientDetailFragment patientDetailFragment = (PatientDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_patient_detail);
                patientDetailFragment.fillEncounter(encounter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void findViewsById() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayoutFragment = (FrameLayout) findViewById(R.id.frameLayoutFragment);
        spinnerEncounters = (Spinner) findViewById(R.id.spinnerEncounters);
    }

    private void initFields() {
        patient = (Patient) getIntent().getSerializableExtra(FIELD_PATIENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_detail, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        initSearchRequest();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intentPatient = new Intent(this, PatientFormActivity.class);
                intentPatient.putExtra(FIELD_PATIENT, patient);
                startActivity(intentPatient);
                break;
            case R.id.action_new:
                Intent intentEncounter = new Intent(this, EncounterFormActivity.class);
                intentEncounter.putExtra(FIELD_PATIENT, patient);
                startActivity(intentEncounter);
                break;
            case R.id.action_delete:
                if(patient == null || patient.getId() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PatientDetailActivity.this);
                    builder.setTitle("Erro ao deletar o encontro");
                    builder.setMessage("O paciente não foi informado");
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    break;
                }

                if(encounter == null || encounter.getId() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PatientDetailActivity.this);
                    builder.setTitle("Erro ao deletar o encontro");
                    builder.setMessage("O paciente não possui encontros");
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    break;
                }

                initDeleteRequest();
                break;
            case R.id.action_drug:
                Intent intentDrug = new Intent(this, AddDrugActivity.class);
                intentDrug.putExtra(FIELD_PATIENT, patient);
                startActivity(intentDrug);
                break;
            case R.id.action_view_drugs:
                Intent intentPatientDrug = new Intent(this, PatientDrugListActivity.class);
                intentPatientDrug.putExtra(FIELD_PATIENT, patient);
                startActivity(intentPatientDrug);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.title_patient_detail));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initSearchRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_encounters_list) +
                        patient.getId();

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            encounters = Arrays.asList(mapper.readValue(response.toString(), Encounter[].class));
                            ArrayAdapter<Encounter> adapter = new ArrayAdapter<Encounter>(PatientDetailActivity.this,
                                    R.layout.spinner_view_encounter_row_item, encounters);
                            spinnerEncounters.setAdapter(adapter);

                            if(encounters != null && !encounters.isEmpty()) {
                                encounter = encounters.get(0);
                                PatientDetailFragment patientDetailFragment = (PatientDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_patient_detail);
                                patientDetailFragment.fillEncounter(encounter);
                                frameLayoutFragment.setVisibility(View.VISIBLE);
                                frameLayout.setVisibility(View.GONE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!Integer.valueOf(404).equals(error.networkResponse.statusCode)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PatientDetailActivity.this);
                    builder.setTitle("Erro ao consultar o encontro");
                    builder.setMessage("Nao foi possivel consultar um encontro para o paciente");
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    frameLayoutFragment.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    private void initDeleteRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_encounters_delete) +
                        encounter.getId();

        StringRequest objectRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        initSearchRequest();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientDetailActivity.this);
                builder.setTitle("Erro ao excluir o encontro");

                if(!Integer.valueOf(404).equals(error.networkResponse.statusCode)) {
                    builder.setMessage("Nao foi possivel excluir o encontro.");
                } else {
                    builder.setMessage("Nao foram encontrado encontros para o paciente selecionado.");
                }

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        AppController.getInstance().addToRequestQueue(objectRequest);
    }

}

