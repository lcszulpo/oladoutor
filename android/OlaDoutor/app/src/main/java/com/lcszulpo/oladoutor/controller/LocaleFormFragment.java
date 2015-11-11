package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class LocaleFormFragment extends Fragment {

    private EditText editTextDescricao;
    private ToggleButton toggleButtonSituacao;
    private Locale locale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_locale_form, container, false);
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
            case R.id.action_delete:
                delete();
                break;
            case R.id.action_clear:
                clear();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void delete() {
        if(locale == null || locale.getId() == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Uma localização não foi selecionada");
            builder.setMessage("Selecione uma localização.");
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            return;
        }

        initDeleteRequest();
    }

    private void initDeleteRequest() {
        final String url =
                getString(R.string.schema) +
                        AppController.getInstance().getDominio() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_locales_delete) +
                        locale.getId();

        StringRequest objectRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Erro ao excluir a localização");
                builder.setMessage("Nao foi possivel excluir a localização..");
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
                        getString(R.string.res_locales_save);

        if(locale == null) {
            locale = new Locale();
        }

        locale.setDescription(editTextDescricao.getText().toString());

        if(toggleButtonSituacao.isChecked()) {
            locale.setStatus(Locale.Status.ACTIVE);
        } else {
            locale.setStatus(Locale.Status.INACTIVE);
        }

        JSONObject jsonObject = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonObject = new JSONObject(objectMapper.writeValueAsString(locale));
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
                builder.setTitle("Erro ao consultar localizações");
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

    private void clear() {
        locale = null;

        editTextDescricao.setText("");
        editTextDescricao.setError(null);
        toggleButtonSituacao.setChecked(true);
    }

    private void findViewsById() {
        editTextDescricao = (EditText) getActivity().findViewById(R.id.editTextDescricao);
        toggleButtonSituacao = (ToggleButton) getActivity().findViewById(R.id.toggleButtonSituacao);
    }

    private void initFields() {
        toggleButtonSituacao.setChecked(true);
    }

    private Boolean validateRequiriedFields() {
        if(editTextDescricao.getText().toString().isEmpty()) {
            editTextDescricao.setError("A descrição deve ser informada");
            return false;
        }

        return true;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;

        editTextDescricao.setText(locale.getDescription());
        if(locale.getStatus().equals(Locale.Status.ACTIVE)) {
            toggleButtonSituacao.setChecked(true);
        } else {
            toggleButtonSituacao.setChecked(false);
        }
    }

}
