package com.lcszulpo.oladoutor.controller;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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
        initRequest();
    }

    private void findViewsById() {
        buttonConnect = (Button) getActivity().findViewById(R.id.buttonConnect);
        editTextEndereco = (EditText) getActivity().findViewById(R.id.editTextEndereco);
    }

    private void initRequest() {
        String url = "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
