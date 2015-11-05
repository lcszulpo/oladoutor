package com.lcszulpo.oladoutor.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lcszulpo.oladoutor.AppController;
import com.lcszulpo.oladoutor.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivityFragment extends Fragment {

    private static final String FILENAME = "url";

    private Button buttonConnect;
    private EditText editTextEndereco;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViewsById();
        initListeners();
        initFields();
    }

    private void initListeners() {
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initConnectionRequest();
            }
        });
    }

    private void findViewsById() {
        buttonConnect = (Button) getActivity().findViewById(R.id.buttonConnect);
        editTextEndereco = (EditText) getActivity().findViewById(R.id.editTextEndereco);
    }

    private void initFields() {
        String url = readUrlFile();

        if(url != null && !url.isEmpty()) {
            editTextEndereco.setText(url);
        }
    }

    private void initConnectionRequest() {
        final String url =
                getString(R.string.schema) +
                editTextEndereco.getText().toString() +
                        getString(R.string.door) +
                        getString(R.string.path) +
                        getString(R.string.res_connection);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Insere no arquivo para a próxima vez que eu abrir o aplicativo
                        writeUrlFile(editTextEndereco.getText().toString());
                        //Insere no singleton para utilizar posteriormente
                        AppController.getInstance().setDominio(editTextEndereco.getText().toString());
                        Intent i = new Intent(getActivity(), PatientListActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Verifique o endereço informado");
                builder.setMessage("Nao foi possivel realizar a conexao com o servidor");
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private void writeUrlFile(String url) {
        try {
            FileOutputStream outputStream = getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(url.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readUrlFile() {
        String url = null;

        try {
            FileInputStream inputStream = getContext().openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder out = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            url = out.toString();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }


}
