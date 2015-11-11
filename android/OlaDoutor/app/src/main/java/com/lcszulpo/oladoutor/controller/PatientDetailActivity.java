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
import android.widget.FrameLayout;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PatientDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Patient patient;
    private Encounter encounter;
    private FrameLayout frameLayout;
    private FrameLayout frameLayoutFragment;

    public static final String FIELD_PATIENT = "PATIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        initToolBar();

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayoutFragment = (FrameLayout) findViewById(R.id.frameLayoutFragment);

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
                        getString(R.string.res_encounters_find) +
                        patient.getId();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            encounter = mapper.readValue(response.toString(), Encounter.class);

                            PatientDetailFragment patientDetailFragment = (PatientDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_patient_detail);
                            patientDetailFragment.fillEncounter(encounter);

                            frameLayoutFragment.setVisibility(View.VISIBLE);
                            frameLayout.setVisibility(View.GONE);
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
                        patient.getId();

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

