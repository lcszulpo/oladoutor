package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PatientDrugListFragment extends ListFragment {

    private Patient patient;

    @Override
    public void onResume() {
        super.onResume();

        patient =
                (Patient) getActivity().
                        getIntent().
                        getSerializableExtra(PatientDetailActivity.FIELD_PATIENT);

        initPatientDrugListRequest();
    }

    private void initPatientDrugListRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_patient_drugs_list) +
                        patient.getId();

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            List<PatientDrug> patientDrugs = Arrays.asList(mapper.readValue(response.toString(), PatientDrug[].class));
                            setListAdapter(new PatientDrugListAdapter(getActivity(), patientDrugs));
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

    private class PatientDrugListAdapter extends BaseAdapter {

        private Context context;
        private List<PatientDrug> patientDrugs;

        public PatientDrugListAdapter(Context context, List<PatientDrug> patientDrugs) {
            this.context = context;
            this.patientDrugs = patientDrugs;
        }

        @Override
        public int getCount() {
            return patientDrugs.size();
        }

        @Override
        public Object getItem(int position) {
            return patientDrugs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final PatientDrug patientDrug = patientDrugs.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.list_view_patient_drug_row_item, null);

            TextView textViewMedicamento = (TextView)view.findViewById(R.id.textViewMedicamento);
            TextView textViewIntervalo = (TextView)view.findViewById(R.id.textViewIntervalo);
            TextView textViewTipoIntervalo = (TextView)view.findViewById(R.id.textViewTipoIntervalo);
            TextView textViewHorario = (TextView)view.findViewById(R.id.textViewHorario);
            Button buttonAplicacao = (Button)view.findViewById(R.id.buttonAplicacao);
            Button buttonExcluir = (Button)view.findViewById(R.id.buttonExcluir);

            textViewMedicamento.setText(patientDrug.getDrug().getDescription());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            textViewIntervalo.setText("Intervalo: " + String.valueOf(patientDrug.getInterval()) + " ");
            textViewTipoIntervalo.setText(patientDrug.getIntervalType().toString());
            textViewHorario.setText(format.format(patientDrug.getTime()));

            buttonExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initDeleteRequest(patientDrug);
                }
            });

            buttonAplicacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initSaveRequest(patientDrug);
                }
            });

            return view;
        }

        private void initDeleteRequest(PatientDrug patientDrug) {
            final String url =
                    getString(R.string.schema) +
                            AppController.getInstance().getDominio() +
                            getString(R.string.door) +
                            getString(R.string.path) +
                            getString(R.string.res_patient_drugs_delete) +
                            patientDrug.getId();

            StringRequest objectRequest = new StringRequest(Request.Method.DELETE, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            initPatientDrugListRequest();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Erro ao excluir o medicamento");
                    builder.setMessage("Nao foi possivel excluir o medicamento..");
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

        private void initSaveRequest(PatientDrug patientDrug) {
            final String url =
                    getString(R.string.schema) +
                            AppController.getInstance().getDominio() +
                            getString(R.string.door) +
                            getString(R.string.path) +
                            getString(R.string.res_patient_drugs_save);

            patientDrug.setTime(new Date());

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
                            initPatientDrugListRequest();
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

    }

}
