package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Drug;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DrugListFragment extends ListFragment {

    public static final String FIELD_DRUG = "DRUG";
    private List<Drug> drugs;

    @Override
    public void onResume() {
        super.onResume();
        initDrugListRequest();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Intent intent = new Intent(getActivity(), DrugFormActivity.class);
        intent.putExtra(FIELD_DRUG, drugs.get(position));
        startActivity(intent);
    }

    private void initDrugListRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_drugs_list);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            drugs = Arrays.asList(mapper.readValue(response.toString(), Drug[].class));
                            setListAdapter(new DrugListAdapter(getActivity(), drugs));
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

    private class DrugListAdapter extends BaseAdapter {

        private Context context;
        private List<Drug> drugs;

        public DrugListAdapter(Context context, List<Drug> drugs) {
            this.context = context;
            this.drugs = drugs;
        }

        @Override
        public int getCount() {
            return drugs.size();
        }

        @Override
        public Object getItem(int position) {
            return drugs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Drug drug = drugs.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.list_view_drug_row_item, null);

            TextView txtvName = (TextView)view.findViewById(R.id.txtvDescription);
            txtvName.setText(drug.getDescription());

            if(drug.getStatus().equals(Drug.Status.INACTIVE)) {
                txtvName.setTextColor(Color.RED);
            }

            return view;
        }
    }

}
