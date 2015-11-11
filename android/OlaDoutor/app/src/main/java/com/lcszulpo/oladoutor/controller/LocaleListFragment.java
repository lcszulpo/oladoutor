package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.lcszulpo.oladoutor.model.Locale;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LocaleListFragment extends ListFragment {

    public static final String FIELD_LOCALE = "LOCALE";

    private List<Locale> locales;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                Intent intentLocale = new Intent(getActivity(), LocaleFormActivity.class);
                startActivity(intentLocale);
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

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Intent intent = new Intent(getActivity(), LocaleFormActivity.class);
        intent.putExtra(FIELD_LOCALE, locales.get(position));
        startActivity(intent);
    }

    private void initLocaleListRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_locales_list);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            locales = Arrays.asList(mapper.readValue(response.toString(), Locale[].class));
                            setListAdapter(new LocaleListAdapter(getActivity(), locales));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao consultar pacientes");
                builder.setMessage("Nao foi possivel consultar os pacientes do servidor");
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

    private class LocaleListAdapter extends BaseAdapter {

        private Context context;
        private List<Locale> locales;

        public LocaleListAdapter(Context context, List<Locale> locales) {
            this.context = context;
            this.locales = locales;
        }

        @Override
        public int getCount() {
            return locales.size();
        }

        @Override
        public Object getItem(int position) {
            return locales.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Locale locale = locales.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.list_view_locale_row_item, null);

            TextView txtvName = (TextView)view.findViewById(R.id.txtvDescription);
            txtvName.setText(locale.getDescription());

            return view;
        }
    }

}
