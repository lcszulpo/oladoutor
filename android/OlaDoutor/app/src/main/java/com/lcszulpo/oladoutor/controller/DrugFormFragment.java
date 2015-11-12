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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Drug;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DrugFormFragment extends Fragment {

    private FloatingActionButton fab;
    private CoordinatorLayout cordinatorLayout;
    private EditText editTextDescricao;
    private Spinner spinnerAdministracao;
    private ToggleButton toggleButtonSituacao;

    private Drug drug;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drug_form, container, false);
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

    private void findViewsById() {
        editTextDescricao = (EditText) getActivity().findViewById(R.id.editTextDescricao);
        toggleButtonSituacao = (ToggleButton) getActivity().findViewById(R.id.toggleButtonSituacao);
        spinnerAdministracao = (Spinner) getActivity().findViewById(R.id.spinnerAdministracao);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        cordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.cordinatorLayout);
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
        toggleButtonSituacao.setChecked(true);
        List<Drug.AdministrationRoute> administrationRoutes = Arrays.asList(Drug.AdministrationRoute.values());
        ArrayAdapter<Drug.AdministrationRoute> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, administrationRoutes);
        spinnerAdministracao.setAdapter(adapter);
    }

    public void setDrug(Drug drug) {
        this.drug = drug;

        editTextDescricao.setText(drug.getDescription());
        if(drug.getStatus().equals(Drug.Status.ACTIVE)) {
            toggleButtonSituacao.setChecked(true);
        } else {
            toggleButtonSituacao.setChecked(false);
        }
        spinnerAdministracao.setSelection(drug.getAdministrationRoute().ordinal());
    }

    private Boolean validateRequiriedFields() {
        if(editTextDescricao.getText().toString().isEmpty()) {
            editTextDescricao.setError("A descrição deve ser informada");
            return false;
        }

        return true;
    }

    private void clear() {
        drug = null;

        editTextDescricao.setText("");
        editTextDescricao.setError(null);
        toggleButtonSituacao.setChecked(true);
        spinnerAdministracao.setSelection(0);

        Snackbar.make(cordinatorLayout, "Operação realizada com sucesso.", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    private void delete() {
        if(drug == null || drug.getId() == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Um medicamento não foi selecionado");
            builder.setMessage("Selecione um medicamento.");
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
                        getString(R.string.res_drugs_delete) +
                        drug.getId();

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
                builder.setTitle("Erro ao excluir o medicamento");
                builder.setMessage("Nao foi possivel excluir o medicamento.");
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
                        getString(R.string.res_drugs_save);

        if(drug == null) {
            drug = new Drug();
        }

        drug.setDescription(editTextDescricao.getText().toString());

        if(toggleButtonSituacao.isChecked()) {
            drug.setStatus(Drug.Status.ACTIVE);
        } else {
            drug.setStatus(Drug.Status.INACTIVE);
        }

        drug.setAdministrationRoute((Drug.AdministrationRoute) spinnerAdministracao.getSelectedItem());

        JSONObject jsonObject = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonObject = new JSONObject(objectMapper.writeValueAsString(drug));
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
                builder.setTitle("Erro ao tentar salvar um medicamento");
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
