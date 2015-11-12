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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Patient;
import com.lcszulpo.oladoutor.model.PatientDrug;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;
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
//            Locale locale = patientDrugs.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.list_view_patient_drug_row_item, null);

            TextView textViewMedicamento = (TextView)view.findViewById(R.id.textViewMedicamento);
            TextView textViewIntervalo = (TextView)view.findViewById(R.id.textViewIntervalo);
            TextView textViewTipoIntervalo = (TextView)view.findViewById(R.id.textViewTipoIntervalo);
            TextView textViewHorario = (TextView)view.findViewById(R.id.textViewHorario);

            return view;
        }
    }

}
